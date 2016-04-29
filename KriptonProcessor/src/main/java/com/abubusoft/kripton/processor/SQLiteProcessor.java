package com.abubusoft.kripton.processor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.convert.UsedClass;
import com.abubusoft.kripton.processor.convert.ConvertGenerator;
import com.abubusoft.kripton.processor.core.KriptonClass;
import com.abubusoft.kripton.processor.core.KriptonAnalyzer;
import com.abubusoft.kripton.processor.core.KriptonProperty;
import com.abubusoft.kripton.processor.core.KriptonPropertyAnalyzer;
import com.abubusoft.kripton.processor.sqlite.ClassTableVisitor;
import com.abubusoft.kripton.processor.utils.StringUtility;

public class SQLiteProcessor extends AbstractProcessor {

	Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private KriptonAnalyzer kriptonCollections;

	private KriptonClass currentKriptonClass;

	private void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();
		annotations.add(BindType.class.getCanonicalName());

		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedSourceVersion()
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation. processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();

		kriptonCollections = new KriptonAnalyzer();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		try {
			kriptonCollections.clear();

			// Analizza tutti gli elementi annotati con Factory
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				// Verifichiamo che siano di tipo class
				if (item.getKind() != ElementKind.CLASS) {
					error(item, "Only classes can be annotated with @%s annotation", BindType.class.getSimpleName());
					return true;
				}

				StringBuilder buffer = new StringBuilder();
				TypeElement typeElement = (TypeElement) item;

				currentKriptonClass = new KriptonClass(typeElement);
				kriptonCollections.add(currentKriptonClass);

				analyzeClassHierarchy(buffer, typeElement, 0);
				buffer.append("\n");
				analyzeDeclaredField(buffer, typeElement, 0);

				// UsedClass adapterDefinition = new UsedClass(typeElement);
				 //ConvertGenerator.build(adapterDefinition, elements, filer);
				logger.info("Analizzo... \n" + buffer.toString());
			}

			// ora dobbiamo generare il codice
			ClassTableVisitor visitor=new ClassTableVisitor(elementUtils, filer);
			visitor.visit(kriptonCollections);
			
			logger.info(kriptonCollections.toString());
		} catch (Exception e) {
			error(null, e.getMessage());
		}

		return true;
	}

	protected void analyzeClassHierarchy(StringBuilder buffer, TypeElement clazz, int level) {
		String name = clazz.getQualifiedName().toString();
		// se siamo arrivati all'object, usciamo
		if (name.equals(Object.class.getCanonicalName()))
			return;

		String pad = StringUtility.repeatString("  ", level);
		buffer.append(pad + " " + name + "\n");

		TypeMirror superClassTypeMirror = clazz.getSuperclass();
		TypeElement superClassTypeElement = (TypeElement) ((DeclaredType) superClassTypeMirror).asElement();
		analyzeClassHierarchy(buffer, superClassTypeElement, level + 1);
	}

	protected void analyzeDeclaredField(StringBuilder buffer, TypeElement clazz, int level) throws MappingException, WriterException {
		List<? extends Element> list = elementUtils.getAllMembers(clazz);

		String pad = StringUtility.repeatString("  ", level);

		// elenco completo
		for (Element item : list) {
			{
				buffer.append(pad + " - " + item.getKind() + " " + item.getSimpleName() + " [" + item.asType() + "] " + item.getModifiers() + "\n");
			}
		}

		KriptonPropertyAnalyzer.analyze(currentKriptonClass, list);
		
		BinderWriter writer=BinderFactory.getJSONWriter(BinderOptions.build().indent(true));
		
		logger.info(writer.write(currentKriptonClass));
		
	}

	

}

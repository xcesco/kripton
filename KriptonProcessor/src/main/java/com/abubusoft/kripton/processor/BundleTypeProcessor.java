package com.abubusoft.kripton.processor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.annotation.BindTypeBundle;
import com.abubusoft.kripton.processor.bundle.BundleGenerator;
import com.abubusoft.kripton.processor.convert.UsedClass;


public class BundleTypeProcessor extends AbstractProcessor {

	private Elements elements;

	private Filer filer;

	private Messager messager;
	
	/**
	 * map of bundle to generate
	 */
	private Map<String, BundleDefinition> map = new LinkedHashMap<String, BundleDefinition>();

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
		annotations.add(BindTypeBundle.class.getCanonicalName());
					
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
		
		elements = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			System.out.println("entro");
			
			// different rounds can use this instance, so clear map before begin
			map.clear();
			
			// Analizza tutti gli elementi annotati con Factory
			for (Element item : roundEnv.getElementsAnnotatedWith(BindTypeBundle.class)) {
				// Verifichiamo che siano di tipo class
				if (item.getKind() != ElementKind.CLASS) {
					error(item, "Only classes can be annotated with @%s annotation",
							BindTypeBundle.class.getSimpleName());
					return true;
				}

				TypeElement typeElement = (TypeElement) item;
				UsedClass adapterDefinition = new UsedClass(typeElement);
				BundleGenerator.build(processingEnv, adapterDefinition, elements, filer);
			}
		} catch (Exception e) {
			error(null, e.getMessage());
		}

		return true;
	}

}

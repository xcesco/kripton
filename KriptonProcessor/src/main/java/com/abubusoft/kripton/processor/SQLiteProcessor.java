package com.abubusoft.kripton.processor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDatabaseSchema;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.android.annotation.SQLSelectBean;
import com.abubusoft.kripton.android.annotation.SQLSelectBeanList;
import com.abubusoft.kripton.android.annotation.SQLSelectScalar;
import com.abubusoft.kripton.android.annotation.SQLUpdate;
import com.abubusoft.kripton.android.annotation.SQLUpdateBean;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.sqlite.ClassTableGenerator;
import com.abubusoft.kripton.processor.sqlite.DaoDefinition;
import com.abubusoft.kripton.processor.sqlite.DaoGenerator;
import com.abubusoft.kripton.processor.sqlite.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.utils.StringUtility;

public class SQLiteProcessor extends AbstractProcessor {

	Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private SQLEntity currentKriptonClass;

	private SQLiteModel model;

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
		annotations.add(SQLDatabaseSchema.class.getCanonicalName());
		annotations.add(BindType.class.getCanonicalName());
		annotations.add(SQLDao.class.getCanonicalName());

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

		model = new SQLiteModel();
	}
	
	private int count;

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		count++;
		if (count>1)
		{
			logger.info("skip "+count);
			return true;
		}
		
		try {
			model.clear();
			Map<String, Element> bindElements = new HashMap<String, Element>();

			// Get all bind type
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					error(item, "Only class can be annotated with @%s annotation", BindType.class.getSimpleName());
					return true;
				}
				bindElements.put(item.toString(), item);
			}

			// Get all database schema definitions
			for (Element item : roundEnv.getElementsAnnotatedWith(SQLDatabaseSchema.class)) {
				if (item.getKind() != ElementKind.INTERFACE) {
					error(item, "Only interfaces can be annotated with @%s annotation", SQLDatabaseSchema.class.getSimpleName());
					return true;
				}

				ModelProperty property;
				List<String> classesIntoDatabase = AnnotationUtility.extractAsClassNameArray(elementUtils, item, SQLDatabaseSchema.class, "value");
				AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).build();
				AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();
				for (String c : classesIntoDatabase) {
					if (bindElements.containsKey(c)) {
						TypeElement classElement=(TypeElement) bindElements.get(c);
						
						currentKriptonClass = new SQLEntity(classElement);						
						AnnotationUtility.buildAnnotations(elementUtils, currentKriptonClass, classAnnotationFilter);
						
						if (currentKriptonClass.getAnnotation(BindAllFields.class)!=null)
						{
							PropertyUtility.buildProperties(elementUtils, currentKriptonClass, propertyAnnotationFilter);
						} else {
							PropertyUtility.buildProperties(elementUtils, currentKriptonClass, propertyAnnotationFilter, new PropertyCreatedListener() {
								
								@Override
								public boolean onProperty(ModelProperty property) {
									if (property.getAnnotation(Bind.class)!=null)
									{
										return true;
									}
									return false;
									
								}
							});	
						}
																		
						
						// check primary key
						property = currentKriptonClass.getPrimaryKey();
						if (property==null) throw(new SQLPrimaryKeyNotFoundException(currentKriptonClass));
						
						if (!property.getType().isSameType(Long.TYPE, Long.class)) throw(new SQLPrimaryKeyNotValidTypeException(currentKriptonClass, property));						
						//TypeUtility.isIn(property.getType().isSameType(value), classes)

						model.entityAdd(currentKriptonClass);
					}
				}
			}			

			// Get all dao definitions
			for (Element daoItem : roundEnv.getElementsAnnotatedWith(SQLDao.class)) {
				if (daoItem.getKind() != ElementKind.INTERFACE) {
					error(daoItem, "Only interfaces can be annotated with @%s annotation", SQLDao.class.getSimpleName());
					return true;
				}

				String entityClassName=AnnotationUtility.extractAsClassName(elementUtils, daoItem, SQLDao.class, "value");
				final DaoDefinition currentDaoDefinition = new DaoDefinition((TypeElement) daoItem, entityClassName);								
				
				if (!bindElements.containsKey(currentDaoDefinition.getEntityClassName()))
				{
					throw(new InvalidSQLDaoDefinitionException(currentDaoDefinition));
				}
				
				model.daoAdd(currentDaoDefinition);				
				logger.info("Dao... " + currentDaoDefinition.getName()+" "+entityClassName+" "+bindElements.containsKey(currentDaoDefinition.getEntityClassName()));				

				AnnotationUtility.forEachMethods(elementUtils, (TypeElement) daoItem, new MethodFoundListener() {

					@Override
					public void onMethod(Element element) {

						final SQLiteModelMethod currentMethod=new SQLiteModelMethod((ExecutableElement) element);	
						currentDaoDefinition.add(currentMethod);
						
						AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {

							@Override
							public void onAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

								if //@formatter:off
									(										
										   annotationClassName.equals(SQLInsert.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLInsertBean.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLUpdate.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLUpdateBean.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLSelectBean.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLSelectBeanList.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLSelectScalar.class.getCanonicalName())														
									)	
									//@formatter:on
								{
								} else {
									return;
								}

								ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);
								currentMethod.addAnnotation(annotation);

								logger.info("Annotation... " + annotation);
								logger.info("Method... " + currentMethod.toString());
							}
						});

					}

				});
			}
			
			// generate table java
			ClassTableGenerator.generate(elementUtils, filer, model);
			DaoGenerator.generate(elementUtils, filer, model);
			

			logger.info(model.toString());
		} catch (Exception e) {
			e.printStackTrace();

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

		PropertyUtility.buildProperties(elementUtils, currentKriptonClass,null);

		BinderWriter writer = BinderFactory.getJSONWriter(BinderOptions.build().indent(true));

		logger.info(writer.write(currentKriptonClass));

	}

}

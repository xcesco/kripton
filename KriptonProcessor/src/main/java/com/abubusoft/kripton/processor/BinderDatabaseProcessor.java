package com.abubusoft.kripton.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
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
import com.abubusoft.kripton.android.annotation.BindDaoDefinition;
import com.abubusoft.kripton.android.annotation.BindDatabaseSchema;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindDeleteBean;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindInsertBean;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindSelectBean;
import com.abubusoft.kripton.android.annotation.BindSelectBeanByListener;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.annotation.BuindUpdateBean;
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
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.sqlite.CursorGenerator;
import com.abubusoft.kripton.processor.sqlite.DaoGenerator;
import com.abubusoft.kripton.processor.sqlite.BindDatabaseGenerator;
import com.abubusoft.kripton.processor.sqlite.TableGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.AbsentAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoBindTypeElementsFound;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.sqlite.exceptions.TooManySQLPrimaryKeyFoundException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.StringUtility;

public class BinderDatabaseProcessor extends AbstractProcessor {

	public static boolean DEVELOP_MODE = false;

	Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private SQLEntity currentEntity;

	private SQLiteDatabaseSchema currentSchema;

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

		annotations.add(BindDatabaseSchema.class.getCanonicalName());
		annotations.add(BindType.class.getCanonicalName());
		annotations.add(BindDaoDefinition.class.getCanonicalName());

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

		try {
			count++;
			if (count > 1) {
				return true;
			}

			model.schemaClear();

			Map<String, Element> bindElements = new HashMap<String, Element>();

			// Put all @BindType elements in bindElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("Only class can be annotated with @%s annotation", BindType.class.getSimpleName());
					throw(new InvalidKindForAnnotationException(msg));
				}
				bindElements.put(item.toString(), item);
			}

			// No bind type is present
			if (bindElements.size() == 0) {
				throw (new NoBindTypeElementsFound());
			}

			ModelProperty property;

			// Get all database schema definitions
			for (Element databaseSchema : roundEnv.getElementsAnnotatedWith(BindDatabaseSchema.class)) {
				if (databaseSchema.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("Only interfaces can be annotated with @%s annotation", BindDatabaseSchema.class.getSimpleName());
					throw(new InvalidKindForAnnotationException(msg));
				}

				// get all entity used within SQLDatabaseSchema annotation
				List<String> classesIntoDatabase = AnnotationUtility.extractAsClassNameArray(elementUtils, databaseSchema, BindDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_VALUE);

				// check if any bean lack of @BindType annotation

				String schemaFileName = AnnotationUtility.extractAsString(elementUtils, databaseSchema, BindDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_FILENAME);
				int schemaVersion = AnnotationUtility.extractAsInt(elementUtils, databaseSchema, BindDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_VERSION);

				currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion);
				model.schemaAdd(currentSchema);

				// define which annotation the annotation processor is interested in
				AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).build();
				AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();
				// for each entity used in database
				for (String beanClassName : classesIntoDatabase) {
					if (!bindElements.containsKey(beanClassName)) {
						String msg = String.format("Class %s, used in %s DatabaseSchemaDefinition, is not marked with @%s annotation", beanClassName, databaseSchema.getSimpleName().toString(), BindType.class);						
						throw(new AbsentAnnotationException(msg));
					}
					
					// assert: bean is present
					TypeElement classElement = (TypeElement) bindElements.get(beanClassName);

					currentEntity = new SQLEntity(classElement);
					AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

					if (currentEntity.containsAnnotation(BindAllFields.class)) {
						PropertyUtility.buildProperties(elementUtils, currentEntity, propertyAnnotationFilter);
					} else {
						PropertyUtility.buildProperties(elementUtils, currentEntity, propertyAnnotationFilter, new PropertyCreatedListener() {

							@Override
							public boolean onProperty(ModelProperty property) {
								if (property.getAnnotation(Bind.class) != null) {
									return true;
								}
								return false;
							}
						});
					}
					
					if (currentEntity.getCollection().size()==0) {						
						String msg = String.format("Class %s, used in %s DatabaseSchemaDefinition, has no property!", beanClassName, databaseSchema.getSimpleName().toString());
						throw(new PropertyNotFoundException(msg));
					}
					
					if (currentEntity.countPrimaryKeys()>1)
					{
						throw (new TooManySQLPrimaryKeyFoundException(currentEntity));
					}

					// check primary key
					property = currentEntity.getPrimaryKey();
					if (property == null)
						throw (new SQLPrimaryKeyNotFoundException(currentEntity));

					if (!property.isType(Long.TYPE, Long.class))
						throw (new SQLPrimaryKeyNotValidTypeException(currentEntity, property));

					currentSchema.entityAdd(currentEntity);
				}
			}

			if (model.schemaCount() == 0) {
				// if no schema is found, exit, BUT THIS NOT AN ERROR
				String msg = String.format("No Database schema definition with @%s annotation was found", BindDatabaseSchema.class.getSimpleName());

				if (DEVELOP_MODE) {
					logger.log(Level.INFO, msg);
				}
				return true;
			}

			if (model.schemaCount() > 1) {
				// TODO improve schema management (more than one)
				String msg = String.format("Only one interface can be defined in project @%s annotation", BindDatabaseSchema.class.getSimpleName());
				error(null, msg);

				if (DEVELOP_MODE) {
					logger.log(Level.SEVERE, msg);
				}
				return true;
			}

			// define methods to ignore
			final Set<String> excludedMethods = new HashSet<String>();
			excludedMethods.add("wait");
			excludedMethods.add("notifyAll");
			excludedMethods.add("notify");
			excludedMethods.add("toString");
			excludedMethods.add("equals");
			excludedMethods.add("hashCode");
			excludedMethods.add("getClass");

			// Get all dao definitions
			for (Element daoItem : roundEnv.getElementsAnnotatedWith(BindDaoDefinition.class)) {
				if (daoItem.getKind() != ElementKind.INTERFACE) {
					error(daoItem, "Only interfaces can be annotated with @%s annotation", BindDaoDefinition.class.getSimpleName());
					return true;
				}

				String entityClassName = AnnotationUtility.extractAsClassName(elementUtils, daoItem, BindDaoDefinition.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
				final SQLDaoDefinition currentDaoDefinition = new SQLDaoDefinition((TypeElement) daoItem, entityClassName);

				if (!bindElements.containsKey(currentDaoDefinition.getEntityClassName())) {
					throw (new InvalidSQLDaoDefinitionException(currentDaoDefinition));
				}

				currentSchema.add(currentDaoDefinition);
				logger.info("Dao... " + currentDaoDefinition.getName() + " " + entityClassName + " " + bindElements.containsKey(currentDaoDefinition.getEntityClassName()));

				// create method for dao
				MethodUtility.forEachMethods(elementUtils, (TypeElement) daoItem, new MethodFoundListener() {

					@Override
					public void onMethod(ExecutableElement element) {
						if (excludedMethods.contains(element.getSimpleName().toString()))
							return;

						// add method
						final SQLiteModelMethod currentMethod = new SQLiteModelMethod((ExecutableElement) element);
						currentDaoDefinition.add(currentMethod);

						AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {




							@Override
							public void onAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

								if //@formatter:off
									(										
										   annotationClassName.equals(BindInsert.class.getCanonicalName()) 
										|| annotationClassName.equals(BindInsertBean.class.getCanonicalName())
										
										|| annotationClassName.equals(BindUpdate.class.getCanonicalName()) 
										|| annotationClassName.equals(BuindUpdateBean.class.getCanonicalName())
										
										|| annotationClassName.equals(BindDelete.class.getCanonicalName()) 
										|| annotationClassName.equals(BindDeleteBean.class.getCanonicalName())
										
										|| annotationClassName.equals(BindSelect.class.getCanonicalName())
										|| annotationClassName.equals(BindSelectBean.class.getCanonicalName()) 
										|| annotationClassName.equals(BindSelectBeanByListener.class.getCanonicalName()) 
																							
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

			if (currentSchema.getCollection().size() == 0) {
				String msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation", BindDaoDefinition.class.getSimpleName(), currentSchema.getElement().getSimpleName().toString(),
						BindDatabaseSchema.class.getSimpleName());
				if (DEVELOP_MODE) {
					logger.log(Level.SEVERE, msg);
				}
				error(null, msg);
				return true;
			}

			// generate table java
			TableGenerator.generate(elementUtils, filer, currentSchema);
			DaoGenerator.generate(elementUtils, filer, currentSchema);
			CursorGenerator.generate(elementUtils, filer, currentSchema);
			BindDatabaseGenerator.generate(elementUtils, filer, currentSchema);

			logger.info(currentSchema.toString());
		} catch (Exception e) {
			String msg = e.getMessage();
			error(null, msg);
			if (DEVELOP_MODE) {
				// logger.error(e.getMessage());
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

}

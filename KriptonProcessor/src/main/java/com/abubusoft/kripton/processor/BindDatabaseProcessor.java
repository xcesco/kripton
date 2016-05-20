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
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDatabase;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindDeleteRaw;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindInsertRaw;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.annotation.BindUpdateRaw;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.sqlite.BindCursorGenerator;
import com.abubusoft.kripton.processor.sqlite.BindDaoGenerator;
import com.abubusoft.kripton.processor.sqlite.BindDatabaseGenerator;
import com.abubusoft.kripton.processor.sqlite.TableGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.AbsentAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.sqlite.exceptions.MethodNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoBindTypeElementsFound;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.sqlite.exceptions.TooManySQLPrimaryKeyFoundException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.TypeName;

public class BindDatabaseProcessor extends AbstractProcessor {

	public static boolean DEVELOP_MODE = false;

	static Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private SQLEntity currentEntity;

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private HashSet<String> excludedMethods;

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

		annotations.add(BindDatabase.class.getCanonicalName());
		annotations.add(BindType.class.getCanonicalName());
		annotations.add(BindDao.class.getCanonicalName());

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

		// define methods to ignore
		excludedMethods = new HashSet<String>();
		excludedMethods.add("wait");
		excludedMethods.add("notifyAll");
		excludedMethods.add("notify");
		excludedMethods.add("toString");
		excludedMethods.add("equals");
		excludedMethods.add("hashCode");
		excludedMethods.add("getClass");
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

			final Map<String, Element> bindElements = new HashMap<String, Element>();

			// Put all @BindType elements in bindElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("Only class can be annotated with @%s annotation", BindType.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				bindElements.put(item.toString(), item);
			}

			// No bind type is present
			if (bindElements.size() == 0) {
				throw (new NoBindTypeElementsFound());
			}

			ModelProperty property;

			// Get all database schema definitions
			for (Element databaseSchema : roundEnv.getElementsAnnotatedWith(BindDatabase.class)) {
				if (databaseSchema.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("Interface %s: only interfaces can be annotated with @%s annotation", databaseSchema.getSimpleName().toString(), BindDatabase.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				if (!databaseSchema.getSimpleName().toString().endsWith("Database")) {
					String msg = String.format("Interface %s: interface marked with @%s annotation must have a name with suffix \"Database\"", databaseSchema.getSimpleName().toString(), BindDatabase.class.getSimpleName());
					throw (new InvalidNameException(msg));
				}

				// get all entity used within SQLDatabaseSchema annotation
				List<String> classesIntoDatabase = AnnotationUtility.extractAsClassNameArray(elementUtils, databaseSchema, BindDatabase.class, AnnotationAttributeType.ATTRIBUTE_VALUE);

				// check if any bean lack of @BindType annotation

				String schemaFileName = AnnotationUtility.extractAsString(elementUtils, databaseSchema, BindDatabase.class, AnnotationAttributeType.ATTRIBUTE_FILENAME);
				int schemaVersion = AnnotationUtility.extractAsInt(elementUtils, databaseSchema, BindDatabase.class, AnnotationAttributeType.ATTRIBUTE_VERSION);

				currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion);
				model.schemaAdd(currentSchema);

				// define which annotation the annotation processor is interested in
				AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).build();
				AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();
				// for each entity used in database
				for (String beanClassName : classesIntoDatabase) {
					if (!bindElements.containsKey(beanClassName)) {
						String msg = String.format("Class %s, used in %s database definition, is not marked with @%s annotation", beanClassName, databaseSchema.getSimpleName().toString(), BindType.class);
						throw (new AbsentAnnotationException(msg));
					}

					// assert: bean is present
					TypeElement classElement = (TypeElement) bindElements.get(beanClassName);

					currentEntity = new SQLEntity(classElement);
					AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

					final boolean bindAllFields = currentEntity.containsAnnotation(BindAllFields.class);

					{
						PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<SQLProperty>() {

							@Override
							public SQLProperty createProperty(Element element) {
								return new SQLProperty(element);
							}
						}, propertyAnnotationFilter, new PropertyCreatedListener<SQLProperty>() {

							@Override
							public boolean onProperty(SQLProperty property) {	
								if (!bindAllFields && (property.getAnnotation(Bind.class) == null && property.getAnnotation(BindColumn.class) != null))
								{
									String msg = String.format("In class '%s', property '%s' needs '%s' annotation", currentEntity.getSimpleName(), property.getName(), Bind.class.getSimpleName());									
									throw (new AbsentAnnotationException(msg));
								}
								
								if (bindAllFields || (property.getAnnotation(Bind.class)) != null) {																											
									TypeName name = TypeName.get(property.getElement().asType());

									LiteralType lt = LiteralType.of(name.toString());

									if (lt.isComposed()) {
										String msg = String.format("In class '%s', property '%s' is ignored in database build because it is composed", currentEntity.getSimpleName(), property.getName());
										info(msg);
										return false;
									}
									
									ModelAnnotation annotation=property.getAnnotation(BindColumn.class);
									if (annotation!=null)
									{																				
										property.setNullable(AnnotationUtility.extractAsBoolean(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_NULLABLE));
										ColumnType columnType=ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE));
										
										property.setPrimaryKey(columnType==ColumnType.PRIMARY_KEY);
									} else {
										property.setNullable(true);
									}
									
									return true;
								}

								return false;
							}
						});
					}
					
					// just to fix that property id can be the default PK without annotation.
					// this operation force primary key flag for property 
					SQLProperty primaryKey = currentEntity.getPrimaryKey();
					if (primaryKey!=null)
						primaryKey.setPrimaryKey(true);


					if (currentEntity.getCollection().size() == 0) {
						String msg = String.format("Class %s, used in %s database definition, has no property!", beanClassName, databaseSchema.getSimpleName().toString());
						throw (new PropertyNotFoundException(msg));
					}

					if (currentEntity.countPrimaryKeys() > 1) {
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
				String msg = String.format("No database definition with @%s annotation was found", BindDatabase.class.getSimpleName());

				if (DEVELOP_MODE) {
					logger.log(Level.INFO, msg);
				}
				return true;
			}

			if (model.schemaCount() > 1) {
				// TODO improve schema management (more than one)
				String msg = String.format("Only one interface can be defined in project @%s annotation", BindDatabase.class.getSimpleName());
				error(null, msg);

				if (DEVELOP_MODE) {
					logger.log(Level.SEVERE, msg);
				}
				return true;
			}

			// Get all dao definitions
			for (Element daoItem : roundEnv.getElementsAnnotatedWith(BindDao.class)) {
				if (daoItem.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation", daoItem.getSimpleName().toString(), BindDao.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				String entityClassName = AnnotationUtility.extractAsClassName(elementUtils, daoItem, BindDao.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
				final SQLDaoDefinition currentDaoDefinition = new SQLDaoDefinition((TypeElement) daoItem, entityClassName);

				// dao is associated to an entity is not contained in analyzed class set.
				if (!bindElements.containsKey(currentDaoDefinition.getEntityClassName())) {
					throw (new InvalidSQLDaoDefinitionException(currentDaoDefinition));
				}

				currentSchema.add(currentDaoDefinition);
				info("Dao... " + currentDaoDefinition.getName() + " " + entityClassName + " " + bindElements.containsKey(currentDaoDefinition.getEntityClassName()));

				// create method for dao
				MethodUtility.forEachMethods(elementUtils, (TypeElement) daoItem, new MethodFoundListener() {

					@Override
					public void onMethod(ExecutableElement element) {
						if (excludedMethods.contains(element.getSimpleName().toString()))
							return;

						final SQLiteModelMethod currentMethod = new SQLiteModelMethod((ExecutableElement) element);
						//TODO fix it: if return type is null, the method is inherited and probably it has bean type
						if (currentMethod.getReturnClass()==null)
						{							
							Element beanElement=bindElements.get(currentDaoDefinition.getEntityClassName());
							currentMethod.setReturnClass(beanElement.asType());
						}

						AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {

							@Override
							public void onAcceptAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

								if //@formatter:off
									(										
										   annotationClassName.equals(BindInsertRaw.class.getCanonicalName()) 
										|| annotationClassName.equals(BindInsert.class.getCanonicalName())
										
										|| annotationClassName.equals(BindUpdateRaw.class.getCanonicalName()) 
										|| annotationClassName.equals(BindUpdate.class.getCanonicalName())
										
										|| annotationClassName.equals(BindDeleteRaw.class.getCanonicalName()) 
										|| annotationClassName.equals(BindDelete.class.getCanonicalName())
										
										|| annotationClassName.equals(BindSelect.class.getCanonicalName())
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

								return;
							}
						});

						// add method
						currentDaoDefinition.add(currentMethod);

					}

				});

				// dao definition must have >0 method associated to query
				if (currentDaoDefinition.getCollection().size() == 0) {
					String msg = "Dao definition " + currentDaoDefinition.getName() + " contains no methods to bind queries";
					throw (new MethodNotFoundException(msg));
				}
			}

			if (currentSchema.getCollection().size() == 0) {
				String msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation", BindDao.class.getSimpleName(), currentSchema.getElement().getSimpleName().toString(),
						BindDatabase.class.getSimpleName());
				info(msg);
				error(null, msg);
				return true;
			}

			// generate table java
			TableGenerator.generate(elementUtils, filer, currentSchema);
			BindDaoGenerator.generate(elementUtils, filer, currentSchema);
			BindCursorGenerator.generate(elementUtils, filer, currentSchema);
			BindDatabaseGenerator.generate(elementUtils, filer, currentSchema);

			logger.info(currentSchema.toString());
		} catch (Exception e) {
			String msg = e.getMessage();
			error(null, msg);

			if (DEVELOP_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * Display message. Used only in develop mode
	 * 
	 * @param msg
	 *            message to display
	 */
	public static void info(String msg) {
		if (DEVELOP_MODE) {
			logger.info(msg);
		}

	}

}

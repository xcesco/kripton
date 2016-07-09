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
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
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
import com.abubusoft.kripton.processor.sqlite.BindAsyncTaskBuilder;
import com.abubusoft.kripton.processor.sqlite.BindCursorBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDaoBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDataSourceBuilder;
import com.abubusoft.kripton.processor.sqlite.TableGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.sqlite.exceptions.MethodNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoAnnotationFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoBindTypeElementsFound;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoDaoElementsFound;
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

public class BindDataSourceProcessor extends AbstractProcessor {

	public static boolean DEVELOP_MODE = false;

	static Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private HashSet<String> excludedMethods;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).build();
	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();
	// define which annotation the annotation processor is interested in
	private final Map<String, Element> globalBeanElements = new HashMap<String, Element>();
	private final Map<String, Element> globalDaoElements = new HashMap<String, Element>();

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

		annotations.add(BindDataSource.class.getCanonicalName());
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

			globalDaoElements.clear();
			globalBeanElements.clear();
			model.schemaClear();

			// Put all @BindType elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("Only class can be annotated with @%s annotation", BindType.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				globalBeanElements.put(item.toString(), item);
			}

			// Put all @BindDao elements in daoElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindDao.class)) {
				if (item.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("Only interface can be annotated with @%s annotation", BindDao.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				globalDaoElements.put(item.toString(), item);
			}

			// Get all database schema definitions
			Set<? extends Element> dataSets = roundEnv.getElementsAnnotatedWith(BindDataSource.class);
			// exit without error
			if (dataSets==null) return true;
			
			// No bind type is present
			if (globalBeanElements.size() == 0) {
				throw (new NoBindTypeElementsFound());
			}
			
			// No bind type is present
			if (globalDaoElements.size() == 0) {
				throw (new NoDaoElementsFound());
			}
			
			for (Element dataSource : dataSets) {
				
				createDataSource(dataSource);
				
				// get all dao used within SQLDatabaseSchema annotation
				List<String> daoIntoDataSource = AnnotationUtility.extractAsClassNameArray(elementUtils, dataSource, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_VALUE);

				// Analyze beans BEFORE daos, because beans are needed for DAO definition
				for (String daoName : daoIntoDataSource) {
					// check dao into bean definition
					createBeanFromDao(dataSource, daoName);

				} // end foreach bean

				// DAO analysis
				// Get all dao definitions
				for (String daoItem : daoIntoDataSource) {
					createDao(globalBeanElements, globalDaoElements, daoItem);
				}

				if (currentSchema.getCollection().size() == 0) {
					String msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation", BindDao.class.getSimpleName(), currentSchema.getElement().getSimpleName().toString(),
							BindDataSource.class.getSimpleName());
					info(msg);
					error(null, msg);
					return true;
				}

				buildClasses();

			} // end foreach dataSource

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
	 * Create bean definition for each dao definition contained in dataSource
	 * 
	 * @param dataSource
	 * @param daoName
	 */
	private void createBeanFromDao(Element dataSource, String daoName) {
		Element daoElement = globalDaoElements.get(daoName);
		if (daoElement == null) {
			String msg = String.format("Data source %s references a DAO %s without @BindDao annotation", dataSource.toString(), daoName);
			throw (new InvalidNameException(msg));
		}
		
		ModelProperty property;
		String beanName = AnnotationUtility.extractAsClassName(elementUtils, daoElement, BindDao.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
		Element beanElement = globalBeanElements.get(beanName);
		if (beanElement == null) {
			String msg = String.format("In dao definition %s is referred a bean definition %s without @BindType annotation", daoElement.toString(), beanName);
			throw (new InvalidNameException(msg));
		}				
		
		// assert: bean is present
		final SQLEntity currentEntity = new SQLEntity((TypeElement) beanElement);
		if (currentSchema.contains(currentEntity.getName())) {
			// bean already defined in datasource
			return;
		}
		currentSchema.addEntity(currentEntity);

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
					if (!bindAllFields && (property.getAnnotation(Bind.class) == null && property.getAnnotation(BindColumn.class) != null)) {
						String msg = String.format("In class '%s', property '%s' needs '%s' annotation", currentEntity.getSimpleName(), property.getName(), Bind.class.getSimpleName());
						throw (new NoAnnotationFoundException(msg));
					}

					if (bindAllFields || (property.getAnnotation(Bind.class)) != null) {
						TypeName name = TypeName.get(property.getElement().asType());

						LiteralType lt = LiteralType.of(name.toString());

						if (lt.isComposed()) {
							String msg = String.format("In class '%s', property '%s' is ignored in database build because it is composed", currentEntity.getSimpleName(), property.getName());
							info(msg);
							return false;
						}

						ModelAnnotation annotation = property.getAnnotation(BindColumn.class);
						if (annotation != null) {
							property.setNullable(AnnotationUtility.extractAsBoolean(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_NULLABLE));
							ColumnType columnType = ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE));

							property.setPrimaryKey(columnType == ColumnType.PRIMARY_KEY);
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
		if (primaryKey != null)
			primaryKey.setPrimaryKey(true);

		if (currentEntity.getCollection().size() == 0) {
			String msg = String.format("Bean class %s, used in %s database definition, has no property!", currentEntity.getName(), dataSource.getSimpleName().toString());
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

	}

	/**
	 * Build classes
	 * 
	 * @throws Exception
	 */
	public void buildClasses() throws Exception {
		// generate
		TableGenerator.generate(elementUtils, filer, currentSchema);
		BindDaoBuilder.execute(elementUtils, filer, currentSchema);
		BindCursorBuilder.execute(elementUtils, filer, currentSchema);
		BindAsyncTaskBuilder.generate(elementUtils, filer, currentSchema);
		BindDataSourceBuilder.generate(elementUtils, filer, currentSchema);
	}

	/**
	 * Create DAO definition
	 * 
	 * @param globalBeanElements
	 * @param globalDaoElements
	 * @param daoItem
	 */
	public void createDao(final Map<String, Element> globalBeanElements, final Map<String, Element> globalDaoElements, String daoItem) {
		Element daoElement = globalDaoElements.get(daoItem);

		if (daoElement.getKind() != ElementKind.INTERFACE) {
			String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation", daoElement.getSimpleName().toString(), BindDao.class.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}

		String entityClassName = AnnotationUtility.extractAsClassName(elementUtils, daoElement, BindDao.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
		final SQLDaoDefinition currentDaoDefinition = new SQLDaoDefinition(currentSchema, (TypeElement) daoElement, entityClassName);

		// dao is associated to an entity is not contained in analyzed class set.
		if (!globalBeanElements.containsKey(currentDaoDefinition.getEntityClassName())) {
			throw (new InvalidSQLDaoDefinitionException(currentDaoDefinition));
		}

		currentSchema.add(currentDaoDefinition);

		// create method for dao
		MethodUtility.forEachMethods(elementUtils, (TypeElement) daoElement, new MethodFoundListener() {

			@Override
			public void onMethod(ExecutableElement element) {
				if (excludedMethods.contains(element.getSimpleName().toString()))
					return;

				final SQLiteModelMethod currentMethod = new SQLiteModelMethod(currentDaoDefinition, (ExecutableElement) element);
				// TODO fix it: if return type is null, the method is inherited and probably it has bean type
				if (currentMethod.getReturnClass() == null) {
					Element beanElement = globalBeanElements.get(currentDaoDefinition.getEntityClassName());
					currentMethod.setReturnClass(beanElement.asType());
				}

				AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {


					@Override
					public void onAcceptAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

						if //@formatter:off
							(										
								annotationClassName.equals(BindInsert.class.getCanonicalName())																				
								|| annotationClassName.equals(BindUpdate.class.getCanonicalName())										
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

	/**
	 * @param databaseSchema
	 */
	public void createDataSource(Element databaseSchema) {
		if (databaseSchema.getKind() != ElementKind.INTERFACE) {
			String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation", databaseSchema.getSimpleName().toString(), BindDataSource.class.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}

		if (!databaseSchema.getSimpleName().toString().endsWith(BindDataSourceBuilder.SUFFIX)) {
			String msg = String.format("Interface marked with @%s annotation must have a name with suffix \"" + BindDataSourceBuilder.SUFFIX + "\" to be used with @BindDataSource", databaseSchema.getSimpleName().toString(),
					BindDataSource.class.getSimpleName());
			throw (new InvalidNameException(msg));
		}

		// go ahead to dataSource analysis
		// ASSERT: daoElement and beanElement is element for dao and bean associated
		String schemaFileName = AnnotationUtility.extractAsString(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_FILENAME);
		int schemaVersion = AnnotationUtility.extractAsInt(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_VERSION);
		boolean generateLog = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_LOG);
		currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion, generateLog);
		model.schemaAdd(currentSchema);
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

package com.abubusoft.kripton.processor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.FieldType;
import com.abubusoft.kripton.annotation.Bind;
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
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.exceptions.MethodNotFoundException;
import com.abubusoft.kripton.processor.exceptions.NoAnnotationFoundException;
import com.abubusoft.kripton.processor.exceptions.NoBindTypeElementsFound;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementsFound;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.exceptions.TooManySQLPrimaryKeyFoundException;
import com.abubusoft.kripton.processor.sqlite.BindAsyncTaskBuilder;
import com.abubusoft.kripton.processor.sqlite.BindCursorBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDaoBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDataSourceBuilder;
import com.abubusoft.kripton.processor.sqlite.TableGenerator;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.EnumTransform;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.TypeName;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

public class BindDataSourceProcessor extends BaseProcessor {

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindTable.class).build();
	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();

	private final Map<String, Element> globalDaoElements = new HashMap<String, Element>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());
		annotations.add(BindDataSource.class.getCanonicalName());
		annotations.add(BindTable.class.getCanonicalName());
		annotations.add(BindDao.class.getCanonicalName());

		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation. processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		model = new SQLiteModel();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		try {
			count++;
			if (count > 1) {
				return true;
			}

			globalDaoElements.clear();

			model.schemaClear();

			parseBindType(roundEnv);
			for (Element item : globalBeanElements.values()) {
				if (item.getKind() == ElementKind.ENUM) {
					Transformer.register(typeName(item), new EnumTransform(typeName(item)));
				}

			}

			// Put all @BindTable elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindTable.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(), item, BindTable.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				globalBeanElements.put(item.toString(), item);
			}

			// Put all @BindDao elements in daoElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindDao.class)) {
				if (item.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("%s %s, only interface can be annotated with @%s annotation", item.getKind(), item, BindDao.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				globalDaoElements.put(item.toString(), item);
			}

			// Get all database schema definitions
			Set<? extends Element> dataSets = roundEnv.getElementsAnnotatedWith(BindDataSource.class);
			// exit without error
			if (dataSets.size() == 0)
				return true;

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
					analyzeBeanFromDao(dataSource, daoName);

				} // end foreach bean

				// DAO analysis
				// Get all dao definitions
				for (String daoItem : daoIntoDataSource) {
					createDao(globalBeanElements, globalDaoElements, daoItem);
				}

				String msg;
				if (currentSchema.getCollection().size() == 0) {
					msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation", BindDao.class.getSimpleName(), currentSchema.getElement().getSimpleName().toString(),
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
	 * <p>
	 * Create bean definition for each dao definition contained in dataSource
	 * </p>
	 * 
	 * @param dataSource
	 * @param daoName
	 */
	private void analyzeBeanFromDao(Element dataSource, String daoName) {
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

		boolean temp1 = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);
		boolean temp2 = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindTable.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);

		if (!temp1 && temp2) {
			String msg = String.format("In class '%s', inconsistent value of attribute 'allFields' in annotations '%s' and '%s'", currentEntity.getSimpleName(), BindType.class.getSimpleName(), BindTable.class.getSimpleName());
			throw (new IncompatibleAttributesInAnnotationException(msg));
		}

		final boolean bindAllFields = temp1 && temp2;
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<SQLProperty>() {

				@Override
				public SQLProperty createProperty(Element element) {
					return new SQLProperty(currentEntity, element);
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

							// set transformer
							FieldType definitionType = FieldType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_FIELD_TYPE));
							if (definitionType != null) {
								switch (definitionType) {
								case NONE:
									break;
								case ENUM:
									Transformer.register(typeName(property.getElement()), new EnumTransform(typeName(property.getElement())));
								default:
									break;
								}

							}

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
	protected void buildClasses() throws Exception {
		TableGenerator.generate(elementUtils, filer, currentSchema);
		BindDaoBuilder.execute(elementUtils, filer, currentSchema);
		if (currentSchema.generateCursor)
			BindCursorBuilder.execute(elementUtils, filer, currentSchema);
		if (currentSchema.generateAsyncTask)
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
	protected void createDao(final Map<String, Element> globalBeanElements, final Map<String, Element> globalDaoElements, String daoItem) {
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
	 * @return databaseSchema
	 */
	protected String createDataSource(Element databaseSchema) {
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
		boolean generateAsyncTask = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_ASYNCTASK);
		boolean generateCursor = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ATTRIBUTE_CURSOR);

		currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion, generateLog, generateAsyncTask, generateCursor);
		model.schemaAdd(currentSchema);

		return currentSchema.getName();
	}

}

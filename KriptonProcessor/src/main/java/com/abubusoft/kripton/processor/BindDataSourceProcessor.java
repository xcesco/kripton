/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

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
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.FieldType;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.EnumBindTransform;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
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
import com.abubusoft.kripton.processor.exceptions.DaoDefinitionWithoutAnnotatedMethodException;
import com.abubusoft.kripton.processor.exceptions.InvalidBeanTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementsFound;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.exceptions.TooManySQLPrimaryKeyFoundException;
import com.abubusoft.kripton.processor.sqlite.BindAsyncTaskBuilder;
import com.abubusoft.kripton.processor.sqlite.BindCursorBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDaoBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDataSourceBuilder;
import com.abubusoft.kripton.processor.sqlite.BindTableGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.EnumSQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;

public class BindDataSourceProcessor extends BaseProcessor {

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindTable.class).build();

	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(BindDisabled.class).add(BindColumn.class).build();

	private final Map<String, Element> globalDaoElements = new HashMap<String, Element>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes
	 * ()
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
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation.
	 * processing.ProcessingEnvironment)
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
					// just for security
					BindTransformer.register(typeName(item), new EnumBindTransform(typeName(item)));
					SQLTransformer.register(typeName(item), new EnumSQLTransform(typeName(item)));
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
					String msg = String.format("%s %s can not be annotated with @%s annotation, because it is not an interface", item.getKind(), item, BindDao.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				globalDaoElements.put(item.toString(), item);
			}

			// Get all database schema definitions
			Set<? extends Element> dataSets = roundEnv.getElementsAnnotatedWith(BindDataSource.class);
			// exit without error
			if (dataSets.size() == 0)
				return true;

			// @BindType can be stored in other jars
			// No bind type is present
			// if (globalBeanElements.size() == 0) {
			// throw (new NoBindTypeElementsFound());
			// }

			// No bind type is present
			if (globalDaoElements.size() == 0) {
				throw (new NoDaoElementsFound());
			}

			for (Element dataSource : dataSets) {
				createDataSource(dataSource);

				// get all dao used within SQLDatabaseSchema annotation
				List<String> daoIntoDataSource = AnnotationUtility.extractAsClassNameArray(elementUtils, dataSource, BindDataSource.class, AnnotationAttributeType.DAO);

				// Analyze beans BEFORE daos, because beans are needed for DAO
				// definition
				for (String daoName : daoIntoDataSource) {
					// check dao into bean definition
					createBeanFromDao(dataSource, daoName);

				} // end foreach bean

				// DAO analysis
				// Get all dao definitions
				for (String daoItem : daoIntoDataSource) {
					createDao(globalBeanElements, globalDaoElements, daoItem);
				}

				String msg;
				if (currentSchema.getCollection().size() == 0) {
					msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation", BindDao.class.getSimpleName(),
							currentSchema.getElement().getSimpleName().toString(), BindDataSource.class.getSimpleName());
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
	private void createBeanFromDao(Element dataSource, String daoName) {
		Element daoElement = globalDaoElements.get(daoName);
		if (daoElement == null) {
			String msg = String.format("Data source %s references a DAO %s without @BindDao annotation", dataSource.toString(), daoName);
			throw (new InvalidNameException(msg));
		}

		ModelProperty property;
		String beanName = AnnotationUtility.extractAsClassName(elementUtils, daoElement, BindDao.class, AnnotationAttributeType.VALUE);
		Element beanElement = globalBeanElements.get(beanName);
		if (beanElement == null) {
			String msg = String.format("In dao definition %s is referred a bean definition %s without @BindType annotation", daoElement.toString(), beanName);
			throw (new InvalidNameException(msg));
		}

		// create equivalent entity in the domain of bind processor
		final BindEntity bindEntity = BindEntityBuilder.build(null, elementUtils, beanElement);
		// assert: bean is present
		final SQLEntity currentEntity = new SQLEntity((TypeElement) beanElement);
		if (currentSchema.contains(currentEntity.getName())) {
			// bean already defined in datasource
			return;
		}
		currentSchema.addEntity(currentEntity);

		AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ALL_FIELDS, Boolean.TRUE);
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<SQLProperty>() {

				@Override
				public SQLProperty createProperty(Element element) {
					return new SQLProperty(currentEntity, element);
				}
			}, propertyAnnotationFilter, new PropertyCreatedListener<SQLProperty>() {

				@Override
				public boolean onProperty(SQLProperty property) {
					if (property.hasAnnotation(BindDisabled.class)) {
						if (bindAllFields) {
							return false;
						} else {
							throw new InvalidDefinition("@BindDisabled can not be used with @BindType(allField=false)");
						}
					}

					ModelAnnotation annotationBindColumn = property.getAnnotation(BindColumn.class);
					if (annotationBindColumn != null && AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBindColumn, AnnotationAttributeType.ENABLED) == false) {
						return false;
					}

					if (!bindAllFields && annotationBindColumn == null) {
						return false;
					}

					if (annotationBindColumn != null) {
						property.setNullable(AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBindColumn, AnnotationAttributeType.NULLABLE));
						ColumnType columnType = ColumnType
								.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, property, annotationBindColumn, AnnotationAttributeType.COLUMN_TYPE));

						property.setPrimaryKey(columnType == ColumnType.PRIMARY_KEY);

						// set transformer for enumeration
						FieldType definitionType = FieldType
								.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, property, annotationBindColumn, AnnotationAttributeType.FIELD_TYPE));
						if (definitionType != null) {
							switch (definitionType) {
							case NONE:
								break;
							case ENUM:
								SQLTransformer.register(typeName(property.getElement()), new EnumSQLTransform(typeName(property.getElement())));
							}

						}

					} else {
						// primary key is set in other places
						property.setNullable(true);
						// ColumnType columnType = ColumnType.STANDARD;
					}

					if (bindEntity.contains(property.getName())) {
						BindProperty bindProperty = bindEntity.get(property.getName());
						if (bindProperty.isBindedArray() || bindProperty.isBindedCollection() || bindProperty.isBindedMap() || bindProperty.isBindedObject()) {
							property.bindProperty = bindProperty;
						}
					} else {
						throw (new KriptonRuntimeException(
								String.format("In class '%s' property '%s' has a wrong definition for create SQLite DataSource", bindEntity.getElement().asType(), property.getName())));
					}

					String columnName = null;
					ModelAnnotation columnAnnotation = property.getAnnotation(BindColumn.class);
					if (columnAnnotation != null) {
						columnName = columnAnnotation.getAttribute(AnnotationAttributeType.VALUE);
					}

					if (!StringUtils.hasText(columnName)) {
						columnName = property.getName();
					}
					
					
					// convert column name from field name to table: fieldName to field_name
					property.columnName = currentSchema.columnNameConverter.convert(columnName);

					return true;

				}
			});
		}

		// just to fix that property id can be the default PK without
		// annotation.
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
		BindTableGenerator.generate(elementUtils, filer, currentSchema);
		BindDaoBuilder.generate(elementUtils, filer, currentSchema);
		if (currentSchema.generateCursor)
			BindCursorBuilder.generate(elementUtils, filer, currentSchema);
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

		String entityClassName = AnnotationUtility.extractAsClassName(elementUtils, daoElement, BindDao.class, AnnotationAttributeType.VALUE);
		final SQLDaoDefinition currentDaoDefinition = new SQLDaoDefinition(currentSchema, (TypeElement) daoElement, entityClassName);

		// dao is associated to an entity is not contained in analyzed class
		// set.
		if (!globalBeanElements.containsKey(currentDaoDefinition.getEntityClassName())) {
			throw (new InvalidBeanTypeException(currentDaoDefinition));
		}

		currentSchema.add(currentDaoDefinition);

		// create method for dao
		MethodUtility.forEachMethods(elementUtils, (TypeElement) daoElement, new MethodFoundListener() {

			@Override
			public void onMethod(ExecutableElement element) {
				if (excludedMethods.contains(element.getSimpleName().toString()))
					return;

				final SQLiteModelMethod currentMethod = new SQLiteModelMethod(currentDaoDefinition, element);
				// TODO fix it: if return type is null, the method is inherited
				// and probably it has bean type
				if (currentMethod.getReturnClass() == null) {
					Element beanElement = globalBeanElements.get(currentDaoDefinition.getEntityClassName());
					currentMethod.setReturnClass(beanElement.asType());
				}

				AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {

					@Override
					public void onAcceptAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

						if // @formatter:off
						(annotationClassName.equals(BindSqlInsert.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlUpdate.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlDelete.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlSelect.class.getCanonicalName()))
						// @formatter:on
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
			throw (new DaoDefinitionWithoutAnnotatedMethodException(currentDaoDefinition));
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
			String msg = String.format("Interface %s marked with @%s annotation must have a name with suffix \"" + BindDataSourceBuilder.SUFFIX + "\" to be used with @BindDataSource",
					databaseSchema.getSimpleName().toString(), BindDataSource.class.getSimpleName());
			throw (new InvalidNameException(msg));
		}

		// go ahead to dataSource analysis
		// ASSERT: daoElement and beanElement is element for dao and bean
		// associated
		String schemaFileName = AnnotationUtility.extractAsString(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.FILENAME);
		int schemaVersion = AnnotationUtility.extractAsInt(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.VERSION);
		boolean generateLog = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.LOG);
		boolean generateAsyncTask = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.ASYNCTASK);
		boolean generateCursor = AnnotationUtility.extractAsBoolean(elementUtils, databaseSchema, BindDataSource.class, AnnotationAttributeType.CURSOR);

		currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion, generateLog, generateAsyncTask, generateCursor);
		model.schemaAdd(currentSchema);

		return currentSchema.getName();
	}

}

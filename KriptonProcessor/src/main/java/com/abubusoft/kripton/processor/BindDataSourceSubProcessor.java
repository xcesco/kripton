/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoGenerated;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.exceptions.DaoDefinitionWithoutAnnotatedMethodException;
import com.abubusoft.kripton.processor.exceptions.InvalidBeanTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementFound;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.exceptions.TooManySQLPrimaryKeyFoundException;
import com.abubusoft.kripton.processor.sqlite.BindAsyncTaskBuilder;
import com.abubusoft.kripton.processor.sqlite.BindContentProviderBuilder;
import com.abubusoft.kripton.processor.sqlite.BindCursorBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDaoBuilder;
import com.abubusoft.kripton.processor.sqlite.BindDataSourceBuilder;
import com.abubusoft.kripton.processor.sqlite.BindTableGenerator;
import com.abubusoft.kripton.processor.sqlite.SqlBuilderHelper;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelContentProvider;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.ClassName;

public class BindDataSourceSubProcessor extends BaseProcessor {

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(BindDisabled.class)
			.add(BindColumn.class).add(BindSqlAdapter.class).build();

	private final Map<String, TypeElement> globalDaoElements = new HashMap<String, TypeElement>();

	private Set<? extends Element> dataSets;

	private Set<SQLiteDatabaseSchema> schemas = new HashSet<>();

	public Pair<Set<GeneratedTypeElement>, Set<GeneratedTypeElement>> generatedPart;

	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.add(BindType.class);
		annotations.add(BindDataSource.class);
		annotations.add(BindTable.class);
		annotations.add(BindDao.class);
		annotations.add(BindDaoMany2Many.class);
		annotations.add(BindDaoGenerated.class);

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
		globalDaoElements.clear();
		model.schemaClear();

		parseBindType(roundEnv);

		// Put all @BindTable elements in beanElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindTable.class)) {
			if (item.getKind() != ElementKind.CLASS) {
				String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(),
						item, BindTable.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalBeanElements.put(item.toString(), (TypeElement) item);
		}

		// Put all @BindDao elements in daoElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindDao.class)) {
			// dao generated will used to replace original dao, so it can not be
			// inserted like others.
			if (item.getAnnotation(BindDaoGenerated.class) != null)
				continue;

			if (item.getKind() != ElementKind.INTERFACE) {
				String msg = String.format(
						"%s %s can not be annotated with @%s annotation, because it is not an interface",
						item.getKind(), item, BindDao.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalDaoElements.put(item.toString(), (TypeElement) item);
		}

		for (Element item : roundEnv.getElementsAnnotatedWith(BindDaoMany2Many.class)) {
			if (item.getKind() != ElementKind.INTERFACE) {
				String msg = String.format(
						"%s %s can not be annotated with @%s annotation, because it is not an interface",
						item.getKind(), item, BindDaoMany2Many.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalDaoElements.put(item.toString(), (TypeElement) item);
		}

		Set<? extends Element> generatedDaos = roundEnv.getElementsAnnotatedWith(BindDaoGenerated.class);
		for (Element item : generatedDaos) {
			String keyToReplace = AnnotationUtility.extractAsClassName(item, BindDaoGenerated.class,
					AnnotationAttributeType.DAO);
			globalDaoElements.put(keyToReplace, (TypeElement) item);
		}

		// Get all database schema definitions
		dataSets = roundEnv.getElementsAnnotatedWith(BindDataSource.class);
		// exit without error
		if (dataSets.size() == 0)
			return true;

		// No bind type is present
		if (globalDaoElements.size() == 0) {
			throw (new NoDaoElementFound());
		}

		// get elements for generated dao's
		// Set<? extends Element> generatedDaoParts =
		// roundEnv.getElementsAnnotatedWith(BindDaoGenerated.class);

		for (Element dataSource : dataSets) {
			createDataSource(dataSource);

			// get all dao used within SQLDatabaseSchema annotation
			List<String> daoIntoDataSource = AnnotationUtility.extractAsClassNameArray(elementUtils, dataSource,
					BindDataSource.class, AnnotationAttributeType.DAO_SET);

			// Analyze beans BEFORE daos, because beans are needed for DAO
			// definition
			for (String daoName : daoIntoDataSource) {
				// check dao into bean definition
				createBeanFromDao(dataSource, daoName);

			} // end foreach bean

			// DAO analysis
			// Get all dao definitions
			for (String generatedDaoItem : daoIntoDataSource) {
				analyzeDao(globalBeanElements, globalDaoElements, generatedDaoItem);
			}

			analyzeForeignKey(currentSchema);

			String msg;
			if (currentSchema.getCollection().size() == 0) {
				msg = String.format("No DAO definition with @%s annotation was found for class %s with @%s annotation",
						BindDao.class.getSimpleName(), currentSchema.getElement().getSimpleName().toString(),
						BindDataSource.class.getSimpleName());
				// info(msg);
				error(null, msg);
				return true;
			}

			schemas.add(currentSchema);
		} // end foreach dataSource
			// logger.info(currentSchema.toString());

		return true;
	}

	private void analyzeForeignKey(SQLiteDatabaseSchema schema) {
		for (SQLEntity entity : schema.getEntities()) {
			for (SQLProperty property : entity.getCollection()) {
				if (property.hasForeignKeyClassName()) {
					SQLEntity reference = schema.getEntity(property.foreignClassName);

					AssertKripton.asserTrueOrUnspecifiedBeanException(reference != null, schema, entity,
							property.foreignClassName);

					if (!entity.equals(reference)) {
						entity.referedEntities.add(reference);
					}
				}

			}
		}

		for (SQLDaoDefinition dao : schema.getCollection()) {
			if (dao.getElement().getAnnotation(BindDaoGenerated.class) != null) {
				ClassName entity1 = TypeUtility.className(AnnotationUtility.extractAsClassName(dao.getElement(),
						BindDaoGenerated.class, AnnotationAttributeType.ENTITY_1));
				ClassName entity2 = TypeUtility.className(AnnotationUtility.extractAsClassName(dao.getElement(),
						BindDaoGenerated.class, AnnotationAttributeType.ENTITY_2));

				// only if dao has an entity
				if (dao.getEntity() != null) {
					// check foreign key to entity1 and entity2
					checkForeignKeyForM2M(dao.getEntity(), entity1);
					checkForeignKeyForM2M(dao.getEntity(), entity2);
				}
			}
		}
	}

	private boolean isGeneratedEntity(String fullName) {
		for (GeneratedTypeElement item : this.generatedPart.value0) {
			if (item.getQualifiedName().equals(fullName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * <p>
	 * Create bean's definition for each dao definition contained in dataSource
	 * </p>
	 * 
	 * @param dataSource
	 * @param daoName
	 */
	private void createBeanFromDao(Element dataSource, String daoName) {
		TypeElement daoElement = globalDaoElements.get(daoName);

		if (daoElement == null) {
			String msg = String.format("Data source %s references a DAO %s without @BindDao annotation",
					dataSource.toString(), daoName);
			throw (new InvalidNameException(msg));
		}

		ModelProperty property;
		String beanName = AnnotationUtility.extractAsClassName(daoElement, BindDao.class,
				AnnotationAttributeType.VALUE);

		/*
		 * BindDaoMany2Many bindMany2ManyAnnotation =
		 * daoElement.getAnnotation(BindDaoMany2Many.class);
		 * AssertKripton.assertTrueOrMissedAnnotationOnClass(
		 * bindMany2ManyAnnotation != null ||
		 * !TypeUtility.isEquals(NoEntity.class, beanName), daoElement,
		 * beanName, BindType.class);
		 * 
		 * M2MEntity m2mEntity =
		 * M2MEntity.extractEntityManagedByDAO(daoElement); String
		 * m2mEntityClassName = m2mEntity.getQualifiedName();
		 * 
		 * if (isGeneratedEntity(m2mEntityClassName)) { // if generated, we
		 * don't need to recreate it return; }
		 */

		final TypeElement beanElement = globalBeanElements.get(beanName);

		// create equivalent entity in the domain of bind processor
		final BindEntity bindEntity = BindEntityBuilder.parse(null, beanElement);
		// assert: bean is present
		final SQLEntity currentEntity = new SQLEntity(currentSchema, bindEntity);
		if (currentSchema.contains(currentEntity.getName())) {
			// bean already defined in datasource
			return;
		}

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class,
				AnnotationAttributeType.ALL_FIELDS, Boolean.TRUE);
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<SQLEntity, SQLProperty>() {
				@Override
				public SQLProperty createProperty(SQLEntity entity, Element propertyElement) {
					return new SQLProperty(entity, propertyElement,
							AnnotationUtility.buildAnnotationList(propertyElement));
				}
			}, propertyAnnotationFilter, new PropertyCreatedListener<SQLEntity, SQLProperty>() {

				@Override
				public boolean onProperty(SQLEntity entity, SQLProperty property) {
					if (property.hasAnnotation(BindDisabled.class)) {
						if (bindAllFields) {
							return false;
						} else {
							throw new InvalidDefinition("@BindDisabled can not be used with @BindType(allField=false)");
						}
					}

					ModelAnnotation annotationBindColumn = property.getAnnotation(BindColumn.class);
					if (annotationBindColumn != null && AnnotationUtility.extractAsBoolean(property,
							annotationBindColumn, AnnotationAttributeType.ENABLED) == false) {
						return false;
					}

					if (!bindAllFields && annotationBindColumn == null) {
						return false;
					}

					if (annotationBindColumn != null) {
						property.setNullable(AnnotationUtility.extractAsBoolean(property, annotationBindColumn,
								AnnotationAttributeType.NULLABLE));
						ColumnType columnType = ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(property,
								annotationBindColumn, AnnotationAttributeType.COLUMN_TYPE));

						property.columnType = columnType;
						property.setPrimaryKey(columnType == ColumnType.PRIMARY_KEY);

						String foreignClassName = annotationBindColumn
								.getAttributeAsClassName(AnnotationAttributeType.FOREIGN_KEY);
						property.foreignClassName = foreignClassName;
						if (property.hasForeignKeyClassName() && property.columnType == ColumnType.PRIMARY_KEY) {
							AssertKripton.failIncompatibleAttributesInAnnotationException(
									"In class '%s' property '%s' can not be defined as PRIMARY KEY and FOREIGN KEY",
									bindEntity.getElement().asType(), property.getName());
						}

						ForeignKeyAction onDeleteAction = ForeignKeyAction
								.valueOf(AnnotationUtility.extractAsEnumerationValue(property, annotationBindColumn,
										AnnotationAttributeType.ON_DELETE));
						ForeignKeyAction onUpdateAction = ForeignKeyAction
								.valueOf(AnnotationUtility.extractAsEnumerationValue(property, annotationBindColumn,
										AnnotationAttributeType.ON_UPDATE));

						if (!property.hasForeignKeyClassName() && onDeleteAction != ForeignKeyAction.NO_ACTION) {
							String msg = String.format(
									"In class '%s', property '%s' defines 'onDelete' attribute but it is not a foreign key",
									bindEntity.getElement().asType(), property.getName());
							AssertKripton.failIncompatibleAttributesInAnnotationException(msg);
						}

						if (!property.hasForeignKeyClassName() && onUpdateAction != ForeignKeyAction.NO_ACTION) {
							String msg = String.format(
									"In class '%s', property '%s' defines 'onUpdate' attribute but it is not a foreign key",
									bindEntity.getElement().asType(), property.getName());
							AssertKripton.failIncompatibleAttributesInAnnotationException(msg);
						}

						property.onDeleteAction = onDeleteAction;
						property.onUpdateAction = onUpdateAction;

					} else {
						// primary key is set in other places
						property.setNullable(true);
						// ColumnType columnType = ColumnType.STANDARD;
						property.columnType = ColumnType.STANDARD;
					}

					if (bindEntity.contains(property.getName())) {
						BindProperty bindProperty = bindEntity.get(property.getName());
						if (bindProperty.isBindedArray() || bindProperty.isBindedCollection()
								|| bindProperty.isBindedMap() || bindProperty.isBindedObject()) {
							property.bindProperty = bindProperty;
						}
					} else {
						throw (new KriptonRuntimeException(String.format(
								"In class '%s' property '%s' has a wrong definition for create SQLite DataSource",
								bindEntity.getElement().asType(), property.getName())));
					}

					String columnName = null;
					if (annotationBindColumn != null) {
						columnName = annotationBindColumn.getAttribute(AnnotationAttributeType.VALUE);
					}

					if (!StringUtils.hasText(columnName)) {
						columnName = property.getName();
					}

					// convert column typeName from field typeName to table:
					// fieldName
					// to field_name
					property.columnName = currentSchema.columnNameConverter.convert(columnName);

					return true;

				}

			});
		}

		// just to fix that property id can be the default PK without
		// annotation.
		// this operation force primary key flag for property
		SQLProperty primaryKey = currentEntity.getPrimaryKey();
		if (primaryKey != null) {
			primaryKey.setPrimaryKey(true);
			primaryKey.columnType = ColumnType.PRIMARY_KEY;
			primaryKey.setNullable(false);
		}

		if (currentEntity.getCollection().size() == 0) {
			String msg = String.format("Class '%s', used in %s database definition, has no property!",
					currentEntity.getName(), dataSource.getSimpleName().toString());
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

		// add entity to schema after properties definition!
		currentSchema.addEntity(currentEntity);

	}

	/**
	 * @param m2mEntity
	 * @param currentEntity
	 */
	private void checkForeignKeyForM2M(final SQLEntity currentEntity, ClassName m2mEntity) {
		// check for m2m relationship
		if (m2mEntity != null) {
			SQLEntity temp = currentSchema.getEntity(m2mEntity.toString());
			AssertKripton.asserTrueOrForeignKeyNotFound(currentEntity.referedEntities.contains(temp), currentEntity,
					m2mEntity);
		}
	}

	/**
	 * Build classes
	 * 
	 * @throws Exception
	 */
	protected void generatedClasses() throws Exception {
		for (SQLiteDatabaseSchema currentSchema : schemas) {
			BindTableGenerator.generate(elementUtils, filer, currentSchema);
			BindDaoBuilder.generate(elementUtils, filer, model, currentSchema);
			if (currentSchema.generateCursor)
				BindCursorBuilder.generate(elementUtils, filer, currentSchema);
			if (currentSchema.generateAsyncTask)
				BindAsyncTaskBuilder.generate(elementUtils, filer, currentSchema);
			BindDataSourceBuilder.generate(elementUtils, filer, currentSchema);
			if (currentSchema.generateContentProvider) {
				BindContentProviderBuilder.generate(elementUtils, filer, currentSchema);
			}
		}
	}

	/**
	 * Create DAO definition
	 * 
	 * @param globalBeanElements
	 * @param globalDaoElements
	 * @param generatedDaoParts
	 * @param daoItem
	 */
	protected void analyzeDao(final Map<String, TypeElement> globalBeanElements,
			final Map<String, TypeElement> globalDaoElements, String daoItem) {
		Element daoElement = globalDaoElements.get(daoItem);

		if (daoElement.getKind() != ElementKind.INTERFACE) {
			String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation",
					daoElement.getSimpleName().toString(), BindDao.class.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}

		M2MEntity entity = M2MEntity.extractEntityManagedByDAO((TypeElement) daoElement);
		final SQLDaoDefinition currentDaoDefinition = new SQLDaoDefinition(currentSchema, daoItem,
				(TypeElement) daoElement, entity.getClassName().toString());

		// content provider management
		BindContentProviderPath daoContentProviderPath = daoElement.getAnnotation(BindContentProviderPath.class);
		if (daoContentProviderPath != null) {
			currentDaoDefinition.contentProviderEnabled = true;
			currentDaoDefinition.contentProviderPath = daoContentProviderPath.path();
			currentDaoDefinition.contentProviderTypeName = daoContentProviderPath.typeName();

			if (StringUtils.isEmpty(currentDaoDefinition.contentProviderTypeName)) {
				Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
				AssertKripton.assertTrue(currentDaoDefinition.getParent().contentProvider != null,
						"DAO '%s' has an inconsistent content provider definition, perhaps you forget to use @%s in data source interface?",
						currentDaoDefinition.getElement().getQualifiedName(),
						BindContentProvider.class.getSimpleName());
				currentDaoDefinition.contentProviderTypeName = currentDaoDefinition
						.getParent().contentProvider.authority + "."
						+ convert.convert(currentDaoDefinition.getSimpleEntityClassName());
			}
		}

		// dao is associated to an entity is not contained in analyzed class
		// set.
		if (!globalBeanElements.containsKey(currentDaoDefinition.getEntityClassName())
				&& !isGeneratedEntity(currentDaoDefinition.getEntityClassName())) {
			throw (new InvalidBeanTypeException(currentDaoDefinition));
		}

		currentSchema.add(currentDaoDefinition);

		fillMethods(currentDaoDefinition, daoElement);
		/*
		 * if (generatedDaoPart != null) {
		 * currentDaoDefinition.addImplementedInterface(TypeUtility.typeName(
		 * generatedDaoPart)); fillMethods(currentDaoDefinition,
		 * generatedDaoPart); }
		 */

		// get @annotation associated to many 2 many relationship
		BindDaoMany2Many daoMany2Many = daoElement.getAnnotation(BindDaoMany2Many.class);

		// dao definition must have >0 method associated to query
		if (currentDaoDefinition.getCollection().size() == 0 && daoMany2Many == null) {
			throw (new DaoDefinitionWithoutAnnotatedMethodException(currentDaoDefinition));
		}
	}

	/**
	 * @param currentDaoDefinition
	 * @param daoElement
	 */
	private void fillMethods(final SQLDaoDefinition currentDaoDefinition, Element daoElement) {
		// create method for dao
		SqlBuilderHelper.forEachMethods((TypeElement) daoElement, new MethodFoundListener() {

			@Override
			public void onMethod(ExecutableElement element) {
				if (excludedMethods.contains(element.getSimpleName().toString()))
					return;

				final List<ModelAnnotation> annotationList = new ArrayList<>();

				// optional annotations
				final List<ModelAnnotation> supportAnnotationList = new ArrayList<>();

				AnnotationUtility.forEachAnnotations(element, new AnnotationFoundListener() {

					@Override
					public void onAcceptAnnotation(Element element, String annotationClassName,
							Map<String, String> attributes) {

						if // @formatter:off
						(annotationClassName.equals(BindSqlInsert.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlUpdate.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlDelete.class.getCanonicalName())
								|| annotationClassName.equals(BindSqlSelect.class.getCanonicalName())
								|| annotationClassName.equals(BindContentProviderEntry.class.getCanonicalName()))
						// @formatter:on
						{
							ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);
							annotationList.add(annotation);
						}
						// we don't insert annotation

					}
				});

				// AssertKripton. assertTrue(annotationList.size()==1, "Dao
				// definition '%s' has method '%s' that is not correctly
				// annotated", currentDaoDefinition.getName(),
				// element.getSimpleName());
				annotationList.addAll(supportAnnotationList);
				final SQLiteModelMethod currentMethod = new SQLiteModelMethod(currentDaoDefinition, element,
						annotationList);

				addWithCheckMethod(currentDaoDefinition, currentMethod);
			}

			private void addWithCheckMethod(SQLDaoDefinition currentDaoDefinition, SQLiteModelMethod newMethod) {
				SQLiteModelMethod oldMethod = currentDaoDefinition.findByName(newMethod.getName());

				// ASSERT: same name and same number
				if (oldMethod != null && oldMethod.getParameters().size() == newMethod.getParameters().size()) {
					boolean sameParameters = true;
					for (int i = 0; i < oldMethod.getParameters().size(); i++) {
						if (!oldMethod.getParameters().get(i).value1.equals(newMethod.getParameters().get(i).value1)) {
							sameParameters = false;
							break;
						}
					}

					AssertKripton.failWithInvalidMethodSignException(sameParameters, newMethod,
							"conflict between generated method and declared method.");
				}

				// add method
				currentDaoDefinition.add(newMethod);

			}

		});
	}

	/**
	 * @param databaseSchema
	 * @return databaseSchema
	 */
	protected void createDataSource(Element databaseSchema) {
		if (databaseSchema.getKind() != ElementKind.INTERFACE) {
			String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation",
					databaseSchema.getSimpleName().toString(), BindDataSource.class.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}

		if (!databaseSchema.getSimpleName().toString().endsWith(BindDataSourceBuilder.SUFFIX)) {
			String msg = String.format(
					"Interface %s marked with @%s annotation must have a typeName with suffix \""
							+ BindDataSourceBuilder.SUFFIX + "\" to be used with @BindDataSource",
					databaseSchema.getSimpleName().toString(), BindDataSource.class.getSimpleName());
			throw (new InvalidNameException(msg));
		}

		// go ahead to dataSource analysis
		// ASSERT: daoElement and beanElement is element for dao and bean
		// associated
		String schemaFileName = AnnotationUtility.extractAsString(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.FILENAME);
		int schemaVersion = AnnotationUtility.extractAsInt(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.VERSION);
		boolean generateLog = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_LOG);
		boolean generateSchema = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_SCHEMA);
		boolean generateAsyncTask = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_ASYNC_TASK);
		boolean generateCursorWrapper = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_CURSOR_WRAPPER);

		currentSchema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName, schemaVersion,
				generateSchema, generateLog, generateAsyncTask, generateCursorWrapper);
		model.schemaAdd(currentSchema);

		// manage for content provider generation
		BindContentProvider contentProviderAnnotation = databaseSchema.getAnnotation(BindContentProvider.class);
		if (contentProviderAnnotation != null) {

			currentSchema.generateContentProvider = true;
			currentSchema.contentProvider = new SQLiteModelContentProvider();
			currentSchema.contentProvider.authority = contentProviderAnnotation.authority();
		} else {
			currentSchema.generateContentProvider = false;
		}

	}

	/**
	 * dao or entity can be null
	 * 
	 * @param dao
	 * @param entity
	 * @return
	 */
	public static String generateEntityName(SQLDaoDefinition dao, SQLEntity entity) {
		String entityName;
		if (entity == null) {
			M2MEntity m2mEntity = M2MEntity.extractEntityManagedByDAO(dao.getElement());
			entityName = m2mEntity.getSimpleName();
		} else {
			entityName = entity.getSimpleName();
		}
		return entityName;
	}

	public static String generateEntityQualifiedName(SQLDaoDefinition dao, SQLEntity entity) {
		String entityName;
		if (entity == null) {
			M2MEntity m2mEntity = M2MEntity.extractEntityManagedByDAO(dao.getElement());
			entityName = m2mEntity.getQualifiedName();
		} else {
			entityName = entity.getName().toString();
		}
		return entityName;
	}

}

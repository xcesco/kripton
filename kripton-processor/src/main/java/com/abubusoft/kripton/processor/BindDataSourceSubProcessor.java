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
import java.util.Collections;
import java.util.Comparator;
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

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.android.sqlite.NoPopulator;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.Touple;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
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
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.SqlAnalyzer;
import com.abubusoft.kripton.processor.sqlite.SqlBuilderHelper;
import com.abubusoft.kripton.processor.sqlite.SqlKeywordsHelper;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDeclarationType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmt_clausesContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLRelationType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelContentProvider;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * The Class BindDataSourceSubProcessor.
 */
public class BindDataSourceSubProcessor extends BaseProcessor {

	private static BindDataSourceSubProcessor instance;

	public static BindDataSourceSubProcessor getInstance() {
		return instance;
	}

	public Set<String> getSupportedOptions() {
		HashSet<String> result = new HashSet<>();

		result.add(KriptonOptions.SCHEMA_LOCATION_OPTIONS);
		result.add(KriptonOptions.ANDROID_X_OPTIONS);
		result.add(KriptonOptions.LOG_ENABLED_OPTIONS);		

		return result;
	}

	/** The property annotation filter. */
	private final AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(BindDisabled.class)
			.add(BindSqlColumn.class).add(BindSqlAdapter.class).add(BindSqlRelation.class).build();

	/** The global dao elements. */
	public final Map<String, TypeElement> globalDaoElements = new HashMap<String, TypeElement>();

	/** The data sets. */
	public Set<TypeElement> dataSets;

	/** The schemas. */
	public LinkedHashSet<SQLiteDatabaseSchema> schemas = new LinkedHashSet<>();

	/** The global dao generated. */
	public Set<String> globalDaoGenerated = new HashSet<String>();

	/** The generated daos. */
	public Set<GeneratedTypeElement> generatedDaos;

	/** The generated entities. */
	public Set<GeneratedTypeElement> generatedEntities;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor# getSupportedAnnotationClasses()
	 */
	@Override
	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.add(BindType.class);
		annotations.add(BindDataSource.class);
		annotations.add(BindDataSourceOptions.class);
		annotations.add(BindSqlType.class);
		annotations.add(BindDao.class);
		annotations.add(BindDaoMany2Many.class);
		annotations.add(BindGeneratedDao.class);

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor#clear()
	 */
	@Override
	public void clear() {
		super.clear();

		// currentSchema = null;
		dataSets = new HashSet<>();
		generatedDaos = null;
		generatedEntities = null;
		globalDaoElements.clear();
		schemas = new LinkedHashSet<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement dataSource : dataSets) {
			SQLiteDatabaseSchema currentSchema = createDataSource(dataSource);

			// Analyze beans BEFORE daos, because beans are needed for DAO
			// definition
			for (String daoName : currentSchema.getDaoNameSet()) {
				// check dao into bean definition
				createSQLEntityFromDao(currentSchema, dataSource, daoName);

			} // end foreach bean

			// sort table by its name, BEFORE inject generated tables and entities
			Collections.sort(currentSchema.getCollection(), new Comparator<SQLiteDaoDefinition>() {
				@Override
				public int compare(SQLiteDaoDefinition o1, SQLiteDaoDefinition o2) {
					return o1.getTableName().compareTo(o2.getTableName());
				}
			});

			// DAO analysis
			// Get all generated dao definitions
			for (String generatedDaoItem : currentSchema.getDaoNameSet()) {
				createSQLDaoDefinition(currentSchema, globalBeanElements, globalDaoElements, generatedDaoItem);
			}

			analyzeForeignKey(currentSchema);

			// Relation must be done AFTER foreign key building!!!
			analyzeRelations(currentSchema);

			// Analyze custom bean
			analyzeCustomBeanForSelect(currentSchema);

			if (currentSchema.getCollection().size() == 0) {
				AssertKripton.fail("DataSource class %s with @%s annotation has no defined DAOs",
						currentSchema.getElement().getSimpleName().toString(), BindDataSource.class.getSimpleName(),
						BindDao.class.getSimpleName());
				// info(msg);
				return true;
			}

			// for each dao definition, we define its uid
			int uid = 0;
			for (SQLiteDaoDefinition daoDefinition : currentSchema.getCollection()) {
				String daoFieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, daoDefinition.getName())
						+ "_UID";

				daoDefinition.daoUidName = daoFieldName;
				daoDefinition.daoUidValue = uid;

				uid++;
			}

			schemas.add(currentSchema);
		} // end foreach dataSource

		return true;
	}

	/**
	 * for each method with custom bean, check if:
	 * <ul>
	 * <li>bean is not included in schema</li>
	 * <li>method must have declared with explicit JQL</li>
	 * </ul>
	 * 
	 * @param currentSchema
	 */
	private void analyzeCustomBeanForSelect(SQLiteDatabaseSchema schema) {
		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			for (SQLiteModelMethod method : dao.getCollection()) {
				if (method.hasCustomProjection()) {
					SQLiteEntity entity = method.getEntity();
					AssertKripton.assertTrueOrInvalidMethodSignException(schema.getEntity(entity.getName()) == null,
							method, "'%s' must be read with its DAO", entity.getSimpleName());
					AssertKripton.assertTrueOrInvalidMethodSignException(
							method.jql.declarationType == JQLDeclarationType.JQL_EXPLICIT, method,
							"select with custom projection must be declared with explicit JQL");
				}
			}
		}
	}

	/**
	 * Analyze relation between entities
	 * 
	 * @param currentSchema
	 * @throws @throws
	 *             IOException
	 */
	private void analyzeRelations(SQLiteDatabaseSchema schema) {
		// resolve child entity
		for (SQLiteEntity entity : schema.getEntities()) {
			// if there is not relations, go on
			if (entity.relations.size() == 0)
				continue;

			for (Touple<SQLProperty, String, SQLiteEntity, SQLRelationType> item : entity.relations) {
				TypeName typeName = TypeUtility.typeName(item.value0.getElement());

				if (TypeUtility.isSet(typeName) || TypeUtility.isList(typeName)) {
					// ASSERT: list, set of type
					AssertKripton.assertTrueOfInvalidDefinition(
							((ParameterizedTypeName) typeName).typeArguments.size() == 1, item.value0, String.format(
									"invalid type for @%s annotated element", BindSqlRelation.class.getSimpleName()));

					typeName = ((ParameterizedTypeName) typeName).typeArguments.get(0);

					SQLiteEntity referredEntity = schema.getEntity(typeName.toString());

					checkForeignKeyContraint(entity, item, referredEntity);

					item.value2 = referredEntity;
					item.value3 = SQLRelationType.ONE_2_MANY;
				} else {
					SQLiteEntity referredEntity = schema.getEntity(typeName.toString());

					checkForeignKeyContraint(entity, item, referredEntity);

					item.value2 = referredEntity;
					item.value3 = SQLRelationType.ONE_2_ONE;
				}

				// get dao definition for entity
				SQLiteDaoDefinition parentDaoDefinition = schema.findDaoDefinitionForEntity(entity);

				for (final SQLiteModelMethod method : parentDaoDefinition.getCollection()) {
					if (method.hasChildrenSelects()) {
						// Pair<relation name, method name>
						for (Triple<String, String, SQLiteModelMethod> childrenSelect : method.childrenSelects) {
							final Touple<SQLProperty, String, SQLiteEntity, SQLRelationType> relation = entity
									.findRelationByParentProperty(childrenSelect.value0);

							AssertKripton.assertTrueOrInvalidMethodSignException(relation != null, method,
									" property '%s#%s' does not exits (referred by annotation @%s(%s='%s', %s='%s'))",
									entity.getSimpleName(), childrenSelect.value0,
									BindSqlChildSelect.class.getSimpleName(), AnnotationAttributeType.FIELD.getValue(),
									childrenSelect.value0, AnnotationAttributeType.METHOD.getValue(),
									childrenSelect.value1);

							final SQLiteDaoDefinition childDaoDefinition = schema
									.findDaoDefinitionForEntity(relation.value2);
							AssertKripton.assertTrueOrInvalidMethodSignException(childDaoDefinition != null, method,
									" dao for entity '%s', referred by @%s annotation, does not exists",
									relation.value2, BindSqlChildSelect.class.getSimpleName());

							final SQLiteModelMethod subMethod = childDaoDefinition.get(childrenSelect.value1);

							AssertKripton.assertTrueOrInvalidMethodSignException(subMethod != null, method,
									" method '%s#%s', referred by @%s annotation, does not exists",
									childDaoDefinition.getElement().getSimpleName().toString(), childrenSelect.value1,
									BindSqlChildSelect.class.getSimpleName());

							AssertKripton.assertTrueOrInvalidMethodSignException(subMethod.getParameters().size() == 1,
									method,
									" method '%s#%s', referred by annotation @%s(%s='%s', %s='%s'), can have only one parameter",
									childDaoDefinition.getTypeName(), subMethod.getName(),
									BindSqlChildSelect.class.getSimpleName(), AnnotationAttributeType.FIELD.getValue(),
									childrenSelect.value0, AnnotationAttributeType.METHOD.getValue(),
									childrenSelect.value1);

							// set sub method to invoke
							childrenSelect.value2 = subMethod;

							final String defaultConditionToTest1 = relation.value1 + "=" + SqlAnalyzer.PARAM_PREFIX
									+ subMethod.findParameterAliasByName(subMethod.getParameters().get(0).value0)
									+ SqlAnalyzer.PARAM_SUFFIX;
							final String defaultConditionToTest2 = SqlAnalyzer.PARAM_PREFIX
									+ subMethod.findParameterAliasByName(subMethod.getParameters().get(0).value0)
									+ SqlAnalyzer.PARAM_SUFFIX + "=" + relation.value1;

							final Set<String> conditionToTest = new HashSet<String>();
							String[] prefix = { "${", ":{", ":" };
							String[] suffix = { "}", "}", "" };

							for (int i = 0; i < prefix.length; i++) {
								final String conditionToTest1 = relation.value1 + "=" + prefix[i]
										+ subMethod.findParameterAliasByName(subMethod.getParameters().get(0).value0)
										+ suffix[i];
								final String conditionToTest2 = prefix[i]
										+ subMethod.findParameterAliasByName(subMethod.getParameters().get(0).value0)
										+ suffix[i] + "=" + relation.value1;

								conditionToTest.add(conditionToTest1);
								conditionToTest.add(conditionToTest2);
							}

							JQLChecker.getInstance().analyze(subMethod, subMethod.jql, new JqlBaseListener() {

								@Override
								public void enterWhere_stmt_clauses(Where_stmt_clausesContext ctx) {
									boolean found = false;

									for (String item : conditionToTest) {
										if (ctx.getText().contains(item)) {
											found = true;
										}
									}

									AssertKripton.assertTrueOrInvalidMethodSignException(found, method,
											" method '%s#%s' referred by @%s annotation must have a where condition like '%s' or '%s'",
											childDaoDefinition.getTypeName(), subMethod.getName(),
											BindSqlChildSelect.class.getSimpleName(), defaultConditionToTest1,
											defaultConditionToTest2);
								}

							});

							AssertKripton.assertTrueOrInvalidMethodSignException(
									TypeUtility.isEquals(subMethod.getParameters().get(0).value1,
											entity.getPrimaryKey().getPropertyType().getTypeName()),
									method,
									" method '%s#%s' referred by annotation @%s(%s='%s', %s='%s') has invalid parameter type ",
									childDaoDefinition.getTypeName(), subMethod.getName(),
									BindSqlChildSelect.class.getSimpleName(), AnnotationAttributeType.FIELD.getValue(),
									childrenSelect.value0, AnnotationAttributeType.METHOD.getValue(),
									childrenSelect.value1);

							TypeName parentFieldTypeName = relation.value0.getPropertyType().getTypeName();
							AssertKripton.assertTrueOrInvalidMethodSignException(

									(parentFieldTypeName.equals(subMethod.getReturnClass())
											|| (TypeUtility.isList(parentFieldTypeName) == TypeUtility
													.isList(subMethod.getReturnClass()))
													&& (TypeUtility.isSet(parentFieldTypeName) == TypeUtility
															.isSet(subMethod.getReturnClass()))),
									method, "field '%s#%s' is incompatible with '%s#%s' referred by @%s annotation ",
									TypeUtility.typeName(relation.value0.getParent().getElement()).toString(),
									relation.value0.getName(), childDaoDefinition.getTypeName(), childrenSelect.value1,
									BindSqlChildSelect.class.getSimpleName());

							// check existence of method
							AssertKripton.assertTrueOrInvalidMethodSignException(subMethod != null, method,
									"an nonexistent method '%s#%s' is referred by @%s annotation ",
									childDaoDefinition.getTypeName(), relation.value1,
									BindSqlChildSelect.class.getSimpleName());

							// check signature
							AssertKripton.assertTrueOrInvalidMethodSignException(subMethod.getParameters().size() == 1,
									method,
									" method '%s#%s' referred by @%s annotation can have one parameter binded to %s property",
									childDaoDefinition.getTypeName(), relation.value1,
									BindSqlChildSelect.class.getSimpleName(), relation.value1);

							// check parameter type
							AssertKripton.assertTrueOrInvalidMethodSignException(
									TypeUtility.isTypeIncludedIn(subMethod.getParameters().get(0).value1, Long.TYPE,
											Long.class, String.class),
									method,
									" method '%s#%s' referred by @%s annotation can have only one parameter of type String, Long or long",
									childDaoDefinition.getTypeName(), relation.value1,
									BindSqlChildSelect.class.getSimpleName(), relation.value1);

							SelectType selectSubMethodResultType = SelectBuilderUtility.detectSelectType(subMethod);
							// specified dao can only return bean of a type
							// TypeName returnTypeName =
							// method.getReturnClass();

							switch (relation.value3) {
							case ONE_2_MANY:
								// we can have a set or a list
								AssertKripton.assertTrueOrInvalidMethodSignException(
										selectSubMethodResultType == SelectType.LIST_BEAN, method,
										" method '%s#%s' referred by @%s annotation does not return an acceptable value from '%s' property",
										childDaoDefinition.getTypeName(), relation.value1,
										BindSqlChildSelect.class.getSimpleName(), relation.value1);
								break;
							case ONE_2_ONE:
								// we can receive only a bean
								AssertKripton.assertTrueOrInvalidMethodSignException(
										selectSubMethodResultType == SelectType.BEAN, method,
										" method '%s#%s' referred by @%s annotation does not return an acceptable value from '%s' property",
										childDaoDefinition.getTypeName(), relation.value1,
										BindSqlChildSelect.class.getSimpleName(), relation.value1);
								break;
							}

						}
					}
				}

			}
		}
	}

	private void checkForeignKeyContraint(SQLiteEntity entity,
			Touple<SQLProperty, String, SQLiteEntity, SQLRelationType> item, SQLiteEntity referredEntity) {
		// ASSERT: check valid type
		AssertKripton.assertTrueOfInvalidDefinition(referredEntity != null, item.value0,
				String.format("invalid type for @%s annotated element", BindSqlRelation.class.getSimpleName()));

		// ASSERT: check if child entity has field
		List<SQLProperty> foreignKeyPropertyList = referredEntity.getForeignKeysToEntity(entity, item.value1);
		AssertKripton.assertTrueOfInvalidDefinition(foreignKeyPropertyList.size() == 1, item.value0,
				String.format("@%s#%s need to specify a valid foreign key to entity '%s'",
						BindSqlRelation.class.getSimpleName(), AnnotationAttributeType.FOREIGN_KEY.getValue(),
						referredEntity.getName()));

		// set field name in case it is no specified
		if (!StringUtils.hasText(item.value1)) {
			item.value1 = foreignKeyPropertyList.get(0).getName();
		}

		SQLProperty foreignKey = referredEntity.get(item.value1);
		AssertKripton.assertTrueOfInvalidDefinition(
				TypeUtility.isEquals(entity.getPrimaryKey().getPropertyType().getTypeName(),
						foreignKey.getPropertyType().getTypeName()),
				item.value0,
				String.format("%s#%s is a foreign key to %s#%s: they have to be same type", referredEntity.getName(),
						foreignKey.getName(), entity.getName(), entity.getPrimaryKey().getName()));

	}

	/**
	 * Process second round.
	 *
	 * @param annotations
	 *            the annotations
	 * @param roundEnv
	 *            the round env
	 * @return true, if successful
	 */
	public boolean processSecondRound(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (SQLiteDatabaseSchema schema : schemas) {

			// Analyze beans BEFORE daos, because beans are needed for DAO
			// definition
			for (String daoName : schema.getDaoNameSet()) {
				// check dao into bean definition
				if (globalDaoGenerated.contains(daoName)) {
					createSQLEntityFromDao(schema, schema.getElement(), daoName);
					createSQLDaoDefinition(schema, globalBeanElements, globalDaoElements, daoName);
				}
			} // end foreach bean

		} // end foreach dataSource

		return true;
	}

	/**
	 * Analyze second round.
	 *
	 * @param annotations
	 *            the annotations
	 * @param roundEnv
	 *            the round env
	 * @return true, if successful
	 */
	public boolean analyzeSecondRound(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		parseBindType(roundEnv);

		// Put all @BindTable elements in beanElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindSqlType.class)) {
			if (item.getKind() != ElementKind.CLASS) {
				String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(),
						item, BindSqlType.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalBeanElements.put(item.toString(), (TypeElement) item);
		}

		// generate dao
		Set<? extends Element> generatedDaos = roundEnv.getElementsAnnotatedWith(BindGeneratedDao.class);
		for (Element item : generatedDaos) {
			String keyToReplace = AnnotationUtility.extractAsClassName(item, BindGeneratedDao.class,
					AnnotationAttributeType.DAO);
			globalDaoElements.put(keyToReplace, (TypeElement) item);
			globalDaoGenerated.add(keyToReplace);
		}

		return false;
	}

	/**
	 * Analyze round.
	 *
	 * @param annotations
	 *            the annotations
	 * @param roundEnv
	 *            the round env
	 * @return true, if successful
	 */
	public boolean analyzeRound(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		parseBindType(roundEnv);

		// Put all @BindSqlType elements in beanElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindSqlType.class)) {
			if (item.getKind() != ElementKind.CLASS) {
				String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(),
						item, BindSqlType.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalBeanElements.put(item.toString(), (TypeElement) item);
		}
		// we have generated bean too.

		// Put all @BindDao elements in daoElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindDao.class)) {
			// dao generated will used to replace original dao, so it can not be
			// inserted like others.
			if (item.getAnnotation(BindGeneratedDao.class) != null)
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

		// Get all database schema definitions
		for (Element item : roundEnv.getElementsAnnotatedWith(BindDataSource.class)) {
			dataSets.add((TypeElement) item);
		}

		// exit without error
		if (dataSets.size() == 0)
			return false;

		// No bind type is present
		if (globalDaoElements.size() == 0) {
			throw (new NoDaoElementFound());
		}

		return false;

	}

	/**
	 * Analyze foreign key.
	 *
	 * @param schema
	 *            the schema
	 */
	private void analyzeForeignKey(SQLiteDatabaseSchema schema) {
		for (SQLiteEntity entity : schema.getEntities()) {
			for (SQLProperty property : entity.getCollection()) {
				if (property.isForeignKey()) {
					SQLiteEntity reference = schema.getEntity(property.foreignParentClassName);

					AssertKripton.asserTrueOrUnspecifiedBeanException(reference != null, schema, entity,
							property.foreignParentClassName);

					if (!entity.equals(reference)) {
						entity.referedEntities.add(reference);
					}
				}

			}
		}

		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			if (dao.getElement().getAnnotation(BindDaoMany2Many.class) != null) {
				ClassName entity1 = TypeUtility.className(AnnotationUtility.extractAsClassName(dao.getElement(),
						BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_1));
				ClassName entity2 = TypeUtility.className(AnnotationUtility.extractAsClassName(dao.getElement(),
						BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_2));

				// only if dao has an entity
				if (dao.getEntity() != null) {
					// check foreign key to entity1 and entity2
					checkForeignKeyForM2M(schema, dao.getEntity(), entity1);
					checkForeignKeyForM2M(schema, dao.getEntity(), entity2);
				}
			}
		}
	}

	/**
	 * Checks if is generated entity.
	 *
	 * @param fullName
	 *            the full name
	 * @return true, if is generated entity
	 */
	private boolean isGeneratedEntity(String fullName) {
		for (GeneratedTypeElement item : this.generatedEntities) {
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
	 * .
	 *
	 * @param schema
	 *            the schema
	 * @param dataSource
	 *            the data source
	 * @param daoName
	 *            the dao name
	 * @return true, if successful
	 */
	private boolean createSQLEntityFromDao(final SQLiteDatabaseSchema schema, TypeElement dataSource, String daoName) {
		TypeElement daoElement = globalDaoElements.get(daoName);

		if (daoElement == null) {
			String msg = String.format("Data source %s references a DAO %s without @%s annotation",
					dataSource.toString(), daoName, BindDao.class.getSimpleName());
			throw (new InvalidNameException(msg));
		}

		String entityClassName = AnnotationUtility.extractAsClassName(daoElement, BindDao.class,
				AnnotationAttributeType.VALUE);

		// if there is no entity, exit now
		if (!StringUtils.hasText(entityClassName)) {
			return false;
		}

		final SQLiteEntity currentEntity = createSQLEntity(schema, daoElement, entityClassName, true);

		if (!schema.contains(currentEntity.getName())) {
			schema.addEntity(currentEntity);
		}

		return true;
	}

	/**
	 * @param schema
	 * @param dataSource
	 * @param parent.get
	 * @param beanElement
	 * @return
	 */
	public SQLiteEntity createSQLEntity(final SQLiteDatabaseSchema schema, TypeElement daoElement, String beanClassName,
			boolean primaryKeyCheck) {

		final TypeElement beanElement = globalBeanElements.get(beanClassName);

		AssertKripton.asserTrueOrMissedAnnotationOnClassException(beanElement != null, daoElement, beanClassName);

		ModelProperty property;
		// create equivalent entity in the domain of bind processor
		final BindEntity bindEntity = BindEntityBuilder.parse(null, beanElement);
		// assert: bean is present
		final SQLiteEntity currentEntity = new SQLiteEntity(schema, bindEntity);

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class,
				AnnotationAttributeType.ALL_FIELDS, Boolean.TRUE);
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity,
					new PropertyFactory<SQLiteEntity, SQLProperty>() {
						@Override
						public SQLProperty createProperty(SQLiteEntity entity, Element propertyElement) {
							return new SQLProperty(entity, propertyElement,
									AnnotationUtility.buildAnnotationList(propertyElement));
						}
					}, propertyAnnotationFilter, new PropertyCreatedListener<SQLiteEntity, SQLProperty>() {

						@Override
						public boolean onProperty(SQLiteEntity entity, SQLProperty property) {
							if (property.hasAnnotation(BindDisabled.class)) {
								if (bindAllFields) {
									return false;
								} else {
									throw new InvalidDefinition(
											String.format("@%s can not be used with @%s(allFields=false)",
													BindDisabled.class, BindType.class));
								}
							}

							ModelAnnotation annotationBindColumn = property.getAnnotation(BindSqlColumn.class);
							if (annotationBindColumn != null && AnnotationUtility.extractAsBoolean(property,
									annotationBindColumn, AnnotationAttributeType.ENABLED) == false) {
								return false;
							}

							if (!bindAllFields && annotationBindColumn == null) {
								return false;
							}

							if (property.hasAnnotation(BindSqlRelation.class)) {
								ModelAnnotation annotationBindRelation = property.getAnnotation(BindSqlRelation.class);
								// add relation (SQLEntity is for the moment set
								// to null
								AssertKripton.assertTrueOfInvalidDefinition(annotationBindColumn == null, property,
										String.format("annotations @%s and @%s can not be used together",
												BindSqlRelation.class.getSimpleName(),
												BindSqlColumn.class.getSimpleName()));
								entity.relations.add(new Touple<SQLProperty, String, SQLiteEntity, SQLRelationType>(
										property,
										annotationBindRelation.getAttribute(AnnotationAttributeType.FOREIGN_KEY), null,
										null));

								return false;
							}

							if (annotationBindColumn != null) {
								property.setNullable(AnnotationUtility.extractAsBoolean(property, annotationBindColumn,
										AnnotationAttributeType.NULLABLE));
								ColumnType columnType = ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(
										property, annotationBindColumn, AnnotationAttributeType.COLUMN_TYPE));

								// detect affinity type
								property.columnAffinityType = ColumnAffinityType
										.valueOf(AnnotationUtility.extractAsEnumerationValue(property,
												annotationBindColumn, AnnotationAttributeType.COLUMN_AFFINITY));

								property.columnType = columnType;
								property.setPrimaryKey((columnType == ColumnType.PRIMARY_KEY)
										|| (columnType == ColumnType.PRIMARY_KEY_UNMANGED));

								String parentClassName = annotationBindColumn
										.getAttributeAsClassName(AnnotationAttributeType.PARENT_ENTITY);
								property.foreignParentClassName = parentClassName;
								if (property.isForeignKey() && property.columnType == ColumnType.PRIMARY_KEY) {
									AssertKripton.failIncompatibleAttributesInAnnotationException(
											"In class '%s' property '%s' can not be defined as PRIMARY KEY and FOREIGN KEY",
											bindEntity.getElement().asType(), property.getName());
								}

								ForeignKeyAction onDeleteAction = ForeignKeyAction
										.valueOf(AnnotationUtility.extractAsEnumerationValue(property,
												annotationBindColumn, AnnotationAttributeType.ON_DELETE));
								ForeignKeyAction onUpdateAction = ForeignKeyAction
										.valueOf(AnnotationUtility.extractAsEnumerationValue(property,
												annotationBindColumn, AnnotationAttributeType.ON_UPDATE));

								if (!property.isForeignKey() && onDeleteAction != ForeignKeyAction.NO_ACTION) {
									String msg = String.format(
											"In class '%s', property '%s' defines 'onDelete' attribute but it is not a foreign key",
											bindEntity.getElement().asType(), property.getName());
									AssertKripton.failIncompatibleAttributesInAnnotationException(msg);
								}

								if (!property.isForeignKey() && onUpdateAction != ForeignKeyAction.NO_ACTION) {
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

							// convert column typeName from field typeName to
							// table:
							// fieldName
							// to field_name
							property.columnName = schema.columnNameConverter.convert(columnName);
							
							AssertKripton.fail(SqlKeywordsHelper.getInstance().isKeyword(property.columnName), "In class '%s' property '%s' can not use keyword '%s' as column name", bindEntity.getElement().asType(), property.getName(), property.columnName);

							return true;

						}

					});
		}

		// check for property count
		if (currentEntity.getCollection().size() == 0) {
			String msg = String.format("Class '%s', used in %s database definition, has no property!",
					currentEntity.getName(), schema.getElement().getSimpleName().toString());
			throw (new PropertyNotFoundException(msg));
		}

		if (primaryKeyCheck) {
			// just to fix that property id can be the default PK without
			// annotation.
			// this operation force primary key flag for property
			SQLProperty primaryKey = currentEntity.getPrimaryKey();
			if (primaryKey != null) {
				primaryKey.setPrimaryKey(true);

				if (!(primaryKey.columnType == ColumnType.PRIMARY_KEY
						|| primaryKey.columnType == ColumnType.PRIMARY_KEY_UNMANGED)) {
					primaryKey.columnType = ColumnType.PRIMARY_KEY;
				}
				primaryKey.setNullable(false);

				// if PK is String is mandatory that is UNMANAGED
				if (TypeUtility.isString(primaryKey.getPropertyType().getTypeName())) {
					primaryKey.columnType = ColumnType.PRIMARY_KEY_UNMANGED;
				}
			}

			if (currentEntity.countPrimaryKeys() > 1) {
				throw (new TooManySQLPrimaryKeyFoundException(currentEntity));
			}

			// check primary key
			property = currentEntity.getPrimaryKey();
			if (property == null)
				throw (new SQLPrimaryKeyNotFoundException(currentEntity));

			if (!property.isType(Long.TYPE, Long.class, String.class))
				throw (new SQLPrimaryKeyNotValidTypeException(currentEntity, property));
		}
		// order entities field by : first is PK, others are in natural order
		List<SQLProperty> properties = currentEntity.getCollection();
		Collections.sort(properties, new Comparator<SQLProperty>() {

			@Override
			public int compare(SQLProperty o1, SQLProperty o2) {
				if (o1.isPrimaryKey()) {
					return -1;
				} else if (o2.isPrimaryKey()) {
					return 1;
				} else {
					return o1.columnName.compareTo(o2.columnName);
				}
			}
		});

		// assert: we have a primary key
		// set the primary key at first place
		for (int i = 1; i < properties.size(); i++) {
			if (properties.get(i).isPrimaryKey()) {
				// swap
				SQLProperty temp = properties.get(0);
				properties.set(0, properties.get(i));
				properties.set(i, temp);
				break;
			}
		}
		
		AssertKripton.fail(SqlKeywordsHelper.getInstance().isKeyword(currentEntity.getTableName()), "Class '%s' can not use keyword '%s' as table name", bindEntity.getElement().asType(), currentEntity.getTableName());

		ImmutableUtility.buildConstructors(elementUtils, currentEntity);
		return currentEntity;
	}

	/**
	 * Check foreign key for M 2 M.
	 *
	 * @param currentSchema
	 *            the current schema
	 * @param currentEntity
	 *            the current entity
	 * @param m2mEntity
	 *            the m 2 m entity
	 */
	private void checkForeignKeyForM2M(SQLiteDatabaseSchema currentSchema, final SQLiteEntity currentEntity,
			ClassName m2mEntity) {
		// check for m2m relationship
		if (m2mEntity != null) {
			SQLiteEntity temp = currentSchema.getEntity(m2mEntity.toString());
			AssertKripton.asserTrueOrForeignKeyNotFound(currentEntity.referedEntities.contains(temp), currentEntity,
					m2mEntity);
		}
	}

	/**
	 * Build classes.
	 *
	 * @param roundEnv
	 *            the round env
	 * @throws Exception
	 *             the exception
	 */
	protected void generateClasses(RoundEnvironment roundEnv) throws Exception {
		for (SQLiteDatabaseSchema currentSchema : schemas) {
			// first of all, order table look for its dependencies
			BindTableGenerator.generate(elementUtils, filer, currentSchema, currentSchema.generatedEntities);
			BindDaoBuilder.generate(elementUtils, filer, currentSchema);
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
	 * Build classes.
	 *
	 * @param roundEnv
	 *            the round env
	 * @throws Exception
	 *             the exception
	 */
	protected void generateClassesSecondRound(RoundEnvironment roundEnv) throws Exception {
		for (SQLiteDatabaseSchema currentSchema : schemas) {
			BindDaoBuilder.generateSecondRound(elementUtils, filer, currentSchema);
		}
	}

	/**
	 * Create DAO definition.
	 *
	 * @param schema
	 *            the schema
	 * @param globalBeanElements
	 *            the global bean elements
	 * @param globalDaoElements
	 *            the global dao elements
	 * @param daoItem
	 *            the dao item
	 */
	protected void createSQLDaoDefinition(SQLiteDatabaseSchema schema,
			final Map<String, TypeElement> globalBeanElements, final Map<String, TypeElement> globalDaoElements,
			String daoItem) {
		Element daoElement = globalDaoElements.get(daoItem);

		if (daoElement.getKind() != ElementKind.INTERFACE) {
			String msg = String.format("Class %s: only interfaces can be annotated with @%s annotation",
					daoElement.getSimpleName().toString(), BindDao.class.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}

		M2MEntity entity = M2MEntity.extractEntityManagedByDAO((TypeElement) daoElement);

		// add to current schema generated entities too
		for (GeneratedTypeElement genItem : this.generatedEntities) {
			if (genItem.getQualifiedName().equals(entity.getQualifiedName())) {
				schema.generatedEntities.add(genItem);
			}
		}

		boolean generated = daoElement.getAnnotation(BindGeneratedDao.class) != null;

		final SQLiteDaoDefinition currentDaoDefinition = new SQLiteDaoDefinition(schema, daoItem,
				(TypeElement) daoElement, entity.getClassName().toString(), generated);

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

		schema.add(currentDaoDefinition);

		fillMethods(currentDaoDefinition, daoElement);

		// get @annotation associated to many 2 many relationship
		BindDaoMany2Many daoMany2Many = daoElement.getAnnotation(BindDaoMany2Many.class);

		// dao definition must have >0 method associated to query
		if (currentDaoDefinition.getCollection().size() == 0 && daoMany2Many == null) {
			throw (new DaoDefinitionWithoutAnnotatedMethodException(currentDaoDefinition));
		}
	}

	/**
	 * Fill methods.
	 *
	 * @param currentDaoDefinition
	 *            the current dao definition
	 * @param daoElement
	 *            the dao element
	 */
	private void fillMethods(final SQLiteDaoDefinition currentDaoDefinition, Element daoElement) {
		final One<Boolean> methodWithAnnotation = new One<Boolean>(false);

		// create method for dao
		SqlBuilderHelper.forEachMethods((TypeElement) daoElement, new MethodFoundListener() {

			@Override
			public void onMethod(ExecutableElement element) {
				if (excludedMethods.contains(element.getSimpleName().toString()))
					return;

				methodWithAnnotation.value0 = false;
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
							if (!annotationClassName.equals(BindContentProviderEntry.class.getCanonicalName())) {
								methodWithAnnotation.value0 = true;
							}

							ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);
							annotationList.add(annotation);
						}
						// we don't insert annotation

					}
				});

				annotationList.addAll(supportAnnotationList);
				final SQLiteModelMethod currentMethod = new SQLiteModelMethod(currentDaoDefinition, element,
						annotationList);

				AssertKripton.assertTrueOrInvalidMethodSignException(methodWithAnnotation.value0, currentMethod,
						"method must be annotated with @%s, @%s, @%s or @%s", BindSqlSelect.class.getSimpleName(),
						BindSqlInsert.class.getSimpleName(), BindSqlUpdate.class.getSimpleName(),
						BindSqlDelete.class.getSimpleName());
				// annotated", currentDaoDefinition.getName(),
				// element.getSimpleName());

				addWithCheckMethod(currentDaoDefinition, currentMethod);
			}

			private void addWithCheckMethod(SQLiteDaoDefinition currentDaoDefinition, SQLiteModelMethod newMethod) {
				SQLiteModelMethod oldMethod = currentDaoDefinition.findPropertyByName(newMethod.getName());

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
	 * Creates the data source.
	 *
	 * @param databaseSchema
	 *            the database schema
	 * @return databaseSchema
	 */
	protected SQLiteDatabaseSchema createDataSource(Element databaseSchema) {
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
		// manage global configuration for log
		if (!KriptonProcessor.LOG_GENERATION_ENABLED_MODE) {
			generateLog=false;
		}
		
		boolean generateSchema = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_SCHEMA);
		boolean generateAsyncTask = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_ASYNC_TASK);
		boolean generateCursorWrapper = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_CURSOR_WRAPPER);
		boolean generateRx = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSource.class,
				AnnotationAttributeType.GENERATE_RX);

		// get all dao used within SQLDatabaseSchema annotation
		List<String> daoIntoDataSource = AnnotationUtility.extractAsClassNameArray(elementUtils, databaseSchema,
				BindDataSource.class, AnnotationAttributeType.DAO_SET);

		String configCursorFactory = ReferredClasses.NO_CURSOR_FACTORY_CLASS_NAME;
		String configDatabaseErrorHandler = ReferredClasses.NO_DATABASE_ERROR_HANDLER_CLASS_NAME;
		String configDatabaseLifecycleHandler = ReferredClasses.NO_DATABASE_LIFECYCLE_HANDLER_CLASS_NAME;
		boolean configInMemory = false;
		boolean configLogEnabled = true;
		String configPopulatorClass = NoPopulator.class.getName();

		// manage for annotated data-source options
		BindDataSourceOptions dataSourceOptionsAnnotation = databaseSchema.getAnnotation(BindDataSourceOptions.class);
		if (dataSourceOptionsAnnotation != null) {
			configInMemory = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSourceOptions.class,
					AnnotationAttributeType.IN_MEMORY);
			configLogEnabled = AnnotationUtility.extractAsBoolean(databaseSchema, BindDataSourceOptions.class,
					AnnotationAttributeType.LOG_ENABLED);
			configPopulatorClass = AnnotationUtility.extractAsClassName(databaseSchema, BindDataSourceOptions.class,
					AnnotationAttributeType.POPULATOR);
			configCursorFactory = AnnotationUtility.extractAsClassName(databaseSchema, BindDataSourceOptions.class,
					AnnotationAttributeType.CURSOR_FACTORY);
			configDatabaseLifecycleHandler = AnnotationUtility.extractAsClassName(databaseSchema,
					BindDataSourceOptions.class, AnnotationAttributeType.DATABASE_LIFECYCLE_HANDLER);
		}

		SQLiteDatabaseSchema schema = new SQLiteDatabaseSchema((TypeElement) databaseSchema, schemaFileName,
				schemaVersion, generateSchema, generateLog, generateAsyncTask, generateCursorWrapper, generateRx,
				daoIntoDataSource, configCursorFactory, configDatabaseErrorHandler, configDatabaseLifecycleHandler,
				configInMemory, configLogEnabled, configPopulatorClass);

		// manage for content provider generation
		BindContentProvider contentProviderAnnotation = databaseSchema.getAnnotation(BindContentProvider.class);
		if (contentProviderAnnotation != null) {
			schema.generateContentProvider = true;
			schema.contentProvider = new SQLiteModelContentProvider();
			schema.contentProvider.authority = contentProviderAnnotation.authority();
		} else {
			schema.generateContentProvider = false;
		}

		return schema;

	}

	/**
	 * dao or entity can be null.
	 *
	 * @param dao
	 *            the dao
	 * @param entity
	 *            the entity
	 * @return the string
	 */
	public static String generateEntityName(SQLiteDaoDefinition dao, SQLiteEntity entity) {
		String entityName;
		if (entity == null) {
			M2MEntity m2mEntity = M2MEntity.extractEntityManagedByDAO(dao.getElement());
			entityName = m2mEntity.getSimpleName();
		} else {
			entityName = entity.getSimpleName();
		}
		return entityName;
	}

	/**
	 * Generate entity qualified name.
	 *
	 * @param dao
	 *            the dao
	 * @param entity
	 *            the entity
	 * @return the string
	 */
	public static String generateEntityQualifiedName(SQLiteDaoDefinition dao, SQLiteEntity entity) {
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

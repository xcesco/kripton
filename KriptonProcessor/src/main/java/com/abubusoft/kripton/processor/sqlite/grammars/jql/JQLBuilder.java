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
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.DELETE_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.DISTINCT_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.FROM_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.GROUP_BY_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.HAVING_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.INSERT_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.INTO_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.LIMIT_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.OFFSET_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.ORDER_BY_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.SELECT_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.SET_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.UPDATE_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.VALUES_KEYWORD;
import static com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords.WHERE_KEYWORD;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertType;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Columns_to_updateContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Conflict_algorithmContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Projected_columnsContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmt_clausesContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;

public abstract class JQLBuilder {

	public interface OnFieldListener {

		String onField(String item);

	}

	/**
	 * Listener on property iteraction
	 */
	interface OnMethodParameterListener {
		/**
		 * 
		 * @param item
		 * @return
		 */
		void onMethodParameter(VariableElement item);
	}

	/**
	 * Listener on property iteraction
	 */
	interface OnPropertyListener {
		/**
		 * 
		 * @param item
		 * @return
		 */
		void onProperty(SQLProperty item);
	}

	public static JQL buildJQL(SQLiteModelMethod method, String preparedJql) {
		final SQLDaoDefinition dao = method.getParent();
		Map<JQLDynamicStatementType, String> dynamicReplace = new HashMap<>();
		final JQL result = new JQL();

		// for each method's parameter
		forEachParameter(method, new OnMethodParameterListener() {

			@Override
			public void onMethodParameter(VariableElement item) {
				if (dao.getEntity().getElement().asType().equals(item.asType())) {
					result.paramBean = item.getSimpleName().toString();
				}
			}
		});

		if (method.hasAnnotation(BindSqlSelect.class)) {
			checkFieldsDefinitions(method, BindSqlSelect.class);
			return buildJQLSelect(method, result, dynamicReplace, preparedJql);
		} else if (method.hasAnnotation(BindSqlInsert.class)) {
			checkFieldsDefinitions(method, BindSqlInsert.class);
			return buildJQLInsert(method, result, preparedJql);
		} else if (method.hasAnnotation(BindSqlUpdate.class)) {
			checkFieldsDefinitions(method, BindSqlUpdate.class);
			return buildJQLUpdate(method, result, dynamicReplace, preparedJql);
		} else if (method.hasAnnotation(BindSqlDelete.class)) {
			return buildJQLDelete(method, result, dynamicReplace, preparedJql);
		}

		return null;
	}

	/**
	 * @param method
	 * @param annotation
	 */
	private static void checkFieldsDefinitions(SQLiteModelMethod method, Class<? extends Annotation> annotation) {
		List<String> includedFields = AnnotationUtility.extractAsStringArray(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.FIELDS);
		List<String> excludedFields = AnnotationUtility.extractAsStringArray(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.EXCLUDED_FIELDS);

		// both elements can not be defined
		if (includedFields.size() > 0 && excludedFields.size() > 0) {
			throw (new IncompatibleAttributesInAnnotationException(method.getParent(), method, method.getAnnotation(annotation), AnnotationAttributeType.FIELDS,
					AnnotationAttributeType.EXCLUDED_FIELDS));
		}
	}

	/**
	 * 
	 * <pre>
	 * DELETE person WHERE id = ${bean.id} AND #{where}
	 * </pre>
	 * 
	 * @param method
	 * @param preparedJql
	 * @return
	 */
	private static JQL buildJQLDelete(SQLiteModelMethod method, final JQL result, Map<JQLDynamicStatementType, String> dynamicReplace, String preparedJql) {
		final SQLDaoDefinition dao = method.getParent();

		if (StringUtils.hasText(preparedJql)) {
			// in DELETE SQL only where statement can contains bind parameter
			result.value = preparedJql;

			JQLChecker.getInstance().analyze(method, result, new JqlBaseListener() {

				@Override
				public void enterBind_parameter(Bind_parameterContext ctx) {
					result.bindParameterOnWhereStatementCounter++;
				}

				// // DELETE method can contains select only on where condition.
				// This mode is supported.
				// @Override
				// public void enterProjected_columns(Projected_columnsContext
				// ctx) {
				// result.containsSelectOperation = true;
				// }

			});

			// where management
			JQLChecker.getInstance().replaceVariableStatements(method, preparedJql, new JQLReplaceVariableStatementListenerImpl() {

				@Override
				public String onWhere(String statement) {
					result.annotatedWhere = true;
					result.staticWhereConditions = true;
					return null;
				}

			});

			// if (result.containsSelectOperation) {
			// AssertKripton.failWithInvalidMethodSignException(!TypeUtility.typeName(Void.TYPE).equals(method.getReturnClass()),
			// method,
			// "method that contains SQL with inner SELECT can return only
			// void");
			// }
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append(DELETE_KEYWORD + " " + FROM_KEYWORD);
			// entity name
			builder.append(" " + dao.getEntitySimplyClassName());

			builder.append(defineWhereStatement(method, result, BindSqlDelete.class, dynamicReplace));

			result.value = builder.toString();
		}
		result.operationType = JQLType.DELETE;
		result.dynamicReplace = dynamicReplace;
		return result;
	}

	/**
	 * <pre>
	 *  
	 * INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})
	 * </pre>
	 * 
	 * @param method
	 * @param preparedJql
	 * @return
	 */
	private static JQL buildJQLInsert(SQLiteModelMethod method, final JQL result, String preparedJql) {
		if (StringUtils.hasText(preparedJql)) {
			result.value = preparedJql;
			// INSERT can contains bind parameter in column values and select
			// statement
			final One<Boolean> inColumnValueSet = new One<Boolean>(false);
			final One<Boolean> inWhereStatement = new One<Boolean>(false);

			JQLChecker.getInstance().analyze(method, result, new JqlBaseListener() {

				@Override
				public void enterConflict_algorithm(Conflict_algorithmContext ctx) {
					result.conflictAlgorithmType = ConflictAlgorithmType.valueOf(ctx.getText().toUpperCase());
				}

				@Override
				public void enterProjected_columns(Projected_columnsContext ctx) {
					result.containsSelectOperation = true;
				}

				@Override
				public void enterWhere_stmt(Where_stmtContext ctx) {
					inWhereStatement.value0 = true;
				}

				@Override
				public void exitWhere_stmt(Where_stmtContext ctx) {
					inWhereStatement.value0 = false;
				}

				@Override
				public void enterColumn_value_set(Column_value_setContext ctx) {
					inColumnValueSet.value0 = true;
				}

				@Override
				public void exitColumn_value_set(Column_value_setContext ctx) {
					inColumnValueSet.value0 = false;
				}

				@Override
				public void enterBind_parameter(Bind_parameterContext ctx) {
					if (inWhereStatement.value0) {
						result.bindParameterOnWhereStatementCounter++;
					} else if (inColumnValueSet.value0) {
						result.bindParameterAsColumnValueCounter++;
					}

					AssertKripton.assertTrue(inWhereStatement.value0 || inColumnValueSet.value0, "unknown situation!");
				}

			});

			if (result.containsSelectOperation) {
				AssertKripton.assertTrueOrInvalidMethodSignException(method.getReturnClass().equals(TypeName.VOID), method, "defined JQL requires that method's return type is void");
			}

			// ASSERT: a INSERT-SELECT SQL can not contains parameters on values
			// section.
		} else {
			// use annotation's attribute value and exclude and bean definition
			// to
			final Class<? extends Annotation> annotation = BindSqlInsert.class;
			final SQLDaoDefinition dao = method.getParent();
			final boolean includePrimaryKey = AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.INCLUDE_PRIMARY_KEY);

			// define field list
			// every method parameter can be used only as insert field
			InsertType insertResultType = SqlInsertBuilder.detectInsertType(method);
			Set<String> fields;
			if (insertResultType == InsertType.INSERT_BEAN) {
				fields = extractFieldsFromAnnotation(method, annotation, includePrimaryKey);
			} else {
				fields = extractFieldsFromMethodParameters(method, annotation);
			}

			result.conflictAlgorithmType = ConflictAlgorithmType
					.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE));

			StringBuilder builder = new StringBuilder();
			builder.append(INSERT_KEYWORD);
			builder.append(" " + result.conflictAlgorithmType.getSqlForInsert());

			builder.append(INTO_KEYWORD);
			builder.append(" " + dao.getEntitySimplyClassName());

			builder.append(" (");
			builder.append(forEachFields(fields, new OnFieldListener() {

				@Override
				public String onField(String item) {
					return item;
				}
			}));
			builder.append(") ");

			builder.append(VALUES_KEYWORD);

			final One<String> prefix = new One<>("");
			if (result.hasParamBean()) {
				prefix.value0 = result.paramBean + ".";
			}

			builder.append(" (");
			builder.append(forEachFields(fields, new OnFieldListener() {

				@Override
				public String onField(String item) {
					return "${" + prefix.value0 + item + "}";
				}
			}));
			builder.append(")");

			result.value = builder.toString();
		}

		result.operationType = JQLType.INSERT;
		result.dynamicReplace = new HashMap<>();
		return result;
	}

	/**
	 * retrieve field used in query extracting them from method's parameter set,
	 * excluding parameters used as dynamic where, dynamic where args, and used
	 * in where attribute of annotation (if present).
	 * 
	 * @param method
	 * @param annotationClazz
	 * @return
	 */
	private static Set<String> extractFieldsFromMethodParameters(SQLiteModelMethod method, Class<? extends Annotation> annotationClazz) {
		String annotatedWhere = AnnotationUtility.extractAsString(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.WHERE);
		Set<String> annotatedFieldValues = new LinkedHashSet<>();

		Set<JQLPlaceHolder> parametersUsedInWhereConditions = new LinkedHashSet<>();
		if (StringUtils.hasText(annotatedWhere)) {
			parametersUsedInWhereConditions = JQLChecker.getInstance().extractPlaceHoldersFromVariableStatementAsSet(method, JQLKeywords.WHERE_KEYWORD + " " + annotatedWhere);
		}

		for (Pair<String, TypeName> param : method.getParameters()) {
			String nameUsed = method.findParameterAliasByName(param.value0);

			if (method.isThisDynamicWhereConditionsName(nameUsed)) {
				// if current parameter is dynamic where, skip it
				continue;
			}

			if (method.isThisDynamicWhereArgsName(nameUsed)) {
				// if current parameter is dynamic where, skip it
				continue;
			}

			if (!isInSet(nameUsed, parametersUsedInWhereConditions)) {
				annotatedFieldValues.add(nameUsed);
			}

		}

		return annotatedFieldValues;
	}

	private static JQL buildJQLSelect(SQLiteModelMethod method, final JQL result, Map<JQLDynamicStatementType, String> dynamicReplace, String preparedJql) {
		final Class<? extends Annotation> annotation = BindSqlSelect.class;
		final SQLDaoDefinition dao = method.getParent();

		if (StringUtils.hasText(preparedJql)) {
			result.value = preparedJql;

			// in SELECT SQL only where statement can contains bind parameter
			JQLChecker.getInstance().analyze(method, result, new JqlBaseListener() {

				@Override
				public void enterBind_parameter(Bind_parameterContext ctx) {
					result.bindParameterOnWhereStatementCounter++;
				}

			});

			JQLChecker.getInstance().replaceVariableStatements(method, preparedJql, new JQLReplaceVariableStatementListenerImpl() {

				@Override
				public String onWhere(String statement) {
					result.annotatedWhere = true;
					result.staticWhereConditions = true;
					return null;
				}

				@Override
				public String onOrderBy(String statement) {
					result.annotatedOrderBy = true;
					result.staticOrderBy = true;
					return null;
				}

				@Override
				public String onOffset(String statement) {
					result.annotatedOffset = true;
					return null;
				}

				@Override
				public String onLimit(String statement) {
					result.annotatedLimit = true;
					return null;
				}

				@Override
				public String onHaving(String statement) {
					result.annotatedHaving = true;
					return null;
				}

				@Override
				public String onGroup(String statement) {
					result.annotatedGroupBy = true;
					return null;
				}

			});

		} else {
			// extract some informaction from method and bean
			// use annotation's attribute value and exclude and bean definition
			// to
			// define field list
			final Set<String> fields = extractFieldsFromAnnotation(method, BindSqlSelect.class, true);

			boolean distinct = AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.DISTINCT);
			String annotatedGroupBy = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.GROUP_BY);
			String annotatedHaving = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.HAVING);

			StringBuilder builder = new StringBuilder();
			builder.append(SELECT_KEYWORD + " ");

			//
			if (distinct) {
				builder.append(DISTINCT_KEYWORD + " ");
			}

			// recreate fields
			builder.append(forEachFields(fields, new OnFieldListener() {

				@Override
				public String onField(String item) {
					return item;
				}
			}));

			// entity name
			builder.append(" " + FROM_KEYWORD + " " + dao.getEntitySimplyClassName());

			// where
			builder.append(defineWhereStatement(method, result, annotation, dynamicReplace));

			// group by
			if (StringUtils.hasText(annotatedGroupBy)) {
				result.annotatedGroupBy = true;
				builder.append(" " + GROUP_BY_KEYWORD + " " + annotatedGroupBy);
			}

			// having
			if (StringUtils.hasText(annotatedHaving)) {
				result.annotatedHaving = true;
				builder.append(" " + HAVING_KEYWORD + " " + annotatedHaving);
			}

			// order by
			builder.append(defineOrderByStatement(method, result, annotation, dynamicReplace));

			// limit
			builder.append(defineLimitStatement(method, result, annotation, dynamicReplace));

			result.value = builder.toString();
		}
		result.operationType = JQLType.SELECT;
		result.dynamicReplace = dynamicReplace;
		return result;
	}

	/**
	 * 
	 * <pre>
	 * UPDATE bean01 SET text=${text} WHERE id=${id}
	 * </pre>
	 * 
	 * @param method
	 * @param preparedJql
	 * @return
	 */
	private static JQL buildJQLUpdate(final SQLiteModelMethod method, final JQL result, Map<JQLDynamicStatementType, String> dynamicReplace, String preparedJql) {
		final Class<? extends Annotation> annotation = BindSqlUpdate.class;

		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to
		// define field list
		// final Set<String> fields = defineFields(JQLType.UPDATE, method,
		// annotation, false);

		if (StringUtils.hasText(preparedJql)) {
			result.value = preparedJql;
			// UPDATE can contains bind parameter in column values and select
			// statement
			final One<Boolean> inWhereCondition = new One<Boolean>(false);
			final One<Boolean> inColumnsToUpdate = new One<Boolean>(false);

			JQLChecker.getInstance().analyze(method, result, new JqlBaseListener() {

				@Override
				public void enterProjected_columns(Projected_columnsContext ctx) {
					if (inColumnsToUpdate.value0) {
						result.containsSelectOperation = true;
					}

				}

				@Override
				public void enterConflict_algorithm(Conflict_algorithmContext ctx) {
					result.conflictAlgorithmType = ConflictAlgorithmType.valueOf(ctx.getText().toUpperCase());
				}

				@Override
				public void enterWhere_stmt(Where_stmtContext ctx) {
					inWhereCondition.value0 = true;
				}

				@Override
				public void exitWhere_stmt_clauses(Where_stmt_clausesContext ctx) {
					inWhereCondition.value0 = false;
				}

				@Override
				public void enterBind_parameter(Bind_parameterContext ctx) {
					if (inWhereCondition.value0) {
						result.bindParameterOnWhereStatementCounter++;
					} else {
						result.bindParameterAsColumnValueCounter++;
					}
				}

				@Override
				public void enterColumns_to_update(Columns_to_updateContext ctx) {
					inColumnsToUpdate.value0 = true;
				}

				@Override
				public void exitColumns_to_update(Columns_to_updateContext ctx) {
					inColumnsToUpdate.value0 = false;
				}

			});

			JQLChecker.getInstance().replaceVariableStatements(method, preparedJql, new JQLReplaceVariableStatementListenerImpl() {

				@Override
				public String onWhere(String statement) {
					result.annotatedWhere = true;
					result.staticWhereConditions = true;
					return null;
				}

			});

			if (result.containsSelectOperation) {
				AssertKripton.assertTrueOrInvalidMethodSignException(method.getReturnClass().equals(TypeName.VOID), method, "defined JQL requires that method's return type is void");
			}

		} else {
			final SQLDaoDefinition dao = method.getParent();
			Set<String> fields;
			ModifyType modifyType = SqlModifyBuilder.detectModifyType(method, JQLType.UPDATE);
			if (modifyType == ModifyType.UPDATE_BEAN) {
				fields = extractFieldsFromAnnotation(method, annotation, false);
			} else {
				fields = extractFieldsFromMethodParameters(method, annotation);
			}

			AssertKripton.assertTrueOrInvalidMethodSignException(fields.size() > 0, method, "no field was specified for update");

			result.conflictAlgorithmType = ConflictAlgorithmType
					.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE));

			StringBuilder builder = new StringBuilder();
			builder.append(UPDATE_KEYWORD);
			builder.append(" " + result.conflictAlgorithmType.getSqlForInsert());
			// entity name
			builder.append(dao.getEntitySimplyClassName());

			// recreate fields
			final One<String> prefix = new One<>("");
			if (result.hasParamBean()) {
				prefix.value0 = result.paramBean + ".";
			}

			builder.append(" " + SET_KEYWORD + " ");

			builder.append(forEachFields(fields, new OnFieldListener() {

				@Override
				public String onField(String item) {
					return item + "=${" + prefix.value0 + item + "}";
				}
			}));

			builder.append(defineWhereStatement(method, result, annotation, dynamicReplace));

			result.value = builder.toString();
		}
		result.operationType = JQLType.UPDATE;
		result.dynamicReplace = dynamicReplace;
		return result;
	}

	/**
	 * @param method
	 * @param annotationClazz
	 * @param includePrimaryKey
	 * @param dao
	 * @param entity
	 * @return
	 */
	private static <A extends Annotation> LinkedHashSet<String> extractFieldsFromAnnotation(final SQLiteModelMethod method, Class<A> annotationClazz, final boolean includePrimaryKey) {
		final SQLDaoDefinition dao = method.getParent();
		final SQLEntity entity = method.getParent().getEntity();

		List<String> annotatedFieldValues = AnnotationUtility.extractAsStringArray(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.FIELDS);
		List<String> annotatedExcludedFieldValues = AnnotationUtility.extractAsStringArray(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.EXCLUDED_FIELDS);
		CollectionUtils.trim(annotatedFieldValues);
		CollectionUtils.trim(annotatedExcludedFieldValues);

		final One<Integer> count = new One<>(0);

		// extract properties from managed bean
		final Set<String> allFields = new LinkedHashSet<>();
		forEachFields(dao, new OnPropertyListener() {

			@Override
			public void onProperty(SQLProperty item) {
				if (!item.isPrimaryKey() || (item.isPrimaryKey() && includePrimaryKey)) {
					allFields.add(item.getName());
				}

				if (TypeUtility.isEquals(item.getPropertyType().getTypeName(), typeName(entity.getElement()))) {
					count.value0++;
				}
			}
		});

		// use attribute includedFields and excludedFields to mantain or remove
		// properties from selected set.
		if (annotatedFieldValues.size() == 0 && annotatedExcludedFieldValues.size() == 0) {
			// if no fields was selected, select all
			annotatedFieldValues.clear();
			annotatedFieldValues.addAll(allFields);
		} else if (annotatedExcludedFieldValues.size() > 0) {
			for (String fieldName : annotatedExcludedFieldValues) {
				if (!entity.contains(fieldName)) {
					AssertKripton.failUnknownPropertyInJQLException(method, annotationClazz, AnnotationAttributeType.EXCLUDED_FIELDS, fieldName);
				}
			}

			allFields.removeAll(annotatedExcludedFieldValues);

			annotatedFieldValues.clear();
			annotatedFieldValues.addAll(allFields);
		}
		LinkedHashSet<String> result = new LinkedHashSet<>();
		result.addAll(annotatedFieldValues);

		return result;
	}

	private static boolean isInSet(String value, Set<JQLPlaceHolder> parametersUsedInWhereConditions) {
		for (JQLPlaceHolder ph : parametersUsedInWhereConditions) {
			if (ph.value.equals(value))
				return true;
		}
		return false;
	}

	/**
	 * Define WHERE statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineLimitStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation, Map<JQLDynamicStatementType, String> dynamicReplace) {
		StringBuilder builder = new StringBuilder();

		int pageSize = AnnotationUtility.extractAsInt(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.PAGE_SIZE);
		if (pageSize > 0) {
			result.annotatedPageSize = true;
		}

		final One<String> pageDynamicName = new One<String>(null);
		forEachParameter(method, new OnMethodParameterListener() {

			@Override
			public void onMethodParameter(VariableElement methodParam) {
				if (methodParam.getAnnotation(BindSqlPageSize.class) != null) {
					pageDynamicName.value0 = methodParam.getSimpleName().toString();
					result.paramPageSize = pageDynamicName.value0;

					// CONSTRAINT: @BindSqlWhereArgs can be used only on
					// String[] parameter type
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeName.INT, TypeUtility.typeName(methodParam)), method.getParent().getElement(),
							method.getElement(), methodParam, BindSqlPageSize.class);
				}

			}
		});

		if (pageSize > 0 || StringUtils.hasText(pageDynamicName.value0)) {
			builder.append(" " + LIMIT_KEYWORD + " ");

			if (pageSize > 0) {
				builder.append(pageSize);
			} else {
				String temp0 = "#{" + JQLDynamicStatementType.DYNAMIC_PAGE_SIZE + "}";
				builder.append(temp0);
				dynamicReplace.put(JQLDynamicStatementType.DYNAMIC_PAGE_SIZE, temp0);
			}

			// define replacement string for PAGE_SIZE
			String temp1 = " " + OFFSET_KEYWORD + " #{" + JQLDynamicStatementType.DYNAMIC_PAGE_OFFSET + "}";

			builder.append(temp1);
			dynamicReplace.put(JQLDynamicStatementType.DYNAMIC_PAGE_OFFSET, temp1);

		}

		return builder.toString();
	}

	/**
	 * Define ORDER BY statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineOrderByStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation, Map<JQLDynamicStatementType, String> dynamicReplace) {
		StringBuilder builder = new StringBuilder();

		String orderBy = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.ORDER_BY);

		if (StringUtils.hasText(orderBy)) {
			result.annotatedOrderBy = true;
		}

		final One<String> orderDynamicName = new One<String>(null);
		forEachParameter(method, new OnMethodParameterListener() {

			@Override
			public void onMethodParameter(VariableElement methodParam) {
				if (methodParam.getAnnotation(BindSqlDynamicOrderBy.class) != null) {
					orderDynamicName.value0 = methodParam.getSimpleName().toString();

					result.paramOrderBy = orderDynamicName.value0;

					// CONSTRAINT: @BindSqlOrderBy can be used only on String
					// parameter type
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeUtility.typeName(String.class), TypeUtility.typeName(methodParam)),
							method.getParent().getElement(), method.getElement(), methodParam, BindSqlDynamicOrderBy.class);
				}

			}
		});

		if (StringUtils.hasText(orderBy) || StringUtils.hasText(orderDynamicName.value0)) {
			builder.append(" " + ORDER_BY_KEYWORD);

			if (StringUtils.hasText(orderBy)) {
				result.staticOrderBy = true;
				builder.append(StringUtils.startWithSpace(orderBy));
			}

			StringBuilder dynamicBuffer = new StringBuilder();

			if (StringUtils.hasText(orderDynamicName.value0)) {
				if (StringUtils.hasText(orderBy)) {
					dynamicBuffer.append(", ");
					builder.append(", ");
				}

				dynamicBuffer.append(" #{" + JQLDynamicStatementType.DYNAMIC_ORDER_BY + "}");
				builder.append(" #{" + JQLDynamicStatementType.DYNAMIC_ORDER_BY + "}");

				// define replacement string for WHERE
				dynamicReplace.put(JQLDynamicStatementType.DYNAMIC_ORDER_BY, dynamicBuffer.toString());
			}

		}

		return builder.toString();
	}

	/**
	 * Define WHERE statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineWhereStatement(final SQLiteModelMethod method, final JQL jql, Class<L> annotation, Map<JQLDynamicStatementType, String> dynamicReplace) {
		StringBuilder builder = new StringBuilder();

		String where = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.WHERE);
		if (StringUtils.hasText(where))
			jql.annotatedWhere = true;

		if (StringUtils.hasText(where) || method.hasDynamicWhereConditions()) {
			builder.append(" " + WHERE_KEYWORD);

			if (StringUtils.hasText(where)) {
				jql.staticWhereConditions = true;
				builder.append(StringUtils.startWithSpace(where));
			}

			StringBuilder dynamicBuffer = new StringBuilder();

			if (StringUtils.hasText(method.dynamicWhereParameterName)) {
				if (StringUtils.hasText(where)) {
					dynamicBuffer.append(" " + method.dynamicWherePrepend);
					builder.append(" " + method.dynamicWherePrepend);
				}
				dynamicBuffer.append(" #{" + JQLDynamicStatementType.DYNAMIC_WHERE + "}");
				builder.append(" #{" + JQLDynamicStatementType.DYNAMIC_WHERE + "}");

				// define replacement string for WHERE
				dynamicReplace.put(JQLDynamicStatementType.DYNAMIC_WHERE, dynamicBuffer.toString());
			}

		}

		return builder.toString();
	}

	/**
	 * @param classBuilder
	 * @param fields
	 */
	private static String forEachFields(final Set<String> fields, OnFieldListener listener) {
		StringBuilder builder = new StringBuilder();
		{
			String comma = "";
			for (String item : fields) {
				builder.append(comma + listener.onField(item));
				comma = ", ";
			}
		}

		return builder.toString();
	}

	private static void forEachFields(SQLDaoDefinition dao, OnPropertyListener listener) {
		for (SQLProperty item : dao.getEntity().getCollection()) {
			listener.onProperty(item);
		}
	}

	private static void forEachParameter(SQLiteModelMethod method, OnMethodParameterListener listener) {
		for (VariableElement p : method.getElement().getParameters()) {
			listener.onMethodParameter(p);
		}
	}

	// private static <A extends Annotation> Pair<String, String>
	// getDefinedFieldsInAnnotation(SQLiteModelMethod method, Class<A>
	// annotation) {
	// String values =
	// AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils,
	// method.getElement(), annotation, AnnotationAttributeType.FIELDS);
	// String excluedValues =
	// AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils,
	// method.getElement(), annotation,
	// AnnotationAttributeType.EXCLUDED_FIELDS);
	//
	// return new Pair<String, String>(values, excluedValues);
	// }

}

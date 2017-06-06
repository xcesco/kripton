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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
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
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
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

	public static JQL buildJQL(SQLiteModelMethod method) {
		final SQLDaoDefinition dao = method.getParent();
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
			return buildJQLSelect(method, result);
		} else if (method.hasAnnotation(BindSqlInsert.class)) {
			return buildJQLInsert(method, result);
		} else if (method.hasAnnotation(BindSqlUpdate.class)) {
			return buildJQLUpdate(method, result);
		} else if (method.hasAnnotation(BindSqlDelete.class)) {
			return buildJQLDelete(method, result);
		}

		return null;
	}

	/**
	 * 
	 * <pre>
	 * DELETE person WHERE id = ${bean.id} AND #{where}
	 * </pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLDelete(SQLiteModelMethod method, JQL result) {
		final SQLDaoDefinition dao = method.getParent();

		StringBuilder builder = new StringBuilder();
		builder.append(DELETE_KEYWORD + " " + FROM_KEYWORD);
		// entity name
		builder.append(" " + dao.getEntitySimplyClassName());

		builder.append(defineWhereStatement(method, result, BindSqlDelete.class));

		result.value = builder.toString();
		result.operationType = JQLType.DELETE;
		return result;
	}

	/**
	 * <pre> 
	 * INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})
	 * </pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLInsert(SQLiteModelMethod method, JQL result) {
		final Class<? extends Annotation> annotation = BindSqlInsert.class;
		final SQLDaoDefinition dao = method.getParent();
		final boolean includePrimaryKey = AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.INCLUDE_PRIMARY_KEY);

		// use annotation's attribute value and exclude and bean definition to
		// define field list
		final Set<String> fields = defineFields(method, annotation, includePrimaryKey);

		StringBuilder builder = new StringBuilder();
		builder.append(INSERT_KEYWORD);
		builder.append(" " + ConflictAlgorithmType
				.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE))
				.getSql());

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
		result.operationType = JQLType.INSERT;
		return result;
	}

	private static JQL buildJQLSelect(SQLiteModelMethod method, JQL result) {
		final Class<? extends Annotation> annotation = BindSqlSelect.class;
		final SQLDaoDefinition dao = method.getParent();

		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to
		// define field list
		final Set<String> fields = defineFields(method, BindSqlSelect.class, true);

		boolean distinct = AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.DISTINCT);
		String annotatedGroupBy = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.GROUP_BY);
		String annotatedHaving = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.HAVING);

		StringBuilder builder = new StringBuilder();
		builder.append(SELECT_KEYWORD + " ");

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
		builder.append(defineWhereStatement(method, result, annotation));

		// group by
		if (StringUtils.hasText(annotatedGroupBy)) {
			result.annotatedGroupBy = true;
			builder.append(" " + GROUP_BY_KEYWORD + " " + annotatedGroupBy);
		}

		// having
		if (StringUtils.hasText(annotatedHaving)) {
			result.annotatedGroupBy = true;
			builder.append(" " + HAVING_KEYWORD + " " + annotatedHaving);
		}
		
		// order by
		builder.append(defineOrderByStatement(method, result, annotation));
		
		// limit
		builder.append(defineLimitStatement(method, result, annotation));

		result.value = builder.toString();
		result.operationType = JQLType.SELECT;
		return result;
	}

	/**
	 * 
	 * <pre>
	 * UPDATE bean01 SET text=${text} WHERE id=${id}
	 * </pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLUpdate(final SQLiteModelMethod method, JQL result) {
		final Class<? extends Annotation> annotation = BindSqlUpdate.class;
		final SQLDaoDefinition dao = method.getParent();

		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to
		// define field list
		final Set<String> fields = defineFields(method, annotation, false);

		StringBuilder builder = new StringBuilder();
		builder.append(UPDATE_KEYWORD);
		// entity name
		builder.append(" " + dao.getEntitySimplyClassName());

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

		builder.append(defineWhereStatement(method, result, annotation));

		result.value = builder.toString();
		result.operationType = JQLType.UPDATE;
		return result;
	}

	/**
	 * @param <A>
	 * @param method
	 * @param dao
	 * @param includePrimaryKey
	 * @return
	 */
	private static <A extends Annotation> Set<String> defineFields(final SQLiteModelMethod method, Class<A> annotationClazz, final boolean includePrimaryKey) {
		final SQLDaoDefinition dao = method.getParent();
		final SQLEntity entity = method.getParent().getEntity();

		// extract some informaction from method and bean
		List<String> annotatedFieldValues = AnnotationUtility.extractAsStringArray(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.FIELDS);
		List<String> annotatedExcludedFieldValues = AnnotationUtility.extractAsStringArray(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.EXCLUDED_FIELDS);
		CollectionUtils.trim(annotatedFieldValues);
		CollectionUtils.trim(annotatedExcludedFieldValues);
		String annotatedWhere = AnnotationUtility.extractAsString(BaseProcessor.elementUtils, method.getElement(), annotationClazz, AnnotationAttributeType.WHERE);

		Set<String> annotatedWhereParameters = new HashSet<String>();
		if (StringUtils.hasText(annotatedWhere)) {
			Set<JQLPlaceHolder> parametersUsedInWhereConditions = JQLChecker.getInstance().extractFromWhereConditionAsSet(annotatedWhere);

			for (JQLPlaceHolder item : parametersUsedInWhereConditions) {
				annotatedWhereParameters.add(item.value);
			}
		}

		final One<Integer> count = new One<>(0);

		// for each field of managed bean
		final Set<String> annotatedFields = new LinkedHashSet<>();
		forEachFields(dao, new OnPropertyListener() {

			@Override
			public void onProperty(SQLProperty item) {
				if (!item.isPrimaryKey() || (item.isPrimaryKey() && includePrimaryKey)) {
					annotatedFields.add(item.getName());
				}

				if (TypeUtility.isEquals(item.getPropertyType().getTypeName(), typeName(entity.getElement()))) {
					count.value0++;
				}
			}
		});

		if (annotatedFieldValues.size() > 0) {
			annotatedFields.clear();
			annotatedFields.addAll(annotatedFieldValues);
		} else if (annotatedExcludedFieldValues.size() > 0) {
			annotatedFields.removeAll(annotatedExcludedFieldValues);
		}

		final Set<String> parameterFields = new LinkedHashSet<>();
		forEachParameter(method, new OnMethodParameterListener() {

			@Override
			public void onMethodParameter(VariableElement item) {
				BindSqlParam paramAlias = item.getAnnotation(BindSqlParam.class);
				BindSqlPageSize parameterPageSize = item.getAnnotation(BindSqlPageSize.class);
				
				BindSqlDynamicWhere parameterWhere = item.getAnnotation(BindSqlDynamicWhere.class);
				BindSqlDynamicWhereArgs paramegerWhereArgs = item.getAnnotation(BindSqlDynamicWhereArgs.class);
				
				BindSqlDynamicOrderBy parameterOrderBy = item.getAnnotation(BindSqlDynamicOrderBy.class);				

				int i = 0;

				if (parameterOrderBy != null) {
					i++;
				}

				if (parameterPageSize != null) {
					i++;
				}

				if (parameterWhere != null) {
					i++;
				}

				if (paramegerWhereArgs != null) {
					i++;
				}

				AssertKripton.assertTrue(i <= 1, "In method %s.%s parameter %s is annotated with incompatible annotations", dao.getName().toString(), method.getName().toString(),
						item.getSimpleName().toString());

				if (paramAlias != null) {
					i++;
				}

				AssertKripton.assertTrue(i <= 1, "In method %s.%s parameter %s is annotated with incompatible annotations", dao.getName().toString(), method.getName().toString(),
						item.getSimpleName().toString());

				// ASSERT: only @Param is good annotation for fields. With other
				// one, we have to ignore parameter.
				if (i > 0 && paramAlias == null)
					return;

				String alias = item.getSimpleName().toString();
				if (paramAlias != null && StringUtils.hasText(paramAlias.value())) {
					alias = paramAlias.value();
				}

				parameterFields.add(alias);
			}
		});

		// parameter fields have major priority respect annotated field
		// definition (they can not coexsists).
		// case: 1 parameter usefull of type bean
		if (parameterFields.size() == 1) {

		} else if (parameterFields.size() > 0) {
			// replace annotated field with parameter fields
			annotatedFields.clear();
			annotatedFields.addAll(parameterFields);
		}

		annotatedFields.removeAll(annotatedWhereParameters);

		return annotatedFields;
	}

	/**
	 * Define WHERE statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineLimitStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation) {
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
				builder.append("#{pageSize}");
			}

			builder.append(" " + OFFSET_KEYWORD + " ");
			builder.append("#{pageOffset}");
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
	private static <L extends Annotation> String defineOrderByStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation) {
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
				builder.append(StringUtils.startWithSpace(orderBy));
			}

			if (StringUtils.hasText(orderDynamicName.value0)) {
				if (StringUtils.hasText(orderBy)) {
					builder.append(", ");
				}
				builder.append(" #{" + orderDynamicName.value0 + "}");
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
	private static <L extends Annotation> String defineWhereStatement(final SQLiteModelMethod method, final JQL jql, Class<L> annotation) {
		StringBuilder builder = new StringBuilder();
		
		String where = AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.WHERE);
		if (StringUtils.hasText(where))
			jql.annotatedWhere = true;
		
		if (StringUtils.hasText(where) || method.hasDynamicWhereConditions()) {
			builder.append(" " + WHERE_KEYWORD);

			if (StringUtils.hasText(where)) {
				builder.append(StringUtils.startWithSpace(where));
			}

			if (StringUtils.hasText(method.dynamicWhereParameterName)) {
				if (StringUtils.hasText(where)) {
					builder.append(" " + method.dynamicWherePrepend);
				}
				builder.append(" #{" + method.dynamicWhereParameterName + "}");
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

	private static <A extends Annotation> Pair<String, String> getDefinedFieldsInAnnotation(SQLiteModelMethod method, Class<A> annotation) {
		String values = AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.FIELDS);
		String excluedValues = AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.EXCLUDED_FIELDS);

		return new Pair<String, String>(values, excluedValues);
	}

}

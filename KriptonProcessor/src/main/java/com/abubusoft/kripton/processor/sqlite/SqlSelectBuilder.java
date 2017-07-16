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
package com.abubusoft.kripton.processor.sqlite;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

import android.database.Cursor;
import android.net.Uri;

public abstract class SqlSelectBuilder {

	public static String getNameParameterOfType(ModelMethod method, TypeName parameter) {
		for (Pair<String, TypeName> item : method.getParameters()) {
			if (item.value1.equals(parameter)) {
				return item.value0;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 */
	public static void generateSelect(Elements elementUtils, Builder builder, SQLiteModelMethod method) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		SelectBuilderUtility.SelectType selectResultType = null;

		// if true, field must be associate to ben attributes
		TypeName returnTypeName = method.getReturnClass();

		ParameterizedTypeName readBeanListener = ParameterizedTypeName.get(ClassName.get(OnReadBeanListener.class), ClassName.get(entity.getElement()));
		ClassName readCursorListener = ClassName.get(OnReadCursorListener.class);

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);

		AssertKripton.failWithInvalidMethodSignException(pageSize < 0, method, "in @%s(pageSize) must be set with positive number", BindSqlSelect.class.getSimpleName());
		AssertKripton.failWithInvalidMethodSignException(pageSize > 0 && method.hasDynamicPageSizeConditions(), method, "can not define @%s(pageSize) and mark a method parameter with @%s ",
				BindSqlSelect.class.getSimpleName(), BindSqlPageSize.class.getSimpleName());

		if (TypeUtility.isTypeIncludedIn(returnTypeName, Void.class, Void.TYPE)) {
			// return VOID (in the parameters must be a listener)
			if (SqlBuilderHelper.hasParameterOfType(method, readCursorListener)) {
				selectResultType = SelectBuilderUtility.SelectType.LISTENER_CURSOR;
			} else if (SqlBuilderHelper.hasParameterOfType(method, readBeanListener)) {
				selectResultType = SelectBuilderUtility.SelectType.LISTENER_BEAN;
			}
		} else if (TypeUtility.isTypeIncludedIn(returnTypeName, Cursor.class)) {
			// return Cursor (no listener)
			selectResultType = SelectBuilderUtility.SelectType.CURSOR;
		} else if (returnTypeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) returnTypeName;
			ClassName returnParameterizedClassName = returnParameterizedTypeName.rawType;

			// return List (no listener)
			AssertKripton.assertTrueOrInvalidMethodSignException(returnParameterizedTypeName.typeArguments.size() == 1, method, "return type %s is not supported", returnTypeName);
			TypeName elementName = returnParameterizedTypeName.typeArguments.get(0);

			try {
				Class<?> wrapperClazz = Class.forName(returnParameterizedClassName.toString());
				if (PaginatedResult.class.isAssignableFrom(wrapperClazz)) {
					// method must have pageSize, statically or dynamically
					// defined
					AssertKripton.assertTrueOrInvalidMethodSignException(method.hasDynamicPageSizeConditions() || pageSize > 0, method,
							"use of PaginatedResult require 'pageSize' attribute or a @%s annotated parameter", returnTypeName, BindSqlPageSize.class.getSimpleName());

					// paged result
					AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isEquals(elementName, entity.getName().toString()), method, "return type %s is not supported", returnTypeName);
					selectResultType = SelectBuilderUtility.SelectType.PAGED_RESULT;
					// set typeName of paginatedResult
					method.paginatedResultName = "paginatedResult";
				} else if (Collection.class.isAssignableFrom(wrapperClazz)) {
					if (TypeUtility.isEquals(elementName, entity.getName().toString())) {
						// entity list
						selectResultType = SelectBuilderUtility.SelectType.LIST_BEAN;
					} else if (SQLTransformer.isSupportedJDKType(elementName) || TypeUtility.isByteArray(elementName)) {
						// scalar list
						selectResultType = SelectBuilderUtility.SelectType.LIST_SCALAR;
					} else {
						AssertKripton.failWithInvalidMethodSignException(true, method, "");
					}

				}
			} catch (Exception e) {
				// error
			}
		} else if (TypeUtility.isEquals(returnTypeName, entity)) {
			// return one element (no listener)
			selectResultType = SelectBuilderUtility.SelectType.BEAN;
		} else if (SQLTransformer.isSupportedJDKType(returnTypeName) || TypeUtility.isByteArray(returnTypeName)) {
			// return single value string, int, long, short, double, float,
			// String (no listener)
			selectResultType = SelectBuilderUtility.SelectType.SCALAR;
		}

		AssertKripton.assertTrueOrInvalidMethodSignException(selectResultType != null, method, "'%s' as return type is not supported", returnTypeName);

		// generate select method
		selectResultType.generate(elementUtils, builder, method, returnTypeName);

		if (method.contentProviderEntryPathEnabled) {
			// we need to generate UPDATE or DELETE for content provider to
			generateSelectForContentProvider(elementUtils, builder, method, selectResultType);
		}
	}
	
	public static class SplittedSql {
		public String sqlWhereStatement;
		public String sqlOrderByStatement;
		public String sqlOffsetStatement;
		public String sqlLimitStatement;
		public String sqlHavingStatement;
		public String sqlGroupStatement;
		public String sqlBasic;
	}

	/**
	 * <p>
	 * Generate select used in content provider class.
	 * </p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param selectResultType
	 */
	private static void generateSelectForContentProvider(Elements elementUtils, Builder builder, final SQLiteModelMethod method, SelectType selectResultType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();		
		final Set<String> columns = new LinkedHashSet<>();
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);

		// params
		methodBuilder.addParameter(ParameterSpec.builder(Uri.class, "uri").build());
		methodBuilder.addParameter(ParameterSpec.builder(ArrayTypeName.of(String.class), "projection").build());
		methodBuilder.addParameter(ParameterSpec.builder(String.class, "selection").build());
		methodBuilder.addParameter(ParameterSpec.builder(ArrayTypeName.of(String.class), "selectionArgs").build());
		methodBuilder.addParameter(ParameterSpec.builder(String.class, "sortOrder").build());

		methodBuilder.returns(Cursor.class);
		
		SqlBuilderHelper.generateLogForContentProviderBeginning(method, methodBuilder);
		
		JQLChecker jqlChecker=JQLChecker.getInstance();
		SplittedSql splittedSql=generateSQL(method, methodBuilder, true);

		List<JQLPlaceHolder> placeHolders = jqlChecker.extractFromVariableStatement(splittedSql.sqlWhereStatement);
		// remove placeholder for dynamic where, we are not interested here
		placeHolders = SqlBuilderHelper.removeDynamicPlaceHolder(placeHolders);
		AssertKripton.assertTrue(placeHolders.size() == method.contentProviderUriVariables.size(), "In '%s.%s' content provider URI path variables and variables in where conditions are different",
				daoDefinition.getName(), method.getName());

		Set<JQLProjection> projectedColumns = jqlChecker.extractProjections(method.jql);
		for (JQLProjection item : projectedColumns) {
			if (item.type == ProjectionType.COLUMN) {
				columns.add(entity.get(item.column.trim()).columnName);
			} else {
				columns.add(item.expression.trim());
			}
		}

		methodBuilder.addStatement("$T _sqlBuilder=new $T()", StringBuilder.class, StringBuilder.class);
		SqlModifyBuilder.generateInitForDynamicWhereVariables(method, methodBuilder, "selection", "selectionArgs");
		
		methodBuilder.addStatement("$T _projectionBuffer=new $T()", StringBuilder.class, StringBuilder.class);		
		if (method.jql.isOrderBy()) {
			methodBuilder.addStatement("String _sortOrder=sortOrder");
		}
		
		methodBuilder.addStatement("_sqlBuilder.append($S)", splittedSql.sqlBasic);

		SqlBuilderHelper.generateWhereCondition(methodBuilder, method, false);

		generateDynamicPartOfQuery(method, methodBuilder, splittedSql);

		// generate and check columns
		{
			methodBuilder.addCode("\n// manage projected columns\n");
			methodBuilder.addStatement("String _columnSeparator=\"\"");
			methodBuilder.beginControlFlow("if (projection!=null && projection.length>0)");
			// generate projected column check
			String columnCheckSetName = SqlBuilderHelper.generateColumnCheckSet(elementUtils, builder, method, columns);
			SqlBuilderHelper.forEachColumnInContentValue(method, methodBuilder, "projection", true, new OnColumnListener() {

				@Override
				public void onColumnCheck(MethodSpec.Builder methodBuilder, String projectedColumnVariable) {
					methodBuilder.addStatement("_projectionBuffer.append(_columnSeparator + $L)", projectedColumnVariable);
					methodBuilder.addStatement("_columnSeparator=\", \"");
				}
			});
			methodBuilder.nextControlFlow("else");
			methodBuilder.beginControlFlow("for (String column: $L)", columnCheckSetName);
			methodBuilder.addStatement("_projectionBuffer.append(_columnSeparator + column)");
			methodBuilder.addStatement("_columnSeparator=\", \"");
			methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();

			int i = 0;
			// extract pathVariables
			// every controls was done in constructor of SQLiteModelMethod
			for (ContentUriPlaceHolder variable : method.contentProviderUriVariables) {
				AssertKripton.assertTrue(SqlBuilderHelper.validate(variable.value, placeHolders, i), "In '%s.%s' content provider URI path and where conditions have to use same set of variables",
						daoDefinition.getName(), method.getName());

				SQLProperty entityProperty = entity.get(variable.value);
				TypeName methodParameterType = method.findParameterTypeByAliasOrName(variable.value);

				methodBuilder.addCode("// Add parameter $L at path segment $L\n", variable.value, variable.pathSegmentIndex);
				methodBuilder.addStatement("_sqlWhereParams.add(uri.getPathSegments().get($L))", variable.pathSegmentIndex);

				if (entityProperty != null) {

					AssertKripton.assertTrue(TypeUtility.isTypeIncludedIn(entityProperty.getPropertyType().getTypeName(), String.class, Long.class, Long.TYPE),
							"In '%s.%s' content provider URI path variables %s must be String of Long type", daoDefinition.getName(), method.getName(), entityProperty.getName());
				} else if (methodParameterType != null) {
					AssertKripton.assertTrue(TypeUtility.isTypeIncludedIn(methodParameterType, String.class, Long.class, Long.TYPE),
							"In '%s.%s' content provider URI path variables %s must be String of Long type", daoDefinition.getName(), method.getName(),
							method.findParameterNameByAlias(variable.value));
				}

				i++;
			}
		}

		SqlBuilderHelper.generateLogForSQL(method, methodBuilder);

		SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);

		methodBuilder.addCode("\n// execute query\n");
		methodBuilder.addStatement("Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]))");

		methodBuilder.addStatement("return _result");

		// we add at last javadoc, because need info is built at last.
		SqlBuilderHelper.generateJavaDocForContentProvider(method, methodBuilder);

		methodBuilder.addJavadoc("@param uri $S\n", method.contentProviderUriTemplate.replace("*", "[*]"));
		methodBuilder.addJavadoc("@param selection dynamic part of <code>where</code> statement $L\n", method.hasDynamicWhereConditions() ? "" : "<b>NOT USED</b>");
		methodBuilder.addJavadoc("@param selectionArgs arguments of dynamic part of <code>where</code> statement $L\n", method.hasDynamicWhereConditions() ? "" : "<b>NOT USED</b>");

		methodBuilder.addJavadoc("@return number of effected rows\n");

		builder.addMethod(methodBuilder.build());
	}

	/**
	 * @param method
	 * @param methodBuilder
	 * @param splittedSql
	 */
	public static void generateDynamicPartOfQuery(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder, SplittedSql splittedSql) {
		final JQL jql = method.jql;
		if (StringUtils.hasText(splittedSql.sqlGroupStatement)) {
			methodBuilder.addCode("\n// manage GROUP BY statement\n");
			methodBuilder.addStatement("String _sqlGroupByStatement=$S", splittedSql.sqlGroupStatement);
			methodBuilder.addStatement("_sqlBuilder.append($L)", "_sqlGroupByStatement");
		}

		if (StringUtils.hasText(splittedSql.sqlHavingStatement)) {
			methodBuilder.addCode("\n// manage HAVING statement\n");
			methodBuilder.addStatement("String _sqlHavingStatement=$S", splittedSql.sqlHavingStatement);
			methodBuilder.addStatement("_sqlBuilder.append($L)", "_sqlHavingStatement");
		}

		if (jql.isOrderBy()) {
			methodBuilder.addCode("\n// manage order by statement\n");
			String value = splittedSql.sqlOrderByStatement;
			String valueToReplace = jql.dynamicReplace.get(JQLDynamicStatementType.DYNAMIC_ORDER_BY);

			if (jql.isStaticOrderBy() && !jql.isDynamicOrderBy()) {
				// case static statement and NO dynamic

				methodBuilder.addStatement("String _sqlOrderByStatement=$S", value);
			} else if (jql.isStaticOrderBy() && jql.isDynamicOrderBy()) {
				methodBuilder.addStatement("String _sqlOrderByStatement=$S+$T.ifNotEmptyAppend($L, \", \")", value.replace(valueToReplace, ""), StringUtils.class, "_sortOrder");
			} else if (!jql.isStaticOrderBy() && jql.isDynamicOrderBy()) {
				methodBuilder.addStatement("String _sqlOrderByStatement=$T.ifNotEmptyAppend($L,\" $L \")", StringUtils.class, "_sortOrder", JQLKeywords.ORDER_BY_KEYWORD);
			}

			methodBuilder.addStatement("_sqlBuilder.append($L)", "_sqlOrderByStatement");

		}

		if (StringUtils.hasText(splittedSql.sqlLimitStatement)) {
			methodBuilder.addStatement("String _sqlLimitStatement=$S", splittedSql.sqlLimitStatement);
		}

		if (StringUtils.hasText(splittedSql.sqlOffsetStatement)) {
			methodBuilder.addStatement("String _sqlOffsetStatement=$S", splittedSql.sqlOffsetStatement);
		}
	}

	static SplittedSql generateSQL(SQLiteModelMethod method, MethodSpec.Builder methodBuilder, final boolean replaceProjectedColumns) {
		JQLChecker jqlChecker = JQLChecker.getInstance();
		final SQLEntity entity=method.getParent().getEntity();
		final SQLiteDatabaseSchema schema=method.getParent().getParent();

		final SplittedSql splittedSql=new SplittedSql();
		
		// convert jql to sql
		String sql = jqlChecker.replace(method.jql, new JQLReplacerListener() {

			@Override
			public String onTableName(String tableName) {
				return schema.getEntityBySimpleName(tableName).getTableName();
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				return "${" + bindParameterName + "}";
			}

			@Override
			public String onColumnName(String columnName) {
				return entity.get(columnName).columnName;
			}

			@Override
			public void onWhereStatementBegin(Where_stmtContext ctx) {
			}

			@Override
			public void onWhereStatementEnd(Where_stmtContext ctx) {
			}

			@Override
			public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
				return null;
			}

			@Override
			public void onColumnNameSetBegin(Column_name_setContext ctx) {
				
			}

			@Override
			public void onColumnNameSetEnd(Column_name_setContext ctx) {
			}

			@Override
			public void onColumnValueSetBegin(Column_value_setContext ctx) {
			}

			@Override
			public void onColumnValueSetEnd(Column_value_setContext ctx) {
			}
		
		});

		// parameters extracted from JQL converted in SQL
		splittedSql.sqlBasic = jqlChecker.replaceVariableStatements(sql, new JQLReplaceVariableStatementListenerImpl() {

			@Override
			public String onWhere(String statement) {
				splittedSql.sqlWhereStatement = statement;
				return "";
			}

			@Override
			public String onOrderBy(String statement) {
				splittedSql.sqlOrderByStatement = statement;
				return "";
			}

			@Override
			public String onOffset(String statement) {
				splittedSql.sqlOffsetStatement = statement;
				return "";
			}

			@Override
			public String onLimit(String statement) {
				splittedSql.sqlLimitStatement = statement;
				return "";
			}

			@Override
			public String onHaving(String statement) {
				splittedSql.sqlHavingStatement = statement;
				return "";
			}

			@Override
			public String onGroup(String statement) {
				splittedSql.sqlGroupStatement = statement;
				return "";
			}

			@Override
			public String onProjectedColumns(String statement) {
				if (replaceProjectedColumns) return "%s"; else return null;
			}
		});
		
		return splittedSql;
	}

}

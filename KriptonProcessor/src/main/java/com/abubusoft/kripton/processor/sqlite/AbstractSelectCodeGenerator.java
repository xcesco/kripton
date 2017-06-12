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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.Cursor;

public abstract class AbstractSelectCodeGenerator implements SelectCodeGenerator {

	public enum JavadocPartType {
		ADD_PARAMETER, RETURN
	}

	public static class JavadocPart {
		public final JavadocPartType javadocPartType;
		public final String name;
		public final String description;

		JavadocPart(JavadocPartType javadocPartType, String name, String description) {
			this.javadocPartType = javadocPartType;
			this.name = name;
			this.description = description;
		}

		public static JavadocPart build(JavadocPartType javadocPartType, String name, String description) {
			return new JavadocPart(javadocPartType, name, description);
		}

	}

	public enum GenerationType {
		ALL(true, true, true), NO_CLOSE_CURSOR(true, true, false), NO_METHOD_SIGN(false, true, true), NO_CONTENT(true, false, true);

		GenerationType(boolean generateMethodSign, boolean generateMethodContent, boolean generateCloseableCursor) {
			this.generateMethodSign = generateMethodSign;
			this.generateMethodContent = generateMethodContent;
			this.generateCloseableCursor = generateCloseableCursor;
		}

		public final boolean generateMethodSign;
		public final boolean generateMethodContent;
		public final boolean generateCloseableCursor;
	}

	SelectType selectType;

	@Override
	public void generate(Elements elementUtils, TypeSpec.Builder builder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		PropertyList fieldList = CodeBuilderUtility.generatePropertyList(elementUtils, daoDefinition, method, BindSqlSelect.class, selectType.isMapFields(), null);
		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		generateCommonPart(elementUtils, method, methodBuilder, fieldList, selectType.isMapFields());
		generateSpecializedPart(elementUtils, method, methodBuilder, fieldList, selectType.isMapFields());

		builder.addMethod(methodBuilder.build());
	}

	public void generateCommonPart(Elements elementUtils, SQLiteModelMethod method, MethodSpec.Builder methodBuilder, PropertyList fieldList, boolean mapFields) {
		generateCommonPart(elementUtils, method, methodBuilder, fieldList, mapFields, GenerationType.ALL);
	}

	public void generateCommonPart(Elements elementUtils, SQLiteModelMethod method, MethodSpec.Builder methodBuilder, PropertyList fieldList, boolean mapFields, GenerationType generationType,
			JavadocPart... javadocParts) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		// if true, field must be associate to ben attributes
		//TypeName returnType = method.getReturnClass();
		TypeName returnTypeName = method.getReturnClass();

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);

		// take field list
		String fieldStatement = fieldList.value0;
		String tableStatement = daoDefinition.getEntity().getTableName();

		boolean distinctClause = Boolean.valueOf(annotation.getAttribute(AnnotationAttributeType.DISTINCT));

		// parameters
		List<String> paramNames = new ArrayList<String>();
		List<String> paramGetters = new ArrayList<String>();
		List<TypeName> paramTypeNames = new ArrayList<TypeName>();

		// used method parameters
		Set<String> usedMethodParameters = new HashSet<String>();

		SqlAnalyzer analyzer = new SqlAnalyzer();

		String whereSQL = annotation.getAttribute(AnnotationAttributeType.WHERE);
		analyzer.execute(elementUtils, method, whereSQL);
		String whereStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String havingSQL = annotation.getAttribute(AnnotationAttributeType.HAVING);
		analyzer.execute(elementUtils, method, havingSQL);
		String havingStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String groupBySQL = annotation.getAttribute(AnnotationAttributeType.GROUP_BY);
		analyzer.execute(elementUtils, method, groupBySQL);
		String groupByStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String orderBySQL = annotation.getAttribute(AnnotationAttributeType.ORDER_BY);
		analyzer.execute(elementUtils, method, orderBySQL);
		String orderByStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// add as used parameter dynamic components too
		if (method.hasDynamicWhereConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicWhereParameterName), method,
					" parameter %s is used like SQL parameter and dynamic WHERE condition.", method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicWhereParameterName);
			
			if (method.hasDynamicWhereArgs()) {
				AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicWhereArgsParameterName), method,
						" parameter %s is used like SQL parameter and dynamic WHERE ARGS condition.", method.dynamicWhereArgsParameterName);
				usedMethodParameters.add(method.dynamicWhereArgsParameterName);
			}
		}

		if (method.hasDynamicOrderByConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicOrderByParameterName), method,
					" parameter %s is used like SQL parameter and dynamic ORDER BY condition.", method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicOrderByParameterName);
		}

		if (method.hasDynamicPageSizeConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicPageSizeName), method,
					" parameter %s is used like SQL parameter and dynamic page size of LIMIT condition.", method.dynamicPageSizeName);
			usedMethodParameters.add(method.dynamicPageSizeName);
		}

		// select statement
		String sqlWithParameters = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereSQL).having(havingSQL).groupBy(groupBySQL)
				.orderBy(orderBySQL).limit(pageSize).build(method);
		String sql = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement)
				.orderBy(orderByStatement).limit(pageSize).build(method);
		String sqlForLog = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement)
				.orderBy(orderByStatement).limit(pageSize).buildForLog(method);

		// generate method signature
		if (generationType.generateMethodSign) {
			generateMethodSignature(method, methodBuilder, returnTypeName);
		}

		// generate javadoc
		JavadocUtility.generateJavaDocForSelect(methodBuilder, sqlWithParameters, paramNames, method, annotation, fieldList, selectType, javadocParts);

		if (generationType.generateMethodContent) {
			// build where condition (common for every type of select)
			StringBuilder logArgsBuffer = new StringBuilder();
			methodBuilder.addCode("// build where condition\n");
			methodBuilder.addCode("String[] _args={");
			{
				String separator = "";

				TypeName paramName;

				boolean nullable;
				int i = 0;
				for (String item : paramGetters) {
					methodBuilder.addCode(separator);
					logArgsBuffer.append(separator + "%s");

					paramName = paramTypeNames.get(i);

					// code for query arguments
					nullable = TypeUtility.isNullable(paramName);
					if (nullable) {
						methodBuilder.addCode("($L==null?\"\":", item);
					}

					// check for string conversion
					TypeUtility.beginStringConversion(methodBuilder, paramName);

					SQLTransformer.java2ContentValues(methodBuilder, daoDefinition,paramName, item);

					// check for string conversion
					TypeUtility.endStringConversion(methodBuilder, paramName);

					if (nullable) {
						methodBuilder.addCode(")");
					}

					separator = ", ";
					i++;
				}
				methodBuilder.addCode("};\n");
			}

			methodBuilder.addCode("\n");
			// this comment is added to include in all situation
			// StringUtil.class
			methodBuilder.addCode("//$T, $T will be used in case of dynamic parts of SQL\n", StringUtils.class, SqlUtils.class);

			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addStatement("$T.info($T.formatSQL($L,(Object[])_args))", Logger.class, SqlUtils.class, formatSqlForLog(method, sqlForLog));
			}

			if (generationType.generateCloseableCursor) {
				methodBuilder.beginControlFlow("try ($T cursor = database().rawQuery($L, _args))", Cursor.class, formatSql(method, sql));
			} else {
				methodBuilder.addStatement("$T cursor = database().rawQuery($L, _args)", Cursor.class, formatSql(method, sql));
			}

			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info(\"Rows found: %s\",cursor.getCount());\n", Logger.class);
			}

			switch (selectType) {
			case LISTENER_CURSOR: {
				ClassName readCursorListenerToExclude=ClassName.get(OnReadCursorListener.class);
				//LiteralType readCursorListenerToExclude = LiteralType.of(OnReadCursorListener.class);
				checkUnusedParameters(method, usedMethodParameters, readCursorListenerToExclude);
			}
				break;
			case LISTENER_BEAN: {
				//LiteralType readBeanListenerToExclude = LiteralType.of(OnReadBeanListener.class.getCanonicalName(), entity.getName());
				ParameterizedTypeName readBeanListenerToExclude=ParameterizedTypeName.get(ClassName.get(OnReadBeanListener.class), TypeName.get(entity.getElement().asType()));
				checkUnusedParameters(method, usedMethodParameters, readBeanListenerToExclude);
			}
				break;
			default:
				checkUnusedParameters(method, usedMethodParameters, null);
				break;
			}
		}

	}

	protected void generateMethodSignature(SQLiteModelMethod method, MethodSpec.Builder methodBuilder, TypeName returnTypeName, ParameterSpec... additionalParameterSpec) {
		// add parameter for method
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}

		// add additional params
		for (ParameterSpec item : additionalParameterSpec) {
			methodBuilder.addParameter(item);
		}

		// add return type
		methodBuilder.returns(returnTypeName);
	}

	public abstract void generateSpecializedPart(Elements elementUtils, SQLiteModelMethod method, MethodSpec.Builder methodBuilder, PropertyList fieldList, boolean mapFields);

	@Override
	public void setSelectResultTye(SelectType value) {
		this.selectType = value;
	}

	public static String formatSqlForLog(SQLiteModelMethod method, String sql) {
		sql = sql.replaceAll("\\%[^s]", "\\%\\%").replaceAll("\\?", "\'%s\'");

		return formatSqlInternal(method, sql, "appendForLog");
	}

	public static String formatSql(SQLiteModelMethod method, String sql) {
		return formatSqlInternal(method, sql, "appendForSQL");
	}

	public static String formatSqlInternal(SQLiteModelMethod method, String sql, String appendMethod) {
		if (method.hasDynamicWhereConditions()) {
			sql = sql.replace("#{" + method.dynamicWhereParameterName + "}", "\"+SqlUtils." + appendMethod + "(" + method.dynamicWhereParameterName + ")+\"");
		}

		if (method.hasDynamicOrderByConditions()) {
			sql = sql.replace("#{" + method.dynamicOrderByParameterName + "}", "\"+SqlUtils." + appendMethod + "(" + method.dynamicOrderByParameterName + ")+\"");
		}

		if (method.hasDynamicPageSizeConditions()) {
			sql = sql.replace("#{" + method.dynamicPageSizeName + "}", String.format("\"+SqlUtils.printIf(%s>0, \" LIMIT \"+%s)+\"", method.dynamicPageSizeName, method.dynamicPageSizeName));
		}

		if (method.hasPaginatedResultParameter()) {
			if (method.hasDynamicPageSizeConditions()) {
				sql = sql.replace("#{" + method.paginatedResultName + "}", String.format("\"+SqlUtils.printIf(%s>0 && %s.firstRow()>0, \" OFFSET \"+%s.firstRow())+\"", method.dynamicPageSizeName,
						method.paginatedResultName, method.paginatedResultName));
			} else {
				sql = sql.replace("#{" + method.paginatedResultName + "}",
						String.format("\"+SqlUtils.printIf(%s.firstRow()>0, \" OFFSET \"+%s.firstRow())+\"", method.paginatedResultName, method.paginatedResultName));
			}
		}

		// smart optimization
		sql = "\"" + sql + "\"";
		sql = sql.replaceAll("\\+\"\"", "");

		return sql;
	}

	/**
	 * Check if there are unused method parameters. In this case an exception
	 * was throws.
	 * 
	 * @param method
	 * @param usedMethodParameters
	 */
	public static void checkUnusedParameters(SQLiteModelMethod method, Set<String> usedMethodParameters, TypeName excludedClasses) {
		int paramsCount = method.getParameters().size();
		int usedCount = usedMethodParameters.size();

		if (paramsCount > usedCount) {
			StringBuilder sb = new StringBuilder();
			String separator = "";
			for (Pair<String, TypeName> item : method.getParameters()) {
				if (excludedClasses != null && item.value1.equals(excludedClasses)) {
					usedCount++;
				} else {
					if (!usedMethodParameters.contains(item.value0)) {
						sb.append(separator + "'" + item.value0 + "'");
						separator = ", ";
					}
				}
			}

			if (paramsCount > usedCount) {
				throw (new InvalidMethodSignException(method, "unused parameter(s) " + sb.toString()));
			}
		}
	}

}

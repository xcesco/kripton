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

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.livedata.KriptonComputableLiveData;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SplittedSql;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.Cursor;

public abstract class AbstractSelectCodeGenerator implements SelectCodeGenerator {

	public static final String LIVE_DATA_PREFIX = "ForLiveData";

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
		ALL(true, true, true), NO_CLOSE_CURSOR(true, true, false), NO_METHOD_SIGN(false, true, true), NO_CONTENT(true,
				false, true);

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
	public void generate(TypeSpec.Builder classBuilder, boolean mapFields, SQLiteModelMethod method) {
		SQLDaoDefinition daoDefinition = method.getParent();
		Set<JQLProjection> fieldList = JQLChecker.getInstance().extractProjections(method, method.jql.value,
				daoDefinition.getEntity());

		// generate method code
		MethodSpec.Builder methodBuilder = generateMethodBuilder(method);

		generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields());
		generateSpecializedPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields());

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * generate live data method
	 * @param classBuilder
	 * @param mapFields
	 * @param method
	 * @param returnType
	 */
	@Override
	public void generateLiveData(TypeSpec.Builder classBuilder, SQLiteModelMethod method) {		
		SQLDaoDefinition daoDefinition = method.getParent();
		Set<JQLProjection> fieldList = JQLChecker.getInstance().extractProjections(method, method.jql.value,
				daoDefinition.getEntity());

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName().replaceAll(LIVE_DATA_PREFIX, "")).addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC);
		
		methodBuilder.addJavadoc("<h2>Live data</h2>\n");
		methodBuilder.addJavadoc("<p>This method open a connection internally.</p>\n\n");

		generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields(), GenerationType.NO_CONTENT, method.liveDataReturnClass);
		
		ClassName dataSourceClazz=BindDataSourceBuilder.generateDataSourceName(daoDefinition.getParent());
		ClassName daoFactoryClazz=TypeUtility.className(BindDaoFactoryBuilder.generateDaoFactoryName(daoDefinition.getParent()));
		ClassName batchClazz=TypeUtility.mergeTypeNameWithSuffix(dataSourceClazz, ".Batch");
		
		List<Pair<String, TypeName>> methodParameters = method.getParameters();
		StringBuilder buffer=new StringBuilder();
		String separator="";
		
		for(Pair<String, TypeName> item: methodParameters) {
			buffer.append(separator+item.value0);
			separator=", ";
		}
		
		TypeSpec batchBuilder = TypeSpec.anonymousClassBuilder("")
			    .addSuperinterface(ParameterizedTypeName.get(batchClazz, method.getReturnClass()))
			    .addMethod(MethodSpec.methodBuilder("onExecute")
			        .addAnnotation(Override.class)
			        .addModifiers(Modifier.PUBLIC)
			        .addParameter(ParameterSpec.builder(daoFactoryClazz, "daoFactory").build())
			        .returns(method.getReturnClass())
			        .addStatement("return daoFactory.get$L().$L($L)", daoDefinition.getName(), method.getName()+LIVE_DATA_PREFIX, buffer.toString())
			        .build())
			    .build();
				
		TypeSpec liveDataBuilder = TypeSpec.anonymousClassBuilder("")
			    .addSuperinterface(ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), method.getReturnClass()))
			    .addMethod(MethodSpec.methodBuilder("compute")
			        .addAnnotation(Override.class)
			        .addModifiers(Modifier.PROTECTED)			        
			        .returns(method.getReturnClass())
			        .addStatement("return $T.instance().executeBatch($L)", dataSourceClazz, batchBuilder)
			        .build())
			    .build();
				
		methodBuilder.addStatement("final $T builder=$L", ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), method.getReturnClass()),liveDataBuilder);
		methodBuilder.addStatement("registryLiveData(builder)");
		methodBuilder.addStatement("return builder.getLiveData()");

		classBuilder.addMethod(methodBuilder.build());
	}

	public void generateCommonPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		generateCommonPart(method, classBuilder, methodBuilder, fieldList, mapFields, GenerationType.ALL,null);
	}

	public void generateCommonPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields,
			GenerationType generationType, TypeName forcedReturnType,  JavadocPart... javadocParts) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		// if true, field must be associate to ben attributes
		// TypeName returnType = method.getReturnClass();
		TypeName returnTypeName=forcedReturnType;
		if (returnTypeName==null) {
			returnTypeName=method.getReturnClass();
		}

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);

		// parameters
		List<String> paramNames = new ArrayList<String>();
		List<String> paramGetters = new ArrayList<String>();
		List<TypeName> paramTypeNames = new ArrayList<TypeName>();
		List<String> usedBeanPropertyNames = new ArrayList<>();

		// used method parameters
		Set<String> usedMethodParameters = new HashSet<String>();

		final One<String> whereJQL = new One<>("");
		final One<String> havingJQL = new One<>("");
		final One<String> groupJQL = new One<>("");
		final One<String> orderJQL = new One<>("");

		// extract parts of jql statement
		JQLChecker.getInstance().replaceVariableStatements(method, method.jql.value,
				new JQLReplaceVariableStatementListenerImpl() {

					@Override
					public String onWhere(String statement) {
						whereJQL.value0 = statement;
						return null;
					}

					@Override
					public String onOrderBy(String statement) {
						orderJQL.value0 = statement;
						return null;
					}

					@Override
					public String onHaving(String statement) {
						havingJQL.value0 = statement;
						return null;
					}

					@Override
					public String onGroup(String statement) {
						groupJQL.value0 = statement;
						return null;
					}

				});

		SqlAnalyzer analyzer = new SqlAnalyzer();

		// String whereSQL =
		// annotation.getAttribute(AnnotationAttributeType.WHERE);
		analyzer.execute(BaseProcessor.elementUtils, method, whereJQL.value0);
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedBeanPropertyNames.addAll(analyzer.getUsedBeanPropertyNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// String havingSQL =
		// annotation.getAttribute(AnnotationAttributeType.HAVING);
		analyzer.execute(BaseProcessor.elementUtils, method, havingJQL.value0);
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedBeanPropertyNames.addAll(analyzer.getUsedBeanPropertyNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// String groupBySQL =
		// annotation.getAttribute(AnnotationAttributeType.GROUP_BY);
		analyzer.execute(BaseProcessor.elementUtils, method, groupJQL.value0);
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedBeanPropertyNames.addAll(analyzer.getUsedBeanPropertyNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// String orderBySQL =
		// annotation.getAttribute(AnnotationAttributeType.ORDER_BY);
		analyzer.execute(BaseProcessor.elementUtils, method, orderJQL.value0);
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedBeanPropertyNames.addAll(analyzer.getUsedBeanPropertyNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// add as used parameter dynamic components too
		if (method.hasDynamicWhereConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(
					!usedMethodParameters.contains(method.dynamicWhereParameterName), method,
					" parameter %s is used like SQL parameter and dynamic WHERE condition.",
					method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicWhereParameterName);

			if (method.hasDynamicWhereArgs()) {
				AssertKripton.assertTrueOrInvalidMethodSignException(
						!usedMethodParameters.contains(method.dynamicWhereArgsParameterName), method,
						" parameter %s is used like SQL parameter and dynamic WHERE ARGS condition.",
						method.dynamicWhereArgsParameterName);
				usedMethodParameters.add(method.dynamicWhereArgsParameterName);
			}
		}

		if (method.hasDynamicOrderByConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(
					!usedMethodParameters.contains(method.dynamicOrderByParameterName), method,
					" parameter %s is used like SQL parameter and dynamic ORDER BY condition.",
					method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicOrderByParameterName);
		}

		if (method.hasDynamicPageSizeConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(
					!usedMethodParameters.contains(method.dynamicPageSizeName), method,
					" parameter %s is used like SQL parameter and dynamic page size of LIMIT condition.",
					method.dynamicPageSizeName);
			usedMethodParameters.add(method.dynamicPageSizeName);
		}

		// generate method signature
		if (generationType.generateMethodSign) {
			generateMethodSignature(method, methodBuilder, returnTypeName);
		}

		// generate javadoc
		JavadocUtility.generateJavaDocForSelect(methodBuilder, paramNames, method, annotation, fieldList, selectType,
				javadocParts);

		if (generationType.generateMethodContent) {
			SplittedSql splittedSql = SqlSelectBuilder.generateSQL(method, methodBuilder, false);

			// retrieve content values
			methodBuilder.addStatement("$T _contentValues=contentValues()", KriptonContentValues.class);

			if (method.hasDynamicParts()) {
				generateSQLBuild(method, methodBuilder, splittedSql);
				methodBuilder.addStatement("String _sql=_sqlBuilder.toString()");
			} else {
				String sqlName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, method.buildSQLName());
				String sql = SqlSelectBuilder.convertJQL2SQL(method, true);
				classBuilder.addField(
						FieldSpec.builder(String.class, sqlName, Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
								.initializer("$S", sql).build());
				methodBuilder.addComment("query SQL is statically defined");
				methodBuilder.addStatement("String _sql=$L", sqlName);
			}

			// build where condition (common for every type of select)
			StringBuilder logArgsBuffer = new StringBuilder();
			methodBuilder.addComment("add where arguments");
			{
				String separator = "";
				TypeName paramTypeName;
				// String paramName;
				boolean nullable;
				int i = 0;
				boolean rawParameters;

				String beanName = null;

				beanName = method.findEntityProperty();

				for (String item : paramGetters) {
					rawParameters = paramNames.get(i).indexOf(".") == -1;

					methodBuilder.addCode("_contentValues.addWhereArgs(");
					logArgsBuffer.append(separator + "%s");

					paramTypeName = paramTypeNames.get(i);

					// code for query arguments
					nullable = TypeUtility.isNullable(paramTypeName);

					if (rawParameters) {
						if (nullable && !method.hasAdapterForParam(item)) {
							methodBuilder.addCode("($L==null?\"\":", item);
						}

						// check for string conversion
						TypeUtility.beginStringConversion(methodBuilder, paramTypeName);

						SQLTransformer.javaMethodParam2WhereConditions(methodBuilder, method, item, paramTypeName);

						// check for string conversion
						TypeUtility.endStringConversion(methodBuilder, paramTypeName);

						if (nullable && !method.hasAdapterForParam(item)) {
							methodBuilder.addCode(")");
						}
					} else {

						// eventually we take associated property
						SQLProperty property = usedBeanPropertyNames.get(i) == null ? null
								: entity.get(usedBeanPropertyNames.get(i));

						if (nullable && !(property != null) && !method.hasAdapterForParam(item)) {
							methodBuilder.addCode("($L==null?\"\":", item);
						}

						// check for string conversion
						TypeUtility.beginStringConversion(methodBuilder, paramTypeName);

						// if (property != null) {
						// SQLTransformer.javaProperty2WhereCondition(methodBuilder,
						// method, item, paramTypeName, property);
						// } else {
						SQLTransformer.javaProperty2WhereCondition(methodBuilder, method, beanName, paramTypeName,
								property);
						// }

						// check for string conversion
						TypeUtility.endStringConversion(methodBuilder, paramTypeName);

						if (nullable && !(property != null) && !method.hasAdapterForParam(item)) {
							methodBuilder.addCode(")");
						}
					}

					separator = ", ";
					i++;

					methodBuilder.addCode(");\n");
				}
			}

			methodBuilder.addStatement("String[] _sqlArgs=_contentValues.whereArgsAsArray()");

			if (daoDefinition.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (_context.isLogEnabled())");
				// manage log
				methodBuilder.addComment("manage log");
				methodBuilder.addStatement("$T.info(_sql)", Logger.class);

				// log for where parames
				SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}

			if (generationType.generateCloseableCursor) {
				methodBuilder.beginControlFlow("try ($T _cursor = database().rawQuery(_sql, _sqlArgs))", Cursor.class);
			} else {
				methodBuilder.addStatement("$T _cursor = database().rawQuery(_sql, _sqlArgs)", Cursor.class);
			}

			if (daoDefinition.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (_context.isLogEnabled())");

				methodBuilder.addCode("$T.info(\"Rows found: %s\",_cursor.getCount());\n", Logger.class);

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}

			switch (selectType) {
			case LISTENER_CURSOR: {
				ClassName readCursorListenerToExclude = ClassName.get(OnReadCursorListener.class);
				checkUnusedParameters(method, usedMethodParameters, readCursorListenerToExclude);
			}
				break;
			case LISTENER_BEAN: {
				ParameterizedTypeName readBeanListenerToExclude = ParameterizedTypeName
						.get(ClassName.get(OnReadBeanListener.class), TypeName.get(entity.getElement().asType()));
				checkUnusedParameters(method, usedMethodParameters, readBeanListenerToExclude);
			}
				break;
			default:
				checkUnusedParameters(method, usedMethodParameters, null);
				break;
			}
		}

	}

	protected MethodSpec.Builder generateMethodBuilder(SQLiteModelMethod method) {
		MethodSpec.Builder methodBuilder;
		if (method.hasLiveData()) {
			methodBuilder = MethodSpec.methodBuilder(method.getName() + LIVE_DATA_PREFIX)
					.addModifiers(Modifier.PROTECTED);
		} else {
			methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class)
					.addModifiers(Modifier.PUBLIC);
		}

		return methodBuilder;
	}

	protected void generateMethodSignature(SQLiteModelMethod method, MethodSpec.Builder methodBuilder,
			TypeName returnTypeName, ParameterSpec... additionalParameterSpec) {
		boolean finalParameter=false;
		if (method.hasLiveData() && returnTypeName.equals(method.liveDataReturnClass)) {
			finalParameter=true;
		}
		
		// add parameter for method						
		for (Pair<String, TypeName> item : method.getParameters()) {
			ParameterSpec.Builder builder = ParameterSpec.builder(item.value1, item.value0);
			
			if (finalParameter) {
				builder.addModifiers(Modifier.FINAL);
			}
			
			methodBuilder.addParameter(builder.build());
		}

		// add additional params
		for (ParameterSpec item : additionalParameterSpec) {
			methodBuilder.addParameter(item);
		}

		// add return type
		methodBuilder.returns(returnTypeName);
	}

	public abstract void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields);

	@Override
	public void setSelectResultTye(SelectType value) {
		this.selectType = value;
	}

	/**
	 * Check if there are unused method parameters. In this case an exception
	 * was throws.
	 * 
	 * @param method
	 * @param usedMethodParameters
	 */
	public static void checkUnusedParameters(SQLiteModelMethod method, Set<String> usedMethodParameters,
			TypeName excludedClasses) {
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

	private static void generateSQLBuild(SQLiteModelMethod method, MethodSpec.Builder methodBuilder,
			SplittedSql splittedSql) {
		methodBuilder.addStatement("$T _sqlBuilder=sqlBuilder()", StringBuilder.class);
		methodBuilder.addStatement("_sqlBuilder.append($S)", splittedSql.sqlBasic.trim());

		SqlModifyBuilder.generateInitForDynamicWhereVariables(method, methodBuilder, method.dynamicWhereParameterName,
				method.dynamicWhereArgsParameterName);

		if (method.jql.isOrderBy()) {
			methodBuilder.addStatement("String _sortOrder=$L", method.jql.paramOrderBy);
		}

		SqlBuilderHelper.generateWhereCondition(methodBuilder, method, false);
		SqlSelectBuilder.generateDynamicPartOfQuery(method, methodBuilder, splittedSql);
	}

}

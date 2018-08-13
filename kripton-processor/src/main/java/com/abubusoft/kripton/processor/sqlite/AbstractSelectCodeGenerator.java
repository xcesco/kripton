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
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.KriptonLiveDataManager;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SplittedSql;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.Cursor;

/**
 * The Class AbstractSelectCodeGenerator.
 */
public abstract class AbstractSelectCodeGenerator implements SelectCodeGenerator {

	/** The Constant LIVE_DATA_PREFIX. */
	public static final String LIVE_DATA_PREFIX = "ForLiveData";

	/**
	 * The Enum JavadocPartType.
	 */
	public enum JavadocPartType {

		/** The add parameter. */
		ADD_PARAMETER,
		/** The return. */
		RETURN
	}

	/**
	 * generate code for sub queries
	 * 
	 * @param methodBuilder
	 * @param method
	 */
	protected void generateSubQueries(Builder methodBuilder, SQLiteModelMethod method) {		
		SQLiteEntity entity = method.getEntity();
		for (Triple<String, String, SQLiteModelMethod> item : method.childrenSelects) {
			TypeName entityTypeName = TypeUtility.typeName(entity.getElement());

			String setter;
			if (entity.isImmutablePojo()) {
				String idGetter = ImmutableUtility.IMMUTABLE_PREFIX + entity.getPrimaryKey().getName();
				
				setter=ImmutableUtility.IMMUTABLE_PREFIX+entity.findRelationByParentProperty(item.value0).value0.getName()+"="+String.format("this.daoFactory.get%s().%s(%s)", item.value2.getParent().getName(),
						item.value2.getName(), idGetter);
			} else {
				String idGetter = PropertyUtility.getter("resultBean", entityTypeName, entity.getPrimaryKey());
				setter = PropertyUtility.setter(entityTypeName, "resultBean",
						entity.findRelationByParentProperty(item.value0).value0,
						String.format("this.daoFactory.get%s().%s(%s)", item.value2.getParent().getName(),
								item.value2.getName(), idGetter));
			}

			methodBuilder.addComment("sub query: $L", setter);
			methodBuilder.addStatement("$L", setter);

		}
	}

	/**
	 * The Class JavadocPart.
	 */
	public static class JavadocPart {

		/** The javadoc part type. */
		public final JavadocPartType javadocPartType;

		/** The name. */
		public final String name;

		/** The description. */
		public final String description;

		/**
		 * Instantiates a new javadoc part.
		 *
		 * @param javadocPartType
		 *            the javadoc part type
		 * @param name
		 *            the name
		 * @param description
		 *            the description
		 */
		JavadocPart(JavadocPartType javadocPartType, String name, String description) {
			this.javadocPartType = javadocPartType;
			this.name = name;
			this.description = description;
		}

		/**
		 * Builds the.
		 *
		 * @param javadocPartType
		 *            the javadoc part type
		 * @param name
		 *            the name
		 * @param description
		 *            the description
		 * @return the javadoc part
		 */
		public static JavadocPart build(JavadocPartType javadocPartType, String name, String description) {
			return new JavadocPart(javadocPartType, name, description);
		}

	}

	/**
	 * The Enum GenerationType.
	 */
	public enum GenerationType {

		/** The all. */
		ALL(true, true, true),
		/** The no close cursor. */
		NO_CLOSE_CURSOR(true, true, false),
		/** The no method sign. */
		NO_METHOD_SIGN(false, true, true),
		/** The no content. */
		NO_CONTENT(true, false, true);

		/**
		 * Instantiates a new generation type.
		 *
		 * @param generateMethodSign
		 *            the generate method sign
		 * @param generateMethodContent
		 *            the generate method content
		 * @param generateCloseableCursor
		 *            the generate closeable cursor
		 */
		GenerationType(boolean generateMethodSign, boolean generateMethodContent, boolean generateCloseableCursor) {
			this.generateMethodSign = generateMethodSign;
			this.generateMethodContent = generateMethodContent;
			this.generateCloseableCursor = generateCloseableCursor;
		}

		/** The generate method sign. */
		public final boolean generateMethodSign;

		/** The generate method content. */
		public final boolean generateMethodContent;

		/** The generate closeable cursor. */
		public final boolean generateCloseableCursor;
	}

	/** The select type. */
	SelectType selectType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility. SelectCodeGenerator#generate(com.squareup.javapoet.TypeSpec.Builder, boolean,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod)
	 */
	@Override
	public void generate(TypeSpec.Builder classBuilder, boolean mapFields, SQLiteModelMethod method) {		
		Set<JQLProjection> fieldList = JQLChecker.getInstance().extractProjections(method, method.jql.value,
				method.getEntity());

		// generate method code
		MethodSpec.Builder methodBuilder = generateMethodBuilder(method);

		generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields());
		methodBuilder.addComment("Specialized part - $L - BEGIN", this.getClass().getSimpleName());
		generateSpecializedPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields());
		methodBuilder.addComment("Specialized part - $L - END", this.getClass().getSimpleName());

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * generate live data method.
	 *
	 * @param classBuilder
	 *            the class builder
	 * @param method
	 *            the method
	 */
	@Override
	public void generateLiveData(TypeSpec.Builder classBuilder, SQLiteModelMethod method) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		Set<JQLProjection> fieldList = JQLChecker.getInstance().extractProjections(method, method.jql.value,
				method.getEntity());

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName().replaceAll(LIVE_DATA_PREFIX, ""))
				.addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("<h2>Live data</h2>\n");
		methodBuilder.addJavadoc("<p>This method open a connection internally.</p>\n\n");

		generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields(),
				GenerationType.NO_CONTENT, method.liveDataReturnClass);

		ClassName dataSourceClazz = BindDataSourceBuilder.generateDataSourceName(daoDefinition.getParent());
		ClassName daoFactoryClazz = TypeUtility
				.className(BindDaoFactoryBuilder.generateDaoFactoryName(daoDefinition.getParent()));
		ClassName batchClazz = TypeUtility.mergeTypeNameWithSuffix(dataSourceClazz, ".Batch");

		List<Pair<String, TypeName>> methodParameters = method.getParameters();
		StringBuilder buffer = new StringBuilder();
		String separator = "";

		for (Pair<String, TypeName> item : methodParameters) {
			buffer.append(separator + item.value0);
			separator = ", ";
		}

		TypeSpec batchBuilder = TypeSpec.anonymousClassBuilder("")
				.addSuperinterface(ParameterizedTypeName.get(batchClazz, method.getReturnClass()))
				.addMethod(MethodSpec.methodBuilder("onExecute").addAnnotation(Override.class)
						.addModifiers(Modifier.PUBLIC)
						.addParameter(ParameterSpec.builder(daoFactoryClazz, "daoFactory").build())
						.returns(method.getReturnClass()).addStatement("return daoFactory.get$L().$L($L)",
								daoDefinition.getName(), method.getName() + LIVE_DATA_PREFIX, buffer.toString())
						.build())
				.build();

		TypeSpec liveDataBuilder = TypeSpec.anonymousClassBuilder("")
				.addSuperinterface(ParameterizedTypeName.get(
						ClassName.get(KriptonLiveDataManager.getInstance().getComputableLiveDataClazz()),
						method.getReturnClass()))
				.addMethod(MethodSpec.methodBuilder("compute").addAnnotation(Override.class)
						.addModifiers(Modifier.PROTECTED).returns(method.getReturnClass())
						.addStatement("return $T.getInstance().executeBatch($L)", dataSourceClazz, batchBuilder)
						.build())
				.build();

		methodBuilder.addStatement("final $T builder=$L",
				ParameterizedTypeName.get(
						ClassName.get(KriptonLiveDataManager.getInstance().getComputableLiveDataClazz()),
						method.getReturnClass()),
				liveDataBuilder);
		methodBuilder.addStatement("registryLiveData(builder)");
		methodBuilder.addStatement("return builder.getLiveData()");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate common part.
	 *
	 * @param method
	 *            the method
	 * @param classBuilder
	 *            the class builder
	 * @param methodBuilder
	 *            the method builder
	 * @param fieldList
	 *            the field list
	 * @param mapFields
	 *            the map fields
	 */
	public void generateCommonPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		generateCommonPart(method, classBuilder, methodBuilder, fieldList, mapFields, GenerationType.ALL, null);
	}

	/**
	 * Generate common part.
	 *
	 * @param method
	 *            the method
	 * @param classBuilder
	 *            the class builder
	 * @param methodBuilder
	 *            the method builder
	 * @param fieldList
	 *            the field list
	 * @param mapFields
	 *            the map fields
	 * @param generationType
	 *            the generation type
	 * @param forcedReturnType
	 *            the forced return type
	 * @param javadocParts
	 *            the javadoc parts
	 */
	public void generateCommonPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields,
			GenerationType generationType, TypeName forcedReturnType, JavadocPart... javadocParts) {
		SQLiteDaoDefinition daoDefinition=method.getParent();
		SQLiteEntity entity = method.getEntity();

		// if true, field must be associate to ben attributes
		// TypeName returnType = method.getReturnClass();
		TypeName returnTypeName = forcedReturnType;
		if (returnTypeName == null) {
			returnTypeName = method.getReturnClass();
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

		methodBuilder.addComment("common part generation - BEGIN");

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

				for (String methodParam : paramGetters) {
					rawParameters = paramNames.get(i).indexOf(".") == -1;

					if (method.jql.spreadParams.contains(method.findParameterAliasByName(methodParam))) {
						// paramTypeName = paramTypeNames.get(i);
						String sizeMethod = "";
						String elementMethod = "";
						TypeName paramCollTypeName = paramTypeNames.get(i);
						if (paramCollTypeName instanceof ArrayTypeName) {
							sizeMethod = "length";
							elementMethod = "[_i]";
							paramTypeName = ((ArrayTypeName) paramCollTypeName).componentType;
						} else if (paramCollTypeName instanceof ParameterizedTypeName) {
							sizeMethod = "size()";
							elementMethod = ".get(_i)";
							paramTypeName = ((ParameterizedTypeName) paramCollTypeName).typeArguments.get(0);
						} else {
							paramTypeName = TypeName.get(String.class);
						}

						methodBuilder.beginControlFlow("if ($L!=null)", methodParam);
						methodBuilder.addComment("$L is managed as spread param", methodParam);
						methodBuilder.beginControlFlow("for (int _i=0; _i<$L.$L;_i++)", methodParam, sizeMethod);
						methodBuilder.addCode(" _contentValues.addWhereArgs(");
						generateRawWhereArg(method, methodBuilder, paramTypeName, TypeUtility.isNullable(paramTypeName),
								methodParam, methodParam + elementMethod);
						methodBuilder.addCode(");\n");
						methodBuilder.endControlFlow();
						methodBuilder.endControlFlow();
					} else {
						paramTypeName = paramTypeNames.get(i);

						methodBuilder.addCode("_contentValues.addWhereArgs(");
						logArgsBuffer.append(separator + "%s");

						// code for query arguments
						nullable = TypeUtility.isNullable(paramTypeName);

						if (rawParameters) {
							generateRawWhereArg(method, methodBuilder, paramTypeName, nullable, methodParam);
						} else {

							// eventually we take associated property
							SQLProperty property = usedBeanPropertyNames.get(i) == null ? null
									: entity.get(usedBeanPropertyNames.get(i));

							if (nullable && !(property != null) && !method.hasAdapterForParam(methodParam)) {
								methodBuilder.addCode("($L==null?\"\":", methodParam);
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

							if (nullable && !(property != null) && !method.hasAdapterForParam(methodParam)) {
								methodBuilder.addCode(")");
							}
						}
						methodBuilder.addCode(");\n");
					}

					separator = ", ";
					i++;

				}
			}

			methodBuilder.addStatement("String[] _sqlArgs=_contentValues.whereArgsAsArray()");

			if (daoDefinition.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section for select BEGIN");
				methodBuilder.beginControlFlow("if (_context.isLogEnabled())");
				// manage log
				methodBuilder.addComment("manage log");
				methodBuilder.addStatement("$T.info(_sql)", Logger.class);

				// log for where parames
				SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section for select END");
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

		methodBuilder.addComment("common part generation - END");

	}

	private void generateRawWhereArg(SQLiteModelMethod method, MethodSpec.Builder methodBuilder, TypeName paramTypeName,
			boolean nullable, String item) {
		generateRawWhereArg(method, methodBuilder, paramTypeName, nullable, item, item);
	}

	/**
	 * @param method
	 * @param methodBuilder
	 * @param paramTypeName
	 * @param nullable
	 * @param methodItem
	 *            is the name of method's parameter
	 * @param item
	 *            is the name of used parameter
	 */
	private void generateRawWhereArg(SQLiteModelMethod method, MethodSpec.Builder methodBuilder, TypeName paramTypeName,
			boolean nullable, String methodItem, String item) {
		if (nullable && !method.hasAdapterForParam(methodItem)) {
			methodBuilder.addCode("($L==null?\"\":", item);
		}

		// check for string conversion
		TypeUtility.beginStringConversion(methodBuilder, paramTypeName);

		SQLTransformer.javaMethodParam2WhereConditions(methodBuilder, method, methodItem, item, paramTypeName);

		// check for string conversion
		TypeUtility.endStringConversion(methodBuilder, paramTypeName);

		if (nullable && !method.hasAdapterForParam(methodItem)) {
			methodBuilder.addCode(")");
		}
	}

	/**
	 * Generate method builder.
	 *
	 * @param method
	 *            the method
	 * @return the method spec. builder
	 */
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

	/**
	 * Generate method signature.
	 *
	 * @param method
	 *            the method
	 * @param methodBuilder
	 *            the method builder
	 * @param returnTypeName
	 *            the return type name
	 * @param additionalParameterSpec
	 *            the additional parameter spec
	 */
	protected void generateMethodSignature(SQLiteModelMethod method, MethodSpec.Builder methodBuilder,
			TypeName returnTypeName, ParameterSpec... additionalParameterSpec) {
		boolean finalParameter = false;
		if (method.hasLiveData() && returnTypeName.equals(method.liveDataReturnClass)) {
			finalParameter = true;
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

	/**
	 * Generate specialized part.
	 *
	 * @param method
	 *            the method
	 * @param classBuilder
	 *            the class builder
	 * @param methodBuilder
	 *            the method builder
	 * @param fieldList
	 *            the field list
	 * @param mapFields
	 *            the map fields
	 */
	public abstract void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility. SelectCodeGenerator#setSelectResultTye(com.abubusoft.kripton.processor.
	 * sqlite.SelectBuilderUtility.SelectType)
	 */
	@Override
	public void setSelectResultTye(SelectType value) {
		this.selectType = value;
	}

	/**
	 * Check if there are unused method parameters. In this case an exception was throws.
	 *
	 * @param method
	 *            the method
	 * @param usedMethodParameters
	 *            the used method parameters
	 * @param excludedClasses
	 *            the excluded classes
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

	/**
	 * Generate SQL build.
	 *
	 * @param method
	 *            the method
	 * @param methodBuilder
	 *            the method builder
	 * @param splittedSql
	 *            the splitted sql
	 */
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

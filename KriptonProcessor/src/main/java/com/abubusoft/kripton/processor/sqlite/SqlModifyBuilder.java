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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLPlaceHolderReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

import android.content.ContentValues;
import android.net.Uri;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 05/mag/2016
 */
public abstract class SqlModifyBuilder {

	public enum ModifyType {
		UPDATE_BEAN(ModifyBeanHelper.class, true), UPDATE_RAW(ModifyRawHelper.class,
				true), DELETE_BEAN(ModifyBeanHelper.class, false), DELETE_RAW(ModifyRawHelper.class, false);

		private ModifyCodeGenerator codeGenerator;

		private boolean update;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isUpdate() {
			return update;
		}

		private ModifyType(Class<? extends ModifyCodeGenerator> codeGenerator, boolean updateValue) {
			try {
				this.update = updateValue;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}

		public void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity,
				MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType) {
			codeGenerator.generate(elementUtils, methodBuilder, isUpdate(), method, returnType);

		}
	}

	public interface ModifyCodeGenerator {
		void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields,
				SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param updateMode
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteModelMethod method, boolean updateMode) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		ModifyType updateResultType = null;

		// check type of arguments
		int count = 0;
		for (Pair<String, TypeName> param : method.getParameters()) {
			if (method.isThisDynamicWhereConditionsName(param.value0)) {
				// if current parameter is dynamic where, skip it
				continue;
			}

			if (TypeUtility.isEquals(param.value1, typeName(entity.getElement()))) {
				count++;
			}
		}

		if (count == 0) {
			// method to update raw data: no bean is used
			updateResultType = updateMode ? ModifyType.UPDATE_RAW : ModifyType.DELETE_RAW;

			ModelAnnotation annotation;

			if (updateMode) {
				annotation = method.getAnnotation(BindSqlUpdate.class);

				AssertKripton
						.assertTrueOrInvalidMethodSignException(
								AnnotationUtility.extractAsStringArray(elementUtils, method, annotation,
										AnnotationAttributeType.FIELDS).size() == 0,
								method, " can not use attribute %s in this kind of query definition",
								AnnotationAttributeType.FIELDS.getValue());
				AssertKripton.assertTrueOrInvalidMethodSignException(
						AnnotationUtility.extractAsStringArray(elementUtils, method, annotation,
								AnnotationAttributeType.EXCLUDED_FIELDS).size() == 0,
						method, " can not use attribute %s in this kind of query definition",
						AnnotationAttributeType.EXCLUDED_FIELDS.getValue());

			} else {
				annotation = method.getAnnotation(BindSqlDelete.class);
			}

			// check if there is only one parameter
			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() > 1
					&& TypeUtility.isEquals(method.getParameters().get(0).value1, daoDefinition.getEntityClassName()),
					method);

		} else if (count == 1) {
			updateResultType = updateMode ? ModifyType.UPDATE_BEAN : ModifyType.DELETE_BEAN;

			// with dynamic where conditions, we have to add 1 to parameter
			// check size (one parameter is a bean and one is the where
			// conditions)
			AssertKripton.assertTrueOrInvalidMethodSignException(
					method.getParameters().size() == 1 + (method.hasDynamicWhereConditions() ? 1 : 0), method,
					" expected only one parameter of %s type", daoDefinition.getEntityClassName());
		} else {
			throw (new InvalidMethodSignException(method,
					"only one parameter of type " + typeName(entity.getElement()) + " can be used"));
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();

		if (updateResultType == null) {
			throw (new InvalidMethodSignException(method));
		}

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC);
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// generate inner code
		updateResultType.generate(elementUtils, daoDefinition, entity, methodBuilder, method, returnType);

		MethodSpec methodSpec = methodBuilder.build();
		builder.addMethod(methodSpec);

		if (method.contentProviderEntryPathEnabled) {
			// we need to generate UPDATE or DELETE for content provider to
			generateModifierForContentProvider(elementUtils, builder, method, updateResultType);
		}

	}

	/**
	 * <p>
	 * Generate update and delete used in content provider class.
	 * </p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param updateResultType
	 */
	private static void generateModifierForContentProvider(Elements elementUtils, Builder builder,
			SQLiteModelMethod method, ModifyType updateResultType) {
		final SQLiteDatabaseSchema schema = method.getParent().getParent();
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();
		final Set<String> columns = new LinkedHashSet<>();
		final StringBuilder parametersBuilder = new StringBuilder();

		JQLChecker jqlChecker = JQLChecker.getInstance();

		// parameters extracted from query
		String whereStatement = jqlChecker.extractWhereStatement(method.jql.value);
		List<JQLPlaceHolder> placeHolders = jqlChecker.extractFromWhereCondition(whereStatement);
		// remove placeholder for dynamic where, we are not interested here
		placeHolders = removeDynamicPlaceHolder(placeHolders);
		AssertKripton.assertTrue(placeHolders.size() == method.contentProviderUriVariables.size(),
				"In '%s.%s' content provider URI path variables and variables in where conditions are different",
				daoDefinition.getName(), method.getName());

		final One<Boolean> useColumns = new One<Boolean>(true);
		String resultForLog = jqlChecker.replace(method.jql, new JQLReplacerListener() {

			@Override
			public String onColumnName(String columnName) {
				String convertedColumnName = entity.get(columnName).columnName;

				if (useColumns.value0) {
					columns.add(convertedColumnName);
				}
				return convertedColumnName;
			}

			@Override
			public String onBindParameter(String columnValue) {
				JQLParameterName parameterName = JQLParameterName.parse(columnValue);

				String limit = "";
				SQLProperty property = daoDefinition.getEntity().get(parameterName.getValue());
				SQLColumnType columnType = SQLTransformer.columnType(property);
				switch (columnType) {
				case BLOB:
				case TEXT:
					limit = "'";
				case INTEGER:
				case REAL:
				default:
					;
				}

				parametersBuilder.append(", StringUtils.formatParam(contentValues.get(\"" + property.columnName
						+ "\"),\"" + limit + "\")");

				return "%s";
			}

			@Override
			public String onTableName(String tableName) {
				return schema.getEntityBySimpleName(tableName).getTableName();
			}
			
			@Override
			public void onWhereStatementBegin(Where_stmtContext ctx) {
				useColumns.value0=false;					
			}

			@Override
			public void onWhereStatementEnd(Where_stmtContext ctx) {
				useColumns.value0=true;				
			}
		});

		String sql = jqlChecker.replace(method.jql, new JQLReplacerListener() {

			@Override
			public String onTableName(String tableName) {
				return schema.getEntityBySimpleName(tableName).getTableName();
			}

			@Override
			public String onBindParameter(String columnValue) {
				return "${" + columnValue + "}";
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
		});

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);

		// params
		methodBuilder.addParameter(ParameterSpec.builder(Uri.class, "uri").build());

		if (updateResultType == ModifyType.UPDATE_BEAN || updateResultType == ModifyType.UPDATE_RAW) {
			methodBuilder.addParameter(ParameterSpec.builder(ContentValues.class, "contentValues").build());
		}

		methodBuilder.addParameter(ParameterSpec.builder(String.class, "selection").build());
		methodBuilder.addParameter(ParameterSpec.builder(ArrayTypeName.of(String.class), "selectionArgs").build());
		methodBuilder.returns(Integer.TYPE);

		methodBuilder.addCode("//$T and $T will be used to format SQL\n", SqlUtils.class, StringUtils.class);

		if (method.hasDynamicWhereConditions()) {
			final One<Integer> dynamicWhereCounter = new One<Integer>(0);
			whereStatement = jqlChecker.replaceFromWhere(whereStatement, new JQLPlaceHolderReplacerListener() {

				@Override
				public String onParameter(String placeHolder) {
					return "?";
				}

				@Override
				public String onDynamicSQL(String placeHolder) {
					dynamicWhereCounter.value0++;
					return "#{" + placeHolder + "}";
				}
			});
			AssertKripton.assertTrue(dynamicWhereCounter.value0 <= 1,
					"In %s.%s, @DynamicWhere variable are used more then one time", daoDefinition.getName(),
					method.getName()); //

			if (method.hasDynamicWhereConditions()) {
				whereStatement = whereStatement
						.replace(method.dynamicWherePrepend + " #{" + method.dynamicWhereParameterName + "}", "");
				whereStatement = whereStatement.replace("#{" + method.dynamicWhereParameterName + "}", "");

			}
			methodBuilder.addStatement("String whereCondition=$S", whereStatement);

			if (method.hasDynamicWhereConditions()) {
				methodBuilder.beginControlFlow("if ($T.hasText(selection))", StringUtils.class);
				methodBuilder.addStatement("whereCondition+=\"$L \" + $L", method.dynamicWherePrepend, "selection");
				methodBuilder.endControlFlow();
			}

		} else {
			methodBuilder.addStatement("String whereCondition=$S", whereStatement);
		}

		methodBuilder.addStatement("$T<String> whereParams=new $T<>()", ArrayList.class, ArrayList.class);

		int i = 0;
		// extract pathVariables
		// every controls was done in constructor of SQLiteModelMethod
		for (ContentUriPlaceHolder variable : method.contentProviderUriVariables) {
			AssertKripton.assertTrue(validate(variable.value, placeHolders, i),
					"In '%s.%s' content provider URI path variables and variables in where conditions are different",
					daoDefinition.getName(), method.getName());

			SQLProperty entityProperty = entity.get(variable.value);

			if (entityProperty != null) {
				methodBuilder.addCode("// Add parameter $L at path segment $L\n", variable.value,
						variable.pathSegmentIndex);
				methodBuilder.addStatement("whereParams.add(uri.getPathSegments().get($L))", variable.pathSegmentIndex);
				AssertKripton.assertTrue(
						TypeUtility.isTypeIncludedIn(entityProperty.getPropertyType().getTypeName(), String.class,
								Long.class, Long.TYPE),
						"In '%s.%s' content provider URI path variables %s must be String of Long type",
						daoDefinition.getName(), method.getName(), entityProperty.getName());
			}

			i++;
		}

		if (method.hasDynamicWhereConditions()) {
			// ASSERT: only with dynamic where conditions
			methodBuilder.beginControlFlow("if ($T.hasText(selection) && selectionArgs!=null)", StringUtils.class);

			if (method.hasDynamicWhereConditions()) {
				methodBuilder.beginControlFlow("for (String arg: selectionArgs)");
				methodBuilder.addStatement("whereParams.add(arg)");
				methodBuilder.endControlFlow();
			}

			methodBuilder.endControlFlow();
		}

		switch (updateResultType) {
		case DELETE_BEAN:
		case DELETE_RAW:
			methodBuilder.addStatement(
					"int result = database().delete($S, whereCondition, whereParams.toArray(new String[whereParams.size()]))",
					daoDefinition.getEntity().getTableName());
			break;
		case UPDATE_BEAN:
		case UPDATE_RAW:
			SqlInsertBuilder.generateColumnCheckSet(elementUtils, builder, method, columns);
			SqlInsertBuilder.generateColumnCheck(method, methodBuilder, "UPDATE", "contentValues.keySet()");
			methodBuilder.addStatement(
					"int result = database().update($S, contentValues, whereCondition, whereParams.toArray(new String[whereParams.size()]))",
					daoDefinition.getEntity().getTableName());
			break;
		}

		methodBuilder.addStatement("return result");

		// we add at last javadoc, because need info is built at last.

		// javadoc
		String operation = updateResultType.toString().replaceAll("_BEAN", "").replaceAll("_RAW", "");
		methodBuilder.addJavadoc("<h1>Content provider URI ($L operation):</h1>\n", operation);
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", method.contentProviderUriTemplate.replace("*", "[*]"));

		if (method.contentProviderUriVariables.size() > 0) {
			methodBuilder.addJavadoc("<p>Path variables defined:</p>\n<ul>\n");
			for (ContentUriPlaceHolder variable : method.contentProviderUriVariables) {
				methodBuilder.addJavadoc("<li><strong>$${$L}</strong> at path segment $L</li>\n", variable.value,
						variable.pathSegmentIndex);
			}
			methodBuilder.addJavadoc("</ul>\n\n");
		}

		methodBuilder.addJavadoc("<h2>JQL $L for Content Provider</h2>\n", operation);
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", method.jql.value);
		methodBuilder.addJavadoc("<h2>SQL $L for Content Provider</h2>\n", operation);
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", sql);

		if (!method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc(
					"<p><strong>Dynamic where statement is ignored, due no param with @$L was added.</strong></p>\n\n",
					BindSqlDynamicWhere.class.getSimpleName());
		}
		
		methodBuilder.addJavadoc("<p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>\n\n");

		methodBuilder.addJavadoc("@param uri $S\n", method.contentProviderUriTemplate.replace("*", "[*]"));
		switch (updateResultType) {
		case UPDATE_BEAN:
		case UPDATE_RAW:
			methodBuilder.addJavadoc("@param contentValues content values\n");
			break;
		default:
			break;
		}
		methodBuilder.addJavadoc("@param selection dynamic part of <code>where</code> statement $L\n",
				method.hasDynamicWhereConditions() ? "" : "<b>NOT USED</b>");
		methodBuilder.addJavadoc("@param selectionArgs arguments of dynamic part of <code>where</code> statement $L\n",
				method.hasDynamicWhereConditions() ? "" : "<b>NOT USED</b>");

		methodBuilder.addJavadoc("@return number of effected rows\n");
		

		builder.addMethod(methodBuilder.build());
	}

	static List<JQLPlaceHolder> removeDynamicPlaceHolder(List<JQLPlaceHolder> placeHolders) {
		List<JQLPlaceHolder> result = new ArrayList<>();

		for (JQLPlaceHolder item : placeHolders) {
			if (item.type != JQLPlaceHolderType.DYNAMIC_SQL) {
				result.add(item);
			}
		}

		return result;
	}

	/**
	 * look for variable name in place holders defined through path of content
	 * provider.
	 * 
	 * @param value
	 * @param placeHolders
	 * @return <code>true</code> if we found it path
	 */
	static boolean validate(String value, List<JQLPlaceHolder> placeHolders, int pos) {
		return placeHolders.get(pos).value.equals(value);
	}

}

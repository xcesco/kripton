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

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isTypeIncludedIn;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class ModifyBeanHelper implements ModifyCodeGenerator {

	@Override
	public void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		String beanNameParameter = method.getParameters().get(0).value0;		
		AssertKripton.assertTrueOrInvalidMethodSignException(!method.hasAdapterForParam(beanNameParameter), method, "method's parameter '%s' can not use a type adapter", beanNameParameter);
		
		SqlAnalyzer analyzer = new SqlAnalyzer();

		String whereCondition = ModifyRawHelper.extractWhereConditions(updateMode, method);

		if (StringUtils.hasText(whereCondition)) {
			whereCondition = whereCondition.trim();
		}

		analyzer.execute(elementUtils, method, whereCondition);

		// retrieve content values
		methodBuilder.addStatement("$T _contentValues=contentValues()", KriptonContentValues.class);
		
		List<SQLProperty> listUsedProperty;
		if (updateMode) {
			listUsedProperty = CodeBuilderUtility.extractUsedProperties(methodBuilder, method, BindSqlUpdate.class);

			AssertKripton.assertTrueOrInvalidMethodSignException(listUsedProperty.size() > 0, method, "no column was selected for update");

			CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, method, BindSqlUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		} else {
			listUsedProperty = CodeBuilderUtility.extractUsedProperties(methodBuilder, method, BindSqlDelete.class);
		}
		// build javadoc
		buildJavadoc(methodBuilder, updateMode, method, beanNameParameter, whereCondition, listUsedProperty, analyzer.getUsedBeanPropertyNames());
		
		// build where condition
		generateWhereCondition(methodBuilder, method, analyzer);
		methodBuilder.addCode("\n");

		generateModifyQueryCommonPart(method, methodBuilder);

		// define return value
		buildReturnCode(methodBuilder, updateMode, method, returnType);

	}

	/**
	 * @param method
	 * @param methodBuilder
	 * @param updateMode
	 * @param daoDefinition
	 * @param analyzer
	 * @param sqlModify
	 */
	static void generateModifyQueryCommonPart(SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		boolean updateMode = (method.jql.operationType == JQLType.UPDATE);

		SQLDaoDefinition daoDefinition = method.getParent();
		String tableName = daoDefinition.getEntity().getTableName();

		// query builder
		if (method.jql.isWhereConditions()) {
			methodBuilder.addStatement("$T _sqlBuilder=getSQLStringBuilder()", StringBuilder.class);
		}

		SqlModifyBuilder.generateInitForDynamicWhereVariables(method, methodBuilder, method.dynamicWhereParameterName, method.dynamicWhereArgsParameterName);

		// generate where condition
		SqlBuilderHelper.generateWhereCondition(methodBuilder, method, true);
				
		// generate SQL
		SqlModifyBuilder.generateSQL(method, methodBuilder);
		
		// generate log
		SqlModifyBuilder.generateLogForModifiers(method, methodBuilder);

		if (method.jql.operationType == JQLType.UPDATE) {
			// generate log for content values
			SqlBuilderHelper.generateLogForContentValues(method, methodBuilder);
		}

		// log for where parames
		SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);

		if (updateMode) {
			if (method.jql.conflictAlgorithmType == ConflictAlgorithmType.NONE) {
				//methodBuilder.addStatement("int result = database().update($S, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());", tableName);				
				methodBuilder.addStatement("int result = $T.update(dataSource, _sql, _contentValues)",KriptonDatabaseWrapper.class);
			} else {
				methodBuilder.addComment("conflict algorithm $L", method.jql.conflictAlgorithmType);
				methodBuilder.addStatement("int result = $T.update(dataSource, _sql, _contentValues)",KriptonDatabaseWrapper.class);
			}
			
			if (method.getParent().getParent().generateRx) {
				methodBuilder.addStatement("subject.onNext($T.createUpdate(result))", SQLiteModification.class);
			}

		} else {
			methodBuilder.addStatement("int result = $T.delete(dataSource, _sql, _contentValues)",KriptonDatabaseWrapper.class);
			//methodBuilder.addStatement("int result = database().delete($S, _sqlWhereStatement, _contentValues.whereArgsAsArray())", tableName);
			
			if (method.getParent().getParent().generateRx) {
				methodBuilder.addStatement("subject.onNext($T.createDelete(result))", SQLiteModification.class);
			}
		}

	}

	/**
	 * @param methodBuilder
	 * @param method
	 * @param analyzer
	 */
	public void generateWhereCondition(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, SqlAnalyzer analyzer) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		String beanParamName = method.getParameters().get(0).value0;
		SQLProperty property;
		boolean nullable;
		TypeName beanClass = typeName(entity.getElement());

		//methodBuilder.addStatement("$T<String> _sqlWhereParams=getWhereParamsArray()", ArrayList.class);

		for (String item : analyzer.getUsedBeanPropertyNames()) {
			property = entity.findPropertyByName(item);
			//methodBuilder.addCode("_sqlWhereParams.add(");
			methodBuilder.addCode("_contentValues.addWhereArgs(");
			nullable = TypeUtility.isNullable(property);

			if (nullable && !(property.hasTypeAdapter())) {
				// transform null in ""
				methodBuilder.addCode("($L==null?\"\":", getter(beanParamName, beanClass, property));
			}

			// check for string conversion
			TypeUtility.beginStringConversion(methodBuilder, property);
			SQLTransformer.javaProperty2WhereCondition(methodBuilder, method, beanParamName, beanClass, property);
			// check for string conversion
			TypeUtility.endStringConversion(methodBuilder, property);

			if (nullable && !(property.hasTypeAdapter())) {
				methodBuilder.addCode(")");
			}

			methodBuilder.addCode(");\n");

		}
	}

	/**
	 * @param methodBuilder
	 * @param updateMode
	 * @param method
	 * @param returnType
	 */
	public void buildReturnCode(MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		if (returnType == TypeName.VOID) {

		} else if (isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addJavadoc("\n");
			if (updateMode)
				methodBuilder.addJavadoc("@return <code>true</code> if record is updated, <code>false</code> otherwise");
			else
				methodBuilder.addJavadoc("@return <code>true</code> if record is deleted, <code>false</code> otherwise");
			methodBuilder.addJavadoc("\n");

			methodBuilder.addCode("return result!=0;\n");
		} else if (isTypeIncludedIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			methodBuilder.addJavadoc("\n");
			if (updateMode) {
				methodBuilder.addJavadoc("@return number of updated records");
			} else {
				methodBuilder.addJavadoc("@return number of deleted records");
			}
			methodBuilder.addJavadoc("\n");

			methodBuilder.addCode("return result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}
	}

	/**
	 * @param methodBuilder
	 * @param updateMode
	 * @param method
	 * @param beanNameParameter
	 * @param whereCondition
	 * @param listUsedProperty
	 * @param attributesUsedInWhereConditions
	 */
	public String buildJavadoc(MethodSpec.Builder methodBuilder, boolean updateMode, final SQLiteModelMethod method, String beanNameParameter, String whereCondition,
			List<SQLProperty> listUsedProperty, List<String> attributesUsedInWhereConditions) {
		// SQLDaoDefinition daoDefinition = method.getParent();
		// SQLEntity entity = daoDefinition.getEntity();

		// in this case, only one parameter can exists for method
		Pair<String, TypeName> beanParameter = method.getParameters().get(0);

		String sqlResult;

		// generate javadoc
		StringBuilder buffer = new StringBuilder();
		StringBuilder bufferQuestion = new StringBuilder();

		String separator = "";
		for (SQLProperty property : listUsedProperty) {
			// this line generate ${bean.attribute}
			// buffer.append(String.format("%s%s=${%s.%s}", separator,
			// property.columnName,
			// method.findParameterAliasByName(beanNameParameter),
			// property.getName()));

			// this line genearate only ${attribute}
			buffer.append(String.format("%s%s=${%s}", separator, property.columnName, property.getName()));

			bufferQuestion.append(separator);
			bufferQuestion.append(property.columnName + "=");
			bufferQuestion.append("'\"+StringUtils.checkSize(_contentValues.get(\"" + property.columnName + "\"))+\"'");

			separator = ", ";
		}

		String sqlForJavaDoc = extractSQLForJavaDoc(method);

		sqlResult = method.jql.value;
		if (updateMode) {

			// query
			methodBuilder.addJavadoc("<h2>SQL update:</h2>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>", sqlForJavaDoc);
			methodBuilder.addJavadoc("\n\n");

			// list of updated fields
			// Set<String>
			// updateColumns=JQLChecker.getInstance().extractColumnsToUpdate(method.jql.value,
			// entity);
			methodBuilder.addJavadoc("<h2>Updated columns:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				String resolvedName = method.findParameterAliasByName(beanParameter.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt><dd>is mapped to <strong>$L</strong></dd>\n", property.columnName, "${" + resolvedName + "." + property.getName() + "}");
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		} else {
			// String where =
			// SqlUtility.replaceParametersWithQuestion(whereCondition, "%s");
			// sqlResult = String.format("DELETE %s %s ",
			// daoDefinition.getEntity().getTableName(), where);

			methodBuilder.addJavadoc("<h2>SQL delete:</h2>\n");
			methodBuilder.addJavadoc("<pre>");
			// methodBuilder.addJavadoc("DELETE $L $L",
			// daoDefinition.getEntity().getTableName(), whereCondition);
			methodBuilder.addJavadoc("$L", sqlForJavaDoc);
			methodBuilder.addJavadoc("</pre>");
			methodBuilder.addJavadoc("\n\n");
		}

		if (attributesUsedInWhereConditions.size() > 0) {
			// list of attributes used in where condition
			methodBuilder.addJavadoc("<h2>Parameters used in where conditions:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (String attribute : attributesUsedInWhereConditions) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", "${" + method.findParameterAliasByName(beanParameter.value0) + "." + method.findParameterAliasByName(attribute) + "}");
				methodBuilder.addJavadoc("<dd>is mapped to method's parameter <strong>$L.$L</strong></dd>\n", beanParameter.value0, attribute);
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// dynamic conditions
		if (method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc("<h2>Method's parameters and associated dynamic parts:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			if (method.hasDynamicWhereConditions()) {
				methodBuilder.addJavadoc("<dt>$L</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{$L}</dd>", method.dynamicWhereParameterName,
						JQLDynamicStatementType.DYNAMIC_WHERE);
			}

			methodBuilder.addJavadoc("\n</dl>");
			methodBuilder.addJavadoc("\n\n");

		}

		// method parameters
		// update bean have only one parameter: the bean to update
		for (Pair<String, TypeName> param : method.getParameters()) {
			methodBuilder.addJavadoc("@param $L", param.value0);

			if (method.isThisDynamicWhereConditionsName(param.value0)) {
				methodBuilder.addJavadoc("\n\tis used as dynamic where conditions\n");
			} else {
				methodBuilder.addJavadoc("\n\tis used as $L\n", "${" + method.findParameterAliasByName(param.value0) + "}");
			}
		}

		return sqlResult;
	}

	private String extractSQLForJavaDoc(final SQLiteModelMethod method) {
		final One<Boolean> usedInWhere = new One<>(false);
		final SQLiteDatabaseSchema schema = method.getParent().getParent();
		final SQLEntity entity = method.getParent().getEntity();
		String sqlForJavaDoc = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl() {

			@Override
			public String onColumnNameToUpdate(String columnName) {
				return entity.findPropertyByName(columnName).columnName;
			}

			@Override
			public String onColumnName(String columnName) {
				return entity.findPropertyByName(columnName).columnName;
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				if (!usedInWhere.value0) {
					if (bindParameterName.contains(".")) {
						String[] a = bindParameterName.split("\\.");

						if (a.length == 2) {
							bindParameterName = a[1];
						}
					}

					return ":" + bindParameterName;
				} else {
					return null;
				}
			}

			@Override
			public String onTableName(String tableName) {
				SQLEntity currentEntity = schema.getEntityBySimpleName(tableName);
				AssertKripton.assertTrueOrUnknownClassInJQLException(currentEntity != null, method, tableName);
				return currentEntity.getTableName();
			}

			@Override
			public void onWhereStatementBegin(Where_stmtContext ctx) {
				usedInWhere.value0 = true;
			}

			@Override
			public void onWhereStatementEnd(Where_stmtContext ctx) {
				usedInWhere.value0 = false;
			};

		});
		return sqlForJavaDoc;
	}

}

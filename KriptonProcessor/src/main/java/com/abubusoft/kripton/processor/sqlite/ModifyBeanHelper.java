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

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class ModifyBeanHelper implements ModifyCodeGenerator {
	
	public void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();

		String beanNameParameter = method.getParameters().get(0).value0;
		SqlAnalyzer analyzer = new SqlAnalyzer();

		String whereCondition = ModifyRawHelper.extractWhereConditions(updateMode, method);

		if (StringUtils.hasText(whereCondition)) {
			whereCondition = whereCondition.trim();
		}

		analyzer.execute(elementUtils, method, whereCondition);

		List<SQLProperty> listUsedProperty;
		if (updateMode) {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());

			AssertKripton.assertTrueOrInvalidMethodSignException(listUsedProperty.size() > 0, method, "no column was selected for update");

			CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		} else {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlDelete.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
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
		boolean updateMode=(method.jql.operationType==JQLType.UPDATE);
		
		SQLDaoDefinition daoDefinition=method.getParent();
		String tableName=daoDefinition.getEntity().getTableName();			
		
		// query builder
		methodBuilder.addStatement("$T _sqlBuilder=new $T()", StringBuilder.class, StringBuilder.class);
		
		SqlModifyBuilder.generateInitForDynamicWhereVariables(method, methodBuilder,method.dynamicWhereParameterName, method.dynamicWhereArgsParameterName);
		
		
		// generate where condition
		SqlBuilderHelper.generateWhereCondition(methodBuilder, method, true);

		methodBuilder.addCode("//$T and $T will be used to format SQL\n", StringUtils.class, SqlUtils.class);

		SqlModifyBuilder.generateLogForModifiers(method, methodBuilder);
		
		if (method.jql.operationType==JQLType.UPDATE) {
			// generate log for content values
			SqlBuilderHelper.generateLogForContentValues(method, methodBuilder);
		}
		
		// log for where parames
		SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);
				
		if (updateMode) {			
			methodBuilder.addCode("int result = database().update($S, contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));\n", tableName);
		} else {			
			methodBuilder.addCode("int result = database().delete($S, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));\n", tableName);
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

		methodBuilder.addStatement("$T<String> _sqlWhereParams=new $T<String>()", ArrayList.class, ArrayList.class);
		
		for (String item : analyzer.getUsedBeanPropertyNames()) {
			property = entity.findByName(item);
			methodBuilder.addCode("_sqlWhereParams.add(");
			nullable = TypeUtility.isNullable(property);

			if (nullable) {
				// transform null in ""
				methodBuilder.addCode("($L==null?\"\":", getter(beanParamName, beanClass, property));
			}

			// check for string conversion
			TypeUtility.beginStringConversion(methodBuilder, property);
			SQLTransformer.java2ContentValues(methodBuilder, beanClass, beanParamName, property);
			// check for string conversion
			TypeUtility.endStringConversion(methodBuilder, property);

			if (nullable) {
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
	public String buildJavadoc(MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, String beanNameParameter, String whereCondition, List<SQLProperty> listUsedProperty,
			List<String> attributesUsedInWhereConditions) {
		SQLDaoDefinition daoDefinition = method.getParent();
		//SQLEntity entity = daoDefinition.getEntity();

		// in this case, only one parameter can exists for method
		Pair<String, TypeName> beanParameter = method.getParameters().get(0);

		String sqlResult;

		// generate javadoc
		StringBuilder buffer = new StringBuilder();
		StringBuilder bufferQuestion = new StringBuilder();

		String separator = "";
		for (SQLProperty property : listUsedProperty) {
			buffer.append(String.format("%s%s=${%s.%s}", separator, property.columnName, method.findParameterAliasByName(beanNameParameter), property.getName()));

			bufferQuestion.append(separator);
			bufferQuestion.append(property.columnName + "=");
			bufferQuestion.append("'\"+StringUtils.checkSize(contentValues.get(\"" + property.columnName + "\"))+\"'");

			separator = ", ";
		}

		if (updateMode) {
			String where = SqlUtility.replaceParametersWithQuestion(whereCondition, "'%s'");
			sqlResult = String.format("UPDATE %s SET %s WHERE %s", daoDefinition.getEntity().getTableName(), bufferQuestion.toString(), where);			

			// query
			methodBuilder.addJavadoc("<h2>SQL update:</h2>\n");
			methodBuilder.addJavadoc("<pre>");
			methodBuilder.addJavadoc("UPDATE $L SET $L WHERE $L", daoDefinition.getEntity().getTableName(), buffer.toString(), whereCondition);
			if (method.hasDynamicWhereConditions()) {
				sqlResult += " #{" + method.dynamicWhereParameterName + "}";
				methodBuilder.addJavadoc(" #{$L}", method.dynamicWhereParameterName);
			}
			methodBuilder.addJavadoc("</pre>");
			methodBuilder.addJavadoc("\n\n");

			// list of updated fields
			methodBuilder.addJavadoc("<h2>Updated columns:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				String resolvedName = method.findParameterAliasByName(beanParameter.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt><dd>is mapped to <strong>$L</strong></dd>\n", property.columnName, "${" + resolvedName + "." + property.getName() + "}");
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		} else {
			String where = SqlUtility.replaceParametersWithQuestion(whereCondition, "%s");
			sqlResult = String.format("DELETE %s WHERE %s ", daoDefinition.getEntity().getTableName(), where);

			methodBuilder.addJavadoc("<h2>SQL delete:</h2>\n");
			methodBuilder.addJavadoc("<pre>");
			methodBuilder.addJavadoc("DELETE $L WHERE $L", daoDefinition.getEntity().getTableName(), whereCondition);
			if (method.hasDynamicWhereConditions()) {
				sqlResult += " #{" + method.dynamicWhereParameterName + "}";
				methodBuilder.addJavadoc(" #{$L}", method.dynamicWhereParameterName);
			}
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
				methodBuilder.addJavadoc("<dt>$L</dt><dd>is part of where conditions resolved at runtime. In above SQL compairs as #{$L}</dd>", method.dynamicWhereParameterName, JQLDynamicStatementType.DYNAMIC_WHERE);
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

}

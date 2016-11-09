/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class ModifyBeanHelper implements ModifyCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteUpdateBuilder.
	 * UpdateCodeGenerator#generate(javax.lang.model.util.Elements,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLEntity,
	 * com.squareup.javapoet.MethodSpec.Builder, boolean,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod,
	 * com.squareup.javapoet.TypeName)
	 */
	public void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();

		String beanNameParameter = method.getParameters().get(0).value0;
		SqlAnalyzer analyzer = new SqlAnalyzer();

		String whereCondition;

		if (updateMode) {
			whereCondition = method.getAnnotation(BindSqlUpdate.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		} else {
			whereCondition = method.getAnnotation(BindSqlDelete.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		}

		if (StringUtility.hasText(whereCondition)) {
			whereCondition = whereCondition.trim();
		}

		analyzer.execute(elementUtils, method, whereCondition);

		List<SQLProperty> listUsedProperty;
		if (updateMode) {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
			CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		} else {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlDelete.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		}
		// build javadoc
		String sqlModify = buildJavadoc(methodBuilder, updateMode, method, beanNameParameter, whereCondition, listUsedProperty, analyzer.getUsedBeanPropertyNames());

		// build where condition
		generateWhereCondition(methodBuilder, method, analyzer);

		methodBuilder.addCode("\n");

		if (updateMode) {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL(\"$L\"), (Object[])whereConditions);\n", Logger.class, StringUtil.class, sqlModify);
			}
			methodBuilder.addCode("int result = database().update($S, contentValues, $S, whereConditions);\n", daoDefinition.getEntity().getTableName(), analyzer.getSQLStatement());
		} else {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL(\"$L\"), (Object[])whereConditions);\n", Logger.class, StringUtil.class, analyzer.getSQLStatement().replaceAll("\\?", "%s"));
			}
			methodBuilder.addCode("int result = database().delete($S, $S, whereConditions);\n", daoDefinition.getEntity().getTableName(), analyzer.getSQLStatement());
		}

		// define return value
		buildReturnCode(methodBuilder, updateMode, method, returnType);

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

		methodBuilder.addCode("String[] whereConditions={");
		String separator = "";
		for (String item : analyzer.getUsedBeanPropertyNames()) {
			property = entity.findByName(item);
			methodBuilder.addCode(separator);
			nullable = TypeUtility.isNullable(property);

			if (nullable) {
				methodBuilder.addCode("($L." + getter(beanClass, property) + "==null?null:", beanParamName);
			}

			// check for string conversion
			TypeUtility.beginStringConversion(methodBuilder, property);
			Transformer.java2ContentValues(methodBuilder, beanClass, beanParamName, property);
			// check for string conversion
			TypeUtility.endStringConversion(methodBuilder, property);

			if (nullable) {
				methodBuilder.addCode(")");
			}

			separator = ", ";
		}
		methodBuilder.addCode("};");

		methodBuilder.addCode("\n");
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
		Converter<String, String> columnNameConverter = daoDefinition.getColumnNameConverter();
		
		// in this case, only one parameter can exists for method
		Pair<String, TypeMirror> beanParameter = method.getParameters().get(0);
		
		String sqlResult;

		// generate javadoc
		StringBuilder buffer = new StringBuilder();
		StringBuilder bufferQuestion = new StringBuilder();

		String separator = "";
		for (SQLProperty property : listUsedProperty) {
			buffer.append(String.format("%s%s=${%s.%s}", separator, columnNameConverter.convert(property.getName()), method.findParameterAliasByName(beanNameParameter), property.getName()));

			bufferQuestion.append(separator);
			bufferQuestion.append(columnNameConverter.convert(property.getName()) + "=");
			bufferQuestion.append("'\"+StringUtil.checkSize(contentValues.get(\"" + columnNameConverter.convert(property.getName()) + "\"))+\"'");

			separator = ", ";
		}

		if (updateMode) {
			String where = SqlUtility.replaceParametersWithQuestion(whereCondition, "%s");
			sqlResult = String.format("UPDATE %s SET %s WHERE %s", daoDefinition.getEntity().getTableName(), bufferQuestion.toString(), where);

			methodBuilder.addJavadoc("<p>SQL Update:</p>\n");
			methodBuilder.addJavadoc("<pre>UPDATE $L SET $L WHERE $L</pre>\n", daoDefinition.getEntity().getTableName(), buffer.toString(), whereCondition);
			
			// list of updated fields
			methodBuilder.addJavadoc("\n");
			methodBuilder.addJavadoc("<p><strong>Updated columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				String resolvedName = method.findParameterAliasByName(beanParameter.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", columnNameConverter.convert(property.getName()));
				methodBuilder.addJavadoc("<dd>is mapped to <strong>$L</strong></dd>\n", "${"+resolvedName+"."+property.getName()+"}");
			}
			methodBuilder.addJavadoc("</dl>\n");			
			
		} else {
			String where = SqlUtility.replaceParametersWithQuestion(whereCondition, "%s");
			sqlResult = String.format("DELETE %s WHERE %s", daoDefinition.getEntity().getTableName(), bufferQuestion.toString(), where);

			methodBuilder.addJavadoc("<p>SQL delete:</p>\n");
			methodBuilder.addJavadoc("<pre>DELETE $L WHERE $L</pre>\n", daoDefinition.getEntity().getTableName(), whereCondition);
		}
		

		if (attributesUsedInWhereConditions.size() > 0) {
			// list of attributes used in where condition
			methodBuilder.addJavadoc("\n");
			methodBuilder.addJavadoc("<p><strong>Parameters used in where conditions:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (String attribute : attributesUsedInWhereConditions) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", "${"+method.findParameterAliasByName(beanParameter.value0)+"."+method.findParameterAliasByName(attribute)+"}");
				methodBuilder.addJavadoc("<dd>is mapped to method's parameter <strong>$L.$L</strong></dd>\n", beanParameter.value0, attribute);
			}
			methodBuilder.addJavadoc("</dl>\n");
			methodBuilder.addJavadoc("\n");
		}

		// update bean have only one parameter: the bean to update
		for (Pair<String, TypeMirror> param : method.getParameters()) {
			methodBuilder.addJavadoc("@param $L", param.value0);
			methodBuilder.addJavadoc("\n\tis used as $L\n", "${" + method.findParameterAliasByName(param.value0) + "}");
		}

		return sqlResult;
	}

}

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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isNullable;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import android.content.ContentValues;

public class ModifyRawHelper implements ModifyCodeGenerator {

	public void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		// separate params used for update bean and params used in
		// whereCondition
		// analyze whereCondition
		String whereCondition = null;

		if (updateMode) {
			whereCondition = method.getAnnotation(BindSqlUpdate.class).getAttribute(AnnotationAttributeType.WHERE);
		} else {
			whereCondition = method.getAnnotation(BindSqlDelete.class).getAttribute(AnnotationAttributeType.WHERE);
		}

		Pair<String, List<Pair<String, TypeMirror>>> where = SqlUtility.extractParametersFromString(whereCondition, method, daoDefinition.getColumnNameConverter(), entity);

		// defines which parameter is used like update field and which is used
		// in where condition.
		List<Pair<String, TypeMirror>> methodParams = method.getParameters();
		List<Pair<String, TypeMirror>> updateableParams = new ArrayList<Pair<String, TypeMirror>>();
		List<Pair<String, TypeMirror>> whereParams = new ArrayList<Pair<String, TypeMirror>>();

		String name;

		for (Pair<String, TypeMirror> param : methodParams) {
			name = method.findParameterAliasByName(param.value0);

			if (method.isThisDynamicWhereConditionsName(name)) {
				// skip for dynamic where
				continue;
			}

			if (where.value1.contains(new Pair<>(name, param.value1))) {
				whereParams.add(param);
			} else {
				updateableParams.add(param);
			}

		}

		if (updateMode) {
			AssertKripton.assertTrueOrInvalidMethodSignException(updateableParams.size() > 0, method, "no column was selected for update");

			// clear contentValues
			methodBuilder.addCode("$T contentValues=contentValues();\n", ContentValues.class);
			methodBuilder.addCode("contentValues.clear();\n");

			for (Pair<String, TypeMirror> item : updateableParams) {
				String resolvedParamName = method.findParameterAliasByName(item.value0);
				SQLProperty property = entity.get(resolvedParamName);
				if (property == null)
					throw (new PropertyNotFoundException(method, resolvedParamName));

				// check same type
				TypeUtility.checkTypeCompatibility(method, item, property);

				if (TypeUtility.isNullable(method, item, property)) {
					methodBuilder.beginControlFlow("if ($L!=null)", item.value0);
				}

				// here it needed raw parameter name
				methodBuilder.addCode("contentValues.put($S, ", daoDefinition.getColumnNameConverter().convert(property.getName()));

				SQLTransformer.java2ContentValues(methodBuilder, daoDefinition, TypeUtility.typeName(property.getElement()), item.value0);

				methodBuilder.addCode(");\n");

				if (TypeUtility.isNullable(method, item, property)) {
					methodBuilder.nextControlFlow("else");
					methodBuilder.addCode("contentValues.putNull($S);\n", daoDefinition.getColumnNameConverter().convert(property.getName()));
					methodBuilder.endControlFlow();
				}
			}

			methodBuilder.addCode("\n");
		} else {
			if (updateableParams.size() > 0) {
				String separator = "";
				StringBuilder buffer = new StringBuilder();
				for (Pair<String, TypeMirror> item : updateableParams) {
					String resolvedParamName = method.findParameterAliasByName(item.value0);
					buffer.append(separator + resolvedParamName);
					separator = ", ";
				}
				// in DELETE can not be updated fields
				if (updateableParams.size() > 1) {
					throw (new InvalidMethodSignException(method, " parameters " + buffer.toString() + " are not used in where conditions"));
				} else {
					throw (new InvalidMethodSignException(method, " parameter " + buffer.toString() + " is not used in where conditions"));
				}
			}
		}

		// build where condition
		generateWhereCondition(methodBuilder, method, where);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("\n");

		// generate javadoc
		String sqlModify = generateJavaDoc(daoDefinition, method, methodBuilder, updateMode, whereCondition, where, methodParams, updateableParams);

		methodBuilder.addCode("//$T and $T will be used to format SQL\n", StringUtils.class, SqlUtils.class);

		if (updateMode) {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL($L, (Object[])whereConditionsArray));\n", Logger.class, SqlUtils.class, AbstractSelectCodeGenerator.formatSqlForLog(method, sqlModify));
			}
			methodBuilder.addCode("int result = database().update($S, contentValues, \"$L$L\", whereConditionsArray);\n", daoDefinition.getEntity().getTableName(), where.value0, appendSQL(method));
		} else {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL($L, (Object[])whereConditionsArray));\n", Logger.class, SqlUtils.class, AbstractSelectCodeGenerator.formatSqlForLog(method, sqlModify));
			}
			methodBuilder.addCode("int result = database().delete($S, \"$L$L\", whereConditionsArray);\n", daoDefinition.getEntity().getTableName(),
					where.value0, appendSQL(method));
		}

		// define return value
		if (returnType == TypeName.VOID) {

		} else {
			methodBuilder.addJavadoc("\n");
			if (isIn(returnType, Boolean.TYPE, Boolean.class)) {
				if (updateMode) {
					methodBuilder.addJavadoc("@return <code>true</code> if record is updated, <code>false</code> otherwise");
				} else {
					methodBuilder.addJavadoc("@return <code>true</code> if record is deleted, <code>false</code> otherwise");
				}
				methodBuilder.addCode("return result!=0;\n");
			} else if (isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
				if (updateMode) {
					methodBuilder.addJavadoc("@return number of updated records");
				} else {
					methodBuilder.addJavadoc("@return number of deleted records");
				}

				methodBuilder.addCode("return result;\n");
			} else {
				// more than one listener found
				throw (new InvalidMethodSignException(method, "invalid return type"));
			}
			methodBuilder.addJavadoc("\n");
		}
	}

	private static String appendSQL(SQLiteModelMethod method) {
		String result = "";
		if (method.hasDynamicWhereConditions()) {
			// add a space
			result = " \"+SqlUtils.appendForSQL(" + method.dynamicWhereParameterName + ")+\"";
		}

		return result;
	}

	/**
	 * @param daoDefinition
	 * @param method
	 * @param methodBuilder
	 * @param updateMode
	 * @param whereCondition
	 * @param where
	 * @param methodParams
	 * @param updateableParams
	 * 
	 * @return sql generated
	 */
	public String generateJavaDoc(SQLDaoDefinition daoDefinition, SQLiteModelMethod method, MethodSpec.Builder methodBuilder, boolean updateMode, String whereCondition,
			Pair<String, List<Pair<String, TypeMirror>>> where, List<Pair<String, TypeMirror>> methodParams, List<Pair<String, TypeMirror>> updateableParams) {
		String sqlResult;
		StringBuilder buffer = new StringBuilder();
		StringBuilder bufferQuestion = new StringBuilder();
		Converter<String, String> nc = daoDefinition.getColumnNameConverter();

		String separator = "";
		for (Pair<String, TypeMirror> param : updateableParams) {
			String resolvedName = method.findParameterAliasByName(param.value0);
			buffer.append(separator + resolvedName + "=${" + resolvedName + "}");
			bufferQuestion.append(separator + resolvedName + "='\"+StringUtils.checkSize(contentValues.get(\"" + daoDefinition.getColumnNameConverter().convert(resolvedName) + "\"))+\"'");

			separator = ", ";
		}

		// used for logging
		String whereForLogging = SqlUtility.replaceParametersWithQuestion(whereCondition, "%s");

		if (updateMode) {
			// generate sql query
			sqlResult = String.format("UPDATE %s SET %s WHERE %s", daoDefinition.getEntity().getTableName(), bufferQuestion.toString(), whereForLogging);

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
			methodBuilder.addJavadoc("<h2>Updated columns:</strong></h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeMirror> property : updateableParams) {
				String resolvedName = method.findParameterAliasByName(property.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", nc.convert(resolvedName));
				methodBuilder.addJavadoc("<dd>is binded to query's parameter <strong>$L</strong> and method's parameter <strong>$L</strong></dd>\n", "${" + resolvedName + "}", property.value0);
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");

		} else {
			// generate sql query
			sqlResult = String.format("DELETE %s WHERE %s", daoDefinition.getEntity().getTableName(), whereForLogging);

			methodBuilder.addJavadoc("<h2>SQL delete:</h2>\n");
			methodBuilder.addJavadoc("<pre>");
			methodBuilder.addJavadoc("DELETE $L WHERE $L</pre>", daoDefinition.getEntity().getTableName(), whereCondition);
			if (method.hasDynamicWhereConditions()) {
				sqlResult += " #{" + method.dynamicWhereParameterName + "}";
				methodBuilder.addJavadoc(" #{$L}", method.dynamicWhereParameterName);
			}
			methodBuilder.addJavadoc("</pre>");
			methodBuilder.addJavadoc("\n\n");
		}

		// list of where parameter
		methodBuilder.addJavadoc("<h2>Where parameters:</h2>\n");
		methodBuilder.addJavadoc("<dl>\n");
		for (Pair<String, TypeMirror> property : where.value1) {
			String rawName = method.findParameterNameByAlias(property.value0);
			methodBuilder.addJavadoc("\t<dt>$L</dt>", "${" + property.value0 + "}");
			methodBuilder.addJavadoc("<dd>is mapped to method's parameter <strong>$L</strong></dd>\n", rawName);
		}
		methodBuilder.addJavadoc("</dl>");
		methodBuilder.addJavadoc("\n\n");

		// dynamic conditions
		if (method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc("<h2>Dynamic parts:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			if (method.hasDynamicWhereConditions()) {
				methodBuilder.addJavadoc("\t<dt>#{$L}</dt><dd>is part of where conditions resolved at runtime.</dd>\n", method.dynamicWhereParameterName);
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// method parameters
		if (methodParams.size() > 0) {
			for (Pair<String, TypeMirror> param : methodParams) {
				String resolvedName = method.findParameterAliasByName(param.value0);
				methodBuilder.addJavadoc("@param $L", param.value0);
				if (method.isThisDynamicWhereConditionsName(param.value0)) {
					methodBuilder.addJavadoc("\n\tis used as dynamic where conditions\n");
				} else if (where.value1.contains(new Pair<>(resolvedName, param.value1))) {
					methodBuilder.addJavadoc("\n\tis used as where parameter <strong>$L</strong>\n", "${" + resolvedName + "}");
				} else {
					methodBuilder.addJavadoc("\n\tis used as updated field <strong>$L</strong>\n", resolvedName);
				}
			}
		}

		return sqlResult;
	}

	/**
	 * @param methodBuilder
	 * @param method
	 * @param where
	 */
	public void generateWhereCondition(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, Pair<String, List<Pair<String, TypeMirror>>> where) {
		boolean nullable;

		methodBuilder.addCode("String[] whereConditionsArray={");
		String separator = "";
		for (Pair<String, TypeMirror> item : where.value1) {
			String resolvedParamName = method.findParameterNameByAlias(item.value0);
			methodBuilder.addCode(separator);

			nullable = isNullable(typeName(item.value1));

			if (nullable) {
				// transform null in ""
				methodBuilder.addCode("($L==null?\"\":", resolvedParamName);
			}

			// check for string conversion
			TypeUtility.beginStringConversion(methodBuilder, item.value1);

			SQLTransformer.java2ContentValues(methodBuilder, method.getParent(), TypeUtility.typeName(item.value1), resolvedParamName);
			// check for string conversion
			TypeUtility.endStringConversion(methodBuilder, item.value1);

			if (nullable) {
				methodBuilder.addCode(")");
			}

			separator = ", ";
		}
		methodBuilder.addCode("};");
	}

	static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}

}

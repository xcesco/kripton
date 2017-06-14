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

import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import android.database.sqlite.SQLiteDatabase;

public class InsertBeanHelper implements InsertCodeGenerator {

	@Override
	public String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();
		String sqlInsert;

		List<SQLProperty> listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlInsert.class, methodBuilder, null);
		CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, daoDefinition, method, BindSqlInsert.class, methodBuilder, null);

		ModelProperty primaryKey = entity.getPrimaryKey();

		// generate javadoc and query
		sqlInsert = generateJavaDoc(methodBuilder, method, returnType, listUsedProperty, primaryKey);
		methodBuilder.addCode("//$T and $T will be used to format SQL\n", StringUtils.class, SqlUtils.class);

		SqlBuilderHelper.generateLogForInsert(method, methodBuilder);

		ConflictAlgorithmType conflictAlgorithmType = InsertBeanHelper.getConflictAlgorithmType(method);
		String conflictString1 = "";
		String conflictString2 = "";

		if (conflictAlgorithmType != ConflictAlgorithmType.NONE) {
			conflictString1 = "WithOnConflict";
			conflictString2 = ", SQLiteDatabase." + conflictAlgorithmType;
			methodBuilder.addCode("// use $T conflicts algorithm\n", SQLiteDatabase.class);
		}
		methodBuilder.addStatement("long result = database().insert$L($S, null, contentValues$L)", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);

		if (primaryKey != null) {
			if (primaryKey.isPublicField()) {
				methodBuilder.addCode("$L.$L=result;\n", method.getParameters().get(0).value0, primaryKey.getName());
			} else {
				methodBuilder.addCode("$L.$L(result);\n", method.getParameters().get(0).value0, PropertyUtility.setter(typeName(entity.getElement()), primaryKey));
			}
		}

		// define return value
		if (returnType == TypeName.VOID) {

		} else if (TypeUtility.isTypeIncludedIn(returnType, String.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return result!=-1;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return (int)result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}

		return sqlInsert;
	}



	/**
	 * @param methodBuilder
	 * @param method
	 * @param returnType
	 * @param listUsedProperty
	 * @param primaryKey
	 * @return string to use in log
	 */
	public String generateJavaDoc(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType, List<SQLProperty> listUsedProperty, ModelProperty primaryKey) {
		SQLDaoDefinition daoDefinition = method.getParent();
		Converter<String, String> nc = daoDefinition.getColumnNameConverter();

		String sqlInsert;
		// generate javadoc and result
		{
			String beanNameParameter = method.findParameterAliasByName(method.getParameters().get(0).value0);
			StringBuilder bufferName = new StringBuilder();
			StringBuilder bufferValue = new StringBuilder();
			StringBuilder bufferQuestion = new StringBuilder();
			String separator = "";
			for (SQLProperty property : listUsedProperty) {
				bufferName.append(separator + nc.convert(property.getName()));
				bufferValue.append(separator + "${" + beanNameParameter + "." + property.getName() + "}");

				bufferQuestion.append(separator + "'\"+StringUtils.checkSize(contentValues.get(\"" + nc.convert(property.getName()) + "\"))+\"'");

				separator = ", ";
			}

			ConflictAlgorithmType conflictAlgorithmType = getConflictAlgorithmType(method);

			methodBuilder.addJavadoc("<p>SQL insert:</p>\n");
			methodBuilder.addJavadoc("<pre>INSERT $LINTO $L ($L) VALUES ($L)</pre>\n\n", conflictAlgorithmType.getSql(), daoDefinition.getEntity().getTableName(), bufferName.toString(),
					bufferValue.toString());
			methodBuilder.addJavadoc("<p><code>$L.$L</code> is automatically updated because it is the primary key</p>\n", beanNameParameter, primaryKey.getName());
			methodBuilder.addJavadoc("\n");

			// generate sql query
			sqlInsert = String.format("INSERT %sINTO %s (%s) VALUES (%s)", conflictAlgorithmType.getSql(), daoDefinition.getEntity().getTableName(), bufferName.toString(), bufferQuestion.toString());

			// list of inserted fields
			methodBuilder.addJavadoc("<p><strong>Inserted columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", daoDefinition.getColumnNameConverter().convert(property.getName()));
				methodBuilder.addJavadoc("<dd>is mapped to <strong>$L</strong></dd>\n",
						"${" + method.findParameterAliasByName(method.getParameters().get(0).value0) + "." + method.findParameterNameByAlias(property.getName()) + "}");
			}
			methodBuilder.addJavadoc("</dl>\n\n");

			// update bean have only one parameter: the bean to update
			for (Pair<String, TypeName> param : method.getParameters()) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				methodBuilder.addJavadoc("\n\tis mapped to parameter <strong>$L</strong>\n", method.findParameterAliasByName(param.value0));
			}

			InsertRawHelper.generateJavaDocReturnType(methodBuilder, returnType);
		}
		return sqlInsert;
	}

	public static ConflictAlgorithmType getConflictAlgorithmType(SQLiteModelMethod method) {
		ModelAnnotation annotation = method.getAnnotation(BindSqlInsert.class);

		String value = annotation.getAttribute(AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE);

		if (value != null && value.indexOf(".") > -1) {
			value = value.substring(value.lastIndexOf(".") + 1);
		}
		ConflictAlgorithmType conflictAlgorithmType = ConflictAlgorithmType.valueOf(value);

		return conflictAlgorithmType;
	}

}

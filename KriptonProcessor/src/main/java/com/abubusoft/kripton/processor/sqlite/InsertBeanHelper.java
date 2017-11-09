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
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.sqlite.SQLiteStatement;

public class InsertBeanHelper implements InsertCodeGenerator {

	@Override
	public void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();
		// String sqlInsert;
		
		// retrieve content values
		methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate()", KriptonContentValues.class);

		List<SQLProperty> listUsedProperty = CodeBuilderUtility.extractUsedProperties(methodBuilder, method, BindSqlInsert.class);
		CodeBuilderUtility.generateContentValuesFromEntity(BaseProcessor.elementUtils, method, BindSqlInsert.class, methodBuilder, null);

		ModelProperty primaryKey = entity.getPrimaryKey();

		// generate javadoc and query
		generateJavaDoc(methodBuilder, method, returnType, listUsedProperty, primaryKey);		

		SqlBuilderHelper.generateLogForInsert(method, methodBuilder);

		methodBuilder.addComment("insert operation");
		if (method.jql.hasDynamicParts()) {
			// does not memorize compiled statement, it can vary every time
			// generate SQL for insert
			SqlBuilderHelper.generateSQLForInsert(method, methodBuilder);	
			
			methodBuilder.addStatement("long result = $T.insert(dataSource, _sql, _contentValues)", KriptonDatabaseWrapper.class);
		} else {			
			String psName=method.buildPreparedStatementName();
			// generate SQL for insert
			classBuilder.addField(FieldSpec.builder(TypeName.get(SQLiteStatement.class),  psName, Modifier.PRIVATE).build());
			
			methodBuilder.beginControlFlow("if ($L==null)", psName);
			SqlBuilderHelper.generateSQLForInsert(method, methodBuilder);
			methodBuilder.addStatement("$L = $T.compile(dataSource, _sql)", psName, KriptonDatabaseWrapper.class);
			methodBuilder.endControlFlow();
			
			methodBuilder.addStatement("long result = $T.insert(dataSource, $L, _contentValues)", KriptonDatabaseWrapper.class, psName);
		}
		
		if (method.getParent().getParent().generateRx) {
			methodBuilder.addStatement("subject.onNext($T.createInsert(result))", SQLiteModification.class);
		}

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
		} else if (TypeUtility.isEquals(returnType, entity)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return $L;\n", method.getParameters().get(0).value0);
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}

	}

	public void generateJavaDoc(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method, TypeName returnType, List<SQLProperty> listUsedProperty, ModelProperty primaryKey) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLiteDatabaseSchema schema = daoDefinition.getParent();

		// transform JQL to SQL
		String sqlInsert = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl() {

			@Override
			public String onColumnName(String columnName) {
				Set<SQLProperty> property = schema.getPropertyBySimpleName(columnName);

				SQLProperty tempProperty = property.iterator().next();
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

			@Override
			public String onTableName(String tableName) {
				SQLEntity entity=schema.getEntityBySimpleName(tableName);
				
				AssertKripton.assertTrueOrUnknownClassInJQLException(entity != null, method, tableName);
				
				return entity.getTableName();
				
				
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				String resolvedParamName = method.findParameterNameByAlias(bindParameterName);
				
				return "${" + resolvedParamName + "}";
			}

		});

		// generate javadoc and result
		{
			String beanNameParameter = method.findParameterAliasByName(method.getParameters().get(0).value0);
			//StringBuilder bufferName = new StringBuilder();
			//StringBuilder bufferValue = new StringBuilder();
			//StringBuilder bufferQuestion = new StringBuilder();
			//String separator = "";
//			for (SQLProperty property : listUsedProperty) {
//				bufferName.append(separator + property.columnName);
//				bufferValue.append(separator + "${" + beanNameParameter + "." + property.getName() + "}");
//
//				bufferQuestion.append(separator + "'\"+StringUtils.checkSize(contentValues.get(\"" + property.columnName + "\"))+\"'");
//
//				separator = ", ";
//			}

			// ConflictAlgorithmType conflictAlgorithmType =
			// getConflictAlgorithmType(method);

			methodBuilder.addJavadoc("<p>SQL insert:</p>\n");
			// methodBuilder.addJavadoc("<pre>INSERT $LINTO $L ($L) VALUES
			// ($L)</pre>\n\n", conflictAlgorithmType.getSqlForInsert(),
			// daoDefinition.getEntity().getTableName(), bufferName.toString(),
			// bufferValue.toString());
			methodBuilder.addJavadoc("<pre>$L</pre>\n\n", sqlInsert);
			methodBuilder.addJavadoc("<p><code>$L.$L</code> is automatically updated because it is the primary key</p>\n", beanNameParameter, primaryKey.getName());
			methodBuilder.addJavadoc("\n");

			// generate sql query
			// sqlInsert = String.format("INSERT %sINTO %s (%s) VALUES (%s)",
			// conflictAlgorithmType.getSqlForInsert(),
			// daoDefinition.getEntity().getTableName(), bufferName.toString(),
			// bufferQuestion.toString());

			// list of inserted fields
			methodBuilder.addJavadoc("<p><strong>Inserted columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", property.columnName);
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

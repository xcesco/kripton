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

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.GenericSQLHelper.SubjectType;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.sqlite.SQLiteStatement;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertBeanHelper.
 */
public class InsertBeanHelper implements InsertCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder. InsertCodeGenerator#generate(com.squareup.javapoet.TypeSpec.Builder, com.squareup.javapoet.MethodSpec.Builder,
	 * boolean, com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod, com.squareup.javapoet.TypeName)
	 */
	@Override
	public void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, boolean mapFields,
			SQLiteModelMethod method, TypeName returnType) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		SQLiteEntity entity = daoDefinition.getEntity();

		// name of the entity param
		String entityParamName = method.getParameters().get(0).value0;

		// String sqlInsert;

		// retrieve content values
		if (method.jql.hasDynamicParts() || method.jql.containsSelectOperation) {
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate()", KriptonContentValues.class);
		} else {
			String psName = method.buildPreparedStatementName();
			classBuilder.addField(FieldSpec
					.builder(TypeName.get(SQLiteStatement.class), psName, Modifier.PRIVATE, Modifier.STATIC).build());

			methodBuilder.beginControlFlow("if ($L==null)", psName);
			SqlBuilderHelper.generateSQLForStaticQuery(method, methodBuilder);
			methodBuilder.addStatement("$L = $T.compile(_context, _sql)", psName, KriptonDatabaseWrapper.class);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate($L)", KriptonContentValues.class,
					psName);
		}

		List<SQLProperty> listUsedProperty = CodeBuilderUtility.extractUsedProperties(methodBuilder, method,
				BindSqlInsert.class);
					
		CodeBuilderUtility.generateContentValuesFromEntity(BaseProcessor.elementUtils, method, BindSqlInsert.class,
				methodBuilder, null);

		SQLProperty primaryKey = entity.getPrimaryKey();

		// generate javadoc and query
		generateJavaDoc(methodBuilder, method, returnType, listUsedProperty, primaryKey);

		SqlBuilderHelper.generateLog(method, methodBuilder);

		methodBuilder.addComment("insert operation");
		if (method.jql.hasDynamicParts() || method.jql.containsSelectOperation) {
			// does not memorize compiled statement, it can vary every time
			// generate SQL for insert
			SqlBuilderHelper.generateSQLForInsertDynamic(method, methodBuilder);
			methodBuilder.addStatement("long result = $T.insert(_context, _sql, _contentValues)",
					KriptonDatabaseWrapper.class);
		} else {
			String psName = method.buildPreparedStatementName();
			methodBuilder.addStatement("long result = $T.insert($L, _contentValues)", KriptonDatabaseWrapper.class,
					psName);
		}
		
		// if we are in mutable POJO work to set only id, for immutable object, we need to fix all fields.
		if (entity.isImmutablePojo()) {
			// only if we need to return an entity
			if (TypeUtility.isEquals(returnType, entity)) {
				methodBuilder.addComment("immutable POJO - create a copy with new id");
				ImmutableUtility.generateImmutableVariableInit(entity, methodBuilder);
				ImmutableUtility.generateImmutableVariableCopyFromEntity(entity, methodBuilder, entityParamName);
				// if pk is managed and autogenerated we have to overload its value with one obtained with execution of the query
				if (primaryKey != null && !primaryKey.isType(String.class)
						&& primaryKey.columnType != ColumnType.PRIMARY_KEY_UNMANGED) {
					methodBuilder.addCode("$L$L=result;\n", ImmutableUtility.IMMUTABLE_PREFIX, primaryKey.getName());										
				}				
				ImmutableUtility.generateImmutableEntityCreation(entity, methodBuilder, entityParamName, false);
			}
		} else {
			if (primaryKey != null && !primaryKey.isType(String.class)
					&& primaryKey.columnType != ColumnType.PRIMARY_KEY_UNMANGED) {
				// if PK string, can not overwrite id (with a long)
				// same thing if column type is UNMANAGED (user manage PK)
				methodBuilder.addComment(
						"if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)");
				if (primaryKey.isPublicField()) {
					methodBuilder.addCode("$L.$L=result;\n", entityParamName, primaryKey.getName());
				} else {
					methodBuilder.addCode("$L(result);\n",
							PropertyUtility.setter(typeName(entity.getElement()),entityParamName,  primaryKey));
				}

			}
		}
		
		if (method.getParent().getParent().generateRx) {
			// rx management
			String rxIdGetter=null;
			if (entity.getPrimaryKey().columnType==ColumnType.PRIMARY_KEY) {
				rxIdGetter="result";
			} else {
				// unmanaged pk
				for (SQLProperty item: listUsedProperty) {
					if (item.isPrimaryKey()) {
						rxIdGetter=entityParamName+"."+PropertyUtility.getter(item);
						break;
					}
				}	
			}								
			
			GenericSQLHelper.generateSubjectNext(entity, methodBuilder, SubjectType.INSERT, rxIdGetter);
		}

		// support for livedata
		if (daoDefinition.hasLiveData()) {
			methodBuilder.addComment("support for livedata");
			methodBuilder.addStatement(BindDaoBuilder.METHOD_NAME_REGISTRY_EVENT + "(result>0?1:0)");
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
			methodBuilder.addCode("return $L;\n", entityParamName);
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}

	}

	/**
	 * Generate java doc.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param returnType
	 *            the return type
	 * @param listUsedProperty
	 *            the list used property
	 * @param primaryKey
	 *            the primary key
	 */
	public void generateJavaDoc(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method, TypeName returnType,
			List<SQLProperty> listUsedProperty, ModelProperty primaryKey) {
		// transform JQL to SQL
		String sqlInsert = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {

			@Override
			public String onColumnName(String columnName) {
				Set<SQLProperty> property = currentSchema.getPropertyBySimpleName(columnName);

				SQLProperty tempProperty = property.iterator().next();
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

			@Override
			public String onBindParameter(String bindParameterName, boolean inStatement) {
				String resolvedParamName = method.findParameterNameByAlias(bindParameterName);

				return SqlAnalyzer.PARAM_PREFIX + resolvedParamName + SqlAnalyzer.PARAM_SUFFIX;
			}

		});

		// generate javadoc and result
		{
			String beanNameParameter = method.findParameterAliasByName(method.getParameters().get(0).value0);

			methodBuilder.addJavadoc("<p>SQL insert:</p>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>\n\n", sqlInsert);
			methodBuilder.addJavadoc(
					"<p><code>$L.$L</code> is automatically updated because it is the primary key</p>\n",
					beanNameParameter, primaryKey.getName());
			methodBuilder.addJavadoc("\n");

			// list of inserted fields
			methodBuilder.addJavadoc("<p><strong>Inserted columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (SQLProperty property : listUsedProperty) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", property.columnName);
				methodBuilder.addJavadoc("<dd>is mapped to <strong>$L</strong></dd>\n",
						SqlAnalyzer.PARAM_PREFIX + method.findParameterAliasByName(method.getParameters().get(0).value0)
								+ "." + method.findParameterNameByAlias(property.getName()) + SqlAnalyzer.PARAM_SUFFIX);
			}
			methodBuilder.addJavadoc("</dl>\n\n");

			// update bean have only one parameter: the bean to update
			for (Pair<String, TypeName> param : method.getParameters()) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				methodBuilder.addJavadoc("\n\tis mapped to parameter <strong>$L</strong>\n",
						method.findParameterAliasByName(param.value0));
			}

			InsertRawHelper.generateJavaDocReturnType(methodBuilder, returnType);
		}
	}

	/**
	 * Gets the conflict algorithm type.
	 *
	 * @param method
	 *            the method
	 * @return the conflict algorithm type
	 */
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

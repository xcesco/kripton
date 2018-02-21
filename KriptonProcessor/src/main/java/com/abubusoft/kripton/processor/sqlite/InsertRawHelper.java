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
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.database.sqlite.SQLiteStatement;

public class InsertRawHelper implements InsertCodeGenerator {

	@Override
	public void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, boolean mapFields, final SQLiteModelMethod method, TypeName returnType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();

		boolean nullable;

		// generate javadoc
		generateJavaDoc(methodBuilder, method, returnType);
		
		// standard INSERT
		if (method.jql.hasDynamicParts()  || method.jql.containsSelectOperation) {
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate()", KriptonContentValues.class);			
		} else {
			String psName=method.buildPreparedStatementName();
			// generate SQL for insert
			classBuilder.addField(FieldSpec.builder(TypeName.get(SQLiteStatement.class),  psName, Modifier.PRIVATE, Modifier.STATIC).build());								
			
			methodBuilder.beginControlFlow("if ($L==null)",psName);
			SqlBuilderHelper.generateSQLForStaticQuery(method, methodBuilder);
			methodBuilder.addStatement("$L = $T.compile(_context, _sql)", psName, KriptonDatabaseWrapper.class);						
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate($L)", KriptonContentValues.class, psName);
		}

		if (method.jql.containsSelectOperation) {
			// INSERT-SELECT
			GenericSQLHelper.generateGenericExecSQL(methodBuilder, method);
		} else {			
			methodBuilder.addCode("\n");
			
			List<Pair<String, TypeName>> fieldsToUpdate = method.getParameters();
			fieldsToUpdate=SqlBuilderHelper.orderContentValues(method, fieldsToUpdate);
			
			for (Pair<String, TypeName> item : fieldsToUpdate) {
				String propertyName = method.findParameterAliasByName(item.value0);
				
				SQLProperty property = entity.get(propertyName);
									
				if (property == null)
					throw (new PropertyNotFoundException(method, propertyName, item.value1));
								
				// check same type
				TypeUtility.checkTypeCompatibility(method, item, property);
				// check nullabliity
				nullable = TypeUtility.isNullable(method, item, property);

				if (nullable) {
					// it use raw method param's typeName
					methodBuilder.beginControlFlow("if ($L!=null)", item.value0);
				}
				
				if (method.isLogEnabled()) {
					methodBuilder.addCode("_contentValues.put($S, ", property.columnName);
				} else {
					methodBuilder.addCode("_contentValues.put(");
				}
				
				// it does not need to be converted in string
				SQLTransformer.javaMethodParam2ContentValues(methodBuilder, method, item.value0, item.value1, property);
				methodBuilder.addCode(");\n");
				if (nullable) {
					methodBuilder.nextControlFlow("else");					
					
					if (method.isLogEnabled()) {
						methodBuilder.addStatement("_contentValues.putNull($S)", property.columnName);
					} else {
						methodBuilder.addStatement("_contentValues.putNull()");
					}
					
					methodBuilder.endControlFlow();
				}

			}
			methodBuilder.addCode("\n");

			SqlBuilderHelper.generateLog(method, methodBuilder);
						
			methodBuilder.addComment("insert operation");
			if (method.jql.hasDynamicParts() || method.jql.containsSelectOperation) {
				// does not memorize compiled statement, it can vary every time generate SQL for insert
				SqlBuilderHelper.generateSQLForInsertDynamic(method, methodBuilder);					
				methodBuilder.addStatement("long result = $T.insert(_context, _sql, _contentValues)", KriptonDatabaseWrapper.class);
			} else {
				String psName=method.buildPreparedStatementName();
				methodBuilder.addStatement("long result = $T.insert($L, _contentValues)", KriptonDatabaseWrapper.class, psName);		
			}			
			
			if (method.getParent().getParent().generateRx) {
				methodBuilder.addStatement("subject.onNext($T.createInsert(result))", SQLiteModification.class);
			}

			// define return value
			if (returnType == TypeName.VOID) {
			} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
				methodBuilder.addCode("return result!=-1;\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
				methodBuilder.addCode("return result;\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
				methodBuilder.addCode("return (int)result;\n");
			} else {
				// more than one listener found
				throw (new InvalidMethodSignException(method, "invalid return type"));
			}
		}
	}

	/**
	 * @param methodBuilder
	 * @param method
	 * @param returnType
	 * @return string sql
	 */
	public String generateJavaDoc(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method, TypeName returnType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();
		final One<Boolean> inColumnValues = new One<Boolean>(false);
		final List<Pair<String, TypeName>> methodParamsUsedAsColumnValue = new ArrayList<>();
		final List<Pair<String, TypeName>> methodParamsUsedAsParameter = new ArrayList<>();

		// transform JQL to SQL
		String sqlInsert = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {

			@Override
			public void onColumnValueSetBegin(Column_value_setContext ctx) {
				inColumnValues.value0 = true;
			}

			@Override
			public void onColumnValueSetEnd(Column_value_setContext ctx) {
				inColumnValues.value0 = false;
			}

			@Override
			public String onColumnName(String columnName) {
				Set<SQLProperty> property = currentSchema.getPropertyBySimpleName(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(property != null, method, columnName);

				SQLProperty tempProperty = property.iterator().next();
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				String resolvedParamName = method.findParameterNameByAlias(bindParameterName);

				if (inColumnValues.value0) {
					methodParamsUsedAsColumnValue.add(new Pair<>(resolvedParamName, method.findParameterType(resolvedParamName)));
				} else {
					methodParamsUsedAsParameter.add(new Pair<>(resolvedParamName, method.findParameterType(resolvedParamName)));
				}

				return "${" + resolvedParamName + "}";
			}					

		});

		methodBuilder.addJavadoc("<h2>SQL insert</h2>\n");
		methodBuilder.addJavadoc("<pre>$L</pre>\n", sqlInsert);
		methodBuilder.addJavadoc("\n");

		if (methodParamsUsedAsColumnValue.size() > 0) {
			// list of inserted fields
			methodBuilder.addJavadoc("<h2>Inserted columns:</strong></h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeName> property : methodParamsUsedAsColumnValue) {
				//String resolvedName = method.findParameterAliasByName(property.value0);				
				String resolvedName=method.findParameterNameByAlias(property.value0);
				/*SQLProperty prop = entity.get(resolvedName);
								
				if (prop == null)
					throw (new PropertyNotFoundException(method, property.value0, property.value1));*/
				
				methodBuilder.addJavadoc("\t<dt>$L</dt>", property.value0);
				methodBuilder.addJavadoc("<dd>is binded to query's parameter <strong>$L</strong> and method's parameter <strong>$L</strong></dd>\n", "${" + property.value0 + "}", resolvedName);
			}
			methodBuilder.addJavadoc("</dl>\n\n");
		}

		// list of parameters
		if (methodParamsUsedAsParameter.size() > 0) {
			methodBuilder.addJavadoc("<h2>Method parameters used as sql parameters</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeName> property : methodParamsUsedAsParameter) {
				String resolvedName = method.findParameterNameByAlias(property.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", resolvedName);
				methodBuilder.addJavadoc("<dd>is binded to query's parameter <strong>$${$L}</strong></dd>\n", property.value0);
			}
			methodBuilder.addJavadoc("</dl>\n\n");
		}

		for (Pair<String, TypeName> param : method.getParameters()) {
			if (methodParamsUsedAsColumnValue.contains(param)) {
				methodBuilder.addJavadoc("@param $L\n", param.value0);
				
				if (entity.get(method.findParameterAliasByName(param.value0))!=null) {												
					methodBuilder.addJavadoc("\tis binded to column value <strong>$L</strong>\n", entity.get(method.findParameterAliasByName(param.value0)).columnName);
				} else {
					// in case of JQL explicit, you can declare name of parameter
					methodBuilder.addJavadoc("\tis binded to query parameter <strong>$L</strong>\n", param.value0);				}
			}

			if (methodParamsUsedAsParameter.contains(param)) {
				methodBuilder.addJavadoc("@param $L\n", param.value0);
				methodBuilder.addJavadoc("\tis used as parameter\n");
			}
		}

		generateJavaDocReturnType(methodBuilder, returnType);
		return sqlInsert;
	}

	/**
	 * Generate javadoc about return type of method
	 * 
	 * @param methodBuilder
	 * @param returnType
	 */
	public static void generateJavaDocReturnType(MethodSpec.Builder methodBuilder, TypeName returnType) {
		if (returnType == TypeName.VOID) {

		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addJavadoc("\n");
			methodBuilder.addJavadoc("@return <code>true</code> if record is inserted, <code>false</code> otherwise");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addJavadoc("\n");
			methodBuilder.addJavadoc("@return <strong>id</strong> of inserted record");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addJavadoc("\n");
			methodBuilder.addJavadoc("@return <strong>id</strong> of inserted record");
		}
		methodBuilder.addJavadoc("\n");
	}

}

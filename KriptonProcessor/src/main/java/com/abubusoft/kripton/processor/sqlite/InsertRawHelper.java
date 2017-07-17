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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
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
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class InsertRawHelper implements InsertCodeGenerator {

	@Override
	public String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, final SQLiteModelMethod method, TypeName returnType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();
		final SQLiteDatabaseSchema schema=daoDefinition.getParent();
		final List<String> paramsList=new ArrayList<String>();

		String sqlInsert;
		boolean nullable;

		// generate javadoc
		sqlInsert = generateJavaDoc(methodBuilder, method, returnType);

		if (method.jql.containsSelectOperation) {
			// INSERT-SELECT
			String sql=JQLChecker.getInstance().replace(method.jql, new JQLReplacerListenerImpl() {
				@Override
				public String onColumnName(String columnName) {
					return schema.getExactPropertyBySimpleName(method, columnName);					
				}
				
				@Override
				public String onTableName(String tableName) {
					return schema.getEntityBySimpleName(tableName).getTableName();
				}
				
				@Override
				public String onBindParameter(String bindParameterName) {
					String propertyName = method.findParameterAliasByName(bindParameterName);
					paramsList.add(propertyName);
					return "?";
				}
			});			
			
			final One<Integer> paramCounter=new One<Integer>(0);
			String sqlForLog=JQLChecker.getInstance().replace(method.jql, new JQLReplacerListenerImpl() {
				@Override
				public String onColumnName(String columnName) {
					return schema.getExactPropertyBySimpleName(method, columnName);					
				}
				
				@Override
				public String onTableName(String tableName) {
					return schema.getEntityBySimpleName(tableName).getTableName();
				}
				
				@Override
				public String onBindParameter(String bindParameterName) {
					return ":"+(paramCounter.value0++);
				}
			});
			
			methodBuilder.addStatement("$T<String> _sqlWhereParams=new $T<String>()", List.class, ArrayList.class);
				
			methodBuilder.addCode("\n// build where condition\n");			
			{
				String separator = "";
				TypeName paramType;				
				int i = 0;
				String realName;
				
				for (String item : paramsList) {
					methodBuilder.addCode("_sqlWhereParams.add(");					

					paramType = method.findParameterTypeByAliasOrName(item);
					realName=method.findParameterNameByAlias(item);

					// code for query arguments
					nullable = TypeUtility.isNullable(paramType);
					if (nullable) {
						methodBuilder.addCode("($L==null?\"\":", item);
					}

					// check for string conversion
					TypeUtility.beginStringConversion(methodBuilder, paramType);

					SQLTransformer.java2ContentValues(methodBuilder, daoDefinition,paramType, realName);

					// check for string conversion
					TypeUtility.endStringConversion(methodBuilder, paramType);

					if (nullable) {
						methodBuilder.addCode(")");
					}

					separator = ", ";
					i++;
					
					methodBuilder.addCode(");\n");
				}				
			}
			
			if (daoDefinition.isLogEnabled()) {
			    // manage log			    
				methodBuilder.addStatement("$T.info($S)", Logger.class, sqlForLog);
			}
			
			// log for where parames
			SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);			
			methodBuilder.addStatement("database().execSQL($S, _sqlWhereParams.toArray(new Object[_sqlWhereParams.size()]))", sql);		    
		} else {
			// standard INSERT
			methodBuilder.addCode("$T contentValues=contentValues();\n", ContentValues.class);
			methodBuilder.addCode("contentValues.clear();\n\n");
			for (Pair<String, TypeName> item : method.getParameters()) {
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
				methodBuilder.addCode("contentValues.put($S, ", property.columnName);
				// it does not need to be converted in string

				SQLTransformer.java2ContentValues(methodBuilder, daoDefinition, item.value1, item.value0);
				// SQLTransformer.java2ContentValues(methodBuilder, item.value1,
				// item.value0);
				methodBuilder.addCode(");\n");
				if (nullable) {
					methodBuilder.nextControlFlow("else");
					methodBuilder.addCode("contentValues.putNull($S);\n", property.columnName);
					methodBuilder.endControlFlow();
				}
				methodBuilder.addCode("\n");
			}

			SqlBuilderHelper.generateLogForInsert(method, methodBuilder);

			ConflictAlgorithmType conflictAlgorithmType = InsertBeanHelper.getConflictAlgorithmType(method);
			String conflictString1 = "";
			String conflictString2 = "";

			if (conflictAlgorithmType != ConflictAlgorithmType.NONE) {
				conflictString1 = "WithOnConflict";
				conflictString2 = ", SQLiteDatabase." + conflictAlgorithmType;
				methodBuilder.addCode("// use $T conflicts algorithm\n", SQLiteDatabase.class);
			}

			// define return value
			if (returnType == TypeName.VOID) {
				methodBuilder.addCode("database().insert$L($S, null, contentValues$L);\n", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
			} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
				methodBuilder.addCode("long result = database().insert$L($S, null, contentValues$L);\n", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
				methodBuilder.addCode("return result!=-1;\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
				methodBuilder.addCode("long result = database().insert$L($S, null, contentValues$L);\n", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
				methodBuilder.addCode("return result;\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
				methodBuilder.addCode("int result = (int)database().insert$L($S, null, contentValues$L);\n", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
				methodBuilder.addCode("return result;\n");
			} else {
				// more than one listener found
				throw (new InvalidMethodSignException(method, "invalid return type"));
			}
		}

		return sqlInsert;
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
		String sqlInsert;
		final One<Boolean> inColumnValues = new One<Boolean>(false);
		final List<Pair<String, TypeName>> methodParamsUsedAsColumnValue = new ArrayList<>();
		final List<Pair<String, TypeName>> methodParamsUsedAsParameter = new ArrayList<>();
		// new
		sqlInsert = JQLChecker.getInstance().replace(method.jql, new JQLReplacerListenerImpl() {

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
				String convertedColumnName = entity.get(columnName).columnName;
				return convertedColumnName;
			}

			@Override
			public String onTableName(String tableName) {
				return daoDefinition.getParent().getEntityBySimpleName(tableName).getTableName();
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

		methodBuilder.addJavadoc("<p>SQL insert:</p>\n");
		methodBuilder.addJavadoc("<pre>$L</pre>\n", sqlInsert);
		methodBuilder.addJavadoc("\n");

		if (methodParamsUsedAsColumnValue.size() > 0) {
			// list of inserted fields
			methodBuilder.addJavadoc("<p><strong>Inserted columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeName> property : methodParamsUsedAsColumnValue) {
				String resolvedName = method.findParameterAliasByName(property.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", entity.get(resolvedName).columnName);
				methodBuilder.addJavadoc("<dd>is binded to query's parameter <strong>$L</strong> and method's parameter <strong>$L</strong></dd>\n", "${" + resolvedName + "}", property.value0);
			}
			methodBuilder.addJavadoc("</dl>\n\n");
		}

		// list of parameters
		if (methodParamsUsedAsParameter.size() > 0) {
			methodBuilder.addJavadoc("<p><strong>Inserted columns:</strong></p>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeName> property : methodParamsUsedAsParameter) {
				String resolvedName = method.findParameterNameByAlias(property.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", resolvedName);
				methodBuilder.addJavadoc("<dd>is binded to query's parameter <strong>$L</strong> and method's parameter <strong>$L</strong></dd>\n", "${" + resolvedName + "}", property.value0);
			}
			methodBuilder.addJavadoc("</dl>\n\n");
		}

		for (Pair<String, TypeName> param : method.getParameters()) {
			if (methodParamsUsedAsColumnValue.contains(param)) {
				methodBuilder.addJavadoc("@param $L\n", param.value0);
				methodBuilder.addJavadoc("\tis binded to column value <strong>$L</strong>\n", entity.get(method.findParameterAliasByName(param.value0)).columnName);
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
		methodBuilder.addJavadoc("\n");
		if (returnType == TypeName.VOID) {
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addJavadoc("@return <code>true</code> if record is inserted, <code>false</code> otherwise");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addJavadoc("@return <strong>id</strong> of inserted record");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addJavadoc("@return <strong>id</strong> of inserted record");
		}
		methodBuilder.addJavadoc("\n");
	}

}

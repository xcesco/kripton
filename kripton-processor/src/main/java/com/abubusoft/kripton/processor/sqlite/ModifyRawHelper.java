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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isNullable;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * The Class ModifyRawHelper.
 */
public class ModifyRawHelper implements ModifyCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SqlModifyBuilder.
	 * ModifyCodeGenerator#generate(com.squareup.javapoet.TypeSpec.Builder,
	 * com.squareup.javapoet.MethodSpec.Builder, boolean,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod,
	 * com.squareup.javapoet.TypeName)
	 */
	@Override
	public void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		SQLiteEntity entity = method.getEntity();

		// separate params used for update bean and params used in
		// whereCondition
		// analyze whereCondition
		String whereCondition = extractWhereConditions(updateMode, method);

		// this method is invoked to check if every parameter is binded to an
		// method param
		SqlUtility.extractParametersFromString(method.jql.value, method, entity);

		Pair<String, List<Pair<String, TypeName>>> where = SqlUtility.extractParametersFromString(whereCondition, method, entity);

		// defines which parameter is used like update field and which is used
		// in where condition.
		List<Pair<String, TypeName>> methodParams = method.getParameters();
		List<Pair<String, TypeName>> updateableParams = new ArrayList<Pair<String, TypeName>>();
		List<Pair<String, TypeName>> whereParams = new ArrayList<Pair<String, TypeName>>();

		String name;

		for (Pair<String, TypeName> param : methodParams) {
			name = method.findParameterAliasByName(param.value0);

			if (method.isThisDynamicWhereConditionsName(name)) {
				// skip for dynamic where
				continue;
			}

			if (method.isThisDynamicWhereArgsName(name)) {
				// skip for dynamic where
				continue;
			}

			if (where.value1.contains(new Pair<>(name, param.value1))) {
				whereParams.add(param);
			} else {
				updateableParams.add(param);
			}
		}

		// clear contentValues
		if (method.jql.hasDynamicParts() || method.jql.containsSelectOperation) {
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate()", KriptonContentValues.class);
		} else {
			String psName = method.buildPreparedStatementName();
			// generate SQL for insert
			classBuilder.addField(FieldSpec.builder(TypeName.get(SupportSQLiteStatement.class), psName, Modifier.PRIVATE, Modifier.STATIC).build());

			methodBuilder.beginControlFlow("if ($L==null)", psName);
			SqlBuilderHelper.generateSQLForStaticQuery(method, methodBuilder);
			methodBuilder.addStatement("$L = $T.compile(_context, _sql)", psName, KriptonDatabaseHelper.class);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$T _contentValues=contentValuesForUpdate($L)", KriptonContentValues.class, psName);
		}

		if (method.jql.containsSelectOperation) {
			generateJavaDoc(method, methodBuilder, updateMode);

			GenericSQLHelper.generateGenericExecSQL(methodBuilder, method);
		} else {
			// generate javadoc
			generateJavaDoc(method, methodBuilder, updateMode, whereCondition, where, methodParams);

			if (updateMode) {
				//AssertKripton.assertTrueOrInvalidMethodSignException(updateableParams.size() > 0, method, "no column was selected for update");

				// order item for content values
				updateableParams = SqlBuilderHelper.orderContentValues(method, updateableParams);

				for (Pair<String, TypeName> item : updateableParams) {
					String resolvedParamName = method.findParameterAliasByName(item.value0);
					SQLProperty property = entity.get(resolvedParamName);
					if (property == null)
						throw (new PropertyNotFoundException(method, resolvedParamName, item.value1));

					// check same type
					TypeUtility.checkTypeCompatibility(method, item, property);
					// boolean nullable=TypeUtility.isNullable(method, item,
					// property) && !property.hasTypeAdapter();
					//
					// if (nullable) {
					// methodBuilder.beginControlFlow("if ($L!=null)",
					// item.value0);
					// }

					// here it needed raw parameter typeName
					if (method.isLogEnabled()) {
						methodBuilder.addCode("_contentValues.put($S, ", property.columnName);
					} else {
						methodBuilder.addCode("_contentValues.put(");
					}

					SQLTransformer.javaMethodParam2ContentValues(methodBuilder, method, item.value0, TypeUtility.typeName(property.getElement()), property);

					methodBuilder.addCode(");\n");

					// if (nullable) {
					// methodBuilder.nextControlFlow("else");
					//
					// if (method.isLogEnabled()) {
					// methodBuilder.addStatement("_contentValues.putNull($S)",
					// property.columnName);
					// } else {
					// methodBuilder.addStatement("_contentValues.putNull()");
					// }
					//
					// methodBuilder.endControlFlow();
					// }
				}

				methodBuilder.addCode("\n");

			} else {
				if (updateableParams.size() > 0) {
					String separator = "";
					StringBuilder buffer = new StringBuilder();
					for (Pair<String, TypeName> item : updateableParams) {
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

			ModifyBeanHelper.generateModifyQueryCommonPart(method, classBuilder, methodBuilder);

			// support for livedata
			if (daoDefinition.hasLiveData()) {
				methodBuilder.addComment("support for livedata");
				methodBuilder.addStatement(BindDaoBuilder.METHOD_NAME_REGISTRY_EVENT + "(result)");
			}

			// return management
			// if true, field must be associate to ben attributes
			if (returnType == TypeName.VOID) {

			} else {
				if (isIn(returnType, Boolean.TYPE, Boolean.class)) {
					methodBuilder.addStatement("return result!=0");
				} else if (isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
					methodBuilder.addStatement("return result");
				} else {
					// more than one listener found
					throw (new InvalidMethodSignException(method, "invalid return type"));
				}
			}

		}

	}

	/**
	 * Generate java doc.
	 *
	 * @param method
	 *            the method
	 * @param methodBuilder
	 *            the method builder
	 * @param updateMode
	 *            the update mode
	 */
	private void generateJavaDoc(final SQLiteModelMethod method, Builder methodBuilder, boolean updateMode) {
		List<Pair<String, TypeName>> methodParams = method.getParameters();

		final List<SQLProperty> updatedProperties = new ArrayList<>();
		final List<Pair<String, TypeName>> methodParamsUsedAsParameter = new ArrayList<>();

		// new
		String sqlModify = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {

			@Override
			public String onColumnNameToUpdate(String columnName) {
				SQLProperty tempProperty = currentEntity.get(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				updatedProperties.add(tempProperty);

				return tempProperty.columnName;
			}

			@Override
			public String onColumnName(String columnName) {
				SQLProperty tempProperty = currentEntity.get(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

			@Override
			public String onBindParameter(String bindParameterName, boolean inStatement) {
				String resolvedParamName = method.findParameterNameByAlias(bindParameterName);
				AssertKripton.assertTrueOrUnknownParamInJQLException(resolvedParamName != null, method, bindParameterName);

				methodParamsUsedAsParameter.add(new Pair<>(resolvedParamName, method.findParameterType(resolvedParamName)));

				return SqlAnalyzer.PARAM_PREFIX + bindParameterName + SqlAnalyzer.PARAM_SUFFIX;
			}

		});

		if (updateMode) {
			methodBuilder.addJavadoc("<h2>SQL update</h2>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>\n", sqlModify);
			methodBuilder.addJavadoc("\n");

			// list of updated fields
			methodBuilder.addJavadoc("<h2>Updated columns:</h2>\n");
			methodBuilder.addJavadoc("<ul>\n");
			for (SQLProperty property : updatedProperties) {
				methodBuilder.addJavadoc("\t<li>$L</li>\n", property.columnName);
			}
			methodBuilder.addJavadoc("</ul>");
			methodBuilder.addJavadoc("\n\n");

		} else {
			methodBuilder.addJavadoc("<h2>SQL delete</h2>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>\n", sqlModify);
			methodBuilder.addJavadoc("\n\n");
		}

		// list of where parameter
		methodBuilder.addJavadoc("<h2>Parameters:</h2>\n");
		methodBuilder.addJavadoc("<dl>\n");
		for (Pair<String, TypeName> property : methodParamsUsedAsParameter) {
			String rawName = method.findParameterNameByAlias(property.value0);
			methodBuilder.addJavadoc("\t<dt>$L</dt>", SqlAnalyzer.PARAM_PREFIX + property.value0 + SqlAnalyzer.PARAM_SUFFIX);
			methodBuilder.addJavadoc("<dd>is mapped to method's parameter <strong>$L</strong></dd>\n", rawName);
		}
		methodBuilder.addJavadoc("</dl>");
		methodBuilder.addJavadoc("\n\n");

		if (method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc("<dl>\n");
			methodBuilder.addJavadoc("<dt>$L</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{$L}</dd>", method.dynamicWhereParameterName,
					JQLDynamicStatementType.DYNAMIC_WHERE);
			methodBuilder.addJavadoc("\n</dl>");
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

			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// method parameters
		if (methodParams.size() > 0) {
			for (Pair<String, TypeName> param : methodParams) {
				String resolvedName = method.findParameterAliasByName(param.value0);
				methodBuilder.addJavadoc("@param $L", param.value0);
				if (method.isThisDynamicWhereConditionsName(param.value0)) {
					methodBuilder.addJavadoc("\n\tis used as dynamic where conditions\n");
				} else {
					methodBuilder.addJavadoc("\n\tis used as for parameter <strong>$L</strong>\n", resolvedName);
				}
			}
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();
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

				// methodBuilder.addCode("return result;\n");
			} else {
				// more than one listener found
				throw (new InvalidMethodSignException(method, "invalid return type"));
			}
			methodBuilder.addJavadoc("\n");
		}

	}

	/**
	 * Extract where conditions.
	 *
	 * @param updateMode
	 *            the update mode
	 * @param method
	 *            the method
	 * @return the string
	 */
	static String extractWhereConditions(boolean updateMode, SQLiteModelMethod method) {
		final One<String> whereCondition = new One<String>("");
		final One<Boolean> found = new One<Boolean>(null);
		JQLChecker.getInstance().replaceVariableStatements(method, method.jql.value, new JQLReplaceVariableStatementListenerImpl() {

			@Override
			public String onWhere(String statement) {
				if (found.value0 == null) {
					whereCondition.value0 = statement;
					found.value0 = true;
				}
				return null;
			}

		});

		return StringUtils.ifNotEmptyAppend(whereCondition.value0, " ");
	}

	/**
	 * Generate java doc.
	 *
	 * @param method
	 *            the method
	 * @param methodBuilder
	 *            the method builder
	 * @param updateMode
	 *            the update mode
	 * @param whereCondition
	 *            the where condition
	 * @param where
	 *            the where
	 * @param methodParams
	 *            the method params
	 */
	private void generateJavaDoc(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder, boolean updateMode, String whereCondition, Pair<String, List<Pair<String, TypeName>>> where,
			List<Pair<String, TypeName>> methodParams) {

		final List<SQLProperty> updatedProperties = new ArrayList<>();
		final One<Boolean> onWhereStatement = new One<Boolean>(false);

		String sqlModify = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {

			@Override
			public void onWhereStatementBegin(Where_stmtContext ctx) {
				onWhereStatement.value0 = true;
			}

			@Override
			public void onWhereStatementEnd(Where_stmtContext ctx) {
				onWhereStatement.value0 = false;
			}

			@Override
			public String onColumnNameToUpdate(String columnName) {
				SQLProperty tempProperty = currentEntity.get(columnName);

				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				updatedProperties.add(tempProperty);

				return tempProperty.columnName;
			}

			@Override
			public String onColumnName(String columnName) {
				SQLProperty tempProperty = currentEntity.get(columnName);

				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

			@Override
			public String onBindParameter(String bindParameterName, boolean inStatement) {
				String resolvedParamName = method.findParameterNameByAlias(bindParameterName);
				AssertKripton.assertTrueOrUnknownParamInJQLException(resolvedParamName != null, method, bindParameterName);

				if (onWhereStatement.value0) {
					return SqlAnalyzer.PARAM_PREFIX + bindParameterName + SqlAnalyzer.PARAM_SUFFIX;
				} else {
					return SqlAnalyzer.PARAM_PREFIX + bindParameterName + SqlAnalyzer.PARAM_SUFFIX;
				}

			}

		});

		if (updateMode) {
			methodBuilder.addJavadoc("<h2>SQL update</h2>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>\n", sqlModify);
			methodBuilder.addJavadoc("\n");

			// list of updated fields
			methodBuilder.addJavadoc("<h2>Updated columns:</h2>\n");
			methodBuilder.addJavadoc("<ul>\n");
			for (SQLProperty property : updatedProperties) {
				methodBuilder.addJavadoc("\t<li>$L</li>\n", property.columnName);
			}
			methodBuilder.addJavadoc("</ul>");
			methodBuilder.addJavadoc("\n\n");

		} else {
			methodBuilder.addJavadoc("<h2>SQL delete</h2>\n");
			methodBuilder.addJavadoc("<pre>$L</pre>", sqlModify);
			methodBuilder.addJavadoc("\n\n");
		}

		// list of where parameter
		if (where.value1.size() > 0) {
			methodBuilder.addJavadoc("<h2>Where parameters:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Pair<String, TypeName> property : where.value1) {
				String rawName = method.findParameterNameByAlias(property.value0);
				methodBuilder.addJavadoc("\t<dt>$L</dt>", SqlAnalyzer.PARAM_PREFIX + property.value0 + SqlAnalyzer.PARAM_SUFFIX);
				methodBuilder.addJavadoc("<dd>is mapped to method's parameter <strong>$L</strong></dd>\n", rawName);
			}
			methodBuilder.addJavadoc("</dl>");
		} else {
			methodBuilder.addJavadoc("<p>No where parameters were found.</p>");
		}
		methodBuilder.addJavadoc("\n\n");

		if (method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc("<dl>\n");
			methodBuilder.addJavadoc("<dt>$L</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{$L}</dd>", method.dynamicWhereParameterName,
					JQLDynamicStatementType.DYNAMIC_WHERE);
			methodBuilder.addJavadoc("\n</dl>");
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

			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// method parameters
		if (methodParams.size() > 0) {
			for (Pair<String, TypeName> param : methodParams) {
				String resolvedName = method.findParameterAliasByName(param.value0);
				methodBuilder.addJavadoc("@param $L", param.value0);
				if (method.isThisDynamicWhereConditionsName(param.value0)) {
					methodBuilder.addJavadoc("\n\tis used as dynamic where conditions\n");
				} else if (where.value1.contains(new Pair<>(resolvedName, param.value1))) {
					methodBuilder.addJavadoc("\n\tis used as where parameter <strong>$L</strong>\n", SqlAnalyzer.PARAM_PREFIX + resolvedName + SqlAnalyzer.PARAM_SUFFIX);
				} else {
					methodBuilder.addJavadoc("\n\tis used as updated field <strong>$L</strong>\n", resolvedName);
				}
			}
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();
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
				// methodBuilder.addCode("return result!=0;\n");
			} else if (isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
				if (updateMode) {
					methodBuilder.addJavadoc("@return number of updated records");
				} else {
					methodBuilder.addJavadoc("@return number of deleted records");
				}

				// methodBuilder.addCode("return result;\n");
			} else {
				// more than one listener found
				throw (new InvalidMethodSignException(method, "invalid return type"));
			}
			methodBuilder.addJavadoc("\n");
		}

	}

	/**
	 * Generate where condition.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param where
	 *            the where
	 */
	public static void generateWhereCondition(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, Pair<String, List<Pair<String, TypeName>>> where) {
		boolean nullable;

		// methodBuilder.addStatement("$T<String>
		// _sqlWhereParams=getWhereParamsArray()", ArrayList.class);

		for (Pair<String, TypeName> item : where.value1) {
			String resolvedParamName = method.findParameterNameByAlias(item.value0);
			// methodBuilder.addCode("_sqlWhereParams.add(");
			methodBuilder.addCode("_contentValues.addWhereArgs(");

			nullable = isNullable(item.value1);

			if (nullable && !method.hasAdapterForParam(item.value0)) {
				// transform null in ""
				methodBuilder.addCode("($L==null?\"\":", resolvedParamName);
			}

			// check for string conversion
			TypeUtility.beginStringConversion(methodBuilder, item.value1);

			SQLTransformer.javaMethodParam2WhereConditions(methodBuilder, method, resolvedParamName, item.value1);
			// check for string conversion
			TypeUtility.endStringConversion(methodBuilder, item.value1);

			if (nullable && !method.hasAdapterForParam(item.value0)) {
				methodBuilder.addCode(")");
			}

			methodBuilder.addCode(");\n");
		}
	}

	/**
	 * Checks if is in.
	 *
	 * @param value
	 *            the value
	 * @param classes
	 *            the classes
	 * @return true, if is in
	 */
	static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}

}

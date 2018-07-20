/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericSQLHelper.
 */
public abstract class GenericSQLHelper {
	
	/**
	 * The Enum SubjectType.
	 */
	public enum SubjectType {
		
		/** The insert. */
		INSERT("Insert"),
		
		/** The update. */
		UPDATE("Update"),
		
		/** The delete. */
		DELETE("Delete");
		
		/**
		 * Instantiates a new subject type.
		 *
		 * @param value the value
		 */
		private SubjectType(String value) {
			this.value=value;
		}
		
		/** The value. */
		private String value;
		
		/**
		 * Value.
		 *
		 * @return the string
		 */
		public String value() {
			return value;
		}
	}
	
	/**
	 * Generate subject next.
	 *
	 * @param methodBuilder the method builder
	 * @param subjectType the subject type
	 */
	public static void generateSubjectNext(MethodSpec.Builder methodBuilder, SubjectType subjectType) {
		methodBuilder.beginControlFlow("if (result>0)");
		methodBuilder.addStatement("subject.onNext($T.create$L(result))", SQLiteEvent.class, subjectType.value());
		methodBuilder.endControlFlow();
	}
	
	/**
	 * Generate generic exec SQL.
	 *
	 * @param methodBuilder the method builder
	 * @param method the method
	 */
	public static void generateGenericExecSQL(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method) {
		final SQLiteDaoDefinition daoDefinition = method.getParent();		

		boolean nullable;
		final List<String> paramsList = new ArrayList<String>();
		final List<String> contentValueList = new ArrayList<String>();		
		final One<Boolean> columnsToUpdate=new One<Boolean>(true);
		
		String sql = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {					
			
			@Override
			public void onWhereStatementBegin(Where_stmtContext ctx) {
				super.onWhereStatementBegin(ctx);
				
				columnsToUpdate.value0=false;
			}
			
			@Override
			public void onWhereStatementEnd(Where_stmtContext ctx) {
				super.onWhereStatementEnd(ctx);
				
				columnsToUpdate.value0=true;
			}
					
						
			@Override
			public String onColumnName(String columnName) {
				String resolvedName = currentSchema.findColumnNameByPropertyName(method, columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(resolvedName != null, method, columnName);

				return resolvedName;

			}

			@Override
			public String onBindParameter(String bindParameterName,boolean inStatement) {
				String propertyName = method.findParameterAliasByName(bindParameterName);
				
				if (columnsToUpdate.value0) {
					contentValueList.add(propertyName);
				} else {
					paramsList.add(propertyName);
				}
				return "?";
			}
		});

				
		// update/insert columns
		final SQLiteEntity entity = daoDefinition.getEntity();
		for (String item : contentValueList) {
			// ASSERT: property is always in entity
			String propertyName = method.findParameterNameByAlias(item);
			TypeName paramType = method.findParameterTypeByAliasOrName(item);			
			
			SQLProperty property = entity.get(item);
								
			if (propertyName == null)
				throw (new PropertyNotFoundException(method, propertyName, paramType));
							
			Pair<String, TypeName> methodParam = new Pair<String, TypeName>(propertyName, paramType);
			
			// check same type
			TypeUtility.checkTypeCompatibility(method, methodParam, property);
			// check nullabliity
//			nullable = TypeUtility.isNullable(method, methodParam, property);
//
//			if (nullable) {
//				// it use raw method param's typeName
//				methodBuilder.beginControlFlow("if ($L!=null)", methodParam.value0);
//			}
			
			if (method.isLogEnabled()) {
				methodBuilder.addCode("_contentValues.put($S, ", property.columnName);
			} else {
				methodBuilder.addCode("_contentValues.put(");
			}
			
			// it does not need to be converted in string
			SQLTransformer.javaMethodParam2ContentValues(methodBuilder, method, methodParam.value0, methodParam.value1, property);
			methodBuilder.addCode(");\n");
//			if (nullable) {
//				methodBuilder.nextControlFlow("else");					
//				
//				if (method.isLogEnabled()) {
//					methodBuilder.addStatement("_contentValues.putNull($S)", property.columnName);
//				} else {
//					methodBuilder.addStatement("_contentValues.putNull()");
//				}
//				
//				methodBuilder.endControlFlow();
//			}

		}

		// where condition
		methodBuilder.addComment("build where condition");
		{
			// String separator = "";
			TypeName paramType;
			String realName;

			for (String item : paramsList) {
				methodBuilder.addCode("_contentValues.addWhereArgs(");

				paramType = method.findParameterTypeByAliasOrName(item);
				realName = method.findParameterNameByAlias(item);
				
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(paramType!=null, method, item);

				// code for query arguments
				nullable = TypeUtility.isNullable(paramType);
				if (nullable) {
					methodBuilder.addCode("($L==null?\"\":", realName);
				}

				// check for string conversion
				TypeUtility.beginStringConversion(methodBuilder, paramType);

				SQLTransformer.javaMethodParam2ContentValues(methodBuilder, method, realName, paramType, null);

				// check for string conversion
				TypeUtility.endStringConversion(methodBuilder, paramType);

				if (nullable) {
					methodBuilder.addCode(")");
				}

				methodBuilder.addCode(");\n");
			}
		}

		// log for where parames
		SqlBuilderHelper.generateLog(method, methodBuilder);
		
		// log		
		methodBuilder.addCode("\n");
		methodBuilder.addStatement("database().execSQL($S, _contentValues.whereArgsAsArray())", sql);
	}

}

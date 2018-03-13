package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public abstract class GenericSQLHelper {
	
	public enum SubjectType {
		INSERT("Insert"),
		UPDATE("Update"),
		DELETE("Delete");
		
		private SubjectType(String value) {
			this.value=value;
		}
		
		private String value;
		
		public String value() {
			return value;
		}
	}
	
	public static void generateSubjectNext(MethodSpec.Builder methodBuilder, SubjectType subjectType) {
		methodBuilder.beginControlFlow("if (result>0)");
		methodBuilder.addStatement("subject.onNext($T.create$L(result))", SQLiteModification.class, subjectType.value());
		methodBuilder.endControlFlow();
	}
	
	/**
	 * @param methodBuilder
	 * @param method
	 * @param daoDefinition
	 * @param schema
	 */
	public static void generateGenericExecSQL(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method) {
		final SQLDaoDefinition daoDefinition = method.getParent();		

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
			public String onBindParameter(String bindParameterName) {
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
		final SQLEntity entity = daoDefinition.getEntity();
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

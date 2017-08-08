package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public abstract class GenericSQLHelper {
	/**
	 * @param methodBuilder
	 * @param method
	 * @param daoDefinition
	 * @param schema
	 */
	public static void generateGenericExecSQL(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method) {
		final SQLDaoDefinition daoDefinition = method.getParent();		
		final SQLiteDatabaseSchema schema=daoDefinition.getParent();
		
		boolean nullable;
		final List<String> paramsList=new ArrayList<String>();
		String sql=JQLChecker.getInstance().replace(method.jql, new JQLReplacerListenerImpl() {
			@Override
			public String onColumnName(String columnName) {												
				String resolvedName = schema.getExactPropertyBySimpleName(method, columnName);				
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(resolvedName!=null, method, columnName);
								
				return resolvedName;
				
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
				String resolvedName = schema.getExactPropertyBySimpleName(method, columnName);				
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(resolvedName!=null, method, columnName);
								
				return resolvedName;
			}
			
			@Override
			public String onTableName(String tableName) {
				return schema.getEntityBySimpleName(tableName).getTableName();
			}
			
			@Override
			public String onBindParameter(String bindParameterName) {										
				return "${param"+(paramCounter.value0++)+"}";
			}
		});
		
		methodBuilder.addStatement("$T<String> _sqlWhereParams=getWhereParamsArray()", ArrayList.class);
			
		methodBuilder.addCode("\n// build where condition\n");			
		{
			//String separator = "";
			TypeName paramType;				
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

				methodBuilder.addCode(");\n");
			}				
		}
		
		if (daoDefinition.isLogEnabled()) {
		   // manage log
			methodBuilder.addCode("\n");
			methodBuilder.addStatement("$T.info($S)", Logger.class, sqlForLog);
		}
		
		// log for where parames
		SqlBuilderHelper.generateLogForWhereParameters(method, methodBuilder);
		methodBuilder.addCode("\n");
		methodBuilder.addStatement("database().execSQL($S, _sqlWhereParams.toArray(new Object[_sqlWhereParams.size()]))", sql);
	}

}

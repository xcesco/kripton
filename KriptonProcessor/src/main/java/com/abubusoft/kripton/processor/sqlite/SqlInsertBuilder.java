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

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

import android.content.ContentValues;
import android.net.Uri;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 * @since 05/mag/2016
 */
public abstract class SqlInsertBuilder {

	public enum InsertType {
		INSERT_BEAN(InsertBeanHelper.class, true), INSERT_RAW(InsertRawHelper.class, false);

		private InsertCodeGenerator codeGenerator;

		private boolean mapFields;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}

		private InsertType(Class<? extends InsertCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}

		public String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType) {
			return codeGenerator.generate(elementUtils, methodBuilder, this.isMapFields(), method, returnType);

		}
	}

	public interface InsertCodeGenerator {
		String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteModelMethod method) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		InsertType insertResultType = null;

		// check type of arguments
		int count = 0;
		for (Pair<String, TypeName> param : method.getParameters()) {
			if (TypeUtility.isEquals(param.value1, typeName(entity.getElement()))) {
				count++;
			}
		}

		if (count == 0) {
			// method to insert raw data: no bean is used
			insertResultType = InsertType.INSERT_RAW;

			ModelAnnotation annotation = method.getAnnotation(BindSqlInsert.class);

			// check value attribute
			AssertKripton.failWithInvalidMethodSignException(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.FIELDS).size() > 0, method,
					" can not use attribute %s in this kind of query definition", AnnotationAttributeType.FIELDS.getValue());

			// check excludeFields attribute
			AssertKripton.failWithInvalidMethodSignException(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.EXCLUDED_FIELDS).size() > 0, method,
					" can not use attribute %s in this kind of query definition", AnnotationAttributeType.EXCLUDED_FIELDS.getValue());

			// check if there is only one parameter
			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() != 1 && TypeUtility.isEquals(method.getParameters().get(0).value1, daoDefinition.getEntityClassName()),
					method);

			// check no
			AssertKripton.failWithInvalidMethodSignException(annotation.getAttributeAsBoolean(AnnotationAttributeType.INCLUDE_PRIMARY_KEY), method, "attribute '%s' can not be used here",
					AnnotationAttributeType.INCLUDE_PRIMARY_KEY.getValue());

		} else if (count == 1) {
			insertResultType = InsertType.INSERT_BEAN;

			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() > 1, method, " aspected only one parameter of %s type", daoDefinition.getEntityClassName());
		} else {
			throw (new InvalidMethodSignException(method, "only one parameter of type " + typeName(entity.getElement()) + " can be used"));
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();

		AssertKripton.failWithInvalidMethodSignException(insertResultType == null, method);

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// generate inner code
		insertResultType.generate(elementUtils, methodBuilder, method, returnType);

		MethodSpec methodSpec = methodBuilder.build();
		builder.addMethod(methodSpec);

		if (method.contentProviderEntryPathEnabled) {
			// we need to generate insert for content provider to			
			generateInsertForContentProvider(elementUtils, builder, method, insertResultType);
		}

	}

	/**
	 * <p>Generate insert used in content provider class.</p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param insertResultType
	 */
	private static void generateInsertForContentProvider(Elements elementUtils, Builder builder, final SQLiteModelMethod method, InsertType insertResultType) {
		final SQLDaoDefinition daoDefinition = method.getParent();		
		final SQLEntity entity = daoDefinition.getEntity();
		final Set<String> columns=new LinkedHashSet<>();
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);
		ParameterSpec parameterSpec;
		parameterSpec = ParameterSpec.builder(Uri.class, "uri").build();
		methodBuilder.addParameter(parameterSpec);
		parameterSpec = ParameterSpec.builder(ContentValues.class, "contentValues").build();
		methodBuilder.addParameter(parameterSpec);
		methodBuilder.returns(Long.TYPE);
			
		SqlSelectBuilder.generateLogForMethodBeginning(method, methodBuilder);
				
		// just detect which columns are admitted
		JQLChecker.getInstance().replace(method.jql, new JQLReplacerListenerImpl() {
			
			@Override
			public String onColumnName(String columnName) {
				String convertedColumnName=entity.get(columnName).columnName;
				columns.add(convertedColumnName);
				return convertedColumnName;				
			}
			
			@Override
			public String onTableName(String tableName) {
				return daoDefinition.getParent().getEntityBySimpleName(tableName).getTableName();
			}
								
		});
		
		
		generateColumnCheckSet(elementUtils, builder, method,columns);
		
		// extract pathVariables
		// generate get uri variables in content values
		// every controls was done in constructor of SQLiteModelMethod
		for (ContentUriPlaceHolder variable: method.contentProviderUriVariables) {
			SQLProperty entityProperty = entity.get(variable.value);
			
			if (entityProperty!=null) {
				TypeName entityPropertyType=entityProperty.getPropertyType().getTypeName();
				if (TypeUtility.isString(entityPropertyType)) {
					methodBuilder.addStatement("contentValues.put($S, uri.getPathSegments().get($L))", entityProperty.columnName, variable.pathSegmentIndex);									
				} else {
					methodBuilder.addStatement("contentValues.put($S, Long.valueOf(uri.getPathSegments().get($L)))", entityProperty.columnName, variable.pathSegmentIndex);					
				}													
			} 
		}		
		
		methodBuilder.addStatement("$T _columnNameBuffer=new $T()", StringBuffer.class, StringBuffer.class);
		methodBuilder.addStatement("$T _columnValueBuffer=new $T()", StringBuffer.class, StringBuffer.class);
		methodBuilder.addStatement("String _columnSeparator=$S", "");	
		
		generateColumnCheck(method, methodBuilder, "contentValues.keySet()", new OnColumnListener() {
			
			@Override
			public void onColumnCheck(MethodSpec.Builder methodBuilder, String columNameVariable) {
				methodBuilder.addStatement("_columnNameBuffer.append(_columnSeparator+$L)", columNameVariable);
				methodBuilder.addStatement("_columnValueBuffer.append(_columnSeparator+$S+$L)",":",columNameVariable);
				methodBuilder.addStatement("_columnSeparator=$S",", ");	
				
			}
		});
						
		JQLChecker checker=JQLChecker.getInstance();
		String sql=checker.replaceVariableStatements(method.jql.value, new JQLReplaceVariableStatementListenerImpl() {
			@Override
			public String onColumnNameSet(String statement) {
				return "%s";
			}
			
			@Override
			public String onColumnValueSet(String statement) {
				return "%s";
			}
		});	
		
		if (daoDefinition.getParent().generateLog) {			
			methodBuilder.addStatement("$T.info($T.formatSQL($S, _columnNameBuffer.toString(), _columnValueBuffer.toString()))", Logger.class, SqlUtils.class, sql);
		}
		
		SqlModifyBuilder.generateLogForContentValues(method, methodBuilder);
		
		ConflictAlgorithmType conflictAlgorithmType = InsertBeanHelper.getConflictAlgorithmType(method);
		String conflictString1 = "";
		String conflictString2 = "";		
		if (conflictAlgorithmType != ConflictAlgorithmType.NONE) {
			conflictString1 = "WithOnConflict";
			conflictString2 = ", SQLiteDatabase." + conflictAlgorithmType;			
		}
		methodBuilder.addStatement("long result = database().insert$L($S, null, contentValues$L)", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
		methodBuilder.addStatement("return result");
		
		// javadoc
		// we add at last javadoc, because need info is built at last.
		SqlModifyBuilder.generateJavaDoc(method, methodBuilder);

		methodBuilder.addJavadoc("@param uri $S\n", method.contentProviderUriTemplate.replace("*", "[*]"));
		methodBuilder.addJavadoc("@param contentValues content values\n");
		
		methodBuilder.addJavadoc("@return new row's id\n");


		builder.addMethod(methodBuilder.build());
	}

	/**
	 * <p>Generate column check</p>
	 * 
	 * @param method
	 * @param daoDefinition
	 * @param operationType
	 */
	static void generateColumnCheck(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder, String columnSetString, OnColumnListener listener) {
		SQLDaoDefinition daoDefinition=method.getParent();
		methodBuilder.beginControlFlow("for (String columnName:$L)", columnSetString);
			methodBuilder.beginControlFlow("if (!$L.contains(columnName))", method.contentProviderMethodName+"ColumnSet");
				methodBuilder.addStatement("throw new $T(String.format(\"For URI '$L', column '%s' does not exists in table '%s' or can not be defined in this $L operation\", columnName, $S ))", KriptonRuntimeException.class, method.contentProviderUriTemplate,  method.jql.operationType ,daoDefinition.getEntity().getTableName());
			methodBuilder.endControlFlow();
			if (listener!=null)
				listener.onColumnCheck(methodBuilder, "columnName");
		methodBuilder.endControlFlow();
	}
	

	/**
	 * <p></p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param columnNames
	 * 
	 * @return
	 * 		name of column name set
	 */
	static String generateColumnCheckSet(Elements elementUtils, Builder builder, SQLiteModelMethod method, Set<String> columnNames) {
		String columnNameSet=method.contentProviderMethodName+"ColumnSet";
		StringBuilder initBuilder=new StringBuilder();
		String temp="";
		
		for (String item: columnNames)
		{
			initBuilder.append(temp+"\""+item+"\"");
			temp=", ";
		}
		
		FieldSpec.Builder fieldBuilder=FieldSpec.builder(ParameterizedTypeName.get(Set.class, String.class), columnNameSet, Modifier.STATIC, Modifier.PRIVATE, Modifier.FINAL);				
		fieldBuilder.initializer("$T.asSet($T.class, $L)",CollectionUtils.class, String.class,initBuilder.toString());		

		builder.addField(fieldBuilder.build());
		
		return columnNameSet;
	}
			
			
}

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

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.Converter;
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
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLReplacerListener;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

import android.content.ContentValues;

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
	private static void generateInsertForContentProvider(Elements elementUtils, Builder builder, SQLiteModelMethod method, InsertType insertResultType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final Converter<String, String> columnNameConverter=daoDefinition.getColumnNameConverter();
		final Converter<String, String> tableNameConverter = daoDefinition.getClassNameConverter();
		final SQLEntity entity = daoDefinition.getEntity();
		final Set<String> columns=new LinkedHashSet<>();
		final StringBuilder parametersBuilder=new StringBuilder();
		
		String resultA = JQLChecker.getInstance().replace(method.jql, new JQLReplacerListener() {
			
			@Override
			public String onColumnName(String columnName) {
				String convertedColumnName=columnNameConverter.convert(columnName);
				columns.add(convertedColumnName);
				return convertedColumnName;				
			}
			
			@Override
			public String onColumnValue(String columnValue) {
				JQLParameterName parameterName=JQLParameterName.parse(columnValue);				
				
				String limit="";
				SQLProperty property = daoDefinition.getEntity().get(parameterName.getValue());
				SQLColumnType columnType = SQLTransformer.columnType(property);				
				switch(columnType) {
				case BLOB:
				case TEXT:
					limit="'";
				case INTEGER:
				case REAL:
				default:
					;
				}
				
				parametersBuilder.append(", StringUtils.formatParam(contentValues.get(\""+columnNameConverter.convert(parameterName.getValue())+"\"),\""+limit+"\")");
				
				return "%s";
			}

			@Override
			public String onTableName(String tableName) {
				return tableNameConverter.convert(tableName);
			}
			
			
		});
		
		
		generateInsertCheckForContentProvider(elementUtils, builder, method,columns);
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);
		ParameterSpec parameterSpec;

		parameterSpec = ParameterSpec.builder(ContentValues.class, "contentValues").build();
		methodBuilder.addParameter(parameterSpec);
		methodBuilder.returns(Long.TYPE);

		methodBuilder.addCode("// $L\n", method.jql.value);
		
		methodBuilder.beginControlFlow("for (String columnName:contentValues.keySet())");
			methodBuilder.beginControlFlow("if (!$L.contains(columnName))", method.contentProviderMethodName+"ColumnSet");
				methodBuilder.addStatement("throw new $T(String.format(\"Column '%s' does not exists in table '%s'\", columnName, $S ))", KriptonRuntimeException.class, daoDefinition.getEntity().getTableName());
			methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
				
		methodBuilder.addCode("// $L\n", resultA);		
		methodBuilder.addCode("//$T and $T will be used to format SQL\n", SqlUtils.class, StringUtils.class);
		
		if (daoDefinition.getParent().generateLog) {			
			methodBuilder.addStatement("$T.info($T.formatSQL($S$L))", Logger.class, SqlUtils.class, resultA, parametersBuilder.toString());
		}
		
		ConflictAlgorithmType conflictAlgorithmType = InsertBeanHelper.getConflictAlgorithmType(method);
		String conflictString1 = "";
		String conflictString2 = "";		
		if (conflictAlgorithmType != ConflictAlgorithmType.NONE) {
			conflictString1 = "WithOnConflict";
			conflictString2 = ", SQLiteDatabase." + conflictAlgorithmType;			
		}
		methodBuilder.addStatement("long result = database().insert$L($S, null, contentValues$L)", conflictString1, daoDefinition.getEntity().getTableName(), conflictString2);
		methodBuilder.addStatement("return result");

		builder.addMethod(methodBuilder.build());
	}
	

	private static void generateInsertCheckForContentProvider(Elements elementUtils, Builder builder, SQLiteModelMethod method, Set<String> columnNames) {
		StringBuilder initBuilder=new StringBuilder();
		String temp="";
		
		for (String item: columnNames)
		{
			initBuilder.append(temp+"\""+item+"\"");
			temp=", ";
		}
		
		FieldSpec.Builder fieldBuilder=FieldSpec.builder(ParameterizedTypeName.get(Set.class, String.class), method.contentProviderMethodName+"ColumnSet", Modifier.STATIC, Modifier.PRIVATE, Modifier.FINAL);
		
		
		fieldBuilder.initializer("$T.asSet($T.class, $L)",CollectionUtils.class, String.class,initBuilder.toString());		

		builder.addField(fieldBuilder.build());

	}
			
			
}

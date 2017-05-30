/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
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
import com.abubusoft.kripton.processor.sqlite.SqlInsertBuilder.InsertType;
import com.abubusoft.kripton.processor.sqlite.grammar.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammar.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.grammar.JQLChecker.JSQLReplacerListener;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ArrayTypeName;
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
 *
 * @since 05/mag/2016
 */
public abstract class SqlModifyBuilder {

	public enum ModifyType {
		UPDATE_BEAN(ModifyBeanHelper.class, true), UPDATE_RAW(ModifyRawHelper.class, true), DELETE_BEAN(ModifyBeanHelper.class, false), DELETE_RAW(ModifyRawHelper.class, false);

		private ModifyCodeGenerator codeGenerator;

		private boolean update;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isUpdate() {
			return update;
		}

		private ModifyType(Class<? extends ModifyCodeGenerator> codeGenerator, boolean updateValue) {
			try {
				this.update = updateValue;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}

		public void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType) {
			codeGenerator.generate(elementUtils, methodBuilder, isUpdate(), method, returnType);

		}
	}

	public interface ModifyCodeGenerator {
		void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param updateMode
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteModelMethod method, boolean updateMode) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		ModifyType updateResultType = null;

		// check type of arguments
		int count = 0;
		for (Pair<String, TypeName> param : method.getParameters()) {
			if (method.hasDynamicWhereConditions() && param.value0.equals(method.dynamicWhereParameterName)) {
				// if current parameter is dynamic where, skip it
				continue;
			}

			if (TypeUtility.isEquals(param.value1, typeName(entity.getElement()))) {
				count++;
			}
		}

		if (count == 0) {
			// method to update raw data: no bean is used
			updateResultType = updateMode ? ModifyType.UPDATE_RAW : ModifyType.DELETE_RAW;

			ModelAnnotation annotation;

			if (updateMode) {
				annotation = method.getAnnotation(BindSqlUpdate.class);

				AssertKripton.assertTrueOrInvalidMethodSignException(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.VALUE).size() == 0, method,
						" can not use attribute %s in this kind of query definition", AnnotationAttributeType.VALUE.getValue());
				AssertKripton.assertTrueOrInvalidMethodSignException(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.EXCLUDED_FIELDS).size() == 0,
						method, " can not use attribute %s in this kind of query definition", AnnotationAttributeType.EXCLUDED_FIELDS.getValue());

			} else {
				annotation = method.getAnnotation(BindSqlDelete.class);
			}

			// check if there is only one parameter
			AssertKripton.failWithInvalidMethodSignException(
					method.getParameters().size() > 1 && TypeUtility.isEquals(method.getParameters().get(0).value1, daoDefinition.getEntityClassName()), method);

		} else if (count == 1) {
			updateResultType = updateMode ? ModifyType.UPDATE_BEAN : ModifyType.DELETE_BEAN;

			// with dynamic where conditions, we have to add 1 to parameter
			// check size (one parameter is a bean and one is the where
			// conditions)
			AssertKripton.assertTrueOrInvalidMethodSignException(method.getParameters().size() == 1 + (method.hasDynamicWhereConditions() ? 1 : 0), method, " expected only one parameter of %s type",
					daoDefinition.getEntityClassName());
		} else {
			throw (new InvalidMethodSignException(method, "only one parameter of type " + typeName(entity.getElement()) + " can be used"));
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();

		if (updateResultType == null) {
			throw (new InvalidMethodSignException(method));
		}

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// generate inner code
		updateResultType.generate(elementUtils, daoDefinition, entity, methodBuilder, method, returnType);

		MethodSpec methodSpec = methodBuilder.build();
		builder.addMethod(methodSpec);
		
		if (method.contentProviderEntryPathEnabled) {
			// we need to generate insert for content provider to			
			generateModifierForContentProvider(elementUtils, builder, method, updateResultType);
		}

	}
	
	/**
	 * <p>Generate update and delete used in content provider class.</p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param updateResultType
	 */
	private static void generateModifierForContentProvider(Elements elementUtils, Builder builder, SQLiteModelMethod method, ModifyType updateResultType) {
		final SQLDaoDefinition daoDefinition = method.getParent();
		final Converter<String, String> columnNameConverter=daoDefinition.getColumnNameConverter();
		final Converter<String, String> tableNameConverter = daoDefinition.getClassNameConverter();
		final SQLEntity entity = daoDefinition.getEntity();
		final Set<String> columns=new LinkedHashSet<>();
		final StringBuilder parametersBuilder=new StringBuilder();
		
		String resultA="";
		
		resultA = JQLChecker.getInstance().replace(method.jql, new JSQLReplacerListener() {
			
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
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);				
		methodBuilder.addParameter(ParameterSpec.builder(Uri.class, "uri").build());
		methodBuilder.addParameter(ParameterSpec.builder(String.class, "selection").build());
		methodBuilder.addParameter(ParameterSpec.builder(ArrayTypeName.get(String.class), "selectionArgs").build());		
		methodBuilder.returns(Long.TYPE);

		methodBuilder.addCode("// $L\n", method.jql.value);
				
		methodBuilder.addCode("// $L\n", resultA);		
		methodBuilder.addCode("//$T and $T will be used to format SQL\n", SqlUtils.class, StringUtils.class);
		
//		if (daoDefinition.getParent().generateLog) {			
//			methodBuilder.addStatement("$T.info($T.formatSQL($S$L))", Logger.class, SqlUtils.class, resultA, parametersBuilder.toString());
//		}
				
		switch (updateResultType)
		{
		case DELETE_BEAN:
		case DELETE_RAW:			
			methodBuilder.addStatement("long result = database().delete($S, null, null)", daoDefinition.getEntity().getTableName());
			break;
		case UPDATE_BEAN:
		case UPDATE_RAW:
			generateModifierCheckForContentProvider(elementUtils, builder, method,columns);
			methodBuilder.beginControlFlow("for (String columnName:contentValues.keySet())");
				methodBuilder.beginControlFlow("if (!$L.contains(columnName))", method.contentProviderMethodName+"ColumnSet");
					methodBuilder.addStatement("throw new $T(String.format(\"Column '%s' does not exists in table '%s'\", columnName, $S ))", KriptonRuntimeException.class, daoDefinition.getEntity().getTableName());
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("long result = database().update($S, null, null)", daoDefinition.getEntity().getTableName());
			break;
		}
		
		methodBuilder.addStatement("return result");

		builder.addMethod(methodBuilder.build());
	}
	

	private static void generateModifierCheckForContentProvider(Elements elementUtils, Builder builder, SQLiteModelMethod method, Set<String> columnNames) {
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

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

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.GenericSQLHelper.SubjectType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.content.ContentValues;
import android.net.Uri;

/**
 * The Class SqlInsertBuilder.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @since 05/mag/2016
 */
public abstract class SqlInsertBuilder {

	/**
	 * The Enum InsertType.
	 */
	public enum InsertType {

		/** The insert bean. */
		INSERT_BEAN(InsertBeanHelper.class, true),

		/** The insert list bean. */
		INSERT_LIST_BEAN(InsertListBeanHelper.class, true),

		/** The insert raw. */
		INSERT_RAW(InsertRawHelper.class, false);

		/** The code generator. */
		private InsertCodeGenerator codeGenerator;

		/** The map fields. */
		private boolean mapFields;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}

		/**
		 * Instantiates a new insert type.
		 *
		 * @param codeGenerator
		 *            the code generator
		 * @param mapFields
		 *            the map fields
		 */
		private InsertType(Class<? extends InsertCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}

		/**
		 * Generate.
		 *
		 * @param classBuilder
		 *            the class builder
		 * @param methodBuilder
		 *            the method builder
		 * @param method
		 *            the method
		 * @param returnType
		 *            the return type
		 */
		public void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
				TypeName returnType) {
			codeGenerator.generate(classBuilder, methodBuilder, this.isMapFields(), method, returnType);

		}
	}

	/**
	 * The Interface InsertCodeGenerator.
	 */
	public interface InsertCodeGenerator {

		/**
		 * Generate.
		 *
		 * @param classBuilder
		 *            the class builder
		 * @param methodBuilder
		 *            the method builder
		 * @param mapFields
		 *            the map fields
		 * @param method
		 *            the method
		 * @param returnType
		 *            the return type
		 */
		void generate(TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, boolean mapFields,
				SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * Generate.
	 *
	 * @param classBuilder
	 *            the class builder
	 * @param method
	 *            the method
	 */
	public static void generate(TypeSpec.Builder classBuilder, SQLiteModelMethod method) {
		InsertType insertResultType = detectInsertType(method);

		// if true, field must be associate to ben attributes
		TypeName returnType = method.getReturnClass();

		AssertKripton.failWithInvalidMethodSignException(insertResultType == null, method);

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC);

		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// fail if we use jql to INSERT_BEAN with operation of INSERT-FOR-SELECT
		// AssertKripton.failWithInvalidMethodSignException(insertResultType ==
		// InsertType.INSERT_BEAN && method.jql.containsSelectOperation, method,
		// "INSERT-FROM-SELECT SQL can not be used with method sign");

		// generate inner code
		methodBuilder.addComment("Specialized Insert - $L - BEGIN", insertResultType.getClass().getSimpleName());
		insertResultType.generate(classBuilder, methodBuilder, method, returnType);
		methodBuilder.addComment("Specialized Insert - $L - END", insertResultType.getClass().getSimpleName());

		MethodSpec methodSpec = methodBuilder.build();
		classBuilder.addMethod(methodSpec);

		if (method.contentProviderEntryPathEnabled) {
			// we need to generate insert for content provider to
			generateInsertForContentProvider(classBuilder, method, insertResultType);
		}

	}

	/**
	 * Detect insert type.
	 *
	 * @param method
	 *            the method
	 * @return the insert type
	 */
	public static InsertType detectInsertType(SQLiteModelMethod method) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		SQLiteEntity entity = method.getEntity();
		TypeName beanTypeName = typeName(entity.getElement());

		InsertType insertResultType = null;

		// check type of arguments
		int beanCounter = 0;
		int beanCollectionCounter = 0;
		for (Pair<String, TypeName> param : method.getParameters()) {
			if (TypeUtility.isEquals(param.value1, beanTypeName)) {
				beanCounter++;
			} else if (TypeUtility.isCollectionOfType(param.value1, beanTypeName)) {
				beanCollectionCounter++;
			}
		}

		AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() == 0, method,
				" INSERT operations require at least one parameter");

		if (beanCounter == 0 && beanCollectionCounter == 0) {
			// method to insert raw data: no bean is used
			insertResultType = InsertType.INSERT_RAW;

			ModelAnnotation annotation = method.getAnnotation(BindSqlInsert.class);

			// check value attribute
			AssertKripton.failWithInvalidMethodSignException(
					AnnotationUtility.extractAsStringArray(method, annotation, AnnotationAttributeType.FIELDS)
							.size() > 0,
					method, " can not use attribute %s in this kind of query definition",
					AnnotationAttributeType.FIELDS.getValue());

			// check excludeFields attribute
			AssertKripton.failWithInvalidMethodSignException(
					AnnotationUtility.extractAsStringArray(method, annotation, AnnotationAttributeType.EXCLUDED_FIELDS)
							.size() > 0,
					method, " can not use attribute %s in this kind of query definition",
					AnnotationAttributeType.EXCLUDED_FIELDS.getValue());

			// check if there is only one parameter
			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() != 1
					&& TypeUtility.isEquals(method.getParameters().get(0).value1, daoDefinition.getEntityClassName()),
					method);

			// check no
			AssertKripton.failWithInvalidMethodSignException(
					annotation.getAttributeAsBoolean(AnnotationAttributeType.INCLUDE_PRIMARY_KEY), method,
					"attribute '%s' can not be used here", AnnotationAttributeType.INCLUDE_PRIMARY_KEY.getValue());

		} else if (beanCounter == 1) {
			insertResultType = InsertType.INSERT_BEAN;

			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() > 1, method,
					" aspected only one parameter of %s type", daoDefinition.getEntityClassName());
		} else if (beanCollectionCounter == 1) {
			insertResultType = InsertType.INSERT_LIST_BEAN;
			AssertKripton.failWithInvalidMethodSignException(method.getParameters().size() > 1, method,
					" aspected only one collection of %s type as parameter", daoDefinition.getEntityClassName());

			AssertKripton.assertTrueOrInvalidMethodSignException(
					TypeUtility.isCollectionOfType(method.getReturnClass(), beanTypeName)
							|| TypeUtility.isEquals(method.getReturnClass(), typeName(Void.TYPE)),
					method, " invalid return type for multiple insert method", daoDefinition.getEntityClassName());
		} else {
			throw (new InvalidMethodSignException(method,
					"only one parameter of type " + typeName(entity.getElement()) + " can be used"));
		}

		return insertResultType;
	}

	/**
	 * <p>
	 * Generate insert used in content provider class.
	 * </p>
	 *
	 * @param classBuilder
	 *            the class builder
	 * @param method
	 *            the method
	 * @param insertResultType
	 *            the insert result type
	 */
	private static void generateInsertForContentProvider(TypeSpec.Builder classBuilder, final SQLiteModelMethod method,
			InsertType insertResultType) {
		final SQLiteDaoDefinition daoDefinition = method.getParent();
		final SQLiteEntity entity = method.getEntity();
		final Set<String> columns = new LinkedHashSet<>();

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.contentProviderMethodName);
		if (!method.getParent().hasSamePackageOfSchema()) {
			methodBuilder.addModifiers(Modifier.PUBLIC);
		}

		ParameterSpec parameterSpec;
		parameterSpec = ParameterSpec.builder(Uri.class, "uri").build();
		methodBuilder.addParameter(parameterSpec);
		parameterSpec = ParameterSpec.builder(ContentValues.class, "contentValues").build();
		methodBuilder.addParameter(parameterSpec);
		methodBuilder.returns(Long.TYPE);

		SqlBuilderHelper.generateLogForContentProviderBeginning(method, methodBuilder);

		// just detect which columns are admitted
		JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {

			@Override
			public String onColumnName(String columnName) {
				SQLProperty tempProperty = entity.get(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				columns.add(tempProperty.columnName);

				return tempProperty.columnName;

			}

			@Override
			public String onColumnFullyQualifiedName(String tableName, String columnName) {
				AssertKripton.fail("Inconsistent state");
				return null;
			}

		});

		// generate columnCheckSet
		SqlBuilderHelper.generateColumnCheckSet(classBuilder, method, columns);

		// retrieve content values
		methodBuilder.addStatement("$T _contentValues=contentValuesForContentProvider(contentValues)",
				KriptonContentValues.class);

		// generate column check
		SqlBuilderHelper.forEachColumnInContentValue(methodBuilder, method, "_contentValues.values().keySet()", true,
				null);

		methodBuilder.addCode("\n");

		String rxIdGetter = "result";

		// extract pathVariables
		// generate get uri variables in content values
		// every controls was done in constructor of SQLiteModelMethod
		for (ContentUriPlaceHolder variable : method.contentProviderUriVariables) {
			SQLProperty entityProperty = entity.get(variable.value);

			if (entityProperty != null) {
				methodBuilder.addCode("// Add parameter $L at path segment $L\n", variable.value,
						variable.pathSegmentIndex);
				TypeName entityPropertyType = entityProperty.getPropertyType().getTypeName();
				if (TypeUtility.isString(entityPropertyType)) {
					methodBuilder.addStatement("contentValues.put($S, uri.getPathSegments().get($L))",
							entityProperty.columnName, variable.pathSegmentIndex);
				} else {
					methodBuilder.addStatement("contentValues.put($S, Long.valueOf(uri.getPathSegments().get($L)))",
							entityProperty.columnName, variable.pathSegmentIndex);
				}
			}

			if (entityProperty.isPrimaryKey()) {
				rxIdGetter = PropertyUtility.getter(entityProperty);
			}
		}

		// generate log for inser operation
		SqlBuilderHelper.generateLogForContentValuesContentProvider(method, methodBuilder);

		ConflictAlgorithmType conflictAlgorithmType = InsertBeanHelper.getConflictAlgorithmType(method);
		String conflictString2 = "";
		// we need to use everytime after support
		//if (conflictAlgorithmType != ConflictAlgorithmType.NONE) {
			conflictString2 = ", " + conflictAlgorithmType.getConflictAlgorithm();
			methodBuilder.addCode("// conflict algorithm $L\n", method.jql.conflictAlgorithmType);
		//}

		methodBuilder.addComment("insert operation");
		methodBuilder.addStatement("long result = database().insert($S$L, _contentValues.values())",
				entity.getTableName(), conflictString2);
		if (method.getParent().getParent().generateRx) {
			SQLProperty primaryKey = entity.getPrimaryKey();
			if (primaryKey.columnType == ColumnType.PRIMARY_KEY) {
				// long autogenerated
				rxIdGetter = "result";
			} else {

				if (primaryKey.isType(String.class)) {
					rxIdGetter = String.format("contentValues.getAsString(\"%s\")", primaryKey.columnName);
				} else {
					rxIdGetter = String.format("contentValues.getAsLong(\"%s\")", primaryKey.columnName);
				}
			}

			GenericSQLHelper.generateSubjectNext(entity, methodBuilder, SubjectType.INSERT, rxIdGetter);
		}

		// support for livedata
		if (daoDefinition.hasLiveData()) {
			methodBuilder.addComment("support for livedata");
			methodBuilder.addStatement(BindDaoBuilder.METHOD_NAME_REGISTRY_EVENT + "(result>0?1:0)");
		}

		methodBuilder.addStatement("return result");

		// javadoc
		// we add at last javadoc, because need info is built at last.
		SqlBuilderHelper.generateJavaDocForContentProvider(method, methodBuilder);

		methodBuilder.addJavadoc("@param uri $S\n", method.contentProviderUriTemplate.replace("*", "[*]"));
		methodBuilder.addJavadoc("@param contentValues content values\n");

		methodBuilder.addJavadoc("@return new row's id\n");

		classBuilder.addMethod(methodBuilder.build());
	}

}

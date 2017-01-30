/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PagedResult;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectResultType;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SelectBuilderUtility {

	private static final Pattern pattern = Pattern.compile("\\((.*)\\)(.*)");

	public static Pair<String, String> extractResultAndArguments(String value) {
		Matcher matcher = pattern.matcher(value);

		Pair<String, String> result = new Pair<String, String>();
		if (matcher.matches()) {
			result.value0 = matcher.group(1);
			result.value1 = matcher.group(2);
		}
		return result;
	}

	/**
	 * Iterate over methods.
	 * 
	 * @param elementUtils
	 * @param typeElement
	 * @param listener
	 */
	public static void forEachMethods(Elements elementUtils, TypeElement typeElement, MethodFoundListener listener) {

		Map<String, TypeMirror> resolvedParameter = new HashMap<String, TypeMirror>();
		List<? extends TypeMirror> listInterface = typeElement.getInterfaces();
		for (TypeMirror item : listInterface) {
			// if (item instanceof Type$ClassType)
			{
				resolvedParameter.put("E", item);
			}
		}

		List<? extends Element> list = elementUtils.getAllMembers(typeElement);

		for (Element item : list) {
			if (item.getKind() == ElementKind.METHOD) {
				listener.onMethod((ExecutableElement) item);
			}
		}
	}

	public static boolean hasParameterOfType(ModelMethod method, String kindOfParameter) {
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			if (typeName(item.value1).toString().equals(kindOfParameter)) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasParameterOfType(ModelMethod method, LiteralType parameter) {
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			if (typeName(item.value1).toString().equals(parameter.getValue())) {
				return true;
			}
		}

		return false;
	}

	public static int countParameterOfType(ModelMethod method, LiteralType parameter) {
		int counter = 0;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			if (typeName(item.value1).toString().equals(parameter.getValue())) {
				counter++;
			}
		}

		return counter;
	}

	public static String getNameParameterOfType(ModelMethod method, LiteralType parameter) {
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			if (typeName(item.value1).toString().equals(parameter.getValue())) {
				return item.value0;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 */
	public static void generateSelect(Elements elementUtils, Builder builder, SQLiteModelMethod method) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		SqlSelectBuilder.SelectResultType selectResultType = null;

		// if true, field must be associate to ben attributes
		TypeMirror returnType = method.getReturnClass();
		TypeName returnTypeName = typeName(returnType);

		LiteralType readBeanListener = LiteralType.of(OnReadBeanListener.class.getCanonicalName(), entity.getName());
		LiteralType readCursorListener = LiteralType.of(OnReadCursorListener.class);

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);

		if (TypeUtility.isTypeIncludedIn(returnTypeName, Void.class, Void.TYPE)) {
			// return VOID (in the parameters must be a listener)
			if (hasParameterOfType(method, readCursorListener)) {
				selectResultType = SqlSelectBuilder.SelectResultType.LISTENER_CURSOR;
			} else if (hasParameterOfType(method, readBeanListener)) {
				selectResultType = SqlSelectBuilder.SelectResultType.LISTENER_BEAN;
			}
		} else if (TypeUtility.isTypeIncludedIn(returnTypeName, Cursor.class)) {
			// return Cursor (no listener)
			selectResultType = SqlSelectBuilder.SelectResultType.CURSOR;
		} else if (returnTypeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) returnTypeName;
			ClassName returnParameterizedClassName = returnParameterizedTypeName.rawType;

			// return List (no listener)
			AssertKripton.assertTrueOrInvalidMethodSignException(returnParameterizedTypeName.typeArguments.size() == 1, method, "return type %s is not supported", returnTypeName);
			TypeName elementName = returnParameterizedTypeName.typeArguments.get(0);

			try {
				Class<?> wrapperClazz = Class.forName(returnParameterizedClassName.toString());
				if (PagedResult.class.isAssignableFrom(wrapperClazz)) {
					// method must have pageSize, statically or dynamically
					// defined
					AssertKripton.assertTrueOrInvalidMethodSignException(method.hasDynamicPageSizeConditions() || pageSize > 0, method,
							"use of PagedResult require 'pageSize' attribute or a @BindSqlPageSize annotated parameter", returnTypeName);

					// paged result
					AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isSameType(elementName, entity.getName().toString()), method, "return type %s is not supported", returnTypeName);
					selectResultType = SqlSelectBuilder.SelectResultType.PAGED_RESULT;

					// create PagedResult
					buildPagedResultClass(builder, method);

				} else if (Collection.class.isAssignableFrom(wrapperClazz)) {
					if (TypeUtility.isSameType(elementName, entity.getName().toString())) {
						// entity list
						selectResultType = SqlSelectBuilder.SelectResultType.LIST_BEAN;
					} else if (TypeUtility.isByteArray(elementName) || TypeUtility.isTypePrimitive(elementName) || TypeUtility.isTypeWrappedPrimitive(elementName)
							|| TypeUtility.isTypeIncludedIn(elementName, String.class)) {
						// scalar list
						selectResultType = SqlSelectBuilder.SelectResultType.LIST_SCALAR;
					} else {
						AssertKripton.failWithInvalidMethodSignException(true, method, "");
					}

				}
			} catch (Exception e) {
				// error
			}
		} else if (TypeUtility.isEquals(returnTypeName, entity)) {
			// return one element (no listener)
			selectResultType = SqlSelectBuilder.SelectResultType.BEAN;
		} else if (TypeUtility.isTypePrimitive(returnTypeName) || TypeUtility.isTypeWrappedPrimitive(returnTypeName) || TypeUtility.isTypeIncludedIn(returnTypeName, String.class)
				|| TypeUtility.isByteArray(returnTypeName)) {
			// return single value string, int, long, short, double, float,
			// String (no listener)
			selectResultType = SqlSelectBuilder.SelectResultType.SCALAR;
		}

		if (selectResultType == null) {
			throw (new InvalidMethodSignException(method, String.format("'%s' as return type is not supported", returnTypeName)));
		}

		
		//
		//selectResultType.

		selectResultType.generate(elementUtils, builder, method, returnType);
	}

	static int pageResultCounter;

	private static void buildPagedResultClass(Builder builder, SQLiteModelMethod method) {
		/*
		 * builder =
		 * TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).
		 * addModifiers(Modifier.ABSTRACT)
		 * .addTypeVariable(TypeVariableName.get("I"))
		 * .addTypeVariable(TypeVariableName.get("U"))
		 * .addTypeVariable(TypeVariableName.get("R"));
		 */
		// TypeSpec
		// baseClazz=TypeSpec.classBuilder(PagedResult.class.getName()).addTypeVariable(TypeVariableName.get(method.getParent().getEntityClassName())).build();

		TypeVariableName.get(method.getParent().getEntityClassName());

		Builder typeBuilder = TypeSpec.classBuilder("PagedResult" + (pageResultCounter++)).addModifiers(Modifier.PUBLIC)
				.superclass(TypeUtility.parameterizedTypeName(TypeUtility.className(PagedResult.class), TypeUtility.typeName(method.getParent().getEntityClassName())));

		// add fields and define constructor
		MethodSpec.Builder setupBuilder = MethodSpec.methodBuilder("setup");

		MethodSpec.Builder executeBuilder = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).returns(TypeName.INT);
		executeBuilder.addCode("list=$T.this.$L(", TypeUtility.typeName(method.getParent().getElement(), BindDaoBuilder.SUFFIX), method.getName());

		String separator = "";
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			// field
			typeBuilder.addField(TypeName.get(item.value1), item.value0);

			// construtor
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			setupBuilder.addParameter(parameterSpec);
			setupBuilder.addStatement("this.$L=$L", item.value0, item.value0);

			// execute
			executeBuilder.addCode(separator + item.value0);
			separator = ", ";
		}
		typeBuilder.addMethod(setupBuilder.build());

		executeBuilder.addCode(");\n");
		executeBuilder.addStatement("return list.size()");
		typeBuilder.addMethod(executeBuilder.build());

		// generate class
		/*
		 * MethodSpec.Builder methodBuilder = TypeSpec. m
		 * MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.
		 * class).addModifiers(Modifier.PUBLIC);
		 * 
		 * // generate javadoc
		 * JavadocUtility.generateJavaDocForSelect(methodBuilder,
		 * sqlWithParameters, paramNames, method, annotation, fieldList,
		 * selectResultType);
		 */
		/*
		 * // add parameter for method
		 * 
		 * 
		 * // add return type methodBuilder.returns(returnTypeName);
		 */

		builder.addType(typeBuilder.build());
	}

	private static void buildPagedResultThreadLocal(Builder builder, SQLiteModelMethod method) {

		TypeVariableName.get(method.getParent().getEntityClassName());

		Builder typeBuilder = TypeSpec.classBuilder("PagedResult" + (pageResultCounter++)).addModifiers(Modifier.PUBLIC)
				.superclass(TypeUtility.parameterizedTypeName(TypeUtility.className(PagedResult.class), TypeUtility.typeName(method.getParent().getEntityClassName())));

		// add fields and define constructor
		MethodSpec.Builder setupBuilder = MethodSpec.methodBuilder("setup");

		MethodSpec.Builder executeBuilder = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).returns(TypeName.INT);
		executeBuilder.addCode("list=$T.this.$L(", TypeUtility.typeName(method.getParent().getElement(), BindDaoBuilder.SUFFIX), method.getName());

		String separator = "";
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			// field
			typeBuilder.addField(TypeName.get(item.value1), item.value0);

			// construtor
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			setupBuilder.addParameter(parameterSpec);
			setupBuilder.addStatement("this.$L=$L", item.value0, item.value0);

			// execute
			executeBuilder.addCode(separator + item.value0);
			separator = ", ";
		}
		typeBuilder.addMethod(setupBuilder.build());

		executeBuilder.addCode(");\n");
		executeBuilder.addStatement("return list.size()");
		typeBuilder.addMethod(executeBuilder.build());

		// generate class
		/*
		 * MethodSpec.Builder methodBuilder = TypeSpec. m
		 * MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.
		 * class).addModifiers(Modifier.PUBLIC);
		 * 
		 * // generate javadoc
		 * JavadocUtility.generateJavaDocForSelect(methodBuilder,
		 * sqlWithParameters, paramNames, method, annotation, fieldList,
		 * selectResultType);
		 */
		/*
		 * // add parameter for method
		 * 
		 * 
		 * // add return type methodBuilder.returns(returnTypeName);
		 */

		builder.addType(typeBuilder.build());
	}


}

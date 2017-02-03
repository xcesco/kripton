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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
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
import com.squareup.javapoet.TypeSpec.Builder;
import com.squareup.javapoet.TypeVariableName;

import android.database.Cursor;

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

		SqlSelectBuilder.SelectType selectResultType = null;

		// if true, field must be associate to ben attributes
		TypeMirror returnType = method.getReturnClass();
		TypeName returnTypeName = typeName(returnType);

		LiteralType readBeanListener = LiteralType.of(OnReadBeanListener.class.getCanonicalName(), entity.getName());
		LiteralType readCursorListener = LiteralType.of(OnReadCursorListener.class);

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);
		
		AssertKripton.failWithInvalidMethodSignException(pageSize<0, method, "in @%s(pageSize) must be set with positive number",BindSqlSelect.class.getSimpleName());
		AssertKripton.failWithInvalidMethodSignException(pageSize>0 && method.hasDynamicPageSizeConditions(), method, "can not define @%s(pageSize) and mark a method parameter with @%s ",BindSqlSelect.class.getSimpleName(), BindSqlPageSize.class.getSimpleName());		

		if (TypeUtility.isTypeIncludedIn(returnTypeName, Void.class, Void.TYPE)) {
			// return VOID (in the parameters must be a listener)
			if (hasParameterOfType(method, readCursorListener)) {
				selectResultType = SqlSelectBuilder.SelectType.LISTENER_CURSOR;
			} else if (hasParameterOfType(method, readBeanListener)) {
				selectResultType = SqlSelectBuilder.SelectType.LISTENER_BEAN;
			}
		} else if (TypeUtility.isTypeIncludedIn(returnTypeName, Cursor.class)) {
			// return Cursor (no listener)
			selectResultType = SqlSelectBuilder.SelectType.CURSOR;
		} else if (returnTypeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) returnTypeName;
			ClassName returnParameterizedClassName = returnParameterizedTypeName.rawType;

			// return List (no listener)
			AssertKripton.assertTrueOrInvalidMethodSignException(returnParameterizedTypeName.typeArguments.size() == 1, method, "return type %s is not supported", returnTypeName);
			TypeName elementName = returnParameterizedTypeName.typeArguments.get(0);

			try {
				Class<?> wrapperClazz = Class.forName(returnParameterizedClassName.toString());
				if (PaginatedResult.class.isAssignableFrom(wrapperClazz)) {
					// method must have pageSize, statically or dynamically
					// defined
					AssertKripton.assertTrueOrInvalidMethodSignException(method.hasDynamicPageSizeConditions() || pageSize > 0, method,
							"use of PaginatedResult require 'pageSize' attribute or a @%s annotated parameter", returnTypeName, BindSqlPageSize.class.getSimpleName());

					// paged result
					AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isSameType(elementName, entity.getName().toString()), method, "return type %s is not supported", returnTypeName);
					selectResultType = SqlSelectBuilder.SelectType.PAGED_RESULT;
					// set name of paginatedResult
					method.paginatedResultName="paginatedResult";
				} else if (Collection.class.isAssignableFrom(wrapperClazz)) {
					if (TypeUtility.isSameType(elementName, entity.getName().toString())) {
						// entity list
						selectResultType = SqlSelectBuilder.SelectType.LIST_BEAN;
					} else if (SQLTransformer.isSupportedJDKType(elementName) || TypeUtility.isByteArray(elementName)) {
						// scalar list
						selectResultType = SqlSelectBuilder.SelectType.LIST_SCALAR;
					} else {
						AssertKripton.failWithInvalidMethodSignException(true, method, "");
					}

				}
			} catch (Exception e) {
				// error
			}
		} else if (TypeUtility.isEquals(returnTypeName, entity)) {
			// return one element (no listener)
			selectResultType = SqlSelectBuilder.SelectType.BEAN;
		} else if (SQLTransformer.isSupportedJDKType(returnTypeName) || TypeUtility.isByteArray(returnTypeName)) {
			// return single value string, int, long, short, double, float,
			// String (no listener)
			selectResultType = SqlSelectBuilder.SelectType.SCALAR;
		}

		AssertKripton.assertTrueOrInvalidMethodSignException(selectResultType != null, method,"'%s' as return type is not supported", returnTypeName);

		// generate select method
		selectResultType.generate(elementUtils, builder, method, returnType);
	}





}

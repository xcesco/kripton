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
package com.abubusoft.kripton.processor.core.reflect;

import java.lang.reflect.Type;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class TypeUtility {
	public static boolean isTypeIncludedIn(TypeName value, Type... types) {
		for (Type item : types) {
			if (value.equals(typeName(item))) {
				return true;
			}
		}

		return false;
	}

	public static boolean isTypePrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.TYPE, Boolean.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE);
	}

	public static boolean isTypeWrappedPrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.class, Boolean.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
	}

	public static boolean isSameType(TypeName value, String className) {
		return value.toString().equals(className);
	}

	/**
	 * Check if its type is byte[]
	 * 
	 * @param value
	 * @return true if it is byte[]
	 */
	public static boolean isByteArray(TypeName value) {
		return value.toString().equals(Byte.TYPE.getCanonicalName() + "[]");
	}

	/**
	 * Check if its type is an array of type specified
	 * 
	 * @param value
	 * @return true if it is array []
	 */
	public static boolean isArray(TypeName arrayType, TypeName value) {
		return value.toString().equals(arrayType + "[]");
	}

	public static boolean isArrayOfPrimitive(TypeName typeName) {
		return isArray(TypeName.BOOLEAN, typeName) || isArray(TypeName.BYTE, typeName) || isArray(TypeName.CHAR, typeName) || isArray(TypeName.DOUBLE, typeName) || isArray(TypeName.FLOAT, typeName) || isArray(TypeName.INT, typeName)
				|| isArray(TypeName.LONG, typeName) || isArray(TypeName.SHORT, typeName);
	}

	/**
	 * Check if its type is String
	 * 
	 * @param value
	 * @return true if it is String
	 */
	public static boolean isString(TypeName value) {
		return value.toString().equals(String.class.getCanonicalName());
	}

	public static boolean isNullable(TypeName value) {
		return !isTypePrimitive(value);
	}

	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param entity
	 * @return true if value is equals to className.
	 */
	public static boolean isEquals(TypeName value, ModelClass<?> entity) {
		return isSameType(value, entity.getName());
	}

	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param rawType
	 * @param generic
	 * @return true if value is equals to className.
	 */
	public static boolean isEqualsToGeneric(TypeName value, TypeName rawType, TypeName generic) {
		return isSameType(value, rawType + "<" + generic.toString() + ">");
	}

	// com.abubusoft.kripton.android.sqlite.ReadBeanListener<com.abubusoft.kripton.processor.test03.Bean01>

	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param kindOfParameter
	 * @return true if value is equals to className.
	 */
	public static boolean isEquals(TypeName value, TypeName kindOfParameter) {
		return value.toString().equals(kindOfParameter.toString());
	}

	/**
	 * Generate class name
	 * 
	 * @param packageName
	 * @param className
	 * @return class name generated
	 */
	public static ClassName className(String packageName, String className) {
		return ClassName.get(packageName, className);
	}

	/**
	 * Generate class name
	 * 
	 * @param className
	 * @return class name generated
	 */
	public static ClassName className(String className) {
		return ClassName.get("", className);
	}

	/**
	 * Convert a class in a classname
	 * 
	 * @param clazz
	 * @return typeName
	 */
	public static ClassName className(Class<?> clazz) {
		return ClassName.get(clazz);
	}

	/**
	 * Convert a type in a typeName
	 * 
	 * @param type
	 * @return typeName
	 */
	public static TypeName typeName(Type type) {
		return TypeName.get(type);
	}

	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param typeMirror
	 * @return typeName
	 */
	public static TypeName typeName(TypeMirror typeMirror) {
		return TypeName.get(typeMirror);
	}

	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param typeName
	 * @return typeName
	 */
	public static TypeName typeName(String typeName) {
		return className(typeName);
	}

	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param element
	 * @return typeName
	 */
	public static TypeName typeName(Element element) {
		return TypeName.get(element.asType());
	}

	public static boolean isEnumType(Element element) {
		return element.getKind() == ElementKind.ENUM;
	}

	public static boolean isTypeIncludedIn(String clazzName, Class<?>... set) {
		String a;
		for (Class<?> item : set) {
			a = item.getCanonicalName();

			if (clazzName.equals(a))
				return true;
		}

		return false;

	}

	public static boolean isNullable(ModelProperty property) {
		return isNullable(property.getPropertyType().getName());
	}

	/**
	 * Check if method parameter is nullable. Moreover, check nullable status of method param and property are compatible.
	 * 
	 * @param method
	 * @param methodParam
	 * @param property
	 * @return true is method param is nullable
	 */
	public static boolean isNullable(SQLiteModelMethod method, Pair<String, TypeMirror> methodParam, ModelProperty property) {
		if (!isNullable(property) && isNullable(typeName(methodParam.value1))) {
			// ASSERT: property is not nullable but method yes, so we throw an
			// exception
			throw (new InvalidMethodSignException(method, String.format("property '%s' is NOT nullable but method parameter '%s' is nullable  ", property.getName(), methodParam.value0)));
		}
		return isNullable(typeName(methodParam.value1));
	}

	/**
	 * Check if type if compatibility
	 * 
	 * @param method
	 * @param item
	 * @param property
	 */
	public static void checkTypeCompatibility(SQLiteModelMethod method, Pair<String, TypeMirror> item, ModelProperty property) {
		if (!TypeUtility.isEquals(typeName(item.value1), property.getPropertyType().getName())) {
			// ASSERT: property is not nullable but method yes, so we throw an
			// exception
			throw (new InvalidMethodSignException(method, String.format("property '%s' is type '%s' and method parameter '%s' is type '%s'. They have to be same type  ", property.getName(), property.getPropertyType().getName(),
					item.value0, item.value1.toString())));
		}
	}

	/**
	 * generate begin string to translate in code to used in content value or parameter need to be converted in string through String.valueOf
	 * 
	 * @param property
	 * @return
	 * 
	 */
	public static String beginStringConversion(ModelProperty property) {
		TypeMirror modelType = property.getElement().asType();

		return beginStringConversion(modelType);
	}

	/**
	 * generate begin string to translate in code to used in content value or parameter need to be converted in string through String.valueOf
	 * 
	 * @param property
	 * @return
	 * 
	 */

	/**
	 * generate begin string to translate in code to used in String or parameter need to be converted in string through String.valueOf
	 * 
	 * @param typeMirror
	 * @return
	 * 
	 */
	public static String beginStringConversion(TypeMirror typeMirror) {
		TypeName typeName = typeName(typeMirror);
		if (typeName == null && typeMirror instanceof ModelType) {
			typeName = ((ModelType) typeMirror).getName();
		}

		if (isString(typeName)) {
			return "";
		} else if (isArray(typeName)) {
			// every array of primitive will be converted in byte[]
			return "new String(";
		}
		return "String.valueOf(";
	}

	/**
	 * generate end string to translate in code to used in content value or parameter need to be converted in string through String.valueOf
	 * 
	 * @param property
	 * @return
	 * 
	 */
	public static String endStringConversion(ModelProperty property) {
		TypeMirror modelType = property.getElement().asType();

		return endStringConversion(modelType);
	}

	/**
	 * generate end string to translate in code to used in content value or parameter need to be converted in string through String.valueOf
	 * 
	 * @param property
	 * @return
	 * 
	 */
	public static String endStringConversion(TypeMirror typeMirror) {
		TypeName typeName = typeName(typeMirror);
		if (typeName == null && typeMirror instanceof ModelType) {
			typeName = ((ModelType) typeMirror).getName();
		}

		if (isString(typeName)) {
			return "";
		} else if (isArray(typeName)) {
			return ")";
		}
		return ")";		
	}

	public static boolean isArray(TypeName typeName) {
		if (typeName instanceof ArrayTypeName) {
			return true;
		}

		return false;
	}

}

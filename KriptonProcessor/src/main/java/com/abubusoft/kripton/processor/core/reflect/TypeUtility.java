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
package com.abubusoft.kripton.processor.core.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BindTypeProcessor;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder.VisitResult;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
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

	public static boolean isTypeEquals(TypeName value, TypeName value2) {
		return value.equals(value2);
	}

	public static boolean isTypePrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.TYPE, Character.TYPE, Boolean.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE);
	}

	public static boolean isTypeWrappedPrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.class, Character.class, Boolean.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
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
	 * Check if class that is rapresented by value has same name of entity
	 * parameter.
	 * 
	 * @param value
	 * @param entity
	 * @return true if value is equals to className.
	 */
	public static boolean isEquals(TypeName value, ModelClass<?> entity) {
		return isSameType(value, entity.getName());
	}

	/**
	 * Check if class that is rapresented by value has same name of entity
	 * parameter.
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
	 * @param prefix
	 * @return class name generated
	 */
	public static ClassName classNameWithPrefix(String packageName, String className, String prefix) {
		return ClassName.get(packageName, className + prefix);
	}

	/**
	 * Generate class name
	 * 
	 * @param className
	 * @return class name generated
	 */
	public static ClassName className(String className) {
		int index = className.lastIndexOf(".");
		if (index > 0) {
			return classNameWithPrefix(className.substring(0, index), className.substring(index + 1), "");
		}
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
		if (typeMirror instanceof ModelType) {
			return ((ModelType) typeMirror).getName();
		}

		return TypeName.get(typeMirror);
	}

	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param typeName
	 * @return typeName
	 */
	public static TypeName typeName(String packageName, String typeName) {
		return classNameWithPrefix(packageName, typeName, "");
	}

	public static TypeName mergeTypeName(TypeName typeName, String typeNamePrefix) {
		ClassName className = className(typeName.toString());

		return classNameWithPrefix(className.packageName(), className.simpleName(), typeNamePrefix);
	}

	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param typeName
	 * @return typeName
	 */
	public static TypeName typeName(String typeName) {
		TypeName[] values = { TypeName.BOOLEAN, TypeName.BYTE, TypeName.CHAR, TypeName.DOUBLE, TypeName.FLOAT, TypeName.INT, TypeName.LONG, TypeName.SHORT };

		for (TypeName item : values) {
			if (item.toString().equals(typeName)) {
				return item;
			}
		}

		if (typeName.endsWith("[]")) {
			return ArrayTypeName.of(typeName(typeName.substring(0, typeName.length() - 2)));
		}

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

	public static boolean isNullable(ModelProperty property) {
		return isNullable(property.getPropertyType().getName());
	}

	/**
	 * Check if method parameter is nullable. Moreover, check nullable status of
	 * method param and property are compatible.
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
			throw (new InvalidMethodSignException(method, String.format("property '%s' is type '%s' and method parameter '%s' is type '%s'. They have to be same type  ", property.getName(),
					property.getPropertyType().getName(), item.value0, item.value1.toString())));
		}
	}

	/**
	 * generate begin string to translate in code to used in content value or
	 * parameter need to be converted in string through String.valueOf
	 * 
	 * 
	 */
	public static void beginStringConversion(Builder methodBuilder, ModelProperty property) {
		TypeMirror modelType = property.getElement().asType();

		beginStringConversion(methodBuilder, modelType);
	}

	/**
	 * generate begin string to translate in code to used in content value or
	 * parameter need to be converted in string through String.valueOf
	 * 
	 * @param property
	 * @return
	 * 
	 */
	public static void beginStringConversion(Builder methodBuilder, TypeMirror typeMirror) {
		TypeName typeName;
		if (typeMirror instanceof ModelType) {
			typeName = ((ModelType) typeMirror).getName();
		} else {
			typeName = typeName(typeMirror);
		}

		SQLTransform transform = SQLTransformer.lookup(typeMirror);

		switch (transform.getColumnType()) {
		case TEXT:
			return;
		case BLOB:
			methodBuilder.addCode("new String(");
			break;
		case INTEGER:
		case REAL:
			methodBuilder.addCode("String.valueOf(");
			break;
		}
	}

	/**
	 * generate end string to translate in code to used in content value or
	 * parameter need to be converted in string through String.valueOf
	 * 
	 * 
	 */
	public static void endStringConversion(Builder methodBuilder, ModelProperty property) {
		TypeMirror modelType = property.getElement().asType();

		endStringConversion(methodBuilder, modelType);
	}

	/**
	 * generate end string to translate in code to used in content value or
	 * parameter need to be converted in string through String.valueOf
	 * 
	 * 
	 */
	public static void endStringConversion(Builder methodBuilder, TypeMirror typeMirror) {
		TypeName typeName;
		if (typeMirror instanceof ModelType) {
			typeName = ((ModelType) typeMirror).getName();
		} else {
			typeName = typeName(typeMirror);
		}

		SQLTransform transform = SQLTransformer.lookup(typeMirror);

		switch (transform.getColumnType()) {
		case TEXT:
			return;
		case BLOB:
			methodBuilder.addCode(",$T.UTF_8)", StandardCharsets.class);
			break;
		case INTEGER:
		case REAL:
			methodBuilder.addCode(")");
			break;
		}
	}

	public static boolean isArray(TypeName typeName) {
		if (typeName instanceof ArrayTypeName) {
			return true;
		}

		return false;
	}

	public static boolean isList(TypeName typeName) {
		if (typeName instanceof ParameterizedTypeName) {
			LiteralType lt = LiteralType.of(typeName.toString());

			return lt.isList();
		}

		return false;
	}

	public static boolean isSet(TypeName typeName) {
		if (typeName instanceof ParameterizedTypeName) {
			LiteralType lt = LiteralType.of(typeName.toString());

			return lt.isSet();
		}

		return false;
	}

	public static boolean isMap(TypeName typeName) {
		if (typeName instanceof ParameterizedTypeName) {
			LiteralType lt = LiteralType.of(typeName.toString());

			return lt.isMap();
		}

		return false;
	}

	public static TypeName typeName(TypeElement element, String suffix) {
		String fullName = element.getQualifiedName().toString() + suffix;

		int lastIndex = fullName.lastIndexOf(".");

		String packageName = fullName.substring(0, lastIndex);
		String className = fullName.substring(lastIndex + 1);

		return typeName(packageName, className);
	}

	public static ClassName className(String packageName, String className) {
		return classNameWithPrefix(packageName, className, "");
	}

	public static TypeName parameterizedTypeName(ClassName rawClass, TypeName paramClass) {
		return ParameterizedTypeName.get(rawClass, paramClass);
	}

	/**
	 * Return simple name of class
	 * 
	 * @param clazz
	 * @return
	 */
	public static String simpleName(TypeName clazzName) {
		String clazz = clazzName.toString();
		int index = clazz.lastIndexOf(".");
		if (index > 0) {
			return clazz.substring(index + 1);
		}
		return clazz;
	}

	public static ArrayTypeName arrayTypeName(Type type) {
		return ArrayTypeName.of(type);
	}

	/**
	 * Given an element, return true if it is a Enum class.
	 * 
	 * @param elements
	 * @param element
	 * @return
	 */
	public static boolean isEnum(TypeName typeName) {
		return isEnum(typeName.toString());
	}

	private static final String JAVA_LANG_ENUM = "java.lang.Enum<?>";

	public static boolean isEnum(String className) {
		try {
			// is it an enum?
			TypeElement element = BindTypeProcessor.elementUtils.getTypeElement(className);
			if (element instanceof TypeElement) {
				TypeElement typeElement = (TypeElement) element;
				TypeMirror superclass = typeElement.getSuperclass();
				if (superclass instanceof DeclaredType) {
					DeclaredType superclassDeclaredType = (DeclaredType) superclass;

					if (JAVA_LANG_ENUM.equals(getCanonicalTypeName(superclassDeclaredType))) {
						return true;
					}
				}
			}
			return false;
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * <p>
	 * Retrieve parametrized type of element (from its parent).
	 * </p>
	 * 
	 * @param element
	 * @return list of typemirror or null
	 */
	public static List<? extends TypeMirror> getParametrizedType(Element element) {
		TypeMirror elementTypeMirror = element.asType();

		System.out.println("DeclaredType " + (element.asType() instanceof DeclaredType));
		System.out.println("ParameterizedType " + (element.asType() instanceof ParameterizedType));
		if (elementTypeMirror instanceof DeclaredType) {
			DeclaredType declaredType = (DeclaredType) element.asType();

			System.out.println(declaredType.asElement().toString());
			System.out.println("naz0" + declaredType.getTypeArguments());

			/*if (declaredType. .getSuperclass() instanceof DeclaredType) {
				DeclaredType superclassDeclaredType = declaredType.getSuperclass();

				System.out.println("naz parent" + typeElement.getTypeParameters());
				System.out.println("naz parent" + typeElement.getTypeParameters());
				return superclassDeclaredType.getTypeArguments();
			}*/
			return null;
		}

		return null;
	}

	protected static DeclaredType parse(TypeMirror clazz) {
		TypeElement element = BindTypeProcessor.elementUtils.getTypeElement(clazz.toString());

		if (element.getTypeParameters() instanceof DeclaredType) {
			DeclaredType declaredType = (DeclaredType) element;

			List<? extends TypeMirror> list = declaredType.getTypeArguments();
			System.out.print(list.size());
		}
		return null;

	}

	public static TypeName parameterParentType(Element entity) {
		TypeElement parentType;
		if (entity instanceof TypeElement) {
			parentType = (TypeElement) entity;

			// parse(parentType.getSuperclass());

			LiteralType parentLiteralType = LiteralType.of(parentType.getSuperclass().toString());
			if (parentLiteralType.getComposedValue() != null) {
				return typeName(parentLiteralType.getComposedValue());
			}
			return null;

		}

		return null;
	}

	/**
	 * Returns a string with type parameters replaced with wildcards. This is
	 * slightly different from
	 * {@link Types#erasure(javax.lang.model.type.TypeMirror)}, which removes
	 * all type parameter data.
	 *
	 * For instance, if there is a field with type List&lt;String&gt;, this
	 * returns a string List&lt;?&gt;.
	 */
	private static String getCanonicalTypeName(DeclaredType declaredType) {
		List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
		if (!typeArguments.isEmpty()) {
			StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
			typeString.append('<');
			for (int i = 0; i < typeArguments.size(); i++) {
				if (i > 0) {
					typeString.append(',');
				}
				typeString.append('?');
			}
			typeString.append('>');

			return typeString.toString();
		} else {
			return declaredType.toString();
		}
	}

}

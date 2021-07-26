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
package com.abubusoft.kripton.processor.sqlite.transform;

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.TypeAdapterHelper;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.lang.*;
import com.abubusoft.kripton.processor.sqlite.transform.math.MathTransformations;
import com.abubusoft.kripton.processor.sqlite.transform.net.NetTransformations;
import com.abubusoft.kripton.processor.sqlite.transform.net.UrlSQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.sql.SQLDateSQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.sql.SQLTimeSQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.sql.SQLTransformations;
import com.abubusoft.kripton.processor.sqlite.transform.time.TimeTransformations;
import com.abubusoft.kripton.processor.sqlite.transform.util.UtilsTransformations;
import com.google.common.collect.Lists;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.abubusoft.kripton.common.Pair.of;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

/**
 * Transformer for java primitive types and frequently used java types.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class SQLTransformer {

	/** cache for transform. */
	private static final Map<TypeName, SQLTransform> cache = new ConcurrentHashMap<TypeName, SQLTransform>();

	private static final List<Pair<String, List<Pair<Class<?>, Class<? extends SQLTransform>>>>> transformations = Lists.newArrayList(
					of("java.lang", LangTransformations.transformations),
					of("java.util", UtilsTransformations.transformations),
					of("java.math", MathTransformations.transformations),
					of("java.net", NetTransformations.transformations),
					of("java.sql", SQLTransformations.transformations),
					of("java.time", TimeTransformations.transformations)
	);


	static SQLTransform getSupportedTransformations(TypeName typeName, List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations) {
		return transformations.stream()
						.filter(item -> item.value0.getName().equals(typeName.toString()))
						.findFirst().map(item -> {
							SQLTransform tranformation = null;
							try {
								tranformation = item.value1.newInstance();
							} catch (InstantiationException | IllegalAccessException e) {
								e.printStackTrace();
							}
							return tranformation;
						}).orElse(null);
	}

	/**
	 * "resultBean", "cursor","indexes["+(i++)+"]".
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param beanClass
	 *            the bean class
	 * @param property
	 *            the property
	 * @param beanName
	 *            the bean name
	 * @param cursorName
	 *            the cursor name
	 * @param indexName
	 *            the index name
	 */
	public static void cursor2Java(SQLiteEntity tableEntity, MethodSpec.Builder methodBuilder, TypeName beanClass, ModelProperty property,
			String beanName, String cursorName, String indexName) {
		TypeName typeName = property.getPropertyType().getTypeName();

		if (property.hasTypeAdapter()) {
			typeName = typeName(property.typeAdapter.dataType);
		}
		SQLTransform transform = lookup(typeName);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null,
				TypeUtility.typeName(property.getElement().asType()));
		transform.generateReadPropertyFromCursor(tableEntity, methodBuilder, beanClass, beanName, property, cursorName, indexName);
	}

	/**
	 * Used to convert a property of managed bean to contentValue.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param beanClass
	 *            the bean class
	 * @param beanName
	 *            the bean name
	 * @param property
	 *            the property
	 */
	public static void javaProperty2ContentValues(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property) {
		TypeName typeName = null;

		if (property != null && property.getPropertyType() != null) {
			typeName = property.getPropertyType().getTypeName();
		}

		if (property != null && property.hasTypeAdapter()) {
			typeName = typeName(property.typeAdapter.dataType);
		}
		SQLTransform transform = lookup(typeName);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, typeName);
		transform.generateWriteProperty2ContentValues(methodBuilder, beanName, beanClass, property);
	}

	/**
	 * Used to convert a property of managed bean to where condition.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param paramName
	 *            the param name
	 * @param paramType
	 *            the param type
	 * @param property
	 *            the property
	 */
	public static void javaProperty2WhereCondition(MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
			String paramName, TypeName paramType, ModelProperty property) {
		if (property != null && property.hasTypeAdapter()) {
			methodBuilder.addCode(
					AbstractSQLTransform.PRE_TYPE_ADAPTER_TO_STRING + "$L" + AbstractSQLTransform.POST_TYPE_ADAPTER,
					SQLTypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz),
					PropertyUtility.getter(paramName, paramType, property));
		} else if (property != null) {
			SQLTransform transform = lookup(property.getPropertyType().getTypeName());

			AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, paramType);
			transform.generateWriteProperty2WhereCondition(methodBuilder, paramName, paramType, property);
		} else {
			AssertKripton.assertTrueOrUnsupportedFieldTypeException(false, paramType);
		}
	}

	/**
	 * Used to convert a method's parameter to contentValues. It can not have a
	 * type adpter, because it eventually use the one define in associated
	 * bean's attribute
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param paramName
	 *            the param name
	 * @param paramType
	 *            the param type
	 * @param property
	 *            the property
	 */
	public static void javaMethodParam2ContentValues(MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
			String paramName, TypeName paramType, ModelProperty property) {
		AssertKripton.assertTrueOrInvalidMethodSignException(!method.hasAdapterForParam(paramName), method,
				"method's parameter '%s' can not use a type adapter", paramName);
		SQLTransform transform = (property != null && property.hasTypeAdapter())
				? lookup(typeName(property.typeAdapter.dataType)) : lookup(paramType);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null,
				(property != null && property.hasTypeAdapter()) ? typeName(property.typeAdapter.dataType) : paramType);
		transform.generateWriteParam2ContentValues(methodBuilder, method, paramName, paramType, property);

	}

	private static void checkTypeAdapterForParam( SQLiteModelMethod method,
			String methodParamName, Class<? extends Annotation> annotation) {
		
		TypeName paramType=method.findParameterType(methodParamName);
		if (method.isSpreadParameter(methodParamName)) {
			if (paramType instanceof ArrayTypeName) {				
				paramType = ((ArrayTypeName) paramType).componentType;
			} else if (paramType instanceof ParameterizedTypeName) {				
				paramType = ((ParameterizedTypeName) paramType).typeArguments.get(0);
			} 
		}
		
		TypeName adapterType = method.getAdapterForParam(methodParamName);
		
		
		TypeName sourceType = TypeUtility.typeName(TypeAdapterHelper.detectSourceType(method, adapterType));
		TypeName uboxSourceType=sourceType;
		
		if (TypeUtility.isTypeWrappedPrimitive(sourceType)) {
			uboxSourceType=sourceType.unbox();
		}
		
		boolean expr=uboxSourceType.toString().equals(paramType.toString()) || sourceType.toString().equals(paramType.toString());
		
		
		AssertKripton.fail(!expr,
				"In DAO '%s', method '%s' has parameter '%s' uses @%s that manages type '%s' instead of '%s'", method.getParent().getName(), method.getName(),
				methodParamName, annotation.getSimpleName(),sourceType, paramType);

	}

	/**
	 * Used to convert a generic parameter to where conditions.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param methodParamName
	 * 			name of the parameter in the method
	 * @param paramName
	 *            the param name
	 * @param paramType
	 *            the param type
	 */
	public static void javaMethodParam2WhereConditions(MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
			String methodParamName, String paramName, TypeName paramType) {
		if (method.hasAdapterForParam(methodParamName)) {
			
			checkTypeAdapterForParam(method, methodParamName, BindSqlParam.class);
			
			methodBuilder.addCode(
					AbstractSQLTransform.PRE_TYPE_ADAPTER_TO_STRING + "$L" + AbstractSQLTransform.POST_TYPE_ADAPTER,
					SQLTypeAdapterUtils.class, method.getAdapterForParam(methodParamName), paramName);
		} else {
			SQLTransform transform = lookup(paramType);

			AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, paramType);
			transform.generateWriteParam2WhereCondition(methodBuilder, method, paramName, paramType);
		}
	}

	/**
	 * Used to convert a generic parameter to where conditions.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param method
	 *            the method
	 * @param paramName
	 *            the param name
	 * @param paramType
	 *            the param type
	 */
	public static void javaMethodParam2WhereConditions(MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
			String paramName, TypeName paramType) {
		javaMethodParam2WhereConditions(methodBuilder, method, paramName, paramName, paramType);
	}

	/**
	 * Get transformer for type.
	 *
	 * @param typeMirror
	 *            the type mirror
	 * @return transform
	 */
	public static SQLTransform lookup(TypeMirror typeMirror) {
		TypeName typeName;

		typeName = typeName(typeMirror);

		return lookup(typeName);
	}

	/**
	 * Get transformer for type.
	 *
	 * @param typeName
	 *            the type name
	 * @return transform
	 */
	public static SQLTransform lookup(TypeName typeName) {
		SQLTransform transform = cache.get(typeName);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(typeName);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, typeName);
		cache.put(typeName, transform);

		return transform;
	}

	/**
	 * Gets the transform.
	 *
	 * @param typeName
	 *            the type name
	 * @return the transform
	 */
	private static SQLTransform getTransform(TypeName typeName) {
		if (typeName.isPrimitive()) {
			return getPrimitiveTransform(typeName);
		}

		if (typeName instanceof ArrayTypeName) {
			ArrayTypeName typeNameArray = (ArrayTypeName) typeName;
			TypeName componentTypeName = typeNameArray.componentType;

			if (TypeUtility.isEquals(componentTypeName, Byte.TYPE.toString())) {
				return new ByteArraySQLTransform();
			} else {
				return new ArraySQLTransform();
			}
		} else if (typeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
			if (TypeUtility.isList(parameterizedTypeName)) {
				return new ListSQLTransformation();
			} else if (TypeUtility.isSet(parameterizedTypeName)) {
				return new SetSQLTransformation();
			} else if (TypeUtility.isMap(parameterizedTypeName)) {
				return new MapSQLTransformation();
			}
		}

		if (TypeUtility.isEnum(typeName)) {
			return new EnumSQLTransform(typeName);
		}

		// for default is treated as object
		String name = typeName.toString();
		List<Pair<Class<?>, Class<? extends SQLTransform>>> values = getSupportedTransformations(name);

		if (values != null) {
			return getSupportedTransformations(typeName, values);
		} else {
			return new ObjectSQLTransform();
		}
	}

	private static List<Pair<Class<?>, Class<? extends SQLTransform>>> getSupportedTransformations(String name) {
		List<Pair<Class<?>, Class<? extends SQLTransform>>> values = transformations.stream()
						.filter(item -> name.startsWith(item.value0))
						.findFirst().map(item -> item.value1).orElse(null);
		return values;
	}

	/**
	 * Gets the sql transform.
	 *
	 * @param typeName
	 *            the type name
	 * @return the sql transform
	 */
	static SQLTransform getSqlTransform(TypeName typeName) {
		if (Time.class.getName().equals(typeName.toString())) {
			return new SQLTimeSQLTransform();
		}

		if (java.sql.Date.class.getName().equals(typeName.toString())) {
			return new SQLDateSQLTransform();
		}

		return null;
	}

	/**
	 * Gets the net transform.
	 *
	 * @param typeName
	 *            the type name
	 * @return the net transform
	 */
	static SQLTransform getNetTransform(TypeName typeName) {
		if (URL.class.getName().equals(typeName.toString())) {
			return new UrlSQLTransform();
		}

		return null;
	}

	/**
	 * Checks if is supported JDK type.
	 *
	 * @param typeName
	 *            the type name
	 * @return true, if is supported JDK type
	 */
	public static boolean isSupportedJDKType(TypeName typeName) {
		if (typeName.isPrimitive()) {
			return getPrimitiveTransform(typeName) != null;
		}

		String name = typeName.toString();
		return getSupportedTransformations(name)!=null;
	}

	/**
	 * Get Java primitive type Transformable.
	 *
	 * @param type
	 *            the type
	 * @return the primitive transform
	 */
	static SQLTransform getPrimitiveTransform(TypeName type) {

		if (Integer.TYPE.toString().equals(type.toString())) {
			return new IntegerSQLTransform(false);
		}
		if (Boolean.TYPE.toString().equals(type.toString())) {
			return new BooleanSQLTransform(false);
		}
		if (Long.TYPE.toString().equals(type.toString())) {
			return new LongSQLTransform(false);
		}
		if (Double.TYPE.toString().equals(type.toString())) {
			return new DoubleSQLTransform(false);
		}
		if (Float.TYPE.toString().equals(type.toString())) {
			return new FloatSQLTransform(false);
		}
		if (Short.TYPE.toString().equals(type.toString())) {
			return new ShortSQLTransform(false);
		}
		if (Byte.TYPE.toString().equals(type.toString())) {
			return new ByteSQLTransform(false);
		}
		if (Character.TYPE.toString().equals(type.toString())) {
			return new CharacterSQLTransform(false);
		}
		return null;
	}

	/**
	 * Reset bean.
	 *
	 * @param methodBuilder
	 *            the method builder
	 * @param beanClass
	 *            the bean class
	 * @param beanName
	 *            the bean name
	 * @param property
	 *            the property
	 * @param cursorName
	 *            the cursor name
	 * @param indexName
	 *            the index name
	 */
	public static void resetBean(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		SQLTransform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateResetProperty(methodBuilder, beanClass, beanName, property, cursorName, indexName);

	}

	/**
	 * Detect column type. If column affinity is specified by annotation,
	 * affinity column is used.
	 *
	 * @param property
	 *            the property
	 * @return the SQlite column type as string
	 */
	public static String columnTypeAsString(SQLProperty property) {
		if (property.columnAffinityType != null && ColumnAffinityType.AUTO != property.columnAffinityType) {
			return property.columnAffinityType.toString();
		}

		TypeName typeName = property.getPropertyType().getTypeName();
		if (property.hasTypeAdapter()) {
			typeName = typeName(property.typeAdapter.dataType);
		}

		SQLTransform transform = lookup(typeName);

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		return transform.getColumnType().toString();

	}

}

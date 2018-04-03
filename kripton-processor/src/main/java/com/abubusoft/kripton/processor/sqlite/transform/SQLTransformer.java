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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * Transformer for java primitive types and frequently used java types
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SQLTransformer {

	/**
	 * cache for transform
	 */
	private static final Map<TypeName, SQLTransform> cache = new ConcurrentHashMap<TypeName, SQLTransform>();

	/**
	 * "resultBean", "cursor","indexes["+(i++)+"]"
	 * 
	 * @param beanClass
	 */
	public static void cursor2Java(MethodSpec.Builder methodBuilder, TypeName beanClass, ModelProperty property, String beanName, String cursorName, String indexName) {
		TypeName typeName = property.getPropertyType().getTypeName();

		if (property.hasTypeAdapter()) {
			typeName = typeName(property.typeAdapter.dataType);
		}
		SQLTransform transform = lookup(typeName);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, TypeUtility.typeName(property.getElement().asType()));
		transform.generateReadPropertyFromCursor(methodBuilder, beanClass, beanName, property, cursorName, indexName);
	}

	/**
	 * Used to convert a property of managed bean to contentValue
	 * 
	 * @param methodBuilder
	 * @param beanClass
	 * @param beanName
	 * @param property
	 */
	public static void javaProperty2ContentValues(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		TypeName typeName = null;

		if (property!=null && property.getPropertyType() != null) {
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
	 * Used to convert a property of managed bean to where condition
	 * 
	 * @param methodBuilder
	 * @param paramName
	 * @param paramType
	 * @param property
	 */
	public static void javaProperty2WhereCondition(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramType, ModelProperty property) {
		if (property != null && property.hasTypeAdapter()) {
			methodBuilder.addCode(AbstractSQLTransform.PRE_TYPE_ADAPTER_TO_STRING + "$L" + AbstractSQLTransform.POST_TYPE_ADAPTER, SQLTypeAdapterUtils.class,
					typeName(property.typeAdapter.adapterClazz), PropertyUtility.getter(paramName, paramType, property));
		} else if (property!=null) {
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
	 * @param paramName
	 * @param paramType
	 * @param property
	 * @param sqlDaoDefinition
	 */
	public static void javaMethodParam2ContentValues(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramType, ModelProperty property) {
		AssertKripton.assertTrueOrInvalidMethodSignException(!method.hasAdapterForParam(paramName), method, "method's parameter '%s' can not use a type adapter", paramName);
		SQLTransform transform = (property != null && property.hasTypeAdapter()) ? lookup(typeName(property.typeAdapter.dataType)) : lookup(paramType);

		AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, (property != null && property.hasTypeAdapter()) ? typeName(property.typeAdapter.dataType) : paramType);
		transform.generateWriteParam2ContentValues(methodBuilder, method, paramName, paramType, property);

	}

	/**
	 * Used to convert a generic parameter to where conditions
	 * 
	 * @param methodBuilder
	 * @param paramName
	 * @param paramType
	 * @param property
	 * @param sqlDaoDefinition
	 */
	public static void javaMethodParam2WhereConditions(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramType) {
		if (method.hasAdapterForParam(paramName)) {
			methodBuilder.addCode(AbstractSQLTransform.PRE_TYPE_ADAPTER_TO_STRING + "$L" + AbstractSQLTransform.POST_TYPE_ADAPTER, SQLTypeAdapterUtils.class, method.getAdapterForParam(paramName),
					paramName);
		} else {
			SQLTransform transform = lookup(paramType);

			AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, paramType);
			transform.generateWriteParam2WhereCondition(methodBuilder, method, paramName, paramType);
		}
	}

	/**
	 * Get transformer for type
	 * 
	 * @param typeMirror
	 * @return transform
	 */
	public static SQLTransform lookup(TypeMirror typeMirror) {
		TypeName typeName;

		typeName = typeName(typeMirror);

		return lookup(typeName);
	}

	/**
	 * Get transformer for type
	 * 
	 * @param typeName
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

		String name = typeName.toString();

		if (name.startsWith("java.lang")) {
			return getLanguageTransform(typeName);
		}

		if (name.startsWith("java.util")) {
			return getUtilTransform(typeName);
		}

		if (name.startsWith("java.math")) {
			return getMathTransform(typeName);
		}

		if (name.startsWith("java.net")) {
			return getNetTransform(typeName);
		}

		if (name.startsWith("java.sql")) {
			return getSqlTransform(typeName);
		}

		if (TypeUtility.isEnum(typeName)) {
			return new EnumSQLTransform(typeName);
		}

		return new ObjectSQLTransform();
	}

	static SQLTransform getSqlTransform(TypeName typeName) {
		if (Time.class.getName().equals(typeName.toString())) {
			return new SQLTimeSQLTransform();
		}

		if (java.sql.Date.class.getName().equals(typeName.toString())) {
			return new SQLDateSQLTransform();
		}

		return null;
	}

	static SQLTransform getNetTransform(TypeName typeName) {
		if (URL.class.getName().equals(typeName.toString())) {
			return new UrlSQLTransform();
		}

		return null;
	}

	public static boolean isSupportedJDKType(TypeName typeName) {
		if (typeName.isPrimitive()) {
			return getPrimitiveTransform(typeName) != null;
		}

		String name = typeName.toString();

		if (name.startsWith("java.lang")) {
			return getLanguageTransform(typeName) != null;
		}

		if (name.startsWith("java.util")) {
			return getUtilTransform(typeName) != null;
		}

		if (name.startsWith("java.math")) {
			return getMathTransform(typeName) != null;
		}

		if (name.startsWith("java.net")) {
			return getNetTransform(typeName) != null;
		}

		if (name.startsWith("java.sql")) {
			return getSqlTransform(typeName) != null;
		}

		return false;
	}

	static SQLTransform getMathTransform(TypeName typeName) {
		if (BigDecimal.class.getName().equals(typeName.toString())) {
			return new BigDecimalSQLTransform();
		} else if (BigInteger.class.getName().equals(typeName.toString())) {
			return new BigIntegerSQLTransform();
		}

		return null;
	}

	/**
	 * Get Java primitive type Transformable
	 * 
	 * @param type
	 * @return
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
	 * Get Java primitive wrapping type Transformable
	 * 
	 * @param type
	 * @return
	 */
	static SQLTransform getLanguageTransform(TypeName type) {
		String typeName = type.toString();

		if (Integer.class.getCanonicalName().equals(typeName)) {
			return new IntegerSQLTransform(true);
		}
		if (Boolean.class.getCanonicalName().equals(typeName)) {
			return new BooleanSQLTransform(true);
		}
		if (Long.class.getCanonicalName().equals(typeName)) {
			return new LongSQLTransform(true);
		}
		if (Double.class.getCanonicalName().equals(typeName)) {
			return new DoubleSQLTransform(true);
		}
		if (Float.class.getCanonicalName().equals(typeName)) {
			return new FloatSQLTransform(true);
		}
		if (Short.class.getCanonicalName().equals(typeName)) {
			return new ShortSQLTransform(true);
		}
		if (Byte.class.getCanonicalName().equals(typeName)) {
			return new ByteSQLTransform(true);
		}
		if (Character.class.getCanonicalName().equals(typeName)) {
			return new CharacterSQLTransform(true);
		}
		if (String.class.getCanonicalName().equals(typeName)) {
			return new StringSQLTransform();
		}
		return null;
	}

	/**
	 * Get java.util type Transformable
	 * 
	 * @param type
	 * @return
	 */

	static SQLTransform getUtilTransform(TypeName type) {
		String typeName = type.toString();

		// Integer.class.getCanonicalName().equals(typeName)
		if (Date.class.getCanonicalName().equals(typeName)) {
			return new DateSQLTransform();
		}
		if (Locale.class.getCanonicalName().equals(typeName)) {
			return new LocaleSQLTransform();
		}
		if (Currency.class.getCanonicalName().equals(typeName)) {
			return new CurrencySQLTransform();
		}
		if (Calendar.class.getCanonicalName().equals(typeName)) {
			return new CalendarSQLTransform();
		}
		if (TimeZone.class.getCanonicalName().equals(typeName)) {
			return new TimeZoneSQLTransform();
		}
		return null;
	}

	public static void resetBean(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		SQLTransform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateResetProperty(methodBuilder, beanClass, beanName, property, cursorName, indexName);

	}

	public static String columnTypeAsString(ModelProperty property) {
		TypeName typeName = property.getPropertyType().getTypeName();
		if (property.hasTypeAdapter()) {
			typeName = typeName(property.typeAdapter.dataType);
		}

		SQLTransform transform = lookup(typeName);

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		return transform.getColumnTypeAsString();

	}

	public static SQLColumnType columnType(ModelProperty property) {
		SQLTransform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		return transform.getColumnType();

	}

	public static SQLColumnType columnType(TypeName property) {
		SQLTransform transform = lookup(property);

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property + " not supported");
		}
		return transform.getColumnType();

	}

}

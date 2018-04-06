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
package com.abubusoft.kripton.processor.sharedprefs.transform;

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

import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * Transformer for java primitive types and frequently used java types.
 *
 * @author xcesco
 */
public abstract class PrefsTransformer {

	/** cache for transform. */
	private static final Map<TypeName, PrefsTransform> cache = new ConcurrentHashMap<TypeName, PrefsTransform>();

	/**
	 * Register custom transformable for a Java primitive type or a frequently
	 * used Java type.
	 * 
	 * @param type
	 *            a Java primitive type or a frequently used Java type.
	 * @param transform
	 *            a class implementing @see
	 *            org.abubu.elio.binder.transform.Transformable interface.
	 */
	public static void register(TypeName type, PrefsTransform transform) {
		cache.put(type, transform);
	}

	/**
	 * Get transformer for type.
	 *
	 * @param property the property
	 * @return transform
	 */
	public static PrefsTransform lookup(PrefsProperty property) {
		TypeMirror typeMirror = property.getElement().asType();

		TypeName typeName = typeName(typeMirror);
		return lookup(typeName);
	}

	/**
	 * Get transformer for type.
	 *
	 * @param typeName the type name
	 * @return transform
	 */
	public static PrefsTransform lookup(TypeName typeName) {
		PrefsTransform transform = cache.get(typeName);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(typeName);
		AssertKripton.assertNotNull(transform, new UnsupportedFieldTypeException(typeName));		
		cache.put(typeName, transform);

		return transform;
	}

	/**
	 * Gets the transform.
	 *
	 * @param typeName the type name
	 * @return the transform
	 */
	private static PrefsTransform getTransform(TypeName typeName) {
		if (typeName.isPrimitive()) {
			return getPrimitiveTransform(typeName);
		}

		if (typeName instanceof ArrayTypeName) {
			ArrayTypeName typeNameArray = (ArrayTypeName) typeName;
			TypeName componentTypeName = typeNameArray.componentType;

			if (TypeUtility.isEquals(componentTypeName, Byte.TYPE.toString())) {
				return new ByteArrayPrefsTransform();
			} else {
				return new ArrayPrefsTransform();
			}
		} else if (typeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
			if (TypeUtility.isList(parameterizedTypeName)) {
				return new ListPrefsTransformation();
			} else if (TypeUtility.isSet(parameterizedTypeName)) {
				return new SetPrefsTransformation();
			} else if (TypeUtility.isMap(parameterizedTypeName)) {
				return new MapPrefsTransformation();
			}
		}

		PrefsTransform transform;

		transform = getLanguageTransform(typeName);
		if (transform != null) {
			return transform;
		}

		transform = getUtilTransform(typeName);
		if (transform != null) {
			return transform;
		}

		transform = getMathTransform(typeName);
		if (transform != null) {
			return transform;
		}

		transform = getNetTransform(typeName);
		if (transform != null) {
			return transform;
		}

		transform = getSqlTransform(typeName);
		if (transform != null) {
			return transform;
		}

		if (TypeUtility.isEnum(typeName)) {
			return new EnumPrefsTransform(typeName);
		}

		return new ObjectPrefsTransform();
	}

	/**
	 * Gets the sql transform.
	 *
	 * @param typeName the type name
	 * @return the sql transform
	 */
	static PrefsTransform getSqlTransform(TypeName typeName) {
		if (Time.class.getName().equals(typeName.toString())) {
			return new SQLTimePrefsTransform();
		}
		
		if (java.sql.Date.class.getName().equals(typeName.toString())) {
			return new SQLDatePrefsTransform();
		}

		return null;
	}

	/**
	 * Gets the net transform.
	 *
	 * @param typeName the type name
	 * @return the net transform
	 */
	static PrefsTransform getNetTransform(TypeName typeName) {
		if (URL.class.getName().equals(typeName.toString())) {
			return new UrlPrefsTransform();
		}

		return null;
	}

	/**
	 * Gets the math transform.
	 *
	 * @param typeName the type name
	 * @return the math transform
	 */
	static PrefsTransform getMathTransform(TypeName typeName) {
		if (BigDecimal.class.getName().equals(typeName.toString())) {
			return new BigDecimalPrefsTransform();
		} else if (BigInteger.class.getName().equals(typeName.toString())) {
			return new BigIntegerPrefsTransform();
		}

		return null;
	}

	/**
	 * Get Java primitive type Transformable.
	 *
	 * @param type the type
	 * @return the primitive transform
	 */
	static PrefsTransform getPrimitiveTransform(TypeName type) {

		if (Integer.TYPE.toString().equals(type.toString())) {
			return new IntegerPrefsTransform(false);
		}
		if (Boolean.TYPE.toString().equals(type.toString())) {
			return new BooleanPrefsTransform(false);
		}
		if (Long.TYPE.toString().equals(type.toString())) {
			return new LongPrefsTransform(false);
		}
		if (Double.TYPE.toString().equals(type.toString())) {
			return new DoublePrefsTransform(false);
		}
		if (Float.TYPE.toString().equals(type.toString())) {
			return new FloatPrefsTransform(false);
		}
		if (Short.TYPE.toString().equals(type.toString())) {
			return new ShortPrefsTransform(false);
		}
		if (Byte.TYPE.toString().equals(type.toString())) {
			return new BytePrefsTransform(false);
		}
		if (Character.TYPE.toString().equals(type.toString())) {
			return new CharacterPrefsTransform(false);
		}
		return null;
	}

	/**
	 * Get Java primitive wrapping type Transformable.
	 *
	 * @param type the type
	 * @return the language transform
	 */
	static PrefsTransform getLanguageTransform(TypeName type) {
		String typeName = type.toString();

		if (Integer.class.getCanonicalName().equals(typeName)) {
			return new IntegerPrefsTransform(true);
		}
		if (Boolean.class.getCanonicalName().equals(typeName)) {
			return new BooleanPrefsTransform(true);
		}
		if (Long.class.getCanonicalName().equals(typeName)) {
			return new LongPrefsTransform(true);
		}
		if (Double.class.getCanonicalName().equals(typeName)) {
			return new DoublePrefsTransform(true);
		}
		if (Float.class.getCanonicalName().equals(typeName)) {
			return new FloatPrefsTransform(true);
		}
		if (Short.class.getCanonicalName().equals(typeName)) {
			return new ShortPrefsTransform(true);
		}
		if (Byte.class.getCanonicalName().equals(typeName)) {
			return new BytePrefsTransform(true);
		}
		if (Character.class.getCanonicalName().equals(typeName)) {
			return new CharacterPrefsTransform(true);
		}
		if (String.class.getCanonicalName().equals(typeName)) {
			return new StringPrefsTransform();
		}
		return null;
	}

	/**
	 * Get java.util type Transformable
	 *
	 * @param type the type
	 * @return the util transform
	 */

	static PrefsTransform getUtilTransform(TypeName type) {
		String typeName = type.toString();

		// Integer.class.getCanonicalName().equals(typeName)
		if (Date.class.getCanonicalName().equals(typeName)) {
			return new DatePrefsTransform();
		}
		if (Locale.class.getCanonicalName().equals(typeName)) {
			return new LocalePrefsTransform();
		}
		if (Currency.class.getCanonicalName().equals(typeName)) {
			return new CurrencyPrefsTransform();
		}
		if (Calendar.class.getCanonicalName().equals(typeName)) {
			return new CalendarPrefsTransform();
		}
		if (TimeZone.class.getCanonicalName().equals(typeName)) {
			return new TimeZonePrefsTransform();
		}
		return null;
	}

}

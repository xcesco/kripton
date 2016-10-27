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
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * Transformer for java primitive types and frequently used java types
 * 
 * @author bulldog
 *
 */
public abstract class Transformer {

	/**
	 * cache for transform
	 */
	private static final Map<TypeName, Transform> cache = new ConcurrentHashMap<TypeName, Transform>();

	/**
	 * "resultBean", "cursor","indexes["+(i++)+"]"
	 * 
	 * @param beanClass
	 */
	public static void cursor2Java(MethodSpec.Builder methodBuilder, TypeName beanClass, ModelProperty property,
			String beanName, String cursorName, String indexName) {
		Transform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateReadProperty(methodBuilder, beanClass, beanName, property, cursorName, indexName);
	}

	public static void java2ContentValues(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property) {
		Transform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new RuntimeException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateWriteProperty(methodBuilder, beanClass, beanName, property);
	}

	public static void java2ContentValues(MethodSpec.Builder methodBuilder, TypeMirror objectType, String objectName) {
		Transform transform = lookup(objectType);

		if (transform == null) {
			throw new RuntimeException("Transform of " + typeName(objectType) + " not supported");
		}
		transform.generateWriteProperty(methodBuilder, objectName);
	}

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
	public static void register(TypeName type, Transform transform) {
		cache.put(type, transform);
	}

	/**
	 * Get transformer for type
	 * 
	 * @param typeMirror
	 * @return transform
	 */
	public static Transform lookup(TypeMirror typeMirror) {
		TypeName typeName;
		
		if (typeMirror instanceof ModelType)
		{
			typeName = ((ModelType)typeMirror).getName();	
		} else {
			typeName = typeName(typeMirror);
		}
		
		return lookup(typeName);
	}
	
	/**
	 * Get transformer for type
	 * 
	 * @param typeName
	 * @return transform
	 */
	public static Transform lookup(TypeName typeName) {
		Transform transform = cache.get(typeName);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(typeName);
		if (transform != null) {
			cache.put(typeName, transform);
		}

		return transform;
	}

	private static Transform getTransform(TypeName typeName) {
		if (typeName.isPrimitive()) {
			return getPrimitiveTransform(typeName);
		}

		if (typeName instanceof ArrayTypeName) {			
			ArrayTypeName typeNameArray = (ArrayTypeName) typeName;
			TypeName componentTypeName = typeNameArray.componentType;			
			
			if (TypeUtility.isSameType(componentTypeName, Byte.TYPE.toString())) {
				return new ByteArrayTransform();
			} else if (componentTypeName.isPrimitive()) {
				return new ArrayTransform(componentTypeName, componentTypeName.isPrimitive());
			} else {
				return new ArrayTransform(componentTypeName);
			}
		} else if (typeName instanceof ParameterizedTypeName)
		{
			ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
			if (TypeUtility.isList(parameterizedTypeName))
			{
				return new ListTransformation(parameterizedTypeName);
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
		 /*
		 * if (name.startsWith("java.net")) { return new UrlTransform(); } if
		 * (name.startsWith("java.sql")) { return new TimeTransform(); } if
		 * (type == QName.class) { return new QNameTransform(); }
		 * 
		 * if (CustomTransform.class.isAssignableFrom(type) && type !=
		 * DefaultCustomTransform.class) { try { return (Transform<?>)
		 * type.newInstance(); } catch (InstantiationException e) {
		 * e.printStackTrace(); } catch (IllegalAccessException e) {
		 * e.printStackTrace(); } }
		 */

		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }

		return null;
	}

	private static Transform getMathTransform(TypeName typeName) {
		if (BigDecimal.class.getName().equals(typeName.toString())) {
			return new BigDecimalTransform();
		} else if (BigInteger.class.getName().equals(typeName.toString())) {
			return new BigIntegerTransform();
		} 
		
		return null;
	}

	/**
	 * Get Java primitive type Transformable
	 * 
	 * @param type
	 * @return
	 */
	private static Transform getPrimitiveTransform(TypeName type) {

		if (Integer.TYPE.toString().equals(type.toString())) {
			return new IntegerTransform(false);
		}
		if (Boolean.TYPE.toString().equals(type.toString())) {
			return new BooleanTransform(false);
		}
		if (Long.TYPE.toString().equals(type.toString())) {
			return new LongTransform(false);
		}
		if (Double.TYPE.toString().equals(type.toString())) {
			return new DoubleTransform(false);
		}
		if (Float.TYPE.toString().equals(type.toString())) {
			return new FloatTransform(false);
		}
		if (Short.TYPE.toString().equals(type.toString())) {
			return new ShortTransform(false);
		}
		if (Byte.TYPE.toString().equals(type.toString())) {
			return new ByteTransform(false);
		}
		if (Character.TYPE.toString().equals(type.toString())) {
		}
		return null;
	}

	/**
	 * Get Java primitive wrapping type Transformable
	 * 
	 * @param type
	 * @return
	 */
	private static Transform getLanguageTransform(TypeName type) {
		String typeName = type.toString();

		if (Integer.class.getCanonicalName().equals(typeName)) {
			return new IntegerTransform(true);
		}
		if (Boolean.class.getCanonicalName().equals(typeName)) {
			return new BooleanTransform(true);
		}
		if (Long.class.getCanonicalName().equals(typeName)) {
			return new LongTransform(true);
		}
		if (Double.class.getCanonicalName().equals(typeName)) {
			return new DoubleTransform(true);
		}
		if (Float.class.getCanonicalName().equals(typeName)) {
			return new FloatTransform(true);
		}
		if (Short.class.getCanonicalName().equals(typeName)) {
			return new ShortTransform(true);
		}
		if (Byte.class.getCanonicalName().equals(typeName)) {
			return new ByteTransform(true);
		}
		if (Character.class.getCanonicalName().equals(typeName)) {
			return new CharacterTransform(true);
		}
		if (String.class.getCanonicalName().equals(typeName)) {
			return new StringTransform();
		}
		return null;
	}


	/**
	 * Get java.util type Transformable
	 * 
	 * @param type
	 * @return
	 */

	private static Transform getUtilTransform(TypeName type) {
		String typeName = type.toString();

		// Integer.class.getCanonicalName().equals(typeName)
		if (Date.class.getCanonicalName().equals(typeName)) {
			return new DateTransform();
		}
		if (Locale.class.getCanonicalName().equals(typeName)) {
			return new LocaleTransform();
		}
		if (Currency.class.getCanonicalName().equals(typeName)) {
			return new CurrencyTransform();
		}
		if (Calendar.class.getCanonicalName().equals(typeName)) {
			return new CalendarTransform();
		}
		if (TimeZone.class.getCanonicalName().equals(typeName)) {
			return new TimeZoneTransform();
		}
		return null;
	}

	public static void resetBean(MethodSpec.Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		Transform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateResetProperty(methodBuilder, beanClass, beanName, property, cursorName, indexName);

	}

	public static String columnType(ModelProperty property) {
		Transform transform = lookup(property.getElement().asType());

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		return transform.generateColumnType(property);

	}

}

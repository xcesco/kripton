package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.google.common.reflect.TypeToken;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Transformer for java primitive types and frequently used java types
 * 
 * @author bulldog
 *
 */
public class Transformer {

	// Transformable cache
	private static final Map<TypeName, Transform> cache = new ConcurrentHashMap<TypeName, Transform>();

	/**
	 * "resultBean", "cursor","indexes["+(i++)+"]"
	 */
	public static void cursor2Bean(MethodSpec.Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		Transform transform = lookup(typeName(property.getElement().asType()));

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		transform.generateReadProperty(methodBuilder, property, beanName, cursorName, indexName);
	}

	public static String bean2ContentValues(ModelProperty property) throws Exception {
		Transform transform = lookup(typeName(property.getElement().asType()));

		if (transform == null) {
			throw new IllegalArgumentException("Transform of " + property.getElement().asType() + " not supported");
		}
		return transform.generateWriteProperty(property);
	}

	/**
	 * Register custom transformable for a Java primitive type or a frequently used Java type.
	 * 
	 * @param type
	 *            a Java primitive type or a frequently used Java type.
	 * @param transform
	 *            a class implementing @see org.abubu.elio.binder.transform.Transformable interface.
	 */
	public static void register(TypeName type, Transform transform) {
		cache.put(type, transform);
	}

	/**
	 * Get transformer for type
	 * 
	 * @param type
	 * @return
	 */
	public static Transform lookup(TypeName type) {
		Transform transform = cache.get(type);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(type);
		if (transform != null) {
			cache.put(type, transform);
		}

		return transform;
	}

	private static Transform getTransform(TypeName typeName) {
		try {

			if (typeName.isPrimitive()) {
				return getPrimitiveTransform(typeName);
			}

			if (typeName instanceof ArrayTypeName) {
				ArrayTypeName typeNameArray = (ArrayTypeName) typeName;
				if (typeNameArray.toString().equals(Byte.TYPE + "[]")) {
					return new ByteArrayTransform();

				} else {
					return new Base64Transform();
				}
			}

			TypeToken<?> type = TypeToken.of(Class.forName(typeName.toString()));

			if (type.getRawType().isEnum()) {
				return new EnumTransform(typeName);
			}

			String name = typeName.toString();

			if (name.startsWith("java.lang")) {
				return getLanguageTransform(typeName);
			}

			if (name.startsWith("java.util")) {
				return getUtilTransform(typeName);
			}
			/*
			 * if (name.startsWith("java.math")) { return getMathTransform(type); } if (name.startsWith("java.net")) { return new UrlTransform(); } if (name.startsWith("java.sql")) { return new TimeTransform(); } if (type == QName.class) {
			 * return new QNameTransform(); }
			 * 
			 * if (CustomTransform.class.isAssignableFrom(type) && type != DefaultCustomTransform.class) { try { return (Transform<?>) type.newInstance(); } catch (InstantiationException e) { e.printStackTrace(); } catch
			 * (IllegalAccessException e) { e.printStackTrace(); } }
			 */

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			return new IntegerTransform();
		}
		if (Boolean.TYPE.toString().equals(type.toString())) {
			return new BooleanTransform();
		}
		if (Long.TYPE.toString().equals(type.toString())) {
			return new LongTransform();
		}
		if (Double.TYPE.toString().equals(type.toString())) {
			return new DoubleTransform();
		}
		if (Float.TYPE.toString().equals(type.toString())) {
			return new FloatTransform();
		}
		if (Short.TYPE.toString().equals(type.toString())) {
			return new ShortTransform();
		}
		if (Byte.TYPE.toString().equals(type.toString())) {
			return new ByteTransform();
		}
		if (Character.TYPE.toString().equals(type.toString())) {
			return new CharacterTransform();
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
			return new IntegerTransform();
		}
		if (Boolean.class.getCanonicalName().equals(typeName)) {
			return new BooleanTransform();
		}
		if (Long.class.getCanonicalName().equals(typeName)) {
			return new LongTransform();
		}
		if (Double.class.getCanonicalName().equals(typeName)) {
			return new DoubleTransform();
		}
		if (Float.class.getCanonicalName().equals(typeName)) {
			return new FloatTransform();
		}
		if (Short.class.getCanonicalName().equals(typeName)) {
			return new ShortTransform();
		}
		if (Byte.class.getCanonicalName().equals(typeName)) {
			return new ByteTransform();
		}
		if (Character.class.getCanonicalName().equals(typeName)) {
			return new CharacterTransform();
		}
		if (String.class.getCanonicalName().equals(typeName)) {
			return new StringTransform();
		}
		return null;
	}

	/**
	 * Get java.math type Transformable
	 * 
	 * @param type
	 * @return
	 */
	/*
	 * private static Transform<?> getMathTransform(Class<?> type) { if (type == BigDecimal.class) { return new BigDecimalTransform(); } if (type == BigInteger.class) { return new BigIntegerTransform(); } return null; }
	 */

	/**
	 * Get java.util type Transformable
	 * 
	 * @param type
	 * @return
	 */

	private static Transform getUtilTransform(TypeName type) {
		String typeName = type.toString();

		//Integer.class.getCanonicalName().equals(typeName)
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

}

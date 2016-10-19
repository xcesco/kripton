package com.abubusoft.kripton.binder.transform;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;

/**
 * Transformer for java primitive types and frequently used java types
 * 
 * @author bulldog
 *
 */
public class Transformer {

	// Transformable cache
	private static final Map<Class<?>, Transform<?>> cache = new ConcurrentHashMap<Class<?>, Transform<?>>();

	public static Object read(String value, Class<?> type) throws Exception {
		Transform<?> transfrom = lookup(type);

		if (transfrom == null) {
			throw new IllegalArgumentException("Transform of " + type + " not supported");
		}
		return transfrom.read(value);
	}

	@SuppressWarnings("unchecked")
	public static String write(Object value, Class<?> type) throws Exception {
		@SuppressWarnings("rawtypes")
		Transform transfrom = lookup(type);

		if (transfrom == null) {
			throw new IllegalArgumentException("Transform of " + type + " not supported");
		}
		return transfrom.write(value);
	}

	public static boolean isTransformable(Class<?> type) {
		return lookup(type) != null;
	}

	public static boolean isPrimitive(Class<?> type) {
		return isTransformable(type);
	}

	/**
	 * Register custom transformable for a Java primitive type or a frequently used Java type.
	 * 
	 * @param type
	 *            a Java primitive type or a frequently used Java type.
	 * @param transform
	 *            a class implementing @see org.abubu.elio.binder.transform.Transformable interface.
	 */
	public static void register(Class<?> type, Transform<?> transform) {
		cache.put(type, transform);
	}

	/**
	 * Get transformer for type
	 * 
	 * @param type
	 * @return
	 * 		transform 
	 */
	public static Transform<?> lookup(Class<?> type) {
		Transform<?> transform = cache.get(type);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(type);
		if (transform != null) {
			cache.put(type, transform);
		}

		return transform;
	}

	private static Transform<?> getTransform(Class<?> type) {

		if (type.isPrimitive()) {
			return getPrimitiveTransform(type);
		}

		if (type.isArray() && type == byte[].class) {
			return new Base64Transform();
		}

		if (type.isEnum()) {
			return new EnumTransform(type);
		}

		String name = type.getName();

		if (name.startsWith("java.lang")) {
			return getLanguageTransform(type);
		}
		if (name.startsWith("java.util")) {
			return getUtilTransform(type);
		}
		if (name.startsWith("java.math")) {
			return getMathTransform(type);
		}
		if (name.startsWith("java.net")) {
			return new UrlTransform();
		}
		if (name.startsWith("java.sql")) {
			return new TimeTransform();
		}
		if (type == QName.class) {
			return new QNameTransform();
		}

		if (CustomTransform.class.isAssignableFrom(type) && type != DefaultCustomTransform.class) {
			try {
				return (Transform<?>) type.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Get Java primitive type Transformable
	 * @param type
	 * @return
	 */
	private static Transform<?> getPrimitiveTransform(Class<?> type) {
		if (type == int.class) {
			return new IntegerTransform();
		}
		if (type == boolean.class) {
			return new BooleanTransform();
		}
		if (type == long.class) {
			return new LongTransform();
		}
		if (type == double.class) {
			return new DoubleTransform();
		}
		if (type == float.class) {
			return new FloatTransform();
		}
		if (type == short.class) {
			return new ShortTransform();
		}
		if (type == byte.class) {
			return new ByteTransform();
		}
		if (type == char.class) {
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
	private static Transform<?> getLanguageTransform(Class<?> type) {
		if (type == Boolean.class) {
			return new BooleanTransform();
		}
		if (type == Integer.class) {
			return new IntegerTransform();
		}
		if (type == Long.class) {
			return new LongTransform();
		}
		if (type == Double.class) {
			return new DoubleTransform();
		}
		if (type == Float.class) {
			return new FloatTransform();
		}
		if (type == Short.class) {
			return new ShortTransform();
		}
		if (type == Byte.class) {
			return new ByteTransform();
		}
		if (type == Character.class) {
			return new CharacterTransform();
		}
		if (type == String.class) {
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
	private static Transform<?> getMathTransform(Class<?> type) {
		if (type == BigDecimal.class) {
			return new BigDecimalTransform();
		}
		if (type == BigInteger.class) {
			return new BigIntegerTransform();
		}
		return null;
	}

	/**
	 * Get java.util type Transformable
	 * 
	 * @param type
	 * @return
	 */
	private static Transform<?> getUtilTransform(Class<?> type) {
		if (type == Date.class) {
			return new DateTransform();
		}
		if (type == Locale.class) {
			return new LocaleTransform();
		}
		if (type == Currency.class) {
			return new CurrencyTransform();
		}
		if (type == Calendar.class) {
			return new CalendarTransform();
		}
		if (type == TimeZone.class) {
			return new TimeZoneTransform();
		}
		return null;
	}

}

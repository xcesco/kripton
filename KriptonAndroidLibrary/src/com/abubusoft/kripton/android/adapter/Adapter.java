package com.abubusoft.kripton.android.adapter;

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

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer for java primitive types and frequently used java types
 * 
 * @author bulldog
 *
 */
public class Adapter {

	// Transformable cache
	private static final Map<Class<?>, SqliteAdapter<?>> cache = new ConcurrentHashMap<Class<?>, SqliteAdapter<?>>();

	public static Object read(Cursor cursor, int columnIndex, Class<?> type) throws Exception {
		SqliteAdapter<?> adapter = lookup(type);

		if (adapter == null) {
			throw new IllegalArgumentException("Transform of " + type + " not supported");
		}
		return adapter.readCursor(cursor, columnIndex);
	}

	@SuppressWarnings("unchecked")
	public static void write(ContentValues content, String columnKey, Object value, Class<?> type) throws Exception {
		@SuppressWarnings("rawtypes")
		SqliteAdapter adapter = lookup(type);

		if (adapter == null) {
			throw new IllegalArgumentException("Transform of " + type + " not supported");
		}
		adapter.writeValue(value, content, columnKey);
	}

	public static boolean isTransformable(Class<?> type) {
		return lookup(type) != null;
	}

	public static boolean isPrimitive(Class<?> type) {
		return isTransformable(type);
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
	public static void register(Class<?> type, SqliteAdapter<?> transform) {
		cache.put(type, transform);
	}

	/**
	 * Get transformer for type
	 * 
	 * @param type
	 * @return
	 */
	public static SqliteAdapter<?> lookup(Class<?> type) {
		SqliteAdapter<?> transform = cache.get(type);

		if (transform != null) {
			return transform;
		}

		transform = getTransform(type);
		if (transform != null) {
			cache.put(type, transform);
		}

		return transform;
	}

	private static SqliteAdapter<?> getTransform(Class<?> type) {

		if (type.isPrimitive()) {
			return getPrimitiveTransform(type);
		}

		if (type.isArray() && type == byte[].class) {
			return new Base64Adapter();
		}

		if (type.isEnum()) {
			return new EnumAdapter(type);
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
			return new UrlAdapter();
		}
		if (name.startsWith("java.sql")) {
			return new TimeAdapter();
		}
		if (type == QName.class) {
			return new QNameAdapter();
		}

		return null;
	}

	// Get Java primitive type Transformable
	private static SqliteAdapter<?> getPrimitiveTransform(Class<?> type) {
		if (type == int.class) {
			return new IntegerAdapter();
		}
		if (type == boolean.class) {
			return new BooleanAdapter();
		}
		if (type == long.class) {
			return new LongAdapter();
		}
		if (type == double.class) {
			return new DoubleAdapter();
		}
		if (type == float.class) {
			return new FloatAdapter();
		}
		if (type == short.class) {
			return new ShortAdapter();
		}
		if (type == byte.class) {
			return new ByteAdapter();
		}
		if (type == char.class) {
			return new CharacterAdapter();
		}
		return null;
	}

	// Get Java primitive wrapping type Transformable
	private static SqliteAdapter<?> getLanguageTransform(Class<?> type) {
		if (type == Boolean.class) {
			return new BooleanAdapter();
		}
		if (type == Integer.class) {
			return new IntegerAdapter();
		}
		if (type == Long.class) {
			return new LongAdapter();
		}
		if (type == Double.class) {
			return new DoubleAdapter();
		}
		if (type == Float.class) {
			return new FloatAdapter();
		}
		if (type == Short.class) {
			return new ShortAdapter();
		}
		if (type == Byte.class) {
			return new ByteAdapter();
		}
		if (type == Character.class) {
			return new CharacterAdapter();
		}
		if (type == String.class) {
			return new StringAdapter();
		}
		return null;
	}

	// Get java.math type Transformable
	private static SqliteAdapter<?> getMathTransform(Class<?> type) {
		if (type == BigDecimal.class) {
			return new BigDecimalAdapter();
		}
		if (type == BigInteger.class) {
			return new BigIntegerAdapter();
		}
		return null;
	}

	// Get java.util type Transformable
	private static SqliteAdapter<?> getUtilTransform(Class<?> type) {
		if (type == Date.class) {
			return new DateAdapter();
		}
		if (type == Locale.class) {
			return new LocaleAdapter();
		}
		if (type == Currency.class) {
			return new CurrencyAdapter();
		}
		if (type == Calendar.class) {
			return new CalendarAdapter();
		}
		if (type == TimeZone.class) {
			return new TimeZoneAdapter();
		}
		return null;
	}

}

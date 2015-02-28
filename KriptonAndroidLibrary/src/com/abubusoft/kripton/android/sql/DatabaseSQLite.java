package com.abubusoft.kripton.android.sql;

import java.lang.reflect.Field;

public class DatabaseSQLite extends AbstractDatabase {

	private static final String TYPE_BLOB = "BLOB";
	private static final String TYPE_TEXT = "TEXT";
	private static final String TYPE_INTEGER = "INTEGER";
	private static final String TYPE_REAL = "REAL";

	private static final long serialVersionUID = -7514759755448534440L;

	public DatabaseSQLite() {
		field2Column.put(Double.class, TYPE_REAL);
		field2Column.put(Double.TYPE, TYPE_REAL);
		field2Column.put(Long.class, TYPE_INTEGER);
		field2Column.put(Long.TYPE, TYPE_INTEGER);
		field2Column.put(Integer.class, TYPE_INTEGER);
		field2Column.put(Integer.TYPE, TYPE_INTEGER);
		field2Column.put(String.class, TYPE_TEXT);
	}

	public String class2columnType(Field field) {
		Class<?> clazz = field.getType();

		if (clazz.isArray()) {
			if (clazz.getComponentType().equals(Byte.TYPE)) {
				return TYPE_BLOB;
			}

		} else {
			if (clazz.isEnum()) {
				return TYPE_TEXT;
			}

			if (field2Column.containsKey(clazz)) {
				return field2Column.get(clazz);
			}
		}

		return null;
	}

}

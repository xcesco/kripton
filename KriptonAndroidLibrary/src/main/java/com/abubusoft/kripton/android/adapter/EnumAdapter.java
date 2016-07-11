package com.abubusoft.kripton.android.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
@SuppressWarnings("rawtypes")
public class EnumAdapter implements SqliteAdapter<Enum> {

	private final Class type;
	private Method valueMethod;
	private Method fromValueMethod;

	private boolean customEnum = true;// custom enum supports value/fromValue methods

	@SuppressWarnings("unchecked")
	public EnumAdapter(Class type) {
		this.type = type;

		customEnum = true;

		try {
			this.valueMethod = type.getDeclaredMethod("value");
			this.fromValueMethod = type.getDeclaredMethod("fromValue", String.class);
		} catch (Exception e) {
			// throw new ConvertException("Fail to get declared method 'value' from enum type " + type.getName(), e);
			customEnum = false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enum readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		
		if (!customEnum) {
			try {
				return Enum.valueOf(type, value);
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else {
			try {
				return (Enum) this.fromValueMethod.invoke(null, cursor.getString(columnIndex));
			} catch (InvocationTargetException e) {
				if (e.getTargetException().getClass() == java.lang.IllegalArgumentException.class) {
					return null;
				} else {
					throw new IllegalArgumentException("fail to convert string value : " + cursor.getString(columnIndex) + " to enum type : " + type.getName(), e);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("fail to convert string value : " + cursor.getString(columnIndex) + " to enum type : " + type.getName(), e);
			}
		}
	}

	@Override
	public void writeValue(Enum value, ContentValues content, String columnKey) throws Exception {
		if (!customEnum) {
			content.put(columnKey, value.name());
		} else {
			try {
				content.put(columnKey, (String)valueMethod.invoke(value));
			} catch (Exception e) {
				throw new IllegalArgumentException("fail to convert enum value : " + value.toString() + " to string", e);
			}
		}
		
	}
}
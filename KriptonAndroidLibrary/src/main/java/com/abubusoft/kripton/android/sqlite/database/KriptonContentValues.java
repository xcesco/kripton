/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abubusoft.kripton.android.sqlite.database;

import java.util.ArrayList;
import java.util.Set;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public final class KriptonContentValues {
	enum ParamType {
		BOOLEAN, BYTE_ARRAY, DOUBLE, FLOAT, INTEGER, LONG, SHORT, STRING, NULL, BYTE
	};

	public static final String TAG = "ContentValues";

	private ContentValues values;

	private ArrayList<ParamType> valueType;

	private ArrayList<Object> args;
	
	private ArrayList<String> whereArgs;

	public ArrayList<String> whereArgs() {
		return whereArgs;
	}

	/**
	 * Creates an empty set of values using the default initial size
	 */
	public KriptonContentValues() {
		// Choosing a default size of 8 based on analysis of typical
		// consumption by applications.
		values = new ContentValues();
		valueType = new ArrayList<>(8);
		args = new ArrayList<>(4);
		whereArgs=new ArrayList<>(4);
	}
		

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, String value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.STRING);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Byte value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.BYTE);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Short value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.SHORT);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Integer value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.INTEGER);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Long value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.LONG);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Float value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.FLOAT);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Double value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.DOUBLE);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, Boolean value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.BOOLEAN);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, byte[] value) {
		values.put(key, value);
		args.add(value);
		valueType.add(ParamType.BYTE_ARRAY);
	}

	/**
	 * Adds a null value to the set.
	 *
	 * @param key
	 *            the name of the value to make null
	 */
	public void putNull(String key) {
		values.putNull(key);
		args.add(null);
		valueType.add(ParamType.NULL);
	}

	public void addWhereArgs(String value) {
		whereArgs.add(value);
		valueType.add(ParamType.STRING);
	}

	public void bind(SQLiteStatement statement) {
		int index = 1;
		
		statement.clearBindings();
		
		for (Object value : args) {
			switch (valueType.get(index)) {
			case BOOLEAN:
			case BYTE:
			case SHORT:
			case INTEGER:
			case LONG:			
				statement.bindLong(index, (long)value);
				break;				
			case BYTE_ARRAY:
				statement.bindBlob(index, (byte[]) value);
				break;
			case FLOAT:
			case DOUBLE:
				statement.bindDouble(index, (double)value);
				break;			
			case NULL:				
				statement.bindNull(index);
				break;
			case STRING:
				statement.bindString(index, (String)value);
				break;
			}
			index++;
		}		
		
		for (int i=0; i<whereArgs.size();i++, index++) {
			statement.bindString(index, whereArgs.get(i));
		}
	}

	/**
	 * Returns the number of values.
	 *
	 * @return the number of values
	 */
	public int size() {
		return values.size();
	}

	/**
	 * Remove a single value.
	 *
	 * @param key
	 *            the name of the value to remove
	 */
	public void remove(String key) {
		values.remove(key);
	}

	/**
	 * Removes all values.
	 */
	public void clear() {
		values.clear();
		valueType.clear();
		args.clear();
		whereArgs.clear();
		
	}

	/**
	 * Returns true if this object has the named value.
	 *
	 * @param key
	 *            the value to check for
	 * @return {@code true} if the value is present, {@code false} otherwise
	 */
	public boolean containsKey(String key) {
		return values.containsKey(key);
	}

	/**
	 * Gets a value. Valid value types are {@link String}, {@link Boolean}, and
	 * {@link Number} implementations.
	 *
	 * @param key
	 *            the value to get
	 * @return the data for the value
	 */
	public Object get(String key) {
		return values.get(key);
	}

	/**
	 * Gets a value and converts it to a String.
	 *
	 * @param key
	 *            the value to get
	 * @return the String for the value
	 */
	public String getAsString(String key) {
		Object value = values.get(key);
		return value != null ? value.toString() : null;
	}

	/**
	 * Gets a value and converts it to a Long.
	 *
	 * @param key
	 *            the value to get
	 * @return the Long value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Long getAsLong(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).longValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Long.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Long value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Long: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to an Integer.
	 *
	 * @param key
	 *            the value to get
	 * @return the Integer value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Integer getAsInteger(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).intValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Integer.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Integer value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Integer: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Short.
	 *
	 * @param key
	 *            the value to get
	 * @return the Short value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Short getAsShort(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).shortValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Short.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Short value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Short: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Byte.
	 *
	 * @param key
	 *            the value to get
	 * @return the Byte value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Byte getAsByte(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).byteValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Byte.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Byte value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Byte: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Double.
	 *
	 * @param key
	 *            the value to get
	 * @return the Double value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Double getAsDouble(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).doubleValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Double.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Double value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Double: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Float.
	 *
	 * @param key
	 *            the value to get
	 * @return the Float value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Float getAsFloat(String key) {
		Object value = values.get(key);
		try {
			return value != null ? ((Number) value).floatValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Float.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					Log.e(TAG, "Cannot parse Float value for " + value + " at key " + key);
					return null;
				}
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Float: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Boolean.
	 *
	 * @param key
	 *            the value to get
	 * @return the Boolean value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Boolean getAsBoolean(String key) {
		Object value = values.get(key);
		try {
			return (Boolean) value;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				return Boolean.valueOf(value.toString());
			} else if (value instanceof Number) {
				return ((Number) value).intValue() != 0;
			} else {
				Log.e(TAG, "Cannot cast value for " + key + " to a Boolean: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value that is a byte array. Note that this method will not convert
	 * any other types to byte arrays.
	 *
	 * @param key
	 *            the value to get
	 * @return the byte[] value, or null is the value is missing or not a byte[]
	 */
	public byte[] getAsByteArray(String key) {
		Object value = values.get(key);
		if (value instanceof byte[]) {
			return (byte[]) value;
		} else {
			return null;
		}
	}

	public Set<String> keySet() {
		return values.keySet();
	}

	public ContentValues values() {
		return values;
	}

	public String[] whereArgsAsArray() {
		return whereArgs.toArray(new String[whereArgs.size()]);
	}

	public void clear(ContentValues values) {		
		clear();
		this.values.putAll(values);
		
	}


}

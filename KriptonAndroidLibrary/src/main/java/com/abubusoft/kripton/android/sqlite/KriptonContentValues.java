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

package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
import java.util.Set;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

public final class KriptonContentValues {
	enum ParamType {
		BOOLEAN, BYTE_ARRAY, DOUBLE, FLOAT, INTEGER, LONG, SHORT, STRING, NULL, BYTE
	};

	private ContentValues values;

	private ArrayList<ParamType> valueType;
	
	private ArrayList<String> names;

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
		names = new ArrayList<>(8);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
		names.add(key);
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
			switch (valueType.get(index-1)) {
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
		names.clear();
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
		return values.getAsString(key);		
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
		return values.getAsLong(key);		
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
		return values.getAsInteger(key);		
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
		return values.getAsShort(key);		
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
		return values.getAsByte(key);		
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
		return values.getAsDouble(key);		
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
		return values.getAsFloat(key);		
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
		return values.getAsBoolean(key);		
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
		return values.getAsByteArray(key);		
	}

	public Set<String> keySet() {
		return values.keySet();
	}
	
	public String keyList() {
		String separator="";
		StringBuilder buffer=new StringBuilder();
		
		for (String item: names) {
			buffer.append(separator+item);
			separator=", ";
		}
		
		return buffer.toString();
	}
	
	public String keyValueList() {
		String separator="";
		StringBuilder buffer=new StringBuilder();
		
		for (int i=0; i<names.size();i++) {
			buffer.append(separator+"?");
			separator=", ";
		}
		
		return buffer.toString();
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

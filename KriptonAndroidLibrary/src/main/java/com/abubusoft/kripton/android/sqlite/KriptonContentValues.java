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
import java.util.List;

import com.abubusoft.kripton.common.Triple;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

public final class KriptonContentValues {
	public enum ParamType {
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
		// values = new ContentValues();
		valueType = new ArrayList<>(8);
		names = new ArrayList<>(8);
		args = new ArrayList<>(4);
		whereArgs = new ArrayList<>(4);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindString(compiledStatementBindIndex++, (String) value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(String value) {
		compiledStatement.bindString(compiledStatementBindIndex++, value);

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
		if (this.compiledStatement != null) {
			this.compiledStatement.bindLong(compiledStatementBindIndex++, value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(Byte value) {
		this.compiledStatement.bindLong(compiledStatementBindIndex++, value);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindLong(compiledStatementBindIndex++, (short) value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(Short value) {
		compiledStatement.bindLong(compiledStatementBindIndex++, (short) value);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindLong(compiledStatementBindIndex++, (int) value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(Integer value) {
		compiledStatement.bindLong(compiledStatementBindIndex++, value);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindLong(compiledStatementBindIndex++, (long) value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(Long value) {
		compiledStatement.bindLong(compiledStatementBindIndex++, (long) value);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindDouble(compiledStatementBindIndex++, (float) value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
	public void put(Float value) {
		compiledStatement.bindDouble(compiledStatementBindIndex++, (float) value);

	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Double value) {
		this.compiledStatement.bindDouble(compiledStatementBindIndex++, value);
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
		if (this.compiledStatement != null) {
			this.compiledStatement.bindDouble(compiledStatementBindIndex++, value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
		if (this.compiledStatement != null) {
			this.compiledStatement.bindLong(compiledStatementBindIndex++, (((Boolean) value) == true ? 1 : 0));
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindBlob(compiledStatementBindIndex++, value);
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		// values.put(key, value);
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
		if (this.compiledStatement != null) {
			compiledStatement.bindNull(compiledStatementBindIndex++);
		} else if (values != null) {
			values.putNull(key);
			return;
		}

		names.add(key);
		// values.putNull(key);
		args.add(null);
		valueType.add(ParamType.NULL);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Boolean value) {
		this.compiledStatement.bindLong(compiledStatementBindIndex++, (value == true ? 1 : 0));
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(byte[] value) {
		compiledStatement.bindBlob(compiledStatementBindIndex++, value);
	}

	/**
	 * Adds a null value to the set.
	 *
	 * @param key
	 *            the name of the value to make null
	 */
	public void putNull() {
		compiledStatement.bindNull(compiledStatementBindIndex++);
	}
	// ---

	public void addWhereArgs(String value) {
		if (this.compiledStatement != null) {
			compiledStatement.bindString(compiledStatementBindIndex++,value);
		}

		whereArgs.add(value);
		valueType.add(ParamType.STRING);
	}

	public void bind(SQLiteStatement statement) {
		if (this.compiledStatement != null) {
			// already binded
			return;
		}

		int index = 1;

		statement.clearBindings();
		Object value;

		for (int j = 0; j < args.size(); j++) {
			value = args.get(j);
			switch (valueType.get(index - 1)) {
			case BOOLEAN:
				statement.bindLong(index, (long) (((Boolean) value) == true ? 1 : 0));
				break;
			case BYTE:
				statement.bindLong(index, (byte) value);
				break;
			case SHORT:
				statement.bindLong(index, (short) value);
				break;
			case INTEGER:
				statement.bindLong(index, (int) value);
				break;
			case LONG:
				statement.bindLong(index, (long) value);
				break;
			case BYTE_ARRAY:
				statement.bindBlob(index, (byte[]) value);
				break;
			case FLOAT:
				statement.bindDouble(index, (float) value);
				break;
			case DOUBLE:
				statement.bindDouble(index, (double) value);
				break;
			case NULL:
				statement.bindNull(index);
				break;
			case STRING:
				statement.bindString(index, (String) value);
				break;
			}
			index++;
		}

		for (int i = 0; i < whereArgs.size(); i++, index++) {
			statement.bindString(index, whereArgs.get(i));
		}
	}

	/**
	 * Returns the number of values.
	 *
	 * @return the number of values
	 */
	public int size() {
		return names.size();
	}

	/**
	 * Removes all values.
	 */
	public void clear() {
		names.clear();
		values = null;
		valueType.clear();
		args.clear();
		whereArgs.clear();
		compiledStatement=null;
		compiledStatementBindIndex=1;

	}
	
	public void clearBindIndex() {
		compiledStatementBindIndex=1;
	}

	protected Triple<String, Object, ParamType> triple = new Triple<>();

	private SQLiteStatement compiledStatement;

	private int compiledStatementBindIndex;

	public Triple<String, Object, ParamType> get(int index) {
		triple.value0 = names.get(index);
		triple.value1 = args.get(index);
		triple.value2 = valueType.get(index);
		return triple;
	}

	public String keyList() {
		String separator = "";
		StringBuilder buffer = new StringBuilder();
		String item;

		for (int i = 0; i < names.size(); i++) {
			item = names.get(i);
			buffer.append(separator + item);
			separator = ", ";
		}

		return buffer.toString();
	}

	public String keyValueList() {
		String separator = "";
		StringBuilder buffer = new StringBuilder();

		for (int i = 0; i < names.size(); i++) {
			buffer.append(separator + "?");
			separator = ", ";
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
		this.values = values;
	}

	public List<String> keys() {
		return names;
	}

	public void clear(SQLiteStatement compiledStatement) {
		clear();
		this.compiledStatement = compiledStatement;		
		if (compiledStatement != null) {
			this.compiledStatement.clearBindings();
		} 
		
	}

}

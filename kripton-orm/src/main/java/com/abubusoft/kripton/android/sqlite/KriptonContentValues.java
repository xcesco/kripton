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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.Triple;

import android.content.ContentValues;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * The Class KriptonContentValues.
 */
public final class KriptonContentValues {

	/**
	 * The Enum ParamType.
	 */
	public enum ParamType {

		/** The boolean. */
		BOOLEAN,
		/** The byte array. */
		BYTE_ARRAY,
		/** The double. */
		DOUBLE,
		/** The float. */
		FLOAT,
		/** The integer. */
		INTEGER,
		/** The long. */
		LONG,
		/** The short. */
		SHORT,
		/** The string. */
		STRING,
		/** The null. */
		NULL,
		/** The byte. */
		BYTE,
		/** The character. */
		CHARACTER
	};

	/** The values. */
	private ContentValues values;

	/** The value type. */
	private ArrayList<ParamType> valueType;

	/** The names. */
	private ArrayList<String> names;

	/** The args. */
	private ArrayList<Object> args;

	/** The where args. */
	private ArrayList<String> whereArgs;

	/**
	 * Where args.
	 *
	 * @return the array list
	 */
	public ArrayList<String> whereArgs() {
		return whereArgs;
	}

	/**
	 * Creates an empty set of values using the default initial size.
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindString(compiledStatementBindIndex++, (String) value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindString(compiledStatementBindIndex++, value);
		}

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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				this.compiledStatement.bindLong(compiledStatementBindIndex++, value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Byte value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			this.compiledStatement.bindLong(compiledStatementBindIndex++, value);
		}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindLong(compiledStatementBindIndex++, (short) value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Short value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindLong(compiledStatementBindIndex++, (short) value);
		}
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param key
	 *            the name of the value to put
	 * @param value
	 *            the data for the value to put
	 */
	public void put(String key, BigDecimal value) {
		if (this.compiledStatement != null) {
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindString(compiledStatementBindIndex++, value.toPlainString());
			}
		} else if (values != null) {
			values.put(key, value.toPlainString());
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
	public void put(String key, BigInteger value) {
		if (this.compiledStatement != null) {
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindString(compiledStatementBindIndex++, value.toString());
			}
		} else if (values != null) {
			values.put(key, value.toString());
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
	public void put(String key, Character value) {
		if (this.compiledStatement != null) {
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindLong(compiledStatementBindIndex++, Character.getNumericValue(value));
			}
		} else if (values != null) {
			values.put(key, Character.getNumericValue(value));
			return;
		}

		names.add(key);
		// values.put(key, value);
		args.add(value);
		valueType.add(ParamType.CHARACTER);
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindLong(compiledStatementBindIndex++, (int) value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Integer value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindLong(compiledStatementBindIndex++, value);
		}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindLong(compiledStatementBindIndex++, (long) value);
			}
		} else if (values != null) {
			values.put(key, value);
			return;
		}

		names.add(key);
		args.add(value);
		valueType.add(ParamType.LONG);
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Long value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindLong(compiledStatementBindIndex++, (long) value);
		}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				this.compiledStatement.bindDouble(compiledStatementBindIndex++, (float) value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Float value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindDouble(compiledStatementBindIndex++, (float) value);
		}
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Double value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			this.compiledStatement.bindDouble(compiledStatementBindIndex++, value);
		}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				this.compiledStatement.bindDouble(compiledStatementBindIndex++, value);
			}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				this.compiledStatement.bindLong(compiledStatementBindIndex++, (((Boolean) value) == true ? 1 : 0));
			}
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
			if (value == null) {
				this.compiledStatement.bindNull(compiledStatementBindIndex++);
			} else {
				compiledStatement.bindBlob(compiledStatementBindIndex++, value);
			}
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
	 * @param value
	 *            the data for the value to put
	 */
	public void put(Boolean value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			this.compiledStatement.bindLong(compiledStatementBindIndex++, (value == true ? 1 : 0));
		}
	}

	/**
	 * Adds a value to the set.
	 *
	 * @param value
	 *            the data for the value to put
	 */
	public void put(byte[] value) {
		if (value == null) {
			this.compiledStatement.bindNull(compiledStatementBindIndex++);
		} else {
			compiledStatement.bindBlob(compiledStatementBindIndex++, value);
		}
	}

	/**
	 * Adds a null value to the set.
	 */
	public void putNull() {
		compiledStatement.bindNull(compiledStatementBindIndex++);
	}
	// ---

	/**
	 * Adds the where args.
	 *
	 * @param value
	 *            the value
	 */
	public void addWhereArgs(String value) {
		if (this.compiledStatement != null) {
			compiledStatement.bindString(compiledStatementBindIndex++, value);
		}

		whereArgs.add(value);
		valueType.add(ParamType.STRING);
	}

	/**
	 * Bind.
	 *
	 * @param statement
	 *            the statement
	 */
	public void bind(SupportSQLiteStatement statement) {
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
			case CHARACTER:
				statement.bindLong(index, Character.getNumericValue((char) value));
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
		compiledStatement = null;
		compiledStatementBindIndex = 1;

	}

	/**
	 * Clear bind index.
	 */
	public void clearBindIndex() {
		compiledStatementBindIndex = 1;
	}

	/** The triple. */
	protected Triple<String, Object, ParamType> triple = new Triple<>();

	/** The compiled statement. */
	private SupportSQLiteStatement compiledStatement;

	/** The compiled statement bind index. */
	private int compiledStatementBindIndex;

	/**
	 * Gets the.
	 *
	 * @param index
	 *            the index
	 * @return the triple
	 */
	public Triple<String, Object, ParamType> get(int index) {
		triple.value0 = names.get(index);
		triple.value1 = args.get(index);
		triple.value2 = valueType.get(index);
		return triple;
	}

	/**
	 * Key list.
	 *
	 * @return the string
	 */
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

	/**
	 * Key value list.
	 *
	 * @return the string
	 */
	public String keyValueList() {
		String separator = "";
		StringBuilder buffer = new StringBuilder();

		for (int i = 0; i < names.size(); i++) {
			buffer.append(separator + "?");
			separator = ", ";
		}

		return buffer.toString();
	}

	/**
	 * Values.
	 *
	 * @return the content values
	 */
	public ContentValues values() {
		return values;
	}

	/**
	 * Where args as array.
	 *
	 * @return the string[]
	 */
	public String[] whereArgsAsArray() {
		return whereArgs.toArray(new String[whereArgs.size()]);
	}

	/**
	 * Clear.
	 *
	 * @param values
	 *            the values
	 */
	public void clear(ContentValues values) {
		clear();
		this.values = values;
	}

	/**
	 * Keys.
	 *
	 * @return the list
	 */
	public List<String> keys() {
		return names;
	}

	/**
	 * Clear.
	 *
	 * @param compiledStatement
	 *            the compiled statement
	 */
	public void clear(SupportSQLiteStatement compiledStatement) {
		clear();
		this.compiledStatement = compiledStatement;
		if (compiledStatement != null) {
			this.compiledStatement.clearBindings();
		}

	}

}

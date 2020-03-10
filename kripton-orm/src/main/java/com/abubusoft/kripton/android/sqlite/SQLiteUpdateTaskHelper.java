/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.commons.IOUtils;
import com.abubusoft.kripton.common.StringUtils;

import android.content.Context;
import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * SQLiteUpdateTask Helper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class SQLiteUpdateTaskHelper {

	/**
	 * The Enum QueryType.
	 */
	public enum QueryType {

		/** The table. */
		TABLE,
		/** The index. */
		INDEX
	};

	/**
	 * The listener interface for receiving onResult events. The class that is
	 * interested in processing a onResult event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addOnResultListener</code> method. When the
	 * onResult event occurs, that object's appropriate method is invoked.
	 *
	 * 
	 */
	public interface OnResultListener {

		/**
		 * On row.
		 *
		 * @param db
		 *            the db
		 * @param name
		 *            the name
		 * @param sql
		 *            the sql
		 */
		void onRow(SupportSQLiteDatabase db, String name, String sql);
	}

	/**
	 * Query.
	 *
	 * @param db
	 *            the db
	 * @param conditions
	 *            the conditions
	 * @param type
	 *            the type
	 * @param listener
	 *            the listener
	 */
	static void query(SupportSQLiteDatabase db, String conditions, QueryType type, OnResultListener listener) {
		String query = String.format(
				"SELECT name, sql FROM sqlite_master WHERE type='%s'and name!='sqlite_sequence' and name!='android_metadata'%s",
				type.toString().toLowerCase(), StringUtils.hasText(conditions) ? " AND " + conditions : "");
		try (Cursor cursor = db.query(query, null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				int index1 = cursor.getColumnIndex("sql");
				do {
					String name=cursor.getString(index0);
					String sql=cursor.getString(index1);
					listener.onRow(db, name, sql);
				} while (cursor.moveToNext());
			}
		}
	}

	/**
	 * Drop all entity of particular type (table or index). If prefix is
	 * specified, the drop operation is applied only to entity with prefix.
	 *
	 * @param db
	 *            the db
	 * @param type
	 *            the type
	 * @param prefix
	 *            the prefix
	 */
	private static void drop(SupportSQLiteDatabase db, final QueryType type, String prefix) {
		String dropSQL = StringUtils.hasText(prefix) ? "name like '" + prefix + "' || '%'" : null;

		query(db, dropSQL, type, new OnResultListener() {

			@Override
			public void onRow(SupportSQLiteDatabase db, String name, String sql) {
				String drop = "DROP " + type.toString().toUpperCase() + " " + name;
				Logger.info(drop);
				db.execSQL(drop);

			}
		});
	}

	/**
	 * Retrieve all table as a Map of (name, sql).
	 *
	 * @param db
	 *            the db
	 * @return the all tables
	 */
	public static Map<String, String> getAllTables(SupportSQLiteDatabase db) {
		final Map<String, String> result = new LinkedHashMap<>();

		query(db, null, QueryType.TABLE, new OnResultListener() {

			@Override
			public void onRow(SupportSQLiteDatabase db, String name, String sql) {
				if (StringUtils.hasText(sql)) {
					result.put(name, StringUtils.nvl(sql).trim());
				}
			}
		});

		return result;
	}

	/**
	 * Add to all table a specifix prefix.
	 *
	 * @param db
	 *            the db
	 * @param prefix
	 *            the prefix
	 */
	public static void renameTablesWithPrefix(SupportSQLiteDatabase db, final String prefix) {
		Logger.info("MASSIVE TABLE RENAME OPERATION: ADD PREFIX " + prefix);
		query(db, null, QueryType.TABLE, new OnResultListener() {

			@Override
			public void onRow(SupportSQLiteDatabase db, String name, String sql) {
				sql = String.format("ALTER TABLE %s RENAME TO %s%s;", name, prefix, name);
				Logger.info(sql);
				db.execSQL(sql);

			}
		});
	}

	/**
	 * Drop all table with specific prefix.
	 *
	 * @param db
	 *            the db
	 * @param prefix
	 *            the prefix
	 */
	public static void dropTablesWithPrefix(SupportSQLiteDatabase db, String prefix) {
		Logger.info("MASSIVE TABLE DROP OPERATION%s", StringUtils.ifNotEmptyAppend(prefix, " WITH PREFIX "));
		drop(db, QueryType.TABLE, prefix);
	}

	/**
	 * Drop tables and indices.
	 *
	 * @param db
	 *            the db
	 */
	public static void dropTablesAndIndices(SupportSQLiteDatabase db) {
		drop(db, QueryType.INDEX, null);
		drop(db, QueryType.TABLE, null);
	}

	/**
	 * Retrieve all indexes as a Map of (name, sql).
	 *
	 * @param db
	 *            the db
	 * @return the all indexes
	 */
	public static Map<String, String> getAllIndexes(SupportSQLiteDatabase db) {
		final Map<String, String> result = new LinkedHashMap<>();

		query(db, null, QueryType.INDEX, new OnResultListener() {

			@Override
			public void onRow(SupportSQLiteDatabase db, String name, String sql) {
				if (StringUtils.hasText(sql)) {
					result.put(name, StringUtils.nvl(sql).trim());
				}
			}
		});

		return result;
	}

	/**
	 * Execute SQL.
	 *
	 * @param database
	 *            the database
	 * @param context
	 *            the context
	 * @param rawResourceId
	 *            the raw resource id
	 */
	public static void executeSQL(final SupportSQLiteDatabase database, Context context, int rawResourceId) {
		String[] c = IOUtils.readTextFile(context, rawResourceId).split(";");
		List<String> commands = Arrays.asList(c);
		executeSQL(database, commands);
	}

	/**
	 * Read SQL from file.
	 *
	 * @param fileName
	 *            the file name
	 * @return the list
	 */
	public static List<String> readSQLFromFile(String fileName) {
		try {
			return readSQLFromFile(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Read SQL from file.
	 *
	 * @param fileInputStream
	 *            the file input stream
	 * @return the list
	 */
	public static List<String> readSQLFromFile(InputStream fileInputStream) {
		String content = IOUtils.readText(fileInputStream);

		// remove comments
		content = content.replaceAll("\\/\\*.*\\*\\/", "");
		content = content.replaceAll("--.*\n", "");
		content = content.replaceAll("\n", "");

		String[] c = content.split(";");
		List<String> commands = new ArrayList<>();
		for (String i : c) {
			if (StringUtils.hasText(i)) {
				commands.add(i.trim());
			}
		}

		return commands;

	}

	/**
	 * Execute SQL.
	 *
	 * @param database
	 *            the database
	 * @param fileInputStream
	 *            the file input stream
	 */
	public static void executeSQL(final SupportSQLiteDatabase database, InputStream fileInputStream) {
		List<String> commands = readSQLFromFile(fileInputStream);
		executeSQL(database, commands);
	}

	/**
	 * Execute SQL.
	 *
	 * @param database
	 *            the database
	 * @param commands
	 *            the commands
	 */
	public static void executeSQL(final SupportSQLiteDatabase database, List<String> commands) {
		for (String command : commands) {
			executeSQL(database, command);
		}
		// commands.forEach(command -> {
		// executeSQL(database, command);
		// });
	}

	/**
	 * Execute SQL.
	 *
	 * @param database
	 *            the database
	 * @param command
	 *            the command
	 */
	public static void executeSQL(final SupportSQLiteDatabase database, String command) {
		// remove comments
		command = command.replaceAll("\\/\\*.*\\*\\/", "");
		command = command.replaceAll("--.*$", "");

		if (command.trim().length() > 0) {
			Logger.info(command);
			database.execSQL(command);
		}

	}

}

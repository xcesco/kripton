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
import com.abubusoft.kripton.android.commons.IOUtils;
import com.abubusoft.kripton.common.StringUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SQLiteUpdateTaskHelper {

	public enum QueryType {
		TABLE, INDEX
	};

	public interface OnResultListener {
		void onRow(SQLiteDatabase db, String name, String sql);
	}

	static void query(SQLiteDatabase db, String conditions, QueryType type, OnResultListener listener) {
		String query = String.format(
				"SELECT name, sql FROM sqlite_master WHERE type='%s'and name!='sqlite_sequence' and name!='android_metadata'%s",
				type.toString().toLowerCase(), StringUtils.hasText(conditions) ? " AND " + conditions : "");
		try (Cursor cursor = db.rawQuery(query, null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				int index1 = cursor.getColumnIndex("sql");
				do {
					listener.onRow(db, cursor.getString(index0), cursor.getString(index1));
				} while (cursor.moveToNext());
			}
		}
	}

	/**
	 * Drop all entity of particular type (table or index). If prefix is
	 * specified, the drop operation is applied only to entity with prefix.
	 * 
	 * @param db
	 * @param type
	 * @param prefix
	 */
	private static void drop(SQLiteDatabase db, final QueryType type, String prefix) {
		String dropSQL = StringUtils.hasText(prefix) ? "name like '" + prefix + "' || '%'" : null;

		query(db, dropSQL, type, new OnResultListener() {

			@Override
			public void onRow(SQLiteDatabase db, String name, String sql) {
				String drop = "drop " + type.toString().toLowerCase() + " " + name;
				Logger.info(drop);
				db.execSQL(drop);

			}
		});
	}

	/**
	 * Retrieve all table as a Map of <name, sql>
	 * 
	 * @param db
	 * @return
	 */
	public static Map<String, String> getAllTables(SQLiteDatabase db) {
		final Map<String, String> result = new LinkedHashMap<>();

		query(db, null, QueryType.TABLE, new OnResultListener() {

			@Override
			public void onRow(SQLiteDatabase db, String name, String sql) {
				if (StringUtils.hasText(sql)) {
					result.put(name, StringUtils.nvl(sql).trim());
				}				
			}
		});

		return result;
	}

	/**
	 * Add to all table a specifix prefix
	 * 
	 * @param db
	 * @param prefix
	 */
	public static void renameTablesWithPrefix(SQLiteDatabase db, final String prefix) {
		Logger.info("MASSIVE TABLE RENAME OPERATION: ADD PREFIX " + prefix);
		query(db, null, QueryType.TABLE, new OnResultListener() {

			@Override
			public void onRow(SQLiteDatabase db, String name, String sql) {
				sql = String.format("ALTER TABLE %s RENAME TO %s%s;", name, prefix, name);
				Logger.info(sql);
				db.execSQL(sql);

			}
		});
	}

	/**
	 * Drop all table with specific prefix
	 * 
	 * @param db
	 * @param prefix
	 */
	public static void dropTablesWithPrefix(SQLiteDatabase db, String prefix) {
		Logger.info("MASSIVE TABLE DROP OPERATION%s", StringUtils.ifNotEmptyAppend(prefix, " WITH PREFIX "));
		drop(db, QueryType.TABLE, prefix);
	}

	public static void dropTablesAndIndices(SQLiteDatabase db) {
		drop(db, QueryType.INDEX, null);
		drop(db, QueryType.TABLE, null);
	}

	/**
	 * 
	 * Retrieve all indexes as a Map of <name, sql>
	 * 
	 * @param db
	 * @return
	 */
	public static Map<String, String> getAllIndexes(SQLiteDatabase db) {
		final Map<String, String> result = new LinkedHashMap<>();

		query(db, null, QueryType.INDEX, new OnResultListener() {

			@Override
			public void onRow(SQLiteDatabase db, String name, String sql) {
				if (StringUtils.hasText(sql)) {
					result.put(name, StringUtils.nvl(sql).trim());
				}
			}
		});

		return result;
	}

	public static void executeSQL(final SQLiteDatabase database, Context context,  int rawResourceId) {
		String[] c = IOUtils.readTextFile(context, rawResourceId).split(";");
		List<String> commands = Arrays.asList(c);
		executeSQL(database, commands);
	}
	
	public static List<String> readSQLFromFile(String fileName) {
		try {
			return readSQLFromFile(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

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

	public static void executeSQL(final SQLiteDatabase database, InputStream fileInputStream) {
		List<String> commands = readSQLFromFile(fileInputStream);
		executeSQL(database, commands);
	}

	public static void executeSQL(final SQLiteDatabase database, List<String> commands) {
		for (String command : commands) {
			executeSQL(database, command);
		}
		// commands.forEach(command -> {
		// executeSQL(database, command);
		// });
	}

	public static void executeSQL(final SQLiteDatabase database, String command) {
		// remove comments
		command = command.replaceAll("\\/\\*.*\\*\\/", "");
		command = command.replaceAll("--.*$", "");

		if (command.trim().length() > 0) {
			Logger.info(command);
			database.execSQL(command);
		}

	}

}

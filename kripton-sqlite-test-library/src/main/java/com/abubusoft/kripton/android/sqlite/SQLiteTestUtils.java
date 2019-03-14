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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper.OnResultListener;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper.QueryType;
import com.abubusoft.kripton.android.sqlite.commons.IOUtils;
import com.abubusoft.kripton.android.sqlite.internals.MigrationSQLChecker;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * Utility class containing useful methods to:
 * </p>
 * 
 * <ul>
 * <li>clear test database</li>
 * <li>massive table renaming</li>
 * <li>massive table drops</li>
 * <li>retrieve table and index lists</li>
 * <li>execute SQL statements</li>
 * <li>schema verification</li>
 * 
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class SQLiteTestUtils {

	/**
	 * Reset instance.
	 *
	 * @param classDataSource
	 *            the class data source
	 */
	public static void resetDataSourceInstance(Class<? extends AbstractDataSource> classDataSource) {
		Field threadLocalField;
		try {
			threadLocalField = classDataSource.getDeclaredField("instance");
			threadLocalField.setAccessible(true);

			threadLocalField.set(null, null);
			threadLocalField.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

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
	private static void query(SQLiteDatabase db, String conditions, QueryType type, OnResultListener listener) {
		SQLiteUpdateTaskHelper.query(db, conditions, type, listener);
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
	private static void drop(SQLiteDatabase db, final QueryType type, String prefix) {
		String dropSQL = StringUtils.hasText(prefix) ? "name like '" + prefix + "' || '%'" : null;

		query(db, dropSQL, type, new OnResultListener() {

			@Override
			public void onRow(SQLiteDatabase db, String name, String sql) {
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
	public static Map<String, String> getAllTables(SQLiteDatabase db) {
		return SQLiteUpdateTaskHelper.getAllTables(db);
	}

	/**
	 * Add to all schema's table a specifix prefix.
	 *
	 * @param db
	 *            the db
	 * @param prefix
	 *            the prefix
	 */
	public static void renameAllTablesWithPrefix(SQLiteDatabase db, final String prefix) {
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
	 * Drop all table with specific prefix.
	 *
	 * @param db
	 *            the db
	 * @param prefix
	 *            the prefix
	 */
	public static void dropTablesWithPrefix(SQLiteDatabase db, String prefix) {
		Logger.info("MASSIVE TABLE DROP OPERATION%s", StringUtils.ifNotEmptyAppend(prefix, " WITH PREFIX "));
		drop(db, QueryType.TABLE, prefix);
	}

	/**
	 * Drop tables and indices.
	 *
	 * @param db
	 *            the db
	 */
	public static void dropTablesAndIndices(SQLiteDatabase db) {
		drop(db, QueryType.INDEX, null);
		drop(db, QueryType.TABLE, null);
	}

	/**
	 * Retrieve all indexes as a Map of (name, sql).
	 *
	 * @param db
	 *            the db
	 * @return indexes
	 */
	public static Map<String, String> getAllIndexes(SQLiteDatabase db) {
		return SQLiteUpdateTaskHelper.getAllIndexes(db);
	}

	/**
	 * Execute SQL contained in raw resource.
	 *
	 * @param database
	 *            the database
	 * @param context
	 *            the context
	 * @param rawResourceId
	 *            the raw resource id
	 */
	public static void executeSQL(final SQLiteDatabase database, Context context, int rawResourceId) {
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
	 * Execute SQL from file.
	 *
	 * @param database the database
	 * @param fileName the sql definition file
	 */
	public static void executeSQLFromFile(SQLiteDatabase database, String fileName) {
		List<String> executionList = readSQLFromFile(fileName);
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
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

		final String buffer = content;

		final List<String> executionList = new ArrayList<>();
		MigrationSQLChecker.getInstance().analyze(content, new JqlBaseListener() {
			public void enterSql_stmt(Sql_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex();
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = buffer.substring(start, stop).trim();

				executionList.add(statement);
			};
		});
		return executionList;
		/*
		 * String[] c = content.split(";"); List<String> commands = new
		 * ArrayList<>(); for (String i : c) { if (StringUtils.hasText(i)) {
		 * commands.add(i.trim()); } }
		 * 
		 * return commands;
		 */

	}

	/**
	 * Execute SQL.
	 *
	 * @param database
	 *            the database
	 * @param fileInputStream
	 *            the file input stream
	 */
	public static void executeSQL(final SQLiteDatabase database, InputStream fileInputStream) {
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
	public static void executeSQL(final SQLiteDatabase database, List<String> commands) {
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
	public static void executeSQL(final SQLiteDatabase database, String command) {
		// remove comments
		command = command.replaceAll("\\/\\*.*\\*\\/", "");
		command = command.replaceAll("--.*$", "");

		if (command.trim().length() > 0) {
			Logger.info(command);
			database.execSQL(command);
		}

	}

	/**
	 * Verify schema.
	 *
	 * @param database
	 *            the database
	 * @param inputStream
	 *            the input stream
	 */
	public static void verifySchema(SQLiteDatabase database, InputStream inputStream) {
		List<String> ddl = extractCommands(database, inputStream);
		verifySchemaInternal(database, ddl);
	}

	/**
	 * Verify schema.
	 *
	 * @param <H>
	 *            the generic type
	 * @param dataSource
	 *            the data source
	 * @param inputStream
	 *            the input stream
	 */
	public static <H extends AbstractDataSource> void verifySchema(H dataSource, InputStream inputStream) {
		verifySchema(dataSource.openWritableDatabase(), inputStream);
	}

	/**
	 * Verify schema.
	 *
	 * @param database
	 *            the database
	 * @param context
	 *            the context
	 * @param rawId
	 *            the raw id
	 */
	public static void verifySchema(SQLiteDatabase database, Context context, int rawId) {
		verifySchema(database, context.getResources().openRawResource(rawId));
	}

	/**
	 * Extract commands.
	 *
	 * @param database
	 *            the database
	 * @param inputStream
	 *            the input stream
	 * @return the list
	 */
	static List<String> extractCommands(SQLiteDatabase database, InputStream inputStream) {
		final List<String> result = new ArrayList<>();
		final String input = IOUtils.readText(inputStream);
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);
		ParserRuleContext parseContext = parser.parse();

		ParseTreeWalker walk = new ParseTreeWalker();
		walk.walk(new JqlBaseListener() {

			@Override
			public void enterSql_stmt(Sql_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex();
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				result.add(input.substring(start, stop));
			}

		}, parseContext);

		return result;
	}

	/**
	 * Verify schema.
	 *
	 * @param <H>
	 *            the generic type
	 * @param dataSource
	 *            the data source
	 * @param context
	 *            the context
	 * @param rawId
	 *            the raw id
	 */
	public static <H extends AbstractDataSource> void verifySchema(H dataSource, Context context, int rawId) {
		verifySchema(dataSource.openWritableDatabase(), context, rawId);
	}

	/**
	 * Verify schema internal.
	 *
	 * @param database
	 *            the database
	 * @param expectedSQL
	 *            the expected SQL
	 */
	static void verifySchemaInternal(SQLiteDatabase database, List<String> expectedSQL) {
		Set<String> actualSql = new HashSet<String>();
		actualSql.addAll(SQLiteTestUtils.getAllTables(database).values());
		actualSql.addAll(SQLiteTestUtils.getAllIndexes(database).values());

		Logger.info("Database schema version %s", database.getVersion());

		if (actualSql.size() != expectedSQL.size()) {
			Logger.error(
					"Database schema comparison result: ERROR - Number of tables and indexes between aspected and actual schemas are different");
			for (String item1 : actualSql) {
				Logger.info("actual: " + item1);
			}

			for (String item1 : expectedSQL) {
				Logger.info("expected: " + item1);
			}

			throw new KriptonRuntimeException(
					"Number of tables and indexes between aspected and actual schemas are different");
		}

		for (String item : expectedSQL) {
			if (!actualSql.contains(item)) {
				Logger.error("Database schema comparison result: ERROR - Actual and expected schemas are NOT the same");
				for (String item1 : actualSql) {
					Logger.info("actual: " + item1);
				}

				for (String item1 : expectedSQL) {
					Logger.info("expected: " + item1);
				}

				throw new KriptonRuntimeException("not found element: " + item);
			}
		}

		Logger.info("Database schema comparison result: OK - Actual and expected schemas are the same!");

		database.close();

	}

	/**
	 * Force a schema update for a datasource. Note that no DDL was execute
	 * untill the database was opened.
	 *
	 * @param <H>
	 *            the element type
	 * @param dataSource
	 *            the data source
	 * @param version
	 *            to upgrade.
	 */
	public static <H extends AbstractDataSource> void forceDataSourceSchemaUpdate(H dataSource, int version) {
		dataSource.forceClose();

		dataSource.version = version;
		dataSource.database = null;
		dataSource.sqliteHelper = null;

		dataSource.openWritableDatabase();
	}

	/**
	 * Clear datasource.
	 *
	 * @param <H>
	 *            the element type
	 * @param dataSource
	 *            the data source
	 */
	public static <H extends AbstractDataSource> void clearDataSource(H dataSource) {
		if (dataSource.options.inMemory) {
			dataSource.close();
			return;
		}

		dataSource.openWritableDatabase();
		File file = new File(dataSource.database.getPath(), dataSource.name);

		if (dataSource.isOpen()) {
			dataSource.forceClose();
			dataSource.close();
		}

		Logger.info("Clear database file %s", file.getAbsolutePath());
		if (!file.delete()) {
			Logger.warn("Can not delete database " + file.getAbsolutePath());
		}
	}


	public static void dropIndex(SQLiteDatabase database, String... indexNames) {		
		for (String indexName: indexNames) {
			drop(database, QueryType.INDEX, indexName);
		}
			
	}

}

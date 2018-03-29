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
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper.OnResultListener;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper.QueryType;
import com.abubusoft.kripton.android.sqlite.commons.IOUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SQLiteSchemaVerifierHelper {

	/**
	 * Delete database file
	 * @param context
	 */
	public static void clearDatabase(Context context) {		
		File dbFile=context.getDatabasePath(SQLiteUpdateTestDatabase.MIGRATION_TEST);
		Logger.info("Clear database file %s", dbFile.getAbsolutePath());
		if (!dbFile.delete()) {
			Logger.warn("Can not delete database " + dbFile.getAbsolutePath());
		}		
		
	}

	private static void query(SQLiteDatabase db, String conditions, QueryType type, OnResultListener listener) {
		SQLiteUpdateTaskHelper.query(db, conditions, type, listener);
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
		return SQLiteUpdateTaskHelper.getAllTables(db);
	}

	/**
	 * Add to all schema's table a specifix prefix
	 * 
	 * @param db
	 * @param prefix
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
		return SQLiteUpdateTaskHelper.getAllIndexes(db);
	}

	public static void executeSQL(final SQLiteDatabase database, Context context, int rawResourceId) {
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

	public static void verifySchema(SQLiteDatabase database, InputStream inputStream) {
		List<String> ddl = extractCommands(database, inputStream);
		verifySchemaInternal(database, ddl);
	}

	public static <H extends AbstractDataSource> void verifySchema(H dataSource, InputStream inputStream) {
		verifySchema(dataSource.openWritableDatabase(), inputStream);
	}

	public static void verifySchema(SQLiteDatabase database, Context context, int rawId) {
		verifySchema(database, context.getResources().openRawResource(rawId));
	}

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

	public static <H extends AbstractDataSource> void verifySchema(H dataSource, Context context, int rawId) {
		verifySchema(dataSource.openWritableDatabase(), context, rawId);
	}

	static void verifySchemaInternal(SQLiteDatabase database, List<String> expectedSQL) {
		Set<String> actualSql = new HashSet<String>();
		actualSql.addAll(SQLiteSchemaVerifierHelper.getAllTables(database).values());
		actualSql.addAll(SQLiteSchemaVerifierHelper.getAllIndexes(database).values());

		if (actualSql.size() != expectedSQL.size()) {
			Logger.error(
					"SCHEMA COMPARATOR RESULT: ERROR - Number of tables and indexes between aspected and actual schemas are different");
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
				Logger.error("SCHEMA COMPARATOR RESULT: ERROR - Actual and expected schemas are NOT the same");
				for (String item1 : actualSql) {
					Logger.info("actual: " + item1);
				}

				for (String item1 : expectedSQL) {
					Logger.info("expected: " + item1);
				}

				throw new KriptonRuntimeException("not found element: " + item);
			}
		}

		Logger.info("SCHEMA COMPARATOR RESULT: OK - Actual and expected schemas are the same!");

		database.close();

	}

	/**
	 * Force a schema update for a datasource. Note that no DDL was execute
	 * untill the database was opened.
	 * 
	 * @param dataSource
	 * @param version
	 *            to upgrade.
	 */
	public static <E extends AbstractDataSource> void forceSchemaUpdate(E dataSource, int version) {
		dataSource.forceClose();

		dataSource.version = version;
		dataSource.database = null;
		dataSource.sqliteHelper = null;

		dataSource.openWritableDatabase();
	}

	public static <E extends AbstractDataSource> void clearDatabase(E dataSource) {
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

}

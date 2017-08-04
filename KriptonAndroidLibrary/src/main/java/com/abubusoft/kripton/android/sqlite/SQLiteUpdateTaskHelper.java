package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.commons.IOUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class SQLiteUpdateTaskHelper {

	public enum QueryType {
		TABLE, INDEX
	};

	interface OnResultListener {
		void onRow(SQLiteDatabase db, String name, String sql);
	}

	private static void query(SQLiteDatabase db, String conditions, QueryType type, OnResultListener listener) {
		String query = String.format("SELECT name, sql FROM sqlite_master WHERE type='%s'and name!='sqlite_sequence' and name!='android_metadata'%s", type.toString().toLowerCase(),
				StringUtils.hasText(conditions) ? " AND " + conditions : "");
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
				result.put(name, sql.trim());
				// Logger.info("found TABLE %s = %s", name, sql);

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
	public static void addPrefixToTables(SQLiteDatabase db, final String prefix) {
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
				result.put(name, sql.trim());
				// Logger.info("found INDEX %s = %s", name, sql);

			}
		});

		return result;
	}

	public static void executeSQLFromResourceId(final SQLiteDatabase database, int resourceId) {
		String[] c = IOUtils.readTextFile(KriptonLibrary.context(), resourceId).split(";");
		List<String> commands = Arrays.asList(c);
		executeSQL(database, commands);
	}

	public static void executeSQLFromFile(final SQLiteDatabase database, String fileName) {
		try {
			executeSQLFromFile(database, new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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

	public static void executeSQLFromFile(final SQLiteDatabase database, InputStream fileInputStream) {
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
	
	public static void verifySchema(SQLiteDatabase database, String fileNameSchema) {
		try {
			verifySchema(database, new FileInputStream(fileNameSchema));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			throw(new KriptonRuntimeException(e));
		}
	}
	
	public static void verifySchema(SQLiteDatabase database, File fileSchema) {
		try {
			verifySchema(database, new FileInputStream(fileSchema));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			throw(new KriptonRuntimeException(e));
		}
	}

	public static void verifySchema(SQLiteDatabase database, InputStream inputStream) {
		Set<String> actualSql = new HashSet<String>();
		actualSql.addAll(SQLiteUpdateTaskHelper.getAllTables(database).values());
		actualSql.addAll(SQLiteUpdateTaskHelper.getAllIndexes(database).values());

		List<String> expectedSQL = SQLiteUpdateTaskHelper.readSQLFromFile(inputStream);

		if (actualSql.size() != expectedSQL.size()) {
			Logger.error("SCHEMA COMPARATOR RESULT: ERROR - Number of tables and indexes between aspected and actual schemas are different");
			for (String item1 : actualSql) {
				Logger.info("actual: " + item1);
			}

			for (String item1 : expectedSQL) {
				Logger.info("expected: " + item1);
			}

			throw new KriptonRuntimeException("Number of tables and indexes between aspected and actual schemas are different");
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
	}

	public static <E extends AbstractDataSource> void clearDatabase(E dataSource) {
		if (dataSource.isOpen())
		{
			dataSource.forceClose();
		}
		
		File file=new File(dataSource.database.getPath(),dataSource.name);
		
		Logger.info("Clear database file %s", file.getAbsolutePath());		
		file.delete();
		
		
		
		
	}

}

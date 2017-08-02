package com.abubusoft.kripton.android.sqlite.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.migration.internals.MigrationSQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class MigrationTestHelper {

	private static void clear(SQLiteDatabase db, String type) {
		String sql;
		try (Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type == '" + type + "'", null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				do {
					sql = "drop " + type + " " + cursor.getString(index0);
					Logger.info(sql);
					db.execSQL(sql);
				} while (cursor.moveToNext());
			}
		}
	}

	public static Map<String, String> getAllTables(SQLiteDatabase db) {
		Map<String, String> result = new LinkedHashMap<>();
		try (Cursor cursor = db.rawQuery("select name, sql from sqlite_master where type='table' and name!='sqlite_sequence' and name!='android_metadata'", null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				int index1 = cursor.getColumnIndex("sql");
				do {
					result.put(cursor.getString(index0).trim(), cursor.getString(index1).trim());
					// sql="drop "+type+" "+cursor.getString(index0);
					Logger.info("found TABLE %s = %s", cursor.getString(index0), cursor.getString(index1));
					// db.execSQL(sql);

				} while (cursor.moveToNext());
			}

			return result;
		}
	}

	public static void renameAllTables(SQLiteDatabase db, String prefix) {
		String sql;
		try (Cursor cursor = db.rawQuery("select name, sql from sqlite_master where type='table' and name!='sqlite_sequence' and name!='android_metadata'", null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				do {
					// sql="drop "+type+" "+cursor.getString(index0);
					sql = String.format("ALTER TABLE %s RENAME TO %s%s;", cursor.getString(index0), prefix, cursor.getString(index0));
					Logger.info(sql);
					db.execSQL(sql);

				} while (cursor.moveToNext());
			}

		}
	}

	public static void dropAllTables(SQLiteDatabase db, String prefix) {
		String sql;
		String args[] = { prefix };
		try (Cursor cursor = db.rawQuery("select name, sql from sqlite_master where type='table' and name like ? || '%'", args)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				do {
					// sql="drop "+type+" "+cursor.getString(index0);
					sql = String.format("DROP TABLE %s;", cursor.getString(index0));
					Logger.info(sql);
					db.execSQL(sql);

				} while (cursor.moveToNext());
			}

		}
	}

	public static void clearAll(SQLiteDatabase db) {
		clear(db, "table");
		clear(db, "index");
	}

	public static List<String> readSQLFromFile(String sqlDefinitionFile) {
		try {
			final List<String> executionList = new ArrayList<>();
			File f = new File(sqlDefinitionFile);
			final String ddl = IOUtils.toString(new FileInputStream(f), Charset.forName("utf-8"));

			MigrationSQLChecker.getInstance().analyze(ddl, new JqlBaseListener() {
				public void enterSelect_stmt(Select_stmtContext ctx) {
				};

				public void enterSql_stmt(Sql_stmtContext ctx) {
					;
					int start = ctx.getStart().getStartIndex();
					int stop = ctx.getStop().getStopIndex() + 1;

					if (start == stop)
						return;

					String statement = ddl.substring(start, stop).trim();

					executionList.add(statement);
				};
			});
			return executionList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void executeSQLFromFile(SQLiteDatabase database, String sqlDefinitionFile) {
		List<String> executionList = readSQLFromFile(sqlDefinitionFile);
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
		}

	}

	public static void dropAllIndex(SQLiteDatabase database) {
		String sql = "";
		try (Cursor cursor = database.rawQuery("select * from sqlite_master where type='index'", null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				do {
					sql = String.format("DROP INDEX %s;", cursor.getString(index0));
					Logger.info(sql);
					database.execSQL(sql);

				} while (cursor.moveToNext());
			}

		}

	}

	public static Map<String, String> getAllIndexes(SQLiteDatabase db) {
		Map<String, String> result = new LinkedHashMap<>();
		try (Cursor cursor = db.rawQuery("select name, sql from sqlite_master where type='index' and name!='sqlite_sequence' and name!='android_metadata'", null)) {
			if (cursor.moveToFirst()) {
				int index0 = cursor.getColumnIndex("name");
				int index1 = cursor.getColumnIndex("sql");
				do {
					result.put(cursor.getString(index0), cursor.getString(index1));
					// sql="drop "+type+" "+cursor.getString(index0);
					Logger.info("found INDEX %s = %s", cursor.getString(index0).trim(), cursor.getString(index1).trim());
					// db.execSQL(sql);

				} while (cursor.moveToNext());
			}

			return result;
		}
	}

}

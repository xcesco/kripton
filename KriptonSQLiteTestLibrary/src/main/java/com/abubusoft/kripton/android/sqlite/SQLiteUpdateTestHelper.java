package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.internals.MigrationSQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.database.sqlite.SQLiteDatabase;

public class SQLiteUpdateTestHelper {
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
}

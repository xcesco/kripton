package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteStatement;

public abstract class KriptonDatabaseWrapper {
	
	public static SQLiteStatement compile(AbstractDataSource dataSource, String sql) {
		SQLiteStatement ps = dataSource.database().compileStatement(sql);
		
		return ps;
	}

	public static long insert(AbstractDataSource dataSource, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = dataSource.database().compileStatement(sql);
		try {
			contentValues.bind(ps);
			return ps.executeInsert();
		} finally {
			ps.close();
		}
	}

	public static long insert(AbstractDataSource dataSource, SQLiteStatement ps, KriptonContentValues contentValues) {
		contentValues.bind(ps);

		return ps.executeInsert();
	}

	public static int updateDelete(AbstractDataSource dataSource, SQLiteStatement ps, KriptonContentValues contentValues) {
		contentValues.bind(ps);

		return ps.executeUpdateDelete();

	}

	public static int updateDelete(AbstractDataSource dataSource, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = dataSource.database().compileStatement(sql);
		try {
			contentValues.bind(ps);
			return ps.executeUpdateDelete();
		} finally {
			ps.close();
		}
	}

}

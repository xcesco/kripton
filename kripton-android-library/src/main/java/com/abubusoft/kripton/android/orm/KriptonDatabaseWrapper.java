package com.abubusoft.kripton.android.orm;

import android.database.sqlite.SQLiteStatement;

public abstract class KriptonDatabaseWrapper {
	
	public static SQLiteStatement compile(SQLContext context, String sql) {
		SQLiteStatement ps = context.database().compileStatement(sql);
		
		return ps;
	}

	public static long insert(SQLContext context, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = context.database().compileStatement(sql);
		try {
			contentValues.bind(ps);
			return ps.executeInsert();
		} finally {
			ps.close();
		}
	}

	public static long insert(SQLiteStatement ps, KriptonContentValues contentValues) {
		contentValues.bind(ps);

		return ps.executeInsert();
	}

	public static int updateDelete(SQLiteStatement ps, KriptonContentValues contentValues) {
		contentValues.bind(ps);

		return ps.executeUpdateDelete();

	}

	public static int updateDelete(SQLContext context, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = context.database().compileStatement(sql);
		try {
			contentValues.bind(ps);
			return ps.executeUpdateDelete();
		} finally {
			ps.close();
		}
	}

}

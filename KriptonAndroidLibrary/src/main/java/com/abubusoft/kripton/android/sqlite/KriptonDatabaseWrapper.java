package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;

import android.database.sqlite.SQLiteStatement;

public abstract class KriptonDatabaseWrapper {

	public static long insert(AbstractDataSource dataSource, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = dataSource.preparedStatements.get(sql);
		if (ps == null) {
			ps = dataSource.database().compileStatement(sql);
			dataSource.preparedStatements.put(sql, ps);
		}

		synchronized (ps) {
			try {
				contentValues.bind(ps);

				return ps.executeInsert();
			} finally {
				//ps.close();
			}
		}

	}

	public static int delete(AbstractDataSource dataSource, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = dataSource.preparedStatements.get(sql);
		if (ps == null) {
			ps = dataSource.database().compileStatement(sql);
			dataSource.preparedStatements.put(sql, ps);
						
		}

		synchronized (ps) {
			try {
				contentValues.bind(ps);

				return ps.executeUpdateDelete();
			} finally {
				//ps.close();
			}
		}
					
	}

	public static int update(AbstractDataSource dataSource, String sql, KriptonContentValues contentValues) {
		SQLiteStatement ps = dataSource.preparedStatements.get(sql);
		if (ps == null) {
			ps = dataSource.database().compileStatement(sql);
			dataSource.preparedStatements.put(sql, ps);
		}

		synchronized (ps) {
			try {
				contentValues.bind(ps);

				return ps.executeUpdateDelete();
			} finally {
				//ps.close();
			}
		}
	}

}

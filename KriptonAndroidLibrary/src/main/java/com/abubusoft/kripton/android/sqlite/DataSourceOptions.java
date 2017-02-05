package com.abubusoft.kripton.android.sqlite;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Options to build a data source
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class DataSourceOptions {
	public CursorFactory factory;
	public DatabaseErrorHandler errorHandler;
	public DatabaseLifecycleHandler databaseLifecycleHandler;

	private DataSourceOptions() {

	}

	public DataSourceOptions cursorFactory(CursorFactory value) {
		this.factory = value;
		return this;
	}

	public DataSourceOptions errorHandler(DatabaseErrorHandler value) {
		this.errorHandler = value;
		return this;
	}

	public DataSourceOptions databaseLifecycleHandler(DatabaseLifecycleHandler value) {
		this.databaseLifecycleHandler = value;
		return this;
	}

	public static DataSourceOptions build() {
		return new DataSourceOptions();
	}

}
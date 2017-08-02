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

	public final CursorFactory factory;
	public final  DatabaseErrorHandler errorHandler;
	public final  DatabaseLifecycleHandler databaseLifecycleHandler;
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private CursorFactory factory;
		private DatabaseErrorHandler errorHandler;
		private DatabaseLifecycleHandler databaseLifecycleHandler;

		public Builder cursorFactory(CursorFactory value) {
			this.factory = value;
			return this;
		}

		public Builder errorHandler(DatabaseErrorHandler value) {
			this.errorHandler = value;
			return this;
		}

		public Builder databaseLifecycleHandler(DatabaseLifecycleHandler value) {
			this.databaseLifecycleHandler = value;
			return this;
		}
		
		public Builder versionUpdateTask(DatabaseVersionUpdateTask databaseVersionUpdateTask) {			
			return this;
		}

		public Builder versionUpdateTask(int oldVersion, int newVersion, String updateSqlFileName) {
			return this;	
		}
				
		public DataSourceOptions build() {
			return new DataSourceOptions(factory, errorHandler, databaseLifecycleHandler);
		}
	}

	private DataSourceOptions(CursorFactory factory, DatabaseErrorHandler errorHandler, DatabaseLifecycleHandler databaseLifecycleHandler) {
		this.factory = factory;
		this.errorHandler = errorHandler;
		this.databaseLifecycleHandler = databaseLifecycleHandler;
	}

}
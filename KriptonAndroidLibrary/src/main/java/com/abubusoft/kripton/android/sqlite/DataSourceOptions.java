package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.commons.IOUtils;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Options to build a data source
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class DataSourceOptions {

	public final CursorFactory factory;
	public final DatabaseErrorHandler errorHandler;
	public final DatabaseLifecycleHandler databaseLifecycleHandler;
	public final List<SQLiteUpdateTask> updateTasks;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private CursorFactory factory;
		private DatabaseErrorHandler errorHandler;
		private DatabaseLifecycleHandler databaseLifecycleHandler;
		private List<SQLiteUpdateTask> updateTasks = new ArrayList<>();

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

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. File can contains -- or multiline comments.
		 * 
		 * @param currentVersion
		 * @param resId
		 * @return
		 */
		public Builder addVersionUpdateTask(int currentVersion, int resId) {
			String[] commands = IOUtils.readTextFile(KriptonLibrary.context(), resId).split(";");

			return addUpdateTask(currentVersion, Arrays.asList(commands));
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. No comment are allowed. Only sql.
		 * 
		 * @param currentVersion
		 * @param resId
		 * @return
		 */
		public Builder addUpdateTask(int currentVersion, final List<String> sqlList) {
			SQLiteUpdateTask task = new SQLiteUpdateTask(currentVersion) {

				@Override
				public void execute(SQLiteDatabase database) {
					for(String item: sqlList) {
						Logger.info(item);
						database.execSQL(item);
					}
//					sqlList.forEach(item -> {
//						Logger.info(item);
//						database.execSQL(item);
//					});

				}
			};

			this.updateTasks.add(task);

			return this;
		}

		public Builder addUpdateTask(SQLiteUpdateTask task) {
			this.updateTasks.add(task);
			return this;
		}

		/**
		 * task to execute upgrade from currentVersion-1 to currentVersion.
		 * 
		 * @param currentVersion
		 *            database current version
		 * @param updateSqlFileName
		 *            filename to read and execute
		 * @return
		 */
		public Builder addUpdateTask(int currentVersion, String updateSqlFileName) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(currentVersion, updateSqlFileName);

			this.updateTasks.add(task);

			return this;
		}

		public DataSourceOptions build() {
			return new DataSourceOptions(factory, errorHandler, databaseLifecycleHandler, updateTasks);
		}
	}

	private DataSourceOptions(CursorFactory factory, DatabaseErrorHandler errorHandler,
			DatabaseLifecycleHandler databaseLifecycleHandler, List<SQLiteUpdateTask> updateTasks) {
		this.factory = factory;
		this.errorHandler = errorHandler;
		this.databaseLifecycleHandler = databaseLifecycleHandler;
		this.updateTasks = updateTasks;
	}

}
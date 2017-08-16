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

import java.io.InputStream;
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
 * @author Francesco Benincasa (info@abubusoft.com)
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
		public Builder addUpdateTask(int currentVersion, int resId) {
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
		
		/**
		 * task to execute upgrade from currentVersion-1 to currentVersion.
		 * 
		 * @param currentVersion
		 *            database current version
		 * @param updateSqlFileName
		 *            filename to read and execute
		 * @return
		 */
		public Builder addUpdateTask(int currentVersion, InputStream inputStream) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(currentVersion, inputStream);

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
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
import java.util.List;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.Pair;

import android.content.Context;
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

	public final boolean logEnabled;

	public final CursorFactory factory;

	public final DatabaseErrorHandler errorHandler;

	public final DatabaseLifecycleHandler databaseLifecycleHandler;

	public final List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks;

	public final SQLitePopulator populator;

	public final boolean inMemory;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private boolean logEnabled = true;
		private CursorFactory factory;
		private DatabaseErrorHandler errorHandler;
		private DatabaseLifecycleHandler databaseLifecycleHandler;
		private List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks = new ArrayList<>();
		private SQLitePopulator populator;
		private boolean inMemory;

		public Builder cursorFactory(CursorFactory value) {
			this.factory = value;
			return this;
		}

		public Builder log(boolean value) {
			this.logEnabled = value;
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

		public Builder populator(SQLitePopulator populator) {
			this.populator = populator;
			return this;
		}

		public Builder inMemory(boolean inMemory) {
			this.inMemory = inMemory;
			return this;
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. File can contains -- or multiline comments.
		 * 
		 * @param currentVersion
		 * @param resRawId
		 * @return
		 */
		public Builder addUpdateTask(int currentVersion, Context context, int resRawId) {
			return addUpdateTask(currentVersion, context.getResources().openRawResource(resRawId));
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. No comment are allowed. Only sql.
		 * 
		 * @param currentVersion
		 * @param sqlCommandList
		 * 		sql command to execute
		 * @return
		 */
		public Builder addUpdateTask(final int currentVersion, final List<String> sqlCommandList) {
			SQLiteUpdateTask task = new SQLiteUpdateTask() {

				@Override
				public void execute(SQLiteDatabase database, int previousVersion, int currentVersion) {
					for (String item : sqlCommandList) {
						Logger.info(item);
						database.execSQL(item);
					}
					
				}
			};				

			this.updateTasks.add(new Pair<>(currentVersion, task));

			return this;
		}

		public Builder addUpdateTask(int version, SQLiteUpdateTask task) {

			this.updateTasks.add(new Pair<>(version, task));
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
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(inputStream);

			this.updateTasks.add(new Pair<>(currentVersion, task));

			return this;
		}

		public DataSourceOptions build() {
			return new DataSourceOptions(factory, errorHandler, databaseLifecycleHandler, updateTasks, logEnabled, populator, inMemory);
		}
	}

	private DataSourceOptions(CursorFactory factory, DatabaseErrorHandler errorHandler, DatabaseLifecycleHandler databaseLifecycleHandler, List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks,
			boolean log, SQLitePopulator populator, boolean inMemory) {
		this.logEnabled = log;
		this.factory = factory;
		this.errorHandler = errorHandler;
		this.databaseLifecycleHandler = databaseLifecycleHandler;
		this.updateTasks = updateTasks;
		this.populator = populator;
		this.inMemory = inMemory;
	}

}
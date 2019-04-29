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
 * Options to build a data source.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class DataSourceOptions {

	/** The log enabled. */
	public final boolean logEnabled;

	/** The factory. */
	public final CursorFactory factory;

	/** The error handler. */
	public final DatabaseErrorHandler errorHandler;

	/** The database lifecycle handler. */
	public final DatabaseLifecycleHandler databaseLifecycleHandler;

	/** The update tasks. */
	public final List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks;

	/** The populator. */
	public final SQLitePopulator populator;

	/** The in memory. */
	public final boolean inMemory;

	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The log enabled. */
		private boolean logEnabled = true;
		
		/** The factory. */
		private CursorFactory factory;
		
		/** The error handler. */
		private DatabaseErrorHandler errorHandler;
		
		/** The database lifecycle handler. */
		private DatabaseLifecycleHandler databaseLifecycleHandler;
		
		/** The update tasks. */
		private List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks = new ArrayList<>();
		
		/** The populator. */
		private SQLitePopulator populator;
		
		/** The in memory. */
		private boolean inMemory;

		/**
		 * Cursor factory.
		 *
		 * @param value the value
		 * @return the builder
		 */
		public Builder cursorFactory(CursorFactory value) {
			this.factory = value;
			return this;
		}

		/**
		 * Log.
		 *
		 * @param value the value
		 * @return the builder
		 */
		public Builder log(boolean value) {
			this.logEnabled = value;
			return this;
		}

		/**
		 * Error handler.
		 *
		 * @param value the value
		 * @return the builder
		 */
		public Builder errorHandler(DatabaseErrorHandler value) {
			this.errorHandler = value;
			return this;
		}

		/**
		 * Database lifecycle handler.
		 *
		 * @param value the value
		 * @return the builder
		 */
		public Builder databaseLifecycleHandler(DatabaseLifecycleHandler value) {
			this.databaseLifecycleHandler = value;
			return this;
		}

		/**
		 * Populator.
		 *
		 * @param populator the populator
		 * @return the builder
		 */
		public Builder populator(SQLitePopulator populator) {
			this.populator = populator;
			return this;
		}

		/**
		 * In memory.
		 *
		 * @param inMemory the in memory
		 * @return the builder
		 */
		public Builder inMemory(boolean inMemory) {
			this.inMemory = inMemory;
			return this;
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. File can contains -- or multiline comments.
		 *
		 * @param currentVersion the current version
		 * @param context the context
		 * @param resRawId the res raw id
		 * @return the builder
		 */
		public Builder addUpdateTask(int currentVersion, Context context, int resRawId) {
			return addUpdateTask(currentVersion, context.getResources().openRawResource(resRawId));
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. No comment are allowed. Only sql.
		 *
		 * @param currentVersion the current version
		 * @param sqlCommandList 		sql command to execute
		 * @return the builder
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

		/**
		 * Adds the update task.
		 *
		 * @param version the version
		 * @param task the task
		 * @return the builder
		 */
		public Builder addUpdateTask(int version, SQLiteUpdateTask task) {

			this.updateTasks.add(new Pair<>(version, task));
			return this;
		}

		/**
		 * task to execute upgrade from currentVersion-1 to currentVersion.
		 *
		 * @param currentVersion            database current version
		 * @param inputStream the input stream
		 * @return the builder
		 */
		public Builder addUpdateTask(int currentVersion, InputStream inputStream) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(inputStream);

			this.updateTasks.add(new Pair<>(currentVersion, task));

			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the data source options
		 */
		public DataSourceOptions build() {
			return new DataSourceOptions(factory, errorHandler, databaseLifecycleHandler, updateTasks, logEnabled, populator, inMemory);
		}
		
		/**
		 * Create builder from data source
		 * @param source
		 * @return
		 */
		public Builder createFrom(DataSourceOptions source) {
			Builder builder=new Builder();
			
			builder.logEnabled = source.logEnabled;
			builder.factory = source.factory;
			builder.errorHandler = source.errorHandler;
			builder.databaseLifecycleHandler = source.databaseLifecycleHandler;
			builder.updateTasks = source.updateTasks;
			builder.populator = source.populator;
			builder.inMemory = source.inMemory;
						
			return builder;
		}
	}

	/**
	 * Instantiates a new data source options.
	 *
	 * @param factory the factory
	 * @param errorHandler the error handler
	 * @param databaseLifecycleHandler the database lifecycle handler
	 * @param updateTasks the update tasks
	 * @param log the log
	 * @param populator the populator
	 * @param inMemory the in memory
	 */
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
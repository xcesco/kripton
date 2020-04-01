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

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.Pair;

import android.content.Context;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;

/**
 * Options to build a data source.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class DataSourceOptions {

	/** The log enabled. */
	public final boolean logEnabled;

	/** The database lifecycle handler. */
	public final DatabaseLifecycleHandler databaseLifecycleHandler;

	/** The update tasks. */
	public final List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks;

	/** The populator. */
	public final SQLitePopulator populator;

	/** The in memory. */
	public final boolean inMemory;

	/**
	 * Factory used to create the helper object.
	 */
	public final Factory openHelperFactory;

	/**
	 * If database is builded with the build method, forces the instance to be
	 * created
	 */
	public final boolean forceBuild;

	@Override
	public String toString() {
		return "DataSourceOptions [logEnabled=" + logEnabled + ", inMemory=" + inMemory + ", openHelperFactory="
				+ openHelperFactory.getClass().getName() + ", forceBuild=" + forceBuild + "]";
	}

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

		/** The database lifecycle handler. */
		private DatabaseLifecycleHandler databaseLifecycleHandler;

		/** The update tasks. */
		private List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks = new ArrayList<>();

		/** The populator. */
		private SQLitePopulator populator;

		/** The in memory. */
		private boolean inMemory;

		/**
		 * If <code>true</code> force instance to be created. Default is
		 * <code>false</code>.
		 */
		private boolean forceBuild = false;

		/**
		 * OpenHelper factory. Default is provided with kripton
		 */
		private SupportSQLiteOpenHelper.Factory openHelperFactory = new KriptonSQLiteOpenHelperFactory();

		public Builder openHelperFactory(SupportSQLiteOpenHelper.Factory openHelperFactory) {
			this.openHelperFactory = openHelperFactory;
			return this;
		}

		/**
		 * Log.
		 *
		 * @param value
		 *            the value
		 * @return the builder
		 */
		public Builder log(boolean value) {
			this.logEnabled = value;
			return this;
		}

		/**
		 * <p>
		 * If <code>true</code> force instance to be created. Default is
		 * <code>false</code>.
		 * </p>
		 * <p>
		 * Add force instance properties to force instance creation during
		 * datasource build.
		 * </p>
		 * <p>
		 * It can be very usefull when you need to cipher an existing database
		 * and you need to open the db before set the upgrade
		 * </p>
		 * .
		 * 
		 * @param value
		 *            the value
		 * @return the builder
		 */
		public Builder forceBuild(boolean value) {
			this.forceBuild = value;
			return this;
		}

		/**
		 * Database lifecycle handler.
		 *
		 * @param value
		 *            the value
		 * @return the builder
		 */
		public Builder databaseLifecycleHandler(DatabaseLifecycleHandler value) {
			this.databaseLifecycleHandler = value;
			return this;
		}

		/**
		 * Populator.
		 *
		 * @param populator
		 *            the populator
		 * @return the builder
		 */
		public Builder populator(SQLitePopulator populator) {
			this.populator = populator;
			return this;
		}

		/**
		 * In memory.
		 *
		 * @param inMemory
		 *            the in memory
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
		 * @param targetVersion
		 *            the version of database we want to reach
		 * @param context
		 *            the context
		 * @param resRawId
		 *            the res raw id
		 * @return the builder
		 */
		public Builder addUpdateTask(int targetVersion, Context context, int resRawId) {
			return addUpdateTask(targetVersion, context.getResources().openRawResource(resRawId));
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. File can contains -- or multiline comments.
		 *
		 * @param targetVersion
		 *            the version of database we want to reach
		 * @param context
		 *            the context
		 * @param resRawId
		 *            the res raw id
		 * @return the builder
		 */
		public Builder addUpdateTask(int targetVersion, int resRawId) {
			return addUpdateTask(targetVersion, KriptonLibrary.getContext().getResources().openRawResource(resRawId));
		}

		/**
		 * Retrieve from a raw resource a list of comma separated sql commands
		 * to execute. No comment are allowed. Only sql.
		 *
		 * @param targetVersion
		 *            the version of database we want to reach
		 * @param sqlCommandList
		 *            sql command to execute
		 * @return the builder
		 */
		public Builder addUpdateTask(final int targetVersion, final List<String> sqlCommandList) {
			SQLiteUpdateTask task = new SQLiteUpdateTask() {

				@Override
				public void execute(SupportSQLiteDatabase database, int previousVersion, int currentVersion) {
					for (String item : sqlCommandList) {
						Logger.info(item);
						database.execSQL(item);
					}

				}
			};

			this.updateTasks.add(new Pair<>(targetVersion, task));

			return this;
		}

		/**
		 * Adds the update task.
		 *
		 * @param targetVersion
		 *            the initial version of database
		 * @param task
		 *            the task
		 * @return the builder
		 */
		public Builder addUpdateTask(int targetVersion, SQLiteUpdateTask task) {

			this.updateTasks.add(new Pair<>(targetVersion, task));
			return this;
		}

		/**
		 * task to execute upgrade from currentVersion-1 to currentVersion.
		 *
		 * @param targetVersion
		 *            the version of database we want to reach
		 * @param inputStream
		 *            the input stream
		 * @return the builder
		 */
		public Builder addUpdateTask(int targetVersion, InputStream inputStream) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(inputStream);

			this.updateTasks.add(new Pair<>(targetVersion, task));

			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the data source options
		 */
		public DataSourceOptions build() {
			return new DataSourceOptions(databaseLifecycleHandler, updateTasks, logEnabled, populator, inMemory,
					openHelperFactory, forceBuild);
		}

		/**
		 * Create builder from data source
		 * 
		 * @param source
		 * @return
		 */
		public Builder createFrom(DataSourceOptions source) {
			Builder builder = new Builder();

			builder.logEnabled = source.logEnabled;
			builder.databaseLifecycleHandler = source.databaseLifecycleHandler;
			builder.updateTasks = source.updateTasks;
			builder.populator = source.populator;
			builder.inMemory = source.inMemory;
			builder.openHelperFactory = source.openHelperFactory;

			return builder;
		}
	}

	/**
	 * Instantiates a new data source options.
	 *
	 * @param factory
	 *            the factory
	 * @param errorHandler
	 *            the error handler
	 * @param databaseLifecycleHandler
	 *            the database lifecycle handler
	 * @param updateTasks
	 *            the update tasks
	 * @param log
	 *            the log
	 * @param populator
	 *            the populator
	 * @param inMemory
	 *            the in memory
	 * @param openHelperFactory
	 * @param forceBuild
	 *            force the build method to rebuild the instance
	 */
	private DataSourceOptions(DatabaseLifecycleHandler databaseLifecycleHandler,
			List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks, boolean log, SQLitePopulator populator,
			boolean inMemory, Factory openHelperFactory, boolean forceBuild) {
		this.logEnabled = log;
		this.databaseLifecycleHandler = databaseLifecycleHandler;
		this.updateTasks = updateTasks;
		this.populator = populator;
		this.inMemory = inMemory;
		this.openHelperFactory = openHelperFactory;
		this.forceBuild = forceBuild;
	}

}
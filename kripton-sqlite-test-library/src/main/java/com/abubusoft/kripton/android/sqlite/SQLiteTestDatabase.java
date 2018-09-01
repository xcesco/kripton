/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskFromFile;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The Class SQLiteUpdateTestDatabase.
 */
public class SQLiteTestDatabase {

	/**
	 * Builder.
	 *
	 * @param version
	 *            the version
	 * @param initialSchemaInputStream
	 *            the initial schema input stream
	 * @return the builder
	 */
	public static Builder builder(int version, InputStream initialSchemaInputStream) {
		return new Builder(version, initialSchemaInputStream);
	}

	/**
	 * Builder.
	 *
	 * @param version
	 *            the version
	 * @param context
	 *            the context
	 * @param initialSchemaRawResourceId
	 *            the initial schema raw resource id
	 * @return the builder
	 */
	public static Builder builder(int version, Context context, int initialSchemaRawResourceId) {
		return new Builder(version, context.getResources().openRawResource(initialSchemaRawResourceId));
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The version. */
		private int version;

		/** The update tasks. */
		private List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks;

		/** The initial schema input stream. */
		private InputStream initialSchemaInputStream;

		/** The initial schema resource raw id. */
		private int initialSchemaResourceRawId;

		private SQLitePopulator populator;

		/**
		 * Instantiates a new builder.
		 *
		 * @param version
		 *            the version
		 * @param initialSchemaInputStream
		 *            the initial schema input stream
		 */
		Builder(int version, InputStream initialSchemaInputStream) {
			this.version = version;
			this.initialSchemaInputStream = initialSchemaInputStream;
			this.updateTasks = new ArrayList<>();
		}

		/**
		 * Adds the version update task.
		 *
		 * @param currentVersion
		 *            the current version
		 * @param task
		 *            the task
		 * @return the builder
		 */
		public Builder addVersionUpdateTask(int currentVersion, SQLiteUpdateTask task) {
			updateTasks.add(new Pair<>(currentVersion, task));

			return this;
		}

		/**
		 * Adds the version update task.
		 *
		 * @param currentVersion
		 *            the current version
		 * @param updateSqlInputStream
		 *            the update sql input stream
		 * @return the builder
		 */
		public Builder addVersionUpdateTask(int currentVersion, InputStream updateSqlInputStream) {
			updateTasks.add(new Pair<>(currentVersion, new SQLiteUpdateTaskFromFile(updateSqlInputStream)));

			return this;
		}

		/**
		 * Adds the version update task.
		 *
		 * @param currentVersion
		 *            the current version
		 * @param context
		 *            the context
		 * @param updateSqlRawResourceId
		 *            the update sql raw resource id
		 * @return the builder
		 */
		public Builder addVersionUpdateTask(int currentVersion, Context context, int updateSqlRawResourceId) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(
					context.getResources().openRawResource(updateSqlRawResourceId));
			updateTasks.add(new Pair<>(currentVersion, task));

			return this;
		}

		/**
		 * Add populator for the database
		 * 
		 * @param populator
		 *            populator executed after database creation
		 * @return builder
		 */
		public Builder addPopulator(SQLitePopulator populator) {
			this.populator = populator;
			return this;
		}

		/**
		 * build and create test database.
		 *
		 * @return the SQ lite update test database
		 */
		public SQLiteTestDatabase build() {
			Collections.sort(updateTasks, new Comparator<Pair<Integer, ? extends SQLiteUpdateTask>>() {

				@Override
				public int compare(Pair<Integer, ? extends SQLiteUpdateTask> entry0,
						Pair<Integer, ? extends SQLiteUpdateTask> entry1) {
					return entry0.value0 - entry1.value0;
				}
			});

			SQLiteTestDatabase helper = new SQLiteTestDatabase(KriptonLibrary.getContext(), null, version, null,
					initialSchemaInputStream, initialSchemaResourceRawId, populator, updateTasks);

			return helper.create();
		}

	}

	/** The Constant MIGRATION_TEST. */
	public static final String TEST_DATABASE = "migration-test.db";

	/** The sqlite. */
	private SQLiteOpenHelper sqlite;

	/** The update tasks. */
	private List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks;

	/** The factory. */
	private CursorFactory factory;

	/** The error handler. */
	private DatabaseErrorHandler errorHandler;

	/** The version. */
	private int version;

	/** The first schema definition input stream. */
	private InputStream firstSchemaDefinitionInputStream;

	/** The first schema definition resource id. */
	private int firstSchemaDefinitionResourceId;

	/** The context. */
	private Context context;

	private SQLitePopulator populator;

	/**
	 * Instantiates a new SQ lite update test database.
	 *
	 * @param context
	 *            the context
	 * @param factory
	 *            the factory
	 * @param version
	 *            the version
	 * @param errorHandler
	 *            the error handler
	 * @param initialSchemaInputStream
	 *            the initial schema input stream
	 * @param initialSchemaResourceId
	 *            the initial schema resource id
	 * @param populator
	 *            populator to execute when database is created
	 * @param updateTasks
	 *            the update tasks
	 */
	SQLiteTestDatabase(Context context, CursorFactory factory, int version, DatabaseErrorHandler errorHandler,
			InputStream initialSchemaInputStream, int initialSchemaResourceId, SQLitePopulator populator,
			List<Pair<Integer, ? extends SQLiteUpdateTask>> updateTasks) {
		this.version = version;
		this.factory = factory;
		this.context = context;
		this.errorHandler = errorHandler;
		this.firstSchemaDefinitionInputStream = initialSchemaInputStream;
		this.firstSchemaDefinitionResourceId = initialSchemaResourceId;
		this.updateTasks = updateTasks;
		this.populator = populator;
	}

	/**
	 * create database with specified version by builder.
	 *
	 * @return the SQ lite update test database
	 */
	private SQLiteTestDatabase create() {
		sqlite = new SQLiteOpenHelper(context, TEST_DATABASE, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				throw (new KriptonRuntimeException("Unsupported situation"));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				if (firstSchemaDefinitionInputStream != null) {
					Logger.info("Load DDL from input stream");
					SQLiteTestUtils.executeSQL(database, firstSchemaDefinitionInputStream);
				} else {
					Logger.info("Load DDL from resourceId");
					SQLiteTestUtils.executeSQL(database, context, firstSchemaDefinitionResourceId);
				}
			}
		};
		
		sqlite.getWritableDatabase();
		

		if (this.populator != null) {
			this.populator.execute();
		}
		
		sqlite.close();

		return this;
	}

	/**
	 * Allows to update database version to <i>version</i>. This method allows to specify the destination version schema and compare it with schema resulting by version update
	 * applied.
	 *
	 * @param version
	 *            the version
	 * @param schemaDefinitionInputStream
	 *            the schema definition input stream
	 * @return the SQ lite update test database
	 */
	public SQLiteTestDatabase updateAndVerify(int version, final InputStream schemaDefinitionInputStream) {		
		sqlite = new SQLiteOpenHelper(context, TEST_DATABASE, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<SQLiteUpdateTask> task = findTask(oldVersion, newVersion);

				for (SQLiteUpdateTask item : task) {
					item.execute(db, oldVersion, oldVersion + 1);
					oldVersion++;
				}
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		SQLiteTestUtils.verifySchema(sqlite.getWritableDatabase(), schemaDefinitionInputStream);

		return this;
	}

	/**
	 * Delete database file.
	 *
	 * @param context
	 *            the context
	 */
	public static void clearDatabase(Context context) {
		File dbFile = context.getDatabasePath(SQLiteTestDatabase.TEST_DATABASE);
		Logger.info("Clear database file %s", dbFile.getAbsolutePath());
		if (!dbFile.delete()) {
			Logger.warn("Can not delete database " + dbFile.getAbsolutePath());
		}
	}

	/**
	 * Allow to update database version to <i>version</i>. This method allows to specify the destination version schema and compare it with schema resulting by version update
	 * applied.
	 *
	 * @param version
	 *            the version
	 * @param context
	 *            the context
	 * @param schemaDefinitionRawResourceId
	 *            the schema definition raw resource id
	 * @return the SQ lite update test database
	 */
	public SQLiteTestDatabase updateAndVerify(int version, final Context context,
			final int schemaDefinitionRawResourceId) {
		updateAndVerify(version, context.getResources().openRawResource(schemaDefinitionRawResourceId));

		return this;
	}

	/**
	 * Find task.
	 *
	 * @param previousVersion
	 *            the previous version
	 * @param currentVersion
	 *            the current version
	 * @return the list
	 */
	List<SQLiteUpdateTask> findTask(int previousVersion, int currentVersion) {
		List<SQLiteUpdateTask> result = new ArrayList<>();
		final One<Integer> ref = new One<>(null);
		for (int i = previousVersion; i < currentVersion; i++) {
			ref.value0 = i;
			SQLiteUpdateTask t = null;
			for (Pair<Integer, ? extends SQLiteUpdateTask> item : updateTasks) {
				if (item.value0 - 1 == ref.value0) {
					t = item.value1;
					break;
				}
			}

			if (t != null) {
				result.add(t);
			}
		}

		return result;

	}

}

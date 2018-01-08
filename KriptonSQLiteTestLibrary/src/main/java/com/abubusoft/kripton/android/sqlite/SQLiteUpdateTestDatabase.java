package com.abubusoft.kripton.android.sqlite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUpdateTestDatabase {

	public static Builder builder(int version, Context context, InputStream initialSchemaInputStream) {
		return new Builder(version, context, initialSchemaInputStream);
	}

	public static Builder builder(int version, Context context, int initialSchemaResourceId) {
		return new Builder(version, context, initialSchemaResourceId);
	}

	public static class Builder {

		private int version;

		private List<SQLiteUpdateTask> updateTasks;

		private InputStream initialSchemaInputStream;

		private int initialSchemaResourceId;

		private Context context;

		Builder(int version, Context context, InputStream initialSchemaInputStream) {
			this.version = version;
			this.context = context;
			this.initialSchemaInputStream = initialSchemaInputStream;
			this.updateTasks = new ArrayList<>();
		}

		Builder(int version, Context context, int initialSchemaResourceId) {
			this.version = version;
			this.context = context;
			this.initialSchemaResourceId = initialSchemaResourceId;
			this.updateTasks = new ArrayList<>();
		}

		public Builder addVersionUpdateTask(SQLiteUpdateTask task) {
			updateTasks.add(task);

			return this;
		}

		public Builder addVersionUpdateTask(int currentVersion, String updateSqlFileName) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(currentVersion, updateSqlFileName);
			updateTasks.add(task);

			return this;
		}

		public Builder addVersionUpdateTask(int currentVersion, InputStream updateSqlInputStream) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(currentVersion, updateSqlInputStream);
			updateTasks.add(task);

			return this;
		}

		public Builder addVersionUpdateTask(int currentVersion, Context context, int updateSqlResourceId) {
			SQLiteUpdateTaskFromFile task = new SQLiteUpdateTaskFromFile(currentVersion, context.getResources().openRawResource(updateSqlResourceId));
			updateTasks.add(task);

			return this;
		}

		public SQLiteUpdateTestDatabase build() {
			Collections.sort(updateTasks, new Comparator<SQLiteUpdateTask>() {

				@Override
				public int compare(SQLiteUpdateTask entry0, SQLiteUpdateTask entry1) {
					return (entry0.previousVersion == entry1.previousVersion) ? (entry0.currentVersion - entry1.currentVersion) : (entry0.previousVersion - entry1.previousVersion);
				}
			});

			SQLiteUpdateTestDatabase helper = new SQLiteUpdateTestDatabase(version, context, initialSchemaInputStream, 
					initialSchemaResourceId, updateTasks);

			return helper;
		}

	}

	private static final String MIGRATION_TEST = "migration-test";

	private SQLiteOpenHelper sqlite;

	private List<SQLiteUpdateTask> updateTasks;

	private CursorFactory factory;

	private DatabaseErrorHandler errorHandler;

	private int version;

	private InputStream firstSchemaDefinitionInputStream;

	private int firstSchemaDefinitionResourceId;

	private Context context;

	SQLiteUpdateTestDatabase(Context context, CursorFactory factory, int version, DatabaseErrorHandler errorHandler, InputStream initialSchemaInputStream,
			int initialSchemaResourceId, List<SQLiteUpdateTask> updateTasks) {
		this.version = version;
		this.factory = factory;
		this.context=context;
		this.errorHandler = errorHandler;
		this.firstSchemaDefinitionInputStream = initialSchemaInputStream;
		this.firstSchemaDefinitionResourceId = initialSchemaResourceId;
		this.updateTasks = updateTasks;
	}

	/**
	 * create database with specified version by builder.
	 * 
	 * @return
	 */
	public SQLiteUpdateTestDatabase create() {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				throw (new KriptonRuntimeException("Unsupported situation"));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				if (firstSchemaDefinitionInputStream != null) {
					Logger.info("Load DDL from input stream");
					SQLiteUpdateTaskHelper.executeSQLFromFile(database, firstSchemaDefinitionInputStream);
				} else {
					Logger.info("Load DDL from resourceId");
					SQLiteUpdateTaskHelper.executeSQLFromResourceId(database, firstSchemaDefinitionResourceId);
				}
			}
		};
		sqlite.getWritableDatabase();
		sqlite.close();

		return this;
	}

	/**
	 * Allow to update database version to <i>version</i>. This method allows to
	 * specify the destination version schema and compare it with schema
	 * resulting by version update applied.
	 * 
	 * @param version
	 * @param schemaDefinitionFileName
	 */
	public void updateAndVerify(int version, final String schemaDefinitionFileName) {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<SQLiteUpdateTask> task = findTask(oldVersion, newVersion);

				for (SQLiteUpdateTask item : task) {
					item.execute(db);
				}
				// task.forEach(item -> item.execute(db));
				SQLiteUpdateTaskHelper.getAllTables(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		try {
			SQLiteUpdateTaskHelper.verifySchema(sqlite.getWritableDatabase(), new FileInputStream(schemaDefinitionFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}

	/**
	 * Allow to update database version to <i>version</i>. This method allows to
	 * specify the destination version schema and compare it with schema
	 * resulting by version update applied.
	 * 
	 * @param version
	 * @param schemaDefinitionInputStream
	 */
	public void updateAndVerify(int version, final InputStream schemaDefinitionInputStream) {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<SQLiteUpdateTask> task = findTask(oldVersion, newVersion);

				for (SQLiteUpdateTask item : task) {
					item.execute(db);
				}
				// task.forEach(item -> item.execute(db));
				SQLiteUpdateTaskHelper.getAllTables(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		SQLiteUpdateTaskHelper.verifySchema(sqlite.getWritableDatabase(), schemaDefinitionInputStream);
	}

	/**
	 * Allow to update database version to <i>version</i>. This method allows to
	 * specify the destination version schema and compare it with schema
	 * resulting by version update applied.
	 * 
	 * @param version
	 * @param schemaDefinitionInputStream
	 */
	public void updateAndVerify(int version, final Integer schemaDefinitionResourceId) {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<SQLiteUpdateTask> task = findTask(oldVersion, newVersion);

				for (SQLiteUpdateTask item : task) {
					item.execute(db);
				}
				// task.forEach(item -> item.execute(db));
				SQLiteUpdateTaskHelper.getAllTables(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		SQLiteUpdateTaskHelper.verifySchema(sqlite.getWritableDatabase(), KriptonLibrary.context().getResources().openRawResource(schemaDefinitionResourceId));
	}

	List<SQLiteUpdateTask> findTask(int previousVersion, int currentVersion) {
		List<SQLiteUpdateTask> result = new ArrayList<>();
		final One<Integer> ref = new One<>(null);
		for (int i = previousVersion; i < currentVersion; i++) {
			ref.value0 = i;
			SQLiteUpdateTask t = null;
			for (SQLiteUpdateTask item : updateTasks) {
				if (item.previousVersion == ref.value0) {
					t = item;
					break;
				}
			}

			// SQLiteUpdateTask t = this.updateTasks.stream().filter(item ->
			// item.previousVersion == ref.value0).findFirst()
			// .orElse(null);

			if (t != null) {
				result.add(t);
			}
		}

		return result;

	}

}

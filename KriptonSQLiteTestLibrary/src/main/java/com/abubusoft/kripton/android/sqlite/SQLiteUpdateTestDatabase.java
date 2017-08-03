package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
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
import edu.emory.mathcs.backport.java.util.Collections;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUpdateTestDatabase {

	public static Builder builder(int version, String initialSchemaFileName) {
		return new Builder(version, initialSchemaFileName);
	}

	public static class Builder {

		private int version;

		private String initialSchemaFileName;

		private List<SQLiteUpdateTask> updateTasks;
		
		Builder(int version, String initialSchemaFileName) {
			this.version = version;
			this.initialSchemaFileName = initialSchemaFileName;
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

		public SQLiteUpdateTestDatabase build() {
			Collections.sort(updateTasks, new Comparator<SQLiteUpdateTask>() {

				@Override
				public int compare(SQLiteUpdateTask entry0, SQLiteUpdateTask entry1) {
					return (entry0.previousVersion == entry1.previousVersion) ? (entry0.currentVersion - entry1.currentVersion)
							: (entry0.previousVersion - entry1.previousVersion);
				}
			});
			
//			updateTasks.sort((entry0, entry1) -> (entry0.previousVersion == entry1.previousVersion) ? (entry0.currentVersion - entry1.currentVersion)
//					: (entry0.previousVersion - entry1.previousVersion));

			SQLiteUpdateTestDatabase helper = new SQLiteUpdateTestDatabase(KriptonLibrary.context(), null, version,
					null, initialSchemaFileName, updateTasks);

			return helper;
		}

	}

	private static final String MIGRATION_TEST = "migration-test";

	private SQLiteOpenHelper sqlite;

	private List<SQLiteUpdateTask> updateTasks;

	private CursorFactory factory;

	private DatabaseErrorHandler errorHandler;

	private Context context;

	private int version;

	private String firstSchemaDefinitionFile;

	SQLiteUpdateTestDatabase(Context context, CursorFactory factory, int version, DatabaseErrorHandler errorHandler,
			String schemaDefinitionFile, List<SQLiteUpdateTask> updateTasks) {
		this.version = version;
		this.context = context;
		this.factory = factory;
		this.errorHandler = errorHandler;
		this.firstSchemaDefinitionFile = schemaDefinitionFile;
		this.updateTasks = updateTasks;
	}

	public SQLiteUpdateTestDatabase create() {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				throw (new KriptonRuntimeException("Unsupported situation"));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				Logger.info("Load DDL from " + firstSchemaDefinitionFile);
				SQLiteUpdateTaskHelper.executeSQLFromFile(database, firstSchemaDefinitionFile);
			}
		};
		sqlite.getWritableDatabase();
		sqlite.close();

		return this;
	}

	public void updateAndVerify(int version, final String schemaDefinitionFileName) {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<SQLiteUpdateTask> task = findTask(oldVersion, newVersion);
				
				for (SQLiteUpdateTask item: task) {
					item.execute(db);
				}
				//task.forEach(item -> item.execute(db));
				SQLiteUpdateTaskHelper.getAllTables(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		SQLiteUpdateTaskHelper.verifySchema(sqlite.getWritableDatabase(), schemaDefinitionFileName);
	}

	List<SQLiteUpdateTask> findTask(int previousVersion, int currentVersion) {
		List<SQLiteUpdateTask> result = new ArrayList<>();
		final One<Integer> ref = new One<>(null);
		for (int i = previousVersion; i < currentVersion; i++) {
			ref.value0 = i;
			SQLiteUpdateTask t=null;
			for (SQLiteUpdateTask item: updateTasks) {
				if (item.previousVersion == ref.value0) {
					t=item;
					break;
				}
			}
			
//			SQLiteUpdateTask t = this.updateTasks.stream().filter(item -> item.previousVersion == ref.value0).findFirst()
//					.orElse(null);

			if (t != null) {
				result.add(t);
			}
		}

		return result;

	}

}

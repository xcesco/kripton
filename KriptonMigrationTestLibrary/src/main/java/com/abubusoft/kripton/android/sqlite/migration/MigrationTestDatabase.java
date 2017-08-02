package com.abubusoft.kripton.android.sqlite.migration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DatabaseVersionUpdateTask;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MigrationTestDatabase {
	
	public static Builder builder(int version, String initialSchemaFileName) {
		return new Builder(version, initialSchemaFileName);
	}
	
	public static class Builder {

		private int version;
		
		private String initialSchemaFileName;
		
		private List<Triple<Integer, Integer, DatabaseVersionUpdateTask>> list;

		Builder(int version, String initialSchemaFileName) {
			this.version=version;
			this.initialSchemaFileName=initialSchemaFileName;
		}				
		
		public Builder addVersionUpdateTask(DatabaseVersionUpdateTask task) {
			Triple<Integer, Integer, DatabaseVersionUpdateTask> item = new Triple<>(task.previousVersion, task.currentVersion, task);

			list.add(item);
			list.sort((entry0, entry1) -> (entry0.value0 == entry1.value0) ? (entry0.value1 - entry1.value1) : (entry0.value0 - entry1.value0));
			
			return this;
		}

		public Builder addVersionUpdateTask(int previousVersion, int currentVersion, String updateSqlFileName) {
			Triple<Integer, Integer, DatabaseVersionUpdateTask> item = new Triple<>(previousVersion, currentVersion,
					new DatabaseVersionUpdateFromFileTask(previousVersion, currentVersion, updateSqlFileName));

			list.add(item);
			list.sort((entry0, entry1) -> (entry0.value0 == entry1.value0) ? (entry0.value1 - entry1.value1) : (entry0.value0 - entry1.value0));
			
			return this;
		}
		
		public MigrationTestDatabase build() {
			MigrationTestDatabase helper = new MigrationTestDatabase(KriptonLibrary.context(), null, version, null, initialSchemaFileName);

			return helper;
		}

		
	}

	private static final String MIGRATION_TEST = "migration-test";

	private SQLiteOpenHelper sqlite;

	private List<Triple<Integer, Integer, DatabaseVersionUpdateTask>> list;

	private CursorFactory factory;

	private DatabaseErrorHandler errorHandler;

	private Context context;

	private int version;

	private String firstSchemaDefinitionFile;

	MigrationTestDatabase(Context context, CursorFactory factory, int version, DatabaseErrorHandler errorHandler, String schemaDefinitionFile) {
		this.version = version;
		this.context = context;
		this.factory = factory;
		this.errorHandler = errorHandler;
		this.firstSchemaDefinitionFile = schemaDefinitionFile;
		this.list = new ArrayList<Triple<Integer, Integer, DatabaseVersionUpdateTask>>();
	}

	public void create() {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				throw (new KriptonRuntimeException("Unsupported situation"));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				Logger.info("Load DDL from " + firstSchemaDefinitionFile);
				MigrationTestHelper.executeSQLFromFile(database, firstSchemaDefinitionFile);
			}
		};
		sqlite.getWritableDatabase();
		sqlite.close();
	}

	public void updateAndVerify(int version, final String schemaDefinitionFile) {
		sqlite = new SQLiteOpenHelper(context, MIGRATION_TEST, factory, version, errorHandler) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				List<DatabaseVersionUpdateTask> task = findTask(oldVersion, newVersion);

				task.forEach(item -> item.execute(db));

				MigrationTestHelper.getAllTables(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				throw (new KriptonRuntimeException("Unsupported situation"));

			}
		};

		SQLiteDatabase db = sqlite.getWritableDatabase();

		Set<String> actualSql = new HashSet<String>();

		actualSql.addAll(MigrationTestHelper.getAllTables(db).values());
		actualSql.addAll(MigrationTestHelper.getAllIndexes(db).values());

		List<String> expectedSQL = MigrationTestHelper.readSQLFromFile(schemaDefinitionFile);

		if (actualSql.size() != expectedSQL.size()) {
			throw new KriptonRuntimeException("numer of tables and indexes between aspected and actual schema are different");
		}

		expectedSQL.forEach(item -> {
			if (!actualSql.contains(item)) {
				actualSql.forEach(item1->Logger.info("actual: "+item1));
				expectedSQL.forEach(item1->Logger.info("expected: "+item1));
				
				throw new KriptonRuntimeException("not found element: "+item);
			}
		});

		sqlite.close();
	}

	List<DatabaseVersionUpdateTask> findTask(int previousVersion, int currentVersion) {
		List<DatabaseVersionUpdateTask> result = new ArrayList<>();
		final One<Integer> ref = new One<>(null);
		for (int i = previousVersion; i < currentVersion; i++) {
			ref.value0 = i;
			DatabaseVersionUpdateTask t = this.list.stream().filter(item -> item.value0 == ref.value0).findFirst().map(item -> item.value2).orElse(null);

			result.add(t);
		}

		return result;

	}

}

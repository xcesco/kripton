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
/**
 * 
 */
package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>
 * Base class for data source
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
public abstract class AbstractDataSource implements AutoCloseable, SQLContext {

	/**
	 * Interface for database operations.
	 * 
	 *
	 * @param <E>
	 */
	public interface AbstractExecutable<E extends BindDaoFactory> {
		/**
		 * Manages error situations.
		 * 
		 * @param e
		 * 		exception
		 */
		void onError(Throwable e);
	}


	enum TypeStatus {
		CLOSED, READ_AND_WRITE_OPENED, READ_ONLY_OPENED
	}

	/**
	 * database instance
	 */
	SQLiteDatabase database;
	
	/**
	 * used to clear prepared statements
	 */
	public abstract void clearCompiledStatements();
	
	public boolean logEnabled;
	
	private final ReentrantReadWriteLock lockAccess = new ReentrantReadWriteLock();

	private final ReentrantLock lockDb = new ReentrantLock();

	private final Lock lockReadAccess = lockAccess.readLock();

	private final Lock lockReadWriteAccess = lockAccess.writeLock();

	/**
	 * <p>
	 * <file name used to save database,/p>
	 */
	public final String name;

	private AtomicInteger openCounter = new AtomicInteger();

	protected DataSourceOptions options;

	protected SQLiteOpenHelper sqliteHelper;
	
	protected SQLContextImpl context;
	
	public SQLContext context() {
		return context;
	}
	
	@Override
	public KriptonContentValues contentValuesForUpdate() {
		return context.contentValuesForUpdate();
	}

	@Override
	public KriptonContentValues contentValues() {
		return context.contentValues();
	}

	@Override
	public KriptonContentValues contentValues(ContentValues values) {
		return context.contentValues(values);
	}

	@Override
	public StringBuilder sqlBuilder() {
		return context.sqlBuilder();
	}

	public boolean isLogEnabled() {
		return context.isLogEnabled();
	}

	protected ThreadLocal<TypeStatus> status = new ThreadLocal<TypeStatus>() {

		@Override
		protected TypeStatus initialValue() {
			return TypeStatus.CLOSED;
		}
	};

	/**
	 * if true, database was update during this application run
	 */
	protected boolean versionChanged;

	/**
	 * <p>
	 * database version
	 * </p>
	 */
	protected int version;

	public int getVersion() {
		return version;
	}

	void setVersion(int version) {
		this.version = version;
	}

	protected AbstractDataSource(String name, int version, DataSourceOptions options) {
		this.name = name;
		this.version = version;
		this.context=new SQLContextImpl(this);
		this.options = options == null ? DataSourceOptions.builder().build() : options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#close()
	 */
	@Override
	public void close() {
		lockDb.lock();
		try {
			if (openCounter.decrementAndGet() <= 0) {
				// Closing database
				if (database != null) {	
					clearCompiledStatements();
					database.close();
				}
				database = null;
				if (logEnabled) Logger.info("database CLOSED (%s) (connections: %s)", status.get(), openCounter.intValue());
			} else {
				if (logEnabled) Logger.info("database RELEASED (%s) (connections: %s)", status.get(), openCounter.intValue());
			}
		} finally {
			switch (status.get()) {
			case READ_AND_WRITE_OPENED:
				if (database == null)
					status.set(TypeStatus.CLOSED);
				lockReadWriteAccess.unlock();
				lockDb.unlock();
				break;
			case READ_ONLY_OPENED:
				if (database == null)
					status.set(TypeStatus.CLOSED);
				lockReadAccess.unlock();
				lockDb.unlock();
				break;
			case CLOSED:
				// do nothing
				lockDb.unlock();
				break;
			default:
				lockDb.unlock();
				throw (new KriptonRuntimeException("Inconsistent status"));
			}
			
		}

	}

	void forceClose() {
		openCounter.set(0);
	}

	protected SQLiteUpdateTask findPopulateTaskList(int currentVersion) {
		for (SQLiteUpdateTask item : this.options.updateTasks) {
			if (item.previousVersion == item.currentVersion && item.currentVersion == currentVersion) {
				return item;
			}
		}

		return null;
	}

	protected List<SQLiteUpdateTask> buildTaskList(int previousVersion, int currentVersion) {
		int counter = currentVersion - previousVersion > 0 ? 1 : -1;
		List<SQLiteUpdateTask> result = new ArrayList<>();

		for (SQLiteUpdateTask item : this.options.updateTasks) {
			if (item.previousVersion == previousVersion && item.currentVersion == item.previousVersion + counter) {
				result.add(item);

				previousVersion = item.currentVersion;
			}

			if (previousVersion == currentVersion)
				break;
		}

		if (previousVersion != currentVersion) {
			throw (new KriptonRuntimeException(
					String.format("Can not find version update task from version %s to version %s", previousVersion,
							currentVersion)));
		}

		return result;

	}

	protected void createHelper(DataSourceOptions options) {
		if (KriptonLibrary.context() == null)
			throw new KriptonRuntimeException(
					"Kripton library is not properly initialized. Please use KriptonLibrary.init(context) somewhere at application startup");

		sqliteHelper = new SQLiteOpenHelper(KriptonLibrary.context(), name, options.factory, version,
				options.errorHandler) {

			@Override
			public void onConfigure(SQLiteDatabase database) {
				AbstractDataSource.this.database = database;
				AbstractDataSource.this.onConfigure(database);
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				AbstractDataSource.this.onCreate(database);
			}

			@Override
			public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				AbstractDataSource.this.onDowngrade(database, oldVersion, newVersion);
			}

			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				AbstractDataSource.this.onUpgrade(database, oldVersion, newVersion);
			}
		};
	}

	/**
	 * <p>
	 * Return database object or runtimeexception if no database is opened.
	 * </p>
	 * 
	 * @return
	 */
	public SQLiteDatabase database() {
		if (database == null)
			throw (new KriptonRuntimeException(
					"No database connection is opened before use " + this.getClass().getCanonicalName()));
		return database;
	}

	/**
	 * <p>
	 * return true if database is already opened.
	 * </p>
	 * 
	 * @return true if database is opened, otherwise false
	 */
	public boolean isOpen() {
		return database != null && database.isOpen() && database.isDbLockedByCurrentThread();
	}
	
	/**
	 * <p>
	 * return true if database is already opened in write mode.
	 * </p>
	 * 
	 * @return true if database is opened, otherwise false
	 */
	public boolean isOpenInWriteMode() {
		return database != null && database.isOpen() && !database.isReadOnly() && database.isDbLockedByCurrentThread();
	}

	/**
	 * @return the upgradedVersion
	 */
	public boolean isUpgradedVersion() {
		return versionChanged;
	}

	public abstract void onConfigure(SQLiteDatabase database);

	public abstract void onCreate(SQLiteDatabase database);

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
			AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, false);
			versionChanged = true;
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
			AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, true);
			versionChanged = true;
		}
	}

	/**
	 * <p>
	 * Open a read only database.
	 * </p>
	 * 
	 * @return read only database
	 */
	public SQLiteDatabase openReadOnlyDatabase() {
		lockDb.lock();
		try {
			if (sqliteHelper == null)
				createHelper(options);

			status.set(TypeStatus.READ_ONLY_OPENED);

			if (openCounter.incrementAndGet() == 1) {
				// open new read database
				sqliteHelper.setWriteAheadLoggingEnabled(true);
				database = sqliteHelper.getReadableDatabase();
				
				if (logEnabled) Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue()));
			} else {
				if (logEnabled) Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue()));
			}
		} finally {
			lockDb.unlock();
			lockReadAccess.lock();
		}

		return database;
	}

	/**
	 * <p>
	 * open a writable database.
	 * </p>
	 * 
	 * @return writable database
	 */
	public SQLiteDatabase openWritableDatabase() {
		lockDb.lock();
		try {
			if (sqliteHelper == null)
				createHelper(options);

			status.set(TypeStatus.READ_AND_WRITE_OPENED);
			
			if (openCounter.incrementAndGet() == 1) {
				// open new write database
				sqliteHelper.setWriteAheadLoggingEnabled(true);
				database = sqliteHelper.getWritableDatabase();
				if (logEnabled) Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue()));
			} else {
				if (logEnabled) Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue()));
			}
		} finally {
			lockDb.unlock();
			lockReadWriteAccess.lock();
		}

		return database;
	}

	/**
	 * Define options for data source. It must be defined
	 * <strong>before</strong> open first connection.
	 * 
	 * @param options
	 *            options
	 */
	public void setOptions(DataSourceOptions options) {
		this.options = options;
	}

}

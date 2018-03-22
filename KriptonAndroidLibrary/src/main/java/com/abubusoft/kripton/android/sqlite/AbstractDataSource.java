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
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * <p>
 * Base class for data source
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
public abstract class AbstractDataSource implements AutoCloseable {

	protected OnErrorListener onErrorListener = new OnErrorListener() {
		@Override
		public void onError(Throwable e) {
			throw (new KriptonRuntimeException(e));
		}
	};

	public void onSessionOpened() {
		this.context.onSessionOpened();

	}

	public void onSessionClosed() {
		this.context.onSessionClosed();

	}

	/**
	 * Get error listener, in transations
	 * 
	 * @return
	 */
	public OnErrorListener getOnErrorListener() {
		return onErrorListener;
	}

	/**
	 * Set error listener for transactions
	 * 
	 * @param onErrorListener
	 */
	public void setOnErrorListener(OnErrorListener onErrorListener) {
		this.onErrorListener = onErrorListener;
	}

	/**
	 * Interface for database operations.
	 * 
	 *
	 * @param <E>
	 */
	public interface AbstractExecutable<E extends BindDaoFactory> {
		/**
		 * Execute transation. Method need to return
		 * {@link TransactionResult#COMMIT} to commit results or
		 * {@link TransactionResult#ROLLBACK} to rollback. If exception is
		 * thrown, a rollback will be done.
		 *
		 * @param daoFactory
		 * @return
		 * @throws Throwable
		 */
		TransactionResult onExecute(E daoFactory);
	}

	public interface OnErrorListener {
		/**
		 * Manages error situations.
		 * 
		 * @param e
		 *            exception
		 */
		void onError(Throwable e);
	}

	public static enum TypeStatus {
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

	protected SQLContext context() {
		return context;
	}

	protected KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement) {
		return context.contentValuesForUpdate(compiledStatement);
	}

	protected KriptonContentValues contentValues(SQLiteStatement compiledStatement) {
		return context.contentValues(compiledStatement);
	}

	protected KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		return context.contentValuesForContentProvider(values);
	}

	protected StringBuilder sqlBuilder() {
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
	 * True if dataSource is just created
	 * </p>
	 */
	protected boolean justCreated = false;

	/**
	 * <p>
	 * True if dataSource is just created
	 * </p>
	 */
	public boolean isJustCreated() {
		return justCreated;
	}

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
		DataSourceOptions optionsValue = (options == null) ? DataSourceOptions.builder().build() : options;

		this.name = options.inMemory ? null : name;
		this.version = version;
		
		// create new SQLContext
		this.context = new SQLContextImpl(this);
		
		this.options = optionsValue;
		this.logEnabled = optionsValue.logEnabled;
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

				if (!this.options.inMemory) {
					// Closing database
					if (database != null) {
						clearCompiledStatements();
						database.close();
					}
					database = null;
				}
				if (logEnabled)
					Logger.info("database CLOSED (%s) (connections: %s)", status.get(), openCounter.intValue());
			} else {
				if (logEnabled)
					Logger.info("database RELEASED (%s) (connections: %s)", status.get(), openCounter.intValue());
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

	protected List<SQLiteUpdateTask> buildTaskList(int previousVersion, int currentVersion) {
		List<SQLiteUpdateTask> result = new ArrayList<>();

		for (Pair<Integer, ? extends SQLiteUpdateTask> item : this.options.updateTasks) {
			if (item.value0 - 1 == previousVersion) {
				result.add(item.value1);
				previousVersion = item.value0;
			}

			if (previousVersion == currentVersion)
				break;
		}

		if (previousVersion != currentVersion) {
			Logger.warn(String.format("Can not find version update task from version %s to version %s", previousVersion, currentVersion));
		}

		return result;

	}

	protected void createHelper(DataSourceOptions options) {
		if (KriptonLibrary.context() == null)
			throw new KriptonRuntimeException("Kripton library is not properly initialized. Please use KriptonLibrary.init(context) somewhere at application startup");

		sqliteHelper = new SQLiteOpenHelper(KriptonLibrary.context(), name, options.factory, version, options.errorHandler) {

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
			throw (new KriptonRuntimeException("No database connection is opened before use " + this.getClass().getCanonicalName()));
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
		return database != null && database.isOpen();// &&
														// database.isDbLockedByCurrentThread();
	}

	/**
	 * <p>
	 * return true if database is already opened in write mode.
	 * </p>
	 * 
	 * @return true if database is opened, otherwise false
	 */
	public boolean isOpenInWriteMode() {
		// return database != null && database.isOpen() &&
		// !database.isReadOnly() && database.isDbLockedByCurrentThread();
		return database != null && !database.isReadOnly();
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
				if (database == null) {
					sqliteHelper.setWriteAheadLoggingEnabled(true);
					database = sqliteHelper.getReadableDatabase();
				}

				if (logEnabled)
					Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
			} else {
				if (logEnabled)
					Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
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
				if (database == null) {
					sqliteHelper.setWriteAheadLoggingEnabled(true);
					database = sqliteHelper.getWritableDatabase();
				}
				if (logEnabled)
					Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
			} else {
				if (logEnabled)
					Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
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

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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
 * .
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractDataSource implements AutoCloseable {

	/** The on error listener. */
	protected OnErrorListener onErrorListener = new OnErrorListener() {
		@Override
		public void onError(Throwable e) {
			throw (new KriptonRuntimeException(e));
		}
	};

	/**
	 * On session opened.
	 */
	protected void onSessionOpened() {
		this.context.onSessionOpened();

	}

	/**
	 * On session closed.
	 *
	 * @return the sets the
	 */
	protected Set<Integer> onSessionClosed() {
		return this.context.onSessionClosed();

	}

	/**
	 * Get error listener, in transations.
	 *
	 * @return the on error listener
	 */
	public OnErrorListener getOnErrorListener() {
		return onErrorListener;
	}

	/**
	 * Set error listener for transactions.
	 *
	 * @param onErrorListener the new on error listener
	 */
	public void setOnErrorListener(OnErrorListener onErrorListener) {
		this.onErrorListener = onErrorListener;
	}

	/**
	 * Interface for database operations.
	 *
	 * @param <E> the element type
	 */
	public interface AbstractExecutable<E extends BindDaoFactory> {

		/**
		 * Execute transation. Method need to return {@link TransactionResult#COMMIT} to
		 * commit results or {@link TransactionResult#ROLLBACK} to rollback. If
		 * exception is thrown, a rollback will be done.
		 *
		 * @param daoFactory the dao factory
		 * @return the transaction result
		 */
		TransactionResult onExecute(E daoFactory);
	}

	/**
	 * The listener interface for receiving onError events. The class that is
	 * interested in processing a onError event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addOnErrorListener</code> method. When the onError event
	 * occurs, that object's appropriate method is invoked.
	 *
	 */
	public interface OnErrorListener {
		/**
		 * Manages error situations.
		 * 
		 * @param e exception
		 */
		void onError(Throwable e);
	}

	/**
	 * The Enum TypeStatus.
	 */
	public static enum TypeStatus {

		/** The closed. */
		CLOSED,
		/** The read and write opened. */
		READ_AND_WRITE_OPENED,
		/** The read only opened. */
		READ_ONLY_OPENED
	}

	/** database instance. */
	SQLiteDatabase database;

	/**
	 * used to clear prepared statements.
	 */
	public abstract void clearCompiledStatements();

	/** The log enabled. */
	public boolean logEnabled;

	/** The lock db. */
	private final ReentrantLock lockDb = new ReentrantLock();

	protected void beginLock() {
		lockDb.lock();
	}

	protected void endLock() {
		lockDb.unlock();
	}

	/** The lock access. */
	private final ReentrantReadWriteLock lockAccess = new ReentrantReadWriteLock();

	/** The lock read access. */
	private final Lock lockReadAccess = lockAccess.readLock();

	/** The lock read write access. */
	private final Lock lockReadWriteAccess = lockAccess.writeLock();

	/**
	 * <p>
	 * file name used to save database,
	 * </p>
	 * .
	 */
	public final String name;

	/** The open counter. */
	private AtomicInteger openCounter = new AtomicInteger();

	/** The options. */
	protected DataSourceOptions options;

	/** The sqlite helper. */
	protected SQLiteOpenHelper sqliteHelper;

	/** The context. */
	protected SQLContextImpl context;

	/**
	 * Context.
	 *
	 * @return the SQL context
	 */
	public SQLContext context() {
		return context;
	}

	/**
	 * Content values for update.
	 *
	 * @param compiledStatement the compiled statement
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement) {
		return context.contentValuesForUpdate(compiledStatement);
	}

	/**
	 * Content values.
	 *
	 * @param compiledStatement the compiled statement
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValues(SQLiteStatement compiledStatement) {
		return context.contentValues(compiledStatement);
	}

	/**
	 * Content values for content provider.
	 *
	 * @param values the values
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		return context.contentValuesForContentProvider(values);
	}

	/**
	 * Sql builder.
	 *
	 * @return the string builder
	 */
	protected StringBuilder sqlBuilder() {
		return context.sqlBuilder();
	}

	/**
	 * Checks if is log enabled.
	 *
	 * @return true, if is log enabled
	 */
	public boolean isLogEnabled() {
		return context.isLogEnabled();
	}

	/** The status. */
	protected ThreadLocal<TypeStatus> status = new ThreadLocal<TypeStatus>() {

		@Override
		protected TypeStatus initialValue() {
			return TypeStatus.CLOSED;
		}

	};

	/** if true, database was update during this application run. */
	protected boolean versionChanged;

	/**
	 * <p>
	 * True if dataSource is just created
	 * </p>
	 * .
	 */
	protected boolean justCreated = false;

	/**
	 * <p>
	 * True if dataSource is just created
	 * </p>
	 * .
	 *
	 * @return true, if is just created
	 */
	public boolean isJustCreated() {
		return justCreated;
	}

	/**
	 * <p>
	 * database version
	 * </p>
	 * .
	 */
	protected int version;

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Instantiates a new abstract data source.
	 *
	 * @param name    the name
	 * @param version the version
	 * @param options the options
	 */
	protected AbstractDataSource(String name, int version, DataSourceOptions options) {
		DataSourceOptions optionsValue = (options == null) ? DataSourceOptions.builder().build() : options;

		this.name = options.inMemory ? null : name;
		this.version = version;

		// create new SQLContext
		this.context = new SQLContextImpl(this);

		this.options = optionsValue;
		this.logEnabled = optionsValue.logEnabled;
	}

	protected void closeThreadSafeMode(Pair<Boolean, SQLiteDatabase> status) {
		if (status.value0) {
			close();
		} else {
			beginLock();
			// we unlock lockReadWriteAccess, so we can include this code in lockDb
			manageStatus();
			endLock();

			// unlocked inside
			// lockDb.unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#close()
	 */
	@Override
	public void close() {
		beginLock();
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
			manageStatus();
			endLock();
		}

	}

	/**
	 * 
	 */
	private void manageStatus() {
		switch (status.get()) {
		case READ_AND_WRITE_OPENED:
			if (database == null)
				status.set(TypeStatus.CLOSED);
			lockReadWriteAccess.unlock();
			// lockDb.unlock();
			break;
		case READ_ONLY_OPENED:
			if (database == null)
				status.set(TypeStatus.CLOSED);
			lockReadAccess.unlock();
			// lockDb.unlock();
			break;
		case CLOSED:
			// do nothing
			// lockDb.unlock();
			break;
		}
	}

	/**
	 * Force close.
	 */
	void forceClose() {
		openCounter.set(0);
	}

	/**
	 * Builds the task list.
	 *
	 * @param previousVersion the previous version
	 * @param currentVersion  the current version
	 * @return the list
	 */
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
			Logger.warn(String.format("Can not find version update task from version %s to version %s", previousVersion,
					currentVersion));
		}

		return result;

	}

	/**
	 * Creates the helper.
	 *
	 * @param options the options
	 */
	protected void createHelper(DataSourceOptions options) {
		if (KriptonLibrary.getContext() == null)
			throw new KriptonRuntimeException(
					"Kripton library is not properly initialized. Please use KriptonLibrary.init(context) somewhere at application startup");

		if (this.logEnabled) {
			if (options.inMemory) {
				Logger.info("In-memory database");
			} else {
				File dbFile = KriptonLibrary.getContext().getDatabasePath(name);
				Logger.info("Database file %s", dbFile.getAbsolutePath());
			}
		}

		sqliteHelper = new SQLiteOpenHelper(KriptonLibrary.getContext(), name, options.factory, version,
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
	 * @return the SQ lite database
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
		// return database != null && database.isOpen() &&
		// !database.isReadOnly() && database.isDbLockedByCurrentThread();
		return database != null && database.isOpen() && !database.isReadOnly() && database.isDbLockedByCurrentThread();
	}

	/**
	 * Checks if is upgraded version.
	 *
	 * @return the upgradedVersion
	 */
	public boolean isUpgradedVersion() {
		return versionChanged;
	}

	/**
	 * On configure.
	 *
	 * @param database the database
	 */
	public abstract void onConfigure(SQLiteDatabase database);

	/**
	 * On create.
	 *
	 * @param database the database
	 */
	public abstract void onCreate(SQLiteDatabase database);

	/**
	 * On downgrade.
	 *
	 * @param db         the db
	 * @param oldVersion the old version
	 * @param newVersion the new version
	 */
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
			AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, false);
			versionChanged = true;
		}
	}

	/**
	 * On upgrade.
	 *
	 * @param db         the db
	 * @param oldVersion the old version
	 * @param newVersion the new version
	 */
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
	protected SQLiteDatabase openReadOnlyDatabase(boolean lock) {
		if (lock) {
			// if I lock this in dbLock.. the last one remains locked too
			lockReadAccess.lock();

			beginLock();
		}

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
			if (lock)
				endLock();

		}

		return database;
	}

	public SQLiteDatabase openReadOnlyDatabase() {
		return openReadOnlyDatabase(true);
	}

	/**
	 * <p>
	 * open a writable database.
	 * </p>
	 * 
	 * @return writable database
	 */
	public SQLiteDatabase openWritableDatabase() {
		return openWritableDatabase(true);
	}

	/**
	 * Open a database, if it is needed in
	 * 
	 * @param writeMode
	 * @return
	 */
	protected Pair<Boolean, SQLiteDatabase> openDatabaseThreadSafeMode(boolean writeMode) {
		Pair<Boolean, SQLiteDatabase> result = new Pair<Boolean, SQLiteDatabase>();

		try {
			// lock entire operation set
			beginLock();
			boolean needToOpened = writeMode ? !this.isOpenInWriteMode() : !this.isOpen();
			result.value0 = needToOpened;
			// in this part we can not lock lockReadWriteAccess, otherwise it may be a
			// blocking race
			// we lock lockReadWriteAccess after we release
			if (needToOpened) {
				if (writeMode) {
					result.value1 = openWritableDatabase(false);
				} else {
					result.value1 = openReadOnlyDatabase(false);
				}
			}

		} finally {
			// unlock entire operation set
			endLock();

			if (writeMode) {
				lockReadWriteAccess.lock();
			} else {
				lockReadAccess.lock();
			}

		}

		return result;

	}

	protected SQLiteDatabase openWritableDatabase(boolean lock) {
		if (lock) {
			lockReadWriteAccess.lock();

			// if I lock this in dbLock.. the last one remains locked too
			beginLock();
		}

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
			if (lock)
				endLock();
		}

		return database;
	}

}

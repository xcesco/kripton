/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>
 * Base class for data source
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * 
 */
public abstract class AbstractDataSource implements AutoCloseable {

	/**
	 * Interface for database transactions.
	 * 
	 * @author Francesco Benincasa (abubusoft@gmail.com)
	 *
	 * @param <E>
	 */
	public interface AbstractTransaction<E extends BindDaoFactory> {
		void onError(Throwable e);

		/**
		 * Execute transaction. Connection is managed from DataSource
		 * 
		 * @param daoFactory
		 * @return true to commit, false to rollback
		 * 
		 * @exception any
		 *                exception
		 */
		boolean onExecute(E daoFactory) throws Throwable;
	}

	enum TypeStatus {
		CLOSED, READ_AND_WRITE_OPENED, READ_ONLY_OPENED
	}

	/**
	 * database instance
	 */
	protected SQLiteDatabase database;

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

	protected DataSourceOptions options = DataSourceOptions.build();

	protected SQLiteOpenHelper sqliteHelper;

	protected ThreadLocal<TypeStatus> status = new ThreadLocal<TypeStatus>() {

		@Override
		protected TypeStatus initialValue() {
			return TypeStatus.CLOSED;
		}
	};

	/**
	 * if true, database was update during this application run
	 */
	protected boolean upgradedVersion;

	/**
	 * <p>
	 * database version
	 * </p>
	 */
	public final int version;

	protected AbstractDataSource(String name, int version) {
		this.name = name;
		this.version = version;
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
			if (openCounter.decrementAndGet() == 0) {
				// Closing database
				database.close();
				database = null;
			}
			Logger.info("database CLOSE (%s) (id: %s)", status.get(), openCounter.intValue());

		} finally {
			lockDb.unlock();
			switch (status.get()) {
			case READ_AND_WRITE_OPENED:
				if (database == null)
					status.set(TypeStatus.CLOSED);
				lockReadWriteAccess.unlock();
				break;
			case READ_ONLY_OPENED:
				if (database == null)
					status.set(TypeStatus.CLOSED);
				lockReadAccess.unlock();
				break;
			default:
				throw (new KriptonRuntimeException("Inconsistent status"));
			}
		}

	}

	protected void createHelper(DataSourceOptions options) {
		if (KriptonLibrary.context()==null) throw new KriptonRuntimeException("Kripton library is not properly initialized. Please use KriptonLibrary.init(context) somewhere at application startup");
		
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
		return database != null && database.isOpen();
	}

	/**
	 * @return the upgradedVersion
	 */
	public boolean isUpgradedVersion() {
		return upgradedVersion;
	}

	public abstract void onConfigure(SQLiteDatabase database);

	public abstract void onCreate(SQLiteDatabase database);

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
			AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, false);
			upgradedVersion = true;
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
			AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, true);
			upgradedVersion = true;
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
				database = sqliteHelper.getReadableDatabase();
			}
		} finally {
			lockDb.unlock();
			lockReadAccess.lock();
			Logger.info("database OPEN %s (id: %s)", status.get(), (openCounter.intValue() - 1));
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
				database = sqliteHelper.getWritableDatabase();
			}
		} finally {
			lockDb.unlock();
			lockReadWriteAccess.lock();
			Logger.info("database OPEN %s (id: %s)", status.get(), (openCounter.intValue() - 1));
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

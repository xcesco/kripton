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

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>
 * Base class for data source
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * 
 */
public abstract class AbstractDataSource extends SQLiteOpenHelper implements AutoCloseable {

	public enum TypeStatus {
		READ_ONLY_OPENED, READ_AND_WRITE_OPENED, CLOSED
	}

	protected ThreadLocal<TypeStatus> status = new ThreadLocal<TypeStatus>() {

		@Override
		protected TypeStatus initialValue() {
			return TypeStatus.CLOSED;
		}
	};

	private final ReentrantLock lockDb = new ReentrantLock();

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	private final Lock r = rwl.readLock();

	protected final Lock w = rwl.writeLock();

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

	/**
	 * Manage upgrade or downgrade of database.
	 * 
	 * @author Francesco Benincasa (abubusoft@gmail.com)
	 *
	 */
	public interface OnDatabaseListener {

		/**
		 * <p>
		 * Method for DDL or DML
		 * 
		 * @param db
		 *            database
		 * @param oldVersion
		 *            current version of database
		 * @param newVersion
		 *            new version of database
		 * @param upgrade
		 *            if true is an upgrade operation, otherwise it's a
		 *            downgrade operation.
		 */
		void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion, boolean upgrade);

		/**
		 * Invoked after execution of DDL necessary to create database.
		 * 
		 * @param database
		 */
		void onCreate(SQLiteDatabase database);

		/**
		 * Invoked during database configuration.
		 * 
		 * @param database
		 */
		void onConfigure(SQLiteDatabase database);
	}

	/**
	 * database instance
	 */
	protected SQLiteDatabase database;

	/**
	 * listener to execute code during database upgrade events
	 */
	protected OnDatabaseListener databaseListener;

	private AtomicInteger openCounter = new AtomicInteger();

	/**
	 * if true, database was update during this application run
	 */
	protected boolean upgradedVersion;

	public AbstractDataSource(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public AbstractDataSource(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#close()
	 */
	@Override
	public void close() {
		lockDb.lock();
		if (openCounter.decrementAndGet() == 0) {
			// Closing database
			database.close();
			database = null;
		}

		Logger.info("database CLOSE (%s) (id: %s)", status.get(), openCounter.intValue());
		lockDb.unlock();
		switch (status.get()) {
		case READ_AND_WRITE_OPENED:
			if (database == null)
				status.set(TypeStatus.CLOSED);
			w.unlock();
			break;
		case READ_ONLY_OPENED:
			if (database == null)
				status.set(TypeStatus.CLOSED);
			r.unlock();
			break;
		default:
			throw (new KriptonRuntimeException("Inconsistent status"));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#getReadableDatabase()
	 */
	@Override
	public SQLiteDatabase getReadableDatabase() {
		return openReadOnlyDatabase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#getWritableDatabase()
	 */
	@Override
	public SQLiteDatabase getWritableDatabase() {
		return openWritableDatabase();
	}

	/**
	 * <p>
	 * return true if database is already opened.
	 * </p>
	 * 
	 * @return true if database is opened, otherwise false
	 */
	public boolean isOpen() {
		return database.isOpen();
	}

	/**
	 * @return the upgradedVersion
	 */
	public boolean isUpgradedVersion() {
		return upgradedVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onDowngrade(android.database.
	 * sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (databaseListener != null) {
			databaseListener.onUpdate(db, oldVersion, newVersion, false);
			upgradedVersion = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.
	 * sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (databaseListener != null) {
			databaseListener.onUpdate(db, oldVersion, newVersion, true);
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
		status.set(TypeStatus.READ_ONLY_OPENED);

		if (openCounter.incrementAndGet() == 1) {
			// open new read database
			database = super.getReadableDatabase();
		}
		Logger.info("database OPEN %s (id: %s)", status.get(), (openCounter.intValue() - 1));
		lockDb.unlock();
		r.lock();

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
		status.set(TypeStatus.READ_AND_WRITE_OPENED);

		if (openCounter.incrementAndGet() == 1) {
			// open new write database
			database = super.getWritableDatabase();
		}
		Logger.info("database OPEN %s (id: %s)", status.get(), (openCounter.intValue() - 1));
		lockDb.unlock();
		w.lock();

		return database;
	}

	/**
	 * Register the listener. It need to be called before <strong>any</strong>
	 * database operations.
	 * 
	 * @param listener
	 *            listener to user
	 */
	public void setOnDatabaseUpdateListener(OnDatabaseListener listener) {
		this.databaseListener = listener;
	}

}

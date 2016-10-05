/**
 * 
 */
package com.abubusoft.kripton.android.sqlite;

import java.util.concurrent.atomic.AtomicInteger;

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
 * @author xcesco
 * 
 */
public abstract class AbstractDataSource extends SQLiteOpenHelper implements AutoCloseable {

	/**
	 * database instance
	 */
	protected SQLiteDatabase database;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#getReadableDatabase()
	 */
	@Override
	public SQLiteDatabase getReadableDatabase() {
		return openReadOnlyDatabase();
	}

	/**
	 * <p>
	 * Open a read only database.
	 * </p>
	 * 
	 * @return read only database
	 */
	public SQLiteDatabase openReadOnlyDatabase() {
		if (openCounter.incrementAndGet() == 1) {
			// open new read database
			database = super.getReadableDatabase();
		}
		return database;
	}

	/**
	 * Open database. Is alias for openWritableDatabase()
	 * 
	 * @return writable database
	 */
	public SQLiteDatabase openDatabase() {
		return getWritableDatabase();
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
	 * open a writeable database.
	 * </p>
	 * 
	 * @return
	 */
	public SQLiteDatabase openWritableDatabase() {
		if (openCounter.incrementAndGet() == 1) {
			// open new write database
			database = super.getWritableDatabase();
		}
		return database;
	}

	private AtomicInteger openCounter = new AtomicInteger();

	public AbstractDataSource(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public AbstractDataSource(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#close()
	 */
	@Override
	public synchronized void close() {
		if (openCounter.decrementAndGet() == 0) {
			// Closing database
			database.close();
		}
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
	 * Interface for database transactions.
	 * 
	 * @author xcesco
	 *
	 * @param <E>
	 */
	public interface AbstractTransaction<E extends BindDaoFactory> {
		/**
		 * Execute transaction. Connection is managed from DataSource
		 * 
		 * @param daoFactory
		 * @return true to commit, false to rollback
		 */
		boolean onExecute(E daoFactory);

		void onError(Throwable e);
	}

}

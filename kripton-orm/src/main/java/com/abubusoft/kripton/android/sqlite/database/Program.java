package com.abubusoft.kripton.android.sqlite.database;

import android.database.sqlite.SQLiteProgram;
import androidx.sqlite.db.SupportSQLiteProgram;

/**
 * SupportSQLiteProgram implementation that wraps SQLite for Android's
 * implementation
 */
class Program implements SupportSQLiteProgram {
	private final SQLiteProgram delegate;

	Program(SQLiteProgram delegate) {
		this.delegate = delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindNull(int index) {
		delegate.bindNull(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindLong(int index, long value) {
		delegate.bindLong(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindDouble(int index, double value) {
		delegate.bindDouble(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindString(int index, String value) {
		delegate.bindString(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindBlob(int index, byte[] value) {
		delegate.bindBlob(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearBindings() {
		delegate.clearBindings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		delegate.close();
	}
}
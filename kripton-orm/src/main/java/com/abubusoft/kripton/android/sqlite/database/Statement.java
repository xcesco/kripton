package com.abubusoft.kripton.android.sqlite.database;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * SupportSQLiteStatement implementation that wraps SQLite for Android's
 * SQLiteStatement
 */
class Statement extends Program implements SupportSQLiteStatement {
	private final SQLiteStatement safeStatement;

	Statement(SQLiteStatement safeStatement) {
		super(safeStatement);
		this.safeStatement = safeStatement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		safeStatement.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int executeUpdateDelete() {
		return safeStatement.executeUpdateDelete();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long executeInsert() {
		return safeStatement.executeInsert();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long simpleQueryForLong() {
		return safeStatement.simpleQueryForLong();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String simpleQueryForString() {
		return safeStatement.simpleQueryForString();
	}
}
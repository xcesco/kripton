package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * SQL context for queries execution.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLContext {

	KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement);

	KriptonContentValues contentValues(SQLiteStatement compiledStatement);

	KriptonContentValues contentValuesForContentProvider(ContentValues values);

	/**
	 * StringBuilder used to generate SQL
	 * 
	 * @return
	 */
	StringBuilder sqlBuilder();

	/**
	 * Return true if log is enabled
	 * 
	 * @return
	 */
	boolean isLogEnabled();

	/**
	 * Get SQLite database
	 * 
	 * @return
	 */
	SQLiteDatabase database();
	
	/**
	 * Fired when transaction or shared connection is opened
	 */
	void onSessionOpened();
	
	/**
	 * Notify operation like INSERT, UPDATE and DELETE was done on DAO pass as parameter 
	 
	 * @param dao
	 */
	<D extends AbstractDao> void onSessionSQLEvent(D dao);
	
	/**
	 * Fired when transaction or shared connection is closed
	 */
	void onSessionClosed();
}

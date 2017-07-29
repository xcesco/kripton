package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Manage upgrade or downgrade of database.
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public interface DatabaseLifecycleHandler {

	/**
	 * <p>
	 * Method for DDL or DML
	 * 
	 * @param database
	 *            database
	 * @param oldVersion
	 *            current version of database
	 * @param newVersion
	 *            new version of database
	 * @param upgrade
	 *            if true is an upgrade operation, otherwise it's a
	 *            downgrade operation.
	 */
	void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion, boolean upgrade);

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
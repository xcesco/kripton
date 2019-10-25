package com.abubusoft.kripton.android.sqlite;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;

public interface SQLiteLifeCycleListener {

	/**
	 * Called when the database connection is being configured, to enable
	 * features such as write-ahead logging or foreign key support.
	 * <p>
	 * This method is called before {@link #onCreate}, {@link #onUpgrade},
	 * {@link #onDowngrade}, or {@link #onOpen} are called. It should not modify
	 * the database except to configure the database connection as required.
	 * </p>
	 * <p>
	 * This method should only call methods that configure the parameters of the
	 * database connection, such as
	 * {@link SupportSQLiteDatabase#enableWriteAheadLogging}
	 * {@link SupportSQLiteDatabase#setForeignKeyConstraintsEnabled},
	 * {@link SupportSQLiteDatabase#setLocale},
	 * {@link SupportSQLiteDatabase#setMaximumSize}, or executing PRAGMA
	 * statements.
	 * </p>
	 *
	 * @param db
	 *            The database.
	 */
	void onConfigure(@NonNull SupportSQLiteDatabase db);

	/**
	 * Called when the database is created for the first time. This is where the
	 * creation of tables and the initial population of the tables should
	 * happen.
	 *
	 * @param db
	 *            The database.
	 */
	void onCreate(@NonNull SupportSQLiteDatabase db);

	/**
	 * Called when the database needs to be upgraded. The implementation should
	 * use this method to drop tables, add tables, or do anything else it needs
	 * to upgrade to the new schema version.
	 *
	 * <p>
	 * The SQLite ALTER TABLE documentation can be found
	 * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new
	 * columns you can use ALTER TABLE to insert them into a live table. If you
	 * rename or remove columns you can use ALTER TABLE to rename the old table,
	 * then create the new table and then populate the new table with the
	 * contents of the old table.
	 * </p>
	 * <p>
	 * This method executes within a transaction. If an exception is thrown, all
	 * changes will automatically be rolled back.
	 * </p>
	 *
	 * @param db
	 *            The database.
	 * @param oldVersion
	 *            The old database version.
	 * @param newVersion
	 *            The new database version.
	 */
	void onUpgrade(@NonNull SupportSQLiteDatabase db, int oldVersion, int newVersion);

	/**
	 * Called when the database needs to be downgraded. This is strictly similar
	 * to {@link #onUpgrade} method, but is called whenever current version is
	 * newer than requested one. However, this method is not abstract, so it is
	 * not mandatory for a customer to implement it. If not overridden, default
	 * implementation will reject downgrade and throws SQLiteException
	 *
	 * <p>
	 * This method executes within a transaction. If an exception is thrown, all
	 * changes will automatically be rolled back.
	 * </p>
	 *
	 * @param db
	 *            The database.
	 * @param oldVersion
	 *            The old database version.
	 * @param newVersion
	 *            The new database version.
	 */
	void onDowngrade(@NonNull SupportSQLiteDatabase db, int oldVersion, int newVersion);

	/**
	 * Called when the database has been opened. The implementation should check
	 * {@link SupportSQLiteDatabase#isReadOnly} before updating the database.
	 * <p>
	 * This method is called after the database connection has been configured
	 * and after the database schema has been created, upgraded or downgraded as
	 * necessary. If the database connection must be configured in some way
	 * before the schema is created, upgraded, or downgraded, do it in
	 * {@link #onConfigure} instead.
	 * </p>
	 *
	 * @param db
	 *            The database.
	 */
	void onOpen(@NonNull SupportSQLiteDatabase db);

	/**
	 * The method invoked when database corruption is detected. Default
	 * implementation will delete the database file.
	 *
	 * @param db
	 *            the {@link SupportSQLiteDatabase} object representing the
	 *            database on which corruption is detected.
	 */
	void onCorruption(@NonNull SupportSQLiteDatabase db);
	
}

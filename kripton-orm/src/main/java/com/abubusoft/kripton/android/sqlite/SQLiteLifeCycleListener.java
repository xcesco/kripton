package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.abubusoft.kripton.android.Logger;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Pair;
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
	static void onCorruption(@NonNull SupportSQLiteDatabase db) {
		// the following implementation is taken from {@link
		// DefaultDatabaseErrorHandler}.
		Logger.error("Corruption reported by sqlite on database: " + db.getPath());
		// is the corruption detected even before database could be 'opened'?
		if (!db.isOpen()) {
			// database files are not even openable. delete this database file.
			// NOTE if the database has attached databases, then any of them
			// could be corrupt.
			// and not deleting all of them could cause corrupted database file
			// to remain and
			// make the application crash on database open operation. To avoid
			// this problem,
			// the application should provide its own {@link
			// DatabaseErrorHandler} impl class
			// to delete ALL files of the database (including the attached
			// databases).
			deleteDatabaseFile(db.getPath());
			return;
		}
		List<Pair<String, String>> attachedDbs = null;
		try {
			// Close the database, which will cause subsequent operations to
			// fail.
			// before that, get the attached database list first.
			try {
				attachedDbs = db.getAttachedDbs();
			} catch (SQLiteException e) {
				/* ignore */
			}
			try {
				db.close();
			} catch (IOException e) {
				/* ignore */
			}
		} finally {
			// Delete all files of this corrupt database and/or attached
			// databases
			if (attachedDbs != null) {
				for (Pair<String, String> p : attachedDbs) {
					deleteDatabaseFile(p.second);
				}
			} else {
				// attachedDbs = null is possible when the database is so
				// corrupt that even
				// "PRAGMA database_list;" also fails. delete the main database
				// file
				deleteDatabaseFile(db.getPath());
			}
		}
	}

	static void deleteDatabaseFile(String fileName) {
            if (fileName.equalsIgnoreCase(":memory:") || fileName.trim().length() == 0) {
                return;
            }
            Logger.warn("deleting the database file: " + fileName);
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    SQLiteDatabase.deleteDatabase(new File(fileName));
                } else {
                    try {
                        final boolean deleted = new File(fileName).delete();
                        if (!deleted) {
                        	Logger.error("Could not delete the database file " + fileName);
                        }
                    } catch (Exception error) {
                    	Logger.error("error while deleting corrupted database file", error);
                    }
                }
            } catch (Exception e) {
            /* print warning and ignore exception */
            	Logger.warn("delete failed: ", e);
            }
        }
}

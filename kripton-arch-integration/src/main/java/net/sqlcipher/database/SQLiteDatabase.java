package net.sqlcipher.database;
/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import android.content.ContentValues;
import android.content.Context;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import net.sqlcipher.Cursor;
import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.SQLException;

public class SQLiteDatabase extends SQLiteClosable implements SupportSQLiteDatabase {
	private static final String TAG = "Database";
	private static final int EVENT_DB_OPERATION = 52000;
	private static final int EVENT_DB_CORRUPT = 75004;
	private static final String KEY_ENCODING = "UTF-8";

	public static final String SQLCIPHER_ANDROID_VERSION = "dummy";

	// Stores reference to all databases opened in the current process.
	// (The referent Object is not used at this time.)
	// INVARIANT: Guarded by sActiveDatabases.
	private static WeakHashMap<SQLiteDatabase, Object> sActiveDatabases = new WeakHashMap<SQLiteDatabase, Object>();

	public int status(int operation, boolean reset) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Change the password of the open database using sqlite3_rekey().
	 *
	 * @param password
	 *            new database password
	 *
	 * @throws SQLiteException
	 *             if there is an issue changing the password internally OR if
	 *             the database is not open
	 *
	 *             FUTURE @todo throw IllegalStateException if the database is
	 *             not open and update the test suite
	 */
	public void changePassword(String password) throws SQLiteException {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Change the password of the open database using sqlite3_rekey().
	 *
	 * @param password
	 *            new database password (char array)
	 *
	 * @throws SQLiteException
	 *             if there is an issue changing the password internally OR if
	 *             the database is not open
	 *
	 *             FUTURE @todo throw IllegalStateException if the database is
	 *             not open and update the test suite
	 */
	public void changePassword(char[] password) throws SQLiteException {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Implement this interface to provide custom strategy for loading jni
	 * libraries.
	 */
	public interface LibraryLoader {
		/**
		 * Load jni libraries by given names. Straightforward implementation
		 * will be calling {@link System#loadLibrary(String name)} for every
		 * provided library name.
		 *
		 * @param libNames
		 *            library names that sqlcipher need to load
		 */
		void loadLibraries(String... libNames);
	}

	/**
	 * Loads the native SQLCipher library into the application process.
	 */
	public static synchronized void loadLibs(Context context) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Loads the native SQLCipher library into the application process.
	 */
	public static synchronized void loadLibs(Context context, File workingDir) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Loads the native SQLCipher library into the application process.
	 */
	public static synchronized void loadLibs(Context context, LibraryLoader libraryLoader) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Loads the native SQLCipher library into the application process.
	 */
	public static synchronized void loadLibs(Context context, File workingDir, LibraryLoader libraryLoader) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Algorithms used in ON CONFLICT clause
	 * http://www.sqlite.org/lang_conflict.html
	 */
	/**
	 * When a constraint violation occurs, an immediate ROLLBACK occurs, thus
	 * ending the current transaction, and the command aborts with a return code
	 * of SQLITE_CONSTRAINT. If no transaction is active (other than the implied
	 * transaction that is created on every command) then this algorithm works
	 * the same as ABORT.
	 */
	public static final int CONFLICT_ROLLBACK = 1;

	/**
	 * When a constraint violation occurs,no ROLLBACK is executed so changes
	 * from prior commands within the same transaction are preserved. This is
	 * the default behavior.
	 */
	public static final int CONFLICT_ABORT = 2;

	/**
	 * When a constraint violation occurs, the command aborts with a return code
	 * SQLITE_CONSTRAINT. But any changes to the database that the command made
	 * prior to encountering the constraint violation are preserved and are not
	 * backed out.
	 */
	public static final int CONFLICT_FAIL = 3;

	/**
	 * When a constraint violation occurs, the one row that contains the
	 * constraint violation is not inserted or changed. But the command
	 * continues executing normally. Other rows before and after the row that
	 * contained the constraint violation continue to be inserted or updated
	 * normally. No error is returned.
	 */
	public static final int CONFLICT_IGNORE = 4;

	/**
	 * When a UNIQUE constraint violation occurs, the pre-existing rows that are
	 * causing the constraint violation are removed prior to inserting or
	 * updating the current row. Thus the insert or update always occurs. The
	 * command continues executing normally. No error is returned. If a NOT NULL
	 * constraint violation occurs, the NULL value is replaced by the default
	 * value for that column. If the column has no default value, then the ABORT
	 * algorithm is used. If a CHECK constraint violation occurs then the IGNORE
	 * algorithm is used. When this conflict resolution strategy deletes rows in
	 * order to satisfy a constraint, it does not invoke delete triggers on
	 * those rows. This behavior might change in a future release.
	 */
	public static final int CONFLICT_REPLACE = 5;

	/**
	 * use the following when no conflict action is specified.
	 */
	public static final int CONFLICT_NONE = 0;
	private static final String[] CONFLICT_VALUES = new String[] { "", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ",
			" OR IGNORE ", " OR REPLACE " };

	/**
	 * Maximum Length Of A LIKE Or GLOB Pattern The pattern matching algorithm
	 * used in the default LIKE and GLOB implementation of SQLite can exhibit
	 * O(N^2) performance (where N is the number of characters in the pattern)
	 * for certain pathological cases. To avoid denial-of-service attacks the
	 * length of the LIKE or GLOB pattern is limited to
	 * SQLITE_MAX_LIKE_PATTERN_LENGTH bytes. The default value of this limit is
	 * 50000. A modern workstation can evaluate even a pathological LIKE or GLOB
	 * pattern of 50000 bytes relatively quickly. The denial of service problem
	 * only comes into play when the pattern length gets into millions of bytes.
	 * Nevertheless, since most useful LIKE or GLOB patterns are at most a few
	 * dozen bytes in length, paranoid application developers may want to reduce
	 * this parameter to something in the range of a few hundred if they know
	 * that external users are able to generate arbitrary patterns.
	 */
	public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;

	/**
	 * Flag for {@link #openDatabase} to open the database for reading and
	 * writing. If the disk is full, this may fail even before you actually
	 * write anything.
	 *
	 * {@more} Note that the value of this flag is 0, so it is the default.
	 */
	public static final int OPEN_READWRITE = 0x00000000; // update native code
															// if changing

	/**
	 * Flag for {@link #openDatabase} to open the database for reading only.
	 * This is the only reliable way to open a database if the disk may be full.
	 */
	public static final int OPEN_READONLY = 0x00000001; // update native code if
														// changing

	private static final int OPEN_READ_MASK = 0x00000001; // update native code
															// if changing

	/**
	 * Flag for {@link #openDatabase} to open the database without support for
	 * localized collators.
	 *
	 * {@more} This causes the collator <code>LOCALIZED</code> not to be
	 * created. You must be consistent when using this flag to use the setting
	 * the database was created with. If this is set, {@link #setLocale} will do
	 * nothing.
	 */
	public static final int NO_LOCALIZED_COLLATORS = 0x00000010; // update
																	// native
																	// code if
																	// changing

	/**
	 * Flag for {@link #openDatabase} to create the database file if it does not
	 * already exist.
	 */
	public static final int CREATE_IF_NECESSARY = 0x10000000; // update native
																// code if
																// changing

	/**
	 * SQLite memory database name
	 */
	public static final String MEMORY = ":memory:";

	public static final int MAX_SQL_CACHE_SIZE = 250;

	/**
	 * Attempts to release memory that SQLite holds but does not require to
	 * operate properly. Typically this memory will come from the page cache.
	 *
	 * @return the number of bytes actually released
	 */
	static public native int releaseMemory();

	/**
	 * Control whether or not the SQLiteDatabase is made thread-safe by using
	 * locks around critical sections. This is pretty expensive, so if you know
	 * that your DB will only be used by a single thread then you should set
	 * this to false. The default is true.
	 * 
	 * @param lockingEnabled
	 *            set to true to enable locks, false otherwise
	 */
	public void setLockingEnabled(boolean lockingEnabled) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * If set then the SQLiteDatabase is made thread-safe by using locks around
	 * critical sections
	 */
	private boolean mLockingEnabled = true;

	/**
	 * Releases the database lock. This is a no-op if mLockingEnabled is false.
	 *
	 * @see #unlock()
	 */
	/* package */ void unlock() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Releases the database lock.
	 *
	 * @see #unlockForced()
	 */
	private void unlockForced() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Performs a PRAGMA integrity_check; command against the database.
	 * 
	 * @return true if the integrity check is ok, otherwise false
	 */
	public boolean isDatabaseIntegrityOk() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Returns a list of attached databases including the main database by
	 * executing PRAGMA database_list
	 * 
	 * @return a list of pairs of database name and filename
	 */
	public List<Pair<String, String>> getAttachedDbs() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the journal mode of the database to WAL
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean enableWriteAheadLogging() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the journal mode of the database to DELETE (the default mode)
	 */
	public void disableWriteAheadLogging() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * @return true if the journal mode is set to WAL, otherwise false
	 */
	public boolean isWriteAheadLoggingEnabled() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Enables or disables foreign key constraints
	 * 
	 * @param enable
	 *            used to determine whether or not foreign key constraints are
	 *            on
	 */
	public void setForeignKeyConstraintsEnabled(boolean enable) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Begins a transaction. Transactions can be nested. When the outer
	 * transaction is ended all of the work done in that transaction and all of
	 * the nested transactions will be committed or rolled back. The changes
	 * will be rolled back if any transaction is ended without being marked as
	 * clean (by calling setTransactionSuccessful). Otherwise they will be
	 * committed.
	 *
	 * <p>
	 * Here is the standard idiom for transactions:
	 *
	 * <pre>
	 *   db.beginTransaction();
	 *   try {
	 *     ...
	 *     db.setTransactionSuccessful();
	 *   } finally {
	 *     db.endTransaction();
	 *   }
	 * </pre>
	 *
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public void beginTransaction() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Begins a transaction in Exlcusive mode. Transactions can be nested. When
	 * the outer transaction is ended all of the work done in that transaction
	 * and all of the nested transactions will be committed or rolled back. The
	 * changes will be rolled back if any transaction is ended without being
	 * marked as clean (by calling setTransactionSuccessful). Otherwise they
	 * will be committed.
	 *
	 * <p>
	 * Here is the standard idiom for transactions:
	 *
	 * <pre>
	 *   db.beginTransactionWithListener(listener);
	 *   try {
	 *     ...
	 *     db.setTransactionSuccessful();
	 *   } finally {
	 *     db.endTransaction();
	 *   }
	 * </pre>
	 * 
	 * @param transactionListener
	 *            listener that should be notified when the transaction begins,
	 *            commits, or is rolled back, either explicitly or by a call to
	 *            {@link #yieldIfContendedSafely}.
	 *
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public void beginTransactionWithListener(SQLiteTransactionListener transactionListener) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Begins a transaction in Immediate mode
	 */
	public void beginTransactionNonExclusive() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Begins a transaction in Immediate mode
	 * 
	 * @param transactionListener
	 *            is the listener used to report transaction events
	 */
	public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener transactionListener) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * End a transaction. See beginTransaction for notes about how to use this
	 * and when transactions are committed and rolled back.
	 *
	 * @throws IllegalStateException
	 *             if the database is not open or is not locked by the current
	 *             thread
	 */
	public void endTransaction() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Marks the current transaction as successful. Do not do any more database
	 * work between calling this and calling endTransaction. Do as little
	 * non-database work as possible in that situation too. If any errors are
	 * encountered between this and endTransaction the transaction will still be
	 * committed.
	 *
	 * @throws IllegalStateException
	 *             if the database is not open, the current thread is not in a
	 *             transaction, or the transaction is already marked as
	 *             successful.
	 */
	public void setTransactionSuccessful() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * return true if there is a transaction pending
	 */
	public boolean inTransaction() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Checks if the database lock is held by this thread.
	 *
	 * @return true, if this thread is holding the database lock.
	 */
	public boolean isDbLockedByCurrentThread() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Checks if the database is locked by another thread. This is just an
	 * estimate, since this status can change at any time, including after the
	 * call is made but before the result has been acted upon.
	 *
	 * @return true if the transaction was yielded, false if queue was empty or
	 *         database was not open
	 */
	public boolean isDbLockedByOtherThreads() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Temporarily end the transaction to let other threads run. The transaction
	 * is assumed to be successful so far. Do not call setTransactionSuccessful
	 * before calling this. When this returns a new transaction will have been
	 * created but not marked as successful.
	 *
	 * @return true if the transaction was yielded
	 *
	 * @deprecated if the db is locked more than once (becuase of nested
	 *             transactions) then the lock will not be yielded. Use
	 *             yieldIfContendedSafely instead.
	 */
	@Deprecated
	public boolean yieldIfContended() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Temporarily end the transaction to let other threads run. The transaction
	 * is assumed to be successful so far. Do not call setTransactionSuccessful
	 * before calling this. When this returns a new transaction will have been
	 * created but not marked as successful. This assumes that there are no
	 * nested transactions (beginTransaction has only been called once) and will
	 * throw an exception if that is not the case.
	 *
	 * @return true if the transaction was yielded, false if queue was empty or
	 *         database was not open
	 */
	public boolean yieldIfContendedSafely() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Temporarily end the transaction to let other threads run. The transaction
	 * is assumed to be successful so far. Do not call setTransactionSuccessful
	 * before calling this. When this returns a new transaction will have been
	 * created but not marked as successful. This assumes that there are no
	 * nested transactions (beginTransaction has only been called once) and will
	 * throw an exception if that is not the case.
	 *
	 * @param sleepAfterYieldDelay
	 *            if > 0, sleep this long before starting a new transaction if
	 *            the lock was actually yielded. This will allow other
	 *            background threads to make some more progress than they would
	 *            if we started the transaction immediately.
	 *
	 * @return true if the transaction was yielded, false if queue was empty or
	 *         database was not open
	 *
	 * @throws IllegalStateException
	 *             if the database is locked more than once by the current
	 *             thread
	 * @throws InterruptedException
	 *             if the thread was interrupted while sleeping
	 */
	public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
		throw (new RuntimeException("Not implements"));
	}

	public Map<String, String> getSyncedTables() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Used to allow returning sub-classes of {@link Cursor} when calling query.
	 */
	public interface CursorFactory {
		/**
		 * See
		 * {@link SQLiteCursor#SQLiteCursor(SQLiteDatabase, SQLiteCursorDriver, String, SQLiteQuery)}.
		 */
		public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query);
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS}.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, String password, CursorFactory factory, int flags) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS}.
	 *
	 * <p>
	 * Sets the locale of the database to the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file (char array)
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, char[] password, CursorFactory factory, int flags) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS} with optional hook to run on pre/post key
	 * events.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 * @param hook
	 *            to run on pre/post key events
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, String password, CursorFactory factory, int flags,
			SQLiteDatabaseHook hook) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS} with optional hook to run on pre/post key
	 * events.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file (char array)
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 * @param hook
	 *            to run on pre/post key events (may be null)
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, char[] password, CursorFactory factory, int flags,
			SQLiteDatabaseHook hook) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS} with optional hook to run on pre/post key
	 * events.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 * @param hook
	 *            to run on pre/post key events
	 * @param errorHandler
	 *            The {@link DatabaseErrorHandler} to be used when sqlite
	 *            reports database corruption (or null for default).
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, String password, CursorFactory factory, int flags,
			SQLiteDatabaseHook hook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS} with optional hook to run on pre/post key
	 * events.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file (char array)
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 * @param hook
	 *            to run on pre/post key events (may be null)
	 * @param errorHandler
	 *            The {@link DatabaseErrorHandler} to be used when sqlite
	 *            reports database corruption (or null for default).
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, char[] password, CursorFactory factory, int flags,
			SQLiteDatabaseHook hook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Open the database according to the flags {@link #OPEN_READWRITE}
	 * {@link #OPEN_READONLY} {@link #CREATE_IF_NECESSARY} and/or
	 * {@link #NO_LOCALIZED_COLLATORS} with optional hook to run on pre/post key
	 * events.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            to database file to open and/or create
	 * @param password
	 *            to use to open and/or create database file (byte array)
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called, or null for default
	 * @param flags
	 *            to control database access mode and other options
	 * @param hook
	 *            to run on pre/post key events (may be null)
	 * @param errorHandler
	 *            The {@link DatabaseErrorHandler} to be used when sqlite
	 *            reports database corruption (or null for default).
	 *
	 * @return the newly opened database
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public static SQLiteDatabase openDatabase(String path, byte[] password, CursorFactory factory, int flags,
			SQLiteDatabaseHook hook, DatabaseErrorHandler errorHandler) {

		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(file.getPath(), password, factory,
	 * CREATE_IF_NECESSARY, databaseHook).
	 */
	public static SQLiteDatabase openOrCreateDatabase(File file, String password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(path, password, factory, CREATE_IF_NECESSARY,
	 * databaseHook).
	 */
	public static SQLiteDatabase openOrCreateDatabase(File file, String password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(path, password, factory, CREATE_IF_NECESSARY,
	 * databaseHook).
	 */
	public static SQLiteDatabase openOrCreateDatabase(String path, String password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	public static SQLiteDatabase openOrCreateDatabase(String path, String password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	public static SQLiteDatabase openOrCreateDatabase(String path, char[] password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	public static SQLiteDatabase openOrCreateDatabase(String path, char[] password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	public static SQLiteDatabase openOrCreateDatabase(String path, byte[] password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	public static SQLiteDatabase openOrCreateDatabase(String path, byte[] password, CursorFactory factory,
			SQLiteDatabaseHook databaseHook, DatabaseErrorHandler errorHandler) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(file.getPath(), password, factory,
	 * CREATE_IF_NECESSARY).
	 */
	public static SQLiteDatabase openOrCreateDatabase(File file, String password, CursorFactory factory) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(path, password, factory, CREATE_IF_NECESSARY).
	 */
	public static SQLiteDatabase openOrCreateDatabase(String path, String password, CursorFactory factory) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(path, password, factory, CREATE_IF_NECESSARY).
	 */
	public static SQLiteDatabase openOrCreateDatabase(String path, char[] password, CursorFactory factory) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Equivalent to openDatabase(path, password, factory, CREATE_IF_NECESSARY).
	 */
	public static SQLiteDatabase openOrCreateDatabase(String path, byte[] password, CursorFactory factory) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Create a memory backed SQLite database. Its contents will be destroyed
	 * when the database is closed.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called
	 * @param password
	 *            to use to open and/or create database file
	 *
	 * @return a SQLiteDatabase object, or null if the database can't be created
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 */
	public static SQLiteDatabase create(CursorFactory factory, String password) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Create a memory backed SQLite database. Its contents will be destroyed
	 * when the database is closed.
	 *
	 * <p>
	 * Sets the locale of the database to the the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param factory
	 *            an optional factory class that is called to instantiate a
	 *            cursor when query is called
	 * @param password
	 *            to use to open and/or create database file (char array)
	 *
	 * @return a SQLiteDatabase object, or null if the database can't be created
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 */
	public static SQLiteDatabase create(CursorFactory factory, char[] password) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Close the database.
	 */
	public void close() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Gets the database version.
	 *
	 * @return the database version
	 *
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public int getVersion() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the database version.
	 *
	 * @param version
	 *            the new database version
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql internally
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public void setVersion(int version) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Returns the maximum size the database may grow to.
	 *
	 * @return the new maximum database size
	 */
	public long getMaximumSize() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the maximum size the database will grow to. The maximum size cannot
	 * be set below the current size.
	 *
	 * @param numBytes
	 *            the maximum database size, in bytes
	 * @return the new maximum database size
	 */
	public long setMaximumSize(long numBytes) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Returns the current database page size, in bytes.
	 *
	 * @return the database page size, in bytes
	 */
	public long getPageSize() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the database page size. The page size must be a power of two. This
	 * method does not work if any data has been written to the database file,
	 * and must be called right after the database has been created.
	 *
	 * @param numBytes
	 *            the database page size, in bytes
	 */
	public void setPageSize(long numBytes) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Mark this table as syncable. When an update occurs in this table the
	 * _sync_dirty field will be set to ensure proper syncing operation.
	 *
	 * @param table
	 *            the table to mark as syncable
	 * @param deletedTable
	 *            The deleted table that corresponds to the syncable table
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql to mark the table as
	 *             syncable OR if the database is not open
	 *
	 *             FUTURE @todo throw IllegalStateException if the database is
	 *             not open and update the test suite
	 *
	 *             NOTE: This method was deprecated by the AOSP in Android API
	 *             11.
	 */
	public void markTableSyncable(String table, String deletedTable) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Mark this table as syncable, with the _sync_dirty residing in another
	 * table. When an update occurs in this table the _sync_dirty field of the
	 * row in updateTable with the _id in foreignKey will be set to ensure
	 * proper syncing operation.
	 *
	 * @param table
	 *            an update on this table will trigger a sync time removal
	 * @param foreignKey
	 *            this is the column in table whose value is an _id in
	 *            updateTable
	 * @param updateTable
	 *            this is the table that will have its _sync_dirty
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql to mark the table as
	 *             syncable
	 *
	 *             FUTURE @todo throw IllegalStateException if the database is
	 *             not open and update the test suite
	 *
	 *             NOTE: This method was deprecated by the AOSP in Android API
	 *             11.
	 */
	public void markTableSyncable(String table, String foreignKey, String updateTable) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Finds the name of the first table, which is editable.
	 *
	 * @param tables
	 *            a list of tables
	 * @return the first table listed
	 */
	public static String findEditTable(String tables) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Compiles an SQL statement into a reusable pre-compiled statement object.
	 * The parameters are identical to {@link #execSQL(String)}. You may put ?s
	 * in the statement and fill in those values with
	 * {@link SQLiteProgram#bindString} and {@link SQLiteProgram#bindLong} each
	 * time you want to run the statement. Statements may not return result sets
	 * larger than 1x1.
	 *
	 * @param sql
	 *            The raw SQL statement, may contain ? for unknown values to be
	 *            bound later.
	 *
	 * @return A pre-compiled {@link SQLiteStatement} object. Note that
	 *         {@link SQLiteStatement}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public SQLiteStatement compileStatement(String sql) throws SQLException {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Query the given URL, returning a {@link Cursor} over the result set.
	 *
	 * @param distinct
	 *            true if you want each row to be unique, false otherwise.
	 * @param table
	 *            The table name to compile the query against.
	 * @param columns
	 *            A list of which columns to return. Passing null will return
	 *            all columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given table.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the
	 *            values from selectionArgs, in order that they appear in the
	 *            selection. The values will be bound as Strings.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            Limits the number of rows returned by the query, formatted as
	 *            LIMIT clause. Passing null denotes no LIMIT clause.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 *
	 * @see Cursor
	 */
	public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy, String limit) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Query the given URL, returning a {@link Cursor} over the result set.
	 *
	 * @param cursorFactory
	 *            the cursor factory to use, or null for the default factory
	 * @param distinct
	 *            true if you want each row to be unique, false otherwise.
	 * @param table
	 *            The table name to compile the query against.
	 * @param columns
	 *            A list of which columns to return. Passing null will return
	 *            all columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given table.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the
	 *            values from selectionArgs, in order that they appear in the
	 *            selection. The values will be bound as Strings.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            Limits the number of rows returned by the query, formatted as
	 *            LIMIT clause. Passing null denotes no LIMIT clause.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @see Cursor
	 */
	public Cursor queryWithFactory(CursorFactory cursorFactory, boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Query the given table, returning a {@link Cursor} over the result set.
	 *
	 * @param table
	 *            The table name to compile the query against.
	 * @param columns
	 *            A list of which columns to return. Passing null will return
	 *            all columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given table.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the
	 *            values from selectionArgs, in order that they appear in the
	 *            selection. The values will be bound as Strings.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 *
	 * @see Cursor
	 */
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {

		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Query the given table, returning a {@link Cursor} over the result set.
	 *
	 * @param table
	 *            The table name to compile the query against.
	 * @param columns
	 *            A list of which columns to return. Passing null will return
	 *            all columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given table.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the
	 *            values from selectionArgs, in order that they appear in the
	 *            selection. The values will be bound as Strings.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            Limits the number of rows returned by the query, formatted as
	 *            LIMIT clause. Passing null denotes no LIMIT clause.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 *
	 * @see Cursor
	 */
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {

		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Runs the provided SQL and returns a {@link Cursor} over the result set.
	 *
	 * @param sql
	 *            the SQL query. The SQL string must not be ; terminated
	 * @param selectionArgs
	 *            You may include ?s in where clause in the query, which will be
	 *            replaced by the values from selectionArgs. The values will be
	 *            bound as Strings.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public Cursor rawQuery(String sql, String[] selectionArgs) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Runs the provided SQL and returns a {@link Cursor} over the result set.
	 *
	 * @param sql
	 *            the SQL query. The SQL string must not be ; terminated
	 * @param args
	 *            You may include ?s in where clause in the query, which will be
	 *            replaced by the values from args. The values will be bound by
	 *            their type.
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public Cursor rawQuery(String sql, Object[] args) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Runs the provided SQL and returns a cursor over the result set.
	 *
	 * @param cursorFactory
	 *            the cursor factory to use, or null for the default factory
	 * @param sql
	 *            the SQL query. The SQL string must not be ; terminated
	 * @param selectionArgs
	 *            You may include ?s in where clause in the query, which will be
	 *            replaced by the values from selectionArgs. The values will be
	 *            bound as Strings.
	 * @param editTable
	 *            the name of the first table, which is editable
	 *
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 * @throws SQLiteException
	 *             if there is an issue executing the sql or the SQL string is
	 *             invalid
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String sql, String[] selectionArgs,
			String editTable) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Runs the provided SQL and returns a cursor over the result set. The
	 * cursor will read an initial set of rows and the return to the caller. It
	 * will continue to read in batches and send data changed notifications when
	 * the later batches are ready.
	 * 
	 * @param sql
	 *            the SQL query. The SQL string must not be ; terminated
	 * @param selectionArgs
	 *            You may include ?s in where clause in the query, which will be
	 *            replaced by the values from selectionArgs. The values will be
	 *            bound as Strings.
	 * @param initialRead
	 *            set the initial count of items to read from the cursor
	 * @param maxRead
	 *            set the count of items to read on each iteration after the
	 *            first
	 * @return A {@link Cursor} object, which is positioned before the first
	 *         entry. Note that {@link Cursor}s are not synchronized, see the
	 *         documentation for more details.
	 *
	 *         This work is incomplete and not fully tested or reviewed, so
	 *         currently hidden.
	 * @hide
	 */
	public Cursor rawQuery(String sql, String[] selectionArgs, int initialRead, int maxRead) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for inserting a row into the database.
	 *
	 * @param table
	 *            the table to insert the row into
	 * @param nullColumnHack
	 *            SQL doesn't allow inserting a completely empty row, so if
	 *            initialValues is empty this column will explicitly be assigned
	 *            a NULL value
	 * @param values
	 *            this map contains the initial column values for the row. The
	 *            keys should be the column names and the values the column
	 *            values
	 * @return the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long insert(String table, String nullColumnHack, ContentValues values) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for inserting a row into the database.
	 *
	 * @param table
	 *            the table to insert the row into
	 * @param nullColumnHack
	 *            SQL doesn't allow inserting a completely empty row, so if
	 *            initialValues is empty this column will explicitly be assigned
	 *            a NULL value
	 * @param values
	 *            this map contains the initial column values for the row. The
	 *            keys should be the column names and the values the column
	 *            values
	 * @throws SQLException
	 * @return the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long insertOrThrow(String table, String nullColumnHack, ContentValues values) throws SQLException {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for replacing a row in the database.
	 *
	 * @param table
	 *            the table in which to replace the row
	 * @param nullColumnHack
	 *            SQL doesn't allow inserting a completely empty row, so if
	 *            initialValues is empty this row will explicitly be assigned a
	 *            NULL value
	 * @param initialValues
	 *            this map contains the initial column values for the row. The
	 *            key
	 * @return the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long replace(String table, String nullColumnHack, ContentValues initialValues) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for replacing a row in the database.
	 *
	 * @param table
	 *            the table in which to replace the row
	 * @param nullColumnHack
	 *            SQL doesn't allow inserting a completely empty row, so if
	 *            initialValues is empty this row will explicitly be assigned a
	 *            NULL value
	 * @param initialValues
	 *            this map contains the initial column values for the row. The
	 *            key
	 * @throws SQLException
	 * @return the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long replaceOrThrow(String table, String nullColumnHack, ContentValues initialValues) throws SQLException {
		return insertWithOnConflict(table, nullColumnHack, initialValues, CONFLICT_REPLACE);
	}

	/**
	 * General method for inserting a row into the database.
	 *
	 * @param table
	 *            the table to insert the row into
	 * @param nullColumnHack
	 *            SQL doesn't allow inserting a completely empty row, so if
	 *            initialValues is empty this column will explicitly be assigned
	 *            a NULL value
	 * @param initialValues
	 *            this map contains the initial column values for the row. The
	 *            keys should be the column names and the values the column
	 *            values
	 * @param conflictAlgorithm
	 *            for insert conflict resolver
	 *
	 * @return the row ID of the newly inserted row OR the primary key of the
	 *         existing row if the input param 'conflictAlgorithm' =
	 *         {@link #CONFLICT_IGNORE} OR -1 if any error
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public long insertWithOnConflict(String table, String nullColumnHack, ContentValues initialValues,
			int conflictAlgorithm) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for deleting rows in the database.
	 *
	 * @param table
	 *            the table to delete from
	 * @param whereClause
	 *            the optional WHERE clause to apply when deleting. Passing null
	 *            will delete all rows.
	 *
	 * @return the number of rows affected if a whereClause is passed in, 0
	 *         otherwise. To remove all rows and get a count pass "1" as the
	 *         whereClause.
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public int delete(String table, String whereClause, String[] whereArgs) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for deleting rows in the database.
	 *
	 * @param table
	 *            the table to delete from
	 * @param whereClause
	 *            the optional WHERE clause to apply when deleting. Passing null
	 *            will delete all rows.
	 *
	 * @return the number of rows affected if a whereClause is passed in, 0
	 *         otherwise. To remove all rows and get a count pass "1" as the
	 *         whereClause.
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public int delete(String table, String whereClause, Object[] whereArgs) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for updating rows in the database.
	 *
	 * @param table
	 *            the table to update in
	 * @param values
	 *            a map from column names to new column values. null is a valid
	 *            value that will be translated to NULL.
	 * @param whereClause
	 *            the optional WHERE clause to apply when updating. Passing null
	 *            will update all rows.
	 *
	 * @return the number of rows affected
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Convenience method for updating rows in the database.
	 *
	 * @param table
	 *            the table to update in
	 * @param values
	 *            a map from column names to new column values. null is a valid
	 *            value that will be translated to NULL.
	 * @param whereClause
	 *            the optional WHERE clause to apply when updating. Passing null
	 *            will update all rows.
	 * @param conflictAlgorithm
	 *            for update conflict resolver
	 *
	 * @return the number of rows affected
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public int updateWithOnConflict(String table, ContentValues values, String whereClause, String[] whereArgs,
			int conflictAlgorithm) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Execute a single SQL statement that is not a query. For example, CREATE
	 * TABLE, DELETE, INSERT, etc. Multiple statements separated by ;s are not
	 * supported. it takes a write lock
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public void execSQL(String sql) throws SQLException {
		throw (new RuntimeException("Not implements"));
	}

	public void rawExecSQL(String sql) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Execute a single SQL statement that is not a query. For example, CREATE
	 * TABLE, DELETE, INSERT, etc. Multiple statements separated by ;s are not
	 * supported. it takes a write lock,
	 *
	 * @param sql
	 * @param bindArgs
	 *            only byte[], String, Long and Double are supported in
	 *            bindArgs.
	 *
	 * @throws SQLException
	 *             If the SQL string is invalid for some reason
	 * @throws IllegalStateException
	 *             if the database is not open
	 */
	public void execSQL(String sql, Object[] bindArgs) throws SQLException {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Public constructor which attempts to open the database. See
	 * {@link #create} and {@link #openDatabase}.
	 *
	 * <p>
	 * Sets the locale of the database to the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            The full path to the database
	 * @param password
	 *            to use to open and/or create a database file (char array)
	 * @param factory
	 *            The factory to use when creating cursors, may be NULL.
	 * @param flags
	 *            0 or {@link #NO_LOCALIZED_COLLATORS}. If the database file
	 *            already exists, mFlags will be updated appropriately.
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public SQLiteDatabase(String path, char[] password, CursorFactory factory, int flags) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Public constructor which attempts to open the database. See
	 * {@link #create} and {@link #openDatabase}.
	 *
	 * <p>
	 * Sets the locale of the database to the system's current locale. Call
	 * {@link #setLocale} if you would like something else.
	 * </p>
	 *
	 * @param path
	 *            The full path to the database
	 * @param password
	 *            to use to open and/or create a database file (char array)
	 * @param factory
	 *            The factory to use when creating cursors, may be NULL.
	 * @param flags
	 *            0 or {@link #NO_LOCALIZED_COLLATORS}. If the database file
	 *            already exists, mFlags will be updated appropriately.
	 * @param databaseHook
	 *            to run on pre/post key events
	 *
	 * @throws SQLiteException
	 *             if the database cannot be opened
	 * @throws IllegalArgumentException
	 *             if the database path is null
	 */
	public SQLiteDatabase(String path, char[] password, CursorFactory factory, int flags,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	public SQLiteDatabase(String path, byte[] password, CursorFactory factory, int flags,
			SQLiteDatabaseHook databaseHook) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * return whether the DB is opened as read only.
	 * 
	 * @return true if DB is opened as read only
	 */
	public boolean isReadOnly() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * @return true if the DB is currently open (has not been closed)
	 */
	public boolean isOpen() {
		throw (new RuntimeException("Not implements"));
	}

	public boolean needUpgrade(int newVersion) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Getter for the path to the database file.
	 *
	 * @return the path to our database file.
	 */
	public final String getPath() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the locale for this database. Does nothing if this database has the
	 * NO_LOCALIZED_COLLATORS flag set or was opened read only.
	 *
	 * @throws SQLException
	 *             if the locale could not be set. The most common reason for
	 *             this is that there is no collator available for the locale
	 *             you requested. In this case the database remains unchanged.
	 */
	public void setLocale(Locale locale) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * returns true if the given sql is cached in compiled-sql cache.
	 * 
	 * @hide
	 */
	public boolean isInCompiledSqlCache(String sql) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * purges the given sql from the compiled-sql cache.
	 * 
	 * @hide
	 */
	public void purgeFromCompiledSqlCache(String sql) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * remove everything from the compiled sql cache
	 * 
	 * @hide
	 */
	public void resetCompiledSqlCache() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * return the current maxCacheSqlCacheSize
	 * 
	 * @hide
	 */
	public synchronized int getMaxSqlCacheSize() {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * set the max size of the compiled sql cache for this database after
	 * purging the cache. (size of the cache = number of compiled-sql-statements
	 * stored in the cache).
	 *
	 * max cache size can ONLY be increased from its current size (default = 0).
	 * if this method is called with smaller size than the current value of
	 * mMaxSqlCacheSize, then IllegalStateException is thrown
	 *
	 * synchronized because we don't want t threads to change cache size at the
	 * same time.
	 * 
	 * @param cacheSize
	 *            the size of the cache. can be (0 to MAX_SQL_CACHE_SIZE)
	 * @throws IllegalStateException
	 *             if input cacheSize > MAX_SQL_CACHE_SIZE or < 0 or < the value
	 *             set with previous setMaxSqlCacheSize() call.
	 *
	 * @hide
	 */
	public synchronized void setMaxSqlCacheSize(int cacheSize) {
		throw (new RuntimeException("Not implements"));
	}

	public static byte[] getBytes(char[] data) {
		throw (new RuntimeException("Not implements"));
	}

	public static char[] getChars(byte[] data) {
		throw (new RuntimeException("Not implements"));
	}

	/* begin SQLiteSupportDatabase methods */

	@Override
	public android.database.Cursor query(String query) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public android.database.Cursor query(String query, Object[] bindArgs) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public android.database.Cursor query(SupportSQLiteQuery query) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public android.database.Cursor query(final SupportSQLiteQuery supportQuery, CancellationSignal cancellationSignal) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public long insert(String table, int conflictAlgorithm, ContentValues values) throws android.database.SQLException {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public int update(String table, int conflictAlgorithm, ContentValues values, String whereClause,
			Object[] whereArgs) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public void beginTransactionWithListener(
			final android.database.sqlite.SQLiteTransactionListener transactionListener) {
		throw (new RuntimeException("Not implements"));
	}

	@Override
	public void beginTransactionWithListenerNonExclusive(
			final android.database.sqlite.SQLiteTransactionListener transactionListener) {
		throw (new RuntimeException("Not implements"));
	}

	/**
	 * Sets the root directory to search for the ICU data file
	 */
	public static native void setICURoot(String path);

	@Override
	protected void onAllReferencesReleased() {
		// TODO Auto-generated method stub
		
	}

}

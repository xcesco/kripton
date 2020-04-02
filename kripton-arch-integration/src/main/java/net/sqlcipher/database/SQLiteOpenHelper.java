package net.sqlcipher.database;

import android.content.Context;
import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;

public abstract class SQLiteOpenHelper {
   
    public SQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
    	throw new RuntimeException("Not implemented");
    }

    public SQLiteOpenHelper(Context context, String name, CursorFactory factory,
                            int version, SQLiteDatabaseHook hook) {
    	throw new RuntimeException("Not implemented");
    }

    public SQLiteOpenHelper(Context context, String name, CursorFactory factory,
                            int version, SQLiteDatabaseHook hook, DatabaseErrorHandler errorHandler) {
    }

    public synchronized SQLiteDatabase getWritableDatabase(String password) {
    	throw new RuntimeException("Not implemented");
    }

    public synchronized SQLiteDatabase getWritableDatabase(char[] password) {
    	throw new RuntimeException("Not implemented");
    }

    public synchronized SQLiteDatabase getWritableDatabase(byte[] password) {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Create and/or open a database.  This will be the same object returned by
     * {@link #getWritableDatabase} unless some problem, such as a full disk,
     * requires the database to be opened read-only.  In that case, a read-only
     * database object will be returned.  If the problem is fixed, a future call
     * to {@link #getWritableDatabase} may succeed, in which case the read-only
     * database object will be closed and the read/write object will be returned
     * in the future.
     *
     * @throws SQLiteException if the database cannot be opened
     * @return a database object valid until {@link #getWritableDatabase}
     *     or {@link #close} is called.
     */
    public synchronized SQLiteDatabase getReadableDatabase(String password) {
    	throw new RuntimeException("Not implemented");
    }

    public synchronized SQLiteDatabase getReadableDatabase(char[] password) {
    	throw new RuntimeException("Not implemented");
    }

    public synchronized SQLiteDatabase getReadableDatabase(byte[] password) {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Close any open database object.
     */
    public synchronized void close() {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Return the name of the SQLite database being opened, as given to
     * the constructor.
     */
    public String getDatabaseName() {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Enables or disables the use of write-ahead logging for the database.
     *
     * Write-ahead logging cannot be used with read-only databases so the value of
     * this flag is ignored if the database is opened read-only.
     *
     * @param enabled True if write-ahead logging should be enabled, false if it
     * should be disabled.
     *
     * @see SQLiteDatabase#enableWriteAheadLogging()
     */
    public void setWriteAheadLoggingEnabled(boolean enabled) {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Called when the database needs to be downgraded. This is strictly similar to
     * {@link #onUpgrade} method, but is called whenever current version is newer than requested one.
     * However, this method is not abstract, so it is not mandatory for a customer to
     * implement it. If not overridden, default implementation will reject downgrade and
     * throws SQLiteException
     *
     * <p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	throw new RuntimeException("Not implemented");
    }

    /**
     * Called when the database connection is being configured, to enable features
     * such as write-ahead logging or foreign key support.
     * <p>
     * This method is called before {@link #onCreate}, {@link #onUpgrade},
     * {@link #onDowngrade}, or {@link #onOpen} are called.  It should not modify
     * the database except to configure the database connection as required.
     * </p><p>
     * This method should only call methods that configure the parameters of the
     * database connection, such as {@link SQLiteDatabase#enableWriteAheadLogging}
     * {@link SQLiteDatabase#setForeignKeyConstraintsEnabled},
     * {@link SQLiteDatabase#setLocale}, or executing PRAGMA statements.
     * </p>
     *
     * @param db The database.
     */
    public void onConfigure(SQLiteDatabase db) {}

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    public abstract void onCreate(SQLiteDatabase db);

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    /**
     * Called when the database has been opened.
     * Override method should check {@link SQLiteDatabase#isReadOnly} before
     * updating the database.
     *
     * @param db The database.
     */
    public void onOpen(SQLiteDatabase db) {}
}


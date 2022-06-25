/**
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Builder;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * Base class for data source
 * </p>
 * .
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractDataSource implements AutoCloseable {

    /**
     * Interface for database operations.
     *
     * @param <E> the element type
     */
    public interface AbstractExecutable<E extends BindDaoFactory> {

        /**
         * Execute transation. Method need to return
         * {@link TransactionResult#COMMIT} to commit results or
         * {@link TransactionResult#ROLLBACK} to rollback. If exception is
         * thrown, a rollback will be done.
         *
         * @param daoFactory the dao factory
         * @return the transaction result
         */
        TransactionResult onExecute(@NonNull E daoFactory);
    }

    /**
     * The listener interface for receiving onError events. The class that is
     * interested in processing a onError event implements this interface, and
     * the object created with that class is registered with a component using
     * the component's <code>addOnErrorListener</code> method. When the onError
     * event occurs, that object's appropriate method is invoked.
     */
    public interface OnErrorListener {
        /**
         * Manages error situations.
         *
         * @param e exception
         */
        void onError(@NonNull Throwable e);
    }

    /**
     * The Enum TypeStatus.
     */
    public enum TypeStatus {

        /**
         * The closed.
         */
        CLOSED,
        /**
         * The read and write opened.
         */
        READ_AND_WRITE_OPENED,
        /**
         * The read only opened.
         */
        READ_ONLY_OPENED
    }

    /**
     * The context.
     */
    protected SQLContextImpl context;

    /**
     * database instance.
     */
    SupportSQLiteDatabase database;

    /**
     * <p>
     * True if dataSource is just created
     * </p>
     * .
     */
    protected boolean justCreated = false;

    /**
     * The lock access.
     */
    private final ReentrantReadWriteLock lockAccess = new ReentrantReadWriteLock();

    /**
     * The lock db.
     */
    private final ReentrantLock lockDb = new ReentrantLock();

    /**
     * The lock read access.
     */
    private final Lock lockReadAccess = lockAccess.readLock();

    /**
     * The lock read write access.
     */
    private final Lock lockReadWriteAccess = lockAccess.writeLock();

    /**
     * The log enabled.
     */
    protected boolean logEnabled;

    /**
     * <p>
     * file name used to save database,
     * </p>
     * .
     */
    protected final String name;

    /**
     * The on error listener.
     */
    protected OnErrorListener onErrorListener = (Throwable e) -> {
        throw (new KriptonRuntimeException(e));
    };


    /**
     * The open counter.
     */
    private final AtomicInteger openCounter = new AtomicInteger();

    /**
     * The options.
     */
    protected DataSourceOptions options;

    /**
     * The sqlite helper.
     */
    protected SupportSQLiteOpenHelper sqliteHelper;

    /**
     * The status. Do not replace with Initial, it does not work on Android.
     */
    protected ThreadLocal<TypeStatus> status = new ThreadLocal<TypeStatus>() {

        @Override
        protected TypeStatus initialValue() {
            return TypeStatus.CLOSED;
        }

    };
    /**
     * <p>
     * database version
     * </p>
     * .
     */
    protected int version;

    /**
     * if true, database was update during this application run.
     */
    protected boolean versionChanged;

    /**
     * Instantiates a new abstract data source.
     *
     * @param name    the name
     * @param version the version
     * @param options the options
     */
    protected AbstractDataSource(String name, int version, DataSourceOptions options) {
        DataSourceOptions optionsValue = (options != null) ? options : DataSourceOptions.builder().build();

        if (optionsValue.inMemory) {
            this.name = null;
        } else if (StringUtils.hasText(optionsValue.name)) {
            this.name = optionsValue.name;
        } else {
            this.name = name;
        }

        this.version = version;

        // create new SQLContext
        this.context = new SQLContextImpl(this);

        this.options = optionsValue;
        this.logEnabled = optionsValue.logEnabled;

        if (this.logEnabled) {
            Logger.debug("%s is created with %s", getClass().getName(), optionsValue.toString());
        }
    }

    protected void beginLock() {
        lockDb.lock();
    }

    /**
     * Builds the task list.
     *
     * @param previousVersion the previous version
     * @param currentVersion  the current version
     * @return the list
     */
    protected List<SQLiteUpdateTask> buildTaskList(int previousVersion, int currentVersion) {
        List<SQLiteUpdateTask> result = new ArrayList<>();

        for (Pair<Integer, ? extends SQLiteUpdateTask> item : this.options.updateTasks) {
            if (item.value0 - 1 == previousVersion) {
                result.add(item.value1);
                previousVersion = item.value0;
            }

            if (previousVersion == currentVersion)
                break;
        }

        if (previousVersion != currentVersion) {
            Logger.warn(String.format("Can not find version update task from version %s to version %s", previousVersion,
                    currentVersion));
        }

        return result;

    }

    /**
     * used to clear prepared statements.
     */
    public abstract void clearCompiledStatements();

    /**
     * Context.
     *
     * @return the SQL context
     */
    public SQLContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.database.sqlite.SQLiteOpenHelper#close()
     */
    @Override
    public void close() {
        beginLock();
        try {
            if (openCounter.decrementAndGet() <= 0) {

                if (!this.options.inMemory) {
                    // Closing database
                    if (database != null) {
                        clearCompiledStatements();
                        sqliteHelper.close();
                    }
                    database = null;
                }
                if (logEnabled)
                    Logger.info("database CLOSED (%s) (connections: %s)", status.get(), openCounter.intValue());
            } else {
                if (logEnabled)
                    Logger.info("database RELEASED (%s) (connections: %s)", status.get(), openCounter.intValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        } finally {
            manageStatus();
            endLock();
        }

    }

    protected void closeThreadSafeMode(Pair<Boolean, SupportSQLiteDatabase> status) {
        if (status.value0) {
            close();
        } else {
            beginLock();
            // we unlock lockReadWriteAccess, so we can include this code in
            manageStatus();
            endLock();
        }
    }

    /**
     * Content values.
     *
     * @param compiledStatement the compiled statement
     * @return the kripton content values
     */
    protected KriptonContentValues contentValues(SupportSQLiteStatement compiledStatement) {
        return context.contentValues(compiledStatement);
    }

    /**
     * Content values for content provider.
     *
     * @param values the values
     * @return the kripton content values
     */
    protected KriptonContentValues contentValuesForContentProvider(ContentValues values) {
        return context.contentValuesForContentProvider(values);
    }

    /**
     * Content values for update.
     *
     * @param compiledStatement the compiled statement
     * @return the kripton content values
     */
    protected KriptonContentValues contentValuesForUpdate(SupportSQLiteStatement compiledStatement) {
        return context.contentValuesForUpdate(compiledStatement);
    }

    /**
     * Creates the helper.
     */
    protected void createHelper() {
        if (KriptonLibrary.getContext() == null)
            throw new KriptonRuntimeException(
                    "Kripton library is not properly initialized. Please use KriptonLibrary.init(context) somewhere at application startup");

        if (this.logEnabled) {
            if (options.inMemory) {
                Logger.info("In-memory database");
            } else {
                File dbFile = KriptonLibrary.getContext().getDatabasePath(name);
                Logger.info("Database file %s", dbFile.getAbsolutePath());
            }
        }

        Builder config = SupportSQLiteOpenHelper.Configuration.builder(KriptonLibrary.getContext()).name(name)
                .callback(new SupportSQLiteOpenHelper.Callback(version) {

                    @Override
                    public void onConfigure(@NonNull SupportSQLiteDatabase db) {
                        AbstractDataSource.this.onConfigure(db);
                    }

                    @Override
                    public void onCorruption(@NonNull SupportSQLiteDatabase db) {
                        AbstractDataSource.this.onCorruption(db);
                    }

                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        sqliteHelper.setWriteAheadLoggingEnabled(true);
                        AbstractDataSource.this.onCreate(db);
                    }

                    @Override
                    public void onDowngrade(@NonNull SupportSQLiteDatabase db, int oldVersion, int newVersion) {
                        AbstractDataSource.this.onDowngrade(db, oldVersion, newVersion);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        sqliteHelper.setWriteAheadLoggingEnabled(true);
                        AbstractDataSource.this.onOpen(db);
                    }

                    @Override
                    public void onUpgrade(@NonNull SupportSQLiteDatabase db, int oldVersion, int newVersion) {
                        AbstractDataSource.this.onUpgrade(db, oldVersion, newVersion);
                    }
                });

        sqliteHelper = options.openHelperFactory.create(config.build());

        if (this.logEnabled) {
            Logger.debug("Database helper factory class is %s", options.openHelperFactory.getClass().getName());
            Logger.debug("Database helper class is %s", sqliteHelper.getClass().getName());
        }
    }

    private void deleteDatabaseFile(String fileName) {
        if (fileName.equalsIgnoreCase(":memory:") || fileName.trim().length() == 0) {
            return;
        }
        if (this.logEnabled) {
            Logger.fatal("deleting the database file: " + fileName);
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                SQLiteDatabase.deleteDatabase(new File(fileName));
            } else {
                try {
                    final boolean deleted = new File(fileName).delete();
                    if (!deleted && this.logEnabled) {
                        Logger.fatal("Could not delete the database file " + fileName);
                    }
                } catch (Exception error) {
                    if (this.logEnabled) {
                        Logger.fatal("error while deleting corrupted database file " + error.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            if (this.logEnabled) {
                /* print warning and ignore exception */
                Logger.warn("delete failed: ", e);
            }
        }
    }

    protected void endLock() {
        lockDb.unlock();
    }

    /**
     * Force close.
     */
    void forceClose() {
        openCounter.set(0);
    }

    /**
     * <p>
     * Return database object or runtimeexception if no database is opened.
     * </p>
     *
     * @return the SQ lite database
     */
    public SupportSQLiteDatabase getDatabase() {
        if (database == null)
            throw (new KriptonRuntimeException(
                    "No database connection is opened before use " + this.getClass().getCanonicalName()));
        return database;
    }

    public String getName() {
        return name;
    }

    /**
     * Get error listener, in transations.
     *
     * @return the on error listener
     */
    public OnErrorListener getOnErrorListener() {
        return onErrorListener;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * <p>
     * True if dataSource is just created
     * </p>
     * .
     *
     * @return true, if is just created
     */
    public boolean isJustCreated() {
        return justCreated;
    }

    /**
     * Checks if is log enabled.
     *
     * @return true, if is log enabled
     */
    public boolean isLogEnabled() {
        return context.isLogEnabled();
    }

    /**
     * <p>
     * return true if database is already opened.
     * </p>
     *
     * @return true if database is opened, otherwise false
     */
    public boolean isOpen() {
        return database != null && database.isOpen() && database.isDbLockedByCurrentThread();
    }

    /**
     * Return <code>true</code> if any operation is running on datasource,
     * <code>false</code> if database is currently closed.
     *
     * @return
     */
    public boolean isAnyPendingOperation() {
        return openCounter.get() > 0;
    }

    /**
     * <p>
     * return true if database is already opened in write mode.
     * </p>
     *
     * @return true if database is opened, otherwise false
     */
    public boolean isOpenInWriteMode() {
        return database != null && database.isOpen() && !database.isReadOnly() && database.isDbLockedByCurrentThread();
    }

    /**
     * Checks if is upgraded version.
     *
     * @return the upgradedVersion
     */
    public boolean isUpgradedVersion() {
        return versionChanged;
    }

    /**
     *
     */
    private void manageStatus() {
        switch (status.get()) {
            case READ_AND_WRITE_OPENED:
                if (database == null)
                    status.set(TypeStatus.CLOSED);
                lockReadWriteAccess.unlock();
                break;
            case READ_ONLY_OPENED:
                if (database == null)
                    status.set(TypeStatus.CLOSED);
                lockReadAccess.unlock();
                break;
            case CLOSED:
                // do nothing
                break;
        }
    }

    /**
     * Returns <code>true</code> if the database need foreign keys
     *
     * @return
     */
    public abstract boolean hasForeignKeys();

    /**
     * On configure.
     *
     * @param database the database
     */
    protected void onConfigure(SupportSQLiteDatabase database) {
        // configure database
        if (options.databaseLifecycleHandler != null) {
            options.databaseLifecycleHandler.onConfigure(database);
        }
    }

    /**
     * The method invoked when database corruption is detected. Default
     * implementation will delete the database file.
     *
     * @param db the {@link SupportSQLiteDatabase} object representing the
     *           database on which corruption is detected.
     */
    protected void onCorruption(@NonNull SupportSQLiteDatabase db) {
        // the following implementation is taken from {@link
        // DefaultDatabaseErrorHandler}.
        if (this.logEnabled) {
            Logger.fatal("Corruption reported by sqlite on database: " + db.getPath());
        }
        try {
            if (options.databaseLifecycleHandler != null) {
                options.databaseLifecycleHandler.onCorruption(db);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

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
        List<android.util.Pair<String, String>> attachedDbs = null;
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
                for (android.util.Pair<String, String> p : attachedDbs) {
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

    /**
     * On create.
     *
     * @param database the database
     */
    protected abstract void onCreate(SupportSQLiteDatabase database);

    /**
     * On downgrade.
     *
     * @param db         the db
     * @param oldVersion the old version
     * @param newVersion the new version
     */
    protected void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        if (options.databaseLifecycleHandler != null) {
            options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, false);
            versionChanged = true;
        }
    }

    protected void onOpen(SupportSQLiteDatabase db) {
        if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
            AbstractDataSource.this.options.databaseLifecycleHandler.onOpen(db);
            versionChanged = true;
        }
    }

    /**
     * On session closed.
     *
     * @return the sets the
     */
    protected Set<Integer> onSessionClosed() {
        return this.context.onSessionClosed();

    }

    /**
     * On session opened.
     */
    protected void onSessionOpened() {
        this.context.onSessionOpened();
    }

    /**
     * On upgrade.
     *
     * @param db         the db
     * @param oldVersion the old version
     * @param newVersion the new version
     */
    protected void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        if (AbstractDataSource.this.options.databaseLifecycleHandler != null) {
            AbstractDataSource.this.options.databaseLifecycleHandler.onUpdate(db, oldVersion, newVersion, true);
            versionChanged = true;
        }
    }

    /**
     * Open a database, if it is needed in
     *
     * @param writeMode
     * @return
     */
    protected Pair<Boolean, SupportSQLiteDatabase> openDatabaseThreadSafeMode(boolean writeMode) {
        Pair<Boolean, SupportSQLiteDatabase> result = new Pair<>();

        try {
            // lock entire operation set
            beginLock();
            boolean needToOpened = writeMode ? !this.isOpenInWriteMode() : !this.isOpen();
            result.value0 = needToOpened;
            // in this part we can not lock lockReadWriteAccess, otherwise it
            // may be a
            // blocking race
            // we lock lockReadWriteAccess after we release
            if (needToOpened) {
                if (writeMode) {
                    result.value1 = openWritableDatabase(false);
                } else {
                    result.value1 = openReadOnlyDatabase(false);
                }
            } else {
                result.value1 = this.getDatabase();
            }

        } finally {
            // unlock entire operation set
            endLock();

            if (writeMode) {
                lockReadWriteAccess.lock();
            } else {
                lockReadAccess.lock();
            }

        }

        return result;

    }

    public SupportSQLiteDatabase openReadOnlyDatabase() {
        return openReadOnlyDatabase(true);
    }

    /**
     * <p>
     * Open a read only database.
     * </p>
     *
     * @return read only database
     */
    protected SupportSQLiteDatabase openReadOnlyDatabase(boolean lock) {
        if (lock) {
            // if I lock this in dbLock.. the last one remains locked too
            lockReadAccess.lock();

            beginLock();
        }

        try {
            if (sqliteHelper == null)
                createHelper();

            status.set(TypeStatus.READ_ONLY_OPENED);

            if (openCounter.incrementAndGet() == 1) {
                // open new read database
                if (database == null) {
                    sqliteHelper.setWriteAheadLoggingEnabled(true);
                    database = sqliteHelper.getReadableDatabase();
                    database.setForeignKeyConstraintsEnabled(hasForeignKeys());
                }
                if (logEnabled)
                    Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
            } else {
                if (logEnabled)
                    Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
            }
        } catch (Throwable e) {
            if (logEnabled) {
                Logger.fatal("database error during open operation: %s", e.getMessage());
                e.printStackTrace();
            }
            throw (e);
        } finally {
            if (lock)
                endLock();

        }

        return database;
    }

    /**
     * <p>
     * open a writable database.
     * </p>
     *
     * @return writable database
     */
    public SupportSQLiteDatabase openWritableDatabase() {
        return openWritableDatabase(true);
    }

    protected SupportSQLiteDatabase openWritableDatabase(boolean lock) {
        if (lock) {
            lockReadWriteAccess.lock();

            // if I lock this in dbLock.. the last one remains locked too
            beginLock();
        }

        try {
            if (sqliteHelper == null)
                createHelper();

            status.set(TypeStatus.READ_AND_WRITE_OPENED);

            if (openCounter.incrementAndGet() == 1) {
                // open new write database
                if (database == null) {
                    sqliteHelper.setWriteAheadLoggingEnabled(true);
                    database = sqliteHelper.getWritableDatabase();
                    database.setForeignKeyConstraintsEnabled(hasForeignKeys());
                }
                if (logEnabled)
                    Logger.info("database OPEN %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
            } else {
                if (logEnabled)
                    Logger.info("database REUSE %s (connections: %s)", status.get(), (openCounter.intValue() - 1));
            }
        } catch (Throwable e) {
            if (logEnabled) {
                Logger.fatal("database error during open operation: %s", e.getMessage());
                e.printStackTrace();
            }
            throw (e);
        } finally {
            if (lock)
                endLock();
        }

        return database;
    }

    /**
     * Set error listener for transactions.
     *
     * @param onErrorListener the new on error listener
     */
    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    /**
     * Sql builder.
     *
     * @return the string builder
     */
    protected StringBuilder sqlBuilder() {
        return context.sqlBuilder();
    }

}

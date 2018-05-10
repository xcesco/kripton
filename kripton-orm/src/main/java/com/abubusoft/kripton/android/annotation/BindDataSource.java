/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation decorate an interface to define a datasource associated to a
 * database schema. Between its attributes there is a DAO collection. Every DAO
 * is defined by an interface annotated with @{@link BindDao}. Every DAO and is
 * associated to a specific Java class which is associated to a specific table.
 * 
 * A data source class name have to finish with <code>DataSource</code> suffix.
 * </p>
 * 
 * <h2>Attributes</h2>
 * <p>
 * List of attributes is:
 * </p>
 * <ul>
 * <li><strong>asyncTask</strong>: if true, generate async task name
 * `Bind&lt;data source name without DataSource prefix&gt;AsyncTask`</li>
 * <li><strong>cursorWrapper</strong>: if true, generate a wrapped cursor for
 * every Java class managed by data-source. Cursor's name is
 * <code>Bind{data source
 * name without DataSource prefix}Cursor</code>.</li>
 * <li><strong>daoSet</strong>: the collection of DAO associated to
 * data-source.</li>
 * <li><strong>fileName</strong>: filename used to store database</li>
 * <li><strong>rx</strong>: enable reactive programming support. It's necessary
 * to include in project RX dependencies.</li>
 * <li><strong>log</strong>: controls generation of the log of SQL on
 * logcat.</li>
 * <li><strong>typeAdapters</strong>: Global sql-type-adapters. These adapters
 * are applied to every property that adapter supports. See
 * <a href="https://github.com/xcesco/kripton/wiki/Global-SQL-Type-adapter">this
 * page</a> for more information.</li>
 * <li><strong>version</strong>: database version. The default version is
 * 1.</li>
 * <li><strong>schema</strong>: if true generates schema DDL in a separate file
 * in filder <code>schemas</code>. Visit
 * <a href="https://github.com/xcesco/kripton/wiki/Generate-schemas">Kripton
 * wiki</a> for more information about it.</li>
 * </ul>
 * 
 * <h2>Usage</h2> Consider this interface to define a data source:
 * 
 * <pre>
 * &#64;BindDataSource(daoSet = { PersonDao.class }, fileName = "feature01.db")
 * public interface SampleDataSource {
 * }
 * </pre>
 * 
 * <p>
 * With this interface, it is defined a data source name `SampleDataSource ` and
 * is referred a DAO named `PersonDao`.
 * </p>
 * 
 * <p>
 * When Kripton annotation processor process interface `SampleDataSource `, it
 * generate a class name `BindSampleDataSource ` similar to:
 * </p>
 * 
 * <pre>
 * public class BindSampleDataSource extends AbstractDataSource implements BindSampleDaoFactory, SampleDataSource {
 * 	static BindSampleDataSource instance;
 * 
 * 	protected PersonDaoImpl personDao = new PersonDaoImpl(this);
 * 
 * 	private final DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();
 * 
 * 	protected BindSampleDataSource(DataSourceOptions options) {
 * 		super("feature01.db", 1, options);
 * 	}
 * 
 * 	&#64;Override
 * 	public PersonDaoImpl getPersonDao() {
 * 		return personDao;
 * 	}
 * 
 * 	public void execute(Transaction transaction) {
 * 		execute(transaction, onErrorListener);
 * 	}
 * 
 * 	public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
 * 		boolean needToOpened = !this.isOpenInWriteMode();
 * 		&#64;SuppressWarnings("resource")
 * 		SQLiteDatabase connection = needToOpened ? openWritableDatabase() : database();
 * 		try {
 * 			connection.beginTransaction();
 * 			if (transaction != null
 * 					&amp;&amp; TransactionResult.COMMIT == transaction.onExecute(_daoFactorySingleThread.bindToThread())) {
 * 				connection.setTransactionSuccessful();
 * 			}
 * 		} catch (Throwable e) {
 * 			Logger.error(e.getMessage());
 * 			e.printStackTrace();
 * 			if (onErrorListener != null)
 * 				onErrorListener.onError(e);
 * 		} finally {
 * 			try {
 * 				connection.endTransaction();
 * 			} catch (Throwable e) {
 * 				Logger.warn("error closing transaction %s", e.getMessage());
 * 			}
 * 			if (needToOpened) {
 * 				close();
 * 			}
 * 		}
 * 	}
 * 
 * 	public &lt;T&gt; T executeBatch(Batch&lt;T&gt; commands) {
 * 		return executeBatch(commands, false);
 * 	}
 * 
 * 	public &lt;T&gt; T executeBatch(Batch&lt;T&gt; commands, boolean writeMode) {
 * 		boolean needToOpened = writeMode ? !this.isOpenInWriteMode() : !this.isOpen();
 * 		if (needToOpened) {
 * 			if (writeMode) {
 * 				openWritableDatabase();
 * 			} else {
 * 				openReadOnlyDatabase();
 * 			}
 * 		}
 * 		try {
 * 			if (commands != null) {
 * 				return commands.onExecute(new DataSourceSingleThread());
 * 			}
 * 		} catch (Throwable e) {
 * 			Logger.error(e.getMessage());
 * 			e.printStackTrace();
 * 			throw (e);
 * 		} finally {
 * 			if (needToOpened) {
 * 				close();
 * 			}
 * 		}
 * 		return null;
 * 	}
 * 
 * 	public static synchronized BindSampleDataSource getInstance() {
 * 		if (instance == null) {
 * 			instance = new BindSampleDataSource(null);
 * 		}
 * 		return instance;
 * 	}
 * 
 * 	public static BindSampleDataSource open() {
 * 		BindSampleDataSource instance = getInstance();
 * 		instance.openWritableDatabase();
 * 		return instance;
 * 	}
 * 
 * 	public static BindSampleDataSource openReadOnly() {
 * 		BindSampleDataSource instance = getInstance();
 * 		instance.openReadOnlyDatabase();
 * 		return instance;
 * 	}
 * 
 * 	&#64;Override
 * 	public void onCreate(SQLiteDatabase database) {
 * 		// generate tables
 * 		// log section BEGIN
 * 		if (this.logEnabled) {
 * 			Logger.info("Create database '%s' version %s", this.name, this.getVersion());
 * 		}
 * 		// log section END
 * 		// log section BEGIN
 * 		if (this.logEnabled) {
 * 			Logger.info("DDL: %s", PersonTable.CREATE_TABLE_SQL);
 * 		}
 * 		// log section END
 * 		database.execSQL(PersonTable.CREATE_TABLE_SQL);
 * 		// if we have a populate task (previous and current are same), try to
 * 		// execute it
 * 		if (options.updateTasks != null) {
 * 			SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
 * 			if (task != null) {
 * 				// log section BEGIN
 * 				if (this.logEnabled) {
 * 					Logger.info("Begin update database from version %s to %s", task.previousVersion,
 * 							task.currentVersion);
 * 				}
 * 				// log section END
 * 				task.execute(database);
 * 				// log section BEGIN
 * 				if (this.logEnabled) {
 * 					Logger.info("End update database from version %s to %s", task.previousVersion,
 * 							task.currentVersion);
 * 				}
 * 				// log section END
 * 			}
 * 		}
 * 		if (options.databaseLifecycleHandler != null) {
 * 			options.databaseLifecycleHandler.onCreate(database);
 * 		}
 * 	}
 * 
 * 	&#64;Override
 * 	public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
 * 		// log section BEGIN
 * 		if (this.logEnabled) {
 * 			Logger.info("Update database '%s' from version %s to version %s", this.name, previousVersion,
 * 					currentVersion);
 * 		}
 * 		// log section END
 * 		// if we have a list of update task, try to execute them
 * 		if (options.updateTasks != null) {
 * 			List&lt;SQLiteUpdateTask&gt; tasks = buildTaskList(previousVersion, currentVersion);
 * 			for (SQLiteUpdateTask task : tasks) {
 * 				// log section BEGIN
 * 				if (this.logEnabled) {
 * 					Logger.info("Begin update database from version %s to %s", task.previousVersion,
 * 							task.currentVersion);
 * 				}
 * 				// log section END
 * 				task.execute(database);
 * 				// log section BEGIN
 * 				if (this.logEnabled) {
 * 					Logger.info("End update database from version %s to %s", task.previousVersion,
 * 							task.currentVersion);
 * 				}
 * 				// log section END
 * 			}
 * 		} else {
 * 			// drop all tables
 * 			SQLiteUpdateTaskHelper.dropTablesAndIndices(database);
 * 
 * 			// generate tables
 * 			// log section BEGIN
 * 			if (this.logEnabled) {
 * 				Logger.info("DDL: %s", PersonTable.CREATE_TABLE_SQL);
 * 			}
 * 			// log section END
 * 			database.execSQL(PersonTable.CREATE_TABLE_SQL);
 * 		}
 * 		if (options.databaseLifecycleHandler != null) {
 * 			options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
 * 		}
 * 	}
 * 
 * 	&#64;Override
 * 	public void onConfigure(SQLiteDatabase database) {
 * 		// configure database
 * 		if (options.databaseLifecycleHandler != null) {
 * 			options.databaseLifecycleHandler.onConfigure(database);
 * 		}
 * 	}
 * 
 * 	public void clearCompiledStatements() {
 * 		PersonDaoImpl.clearCompiledStatements();
 * 	}
 * 
 * 	public static synchronized BindSampleDataSource build(DataSourceOptions options) {
 * 		if (instance == null) {
 * 			instance = new BindSampleDataSource(options);
 * 		}
 * 		instance.openWritableDatabase();
 * 		instance.close();
 * 		return instance;
 * 	}
 * 
 * 	public static synchronized BindSampleDataSource build() {
 * 		return build(DataSourceOptions.builder().build());
 * 	}
 * 
 * 	public interface Transaction extends AbstractDataSource.AbstractExecutable&lt;BindSampleDaoFactory&gt; {
 * 		TransactionResult onExecute(BindSampleDaoFactory daoFactory);
 * 	}
 * 
 * 	public interface Batch&lt;T&gt; {
 * 		T onExecute(BindSampleDaoFactory daoFactory);
 * 	}
 * 
 * 	class DataSourceSingleThread implements BindSampleDaoFactory {
 * 		private SQLContextSingleThreadImpl _context;
 * 
 * 		private PersonDaoImpl _personDao;
 * 
 * 		DataSourceSingleThread() {
 * 			_context = new SQLContextSingleThreadImpl(BindSampleDataSource.this);
 * 		}
 * 
 * 		public PersonDaoImpl getPersonDao() {
 * 			if (_personDao == null) {
 * 				_personDao = new PersonDaoImpl(_context);
 * 			}
 * 			return _personDao;
 * 		}
 * 
 * 		public DataSourceSingleThread bindToThread() {
 * 			_context.bindToThread();
 * 			return this;
 * 		}
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * Generated data source class derived from <a href=
 * "https://github.com/xcesco/kripton/blob/master/KriptonAndroidLibrary/src/main/java/com/abubusoft/kripton/android/sqlite/AbstractDataSource.java">AbstractDataSource.java</a>
 * that derives from <a href=
 * "http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/5.1.1_r1/android/database/sqlite/SQLiteOpenHelper.java">SQLiteOpenHelper.java</a>.
 * </p>
 * 
 * <p>
 * To use a data source in a client application, just retrieve a reference to
 * singleton instance of data source
 * </p>
 * 
 * <pre>
 * BindSampleDataSource dataSource = BindSampleDataSource.getInstance();
 * </pre>
 * <p>
 * To execute a transaction, just invoke the following code
 * </p>
 * 
 * <pre>
dataSource.execute(new Transaction() {

  &#64;Override
  public TransationResultonExecute(BindSampleDaoFactory daoFactory) {
    DaoPersonImpl dao = daoFactory.getPersonDao();

    long result = dao.insertRaw1("test", 52);
    dao.insertRaw2("test2", 23)
    
    // commit transaction
    return TransationResult.COMMIT;
  }

});
 * </pre>
 * 
 * <p>
 * If you want to use directly DAO without a transaction just retrieve DAO
 * implementation from data-source
 * </p>
 * 
 * <pre>
 * PersonDaoImpl dao = dataSource.getPersonDao();
 * dao.insertRaw1("test", 52);
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDataSource {

	/**
	 * DAOs to include in the database schema.
	 *
	 * @return class to include in the database schema
	 */
	Class<?>[] daoSet();

	/**
	 * Name of database file.
	 *
	 * @return database name
	 */
	String fileName();

	/**
	 * Database version.
	 *
	 * @return database version
	 */
	int version() default 1;

	/**
	 * if true, generate log info.
	 *
	 * @return true if you want to produce log code
	 */
	boolean log() default true;

	/**
	 * <p>
	 * if true, generate schema sql file, in <code>schema/</code> folder. Visit
	 * this <a href=
	 * "https://github.com/xcesco/kripton/wiki/Generate-schemas">page</a> for
	 * more information.
	 * </p>
	 * 
	 * <p>
	 * The name of file will be:
	 * </p>
	 * 
	 * <pre>
	 * {name of datasource}.schema.{version}.sql
	 * </pre>
	 * 
	 * Example:
	 * 
	 * <pre>
	 * xeno.schema.1.sql
	 * </pre>
	 *
	 * @return true, if successful
	 */
	boolean schema() default false;

	/**
	 * if true, generate async task.
	 *
	 * @return true if you want to generate async task class
	 */
	boolean asyncTask() default false;

	/**
	 * if true, generate cursor wrapper.
	 *
	 * @return true if you want to generate cursor wrapper
	 */
	boolean cursorWrapper() default false;

	/**
	 * if true, generate rx support.
	 *
	 * @return true if you want to generate rx support
	 */
	boolean rx() default false;

	/**
	 * Global sql type adapters. These adapter are applied to every property
	 * that adapter supports.
	 * 
	 * <strong>This adapters is ovverride by specific type adapter</strong>
	 *
	 * @return the bind sql adapter[]
	 */
	BindSqlAdapter[] typeAdapters() default {};
}

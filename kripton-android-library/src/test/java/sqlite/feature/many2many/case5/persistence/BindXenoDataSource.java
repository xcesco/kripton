/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.many2many.case5.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.many2many.case5.model.CountryTable;
import sqlite.feature.many2many.case5.model.PersonTable;
import sqlite.feature.many2many.case5.model.PhoneNumberTable;
import sqlite.feature.many2many.case5.model.PrefixConfigTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents implementation of datasource XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see BindXenoDaoFactory
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public class BindXenoDataSource extends AbstractDataSource implements BindXenoDaoFactory, XenoDataSource {
  
  /** <p>datasource singleton</p>. */
  static volatile BindXenoDataSource instance;

  /** <p>Mutex to manage multithread access to instance</p>. */
  private static final Object mutex = new Object();

  /** Unique identifier for Dao PhoneDao. */
  public static final int PHONE_DAO_UID = 0;

  /** Unique identifier for Dao PrefixConfigDao. */
  public static final int PREFIX_CONFIG_DAO_UID = 1;

  /** Unique identifier for Dao CountryDao. */
  public static final int COUNTRY_DAO_UID = 2;

  /** Unique identifier for Dao Person2PhoneDao. */
  public static final int PERSON2_PHONE_DAO_UID = 3;

  /** Unique identifier for Dao PersonDao. */
  public static final int PERSON_DAO_UID = 4;

  /** List of tables compose datasource. */
  static final SQLiteTable[] TABLES = {new PersonTable(), new PrefixConfigTable(), new PhoneNumberTable(), new CountryTable(), new PersonPhoneNumberTable()};

  /** <p>dao instance</p>. */
  protected PhoneDaoImpl phoneDao = new PhoneDaoImpl(context);

  /** <p>dao instance</p>. */
  protected PrefixConfigDaoImpl prefixConfigDao = new PrefixConfigDaoImpl(context);

  /** <p>dao instance</p>. */
  protected CountryDaoImpl countryDao = new CountryDaoImpl(context);

  /** <p>dao instance</p>. */
  protected Person2PhoneDaoImpl person2PhoneDao = new Person2PhoneDaoImpl(context);

  /** <p>dao instance</p>. */
  protected PersonDaoImpl personDao = new PersonDaoImpl(context);

  /** Used only in transactions (that can be executed one for time. */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  /**
   * Instantiates a new bind xeno data source.
   *
   * @param options the options
   */
  protected BindXenoDataSource(DataSourceOptions options) {
    super("xeno.db", 1, options);
  }

  /* (non-Javadoc)
   * @see sqlite.feature.many2many.case5.persistence.BindXenoDaoFactory#getPhoneDao()
   */
  @Override
  public PhoneDaoImpl getPhoneDao() {
    return phoneDao;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.many2many.case5.persistence.BindXenoDaoFactory#getPrefixConfigDao()
   */
  @Override
  public PrefixConfigDaoImpl getPrefixConfigDao() {
    return prefixConfigDao;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.many2many.case5.persistence.BindXenoDaoFactory#getCountryDao()
   */
  @Override
  public CountryDaoImpl getCountryDao() {
    return countryDao;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.many2many.case5.persistence.BindXenoDaoFactory#getPerson2PhoneDao()
   */
  @Override
  public Person2PhoneDaoImpl getPerson2PhoneDao() {
    return person2PhoneDao;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.many2many.case5.persistence.BindXenoDaoFactory#getPersonDao()
   */
  @Override
  public PersonDaoImpl getPersonDao() {
    return personDao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    execute(transaction, onErrorListener);
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   * @param onErrorListener
   * 	error listener
   */
  public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
    boolean needToOpened=!this.isOpenInWriteMode();
    boolean success=false;
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
    currentDaoFactory.onSessionOpened();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(currentDaoFactory)) {
        connection.setTransactionSuccessful();
        success=true;
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (onErrorListener!=null) onErrorListener.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      if (needToOpened) { close(); }
      if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
    }
  }

  /**
   * <p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>
   *
   * @param <T> the generic type
   * @param commands 	batch to execute
   * @return the t
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param <T> the generic type
   * @param commands 	batch to execute
   * @param writeMode 	true to open connection in write mode, false to open connection in read only mode
   * @return the t
   */
  public <T> T executeBatch(Batch<T> commands, boolean writeMode) {
    boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen();
    if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
    DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
    currentDaoFactory.onSessionOpened();
    try {
      if (commands!=null) {
        return commands.onExecute(currentDaoFactory);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      if (needToOpened) { close(); }
      currentDaoFactory.onSessionClosed();
    }
    return null;
  }

  /**
   * <p>Retrieve instance.</p>
   *
   * @return the bind xeno data source
   */
  public static BindXenoDataSource instance() {
    BindXenoDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindXenoDataSource(options);
          try {
            instance.openWritableDatabase();
            instance.close();
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          }
        }
      }
    }
    return result;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource open() {
    BindXenoDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource openReadOnly() {
    BindXenoDataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate.
   *
   * @param database the database
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    // log section BEGIN
    if (this.logEnabled) {
      if (options.inMemory) {
        Logger.info("Create database in memory");
      } else {
        Logger.info("Create database '%s' version %s",this.name, this.version);
      }
    }
    // log section END
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(CountryTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade.
   *
   * @param database the database
   * @param previousVersion the previous version
   * @param currentVersion the current version
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    }
    // log section END
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("Begin update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        task.execute(database, previousVersion, previousVersion+1);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        previousVersion++;
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(CountryTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure.
   *
   * @param database the database
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.android.sqlite.AbstractDataSource#clearCompiledStatements()
   */
  public void clearCompiledStatements() {
    PhoneDaoImpl.clearCompiledStatements();
    PrefixConfigDaoImpl.clearCompiledStatements();
    CountryDaoImpl.clearCompiledStatements();
    Person2PhoneDaoImpl.clearCompiledStatements();
    PersonDaoImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   *
   * @param options the options
   * @return the bind xeno data source
   */
  public static BindXenoDataSource build(DataSourceOptions options) {
    BindXenoDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindXenoDataSource(options);
          try {
            instance.openWritableDatabase();
            instance.close();
            // force database DDL run
            if (options.populator!=null && instance.justCreated) {
              // run populator only a time
              instance.justCreated=false;
              // run populator
              options.populator.execute();
            }
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          }
        } else {
          throw new KriptonRuntimeException("Datasource BindXenoDataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindXenoDataSource is already builded");
    }
    return result;
  }

  /**
   * List of tables compose datasource:.
   *
   * @return the SQ lite table[]
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindXenoDaoFactory> {
    
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory the dao factory
     * @return the transaction result
     */
    TransactionResult onExecute(BindXenoDaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   *
   * @param <T> the generic type
   */
  public interface Batch<T> {
    
    /**
     * Execute batch operations.
     *
     * @param daoFactory the dao factory
     * @return the t
     */
    T onExecute(BindXenoDaoFactory daoFactory);
  }

  /**
   * The Class DataSourceSingleThread.
   */
  class DataSourceSingleThread implements BindXenoDaoFactory {
    
    /** The context. */
    private SQLContextInSessionImpl _context;

    /** The phone dao. */
    protected PhoneDaoImpl _phoneDao;

    /** The prefix config dao. */
    protected PrefixConfigDaoImpl _prefixConfigDao;

    /** The country dao. */
    protected CountryDaoImpl _countryDao;

    /** The person 2 phone dao. */
    protected Person2PhoneDaoImpl _person2PhoneDao;

    /** The person dao. */
    protected PersonDaoImpl _personDao;

    /**
     * Instantiates a new data source single thread.
     */
    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindXenoDataSource.this);
    }

    /**
     * retrieve dao PhoneDao.
     *
     * @return the phone dao
     */
    public PhoneDaoImpl getPhoneDao() {
      if (_phoneDao==null) {
        _phoneDao=new PhoneDaoImpl(_context);
      }
      return _phoneDao;
    }

    /**
     * retrieve dao PrefixConfigDao.
     *
     * @return the prefix config dao
     */
    public PrefixConfigDaoImpl getPrefixConfigDao() {
      if (_prefixConfigDao==null) {
        _prefixConfigDao=new PrefixConfigDaoImpl(_context);
      }
      return _prefixConfigDao;
    }

    /**
     * retrieve dao CountryDao.
     *
     * @return the country dao
     */
    public CountryDaoImpl getCountryDao() {
      if (_countryDao==null) {
        _countryDao=new CountryDaoImpl(_context);
      }
      return _countryDao;
    }

    /**
     * retrieve dao Person2PhoneDao.
     *
     * @return the person 2 phone dao
     */
    public Person2PhoneDaoImpl getPerson2PhoneDao() {
      if (_person2PhoneDao==null) {
        _person2PhoneDao=new Person2PhoneDaoImpl(_context);
      }
      return _person2PhoneDao;
    }

    /**
     * retrieve dao PersonDao.
     *
     * @return the person dao
     */
    public PersonDaoImpl getPersonDao() {
      if (_personDao==null) {
        _personDao=new PersonDaoImpl(_context);
      }
      return _personDao;
    }

    /**
     * On session opened.
     */
    protected void onSessionOpened() {
    }

    /**
     * On session clear.
     */
    protected void onSessionClear() {
    }

    /**
     * On session closed.
     */
    protected void onSessionClosed() {
    }

    /**
     * Bind to thread.
     *
     * @return the data source single thread
     */
    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}

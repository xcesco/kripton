package sqlite.feature.many2many.err3;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.orm.AbstractDataSource;
import com.abubusoft.kripton.android.orm.DataSourceOptions;
import com.abubusoft.kripton.android.orm.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.orm.SQLiteTable;
import com.abubusoft.kripton.android.orm.SQLiteUpdateTask;
import com.abubusoft.kripton.android.orm.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.orm.TransactionResult;

import java.util.List;
import sqlite.feature.many2many.CityTable;
import sqlite.feature.many2many.PersonTable;

/**
 * <p>
 * Represents implementation of datasource PersonCirtyErr3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyErr3DataSource
 * @see BindPersonCirtyErr3DaoFactory
 * @see PersonErr3Dao
 * @see PersonErr3DaoImpl
 * @see Person
 * @see CityErr3Dao
 * @see CityErr3DaoImpl
 * @see City
 * @see PersonCityErr1Dao
 * @see PersonCityErr1DaoImpl
 * @see PersonCityErr3
 */
public class BindPersonCirtyErr3DataSource extends AbstractDataSource implements BindPersonCirtyErr3DaoFactory, PersonCirtyErr3DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindPersonCirtyErr3DataSource instance;

  /**
   * Unique identifier for Dao PersonErr3Dao
   */
  public static final int PERSON_ERR3_DAO_UID = 0;

  /**
   * Unique identifier for Dao CityErr3Dao
   */
  public static final int CITY_ERR3_DAO_UID = 1;

  /**
   * Unique identifier for Dao PersonCityErr1Dao
   */
  public static final int PERSON_CITY_ERR1_DAO_UID = 2;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new PersonCityErr3Table(), new CityTable(), new PersonTable()};

  /**
   * <p>dao instance</p>
   */
  protected PersonErr3DaoImpl personErr3Dao = new PersonErr3DaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected CityErr3DaoImpl cityErr3Dao = new CityErr3DaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected PersonCityErr1DaoImpl personCityErr1Dao = new PersonCityErr1DaoImpl(context);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindPersonCirtyErr3DataSource(DataSourceOptions options) {
    super("person.db", 1, options);
  }

  @Override
  public PersonErr3DaoImpl getPersonErr3Dao() {
    return personErr3Dao;
  }

  @Override
  public CityErr3DaoImpl getCityErr3Dao() {
    return cityErr3Dao;
  }

  @Override
  public PersonCityErr1DaoImpl getPersonCityErr1Dao() {
    return personCityErr1Dao;
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
   * @param commands
   * 	batch to execute
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
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
   * instance
   */
  public static synchronized BindPersonCirtyErr3DataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindPersonCirtyErr3DataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyErr3DataSource open() {
    BindPersonCirtyErr3DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyErr3DataSource openReadOnly() {
    BindPersonCirtyErr3DataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
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
      Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(CityTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PersonCityErr3Table.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PersonCityErr3Table.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade
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
        task.execute(database);
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
        Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(CityTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PersonCityErr3Table.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PersonCityErr3Table.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  public void clearCompiledStatements() {
    PersonErr3DaoImpl.clearCompiledStatements();
    CityErr3DaoImpl.clearCompiledStatements();
    PersonCityErr1DaoImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindPersonCirtyErr3DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindPersonCirtyErr3DataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindPersonCirtyErr3DataSource build() {
    return build(DataSourceOptions.builder().build());
  }

  /**
   * List of tables compose datasource:
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindPersonCirtyErr3DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindPersonCirtyErr3DaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   */
  public interface Batch<T> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindPersonCirtyErr3DaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindPersonCirtyErr3DaoFactory {
    private SQLContextInSessionImpl _context;

    protected PersonErr3DaoImpl _personErr3Dao;

    protected CityErr3DaoImpl _cityErr3Dao;

    protected PersonCityErr1DaoImpl _personCityErr1Dao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindPersonCirtyErr3DataSource.this);
    }

    /**
     *
     * retrieve dao PersonErr3Dao
     */
    public PersonErr3DaoImpl getPersonErr3Dao() {
      if (_personErr3Dao==null) {
        _personErr3Dao=new PersonErr3DaoImpl(_context);
      }
      return _personErr3Dao;
    }

    /**
     *
     * retrieve dao CityErr3Dao
     */
    public CityErr3DaoImpl getCityErr3Dao() {
      if (_cityErr3Dao==null) {
        _cityErr3Dao=new CityErr3DaoImpl(_context);
      }
      return _cityErr3Dao;
    }

    /**
     *
     * retrieve dao PersonCityErr1Dao
     */
    public PersonCityErr1DaoImpl getPersonCityErr1Dao() {
      if (_personCityErr1Dao==null) {
        _personCityErr1Dao=new PersonCityErr1DaoImpl(_context);
      }
      return _personCityErr1Dao;
    }

    protected void onSessionOpened() {
    }

    protected void onSessionClear() {
    }

    protected void onSessionClosed() {
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}

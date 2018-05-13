package sqlite.feature.contentprovider.kripton35.nolog;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.contentprovider.kripton35.entities.CityTable;
import sqlite.feature.contentprovider.kripton35.entities.PersonTable;

/**
 * <p>
 * Implementation of the Person2DataSource datasource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person2DataSource
 * @see BindPerson2DaoFactory
 * @see Person2DAO
 * @see Person2DAOImpl
 * @see Person
 * @see City2DAO
 * @see City2DAOImpl
 * @see City
 */
public class BindPerson2DataSource extends AbstractDataSource implements BindPerson2DaoFactory, Person2DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static volatile BindPerson2DataSource instance;

  /**
   * <p>Mutex to manage multithread access to instance</p>
   */
  private static final Object mutex = new Object();

  /**
   * Unique identifier for Dao Person2DAO
   */
  public static final int PERSON2_D_A_O_UID = 0;

  /**
   * Unique identifier for Dao City2DAO
   */
  public static final int CITY2_D_A_O_UID = 1;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new CityTable(), new PersonTable()};

  /**
   * <p>dao instance</p>
   */
  protected Person2DAOImpl person2DAO = new Person2DAOImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected City2DAOImpl city2DAO = new City2DAOImpl(this);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindPerson2DataSource(DataSourceOptions options) {
    super("person", 1, options);
  }

  @Override
  public Person2DAOImpl getPerson2DAO() {
    return person2DAO;
  }

  @Override
  public City2DAOImpl getCity2DAO() {
    return city2DAO;
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
   * <p>Retrieve instance.</p>
   */
  public static BindPerson2DataSource getInstance() {
    BindPerson2DataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindPerson2DataSource(options);
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
  public static BindPerson2DataSource open() {
    BindPerson2DataSource instance=getInstance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPerson2DataSource openReadOnly() {
    BindPerson2DataSource instance=getInstance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    database.execSQL(CityTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
      database.execSQL(CityTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
    Person2DAOImpl.clearCompiledStatements();
    City2DAOImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   */
  public static BindPerson2DataSource build(DataSourceOptions options) {
    BindPerson2DataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindPerson2DataSource(options);
          try {
            instance.openWritableDatabase();
            instance.close();
            // force database DDL run
            if (options.populator!=null && instance.justCreated) {
              // run populator only a time
              instance.justCreated=false;
              try {
                SQLiteDatabase currentDb=instance.openWritableDatabase();
                // run populator
                options.populator.execute(currentDb);
              } finally {
                instance.close();
              }
            }
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          }
        } else {
          throw new KriptonRuntimeException("Datasource BindPerson2DataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindPerson2DataSource is already builded");
    }
    return result;
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
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindPerson2DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindPerson2DaoFactory daoFactory);
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
    T onExecute(BindPerson2DaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindPerson2DaoFactory {
    private SQLContextInSessionImpl _context;

    protected Person2DAOImpl _person2DAO;

    protected City2DAOImpl _city2DAO;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindPerson2DataSource.this);
    }

    /**
     *
     * retrieve dao Person2DAO
     */
    public Person2DAOImpl getPerson2DAO() {
      if (_person2DAO==null) {
        _person2DAO=new Person2DAOImpl(this);
      }
      return _person2DAO;
    }

    /**
     *
     * retrieve dao City2DAO
     */
    public City2DAOImpl getCity2DAO() {
      if (_city2DAO==null) {
        _city2DAO=new City2DAOImpl(this);
      }
      return _city2DAO;
    }

    @Override
    public SQLContext context() {
      return _context;
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

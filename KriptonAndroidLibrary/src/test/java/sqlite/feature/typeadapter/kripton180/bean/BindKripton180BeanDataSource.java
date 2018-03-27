package sqlite.feature.typeadapter.kripton180.bean;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import java.util.List;
import java.util.Set;
import sqlite.feature.typeadapter.kripton180.EmployeeTable;

/**
 * <p>
 * Represents implementation of datasource Kripton180BeanDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180BeanDataSource
 * @see BindKripton180BeanDaoFactory
 * @see EmployeeBeanDao
 * @see EmployeeBeanDaoImpl
 * @see Employee
 */
public class BindKripton180BeanDataSource extends AbstractDataSource implements BindKripton180BeanDaoFactory, Kripton180BeanDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindKripton180BeanDataSource instance;

  /**
   * Unique identifier for Dao EmployeeBeanDao
   */
  public static final int EMPLOYEE_BEAN_DAO_UID = 0;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new EmployeeTable()};

  /**
   * <p>dao instance</p>
   */
  protected EmployeeBeanDaoImpl employeeBeanDao = new EmployeeBeanDaoImpl(context);

  /**
   * Used only in transactions (that can be executed one for time */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindKripton180BeanDataSource(DataSourceOptions options) {
    super("kripton180.db", 1, options);
  }

  @Override
  public EmployeeBeanDaoImpl getEmployeeBeanDao() {
    return employeeBeanDao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    execute(transaction, onErrorListener);
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   * @param onErrorListener
   * 	error listener
   */
  public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
    boolean needToOpened=!this.isOpenInWriteMode();
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(_daoFactorySingleThread.bindToThread())) {
        connection.setTransactionSuccessful();
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
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
   */
  public <T> T executeBatch(Batch<T> commands, boolean writeMode) {
    boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen();
    if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
    try {
      if (commands!=null) {
        return commands.onExecute(new DataSourceSingleThread());
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      if (needToOpened) { close(); }
    }
    return null;
  }

  /**
   * instance
   */
  public static synchronized BindKripton180BeanDataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindKripton180BeanDataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindKripton180BeanDataSource open() {
    BindKripton180BeanDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindKripton180BeanDataSource openReadOnly() {
    BindKripton180BeanDataSource instance=instance();
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
      Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
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
        Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
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
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  public void clearCompiledStatements() {
    EmployeeBeanDaoImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindKripton180BeanDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindKripton180BeanDataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindKripton180BeanDataSource build() {
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
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindKripton180BeanDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindKripton180BeanDaoFactory daoFactory);
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
    T onExecute(BindKripton180BeanDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindKripton180BeanDaoFactory {
    private SQLContextInSessionImpl _context;

    protected EmployeeBeanDaoImpl _employeeBeanDao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindKripton180BeanDataSource.this);
    }

    /**
     *
     * retrieve dao EmployeeBeanDao
     */
    public EmployeeBeanDaoImpl getEmployeeBeanDao() {
      if (_employeeBeanDao==null) {
        _employeeBeanDao=new EmployeeBeanDaoImpl(_context);
      }
      return _employeeBeanDao;
    }

    protected void onSessionOpened() {
      _context.onSessionOpened();
    }

    protected Set<Integer> onSessionClosed() {
      return _context.onSessionClosed();
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}

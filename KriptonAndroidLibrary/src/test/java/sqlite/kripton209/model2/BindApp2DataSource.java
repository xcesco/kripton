package sqlite.kripton209.model2;

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

/**
 * <p>
 * Represents implementation of datasource App2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see App2DataSource
 * @see BindApp2DaoFactory
 * @see DeviceDao
 * @see DeviceDaoImpl
 * @see Device
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 * @see UserDeviceDao
 * @see UserDeviceDaoImpl
 * @see UserDevice
 */
public class BindApp2DataSource extends AbstractDataSource implements BindApp2DaoFactory, App2DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindApp2DataSource instance;

  /**
   * Unique identifier for Dao DeviceDao
   */
  public static final int DEVICE_DAO_UID = 0;

  /**
   * Unique identifier for Dao UserDao
   */
  public static final int USER_DAO_UID = 1;

  /**
   * Unique identifier for Dao UserDeviceDao
   */
  public static final int USER_DEVICE_DAO_UID = 2;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new UserTable(), new DeviceTable(), new UserDeviceTable()};

  /**
   * <p>dao instance</p>
   */
  protected DeviceDaoImpl deviceDao = new DeviceDaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected UserDaoImpl userDao = new UserDaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected UserDeviceDaoImpl userDeviceDao = new UserDeviceDaoImpl(context);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindApp2DataSource(DataSourceOptions options) {
    super("app2.db", 1, options);
  }

  @Override
  public DeviceDaoImpl getDeviceDao() {
    return deviceDao;
  }

  @Override
  public UserDaoImpl getUserDao() {
    return userDao;
  }

  @Override
  public UserDeviceDaoImpl getUserDeviceDao() {
    return userDeviceDao;
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
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
    currentDaoFactory.onSessionOpened();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(currentDaoFactory)) {
        connection.setTransactionSuccessful();
        currentDaoFactory.onSessionClosed();
      }
    } catch(Throwable e) {
      currentDaoFactory.onSessionClear();
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
  public static synchronized BindApp2DataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindApp2DataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindApp2DataSource open() {
    BindApp2DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindApp2DataSource openReadOnly() {
    BindApp2DataSource instance=instance();
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
      Logger.info("DDL: %s",UserTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(UserTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",DeviceTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(DeviceTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",UserDeviceTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(UserDeviceTable.CREATE_TABLE_SQL);
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
        Logger.info("DDL: %s",UserTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(UserTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",DeviceTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(DeviceTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",UserDeviceTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(UserDeviceTable.CREATE_TABLE_SQL);
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
    DeviceDaoImpl.clearCompiledStatements();
    UserDaoImpl.clearCompiledStatements();
    UserDeviceDaoImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindApp2DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindApp2DataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindApp2DataSource build() {
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
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindApp2DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindApp2DaoFactory daoFactory);
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
    T onExecute(BindApp2DaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindApp2DaoFactory {
    private SQLContextInSessionImpl _context;

    protected DeviceDaoImpl _deviceDao;

    protected UserDaoImpl _userDao;

    protected UserDeviceDaoImpl _userDeviceDao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindApp2DataSource.this);
    }

    /**
     *
     * retrieve dao DeviceDao
     */
    public DeviceDaoImpl getDeviceDao() {
      if (_deviceDao==null) {
        _deviceDao=new DeviceDaoImpl(_context);
      }
      return _deviceDao;
    }

    /**
     *
     * retrieve dao UserDao
     */
    public UserDaoImpl getUserDao() {
      if (_userDao==null) {
        _userDao=new UserDaoImpl(_context);
      }
      return _userDao;
    }

    /**
     *
     * retrieve dao UserDeviceDao
     */
    public UserDeviceDaoImpl getUserDeviceDao() {
      if (_userDeviceDao==null) {
        _userDeviceDao=new UserDeviceDaoImpl(_context);
      }
      return _userDeviceDao;
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

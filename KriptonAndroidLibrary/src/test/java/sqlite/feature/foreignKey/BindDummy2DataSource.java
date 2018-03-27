package sqlite.feature.foreignKey;

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
 * Represents implementation of datasource Dummy2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy2DataSource
 * @see BindDummy2DaoFactory
 * @see DaoBeanA_3
 * @see DaoBeanA_3Impl
 * @see BeanA_3
 * @see DaoBeanA_4
 * @see DaoBeanA_4Impl
 * @see BeanA_4
 */
public class BindDummy2DataSource extends AbstractDataSource implements BindDummy2DaoFactory, Dummy2DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindDummy2DataSource instance;

  /**
   * Unique identifier for Dao DaoBeanA_3
   */
  public static final int DAO_BEAN_A_3_UID = 0;

  /**
   * Unique identifier for Dao DaoBeanA_4
   */
  public static final int DAO_BEAN_A_4_UID = 1;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new BeanA_3Table(), new BeanA_4Table()};

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_3Impl daoBeanA_3 = new DaoBeanA_3Impl(context);

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_4Impl daoBeanA_4 = new DaoBeanA_4Impl(context);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindDummy2DataSource(DataSourceOptions options) {
    super("test.db", 1, options);
  }

  @Override
  public DaoBeanA_3Impl getDaoBeanA_3() {
    return daoBeanA_3;
  }

  @Override
  public DaoBeanA_4Impl getDaoBeanA_4() {
    return daoBeanA_4;
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
  public static synchronized BindDummy2DataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindDummy2DataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindDummy2DataSource open() {
    BindDummy2DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindDummy2DataSource openReadOnly() {
    BindDummy2DataSource instance=instance();
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
      Logger.info("DDL: %s",BeanA_3Table.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(BeanA_3Table.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",BeanA_4Table.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(BeanA_4Table.CREATE_TABLE_SQL);
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
        Logger.info("DDL: %s",BeanA_3Table.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(BeanA_3Table.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",BeanA_4Table.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(BeanA_4Table.CREATE_TABLE_SQL);
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
    DaoBeanA_3Impl.clearCompiledStatements();
    DaoBeanA_4Impl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindDummy2DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindDummy2DataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindDummy2DataSource build() {
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
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindDummy2DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindDummy2DaoFactory daoFactory);
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
    T onExecute(BindDummy2DaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindDummy2DaoFactory {
    private SQLContextInSessionImpl _context;

    protected DaoBeanA_3Impl _daoBeanA_3;

    protected DaoBeanA_4Impl _daoBeanA_4;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindDummy2DataSource.this);
    }

    /**
     *
     * retrieve dao DaoBeanA_3
     */
    public DaoBeanA_3Impl getDaoBeanA_3() {
      if (_daoBeanA_3==null) {
        _daoBeanA_3=new DaoBeanA_3Impl(_context);
      }
      return _daoBeanA_3;
    }

    /**
     *
     * retrieve dao DaoBeanA_4
     */
    public DaoBeanA_4Impl getDaoBeanA_4() {
      if (_daoBeanA_4==null) {
        _daoBeanA_4=new DaoBeanA_4Impl(_context);
      }
      return _daoBeanA_4;
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

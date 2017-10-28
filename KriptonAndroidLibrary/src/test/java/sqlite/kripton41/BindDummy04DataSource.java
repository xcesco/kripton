package sqlite.kripton41;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;

/**
 * <p>
 * Represents implementation of datasource Dummy04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy04DataSource
 * @see BindDummy04DaoFactory
 * @see DaoBeanInsertOK
 * @see DaoBeanInsertOKImpl
 * @see Bean01
 */
public class BindDummy04DataSource extends AbstractDataSource implements BindDummy04DaoFactory, Dummy04DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindDummy04DataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanInsertOKImpl daoBeanInsertOK = new DaoBeanInsertOKImpl(this);

  protected BindDummy04DataSource(DataSourceOptions options) {
    super("dummy1", 1, options);
  }

  @Override
  public DaoBeanInsertOKImpl getDaoBeanInsertOK() {
    return daoBeanInsertOK;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase connection=openWritableDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (transaction!=null) transaction.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      close();
    }
  }

  /**
   * <p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>
   *
   * @param commands
   * 	batch to execute
   */
  public <T> T execute(Batch<T> commands) {
    return execute(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
   */
  public <T> T execute(Batch<T> commands, boolean writeMode) {
    if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }
    try {
      if (commands!=null) {
        return commands.onExecute(this);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (commands!=null) commands.onError(e);
    } finally {
      close();
    }
    return null;
  }

  /**
   * instance
   */
  public static synchronized BindDummy04DataSource instance() {
    if (instance==null) {
      instance=new BindDummy04DataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindDummy04DataSource open() {
    BindDummy04DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindDummy04DataSource openReadOnly() {
    BindDummy04DataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
      database.execSQL(Bean01Table.CREATE_TABLE_SQL);
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

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindDummy04DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindDummy04DataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindDummy04DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindDummy04DaoFactory daoFactory);
  }

  /**
   * Simple class implements interface to define transactions.In this class a simple <code>onError</code> method is implemented.
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }

  /**
   * Rapresents batch operation.
   */
  public interface Batch<T> extends AbstractDataSource.AbstractExecutable<BindDummy04DaoFactory> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindDummy04DaoFactory daoFactory);
  }

  /**
   * Simple class implements interface to define batch.In this class a simple <code>onError</code> method is implemented.
   */
  public abstract static class SimpleBatch<T> implements Batch<T> {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}

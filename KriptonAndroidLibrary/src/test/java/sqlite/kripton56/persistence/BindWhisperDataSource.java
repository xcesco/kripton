package sqlite.kripton56.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.kripton56.entities.MessageEntityTable;

/**
 * <p>
 * Represents implementation of datasource WhisperDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see WhisperDataSource
 * @see BindWhisperDaoFactory
 * @see DaoMessage
 * @see DaoMessageImpl
 * @see MessageEntity
 */
public class BindWhisperDataSource extends AbstractDataSource implements BindWhisperDaoFactory, WhisperDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindWhisperDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected DaoMessageImpl daoMessage = new DaoMessageImpl(this);

  protected BindWhisperDataSource(DataSourceOptions options) {
    super("whisper", 1, options);
  }

  @Override
  public DaoMessageImpl getDaoMessage() {
    return daoMessage;
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
  public static synchronized BindWhisperDataSource instance() {
    if (instance==null) {
      instance=new BindWhisperDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindWhisperDataSource open() {
    BindWhisperDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindWhisperDataSource openReadOnly() {
    BindWhisperDataSource instance=instance();
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
    Logger.info("DDL: %s",MessageEntityTable.CREATE_TABLE_SQL);
    database.execSQL(MessageEntityTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",MessageEntityTable.CREATE_TABLE_SQL);
      database.execSQL(MessageEntityTable.CREATE_TABLE_SQL);
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
   */
  public static synchronized void build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindWhisperDataSource(options);
    }
    instance.openWritableDatabase();
    instance.close();
  }

  /**
   * Build instance with default config.
   */
  public static synchronized void build() {
    build(DataSourceOptions.builder().build());
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindWhisperDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindWhisperDaoFactory daoFactory);
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
  public interface Batch<T> extends AbstractDataSource.AbstractExecutable<BindWhisperDaoFactory> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindWhisperDaoFactory daoFactory);
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

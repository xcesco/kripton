package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInTransactionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.subjects.PublishSubject;
import java.util.List;
import sqlite.feature.typeadapter.kripton180.EmployeeTable;

/**
 * <p>
 * Represents implementation of datasource Kripton180BeanInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180BeanInsertSelectDataSource
 * @see BindKripton180BeanInsertSelectDaoFactory
 * @see EmployeeBeanInsertSelectDao
 * @see EmployeeBeanInsertSelectDaoImpl
 * @see Employee
 */
public class BindKripton180BeanInsertSelectDataSource extends AbstractDataSource implements BindKripton180BeanInsertSelectDaoFactory, Kripton180BeanInsertSelectDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindKripton180BeanInsertSelectDataSource instance;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new EmployeeTable()};

  /**
   * <p>dao instance</p>
   */
  protected EmployeeBeanInsertSelectDaoImpl employeeBeanInsertSelectDao = new EmployeeBeanInsertSelectDaoImpl(this);

  protected Scheduler globalSubscribeOn;

  protected Scheduler globalObserveOn;

  /**
   * Used only in transactions (that can be executed one for time */
  private final DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindKripton180BeanInsertSelectDataSource(DataSourceOptions options) {
    super("kripton180.db", 1, options);
  }

  @Override
  public EmployeeBeanInsertSelectDaoImpl getEmployeeBeanInsertSelectDao() {
    return employeeBeanInsertSelectDao;
  }

  public BindKripton180BeanInsertSelectDataSource globalSubscribeOn(Scheduler scheduler) {
    this.globalSubscribeOn=scheduler;
    return this;
  }

  public BindKripton180BeanInsertSelectDataSource globalObserveOn(Scheduler scheduler) {
    this.globalObserveOn=scheduler;
    return this;
  }

  public <T> Observable<T> execute(final BindKripton180BeanInsertSelectDataSource.ObservableTransaction<T> transaction) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode();
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(_daoFactorySingleThread.bindToThread(), emitter)) {
            connection.setTransactionSuccessful();
          }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> execute(final BindKripton180BeanInsertSelectDataSource.SingleTransaction<T> transaction) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode();
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(_daoFactorySingleThread.bindToThread(), emitter)) {
            connection.setTransactionSuccessful();
          }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> execute(final BindKripton180BeanInsertSelectDataSource.FlowableTransaction<T> transaction) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode();
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(_daoFactorySingleThread.bindToThread(), emitter)) {
            connection.setTransactionSuccessful();
          }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> execute(final BindKripton180BeanInsertSelectDataSource.MaybeTransaction<T> transaction) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode();
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(_daoFactorySingleThread.bindToThread(), emitter)) {
            connection.setTransactionSuccessful();
          }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.ObservableBatch<T> batch,
      final boolean writeMode) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180BeanInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        try {
          if (batch != null) { batch.onExecute(new DataSourceSingleThread(), emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.ObservableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Single<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.SingleBatch<T> batch,
      final boolean writeMode) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180BeanInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        try {
          if (batch != null) { batch.onExecute(new DataSourceSingleThread(), emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.SingleBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Flowable<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.FlowableBatch<T> batch,
      final boolean writeMode) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180BeanInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        try {
          if (batch != null) { batch.onExecute(new DataSourceSingleThread(), emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.FlowableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Maybe<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.MaybeBatch<T> batch,
      final boolean writeMode) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180BeanInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180BeanInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        try {
          if (batch != null) { batch.onExecute(new DataSourceSingleThread(), emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> executeBatch(final BindKripton180BeanInsertSelectDataSource.MaybeBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public PublishSubject<SQLiteModification> employeeSubject() {
    return employeeBeanInsertSelectDao.subject();
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
  public static synchronized BindKripton180BeanInsertSelectDataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindKripton180BeanInsertSelectDataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindKripton180BeanInsertSelectDataSource open() {
    BindKripton180BeanInsertSelectDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindKripton180BeanInsertSelectDataSource openReadOnly() {
    BindKripton180BeanInsertSelectDataSource instance=instance();
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
    EmployeeBeanInsertSelectDaoImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindKripton180BeanInsertSelectDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindKripton180BeanInsertSelectDataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindKripton180BeanInsertSelectDataSource build() {
    return build(DataSourceOptions.builder().build());
  }

  /**
   * List of tables compose datasource:
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  public interface ObservableBatch<T> {
    void onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory,
        ObservableEmitter<T> emitter);
  }

  public interface ObservableTransaction<T> {
    TransactionResult onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory,
        ObservableEmitter<T> emitter);
  }

  public interface SingleBatch<T> {
    void onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory, SingleEmitter<T> emitter);
  }

  public interface SingleTransaction<T> {
    TransactionResult onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory,
        SingleEmitter<T> emitter);
  }

  public interface FlowableBatch<T> {
    void onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory, FlowableEmitter<T> emitter);
  }

  public interface FlowableTransaction<T> {
    TransactionResult onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory,
        FlowableEmitter<T> emitter);
  }

  public interface MaybeBatch<T> {
    void onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory, MaybeEmitter<T> emitter);
  }

  public interface MaybeTransaction<T> {
    TransactionResult onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory,
        MaybeEmitter<T> emitter);
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindKripton180BeanInsertSelectDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory);
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
    T onExecute(BindKripton180BeanInsertSelectDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindKripton180BeanInsertSelectDaoFactory {
    private SQLContextInTransactionImpl _context;

    private EmployeeBeanInsertSelectDaoImpl _employeeBeanInsertSelectDao;

    DataSourceSingleThread() {
      _context=new SQLContextInTransactionImpl(BindKripton180BeanInsertSelectDataSource.this);
    }

    /**
     *
     * retrieve dao EmployeeBeanInsertSelectDao
     */
    public EmployeeBeanInsertSelectDaoImpl getEmployeeBeanInsertSelectDao() {
      if (_employeeBeanInsertSelectDao==null) {
        _employeeBeanInsertSelectDao=new EmployeeBeanInsertSelectDaoImpl(_context);
      }
      return _employeeBeanInsertSelectDao;
    }

    public DataSourceSingleThread bindToThread() {
      _context.bindToThread();
      return this;
    }
  }
}

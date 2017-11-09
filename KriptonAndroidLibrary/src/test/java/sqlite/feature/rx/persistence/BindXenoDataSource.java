package sqlite.feature.rx.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
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
import sqlite.feature.rx.model.CountryTable;
import sqlite.feature.rx.model.PersonTable;
import sqlite.feature.rx.model.PhoneNumberTable;
import sqlite.feature.rx.model.PrefixConfigTable;

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
  /**
   * <p>datasource singleton</p>
   */
  static BindXenoDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected PhoneDaoImpl phoneDao = new PhoneDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected PrefixConfigDaoImpl prefixConfigDao = new PrefixConfigDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected CountryDaoImpl countryDao = new CountryDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected Person2PhoneDaoImpl person2PhoneDao = new Person2PhoneDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected PersonDaoImpl personDao = new PersonDaoImpl(this);

  protected Scheduler globalSubscribeOn;

  protected Scheduler globalObserveOn;

  protected BindXenoDataSource(DataSourceOptions options) {
    super("xeno.db", 1, options);
  }

  @Override
  public PhoneDaoImpl getPhoneDao() {
    return phoneDao;
  }

  @Override
  public PrefixConfigDaoImpl getPrefixConfigDao() {
    return prefixConfigDao;
  }

  @Override
  public CountryDaoImpl getCountryDao() {
    return countryDao;
  }

  @Override
  public Person2PhoneDaoImpl getPerson2PhoneDao() {
    return person2PhoneDao;
  }

  @Override
  public PersonDaoImpl getPersonDao() {
    return personDao;
  }

  public BindXenoDataSource globalSubscribeOn(Scheduler scheduler) {
    this.globalSubscribeOn=scheduler;
    return this;
  }

  public BindXenoDataSource globalObserveOn(Scheduler scheduler) {
    this.globalObserveOn=scheduler;
    return this;
  }

  public <T> Observable<T> execute(final BindXenoDataSource.ObservableTransaction<T> transaction) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        SQLiteDatabase connection=openWritableDatabase();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(BindXenoDataSource.this, emitter)) {
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
          close();
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> execute(final BindXenoDataSource.SingleTransaction<T> transaction) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        SQLiteDatabase connection=openWritableDatabase();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(BindXenoDataSource.this, emitter)) {
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
          close();
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> execute(final BindXenoDataSource.FlowableTransaction<T> transaction) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        SQLiteDatabase connection=openWritableDatabase();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(BindXenoDataSource.this, emitter)) {
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
          close();
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> execute(final BindXenoDataSource.MaybeTransaction<T> transaction) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        SQLiteDatabase connection=openWritableDatabase();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(BindXenoDataSource.this, emitter)) {
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
          close();
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final BindXenoDataSource.ObservableBatch<T> batch,
      final boolean writeMode) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        if (writeMode) open(); else openReadOnly();
        try {
          if (batch != null) { batch.onExecute(BindXenoDataSource.this, emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          close();
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final BindXenoDataSource.ObservableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Single<T> executeBatch(final BindXenoDataSource.SingleBatch<T> batch,
      final boolean writeMode) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        if (writeMode) open(); else openReadOnly();
        try {
          if (batch != null) { batch.onExecute(BindXenoDataSource.this, emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          close();
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> executeBatch(final BindXenoDataSource.SingleBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Flowable<T> executeBatch(final BindXenoDataSource.FlowableBatch<T> batch,
      final boolean writeMode) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        if (writeMode) open(); else openReadOnly();
        try {
          if (batch != null) { batch.onExecute(BindXenoDataSource.this, emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          close();
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> executeBatch(final BindXenoDataSource.FlowableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Maybe<T> executeBatch(final BindXenoDataSource.MaybeBatch<T> batch,
      final boolean writeMode) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        if (writeMode) open(); else openReadOnly();
        try {
          if (batch != null) { batch.onExecute(BindXenoDataSource.this, emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          close();
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> executeBatch(final BindXenoDataSource.MaybeBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public PublishSubject<SQLiteModification> phoneNumberSubject() {
    return phoneDao.subject();
  }

  public PublishSubject<SQLiteModification> prefixConfigSubject() {
    return prefixConfigDao.subject();
  }

  public PublishSubject<SQLiteModification> countrySubject() {
    return countryDao.subject();
  }

  public PublishSubject<SQLiteModification> personPhoneNumberSubject() {
    return person2PhoneDao.subject();
  }

  public PublishSubject<SQLiteModification> personSubject() {
    return personDao.subject();
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
    if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }
    try {
      if (commands!=null) {
        return commands.onExecute(this);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      close();
    }
    return null;
  }

  /**
   * instance
   */
  public static synchronized BindXenoDataSource instance() {
    if (instance==null) {
      instance=new BindXenoDataSource(null);
    }
    return instance;
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
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
    database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
    database.execSQL(CountryTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
    database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
    database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
      database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
      database.execSQL(CountryTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
      database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
      database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
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
    phoneDao.clearCompiledStatements();
    prefixConfigDao.clearCompiledStatements();
    countryDao.clearCompiledStatements();
    person2PhoneDao.clearCompiledStatements();
    personDao.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindXenoDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindXenoDataSource(options);
    }
    instance.openWritableDatabase();
    instance.close();
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindXenoDataSource build() {
    return build(DataSourceOptions.builder().build());
  }

  public interface ObservableBatch<T> {
    void onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<T> emitter);
  }

  public interface ObservableTransaction<T> {
    TransactionResult onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<T> emitter);
  }

  public interface SingleBatch<T> {
    void onExecute(BindXenoDaoFactory daoFactory, SingleEmitter<T> emitter);
  }

  public interface SingleTransaction<T> {
    TransactionResult onExecute(BindXenoDaoFactory daoFactory, SingleEmitter<T> emitter);
  }

  public interface FlowableBatch<T> {
    void onExecute(BindXenoDaoFactory daoFactory, FlowableEmitter<T> emitter);
  }

  public interface FlowableTransaction<T> {
    TransactionResult onExecute(BindXenoDaoFactory daoFactory, FlowableEmitter<T> emitter);
  }

  public interface MaybeBatch<T> {
    void onExecute(BindXenoDaoFactory daoFactory, MaybeEmitter<T> emitter);
  }

  public interface MaybeTransaction<T> {
    TransactionResult onExecute(BindXenoDaoFactory daoFactory, MaybeEmitter<T> emitter);
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
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindXenoDaoFactory daoFactory);
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
  public interface Batch<T> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindXenoDaoFactory daoFactory);
  }
}

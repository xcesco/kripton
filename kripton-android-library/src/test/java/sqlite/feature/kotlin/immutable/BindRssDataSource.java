package sqlite.feature.kotlin.immutable;

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
import java.util.Set;

/**
 * <p>
 * Implementation of the RssDataSource datasource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see RssDataSource
 * @see BindRssDaoFactory
 * @see DaoRss
 * @see DaoRssImpl
 * @see RssFeed
 * @see DaoArticle
 * @see DaoArticleImpl
 * @see Article
 * @see DaoChannel
 * @see DaoChannelImpl
 * @see Channel
 */
public class BindRssDataSource extends AbstractDataSource implements BindRssDaoFactory, RssDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static volatile BindRssDataSource instance;

  /**
   * <p>Mutex to manage multithread access to instance</p>
   */
  private static final Object mutex = new Object();

  /**
   * Unique identifier for Dao DaoRss
   */
  public static final int DAO_RSS_UID = 0;

  /**
   * Unique identifier for Dao DaoArticle
   */
  public static final int DAO_ARTICLE_UID = 1;

  /**
   * Unique identifier for Dao DaoChannel
   */
  public static final int DAO_CHANNEL_UID = 2;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new ArticleTable(), new RssFeedTable(), new ChannelTable()};

  /**
   * <p>dao instance</p>
   */
  protected DaoRssImpl daoRss = new DaoRssImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoArticleImpl daoArticle = new DaoArticleImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoChannelImpl daoChannel = new DaoChannelImpl(this);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindRssDataSource(DataSourceOptions options) {
    super("rss.db", 1, options);
  }

  @Override
  public DaoRssImpl getDaoRss() {
    return daoRss;
  }

  @Override
  public DaoArticleImpl getDaoArticle() {
    return daoArticle;
  }

  @Override
  public DaoChannelImpl getDaoChannel() {
    return daoChannel;
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
  public static BindRssDataSource getInstance() {
    BindRssDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindRssDataSource(options);
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
  public static BindRssDataSource open() {
    BindRssDataSource instance=getInstance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindRssDataSource openReadOnly() {
    BindRssDataSource instance=getInstance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    // log section create BEGIN
    if (this.logEnabled) {
      if (options.inMemory) {
        Logger.info("Create database in memory");
      } else {
        Logger.info("Create database '%s' version %s",this.name, this.version);
      }
    }
    // log section create END
    // log section create BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",RssFeedTable.CREATE_TABLE_SQL);
    }
    // log section create END
    database.execSQL(RssFeedTable.CREATE_TABLE_SQL);
    // log section create BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",ChannelTable.CREATE_TABLE_SQL);
    }
    // log section create END
    database.execSQL(ChannelTable.CREATE_TABLE_SQL);
    // log section create BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",ArticleTable.CREATE_TABLE_SQL);
    }
    // log section create END
    database.execSQL(ArticleTable.CREATE_TABLE_SQL);
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
        Logger.info("DDL: %s",RssFeedTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(RssFeedTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",ChannelTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(ChannelTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",ArticleTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(ArticleTable.CREATE_TABLE_SQL);
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
    DaoRssImpl.clearCompiledStatements();
    DaoArticleImpl.clearCompiledStatements();
    DaoChannelImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   */
  public static BindRssDataSource build(DataSourceOptions options) {
    BindRssDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindRssDataSource(options);
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
          throw new KriptonRuntimeException("Datasource BindRssDataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindRssDataSource is already builded");
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
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindRssDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindRssDaoFactory daoFactory);
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
    T onExecute(BindRssDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindRssDaoFactory {
    private SQLContextInSessionImpl _context;

    protected DaoRssImpl _daoRss;

    protected DaoArticleImpl _daoArticle;

    protected DaoChannelImpl _daoChannel;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindRssDataSource.this);
    }

    /**
     *
     * retrieve dao DaoRss
     */
    public DaoRssImpl getDaoRss() {
      if (_daoRss==null) {
        _daoRss=new DaoRssImpl(this);
      }
      return _daoRss;
    }

    /**
     *
     * retrieve dao DaoArticle
     */
    public DaoArticleImpl getDaoArticle() {
      if (_daoArticle==null) {
        _daoArticle=new DaoArticleImpl(this);
      }
      return _daoArticle;
    }

    /**
     *
     * retrieve dao DaoChannel
     */
    public DaoChannelImpl getDaoChannel() {
      if (_daoChannel==null) {
        _daoChannel=new DaoChannelImpl(this);
      }
      return _daoChannel;
    }

    @Override
    public SQLContext context() {
      return _context;
    }

    protected void onSessionOpened() {
      // support for live data
      _context.onSessionOpened();
    }

    protected void onSessionClear() {
      // support for live data
      _context.onSessionOpened();
    }

    protected void onSessionClosed() {
      // support for live data
      Set<Integer> daosWithEvents=_context.onSessionClosed();
      // "_daoRss" has no live data
      if (_daoArticle!=null && daosWithEvents.contains(DAO_ARTICLE_UID)) {
        _daoArticle.invalidateLiveData();
      }
      if (_daoChannel!=null && daosWithEvents.contains(DAO_CHANNEL_UID)) {
        _daoChannel.invalidateLiveData();
      }
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}

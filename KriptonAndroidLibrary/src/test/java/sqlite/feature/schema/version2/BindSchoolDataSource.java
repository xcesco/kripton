package sqlite.feature.schema.version2;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextSingleThreadImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import java.util.List;

/**
 * <p>
 * Represents implementation of datasource SchoolDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SchoolDataSource
 * @see BindSchoolDaoFactory
 * @see DaoProfessor
 * @see DaoProfessorImpl
 * @see Professor
 * @see DaoSeminar
 * @see DaoSeminarImpl
 * @see Seminar
 * @see DaoSeminar2Student
 * @see DaoSeminar2StudentImpl
 * @see Seminar2Student
 * @see DaoStudent
 * @see DaoStudentImpl
 * @see Student
 */
public class BindSchoolDataSource extends AbstractDataSource implements BindSchoolDaoFactory, SchoolDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindSchoolDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected DaoProfessorImpl daoProfessor = new DaoProfessorImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoSeminarImpl daoSeminar = new DaoSeminarImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoSeminar2StudentImpl daoSeminar2Student = new DaoSeminar2StudentImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoStudentImpl daoStudent = new DaoStudentImpl(this);

  /**
   * Used only in transactions (that can be executed one for time */
  private final DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindSchoolDataSource(DataSourceOptions options) {
    super("school", 2, options);
  }

  @Override
  public DaoProfessorImpl getDaoProfessor() {
    return daoProfessor;
  }

  @Override
  public DaoSeminarImpl getDaoSeminar() {
    return daoSeminar;
  }

  @Override
  public DaoSeminar2StudentImpl getDaoSeminar2Student() {
    return daoSeminar2Student;
  }

  @Override
  public DaoStudentImpl getDaoStudent() {
    return daoStudent;
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
  public static synchronized BindSchoolDataSource instance() {
    if (instance==null) {
      instance=new BindSchoolDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindSchoolDataSource open() {
    BindSchoolDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindSchoolDataSource openReadOnly() {
    BindSchoolDataSource instance=instance();
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
      Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    }
    // log section END
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(SeminarTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(StudentTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(ProfessorTable.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        }
        // log section END
        task.execute(database);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
        }
        // log section END
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
          Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        }
        // log section END
        task.execute(database);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
        }
        // log section END
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(SeminarTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(StudentTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(ProfessorTable.CREATE_TABLE_SQL);
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
    DaoProfessorImpl.clearCompiledStatements();
    DaoSeminarImpl.clearCompiledStatements();
    DaoSeminar2StudentImpl.clearCompiledStatements();
    DaoStudentImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindSchoolDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindSchoolDataSource(options);
    }
    instance.openWritableDatabase();
    instance.close();
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindSchoolDataSource build() {
    return build(DataSourceOptions.builder().build());
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindSchoolDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindSchoolDaoFactory daoFactory);
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
    T onExecute(BindSchoolDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindSchoolDaoFactory {
    private SQLContextSingleThreadImpl _context;

    private DaoProfessorImpl _daoProfessor;

    private DaoSeminarImpl _daoSeminar;

    private DaoSeminar2StudentImpl _daoSeminar2Student;

    private DaoStudentImpl _daoStudent;

    DataSourceSingleThread() {
      _context=new SQLContextSingleThreadImpl(BindSchoolDataSource.this);
    }

    /**
     *
     * retrieve dao DaoProfessor
     */
    public DaoProfessorImpl getDaoProfessor() {
      if (_daoProfessor==null) {
        _daoProfessor=new DaoProfessorImpl(_context);
      }
      return _daoProfessor;
    }

    /**
     *
     * retrieve dao DaoSeminar
     */
    public DaoSeminarImpl getDaoSeminar() {
      if (_daoSeminar==null) {
        _daoSeminar=new DaoSeminarImpl(_context);
      }
      return _daoSeminar;
    }

    /**
     *
     * retrieve dao DaoSeminar2Student
     */
    public DaoSeminar2StudentImpl getDaoSeminar2Student() {
      if (_daoSeminar2Student==null) {
        _daoSeminar2Student=new DaoSeminar2StudentImpl(_context);
      }
      return _daoSeminar2Student;
    }

    /**
     *
     * retrieve dao DaoStudent
     */
    public DaoStudentImpl getDaoStudent() {
      if (_daoStudent==null) {
        _daoStudent=new DaoStudentImpl(_context);
      }
      return _daoStudent;
    }

    public DataSourceSingleThread bindToThread() {
      _context.bindToThread();
      return this;
    }
  }
}

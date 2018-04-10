/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.schema.version2;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.*;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import java.util.List;

// TODO: Auto-generated Javadoc
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
  
  /** <p>datasource singleton</p>. */
  static volatile BindSchoolDataSource instance;

  /** <p>Mutex to manage multithread access to instance</p>. */
  private static final Object mutex = new Object();

  /** Unique identifier for Dao DaoProfessor. */
  public static final int DAO_PROFESSOR_UID = 0;

  /** Unique identifier for Dao DaoSeminar. */
  public static final int DAO_SEMINAR_UID = 1;

  /** Unique identifier for Dao DaoSeminar2Student. */
  public static final int DAO_SEMINAR2_STUDENT_UID = 2;

  /** Unique identifier for Dao DaoStudent. */
  public static final int DAO_STUDENT_UID = 3;

  /** List of tables compose datasource. */
  static final SQLiteTable[] TABLES = {new Seminar2StudentTable(), new ProfessorTable(), new StudentTable(), new SeminarTable()};

  /** <p>dao instance</p>. */
  protected DaoProfessorImpl daoProfessor = new DaoProfessorImpl(context);

  /** <p>dao instance</p>. */
  protected DaoSeminarImpl daoSeminar = new DaoSeminarImpl(context);

  /** <p>dao instance</p>. */
  protected DaoSeminar2StudentImpl daoSeminar2Student = new DaoSeminar2StudentImpl(context);

  /** <p>dao instance</p>. */
  protected DaoStudentImpl daoStudent = new DaoStudentImpl(context);

  /** Used only in transactions (that can be executed one for time. */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  /**
   * Instantiates a new bind school data source.
   *
   * @param options the options
   */
  protected BindSchoolDataSource(DataSourceOptions options) {
    super("school", 2, options);
  }

  /* (non-Javadoc)
   * @see sqlite.feature.schema.version2.BindSchoolDaoFactory#getDaoProfessor()
   */
  @Override
  public DaoProfessorImpl getDaoProfessor() {
    return daoProfessor;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.schema.version2.BindSchoolDaoFactory#getDaoSeminar()
   */
  @Override
  public DaoSeminarImpl getDaoSeminar() {
    return daoSeminar;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.schema.version2.BindSchoolDaoFactory#getDaoSeminar2Student()
   */
  @Override
  public DaoSeminar2StudentImpl getDaoSeminar2Student() {
    return daoSeminar2Student;
  }

  /* (non-Javadoc)
   * @see sqlite.feature.schema.version2.BindSchoolDaoFactory#getDaoStudent()
   */
  @Override
  public DaoStudentImpl getDaoStudent() {
    return daoStudent;
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
  public void execute(Transaction transaction, OnErrorListener onErrorListener) {
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
   * @param <T> the generic type
   * @param commands 	batch to execute
   * @return the t
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param <T> the generic type
   * @param commands 	batch to execute
   * @param writeMode 	true to open connection in write mode, false to open connection in read only mode
   * @return the t
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
   *
   * @return the bind school data source
   */
  public static BindSchoolDataSource instance() {
    BindSchoolDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindSchoolDataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
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
   * onCreate.
   *
   * @param database the database
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
      Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(StudentTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(SeminarTable.CREATE_TABLE_SQL);
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
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade.
   *
   * @param database the database
   * @param previousVersion the previous version
   * @param currentVersion the current version
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
        Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(StudentTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(SeminarTable.CREATE_TABLE_SQL);
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
   * onConfigure.
   *
   * @param database the database
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.android.sqlite.AbstractDataSource#clearCompiledStatements()
   */
  public void clearCompiledStatements() {
    DaoProfessorImpl.clearCompiledStatements();
    DaoSeminarImpl.clearCompiledStatements();
    DaoSeminar2StudentImpl.clearCompiledStatements();
    DaoStudentImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   *
   * @param options the options
   * @return the bind school data source
   */
  public static BindSchoolDataSource build(DataSourceOptions options) {
    BindSchoolDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindSchoolDataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
          }
        } else {
          throw new KriptonRuntimeException("Datasource BindSchoolDataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindSchoolDataSource is already builded");
    }
    return result;
  }

  /**
   * List of tables compose datasource:.
   *
   * @return the SQ lite table[]
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractExecutable<BindSchoolDaoFactory> {
    
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory the dao factory
     * @return the transaction result
     */
    TransactionResult onExecute(BindSchoolDaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   *
   * @param <T> the generic type
   */
  public interface Batch<T> {
    
    /**
     * Execute batch operations.
     *
     * @param daoFactory the dao factory
     * @return the t
     */
    T onExecute(BindSchoolDaoFactory daoFactory);
  }

  /**
   * The Class DataSourceSingleThread.
   */
  class DataSourceSingleThread implements BindSchoolDaoFactory {
    
    /** The context. */
    private SQLContextInSessionImpl _context;

    /** The dao professor. */
    protected DaoProfessorImpl _daoProfessor;

    /** The dao seminar. */
    protected DaoSeminarImpl _daoSeminar;

    /** The dao seminar 2 student. */
    protected DaoSeminar2StudentImpl _daoSeminar2Student;

    /** The dao student. */
    protected DaoStudentImpl _daoStudent;

    /**
     * Instantiates a new data source single thread.
     */
    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindSchoolDataSource.this);
    }

    /**
     * retrieve dao DaoProfessor.
     *
     * @return the dao professor
     */
    public DaoProfessorImpl getDaoProfessor() {
      if (_daoProfessor==null) {
        _daoProfessor=new DaoProfessorImpl(_context);
      }
      return _daoProfessor;
    }

    /**
     * retrieve dao DaoSeminar.
     *
     * @return the dao seminar
     */
    public DaoSeminarImpl getDaoSeminar() {
      if (_daoSeminar==null) {
        _daoSeminar=new DaoSeminarImpl(_context);
      }
      return _daoSeminar;
    }

    /**
     * retrieve dao DaoSeminar2Student.
     *
     * @return the dao seminar 2 student
     */
    public DaoSeminar2StudentImpl getDaoSeminar2Student() {
      if (_daoSeminar2Student==null) {
        _daoSeminar2Student=new DaoSeminar2StudentImpl(_context);
      }
      return _daoSeminar2Student;
    }

    /**
     * retrieve dao DaoStudent.
     *
     * @return the dao student
     */
    public DaoStudentImpl getDaoStudent() {
      if (_daoStudent==null) {
        _daoStudent=new DaoStudentImpl(_context);
      }
      return _daoStudent;
    }

    /**
     * On session opened.
     */
    protected void onSessionOpened() {
    }

    /**
     * On session clear.
     */
    protected void onSessionClear() {
    }

    /**
     * On session closed.
     */
    protected void onSessionClosed() {
    }

    /**
     * Bind to thread.
     *
     * @return the data source single thread
     */
    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}
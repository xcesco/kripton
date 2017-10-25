package sqlite.feature.schema.version2;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
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
  private static BindSchoolDataSource instance;

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
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase connection=openWritableDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT==transaction.onExecute(this)) {
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
   * instance
   */
  public static BindSchoolDataSource instance() {
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
    if (instance==null) {
      instance=new BindSchoolDataSource(null);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindSchoolDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindSchoolDataSource(null);
    }
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
    Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
    database.execSQL(SeminarTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
    database.execSQL(StudentTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
    database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
    database.execSQL(ProfessorTable.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        task.execute(database);
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
        task.execute(database);
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
      database.execSQL(SeminarTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
      database.execSQL(StudentTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
      database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
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

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static SchoolDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindSchoolDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractExecutable<BindSchoolDaoFactory> {
	  
	  TransactionResult onExecute(BindSchoolDaoFactory daoFactory);
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}

package sqlite.feature.schema;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource SchoolDataSource.
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
    super("school", 1, options);
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
      if (transaction!=null && transaction.onExecute(this)) {
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
    Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
    database.execSQL(StudentTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
    database.execSQL(SeminarTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
    database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
    database.execSQL(ProfessorTable.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, oldVersion, newVersion, true);
    } else {
      // drop tables
      Logger.info("DDL: %s",ProfessorTable.DROP_TABLE_SQL);
      database.execSQL(ProfessorTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",Seminar2StudentTable.DROP_TABLE_SQL);
      database.execSQL(Seminar2StudentTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",SeminarTable.DROP_TABLE_SQL);
      database.execSQL(SeminarTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",StudentTable.DROP_TABLE_SQL);
      database.execSQL(StudentTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",StudentTable.CREATE_TABLE_SQL);
      database.execSQL(StudentTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",SeminarTable.CREATE_TABLE_SQL);
      database.execSQL(SeminarTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",Seminar2StudentTable.CREATE_TABLE_SQL);
      database.execSQL(Seminar2StudentTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",ProfessorTable.CREATE_TABLE_SQL);
      database.execSQL(ProfessorTable.CREATE_TABLE_SQL);
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
  public interface Transaction extends AbstractTransaction<BindSchoolDaoFactory> {
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

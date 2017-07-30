package sqlite.feature.dynamic.update;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
import sqlite.feature.dynamic.PersonTable;

/**
 * <p>
 * Rapresents implementation of datasource PersonUpdateDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonUpdateDataSource
 * @see BindPersonUpdateDaoFactory
 * @see PersonUpdateDAO
 * @see PersonUpdateDAOImpl
 * @see sqlite.feature.dynamic.Person
 */
public class BindPersonUpdateDataSource extends AbstractDataSource implements BindPersonUpdateDaoFactory, PersonUpdateDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindPersonUpdateDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected PersonUpdateDAOImpl personUpdateDAO = new PersonUpdateDAOImpl(this);

  protected BindPersonUpdateDataSource(DataSourceOptions options) {
    super("person.db", 1, options);
  }

  @Override
  public PersonUpdateDAOImpl getPersonUpdateDAO() {
    return personUpdateDAO;
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
  public static BindPersonUpdateDataSource instance() {
    if (instance==null) {
      instance=new BindPersonUpdateDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPersonUpdateDataSource open() {
    if (instance==null) {
      instance=new BindPersonUpdateDataSource(null);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPersonUpdateDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindPersonUpdateDataSource(null);
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
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",PersonTable.DROP_TABLE_SQL);
      database.execSQL(PersonTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
  public static PersonUpdateDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindPersonUpdateDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindPersonUpdateDaoFactory> {
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

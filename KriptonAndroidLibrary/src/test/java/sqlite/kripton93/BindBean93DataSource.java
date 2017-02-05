package sqlite.kripton93;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Represents implementation of datasource Bean93DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean93DataSource
 * @see BindBean93DaoFactory
 * @see Bean93Dao
 * @see Bean93DaoImpl
 * @see Bean93
 */
public class BindBean93DataSource extends AbstractDataSource implements BindBean93DaoFactory, Bean93DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean93DataSource instance = new BindBean93DataSource();

  /**
   * <p>dao instance</p>
   */
  protected Bean93DaoImpl bean93Dao = new Bean93DaoImpl(this);

  protected BindBean93DataSource() {
    super("dummy", 1);
  }

  @Override
  public Bean93DaoImpl getBean93Dao() {
    return bean93Dao;
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
  public static BindBean93DataSource instance() {
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindBean93DataSource open() {
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindBean93DataSource openReadOnly() {
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean93Table.CREATE_TABLE_SQL);
    database.execSQL(Bean93Table.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",Bean93Table.DROP_TABLE_SQL);
      database.execSQL(Bean93Table.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",Bean93Table.CREATE_TABLE_SQL);
      database.execSQL(Bean93Table.CREATE_TABLE_SQL);
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
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean93DaoFactory> {
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

package bind.kripton81ExceptionCoverage;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource Bean8DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean8DataSource
 * @see BindBean8DaoFactory
 * @see Bean8Dao
 * @see Bean8DaoImpl
 * @see Bean8
 */
public class BindBean8DataSource extends AbstractDataSource implements BindBean8DaoFactory, Bean8DataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindBean8DataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected Bean8DaoImpl bean8Dao = new Bean8DaoImpl(this);

  protected BindBean8DataSource() {
    this(null);
  }

  protected BindBean8DataSource(DataSourceOptions options) {
    super("", 1, null);
  }

  @Override
  public Bean8DaoImpl getBean8Dao() {
    return bean8Dao;
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
  public static BindBean8DataSource instance() {
    if (instance==null) {
      instance=new BindBean8DataSource();
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindBean8DataSource open() {
    if (instance==null) {
      instance=new BindBean8DataSource();
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindBean8DataSource openReadOnly() {
    if (instance==null) {
      instance=new BindBean8DataSource();
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
    Logger.info("DDL: %s",Bean8Table.CREATE_TABLE_SQL);
    database.execSQL(Bean8Table.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",Bean8Table.DROP_TABLE_SQL);
      database.execSQL(Bean8Table.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",Bean8Table.CREATE_TABLE_SQL);
      database.execSQL(Bean8Table.CREATE_TABLE_SQL);
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
  public static Bean8DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindBean8DataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean8DaoFactory> {
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

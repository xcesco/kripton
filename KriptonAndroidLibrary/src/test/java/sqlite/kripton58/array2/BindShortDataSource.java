package sqlite.kripton58.array2;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource ShortDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ShortDataSource
 * @see BindShortDaoFactory
 * @see ShortDao
 * @see ShortDaoImpl
 * @see ShortBean
 */
public class BindShortDataSource extends AbstractDataSource implements BindShortDaoFactory, ShortDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindShortDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected ShortDaoImpl shortDao = new ShortDaoImpl(this);

  protected BindShortDataSource() {
    this(null);
  }

  protected BindShortDataSource(DataSourceOptions options) {
    super("dummy", 1, null);
  }

  @Override
  public ShortDaoImpl getShortDao() {
    return shortDao;
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
  public static BindShortDataSource instance() {
    if (instance==null) {
      instance=new BindShortDataSource();
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindShortDataSource open() {
    if (instance==null) {
      instance=new BindShortDataSource();
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindShortDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindShortDataSource();
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
    Logger.info("DDL: %s",ShortBeanTable.CREATE_TABLE_SQL);
    database.execSQL(ShortBeanTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",ShortBeanTable.DROP_TABLE_SQL);
      database.execSQL(ShortBeanTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",ShortBeanTable.CREATE_TABLE_SQL);
      database.execSQL(ShortBeanTable.CREATE_TABLE_SQL);
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
  public static ShortDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindShortDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindShortDaoFactory> {
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

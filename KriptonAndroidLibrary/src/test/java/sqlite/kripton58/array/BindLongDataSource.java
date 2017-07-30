package sqlite.kripton58.array;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource LongDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see LongDataSource
 * @see BindLongDaoFactory
 * @see LongDao
 * @see LongDaoImpl
 * @see LongBean
 */
public class BindLongDataSource extends AbstractDataSource implements BindLongDaoFactory, LongDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindLongDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected LongDaoImpl longDao = new LongDaoImpl(this);

  protected BindLongDataSource(DataSourceOptions options) {
    super("dummy", 1, options);
  }

  @Override
  public LongDaoImpl getLongDao() {
    return longDao;
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
  public static BindLongDataSource instance() {
    if (instance==null) {
      instance=new BindLongDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindLongDataSource open() {
    if (instance==null) {
      instance=new BindLongDataSource(null);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindLongDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindLongDataSource(null);
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
    Logger.info("DDL: %s",LongBeanTable.CREATE_TABLE_SQL);
    database.execSQL(LongBeanTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",LongBeanTable.DROP_TABLE_SQL);
      database.execSQL(LongBeanTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",LongBeanTable.CREATE_TABLE_SQL);
      database.execSQL(LongBeanTable.CREATE_TABLE_SQL);
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
  public static LongDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindLongDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindLongDaoFactory> {
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

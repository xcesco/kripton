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
 * Rapresents implementation of datasource PKDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PKDataSource
 * @see BindPKDaoFactory
 * @see PKDao
 * @see PKDaoImpl
 * @see PKBean
 */
public class BindPKDataSource extends AbstractDataSource implements BindPKDaoFactory, PKDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindPKDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected PKDaoImpl pKDao = new PKDaoImpl(this);

  protected BindPKDataSource() {
    this(null);
  }

  protected BindPKDataSource(DataSourceOptions options) {
    super("", 1, null);
  }

  @Override
  public PKDaoImpl getPKDao() {
    return pKDao;
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
  public static BindPKDataSource instance() {
    if (instance==null) {
      instance=new BindPKDataSource();
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPKDataSource open() {
    if (instance==null) {
      instance=new BindPKDataSource();
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPKDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindPKDataSource();
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
    Logger.info("DDL: %s",PKBeanTable.CREATE_TABLE_SQL);
    database.execSQL(PKBeanTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",PKBeanTable.DROP_TABLE_SQL);
      database.execSQL(PKBeanTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",PKBeanTable.CREATE_TABLE_SQL);
      database.execSQL(PKBeanTable.CREATE_TABLE_SQL);
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
  public static PKDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindPKDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindPKDaoFactory> {
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

package sqlite.kripton58.list;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource IntegerDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntegerDataSource
 * @see BindIntegerDaoFactory
 * @see IntegerDao
 * @see IntegerDaoImpl
 * @see IntegerBean
 */
public class BindIntegerDataSource extends AbstractDataSource implements BindIntegerDaoFactory, IntegerDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindIntegerDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected IntegerDaoImpl integerDao = new IntegerDaoImpl(this);

  protected BindIntegerDataSource() {
    this(null);
  }

  protected BindIntegerDataSource(DataSourceOptions options) {
    super("dummy", 1, null);
  }

  @Override
  public IntegerDaoImpl getIntegerDao() {
    return integerDao;
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
  public static BindIntegerDataSource instance() {
    if (instance==null) {
      instance=new BindIntegerDataSource();
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindIntegerDataSource open() {
    if (instance==null) {
      instance=new BindIntegerDataSource();
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindIntegerDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindIntegerDataSource();
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
    Logger.info("DDL: %s",IntegerBeanTable.CREATE_TABLE_SQL);
    database.execSQL(IntegerBeanTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",IntegerBeanTable.DROP_TABLE_SQL);
      database.execSQL(IntegerBeanTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",IntegerBeanTable.CREATE_TABLE_SQL);
      database.execSQL(IntegerBeanTable.CREATE_TABLE_SQL);
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
  public static IntegerDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindIntegerDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindIntegerDaoFactory> {
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

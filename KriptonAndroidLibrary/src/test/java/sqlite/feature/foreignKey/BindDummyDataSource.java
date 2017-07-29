package sqlite.feature.foreignKey;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Rapresents implementation of datasource DummyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DummyDataSource
 * @see BindDummyDaoFactory
 * @see DaoBeanA_1
 * @see DaoBeanA_1Impl
 * @see BeanA_1
 * @see DaoBeanA_2
 * @see DaoBeanA_2Impl
 * @see BeanA_2
 */
public class BindDummyDataSource extends AbstractDataSource implements BindDummyDaoFactory, DummyDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindDummyDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_1Impl daoBeanA_1 = new DaoBeanA_1Impl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_2Impl daoBeanA_2 = new DaoBeanA_2Impl(this);

  protected BindDummyDataSource() {
    this(null);
  }

  protected BindDummyDataSource(DataSourceOptions options) {
    super("test.db", 1, null);
  }

  @Override
  public DaoBeanA_1Impl getDaoBeanA_1() {
    return daoBeanA_1;
  }

  @Override
  public DaoBeanA_2Impl getDaoBeanA_2() {
    return daoBeanA_2;
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
  public static BindDummyDataSource instance() {
    if (instance==null) {
      instance=new BindDummyDataSource();
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindDummyDataSource open() {
    if (instance==null) {
      instance=new BindDummyDataSource();
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindDummyDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindDummyDataSource();
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
    Logger.info("DDL: %s",BeanA_2Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_2Table.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",BeanA_1Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_1Table.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",BeanA_1Table.DROP_TABLE_SQL);
      database.execSQL(BeanA_1Table.DROP_TABLE_SQL);
      Logger.info("DDL: %s",BeanA_2Table.DROP_TABLE_SQL);
      database.execSQL(BeanA_2Table.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",BeanA_2Table.CREATE_TABLE_SQL);
      database.execSQL(BeanA_2Table.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",BeanA_1Table.CREATE_TABLE_SQL);
      database.execSQL(BeanA_1Table.CREATE_TABLE_SQL);
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
  public static DummyDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindDummyDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummyDaoFactory> {
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

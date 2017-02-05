package sqlite.foreignKey;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;

/**
 * <p>
 * Represents implementation of datasource Dummy2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy2DataSource
 * @see BindDummy2DaoFactory
 * @see DaoBeanA_3
 * @see DaoBeanA_3Impl
 * @see BeanA_3
 * @see DaoBeanA_4
 * @see DaoBeanA_4Impl
 * @see BeanA_4
 */
public class BindDummy2DataSource extends AbstractDataSource implements BindDummy2DaoFactory, Dummy2DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy2DataSource instance = new BindDummy2DataSource();

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_3Impl daoBeanA_3 = new DaoBeanA_3Impl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_4Impl daoBeanA_4 = new DaoBeanA_4Impl(this);

  protected BindDummy2DataSource() {
    super("test.db", 1);
  }

  @Override
  public DaoBeanA_3Impl getDaoBeanA_3() {
    return daoBeanA_3;
  }

  @Override
  public DaoBeanA_4Impl getDaoBeanA_4() {
    return daoBeanA_4;
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
  public static BindDummy2DataSource instance() {
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindDummy2DataSource open() {
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindDummy2DataSource openReadOnly() {
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",BeanA_3Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_3Table.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",BeanA_4Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_4Table.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",BeanA_4Table.DROP_TABLE_SQL);
      database.execSQL(BeanA_4Table.DROP_TABLE_SQL);
      Logger.info("DDL: %s",BeanA_3Table.DROP_TABLE_SQL);
      database.execSQL(BeanA_3Table.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",BeanA_3Table.CREATE_TABLE_SQL);
      database.execSQL(BeanA_3Table.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",BeanA_4Table.CREATE_TABLE_SQL);
      database.execSQL(BeanA_4Table.CREATE_TABLE_SQL);
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
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy2DaoFactory> {
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

package sqlite.foreignKey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;

/**
 * <p>
 * Represents implementation of datasource DummyDataSource.
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
   * <p><singleton of datasource,/p>
   */
  private static BindDummyDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "test.db";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_1Impl daoBeanA_1 = new DaoBeanA_1Impl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_2Impl daoBeanA_2 = new DaoBeanA_2Impl(this);

  protected BindDummyDataSource(Context context) {
    super(context, name, null, version);
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
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=openDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindDummyDataSource instance() {
    if (instance==null) {
      instance=new BindDummyDataSource(KriptonLibrary.context());
    }
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
    if (databaseListener == null) {
      databaseListener.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    if (databaseListener == null) {
      databaseListener.onUpdate(database, oldVersion, newVersion, true);
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
    if (databaseListener == null) {
      databaseListener.onConfigure(database);
    }
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
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
  }
}

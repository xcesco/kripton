package sqlite.foreignKey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy3DataSource
 * @see BindDummy3DaoFactory
 * @see DaoBeanA_5
 * @see DaoBeanA_5Impl
 * @see BeanA_5
 * @see DaoBeanA_6
 * @see DaoBeanA_6Impl
 * @see BeanA_6
 */
public class BindDummy3DataSource extends AbstractDataSource implements BindDummy3DaoFactory, Dummy3DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy3DataSource instance;

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
  protected DaoBeanA_5Impl daoBeanA_5 = new DaoBeanA_5Impl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanA_6Impl daoBeanA_6 = new DaoBeanA_6Impl(this);

  protected BindDummy3DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoBeanA_5Impl getDaoBeanA_5() {
    return daoBeanA_5;
  }

  @Override
  public DaoBeanA_6Impl getDaoBeanA_6() {
    return daoBeanA_6;
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
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindDummy3DataSource instance() {
    if (instance==null) {
      instance=new BindDummy3DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",BeanA_6Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_6Table.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",BeanA_5Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_5Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",BeanA_5Table.DROP_TABLE_SQL);
    database.execSQL(BeanA_5Table.DROP_TABLE_SQL);
    Logger.info("DDL: %s",BeanA_6Table.DROP_TABLE_SQL);
    database.execSQL(BeanA_6Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",BeanA_6Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_6Table.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",BeanA_5Table.CREATE_TABLE_SQL);
    database.execSQL(BeanA_5Table.CREATE_TABLE_SQL);
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy3DaoFactory> {
  }
}

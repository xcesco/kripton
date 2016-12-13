package sqlite.kripton58.array2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource FloatDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FloatDataSource
 * @see BindFloatDaoFactory
 * @see FloatDao
 * @see FloatDaoImpl
 * @see FloatBean
 */
public class BindFloatDataSource extends AbstractDataSource implements BindFloatDaoFactory, FloatDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindFloatDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected FloatDaoImpl floatDao = new FloatDaoImpl(this);

  protected BindFloatDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public FloatDaoImpl getFloatDao() {
    return floatDao;
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
  public static synchronized BindFloatDataSource instance() {
    if (instance==null) {
      instance=new BindFloatDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",FloatBeanTable.CREATE_TABLE_SQL);
    database.execSQL(FloatBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",FloatBeanTable.DROP_TABLE_SQL);
    database.execSQL(FloatBeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",FloatBeanTable.CREATE_TABLE_SQL);
    database.execSQL(FloatBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindFloatDaoFactory> {
  }
}

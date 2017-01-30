package bind.kripton81ExceptionCoverage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;

/**
 * <p>
 * Represents implementation of datasource PKDataSource.
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
   * <p><singleton of datasource,/p>
   */
  private static BindPKDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected PKDaoImpl pKDao = new PKDaoImpl(this);

  protected BindPKDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public PKDaoImpl getPKDao() {
    return pKDao;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
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
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindPKDataSource instance() {
    if (instance==null) {
      instance=new BindPKDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPKDataSource open() {
    BindPKDataSource instance=instance();
    instance.getWritableDatabase();
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
    if (databaseListener != null) {
      databaseListener.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    if (databaseListener != null) {
      databaseListener.onUpdate(database, oldVersion, newVersion, true);
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
    if (databaseListener != null) {
      databaseListener.onConfigure(database);
    }
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

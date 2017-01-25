package sqlite.kripton58.array2;

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
 * Represents implementation of datasource ShortDataSource.
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
   * <p><singleton of datasource,/p>
   */
  private static BindShortDataSource instance;

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
  protected ShortDaoImpl shortDao = new ShortDaoImpl(this);

  protected BindShortDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public ShortDaoImpl getShortDao() {
    return shortDao;
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
      if (transaction!=null) transaction.onError(e);
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindShortDataSource instance() {
    if (instance==null) {
      instance=new BindShortDataSource(KriptonLibrary.context());
    }
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
    if (databaseListener != null) {
      databaseListener.onConfigure(database);
    }
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

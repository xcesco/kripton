package sqlite.kripton58.list;

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
 * Represents implementation of datasource IntegerDataSource.
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
   * <p><singleton of datasource,/p>
   */
  private static BindIntegerDataSource instance;

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
  protected IntegerDaoImpl integerDao = new IntegerDaoImpl(this);

  protected BindIntegerDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public IntegerDaoImpl getIntegerDao() {
    return integerDao;
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
  public static synchronized BindIntegerDataSource instance() {
    if (instance==null) {
      instance=new BindIntegerDataSource(KriptonLibrary.context());
    }
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
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",IntegerBeanTable.DROP_TABLE_SQL);
    database.execSQL(IntegerBeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",IntegerBeanTable.CREATE_TABLE_SQL);
    database.execSQL(IntegerBeanTable.CREATE_TABLE_SQL);
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
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
  }
}

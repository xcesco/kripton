package sqlite.kripton64;

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
 * Represents implementation of datasource Bean64BDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean64BDataSource
 * @see BindBean64BDaoFactory
 * @see Bean64BDao
 * @see Bean64BDaoImpl
 * @see Bean64B
 */
public class BindBean64BDataSource extends AbstractDataSource implements BindBean64BDaoFactory, Bean64BDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean64BDataSource instance;

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
  protected Bean64BDaoImpl bean64BDao = new Bean64BDaoImpl(this);

  protected BindBean64BDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Bean64BDaoImpl getBean64BDao() {
    return bean64BDao;
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
  public static synchronized BindBean64BDataSource instance() {
    if (instance==null) {
      instance=new BindBean64BDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean64BTable.CREATE_TABLE_SQL);
    database.execSQL(Bean64BTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean64BTable.DROP_TABLE_SQL);
    database.execSQL(Bean64BTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean64BTable.CREATE_TABLE_SQL);
    database.execSQL(Bean64BTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean64BDaoFactory> {
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

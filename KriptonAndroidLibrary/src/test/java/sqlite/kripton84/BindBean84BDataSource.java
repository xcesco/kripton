package sqlite.kripton84;

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
 * Represents implementation of datasource Bean84BDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84BDataSource
 * @see BindBean84BDaoFactory
 * @see Bean84BDao
 * @see Bean84BDaoImpl
 * @see Bean84B
 */
public class BindBean84BDataSource extends AbstractDataSource implements BindBean84BDaoFactory, Bean84BDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean84BDataSource instance;

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
  protected Bean84BDaoImpl bean84BDao = new Bean84BDaoImpl(this);

  protected BindBean84BDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Bean84BDaoImpl getBean84BDao() {
    return bean84BDao;
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
      throw(new KriptonRuntimeException(e));
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindBean84BDataSource instance() {
    if (instance==null) {
      instance=new BindBean84BDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean84BTable.CREATE_TABLE_SQL);
    database.execSQL(Bean84BTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",Bean84BTable.DROP_TABLE_SQL);
      database.execSQL(Bean84BTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",Bean84BTable.CREATE_TABLE_SQL);
      database.execSQL(Bean84BTable.CREATE_TABLE_SQL);
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
  public interface Transaction extends AbstractTransaction<BindBean84BDaoFactory> {
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      // for default, do nothing
    }
  }
}

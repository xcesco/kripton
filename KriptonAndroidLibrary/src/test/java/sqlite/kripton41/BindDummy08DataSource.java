package sqlite.kripton41;

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
 * Represents implementation of datasource Dummy08DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy08DataSource
 * @see BindDummy08DaoFactory
 * @see DaoBeanDeleteOK
 * @see DaoBeanDeleteOKImpl
 * @see Bean01
 */
public class BindDummy08DataSource extends AbstractDataSource implements BindDummy08DaoFactory, Dummy08DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy08DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy1";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanDeleteOKImpl daoBeanDeleteOK = new DaoBeanDeleteOKImpl(this);

  protected BindDummy08DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoBeanDeleteOKImpl getDaoBeanDeleteOK() {
    return daoBeanDeleteOK;
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
  public static synchronized BindDummy08DataSource instance() {
    if (instance==null) {
      instance=new BindDummy08DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindDummy08DataSource open() {
    BindDummy08DataSource instance=instance();
    instance.getWritableDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",Bean01Table.DROP_TABLE_SQL);
      database.execSQL(Bean01Table.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
      database.execSQL(Bean01Table.CREATE_TABLE_SQL);
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
  public interface Transaction extends AbstractTransaction<BindDummy08DaoFactory> {
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

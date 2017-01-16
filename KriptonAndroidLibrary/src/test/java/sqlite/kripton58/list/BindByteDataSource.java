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
 * Represents implementation of datasource ByteDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ByteDataSource
 * @see BindByteDaoFactory
 * @see ByteDao
 * @see ByteDaoImpl
 * @see ByteBean
 */
public class BindByteDataSource extends AbstractDataSource implements BindByteDaoFactory, ByteDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindByteDataSource instance;

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
  protected ByteDaoImpl byteDao = new ByteDaoImpl(this);

  protected BindByteDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public ByteDaoImpl getByteDao() {
    return byteDao;
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
  public static synchronized BindByteDataSource instance() {
    if (instance==null) {
      instance=new BindByteDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",ByteBeanTable.CREATE_TABLE_SQL);
    database.execSQL(ByteBeanTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",ByteBeanTable.DROP_TABLE_SQL);
      database.execSQL(ByteBeanTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",ByteBeanTable.CREATE_TABLE_SQL);
      database.execSQL(ByteBeanTable.CREATE_TABLE_SQL);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (databaseListener == null) {
      databaseListener.onConfigure(database);
    }
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindByteDaoFactory> {
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

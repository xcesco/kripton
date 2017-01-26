package sqlite.dynamic.update;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;
import sqlite.dynamic.PersonTable;

/**
 * <p>
 * Represents implementation of datasource Err2UpdateDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err2UpdateDataSource
 * @see BindErr2UpdateDaoFactory
 * @see Err2UpdateDAO
 * @see Err2UpdateDAOImpl
 * @see sqlite.dynamic.Person
 */
public class BindErr2UpdateDataSource extends AbstractDataSource implements BindErr2UpdateDaoFactory, Err2UpdateDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindErr2UpdateDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "person.db";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected Err2UpdateDAOImpl err2UpdateDAO = new Err2UpdateDAOImpl(this);

  protected BindErr2UpdateDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Err2UpdateDAOImpl getErr2UpdateDAO() {
    return err2UpdateDAO;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=open();
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
  public static synchronized BindErr2UpdateDataSource instance() {
    if (instance==null) {
      instance=new BindErr2UpdateDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",PersonTable.DROP_TABLE_SQL);
      database.execSQL(PersonTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
  public interface Transaction extends AbstractTransaction<BindErr2UpdateDaoFactory> {
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

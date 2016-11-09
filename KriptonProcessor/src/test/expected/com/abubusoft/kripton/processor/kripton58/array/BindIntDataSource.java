package com.abubusoft.kripton.processor.kripton58.array;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource IntDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntDataSource
 * @see BindIntDaoFactory
 * @see IntDao
 * @see IntDaoImpl
 * @see IntBean
 */
public class BindIntDataSource extends AbstractDataSource implements BindIntDaoFactory, IntDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindIntDataSource instance;

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
  protected IntDaoImpl intDao = new IntDaoImpl(this);

  protected BindIntDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public IntDaoImpl getIntDao() {
    return intDao;
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
  public static BindIntDataSource instance() {
    if (instance==null) {
      instance=new BindIntDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",IntBeanTable.CREATE_TABLE_SQL);
    database.execSQL(IntBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",IntBeanTable.DROP_TABLE_SQL);
    database.execSQL(IntBeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",IntBeanTable.CREATE_TABLE_SQL);
    database.execSQL(IntBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindIntDaoFactory> {
  }
}

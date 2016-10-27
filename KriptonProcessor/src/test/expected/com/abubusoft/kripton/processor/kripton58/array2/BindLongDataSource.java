package com.abubusoft.kripton.processor.kripton58.array2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource LongDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see LongDataSource
 * @see BindLongDaoFactory
 * @see LongDao
 * @see BindLongDao
 * @see LongBean
 */
public class BindLongDataSource extends AbstractDataSource implements BindLongDaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindLongDataSource instance;

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
  protected BindLongDao longDao = new BindLongDao(this);

  protected BindLongDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindLongDao getLongDao() {
    return longDao;
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
  public static BindLongDataSource instance() {
    if (instance==null) {
      instance=new BindLongDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.array2.LongBeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindLongDaoFactory> {
  }
}

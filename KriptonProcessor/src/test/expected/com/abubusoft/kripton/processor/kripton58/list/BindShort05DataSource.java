package com.abubusoft.kripton.processor.kripton58.list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Short05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Short05DataSource
 * @see BindShort05DaoFactory
 * @see Short05Dao
 * @see BindShort05Dao
 * @see Short05Bean
 */
public class BindShort05DataSource extends AbstractDataSource implements BindShort05DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindShort05DataSource instance;

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
  protected BindShort05Dao short05Dao = new BindShort05Dao(this);

  protected BindShort05DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindShort05Dao getShort05Dao() {
    return short05Dao;
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
  public static BindShort05DataSource instance() {
    if (instance==null) {
      instance=new BindShort05DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindShort05DaoFactory> {
  }
}

package com.abubusoft.kripton.processor.kripton58;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.processor.kripton58.list.Bean04Bean;
import com.abubusoft.kripton.processor.kripton58.list.Bean04Dao;
import com.abubusoft.kripton.processor.kripton58.list.Bean04DataSource;

import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Bean04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean04DataSource
 * @see BindBean04DaoFactory
 * @see Bean04Dao
 * @see BindBean04Dao
 * @see Bean04Bean
 */
public class BindBean04DataSource extends AbstractDataSource implements BindBean04DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean04DataSource instance;

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
  protected BindBean04Dao bean04Dao = new BindBean04Dao(this);

  protected BindBean04DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindBean04Dao getBean04Dao() {
    return bean04Dao;
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
  public static BindBean04DataSource instance() {
    if (instance==null) {
      instance=new BindBean04DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Bean04BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean04DaoFactory> {
  }
}

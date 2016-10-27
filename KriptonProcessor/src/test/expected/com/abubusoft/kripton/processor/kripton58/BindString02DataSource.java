package com.abubusoft.kripton.processor.kripton58;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.processor.kripton58.list.String02Bean;
import com.abubusoft.kripton.processor.kripton58.list.String02Dao;
import com.abubusoft.kripton.processor.kripton58.list.String02DataSource;

import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource String02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see String02DataSource
 * @see BindString02DaoFactory
 * @see String02Dao
 * @see BindString02Dao
 * @see String02Bean
 */
public class BindString02DataSource extends AbstractDataSource implements BindString02DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindString02DataSource instance;

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
  protected BindString02Dao string02Dao = new BindString02Dao(this);

  protected BindString02DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindString02Dao getString02Dao() {
    return string02Dao;
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
  public static BindString02DataSource instance() {
    if (instance==null) {
      instance=new BindString02DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.String02BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.String02BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.String02BeanTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.String02BeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.String02BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.String02BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindString02DaoFactory> {
  }
}

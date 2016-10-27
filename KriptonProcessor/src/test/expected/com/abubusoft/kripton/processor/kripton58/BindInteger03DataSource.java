package com.abubusoft.kripton.processor.kripton58;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Bean;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Dao;
import com.abubusoft.kripton.processor.kripton58.list.Integer03DataSource;

import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Integer03DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Integer03DataSource
 * @see BindInteger03DaoFactory
 * @see Integer03Dao
 * @see BindInteger03Dao
 * @see Integer03Bean
 */
public class BindInteger03DataSource extends AbstractDataSource implements BindInteger03DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindInteger03DataSource instance;

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
  protected BindInteger03Dao integer03Dao = new BindInteger03Dao(this);

  protected BindInteger03DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindInteger03Dao getInteger03Dao() {
    return integer03Dao;
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
  public static BindInteger03DataSource instance() {
    if (instance==null) {
      instance=new BindInteger03DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton58.Integer03BeanTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindInteger03DaoFactory> {
  }
}

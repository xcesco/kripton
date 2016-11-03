package com.abubusoft.kripton.processor.kripton62;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Bean2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean2DataSource
 * @see BindBean2DaoFactory
 * @see BeanDao2
 * @see BindBeanDao2
 * @see Bean2
 */
public class BindBean2DataSource extends AbstractDataSource implements BindBean2DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean2DataSource instance;

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
  protected BindBeanDao2 beanDao2 = new BindBeanDao2(this);

  protected BindBean2DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindBeanDao2 getBeanDao2() {
    return beanDao2;
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
  public static BindBean2DataSource instance() {
    if (instance==null) {
      instance=new BindBean2DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton62.Bean2Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton62.Bean2Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton62.Bean2Table.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton62.Bean2Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton62.Bean2Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton62.Bean2Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean2DaoFactory> {
  }
}

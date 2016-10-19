package com.abubusoft.kripton.processor.kripton41;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy08DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy08DataSource
 * @see BindDummy08DaoFactory
 * @see DaoBeanDeleteOK
 * @see BindDaoBeanDeleteOK
 * @see Bean01
 */
public class BindDummy08DataSource extends AbstractDataSource implements BindDummy08DaoFactory {
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
  protected BindDaoBeanDeleteOK daoBeanDeleteOK = new BindDaoBeanDeleteOK(this);

  protected BindDummy08DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoBeanDeleteOK getDaoBeanDeleteOK() {
    return daoBeanDeleteOK;
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
  public static BindDummy08DataSource instance() {
    if (instance==null) {
      instance=new BindDummy08DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton41.Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton41.Bean01Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton41.Bean01Table.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton41.Bean01Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton41.Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton41.Bean01Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy08DaoFactory> {
  }
}

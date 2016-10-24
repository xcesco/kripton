package com.abubusoft.kripton.processor.kripton38;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy03DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy03DataSource
 * @see BindDummy03DaoFactory
 * @see DaoBean03
 * @see BindDaoBean03
 * @see Bean03
 */
public class BindDummy03DataSource extends AbstractDataSource implements BindDummy03DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy03DataSource instance;

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
  protected BindDaoBean03 daoBean03 = new BindDaoBean03(this);

  protected BindDummy03DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoBean03 getDaoBean03() {
    return daoBean03;
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
  public static BindDummy03DataSource instance() {
    if (instance==null) {
      instance=new BindDummy03DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton38.Bean03Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton38.Bean03Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton38.Bean03Table.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton38.Bean03Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton38.Bean03Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton38.Bean03Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy03DaoFactory> {
  }
}

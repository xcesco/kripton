package com.abubusoft.kripton.processor.test03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy01DataSource
 * @see BindDummy01DaoFactory
 * @see DaoBean01
 * @see com.abubusoft.kripton.processor.test03.DaoBean01$Impl
 * @see Bean01
 */
public class BindDummy01DataSource extends AbstractDataSource implements BindDummy01DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy01DataSource instance;

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
  protected com.abubusoft.kripton.processor.test03.DaoBean01 daoBean01 = new com.abubusoft.kripton.processor.test03.DaoBean01$Impl(this);

  protected BindDummy01DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public com.abubusoft.kripton.processor.test03.DaoBean01 getDaoBean01() {
    return daoBean01;
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
  public static BindDummy01DataSource instance() {
    if (instance==null) {
      instance=new BindDummy01DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.test03.Bean01$Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.test03.Bean01$Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.test03.Bean01$Table.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.test03.Bean01$Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.test03.Bean01$Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.test03.Bean01$Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy01DaoFactory> {
  }
}

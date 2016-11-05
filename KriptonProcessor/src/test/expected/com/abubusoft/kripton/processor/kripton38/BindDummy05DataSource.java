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
 * Represents implementation of datasource Dummy05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy05DataSource
 * @see BindDummy05DaoFactory
 * @see DaoBean05
 * @see DaoBean05Impl
 * @see Bean05
 */
public class BindDummy05DataSource extends AbstractDataSource implements BindDummy05DaoFactory, Dummy05DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy05DataSource instance;

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
  protected DaoBean05Impl daoBean05 = new DaoBean05Impl(this);

  protected BindDummy05DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoBean05Impl getDaoBean05() {
    return daoBean05;
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
  public static BindDummy05DataSource instance() {
    if (instance==null) {
      instance=new BindDummy05DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean05$Table.CREATE_TABLE_SQL);
    database.execSQL(Bean05$Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean05$Table.DROP_TABLE_SQL);
    database.execSQL(Bean05$Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean05$Table.CREATE_TABLE_SQL);
    database.execSQL(Bean05$Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy05DaoFactory> {
  }
}

package com.abubusoft.kripton.processor.test05firt_aid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import java.lang.Override;
import java.lang.String;

public class BindFirstAidDataSource extends AbstractDataSource implements BindFirstAidDaoFactory {
  private static BindFirstAidDataSource instance;

  public static final String name = "firstaid.db";

  public static final int version = 1;

  protected BindFirstAidDao firstAidDao = new BindFirstAidDao();

  protected BindFirstAidDataSource(Context context) {
    super(context, name, null, version);
  }

  public BindFirstAidDao getFirstAidDao() {
    // get current database connection, without increment connection counter
    if (database==null) throw(new KriptonRuntimeException("No database connection is opened"));
    firstAidDao.setDatabase(database);
    return firstAidDao;
  }

  @Override
  public BindFirstAidDao getFirstAidDao(SQLiteDatabase database) {
    firstAidDao.setDatabase(database);
    return firstAidDao;
  }

  /**
   * <p>Allow to execute a transaction. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    boolean localConnection=false;
    SQLiteDatabase connection=this.database;
    if (connection==null) {
      localConnection=true;
      connection=openDatabase();
    }
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this, connection)) {
        connection.setTransactionSuccessful();
      }
    } finally {
      connection.endTransaction();
      if (localConnection) {
        connection.close();
      }
    }
  }

  /**
   *
   * instance
   */
  public static BindFirstAidDataSource instance() {
    if (instance==null) {
      instance=new BindFirstAidDataSource(KriptonLibrary.context);
    }
    return instance;
  }

  /**
   *
   * onCreate
   *
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",FirstAidTable.CREATE_TABLE_SQL);
    database.execSQL(FirstAidTable.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",FirstAidTable.DROP_TABLE_SQL);
    database.execSQL(FirstAidTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",FirstAidTable.CREATE_TABLE_SQL);
    database.execSQL(FirstAidTable.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindFirstAidDaoFactory> {
  }
}

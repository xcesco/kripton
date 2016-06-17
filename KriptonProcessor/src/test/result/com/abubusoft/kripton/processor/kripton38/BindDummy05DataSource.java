package com.abubusoft.kripton.processor.kripton38;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.common.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;

public class BindDummy05DataSource extends AbstractDataSource implements BindDummy05DaoFactory {
  private static BindDummy05DataSource instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean05 daoBean05 = new BindDaoBean05();

  protected BindDummy05DataSource(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean05 getDaoBean05() {
    // get current database connection, without increment connection counter
    if (database==null) throw(new KriptonRuntimeException("No database connection is opened"));
    daoBean05.setDatabase(database);
    return daoBean05;
  }

  @Override
  public BindDaoBean05 getDaoBean05(SQLiteDatabase database) {
    daoBean05.setDatabase(database);
    return daoBean05;
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
  public static BindDummy05DataSource instance() {
    if (instance==null) {
      instance=new BindDummy05DataSource(KriptonLibrary.context);
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
    Logger.info("DDL: %s",Bean05Table.CREATE_TABLE_SQL);
    database.execSQL(Bean05Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean05Table.DROP_TABLE_SQL);
    database.execSQL(Bean05Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean05Table.CREATE_TABLE_SQL);
    database.execSQL(Bean05Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy05DaoFactory> {
  }
}

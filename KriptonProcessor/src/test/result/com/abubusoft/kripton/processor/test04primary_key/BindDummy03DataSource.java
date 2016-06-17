package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.common.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;

public class BindDummy03DataSource extends AbstractDataSource implements BindDummy03DaoFactory {
  private static BindDummy03DataSource instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean03 daoBean03 = new BindDaoBean03();

  protected BindDummy03DataSource(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean03 getDaoBean03() {
    // get current database connection, without increment connection counter
    if (database==null) throw(new KriptonRuntimeException("No database connection is opened"));
    daoBean03.setDatabase(database);
    return daoBean03;
  }

  @Override
  public BindDaoBean03 getDaoBean03(SQLiteDatabase database) {
    daoBean03.setDatabase(database);
    return daoBean03;
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
  public static BindDummy03DataSource instance() {
    if (instance==null) {
      instance=new BindDummy03DataSource(KriptonLibrary.context);
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
    Logger.info("DDL: %s",Bean03Table.CREATE_TABLE_SQL);
    database.execSQL(Bean03Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean03Table.DROP_TABLE_SQL);
    database.execSQL(Bean03Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean03Table.CREATE_TABLE_SQL);
    database.execSQL(Bean03Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy03DaoFactory> {
  }
}

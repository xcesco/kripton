package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
import com.abubusoft.kripton.common.Logger;
import java.lang.Override;
import java.lang.String;

public class BindDummy02Database extends AbstractBindDatabaseHelper implements BindDummy02DaoFactory {
  private static BindDummy02Database instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean02 daoBean02 = new BindDaoBean02();

  protected BindDummy02Database(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean02 getDaoBean02() {
    daoBean02.setDatabase(getWritableDatabase());
    return daoBean02;
  }

  @Override
  public BindDaoBean02 getDaoBean02(SQLiteDatabase database) {
    daoBean02.setDatabase(database);
    return daoBean02;
  }

  /**
   * <p>Allow to execute a transaction. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase database=getWritableDatabase();
    try {
      database.beginTransaction();
      if (transaction!=null && transaction.onExecute(this, database)) {
        database.setTransactionSuccessful();
      }
    } finally {
      database.endTransaction();
    }
  }

  /**
   *
   * instance
   */
  public static BindDummy02Database instance() {
    if (instance==null) {
      instance=new BindDummy02Database(KriptonLibrary.context);
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
    Logger.info("DDL: %s",Bean02Table.CREATE_TABLE_SQL);
    database.execSQL(Bean02Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean02Table.DROP_TABLE_SQL);
    database.execSQL(Bean02Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean02Table.CREATE_TABLE_SQL);
    database.execSQL(Bean02Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy02DaoFactory> {
  }
}

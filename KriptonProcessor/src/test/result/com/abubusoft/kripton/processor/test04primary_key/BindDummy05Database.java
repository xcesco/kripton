package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
import com.abubusoft.kripton.common.Logger;
import java.lang.Override;
import java.lang.String;

public class BindDummy05Database extends AbstractBindDatabaseHelper implements BindDummy05DaoFactory {
  private static BindDummy05Database instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean05 daoBean05 = new BindDaoBean05();

  protected BindDummy05Database(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean05 getDaoBean05() {
    daoBean05.setDatabase(getWritableDatabase());
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
  public static BindDummy05Database instance() {
    if (instance==null) {
      instance=new BindDummy05Database(KriptonLibrary.context);
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

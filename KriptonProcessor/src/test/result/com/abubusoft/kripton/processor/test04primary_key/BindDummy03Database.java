package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
import java.lang.Override;
import java.lang.String;

public class BindDummy03Database extends AbstractBindDatabaseHelper implements BindDummy03DaoFactory {
  private static BindDummy03Database instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean03 daoBean03 = new BindDaoBean03();

  protected BindDummy03Database(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean03 getDaoBean03() {
    daoBean03.setDatabase(getWritableDatabase());
    return daoBean03;
  }

  @Override
  public BindDaoBean03 getDaoBean03(SQLiteDatabase database) {
    daoBean03.setDatabase(database);
    return daoBean03;
  }

  public void execute(BindDummy03DatabaseTransactionExecutor executor) {
    SQLiteDatabase database=getWritableDatabase();
    try {
      database.beginTransaction();
      if (executor!=null && executor.onExecute(this, database)) {
        database.setTransactionSuccessful();
      }
    } catch(Throwable e) {
    } finally {
      database.endTransaction();
    }
  }

  /**
   *
   * instance
   */
  public static BindDummy03Database instance() {
    if (instance==null) {
      instance=new BindDummy03Database(KriptonLibrary.context);
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
    database.execSQL(Bean03Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    database.execSQL(Bean03Table.DROP_TABLE_SQL);

    // generate tables
    database.execSQL(Bean03Table.CREATE_TABLE_SQL);
  }

  public interface BindDummy03DatabaseTransactionExecutor extends TransactionExecutor<BindDummy03DaoFactory> {
  }
}

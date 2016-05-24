package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
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

  public void execute(BindDummy02DatabaseTransactionExecutor executor) {
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
    database.execSQL(Bean02Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    database.execSQL(Bean02Table.DROP_TABLE_SQL);

    // generate tables
    database.execSQL(Bean02Table.CREATE_TABLE_SQL);
  }

  public interface BindDummy02DatabaseTransactionExecutor extends TransactionExecutor<BindDummy02DaoFactory> {
  }
}

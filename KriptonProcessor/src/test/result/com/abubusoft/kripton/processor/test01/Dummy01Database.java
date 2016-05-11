package com.abubusoft.kripton.processor.test01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractDatabaseHelper;
import java.lang.Override;
import java.lang.String;

public class Dummy01Database extends AbstractDatabaseHelper implements Dummy01DaoFactory {
  private static Dummy01Database instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected Dummy01Database(Context context) {
    super(context, name, null, version);
  }

  void execute(Dummy01DatabaseTransactionExecutor executor) {
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
  public static Dummy01Database instance() {
    if (instance==null) {
      instance=new Dummy01Database(KriptonLibrary.context);
    }
    return instance;
  }

  /**
   *
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tablesdatabase.execSQL(Bean01Table.DROP_TABLE_SQL);

    // generate tablesdatabase.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  public interface Dummy01DatabaseTransactionExecutor extends TransactionExecutor<Dummy01DaoFactory> {
  }
}

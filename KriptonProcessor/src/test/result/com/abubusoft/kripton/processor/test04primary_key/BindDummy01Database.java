package com.abubusoft.kripton.processor.test04primary_key;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
import java.lang.Override;
import java.lang.String;

public class BindDummy01Database extends AbstractBindDatabaseHelper implements BindDummy01DaoFactory {
  private static BindDummy01Database instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean01 daoBean01 = new BindDaoBean01();

  protected BindDummy01Database(Context context) {
    super(context, name, null, version);
  }

  public BindDaoBean01 getDaoBean01() {
    daoBean01.setDatabase(getWritableDatabase());
    return daoBean01;
  }

  @Override
  public BindDaoBean01 getDaoBean01(SQLiteDatabase database) {
    daoBean01.setDatabase(database);
    return daoBean01;
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
  public static BindDummy01Database instance() {
    if (instance==null) {
      instance=new BindDummy01Database(KriptonLibrary.context);
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
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    database.execSQL(Bean01Table.DROP_TABLE_SQL);

    // generate tables
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy01DaoFactory> {
  }
}

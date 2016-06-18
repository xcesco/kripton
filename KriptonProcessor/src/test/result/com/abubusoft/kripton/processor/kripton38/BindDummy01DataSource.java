package com.abubusoft.kripton.processor.kripton38;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

public class BindDummy01DataSource extends AbstractDataSource implements BindDummy01DaoFactory {
  private static BindDummy01DataSource instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoBean01 daoBean01 = new BindDaoBean01(this);

  protected BindDummy01DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoBean01 getDaoBean01() {
    return daoBean01;
  }

  /**
   * <p>Allow to execute a transaction. Method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
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
   *
   * instance
   */
  public static BindDummy01DataSource instance() {
    if (instance==null) {
      instance=new BindDummy01DataSource(KriptonLibrary.context);
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
    Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean01Table.DROP_TABLE_SQL);
    database.execSQL(Bean01Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy01DaoFactory> {
  }
}

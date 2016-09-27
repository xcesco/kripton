package com.abubusoft.kripton.processor.kripton48.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

public class BindDummy02DataSource extends AbstractDataSource implements BindDummy02DaoFactory {
  private static BindDummy02DataSource instance;

  public static final String name = "dummy2";

  public static final int version = 1;

  protected BindDaoBean02 daoBean02 = new BindDaoBean02(this);

  protected BindDummy02DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoBean02 getDaoBean02() {
    return daoBean02;
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
  public static BindDummy02DataSource instance() {
    if (instance==null) {
      instance=new BindDummy02DataSource(KriptonLibrary.context());
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
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton48.entities.Bean02Table.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy02DaoFactory> {
  }
}

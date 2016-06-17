package com.abubusoft.kripton.example01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.common.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;

public class BindDummy01DataSource extends AbstractDataSource implements BindDummy01DaoFactory {
  private static BindDummy01DataSource instance;

  public static final String name = "dummy";

  public static final int version = 1;

  protected BindDaoChannel daoChannel = new BindDaoChannel();

  protected BindDummy01DataSource(Context context) {
    super(context, name, null, version);
  }

  public BindDaoChannel getDaoChannel() {
    // get current database connection, without increment connection counter
    if (database==null) throw(new KriptonRuntimeException("No database connection is opened"));
    daoChannel.setDatabase(database);
    return daoChannel;
  }

  @Override
  public BindDaoChannel getDaoChannel(SQLiteDatabase database) {
    daoChannel.setDatabase(database);
    return daoChannel;
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
    Logger.info("DDL: %s",ChannelTable.CREATE_TABLE_SQL);
    database.execSQL(ChannelTable.CREATE_TABLE_SQL);
  }

  /**
   *
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",ChannelTable.DROP_TABLE_SQL);
    database.execSQL(ChannelTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",ChannelTable.CREATE_TABLE_SQL);
    database.execSQL(ChannelTable.CREATE_TABLE_SQL);
  }

  public interface Transaction extends AbstractTransaction<BindDummy01DaoFactory> {
  }
}

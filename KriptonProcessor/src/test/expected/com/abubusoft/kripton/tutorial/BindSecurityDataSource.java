package com.abubusoft.kripton.tutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource SecurityDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SecurityDataSource
 * @see BindSecurityDaoFactory
 * @see DaoUser
 * @see DaoUserImpl
 * @see User
 */
public class BindSecurityDataSource extends AbstractDataSource implements BindSecurityDaoFactory, SecurityDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindSecurityDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "security.db";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected DaoUserImpl daoUser = new DaoUserImpl(this);

  protected BindSecurityDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoUserImpl getDaoUser() {
    return daoUser;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
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
   * instance
   */
  public static BindSecurityDataSource instance() {
    if (instance==null) {
      instance=new BindSecurityDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",UserTable.CREATE_TABLE_SQL);
    database.execSQL(UserTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",UserTable.DROP_TABLE_SQL);
    database.execSQL(UserTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",UserTable.CREATE_TABLE_SQL);
    database.execSQL(UserTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindSecurityDaoFactory> {
  }
}

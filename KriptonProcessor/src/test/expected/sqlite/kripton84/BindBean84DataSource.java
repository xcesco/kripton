package sqlite.kripton84;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Bean84DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84DataSource
 * @see BindBean84DaoFactory
 * @see Bean84Dao
 * @see Bean84DaoImpl
 * @see Bean84
 */
public class BindBean84DataSource extends AbstractDataSource implements BindBean84DaoFactory, Bean84DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean84DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected Bean84DaoImpl bean84Dao = new Bean84DaoImpl(this);

  protected BindBean84DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Bean84DaoImpl getBean84Dao() {
    return bean84Dao;
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
  public static synchronized BindBean84DataSource instance() {
    if (instance==null) {
      instance=new BindBean84DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean84Table.CREATE_TABLE_SQL);
    database.execSQL(Bean84Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean84Table.DROP_TABLE_SQL);
    database.execSQL(Bean84Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean84Table.CREATE_TABLE_SQL);
    database.execSQL(Bean84Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean84DaoFactory> {
  }
}

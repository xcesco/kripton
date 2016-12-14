package commons.kripton86.test7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource DS7DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DS7DataSource
 * @see BindDS7DaoFactory
 * @see Dao7
 * @see Dao7Impl
 * @see Bean7
 */
public class BindDS7DataSource extends AbstractDataSource implements BindDS7DaoFactory, DS7DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDS7DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "test.db";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected Dao7Impl dao7 = new Dao7Impl(this);

  protected BindDS7DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Dao7Impl getDao7() {
    return dao7;
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
  public static synchronized BindDS7DataSource instance() {
    if (instance==null) {
      instance=new BindDS7DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean7Table.CREATE_TABLE_SQL);
    database.execSQL(Bean7Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean7Table.DROP_TABLE_SQL);
    database.execSQL(Bean7Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean7Table.CREATE_TABLE_SQL);
    database.execSQL(Bean7Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDS7DaoFactory> {
  }
}

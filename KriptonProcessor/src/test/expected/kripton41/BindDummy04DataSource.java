package kripton41;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy04DataSource
 * @see BindDummy04DaoFactory
 * @see DaoBeanInsertOK
 * @see DaoBeanInsertOKImpl
 * @see Bean01
 */
public class BindDummy04DataSource extends AbstractDataSource implements BindDummy04DaoFactory, Dummy04DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy04DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy1";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected DaoBeanInsertOKImpl daoBeanInsertOK = new DaoBeanInsertOKImpl(this);

  protected BindDummy04DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoBeanInsertOKImpl getDaoBeanInsertOK() {
    return daoBeanInsertOK;
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
  public static BindDummy04DataSource instance() {
    if (instance==null) {
      instance=new BindDummy04DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean01Table.CREATE_TABLE_SQL);
    database.execSQL(Bean01Table.CREATE_TABLE_SQL);
  }

  /**
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

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy04DaoFactory> {
  }
}

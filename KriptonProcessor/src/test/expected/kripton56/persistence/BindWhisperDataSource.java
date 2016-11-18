package kripton56.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;
import kripton56.entities.MessageEntityTable;

/**
 * <p>
 * Represents implementation of datasource WhisperDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see WhisperDataSource
 * @see BindWhisperDaoFactory
 * @see DaoMessage
 * @see DaoMessageImpl
 * @see kripton56.entities.MessageEntity
 */
public class BindWhisperDataSource extends AbstractDataSource implements BindWhisperDaoFactory, WhisperDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindWhisperDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "whisper";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected DaoMessageImpl daoMessage = new DaoMessageImpl(this);

  protected BindWhisperDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public DaoMessageImpl getDaoMessage() {
    return daoMessage;
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
  public static BindWhisperDataSource instance() {
    if (instance==null) {
      instance=new BindWhisperDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",MessageEntityTable.CREATE_TABLE_SQL);
    database.execSQL(MessageEntityTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",MessageEntityTable.DROP_TABLE_SQL);
    database.execSQL(MessageEntityTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",MessageEntityTable.CREATE_TABLE_SQL);
    database.execSQL(MessageEntityTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindWhisperDaoFactory> {
  }
}

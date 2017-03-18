package sqlite.kripton111.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
import sqlite.kripton111.model.CountryTable;
import sqlite.kripton111.model.PhoneNumberTable;
import sqlite.kripton111.model.PrefixConfigTable;

/**
 * <p>
 * Rapresents implementation of datasource XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see BindXenoDaoFactory
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see sqlite.kripton111.model.PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see sqlite.kripton111.model.PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see sqlite.kripton111.model.Country
 */
public class BindXenoDataSource extends AbstractDataSource implements BindXenoDaoFactory, XenoDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindXenoDataSource instance = new BindXenoDataSource();

  /**
   * <p>dao instance</p>
   */
  protected PhoneDaoImpl phoneDao = new PhoneDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected PrefixConfigDaoImpl prefixConfigDao = new PrefixConfigDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected CountryDaoImpl countryDao = new CountryDaoImpl(this);

  protected BindXenoDataSource() {
    super("xeno.db", 1);
  }

  @Override
  public PhoneDaoImpl getPhoneDao() {
    return phoneDao;
  }

  @Override
  public PrefixConfigDaoImpl getPrefixConfigDao() {
    return prefixConfigDao;
  }

  @Override
  public CountryDaoImpl getCountryDao() {
    return countryDao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase connection=openWritableDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (transaction!=null) transaction.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      close();
    }
  }

  /**
   * instance
   */
  public static BindXenoDataSource instance() {
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource open() {
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource openReadOnly() {
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
    database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
    database.execSQL(CountryTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
    database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, oldVersion, newVersion, true);
    } else {
      // drop tables
      Logger.info("DDL: %s",PhoneNumberTable.DROP_TABLE_SQL);
      database.execSQL(PhoneNumberTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",CountryTable.DROP_TABLE_SQL);
      database.execSQL(CountryTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",PrefixConfigTable.DROP_TABLE_SQL);
      database.execSQL(PrefixConfigTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
      database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
      database.execSQL(CountryTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
      database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindXenoDaoFactory> {
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}

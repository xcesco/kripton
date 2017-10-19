package sqlite.feature.many2many.case4.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.many2many.case4.model.CountryTable;
import sqlite.feature.many2many.case4.model.PersonTable;
import sqlite.feature.many2many.case4.model.PhoneNumberTable;
import sqlite.feature.many2many.case4.model.PrefixConfigTable;

/**
 * <p>
 * Represents implementation of datasource XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see BindXenoDaoFactory
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public class BindXenoDataSource extends AbstractDataSource implements BindXenoDaoFactory, XenoDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindXenoDataSource instance;

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

  /**
   * <p>dao instance</p>
   */
  protected Person2PhoneDaoImpl person2PhoneDao = new Person2PhoneDaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected PersonDaoImpl personDao = new PersonDaoImpl(this);

  protected BindXenoDataSource(DataSourceOptions options) {
    super("xeno.db", 1, options);
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

  @Override
  public Person2PhoneDaoImpl getPerson2PhoneDao() {
    return person2PhoneDao;
  }

  @Override
  public PersonDaoImpl getPersonDao() {
    return personDao;
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
  public static synchronized BindXenoDataSource instance() {
    if (instance==null) {
      instance=new BindXenoDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource open() {
    BindXenoDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindXenoDataSource openReadOnly() {
    BindXenoDataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
    database.execSQL(CountryTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
    database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
    database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
    database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      Logger.info("DDL: %s",CountryTable.CREATE_TABLE_SQL);
      database.execSQL(CountryTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PrefixConfigTable.CREATE_TABLE_SQL);
      database.execSQL(PrefixConfigTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PhoneNumberTable.CREATE_TABLE_SQL);
      database.execSQL(PhoneNumberTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PersonPhoneNumberTable.CREATE_TABLE_SQL);
      database.execSQL(PersonPhoneNumberTable.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindXenoDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindXenoDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
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

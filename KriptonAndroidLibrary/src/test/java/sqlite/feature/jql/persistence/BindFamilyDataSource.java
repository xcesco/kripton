package sqlite.feature.jql.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
import sqlite.feature.jql.entities.ChildTable;
import sqlite.feature.jql.entities.PersonTable;

/**
 * <p>
 * Rapresents implementation of datasource FamilyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FamilyDataSource
 * @see BindFamilyDaoFactory
 * @see DaoChild
 * @see DaoChildImpl
 * @see sqlite.feature.jql.entities.Child
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see sqlite.feature.jql.entities.Person
 */
public class BindFamilyDataSource extends AbstractDataSource implements BindFamilyDaoFactory, FamilyDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindFamilyDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected DaoChildImpl daoChild = new DaoChildImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoPersonImpl daoPerson = new DaoPersonImpl(this);

  protected BindFamilyDataSource(DataSourceOptions options) {
    super("familiy", 1, options);
  }

  @Override
  public DaoChildImpl getDaoChild() {
    return daoChild;
  }

  @Override
  public DaoPersonImpl getDaoPerson() {
    return daoPerson;
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
  public static BindFamilyDataSource instance() {
    if (instance==null) {
      instance=new BindFamilyDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindFamilyDataSource open() {
    if (instance==null) {
      instance=new BindFamilyDataSource(null);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindFamilyDataSource openReadOnly() {
    if (instance==null) {
      instance=new BindFamilyDataSource(null);
    }
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",ChildTable.CREATE_TABLE_SQL);
    database.execSQL(ChildTable.CREATE_TABLE_SQL);
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
      Logger.info("DDL: %s",ChildTable.DROP_TABLE_SQL);
      database.execSQL(ChildTable.DROP_TABLE_SQL);
      Logger.info("DDL: %s",PersonTable.DROP_TABLE_SQL);
      database.execSQL(PersonTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",ChildTable.CREATE_TABLE_SQL);
      database.execSQL(ChildTable.CREATE_TABLE_SQL);
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
  public static FamilyDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindFamilyDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindFamilyDaoFactory> {
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

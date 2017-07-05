package sqlite.featJQL.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
import sqlite.featJQL.entities.ChildTable;
import sqlite.featJQL.entities.PersonTable;

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
 * @see sqlite.featJQL.entities.Child
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see sqlite.featJQL.entities.Person
 */
public class BindFamilyDataSource extends AbstractDataSource implements BindFamilyDaoFactory, FamilyDataSource {
  /**
   * <p>datasource singleton</p>
   */
  private static BindFamilyDataSource instance = new BindFamilyDataSource();

  /**
   * <p>dao instance</p>
   */
  protected DaoChildImpl daoChild = new DaoChildImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected DaoPersonImpl daoPerson = new DaoPersonImpl(this);

  protected BindFamilyDataSource() {
    super("familiy", 1);
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
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindFamilyDataSource open() {
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindFamilyDataSource openReadOnly() {
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

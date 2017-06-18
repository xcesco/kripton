package sqlite.contentprovider.kripton35.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import sqlite.contentprovider.kripton35.entities.PersonTable;

/**
 * <p>
 * Rapresents implementation of datasource PersonDataSource. This class expose
 * database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see BindPersonDaoFactory
 * @see PersonDAO
 * @see PersonDAOImpl
 * @see sqlite.contentprovider.kripton35.entities.Person
 */
public class BindPersonDataSource extends AbstractDataSource implements BindPersonDaoFactory, PersonDataSource {
	/**
	 * <p>
	 * datasource singleton
	 * </p>
	 */
	private static BindPersonDataSource instance = new BindPersonDataSource();

	/**
	 * <p>
	 * dao instance
	 * </p>
	 */
	protected PersonDAOImpl personDAO = new PersonDAOImpl(this);

	protected BindPersonDataSource() {
		super("whisper", 1);

		

	}

	public static boolean exportDB(Context context) {
	    String DATABASE_NAME = "whisper";
	    String DATABASE_DESTINATION = "whisper";
	    String databasePath = context.getDatabasePath(DATABASE_NAME).getPath();
	    Logger.info("************"+databasePath);
	    String inFileName = databasePath;
	    try {
	        File dbFile = new File(inFileName);
	        FileInputStream fis = new FileInputStream(dbFile);

	        //String outFileName = Environment.getExternalStorageDirectory() + "/" + DATABASE_DESTINATION;
	        String outFileName=DATABASE_DESTINATION;
	        Logger.info("************"+outFileName);
	        OutputStream output = new FileOutputStream(outFileName);

	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = fis.read(buffer)) > 0) {
	            output.write(buffer, 0, length);
	        }
	        //Close the streams
	        output.flush();
	        output.close();
	        fis.close();
	        return true;
	    } catch (Exception e) {
	        return true;
	      } 
	}

	@Override
	public PersonDAOImpl getPersonDAO() {
		return personDAO;
	}

	/**
	 * <p>
	 * Executes a transaction. This method <strong>is thread safe</strong> to
	 * avoid concurrent problems. Thedrawback is only one transaction at time
	 * can be executed. The database will be open in write mode.
	 * </p>
	 *
	 * @param transaction
	 *            transaction to execute
	 */
	public void execute(Transaction transaction) {
		SQLiteDatabase connection = openWritableDatabase();
		try {
			connection.beginTransaction();
			if (transaction != null && transaction.onExecute(this)) {
				connection.setTransactionSuccessful();
			}
		} catch (Throwable e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
			if (transaction != null)
				transaction.onError(e);
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
	public static BindPersonDataSource instance() {
		return instance;
	}

	/**
	 * Retrieve data source instance and open it.
	 * 
	 * @return opened dataSource instance.
	 */
	public static BindPersonDataSource open() {
		instance.openWritableDatabase();
		return instance;
	}

	/**
	 * Retrieve data source instance and open it in read only mode.
	 * 
	 * @return opened dataSource instance.
	 */
	public static BindPersonDataSource openReadOnly() {
		instance.openReadOnlyDatabase();
		return instance;
	}

	/**
	 * onCreate
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		// generate tables
		Logger.info("DDL: %s", PersonTable.CREATE_TABLE_SQL);
		database.execSQL(PersonTable.CREATE_TABLE_SQL);
		if (options.databaseLifecycleHandler != null) {
			options.databaseLifecycleHandler.onCreate(database);
		}
		
		Cursor c=database.rawQuery("SELECT * FROM sqlite_master",null);
		while (c.moveToNext()) {
			
			for (int i=0; i<c.getColumnCount();i++) {
				System.out.print(String.format("[%s (%s) %s] ",c.getColumnName(i), c.getType(i), c.getString(i)));	
			}			
			
			System.out.println();
		}
		
		String dbPath = "whisper";// "/data/data/com.company.project/databases/project.db";
		String outputPath = "databaseDump.txt";

		Process proc;
		try {
			proc = Runtime.getRuntime().exec("sqlite3 " + dbPath + " '.dump' ");
			proc.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exportDB(KriptonLibrary.context());
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
			Logger.info("DDL: %s", PersonTable.DROP_TABLE_SQL);
			database.execSQL(PersonTable.DROP_TABLE_SQL);

			// generate tables
			Logger.info("DDL: %s", PersonTable.CREATE_TABLE_SQL);
			database.execSQL(PersonTable.CREATE_TABLE_SQL);
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
	public interface Transaction extends AbstractTransaction<BindPersonDaoFactory> {
	}

	/**
	 * Simple class implements interface to define transactions
	 */
	public abstract static class SimpleTransaction implements Transaction {
		@Override
		public void onError(Throwable e) {
			throw (new KriptonRuntimeException(e));
		}
	}
}

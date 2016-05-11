/**
 * 
 */
package com.abubusoft.kripton.android.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author xcesco
 *
 *
 * @since 10/mag/2016
 */
public abstract class AbstractDatabaseHelper extends SQLiteOpenHelper implements AutoCloseable {

	public AbstractDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public AbstractDatabaseHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}
	
	public interface TransactionExecutor<E extends DaoFactory>
	{
		/**
		 * Execute 
		 * @param database
		 * @return
		 * 		true to commit, false to rollback
		 */
		boolean onExecute(E daoFactory, SQLiteDatabase database);
		
		void onError(Throwable e);
	}
	
	//public interface DummyExecutor extends DatabaseExecutor
	
	/**
	 * Execute
	 * @param execute
	 */
	//public void execute(E execute)
	{
		/*SQLiteDatabase database = getWritableDatabase();
		
		try {
			database.beginTransaction();
			
			if (execute.onExecute(database))
			{
				database.setTransactionSuccessful();
			}
		} catch(Throwable e)
		{
			execute.onError(e);
		} finally
		{
			database.endTransaction();
		}*/
	}		
	

}

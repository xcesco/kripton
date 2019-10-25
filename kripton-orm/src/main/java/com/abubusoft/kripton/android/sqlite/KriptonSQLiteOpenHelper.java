package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.sqlite.database.KriptonSQLiteDatabaseWrapperImpl;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

public class KriptonSQLiteOpenHelper implements SupportSQLiteOpenHelper {
	
	final KriptonSQLiteDatabaseWrapperImpl[] dbRef = new KriptonSQLiteDatabaseWrapperImpl[1];

	private SQLiteOpenHelper delegate;
	
	public KriptonSQLiteOpenHelper(Configuration configuration) {
		delegate = new SQLiteOpenHelper(configuration.context, configuration.name, null, configuration.callback.version,
				new DatabaseErrorHandler() {
					
					@Override
					public void onCorruption(SQLiteDatabase db) {
						configuration.callback.onCorruption(getWrappedDb(db));					
					}
				}) {

			@Override
			public void onConfigure(SQLiteDatabase database) {				
				configuration.callback.onConfigure(getWrappedDb(database));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				configuration.callback.onCreate(getWrappedDb(database));
			}

			@Override
			public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				configuration.callback.onDowngrade(getWrappedDb(database), oldVersion, newVersion);
			}

			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				configuration.callback.onUpgrade(getWrappedDb(database), oldVersion, newVersion);
			}
		};
	}

	/*public KriptonSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler, AbstractDataSource dataSource) {
		delegate = new SQLiteOpenHelper(context, name, null, version,
				new DatabaseErrorHandler() {
					
					@Override
					public void onCorruption(SQLiteDatabase db) {
						dataSource.onCorruption(getWrappedDb(db));						
					}
				}) {

			@Override
			public void onConfigure(SQLiteDatabase database) {				
				dataSource.onConfigure(getWrappedDb(database));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				dataSource.onCreate(getWrappedDb(database));
			}

			@Override
			public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				dataSource.onDowngrade(getWrappedDb(database), oldVersion, newVersion);
			}

			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				dataSource.onUpgrade(getWrappedDb(database), oldVersion, newVersion);
			}
		};
	}*/

	/*KriptonSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version,
			SQLiteLifeCycleListener listener) {
		delegate = new SQLiteOpenHelper(context, name, null, version, new DatabaseErrorHandler() {
			
			@Override
			public void onCorruption(SQLiteDatabase database) {
				listener.onCorruption(getWrappedDb(database));
				
			}
		}) {

			@Override
			public void onConfigure(SQLiteDatabase database) {
				listener.onConfigure(getWrappedDb(database));
			}

			@Override
			public void onCreate(SQLiteDatabase database) {
				listener.onCreate(getWrappedDb(database));
			}

			@Override
			public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				listener.onDowngrade(getWrappedDb(database), oldVersion, newVersion);
			}

			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
				listener.onUpgrade(getWrappedDb(database), oldVersion, newVersion);
			}
		};
	}*/



	@Override
	public String getDatabaseName() {
		return delegate.getDatabaseName();
	}

	@Override
	public void setWriteAheadLoggingEnabled(boolean enabled) {
		delegate.setWriteAheadLoggingEnabled(enabled);

	}

	@Override
	public SupportSQLiteDatabase getWritableDatabase() {
		return getWrappedDb(delegate.getWritableDatabase());
	}

	@Override
	public SupportSQLiteDatabase getReadableDatabase() {
		return getWrappedDb(delegate.getReadableDatabase());
	}

	@Override
	public void close() {
		delegate.close();

	}

	synchronized SupportSQLiteDatabase getWrappedDb(SQLiteDatabase db) {
		KriptonSQLiteDatabaseWrapperImpl wrappedDb = dbRef[0];

		if (wrappedDb == null) {
			wrappedDb = new KriptonSQLiteDatabaseWrapperImpl(db);
			dbRef[0] = wrappedDb;
		}

		wrappedDb.update(db);

		return (dbRef[0]);
	}

}

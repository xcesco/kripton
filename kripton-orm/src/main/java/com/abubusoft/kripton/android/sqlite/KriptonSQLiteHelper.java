package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.sqlite.database.KriptonSQLiteDatabaseWrapperImpl;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

public class KriptonSQLiteHelper implements SupportSQLiteOpenHelper {
	
	final KriptonSQLiteDatabaseWrapperImpl[] dbRef = new KriptonSQLiteDatabaseWrapperImpl[1];

	private SQLiteOpenHelper delegate;
	
	public KriptonSQLiteHelper(Configuration configuration) {
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

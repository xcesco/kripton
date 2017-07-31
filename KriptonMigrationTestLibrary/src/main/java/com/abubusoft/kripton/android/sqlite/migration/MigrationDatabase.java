package com.abubusoft.kripton.android.sqlite.migration;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MigrationDatabase extends SQLiteOpenHelper {

	private MigrationDatabaseListener listener;

	public MigrationDatabase(Context context, CursorFactory factory, int version, DatabaseErrorHandler errorHandler, MigrationDatabaseListener listener) {
		super(context, "migration-test", factory, version, errorHandler);
		this.listener=listener;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		listener.onCreate(db);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		listener.onUpgrade(db, oldVersion, newVersion);		
	}
	
	

}

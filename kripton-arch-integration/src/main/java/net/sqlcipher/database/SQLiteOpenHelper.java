package net.sqlcipher.database;

import android.content.Context;
import net.sqlcipher.DatabaseErrorHandler;

public class SQLiteOpenHelper {

	public SQLiteOpenHelper(Context context, String name, Object object, int version,
			SQLiteDatabaseHook sqLiteDatabaseHook, DatabaseErrorHandler databaseErrorHandler) {
		// TODO Auto-generated constructor stub
	}

	public SQLiteDatabase getWritableDatabase(byte[] passphrase) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public void setWriteAheadLoggingEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	public String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		
	}

	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public void onConfigure(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

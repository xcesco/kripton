package com.abubusoft.kripton.android.sqlite.migration;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.database.sqlite.SQLiteDatabase;

public class MigrationHelper {

	public static SQLiteDatabase createDatabase(String name, int version, String schemaDefinitionFile) {
		MigrationDatabase helper=new MigrationDatabase(KriptonLibrary.context(), name, null, version, null);
		
		return helper.getWritableDatabase();
	}
	
	public static void migration(Migration migration) {
		
	}
}

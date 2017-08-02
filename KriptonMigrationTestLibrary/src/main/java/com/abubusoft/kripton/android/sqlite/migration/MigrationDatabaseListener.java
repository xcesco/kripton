package com.abubusoft.kripton.android.sqlite.migration;

import android.database.sqlite.SQLiteDatabase;

public interface MigrationDatabaseListener {
	void onCreate(SQLiteDatabase db);

	void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}

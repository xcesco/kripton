package com.abubusoft.kripton.android.orm;

import android.database.sqlite.SQLiteDatabase;

public interface SQLiteUpdateListener {
	void onCreate(SQLiteDatabase db);

	void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}

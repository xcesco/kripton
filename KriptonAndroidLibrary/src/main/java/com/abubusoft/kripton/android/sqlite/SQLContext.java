package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public interface SQLContext {
	KriptonContentValues contentValuesForUpdate();

	KriptonContentValues contentValues();

	KriptonContentValues contentValues(ContentValues values);

	StringBuilder sqlBuilder();
	
	boolean isLogEnabled();
	
	SQLiteDatabase database();
}

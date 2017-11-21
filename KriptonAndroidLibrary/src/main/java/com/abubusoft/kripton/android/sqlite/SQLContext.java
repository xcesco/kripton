package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public interface SQLContext {
	KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement);

	KriptonContentValues contentValues(SQLiteStatement compiledStatement);

	KriptonContentValues contentValuesForContentProvider(ContentValues values);

	StringBuilder sqlBuilder();
	
	boolean isLogEnabled();
	
	SQLiteDatabase database();
}

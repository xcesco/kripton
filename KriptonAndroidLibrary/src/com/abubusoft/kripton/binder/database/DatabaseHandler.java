package com.abubusoft.kripton.binder.database;

import com.abubusoft.kripton.android.DatabaseColumnSet;

public interface DatabaseHandler {

	void init();

	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	DatabaseColumnSet createColumnSet(DatabaseTable table, String fields);

}

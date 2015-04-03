package com.abubusoft.kripton.binder.database;


public interface DatabaseHandler {

	void init();

	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	DatabaseColumnSet createColumnSet(DatabaseTable table, String fieldsPart, String wherePart, String orderPart);

}

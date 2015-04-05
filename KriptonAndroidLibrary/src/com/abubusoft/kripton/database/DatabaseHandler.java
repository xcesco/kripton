package com.abubusoft.kripton.database;


public interface DatabaseHandler {

	void init();

	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	Query createQuery(DatabaseTable table, QueryOptions options);

}

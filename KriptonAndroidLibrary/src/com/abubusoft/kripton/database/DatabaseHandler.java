package com.abubusoft.kripton.database;


public interface DatabaseHandler<Q extends Query, I extends Insert> {

	void init();

	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	Q createQuery(DatabaseTable table, QueryOptions options);

	I createInsert(DatabaseTable table, InsertOptions options);

}

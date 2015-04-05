package com.abubusoft.kripton.database;


public interface DatabaseHandler<Q extends Query> {

	void init();

	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	Q createQuery(DatabaseTable table, QueryOptions options);

	Insert insert(DatabaseTable table, InsertOptions options);

}

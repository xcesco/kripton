package com.abubusoft.kripton.binder.database;


public interface DatabaseHandler<I extends Insert, Q extends Query, U extends Update, D extends Delete> {

	void init();
	
	String getColumnType(Class<?> fieldType);

	String createTableSQL(DatabaseTable table);

	String dropTableSQL(DatabaseTable table);
	
	Q createQuery(DatabaseTable table, QueryOptions options);
	
	Q getQuery(DatabaseTable table, String name);

	I  createInsert(DatabaseTable table, InsertOptions options);
	
	I getInsert(DatabaseTable table, String name);
	
	U createUpdate(DatabaseTable table, UpdateOptions options);
	
	U getUpdate(DatabaseTable table, String name);
	
	D createDelete(DatabaseTable table, DeleteOptions options);
	
	D getDelete(DatabaseTable table, String name);
}

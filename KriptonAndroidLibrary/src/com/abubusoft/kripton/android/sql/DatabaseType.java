package com.abubusoft.kripton.android.sql;


public enum DatabaseType {
	
	SQL_LITE(AdapterSQLite.class, DatabaseSQLite.class);
	
	public DatabaseAdapter adapter;

	private <A extends DatabaseAdapter, D extends AbstractDatabase> DatabaseType(Class<A> adapterValue, Class<D> databaseValue)
	{
		try {
			adapter=adapterValue.newInstance();
			adapter.setDatabase(databaseValue.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}

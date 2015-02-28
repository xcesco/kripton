package com.abubusoft.kripton.android.sql;


public class DatabaseAdapterOptions {
	
	/**
	 * @return
	 */
	public static DatabaseAdapterOptions build()
	{
		return (new DatabaseAdapterOptions()).type(DatabaseType.SQL_LITE);
	}
	
	public DatabaseType type;
	
	private DatabaseAdapterOptions()
	{
		
	}
	
	/**
	 * @param value
	 * @return
	 */
	public DatabaseAdapterOptions type(DatabaseType value)
	{
		type=value;
		
		return this;
	}
}

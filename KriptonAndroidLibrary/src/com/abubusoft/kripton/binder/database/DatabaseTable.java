package com.abubusoft.kripton.binder.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.schema.MappingSchema;

/**
 * Database table
 * 
 * @author xcesco
 *
 */
public class DatabaseTable {
	
	public Class<?> clazz;

	public ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

	public LinkedHashMap<String, DatabaseColumn> field2column = new LinkedHashMap<String, DatabaseColumn>();

	public LinkedHashMap<String, Insert> inserts = new LinkedHashMap<String, Insert>();

	public String name;

	public LinkedHashMap<String, Query> queries = new LinkedHashMap<String, Query>();

	public MappingSchema schema;

	public LinkedHashMap<String, Update> updates = new LinkedHashMap<String, Update>();

	public DatabaseColumn primaryKey;

	public LinkedHashMap<String, Delete> deletes = new LinkedHashMap<String, Delete>();
	
	//protected boolean parsed=false;
	
	public Query lastQuery;
	
	public Insert lastInsert;
	
	public Update lastUpdate;
	
	public Delete lastDelete;

	public LinkedHashMap<DatabaseColumn, DatabaseColumn> foreignKeys=new LinkedHashMap<DatabaseColumn, DatabaseColumn>();
	
}

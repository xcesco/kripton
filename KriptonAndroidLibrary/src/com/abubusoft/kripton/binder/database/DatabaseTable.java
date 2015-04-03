package com.abubusoft.kripton.binder.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.schema.MappingSchema;

/**
 * Database table 
 * @author xcesco
 *
 */
public class DatabaseTable {
	 
	public LinkedHashMap<String, DatabaseColumnSet> columnsSet=new LinkedHashMap<String, DatabaseColumnSet>();
	
	public ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();
	
	public LinkedHashMap<String, DatabaseColumn> field2column=new LinkedHashMap<String, DatabaseColumn>();
	 
	public String name;
	
	public MappingSchema schema;

}

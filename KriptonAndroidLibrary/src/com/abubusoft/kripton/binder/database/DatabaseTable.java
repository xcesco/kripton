package com.abubusoft.kripton.binder.database;

import java.util.ArrayList;

import com.abubusoft.kripton.binder.schema.MappingSchema;

public class DatabaseTable {

	public ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();
	
	public String name;
	
	public MappingSchema schema;

}

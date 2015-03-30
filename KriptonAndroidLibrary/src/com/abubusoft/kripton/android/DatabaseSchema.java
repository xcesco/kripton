package com.abubusoft.kripton.android;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.common.LRUCache;

public class DatabaseSchema {
	
	interface TableTask {
		String onTable(DatabaseTable item);
	}

	private static final int CACHE_SIZE = 100;

	// use LRU cache to limit memory consumption.
	private static Map<String, DatabaseSchema> schemaCache = Collections.synchronizedMap(new LRUCache<String, DatabaseSchema>(CACHE_SIZE));
	
	// use LRU cache to limit memory consumption.
	public LinkedHashMap<String, DatabaseTable> tables = new LinkedHashMap<String, DatabaseTable>();
	
	public LinkedHashMap<String, MappingSchema> schemas = new LinkedHashMap<String, MappingSchema>();

	private DatabaseHandler handler;

	private LinkedHashMap<MappingSchema, DatabaseTable> schema2Table=new LinkedHashMap<>();


	protected DatabaseSchema(DatabaseType type,DatabaseSchemaOptions options) {
		buildTables(type,options);
	}

	protected void buildTables(DatabaseType type, DatabaseSchemaOptions options) {
		handler = type.handler;
		handler.init();
		MappingSchema[] array = new MappingSchema[options.mappingSchemaSet.size()];
		array = options.mappingSchemaSet.toArray(array);
		DatabaseTable table;
		DatabaseColumn column;
		String key;
		for (MappingSchema item : array) {
			key=options.tablePrefix+options.nameConverter.convertName(item.tableInfo.name);
			
			table=new DatabaseTable();
			table.name=key;
			table.schema=item;
			for (ElementSchema element: item.getField2SchemaMapping().values())
			{
				column=new DatabaseColumn();
				column.name=options.nameConverter.convertName(element.getColumnInfo().name);
				column.feature=element.getColumnInfo().feature;
				column.schema=element;
				column.type=handler.getColumnType(element.getFieldType());
				
				table.columns.add(column);
				table.field2column.put(element.getName(), column);
			}
			
			tables.put(key, table);
			schema2Table.put(item, table);
		}
	}
	
	public void queryFields(Class<?> clazz, String values)
	{
		MappingSchema schema=MappingSchema.fromClass(clazz);
			
		handler.createColumnSet(schema2Table.get(schema), values);
	}
	
	public String[] createTablesSQL()
	{
		return forEachTable(new TableTask() {			
			@Override
			public String onTable(DatabaseTable item) {
				return handler.createTableSQL(item);
			}
		});
	}
	
	public String[] dropTablesSQL()
	{
		return forEachTable(new TableTask() {			
			@Override
			public String onTable(DatabaseTable item) {
				return handler.dropTableSQL(item);
			}
		});
	}
	
	private String[] forEachTable(TableTask task)
	{
		String[] result=new String[tables.size()];
		int i=0;
		
		for(DatabaseTable item: tables.values())
		{
			result[i]=task.onTable(item);
			i++;
		}
		
		return result;
	}
	

	public static DatabaseSchema build(String name, DatabaseType type, DatabaseSchemaOptions options) {
		if (schemaCache.containsKey(name)) {
			return schemaCache.get(name);
		} else {
			DatabaseSchema dbSchema = new DatabaseSchema(type, options);
			schemaCache.put(name, dbSchema);
			return dbSchema;
		}
	}
}

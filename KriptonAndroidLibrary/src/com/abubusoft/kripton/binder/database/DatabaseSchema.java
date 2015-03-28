package com.abubusoft.kripton.binder.database;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.common.LRUCache;

public class DatabaseSchema {

	private static final int CACHE_SIZE = 100;

	// use LRU cache to limit memory consumption.
	private static Map<String, DatabaseSchema> schemaCache = Collections.synchronizedMap(new LRUCache<String, DatabaseSchema>(CACHE_SIZE));
	
	// use LRU cache to limit memory consumption.
	public LinkedHashMap<String, MappingSchema> tables = new LinkedHashMap<String, MappingSchema>();


	protected DatabaseSchema(DatabaseSchemaOptions options) {
		buildTables(options);
	}

	protected void buildTables(DatabaseSchemaOptions options) {
		MappingSchema[] array = new MappingSchema[options.mappingSchemaSet.size()];
		array = options.mappingSchemaSet.toArray(array);
		
		String key;
		for (MappingSchema item : array) {
			key=options.tablePrefix+options.nameConverter.convertName(item.tableInfo.name);
			
			tables.put(key, item);
			
			for (ElementSchema element: item.getField2SchemaMapping().values())
			{
				//columnInfo=element.getColumnInfo();
			}
		}
	}
	

	public static DatabaseSchema getFrom(DatabaseSchemaOptions options) {
		if (schemaCache.containsKey(options.databaseName)) {
			return schemaCache.get(options.databaseName);
		} else {
			DatabaseSchema dbSchema = new DatabaseSchema(options);
			schemaCache.put(options.databaseName, dbSchema);
			return dbSchema;
		}
	}
}

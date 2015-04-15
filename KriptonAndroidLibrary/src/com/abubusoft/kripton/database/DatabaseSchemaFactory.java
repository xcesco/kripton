package com.abubusoft.kripton.database;

import java.util.Collections;
import java.util.Map;

import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.exception.MappingException;

public class DatabaseSchemaFactory {

	protected static final int CACHE_SIZE = 100;

	/**
	 * Use LRU cache to limit memory consumption.
	 */
	@SuppressWarnings("rawtypes")
	protected static Map<String, AbstractDatabaseSchema> schemaCache = Collections.synchronizedMap(new LRUCache<String, AbstractDatabaseSchema>(
			CACHE_SIZE));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E extends AbstractDatabaseSchema> E create(String name, Class<E> schemaClazz, DatabaseSchemaOptions options)  {
		if (schemaCache.containsKey(name)) {
			return (E) schemaCache.get(name);
		} else {
			E dbSchema=null;
			try {
				dbSchema = schemaClazz.newInstance();				
				dbSchema.build(options);
				schemaCache.put(name, dbSchema);
			} catch (Exception e) {
				throw (new MappingException("Error during generation of DatabaseSchema "+e.getMessage()));
			}

			return (E) dbSchema;
		}
	}
}

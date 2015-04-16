package com.abubusoft.kripton;

import java.util.Collections;
import java.util.Map;

import com.abubusoft.kripton.binder.database.DatabaseSchema;
import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.exception.MappingException;

public class DatabaseSchemaFactory {

	protected static final int CACHE_SIZE = 100;

	/**
	 * Use LRU cache to limit memory consumption.
	 */
	@SuppressWarnings("rawtypes")
	protected static Map<String, DatabaseSchema> schemaCache = Collections.synchronizedMap(new LRUCache<String, DatabaseSchema>(
			CACHE_SIZE));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E extends DatabaseSchema> E create(String name, Class<E> schemaClazz, DatabaseSchemaOptions options)  {
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

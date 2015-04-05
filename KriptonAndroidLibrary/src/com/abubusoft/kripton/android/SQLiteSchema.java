/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.database.DatabaseHandler;
import com.abubusoft.kripton.database.DatabaseSchema;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends DatabaseSchema {

	protected SQLiteSchema(DatabaseHandler handler, DatabaseSchemaOptions options) {
		super(handler, options);
	}
	
	public static DatabaseSchema build(String name, DatabaseSchemaOptions options) {
		if (schemaCache.containsKey(name)) {
			return schemaCache.get(name);
		} else {
			DatabaseSchema dbSchema = new SQLiteSchema(new SQLiteHandler(), options);
			schemaCache.put(name, dbSchema);
			return dbSchema;
		}
	}
	
	

}

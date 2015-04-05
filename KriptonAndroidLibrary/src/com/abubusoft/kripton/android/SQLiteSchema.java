/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.database.DatabaseHandler;
import com.abubusoft.kripton.database.AbstractDatabaseSchema;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends AbstractDatabaseSchema<SQLiteQuery> {
	
	public SQLiteSchema(DatabaseHandler<SQLiteQuery> handler, DatabaseSchemaOptions options) {
		super(handler, options);
	}

	public static SQLiteSchema build(String name, DatabaseSchemaOptions options) {
		if (schemaCache.containsKey(name)) {
			return (SQLiteSchema) schemaCache.get(name);
		} else {
			SQLiteSchema dbSchema = new SQLiteSchema(new SQLiteHandler(), options);
			schemaCache.put(name, dbSchema);
			return dbSchema;
		}
	}

}

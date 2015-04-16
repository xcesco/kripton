/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends DatabaseSchema<SQLiteHandler, SQLiteInsert, SQLiteQuery, SQLiteUpdate, SQLiteDelete> {
	
	static final long INVALID_ID = -1;

	@Override
	protected DatabaseColumn onDefineColumn(ElementSchema element, DatabaseSchemaOptions options) {
		DatabaseColumn column = new DatabaseColumn(); 

		if (element.getColumnInfo().feature == ColumnType.PRIMARY_KEY) {
			element.getColumnInfo().name = "_id";
		}

		column.name = options.nameConverter.convertName(element.getColumnInfo().name);
		column.feature = element.getColumnInfo().feature;
		column.schema = element;
		column.type = handler.onDefineColumnType(element.getFieldType());

		return column;
	}

	@Override
	protected Class<SQLiteInsert> onDefineInsertClass() {
		return SQLiteInsert.class;
	}


	@Override
	protected Class<SQLiteQuery> onDefineQueryClass() {
		return SQLiteQuery.class;
	}


	@Override
	protected Class<SQLiteUpdate> onDefineUpdateClass() {
		return SQLiteUpdate.class;
	}


	@Override
	protected Class<SQLiteDelete> onDefineDeleteClass() {
		return SQLiteDelete.class;
	}


	@Override
	protected SQLiteHandler onDefineHandler(DatabaseSchemaOptions options) {
		return new SQLiteHandler();
	}

}

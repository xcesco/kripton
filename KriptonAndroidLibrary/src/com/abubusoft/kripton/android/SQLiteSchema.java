/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.binder.database.AbstractDatabaseSchema;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.binder.database.DeleteOptions;
import com.abubusoft.kripton.binder.database.InsertOptions;
import com.abubusoft.kripton.binder.database.QueryOptions;
import com.abubusoft.kripton.binder.database.UpdateOptions;
import com.abubusoft.kripton.binder.schema.ElementSchema;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends AbstractDatabaseSchema<SQLiteInsert, SQLiteQuery, SQLiteUpdate, SQLiteDelete> {
	
	static final long INVALID_ID = -1;
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createInsert(java.lang.Class, com.abubusoft.kripton.database.InsertOptions)
	 */
	@Override
	public SQLiteInsert createInsert(Class<?> clazz, InsertOptions options) {
		SQLiteInsert insert=(SQLiteInsert) super.createInsert(clazz, options);
		insert.handler=(SQLiteHandler) handler;
		
		return insert;
	}
	

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createUpdate(java.lang.Class, com.abubusoft.kripton.database.UpdateOptions)
	 */
	@Override
	public SQLiteUpdate createUpdate(Class<?> clazz, UpdateOptions options) {
		SQLiteUpdate update=(SQLiteUpdate) super.createUpdate(clazz, options);
		update.handler=(SQLiteHandler) handler;
		
		return update;
	}


	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createQuery(java.lang.Class, com.abubusoft.kripton.database.QueryOptions)
	 */
	@Override
	public SQLiteQuery createQuery(Class<?> clazz, QueryOptions options) {
		SQLiteQuery query=super.createQuery(clazz, options);
		query.handler=(SQLiteHandler) handler;
		
		return query;
	}


	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createDelete(java.lang.Class, com.abubusoft.kripton.database.DeleteOptions)
	 */
	@Override
	public SQLiteDelete createDelete(Class<?> clazz, DeleteOptions options) {
		SQLiteDelete delete=super.createDelete(clazz, options);
		delete.handler=(SQLiteHandler) handler;
		
		return delete;
	}

	@Override
	protected DatabaseColumn createColumn(ElementSchema element, DatabaseSchemaOptions options) {
		DatabaseColumn column = new DatabaseColumn();

		if (element.getColumnInfo().feature == ColumnType.PRIMARY_KEY) {
			element.getColumnInfo().name = "_id";
		}

		column.name = options.nameConverter.convertName(element.getColumnInfo().name);
		column.feature = element.getColumnInfo().feature;
		column.schema = element;
		column.type = handler.getColumnType(element.getFieldType());

		return column;
	}

	@Override
	protected SQLiteHandler createHandler(DatabaseSchemaOptions options) {
		return new SQLiteHandler();
	}


}

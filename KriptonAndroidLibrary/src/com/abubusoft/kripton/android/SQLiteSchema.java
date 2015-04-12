/**
 * 
 */
package com.abubusoft.kripton.android;

import android.content.ContentValues;

import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.database.ColumnType;
import com.abubusoft.kripton.database.DatabaseColumn;
import com.abubusoft.kripton.database.DatabaseHandler;
import com.abubusoft.kripton.database.AbstractDatabaseSchema;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.DeleteOptions;
import com.abubusoft.kripton.database.InsertOptions;
import com.abubusoft.kripton.database.QueryOptions;
import com.abubusoft.kripton.database.UpdateOptions;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends AbstractDatabaseSchema<SQLiteQuery, SQLiteInsert, SQLiteUpdate, SQLiteDelete> {

	static final long INVALID_ID = -1;

	ThreadLocal<ContentValues> values = new ThreadLocal<ContentValues>();


	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createInsert(java.lang.Class, com.abubusoft.kripton.database.InsertOptions)
	 */
	@Override
	public SQLiteInsert createInsert(Class<?> clazz, InsertOptions options) {
		SQLiteInsert insert=super.createInsert(clazz, options);
		insert.schema=this;
		
		return insert;
	}
	

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createQuery(java.lang.Class, com.abubusoft.kripton.database.QueryOptions)
	 */
	@Override
	public SQLiteQuery createQuery(Class<?> clazz, QueryOptions options) {
		SQLiteQuery query=super.createQuery(clazz, options);
		query.schema=this;
		
		return query;
	}


	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createUpdate(java.lang.Class, com.abubusoft.kripton.database.UpdateOptions)
	 */
	@Override
	public SQLiteUpdate createUpdate(Class<?> clazz, UpdateOptions options) {
		SQLiteUpdate update=super.createUpdate(clazz, options);
		update.schema=this;
		
		return update;
	}


	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.AbstractDatabaseSchema#createDelete(java.lang.Class, com.abubusoft.kripton.database.DeleteOptions)
	 */
	@Override
	public SQLiteDelete createDelete(Class<?> clazz, DeleteOptions options) {
		SQLiteDelete delete=super.createDelete(clazz, options);
		delete.schema=this;
		
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
	protected DatabaseHandler<SQLiteQuery, SQLiteInsert, SQLiteUpdate, SQLiteDelete> createHandler(DatabaseSchemaOptions options) {
		return new SQLiteHandler();
	}

}

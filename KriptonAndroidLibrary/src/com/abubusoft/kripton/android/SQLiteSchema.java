/**
 * 
 */
package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.database.ColumnType;
import com.abubusoft.kripton.database.DatabaseHandler;
import com.abubusoft.kripton.database.AbstractDatabaseSchema;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.DatabaseTable;
import com.abubusoft.kripton.database.SQLStatementParam;
import com.abubusoft.kripton.exception.MappingException;

/**
 * @author xcesco
 *
 */
public class SQLiteSchema extends AbstractDatabaseSchema<SQLiteQuery, SQLiteInsert> {

	ThreadLocal<ContentValues> values = new ThreadLocal<ContentValues>();

	public SQLiteSchema(DatabaseHandler<SQLiteQuery, SQLiteInsert> handler, DatabaseSchemaOptions options) {
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

	@SuppressWarnings("unchecked")
	public boolean insert(SQLiteDatabase database, Object bean) {
		DatabaseTable table = class2Table.get(bean.getClass());
		SQLiteInsert insert = (SQLiteInsert) table.inserts.get("*");

		ContentValues value = values.get();
		if (value == null) {
			value = new ContentValues();
			values.set(value);
		}

		value.clear();
		Object v;

		SQLStatementParam primaryKey = null;

		try {

			for (SQLStatementParam item : insert.params.list) {
				v = item.schema.getFieldValue(bean);

				if (item.column.feature == ColumnType.PRIMARY_KEY) {
					primaryKey = item;
					continue;
				}

				if (v != null) {
					value.put(item.column.name, item.trans.write(v));
				}
			}

			System.out.println(value);

			long id = database.insert(table.name, null, value);

			if (primaryKey != null)
				primaryKey.schema.setFieldValue(bean, id);

			return true;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));

		}
	}

	public <E> ArrayList<E> select(SQLiteDatabase database, Class<E> beanClazz, String queryName, Object params) {
		DatabaseTable table = class2Table.get(beanClazz);
		queryName = queryName == null ? "*" : queryName;
		SQLiteQuery select = (SQLiteQuery) table.queries.get(queryName);
		
		String[] filter = select.getParams(params);

		
		ArrayList<E> result=new ArrayList<E>();
		try {
			Cursor cursor=database.rawQuery(select.getSQL(), filter);
			
			return result;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));

		}
	}

}

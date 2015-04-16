package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.Insert;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteInsert extends Insert {

	SQLiteHandler handler;

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>(); 

	public boolean execute(SQLiteDatabase database, Object bean) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for "
					+ bean.getClass().getName()));
		}

		ContentValues value = SQLiteHelper.bean2Values(this, this.columnAdapter, handler.contentValues, bean);
		
		if (table.primaryKey!=null) 
			value.remove(table.primaryKey.name);
		
		long id = database.insert(table.name, null, value);

		if (id == SQLiteSchema.INVALID_ID) {
			throw (new MappingException("Can not insert row in table " + table.name));
		}

		if (table.primaryKey != null) {
			try {
				table.primaryKey.schema.setFieldValue(bean, id);
			} catch (Exception e) {
				throw new MappingException(e.getMessage());
			}
		}

		return true;

	}

}

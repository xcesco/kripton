package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.FilterOriginType;
import com.abubusoft.kripton.database.Update;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteUpdate extends Update {

	/**
	 * schema adapter
	 */
	SQLiteSchema schema;

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public boolean execute(SQLiteDatabase database, Object bean, Object paramValues) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for " + bean.getClass().getName()));
		}

		ContentValues value = schema.values.get();
		if (value == null) {
			value = new ContentValues();
			schema.values.set(value);
		} 

		value.clear();
		Object v;
		
		String[] filterValues = null;				
		if (filter.origin==FilterOriginType.PARAMS) {
			filterValues = getFilterValues(paramValues, filter.inputClazz);
		} else {
			filterValues = getFilterValues(bean, table.clazz);
		}

		
		try {
			int i = 0;
			for (i=0; i<columns.length;i++)
			{						
				v = columns[i].schema.getFieldValue(bean);
				columnAdapter.get(i).writeValue(v, value, columns[i].name);
			}	
			
			
			long id = database.update(table.name, value, filter.sql, filterValues);

			if (id == SQLiteSchema.INVALID_ID) {
				throw (new MappingException("Can not insert row in table " + table.name));
			}

			return true;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));

		}
	}
}

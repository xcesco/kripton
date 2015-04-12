package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.Delete;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteDelete extends Delete {
	/**
	 * schema adapter
	 */
	SQLiteSchema schema;

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> filterAdapter = new ArrayList<>();


	public int execute(SQLiteDatabase database, Object bean, Object paramValues) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Delete '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for " + bean.getClass().getName()));
		}
		
		String[] filterValues = null;		
		switch(filter.origin)
		{
		case PARAMS:
			filterValues = getFilterValuesFromParams(paramValues, filter.inputClazz);
			break;
		case BEAN:
			filterValues = getFilterValuesFromParams(bean, table.clazz);
			break;
		case NONE:
			break;
		case ONE_PARAM:
			filterValues=getFilterValuesFromOneParam(paramValues, filter.inputClazz);
			break;
		}	
	
		try {
			int result=database.delete(table.name, filter.sql, filterValues);

			return result;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}
}

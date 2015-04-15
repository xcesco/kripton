package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.Delete;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteDelete extends Delete {
	SQLiteHandler handler;

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> filterAdapter = new ArrayList<>();

	ThreadLocal<String[]> filterValues=new ThreadLocal<String[]>();

	public int execute(SQLiteDatabase database, Object bean, Object paramValues) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Delete '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for " + bean.getClass().getName()));
		}
		
		String[] filterValuesArray = null;		
		switch(filter.origin)
		{
		case PARAMS:
			filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues, paramValues, filter.inputClazz);
			break;
		case BEAN:
			filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues, bean, table.clazz);
			break;
		case NONE:
			break;
		case ONE_PARAM:
			filterValuesArray=SQLiteHelper.getFilterValuesFromOneParam(filter, filterValues, paramValues, filter.inputClazz);
			break;
		}	
	
		try {
			int result=database.delete(table.name, filter.sql, filterValuesArray);

			return result;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}
}

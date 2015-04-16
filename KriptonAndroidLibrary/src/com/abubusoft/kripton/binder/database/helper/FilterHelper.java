package com.abubusoft.kripton.binder.database.helper;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.database.FilterOriginType;
import com.abubusoft.kripton.binder.database.FilteredStatement;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.exception.MappingException;

public abstract class FilterHelper {
	/**
	 * build filter section
	 * 
	 * @param table
	 * @param statement
	 * @param where
	 * @param paramsClazz
	 */
	public static Filter buildFilter(DatabaseTable table, FilteredStatement statement, String where, Class<?> paramsClazz) {
		LinkedHashMap<String, DatabaseColumn> field2ColumnMap = table.field2column;
		Filter filter = SQLHelper.createFilterAndFieldNames(where);
		statement.filter = filter;

		filter.sql = SQLHelper.createSql(filter.sql, field2ColumnMap);
		filter.inputClazz = paramsClazz;

		if (filter.fieldNames.length == 0) {
			filter.origin = FilterOriginType.NONE;
		} else if (filter.fieldNames.length == 1 && paramsClazz != null && Transformer.isPrimitive(paramsClazz)) {
			// assert: one parameter, type is transformable
			filter.origin = FilterOriginType.ONE_PARAM;

			DatabaseHelper.scanSingleParam(statement.name, filter, paramsClazz);
		} else if (filter.fieldNames.length > 0) {
			// there are many parameters
			if (paramsClazz == null) {
				filter.origin = FilterOriginType.BEAN;
				DatabaseHelper.scanSchema(statement.name, filter, table);
			} else {
				statement.filter.origin = FilterOriginType.PARAMS;
				DatabaseHelper.scanClass(statement.name, filter, paramsClazz);

				// check if filter fields are same type of bean fields
				Field filterField;
				Field beanField;
				DatabaseColumn column;
				for (int i = 0; i < statement.filter.field.length; i++) {
					filterField = statement.filter.field[i];
					column = table.field2column.get(statement.filter.fieldNames[i]);
					if (column != null) {
						beanField = column.schema.getField();

						if (!filterField.getType().equals(beanField.getType())) {
							throw (new MappingException("A field with name " + beanField.getName() + " is present both in " + beanField.getDeclaringClass()
									+ " like " + beanField.getType().getName() + " and " + filterField.getDeclaringClass() + " like "
									+ filterField.getType().getName() + ", but their type are different!"));
						}
					}
				}
			}
		}
		
		return filter;

	}
}

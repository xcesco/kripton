package com.abubusoft.kripton.database;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.abubusoft.kripton.android.adapter.Adapter;
import com.abubusoft.kripton.binder.transform.Transformable;
import com.abubusoft.kripton.exception.MappingException;

public abstract class SQLFilterStatement extends SQLStatement {
	/**
	 * filter to apply to sql.
	 */
	public Filter filter;
	

	@SuppressWarnings("unchecked")
	public String[] getFilterValues(Object parameters, Class<?> checkClazz) {
		if (!parameters.getClass().isAssignableFrom(checkClazz)) {
			throw (new MappingException("Wrong class for query parameters: aspected " + checkClazz + ", but used " + parameters.getClass()));
		}

		String[] values = filter.values.get();
		if (values == null) {
			values = new String[filter.fieldNames.length];
			filter.values.set(values);
		}

		int n = filter.field.length;
		Field f;
		@SuppressWarnings("rawtypes")
		Transformable t;
		try {

			for (int i = 0; i < n; i++) {
				f = filter.field[i];
				t = filter.fieldTransform[i];
				values[i] = t.write(f.get(parameters));
			}
		} catch (Exception e) {
			throw new MappingException("Unable to get parameter " + e.getMessage());
		}

		return values;
	}

}

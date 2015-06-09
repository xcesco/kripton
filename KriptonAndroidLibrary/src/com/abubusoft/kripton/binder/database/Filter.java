package com.abubusoft.kripton.binder.database;

import java.lang.reflect.Field;

import com.abubusoft.kripton.binder.transform.Transform;

/**
 * Filter for SQLStatement
 * 
 * @author xcesco
 *
 */
public class Filter {
	/**
	 * sql filter
	 */
	public String sql;

	/**
	 * field names
	 */
	public String[] fieldNames;
	
	/**
	 * fields
	 */
	public Field[] field;

	/**
	 * values to apply to filter
	 */
	//public ThreadLocal<String[]> values = new ThreadLocal<String[]>();

	/**
	 * class which contains input for filter
	 */
	public Class<?> inputClazz;

	/**
	 * origin of value of the filter
	 */
	public FilterOriginType origin;

	/**
	 * transformer for fields
	 */
	public Transform<?>[] fieldTransform;

	/**
	 * is input take from an external class
	 * 
	 * @return
	 */
	public boolean isInputFromBean() {
		return inputClazz != null;
	}

}
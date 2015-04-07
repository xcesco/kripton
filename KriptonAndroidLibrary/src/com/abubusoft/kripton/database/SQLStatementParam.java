/**
 * 
 */
package com.abubusoft.kripton.database;

import java.lang.reflect.Field;

import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.transform.Transformable;

/**
 * @author xcesco
 *
 */
public class SQLStatementParam {

	@SuppressWarnings("rawtypes")
	public Transformable trans;

	public DatabaseColumn column;

	public ElementSchema schema;

	public Field field;
	
}

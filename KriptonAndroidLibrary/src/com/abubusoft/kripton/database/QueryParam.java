/**
 * 
 */
package com.abubusoft.kripton.database;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.abubusoft.kripton.binder.transform.Transformable;

/**
 * @author xcesco
 *
 */
public class QueryParam {

	public String name;
	
	public Transformable<?> trans;
	
	public Type type;

	public Field field;
	
}

package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NOT USED IN THIS RELEASE.
 * Use with {@link Bind}. This annotation specify information about database binding
 * 
 * @author xcesco
 *
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface BindDatabase {
	
	/**
	 * If true set this field as primary key
	 * @return
	 */
	public boolean primaryKey() default false;
	
	/**
	 * If true index this field
	 * @return
	 */
	public boolean indexed() default false;
	
	/**
	 * If true the field is nullable
	 * @return
	 */
	public boolean nullable() default true;
	
	/**
	 * If true this field must be unique
	 * @return
	 */
	public boolean unique() default false;
}

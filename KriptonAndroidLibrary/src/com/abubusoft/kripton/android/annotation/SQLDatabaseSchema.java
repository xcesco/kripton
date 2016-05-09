package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Decorate an database schema definition interface.
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SQLDatabaseSchema {

	/**
	 * entity classes to include in the database schema
	 * 
	 * @return
	 * 		class to include in the database schema
	 */
	Class<?>[] value();
	
	/**
	 * Name of database file
	 * 
	 * @return
	 * 		database name
	 */
	String fileName();
	
	/**
	 * Database version
	 * 
	 * @return
	 * 		database version
	 */
	int version() default 1;
	
	

}

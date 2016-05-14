package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Decorate an database schema definition interface. This definition is needed to generate a {@link com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper} instance.</p>
 * 
 * <p>For every managed entity referred in {{@link #value()} attribute, will be used the associated {@link BindDao} definition.
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BindDatabase {

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

package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * <p>Defines TypeAdapter to use to the field.</p>
 * 
 * <p>This kind of adapter is applied from java to data format (JSON/XML/etc) and viceversa. <strong>It is not used in 
 * SharedPreference and SQLite generation.</strong></p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAdapter {

	/**
	 * TypeAdapter used to convert data
	 * 
	 * @return
	 * 		instance of class converter
	 */
	public Class<? extends BindTypeAdapter<?,?>> adapter();
		
	/**
	 * Type of data stored in json/xml/etc.
	 * @return
	 */
	public Class<?> dataType();
}

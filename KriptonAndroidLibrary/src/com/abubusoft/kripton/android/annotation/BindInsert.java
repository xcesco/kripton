package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allow to insert a bean into database.
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BindInsert {
	
	/**
	 * properties to include into INSERT command
	 * @return
	 * 		property's names to include
	 */
	String[] value() default {};
	
	/**
	 * properties to include into INSERT command
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default {};
	
}

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
public @interface SQLInsertBean {
	
	/**
	 * properties to exclude from INSERT operation
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default "";
	
}

package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BindSelectBean {
	
	/**
	 * if true, set distinct clause
	 * 
	 * @return
	 * 		distinct clause
	 */
	boolean distinct() default false;
	
	/**
	 * properties to include into UPDATE command
	 * 
	 * @return
	 * 		property's names to include
	 */
	String[] value() default {};
	
	/**
	 * properties to include into UPDATE command
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default {};
	
	/**
	 * where condition
	 * 
	 * @return
	 * 		where condition
	 */
	String where() default "";
	
	/**
	 * having statement
	 * 
	 * @return
	 * 		having statement
	 */
	String having() default "";
	
	/**
	 * having statement
	 * 
	 * @return
	 * 		groupBy statement
	 * 		
	 */
	String groupBy() default "";
	
	/**
	 * order statement
	 * 
	 * @return
	 * 		order statement
	 */
	String orderBy() default "";


}

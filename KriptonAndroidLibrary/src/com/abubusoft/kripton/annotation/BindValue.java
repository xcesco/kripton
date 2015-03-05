package com.abubusoft.kripton.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation maps a POJO field to an XML value
 * 
 * @author bulldog
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindValue {
	
	/**
	 * Indicates if the string content of the field should
	 * be put in a CDATA container or not.
	 * 
	 * @return true or false
	 */
	public boolean data() default false;
}

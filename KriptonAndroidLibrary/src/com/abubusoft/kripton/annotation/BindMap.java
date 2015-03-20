package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used for define names of key and value for a map
 * 
 * @author bulldog
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindMap {

	/**
	 * name of element rapresents key of a map
	 * 
	 * @return
	 */
	public String keyName() default "key";

	/**
	 * name of element rapresents value of a map
	 * 
	 * @return
	 */
	public String valueName() default "value";
}

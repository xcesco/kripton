package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, 
 * indicates a root XML/JSON element
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindType {
	
	/**
	 * All fields are binded, for each kind of binding.
	 * 
	 * @return
	 * 		true if all fields must be binded
	 */
	boolean allFields() default true;
   
}

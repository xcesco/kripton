package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.binder.transform.CustomTransform;
import com.abubusoft.kripton.binder.transform.DefaultCustomTransform;

/**
 * 
 * Define a custom trasformation for a field
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindTransform {

	/**
	 * <p>Custom trasformation for field or for key value of map entry.</p>
	 * 
	 * @return
	 * 		custom trasformation or null.
	 */
	public Class<? extends CustomTransform<?>> value() default DefaultCustomTransform.class;
	
	/**
	 * <p>Custom trasformation for value of map entry.</p>
	 * 
	 * @return
	 * 		custom trasformation or null.
	 */
	public Class<? extends CustomTransform<?>> mapValue() default DefaultCustomTransform.class;

}

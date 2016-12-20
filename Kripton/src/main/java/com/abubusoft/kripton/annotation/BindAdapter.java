package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * Defines TypeAdapter to use to the field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAdapter {

	public Class<? extends BindTypeAdapter<?,?>> adapter();
		
	public Class<?> dataType();
}

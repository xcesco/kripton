package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.annotation.BindType;

/**
 * <p>This annotation mark an interface which define dao methods for specific bean associated with {@link #value()} attribute.</p>
 * 
 * <p>Referred {@link #value()} bean must be annotated with {@link BindType} annotation.</p>
 * 
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BindDao {

	/**
	 * <p>Bean class to associate with this dao definition.</p>
	 * 
	 * <p>Referred {@link #value()} bean must be annotated with {@link BindType} annotation.</p>
	 * 
	 * @return
	 * 		class of assocaited bean
	 */
	Class<?> value();

}

package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotation used to mark a generated bind map. This annotation is usefully in
 * android context, when proguard may need a marker to sign class which need to
 * be compacted.
 * </p>
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindMap {
	
	/**
	 * binded bean class
	 * 
	 * @return
	 * 		associated bean class
	 */
	Class<?> value();

}

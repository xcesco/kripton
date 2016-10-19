package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, 
 * indicates a root XML/JSON element
 * 
 * @author bulldog
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindTypeXml {

	/**
	 * Name of the root XML/JSON element
	 * 
	 * @return name
	 */
    public String value() default "";
    
    /**
     * Default namespace of the root XML element. Not used in JSON
     * 
     * @return namespace
     */
    public String namespace() default "";
    
}

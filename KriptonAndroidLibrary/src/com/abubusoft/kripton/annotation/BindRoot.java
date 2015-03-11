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
public @interface BindRoot {

	/**
	 * Name of the root XML/JSON element
	 * 
	 * @return name
	 */
    public String name() default "";
    
    /**
     * Default namespace of the root XML element. Not used in JSON
     * 
     * @return namespace
     */
    public String namespace() default "";
    
    /**
     * <p>In JSON, if true ignore element and read only fields.</p>
     * <p>In XML it is not used.</p>
     * 
     * @return
     * 		true for default
     */
    public boolean onlyChildren() default true;
    
}

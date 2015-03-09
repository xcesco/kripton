package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, 
 * indicates a root XML element
 * 
 * @author bulldog
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindRoot {

	/**
	 * Name of the root XML element
	 * 
	 * @return name
	 */
    public String name() default "";
    
    /**
     * Namespace of the root XML element
     * 
     * @return namespace
     */
    public String namespace() default "";
    
    /**
     * <p><b>JSON</b>: ignore element and read only fields</p>
     * <p><b>XML</b>: not used</p>
     * 
     * @return
     * 		true for default
     */
    public boolean onlyChildren() default true;
    
    
    /**
     * Use natural order to sort fields. If true, {@link BindOrder} of fields are ignored.
     * @return
     */
    public boolean useNaturalOrder() default false;
	
}

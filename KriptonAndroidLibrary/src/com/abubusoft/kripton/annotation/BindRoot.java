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
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindRoot {

	/**
	 * The name of the root XML element
	 * 
	 * @return name
	 */
    public String name() default "";
    
    /**
     * The namespace of the root XML element
     * 
     * @return namespace
     */
    public String namespace() default "";
    
    /**
     * <p><b>JSON</b>: ignora l'elemento in fase di lettura/scrittura di un file json</p>
     * <p><b>XML</b>: questo attributo non viene utilizzato</p>
     * 
     * @return
     * 		di default è true
     */
    public boolean onlyChildren() default true;
	
}

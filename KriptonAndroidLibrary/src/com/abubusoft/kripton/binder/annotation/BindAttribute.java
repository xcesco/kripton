package com.abubusoft.kripton.binder.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation maps a POJO field to an XML attribute,
 * this annotation can only annotate field of primitive type, frequently used java type or enum type.
 * 
 * @author bulldog
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAttribute {

	/**
	 * The name of the XML attribute, if not provided, 
	 * annotated field name will be used instead.
	 * 
	 * @return name
	 */
	public String name() default "";
	
//	/**
//	 * The namespace of the XML attribute
//	 * 
//	 * @return namespace
//	 */
//	public String namespace() default "";
	
}

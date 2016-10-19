package com.abubusoft.kripton.annotation;       

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Maps a POJO field to an persistent element.
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {
	
	/**
	 * default order of 
	 */
	public static final int DEFAULT_ORDER=1000;

	/**
	 * default name of map value entry
	 */
	public static final String MAP_VALUE_DEFAULT = "value";
	/**
	 * default name of map key entry
	 */
	public static final String MAP_KEY_DEFAULT = "key";

	/**
	 * The name of the XML/JSON element
	 * 
	 * @return name
	 */
	public String value() default "";

	/**
	 * Used with collections. It's the name of elements contained in the
	 * collection or array, except byte array. Thus, name specified in
	 * attribute value will be used for container.
	 * 
	 * @return name of elements of collection. default is ""
	 */
	public String elementName() default "";

	/**
	 * name of element rapresents key of a map. <b>Used only by map type.</b>
	 * 
	 * @return
	 * 		name of map key. Default is {@link #MAP_KEY_DEFAULT}
	 */
	public String mapKeyName() default MAP_KEY_DEFAULT;

	/**
	 * name of element rapresents value of a map. <b>Used only by map type.</b>
	 * 
	 * @return
	 * 		name of map. Default is {@link #MAP_VALUE_DEFAULT}
	 */
	public String mapValueName() default MAP_VALUE_DEFAULT;

	/**
	 * specifies the order of pojo fields during mapping. Order is specified entire schema.
	 * 
	 * @return
	 * 		order of field
	 */
	public int order() default DEFAULT_ORDER;

}

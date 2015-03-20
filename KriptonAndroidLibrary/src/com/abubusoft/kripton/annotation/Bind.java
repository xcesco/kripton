package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.binder.xml.internal.MapEntryType;

/**
 * 
 * This annotation maps a POJO field to an XML/JSON element
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * Kind of mapping of element of a map. Valid only for maps
	 * @return
	 */
	public MapEntryType mapEntryPolicy() default MapEntryType.ELEMENTS ;

	/**
	 * The name of the XML/JSON element
	 * 
	 * @return name
	 */
	public String name() default "";

	/**
	 * Used with collections. It's the name of elements contained in the
	 * collection or array, except byte array. Thus, name specified in annotation {@link Bind} will be used
	 * for container.
	 * 
	 * @return name of elements of collection. default is ""
	 */
	public String elementName() default "";

	/**
	 * <dl>
	 * <dt>JSON</dt>
	 * <dd>non viene utilizzato</dd>
	 * <dt>XML</dt>
	 * <dd>Indicates if the string content of the field should be put in a CDATA
	 * container or not.</dd>
	 * </dl>
	 * 
	 * @return true or false
	 */
	public boolean data() default false;
	
}

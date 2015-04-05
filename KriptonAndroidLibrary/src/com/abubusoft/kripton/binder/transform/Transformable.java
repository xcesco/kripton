package com.abubusoft.kripton.binder.transform;


/**
 * 
 * Class implementing this interface can be used to transform 
 * between a String value and a basic Java object
 * 
 * @author bulldog
 *
 */
public interface Transformable<T> {

	/**
	 * Transform a string value to an appropriate basic Java object representation.
	 * 
	 * @param value a String value
	 * @return object representation of a basic Java type
	 * @throws Exception
	 */
	public T read(String value) throws Exception;
	
	/**
	 * Transform a basic Java object to a string value.
	 * 
	 * @param value object instance
	 * @return string value
	 * @throws Exception
	 */
	public String write(T value) throws Exception;
	
	
}

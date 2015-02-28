package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Short object
 * 
 * @author bulldog
 *
 */
public class ShortTransform implements Transformable<Short> {

	public Short read(String value) throws Exception {
		return Short.valueOf(value);
	}

	public String write(Short value) throws Exception {
		return value.toString();
	}

}

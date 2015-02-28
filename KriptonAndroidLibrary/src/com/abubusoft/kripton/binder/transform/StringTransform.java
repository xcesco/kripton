package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class StringTransform implements Transformable<String> {

	public String read(String value) throws Exception {
	    return value;
	}

	public String write(String value) throws Exception {
	    return value;
	}

}

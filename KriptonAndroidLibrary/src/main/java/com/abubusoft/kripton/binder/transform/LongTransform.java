package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Long object
 * 
 * @author bulldog
 *
 */
public class LongTransform implements Transform<Long> {

	public Long read(String value) throws Exception {
	    return Long.valueOf(value);
	}

	public String write(Long value) throws Exception {
	    return value.toString();
	}

}

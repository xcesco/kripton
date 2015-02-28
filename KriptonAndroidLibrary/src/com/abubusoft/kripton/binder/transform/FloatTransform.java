package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Float object
 * 
 * @author bulldog
 *
 */
public class FloatTransform implements Transformable<Float> {

	public Float read(String value) throws Exception {
	      return Float.valueOf(value);
	}

	public String write(Float value) throws Exception {
	      return value.toString();
	}

}

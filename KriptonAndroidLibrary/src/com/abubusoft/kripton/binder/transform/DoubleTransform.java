package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Double object
 * 
 * @author bulldog
 *
 */
public class DoubleTransform implements Transformable<Double> {

	public Double read(String value) throws Exception {
		return Double.valueOf(value);
	}

	public String write(Double value) throws Exception {
		return value.toString();
	}

}

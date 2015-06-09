package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Integer object
 * 
 * @author bulldog
 *
 */
public class IntegerTransform implements Transform<Integer> {

	public Integer read(String value) throws Exception {
	      return Integer.valueOf(value);
	}

	public String write(Integer value) throws Exception {
	      return value.toString();
	}

}

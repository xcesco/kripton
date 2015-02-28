package com.abubusoft.kripton.binder.transform;

import java.sql.Time;

/**
 * Transformer between a string and a custom Time object
 * 
 * @author bulldog
 *
 */
class TimeTransform implements Transformable<Time> {

	public Time read(String value) throws Exception {
		return Time.valueOf(value);
	}

	public String write(Time value) throws Exception {
		return value.toString();
	}

}

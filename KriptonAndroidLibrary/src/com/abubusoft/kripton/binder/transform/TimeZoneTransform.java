package com.abubusoft.kripton.binder.transform;

import java.util.TimeZone;

/**
 * Transformer between a string and a java.util.TimeZone object
 * 
 * @author bulldog
 *
 */
public class TimeZoneTransform implements Transformable<TimeZone> {

	public TimeZone read(String value) throws Exception {
	    return TimeZone.getTimeZone(value);
	}

	public String write(TimeZone value) throws Exception {
	    return value.getID();
	}

}

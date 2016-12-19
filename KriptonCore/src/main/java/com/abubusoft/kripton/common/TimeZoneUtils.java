package com.abubusoft.kripton.common;

import java.util.TimeZone;

public class TimeZoneUtils {
	
	public static String write(TimeZone value) {
		return value.getID();		
	}
	
	/**
	 * Convert a string to relative locale
	 * @param localeString
	 * @return
	 */
	public static TimeZone read(String string) {
		return TimeZone.getTimeZone(string);
	}
}

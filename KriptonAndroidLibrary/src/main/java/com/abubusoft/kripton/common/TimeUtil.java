package com.abubusoft.kripton.common;

import java.sql.Time;

public class TimeUtil {
	
	public static String write(Time value) {
		return value.toString();
	}
	
	/**
	 * Convert a string to relative locale
	 * @param localeString
	 * @return
	 */
	public static Time read(String string) {
		return Time.valueOf(string);
	}
}

package com.abubusoft.kripton.common;

import java.sql.Date;

public class SQLDateUtils {
	
	public static String write(Date value) {
		return value.toString();
	}
	
	/**
	 * Convert a string to relative locale
	 * @param localeString
	 * @return
	 */
	public static Date read(String string) {		
		return Date.valueOf(string);
	}
}

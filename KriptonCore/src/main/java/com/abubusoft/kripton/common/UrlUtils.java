package com.abubusoft.kripton.common;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

	public static String write(URL url) {
		return url.toString();
	}
	
	/**
	 * Convert a string to relative locale
	 * @param localeString
	 * @return
	 */
	public static URL read(String string) {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

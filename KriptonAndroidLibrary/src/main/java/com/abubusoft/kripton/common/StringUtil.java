package com.abubusoft.kripton.common;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * A few string utils
 * 
 * @author bulldog
 *
 */
public class StringUtil {

	public static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}

	/**
	 * Contains text.
	 * 
	 * @param value
	 * @return true if string is not empty and contains not only spaces
	 */
	public static boolean hasText(String value) {
		return value != null && value.trim().length() > 0;
	}
	
	/**
	 * Format sql
	 * 
	 * @param input
	 * @return
	 * 		formatted sql
	 */
	public static String formatSQL(String input)
	{
		return "SQL: "+input;
	}

	/**
	 * limit string size
	 */
	public static String checkSize(Object value, int limitSize) {		
		if (value != null) {
			String str=value.toString();
			if (str.length() > limitSize) {
				return str.substring(0, limitSize-3)+"...";
			} else
				return str;
		} else
			return null;

	}
	
	/**
	 * limit string size to 32
	 */
	public static String checkSize(Object value) {
		return checkSize(value, 32);
	}

	public static String lowercaseFirstLetter(String value) {
		// se non abbiamo input, allora non facciamo niente
		if (value == null || value.length() == 0)
			return "";

		char[] stringArray = value.toCharArray();

		stringArray[0] = Character.toLowerCase(stringArray[0]);
		return new String(stringArray);
	}

	public static void string2Writer(String source, Writer out) throws IOException {

		char[] buffer = source.toCharArray();
		for (int i = 0; i < buffer.length; i++) {
			out.append(buffer[i]);
		}
		out.flush();
	}

	public static String reader2String(Reader source) throws IOException {
		char[] cbuf = new char[65535];
		StringBuffer stringbuf = new StringBuffer();

		int count = 0;
		while ((count = source.read(cbuf, 0, 65535)) != -1) {
			stringbuf.append(cbuf, 0, count);
		}

		return stringbuf.toString();
	}

}

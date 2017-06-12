/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.common;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * A few string utils
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class StringUtils {

	private static final int VIEW_SIZE = 64;

	public static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}

	/**
	 * if <code>value</code> begin with ' ' or '\t' then return
	 * <code>' ' + value</code> string, otherwise <code>value</code>
	 * 
	 * @param value
	 * @return
	 */
	public static String startWithSpace(String value) {
		if (value.length() > 0 && (value.charAt(0) != ' ' && value.charAt(0) != '\t')) {
			return " " + value;
		}

		return value;
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
	 * limit string size
	 */
	public static String checkSize(Object value, int limitSize, String defaultValue) {
		if (value != null) {
			if (byte[].class.getSimpleName().equals(value.getClass().getSimpleName())) {
				return checkSize((byte[]) value, limitSize);
			}

			String str = value.toString();
			if (str.length() > limitSize) {
				return str.substring(0, limitSize - 3) + "...";
			} else
				return str;
		} else
			return defaultValue;

	}

	public static String checkSize(byte[] value, int limitSize) {
		if (value != null) {
			if (value.length > limitSize) {
				return new String(value, 0, limitSize - 3) + "...";
			} else
				return new String(value);
		} else
			return null;

	}

	/**
	 * format as sql parameter. If value is not null, method will return
	 * 'value'. If value is not null, delimiter will be used to delimit return
	 * value. Otherwise, defaultValue will be returned, without delimiter.
	 * 
	 * @param value
	 * @param delimiter
	 * @param limitSize
	 * @param defaultValue
	 * @return
	 */
	public static String formatParam(Object value, String delimiter) {
		if (value != null) {
			if (byte[].class.getSimpleName().equals(value.getClass().getSimpleName())) {
				return checkSize((byte[]) value, VIEW_SIZE);
			}

			String str = value.toString();
			if (str.length() > VIEW_SIZE) {
				return delimiter + str.substring(0, VIEW_SIZE - 3) + "..." + delimiter;
			} else
				return delimiter + str + delimiter;
		} else
			return "<undefined>";

	}

	/**
	 * limit string size to 32
	 */
	public static String checkSize(Object value) {
		return checkSize(value, VIEW_SIZE, null);
	}

	/**
	 * limit string size to 32
	 */
	public static String checkSize(Object value, String defaultValue) {
		return checkSize(value, VIEW_SIZE, defaultValue);
	}

	/**
	 * limit string size to 32
	 */
	public static String checkSize(byte[] value) {
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

	/**
	 * <p>
	 * If <code>checkString</code> has text, then return value string. Otherwise
	 * empty string was returned
	 * </p>
	 * 
	 * @param chekString
	 * @param value
	 * @return
	 */
	public static String ifNotEmpty(String chekString, String value) {
		if (hasText(chekString)) {
			return value;
		} else {
			return "";
		}
	}

}

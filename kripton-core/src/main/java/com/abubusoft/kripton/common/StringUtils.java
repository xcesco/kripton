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

// TODO: Auto-generated Javadoc
/**
 * A few string utils.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class StringUtils {

	/** The Constant VIEW_SIZE. */
	private static final int VIEW_SIZE = 64;

	/**
	 * Checks if is empty.
	 *
	 * @param value the value
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}

	/**
	 * if <code>value</code> begin with ' ' or '\t' then return
	 * <code>' ' + value</code> string, otherwise <code>value</code>.
	 *
	 * @param value the value
	 * @return the string
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
	 * @param value the value
	 * @return true if string is not empty and contains not only spaces
	 */
	public static boolean hasText(String value) {
		return value != null && value.trim().length() > 0;
	}

	/**
	 * limit string size.
	 *
	 * @param value the value
	 * @param limitSize the limit size
	 * @param defaultValue the default value
	 * @return the string
	 */
	public static String checkSize(Object value, int limitSize, String defaultValue) {
		if (value != null) {
			if (byte[].class.getSimpleName().equals(value.getClass().getSimpleName())) {
				return checkSize((byte[]) value, limitSize/2);
			}

			String str = value.toString();
			if (str.length() > limitSize) {
				return str.substring(0, limitSize - 3) + "...";
			} else
				return str;
		} else
			return defaultValue;

	}
	
	/** The Constant hexArray. */
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	/**
	 * Bytes to hex.
	 *
	 * @param bytes the bytes
	 * @param size the size
	 * @return the string
	 */
	public static String bytesToHex(byte[] bytes, int size) {
		size=Math.min(bytes.length, size);
	    char[] hexChars = new char[size * 2];
	    for ( int j = 0; j < size; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

	/**
	 * Check size.
	 *
	 * @param value the value
	 * @param limitSize the limit size
	 * @return the string
	 */
	public static String checkSize(byte[] value, int limitSize) {
		if (value != null) {
			if (value.length > limitSize) {
				return bytesToHex(value, limitSize-3) + "...";
			} else
				return bytesToHex(value, value.length);
		} else
			return null;

	}

	/**
	 * format as sql parameter. If value is not null, method will return
	 * 'value'. If value is not null, delimiter will be used to delimit return
	 * value. Otherwise, defaultValue will be returned, without delimiter.
	 *
	 * @param value the value
	 * @param delimiter the delimiter
	 * @return the string
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
	 * limit string size to 32.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String checkSize(Object value) {
		return checkSize(value, VIEW_SIZE, null);
	}

	/**
	 * limit string size to 32.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the string
	 */
	public static String checkSize(Object value, String defaultValue) {
		return checkSize(value, VIEW_SIZE, defaultValue);
	}

	/**
	 * limit string size to 32.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String checkSize(byte[] value) {
		return checkSize(value, 32);
	}

	/**
	 * Lowercase first letter.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String lowercaseFirstLetter(String value) {
		// se non abbiamo input, allora non facciamo niente
		if (value == null || value.length() == 0)
			return "";

		char[] stringArray = value.toCharArray();

		stringArray[0] = Character.toLowerCase(stringArray[0]);
		return new String(stringArray);
	}

	/**
	 * String 2 writer.
	 *
	 * @param source the source
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void string2Writer(String source, Writer out) throws IOException {

		char[] buffer = source.toCharArray();
		for (int i = 0; i < buffer.length; i++) {
			out.append(buffer[i]);
		}
		out.flush();
	}

	/**
	 * Reader 2 string.
	 *
	 * @param source the source
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	 * If <code>checkString</code> has text, returns value+checkString. Otherwise
	 * empty string was returned
	 * </p>
	 *
	 * @param chekString the chek string
	 * @param value the value
	 * @return the string
	 */
	public static String ifNotEmptyAppend(String chekString, String value) {
		if (hasText(chekString)) {
			return value+chekString;
		} else {
			return "";
		}
	}
	
	/**
	 * <p>
	 * If <code>checkString</code> has text, returns checkString+value string. Otherwise
	 * empty string was returned
	 * </p>
	 *
	 * @param chekString the chek string
	 * @param value the value
	 * @return the string
	 */
	public static String ifNotEmptyPrepend(String chekString, String value) {
		if (hasText(chekString)) {
			return chekString+value;
		} else {
			return "";
		}
	}

	/**
	 * Nvl.
	 *
	 * @param input the input
	 * @return the string
	 */
	public static String nvl(String input) {
		if (input==null) return "";
		
		return input;
	}

	/**
	 * Prints the if.
	 *
	 * @param test the test
	 * @param value the value
	 * @return the string
	 */
	public static String printIf(boolean test, String value) {
		if(test) return value;
		return "";
	}

}

/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.common;

import java.net.MalformedURLException;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class UrlUtils.
 */
public class UrlUtils {

	/**
	 * Write.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String write(URL value) {
		if (value==null) return null;
		return value.toString();
	}
	
	/**
	 * Convert a string to relative locale.
	 *
	 * @param string the string
	 * @return the url
	 */
	public static URL read(String value) {
		if (value == null)
			return null;
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

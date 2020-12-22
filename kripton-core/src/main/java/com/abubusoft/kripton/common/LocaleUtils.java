/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.common;

import java.util.Locale;


/**
 * The Class LocaleUtils.
 */
public class LocaleUtils {

	/**
	 * Write.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public static String write(Locale locale) {
		if (locale==null) return null;
		return locale.toString();
	}
	
	/**
	 * Convert a string to relative locale.
	 *
	 * @param localeString the locale string
	 * @return the locale
	 */
	public static Locale read(String localeString) {
		if (localeString == null)
			return null;
		if (localeString.toLowerCase().equals("default"))
			return Locale.getDefault();
		int languageIndex = localeString.indexOf('_');
		if (languageIndex == -1)
			return null;
		int countryIndex = localeString.indexOf('_', languageIndex + 1);
		String country = null;
		if (countryIndex == -1) {
			if (localeString.length() > languageIndex) {
				country = localeString.substring(languageIndex + 1, localeString.length());
			} else {
				return null;
			}
		}
		int variantIndex = -1;
		if (countryIndex != -1)
			countryIndex = localeString.indexOf('_', countryIndex + 1);
		String language = localeString.substring(0, languageIndex);
		String variant = null;
		if (variantIndex != -1) {

			variant = localeString.substring(variantIndex + 1, localeString.length());
		}
		if (variant != null) {
			return new Locale(language, country, variant);
		} else {
			return new Locale(language, country);
		}

	}
}

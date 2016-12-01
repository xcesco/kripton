package com.abubusoft.kripton.common;

import java.util.Locale;

public class LocaleUtils {

	public static String write(Locale locale) {
		return locale.toString();
	}
	
	/**
	 * Convert a string to relative locale
	 * @param localeString
	 * @return
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

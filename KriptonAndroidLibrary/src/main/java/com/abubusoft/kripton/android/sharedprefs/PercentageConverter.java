package com.abubusoft.kripton.android.sharedprefs;


/**
 * Converte una preference int che da va da 0 a 100 ad un config float che va da 0f a 1f.
 * 
 * @author Francesco Benincasa
 * 
 */
public class PercentageConverter implements Converter {

	@Override
	public Object convertToPreference(Object configValue, PreferenceType preferenceType) {

		if (preferenceType == PreferenceType.INT) {
			int result;

			result = Math.round(((Float) configValue) * 100f);

			return result;
		}
		return null;
	}

	@Override
	public Object convertToConfig(Object preferenceValue, PreferenceType preferenceType) {
		if (preferenceValue == null)
			return 0;

		Integer value = (Integer) preferenceValue;

		float result = (value / 100f);

		return result;
	}

}

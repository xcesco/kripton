package com.abubusoft.kripton.android.sharedprefs;

import java.lang.reflect.Field;

/**
 * Converte una preference int che da va da 0 a 100 ad un config float che va da 0f a 1f.
 * 
 * @author Francesco Benincasa
 * 
 */
public class PercentageConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.elio.config.Converter#convertToPreference(java.lang.Object, org.abubu.elio.config.TypePreference)
	 */
	@Override
	public Object convertToPreference(Object configValue, PreferenceType preferenceType) {

		if (preferenceType == PreferenceType.INT) {
			int result;

			result = Math.round(((Float) configValue) * 100f);

			return result;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.elio.config.Converter#convertToConfig(java.lang.reflect.Field, java.lang.Object)
	 */
	@Override
	public Object convertToConfig(Field field, Object preferenceValue) {
		if (preferenceValue == null)
			return 0;

		Integer value = (Integer) preferenceValue;

		float result = (value / 100f);

		return result;
	}

}

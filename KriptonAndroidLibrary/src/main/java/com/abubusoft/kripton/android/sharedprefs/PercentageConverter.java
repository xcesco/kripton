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
	public Object convertToConfig(Object preferenceValue, Class<?> propertyType) {
		if (preferenceValue == null)
			return 0;

		Integer value = (Integer) preferenceValue;

		float result = (value / 100f);

		return result;
	}

}

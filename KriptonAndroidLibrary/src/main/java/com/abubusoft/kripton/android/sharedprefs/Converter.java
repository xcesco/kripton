package com.abubusoft.kripton.android.sharedprefs;


/**
 * Interface for shared preference converter to bean and viceversa.
 * 
 * @author xcesco
 * 
 */
public interface Converter {

	/**
	 * Data una configurazione, la converte in preference
	 * 
	 * @param configValue
	 * @param preferenceType
	 * @return
	 * 		
	 */
	public Object convertToPreference(Object configValue, PreferenceType preferenceType);

	/**
	 * Data una preferenza, la converte nella relativa config
	 * 
	 * @param propertyType
	 * @param preferenceValue
	 * @return
	 */
	public Object convertToConfig(Object preferenceValue, Class<?> propertyType);
}

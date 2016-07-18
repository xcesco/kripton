package com.abubusoft.kripton.processor.kripton45;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;

@BindSharedPreferences(allFields=true)
public class AppPreferences {

	@BindPreference(preferenceType=PreferenceType.STRING)
	public String name; 
	
	@BindPreference(preferenceType=PreferenceType.STRING)
	protected String description;
		
	public float valueFloat;
	
	public String[] stringArray;
	
	public int valueInt;
	
	public Long valueLong;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

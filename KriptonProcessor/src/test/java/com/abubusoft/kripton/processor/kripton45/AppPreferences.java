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
}

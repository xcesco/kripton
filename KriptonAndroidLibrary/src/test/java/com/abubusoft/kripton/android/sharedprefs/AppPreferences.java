package com.abubusoft.kripton.android.sharedprefs;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

@BindSharedPreferences
public class AppPreferences {
	 	
	public String name;
	
	public Boolean enabled;
	
	public Integer age;

	public List<String> mirrors;
}

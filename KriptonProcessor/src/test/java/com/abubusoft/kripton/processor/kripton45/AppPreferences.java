package com.abubusoft.kripton.processor.kripton45;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;
import com.abubusoft.kripton.annotation.BindType;

@BindSharedPreferences
public class AppPreferences {

	public String name="ciao"; 
	
	protected String description;
		
	public float valueFloat=5.0f;
	
	public boolean valueBoolean;
	
	//public AppType appType;
	
	protected String[] stringArray;
	
	public List<String> stringList;
	
	
	public String[] getStringArray() {
		return stringArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

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

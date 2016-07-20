package com.abubusoft.kripton.processor.kripton45;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

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

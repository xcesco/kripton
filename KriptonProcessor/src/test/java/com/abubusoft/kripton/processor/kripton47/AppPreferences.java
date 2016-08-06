package com.abubusoft.kripton.processor.kripton47;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSharedPreferences(allFields=true)
public class AppPreferences {

	public String name="ciao"; 
	
	//public String description;
		
	//public float valueFloat=5.0f;
	
	public UserAccessToken userAccessToken;
	
}

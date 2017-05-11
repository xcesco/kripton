package com.abubusoft.kripton.degusta.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Translation {
	
	public Translation()
	{
		
	}
	
	public Translation(String italianValue, String frenchValue, String englishValue)
	{
		translated.put(SupportedLanguage.ENGLISH.getValue().getLanguage(), frenchValue);
		translated.put(SupportedLanguage.ITALIAN.getValue().getLanguage(), italianValue);
		translated.put(SupportedLanguage.FRENCH.getValue().getLanguage(), frenchValue);
	}
	
	public Map<String, String> translated=new HashMap<>();
	
	public String getValue(Locale locale)
	{
		if (translated.containsKey(locale.getLanguage()))
		{
			return translated.get(locale.getLanguage());
		}
		
		return translated.get(SupportedLanguage.ENGLISH.getValue().getLanguage());                 
	}
}

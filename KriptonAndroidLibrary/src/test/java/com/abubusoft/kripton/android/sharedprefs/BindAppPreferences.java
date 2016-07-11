package com.abubusoft.kripton.android.sharedprefs;

import java.util.HashMap;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.content.Context;
import android.content.SharedPreferences;

public class BindAppPreferences {
	
	public BindAppPreferences()
	{
		 converterMap = new HashMap<String, Converter>();
		 
		 DefaultConverter defaultConverter = new DefaultConverter();
		 
		 //converterMap.put("name", defaultConverter);
	}
		
	
	public class Editor
	{
		public Editor putName(String value)
		{
			name=value;
			
			return this;
		}
		
		public void save()
		{
			SharedPreferences prefs=KriptonLibrary.context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
			
			android.content.SharedPreferences.Editor edit=prefs.edit();
			edit.putString("name", (String) BindAppPreferences.this.beanToPref("name", BindAppPreferences.this.name));
			edit.apply();
		}
	}
		
	protected String name;
	
	protected HashMap<String, Converter> converterMap;
	
	public static final String SHARED_PREFERENCE_NAME="dummy";
	
	public static BindAppPreferences instance()
	{		
		SharedPreferences prefs=KriptonLibrary.context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		BindAppPreferences bean=new BindAppPreferences();
		
		bean.name= bean.prefToBean("name", prefs.getString("name", "default"));
		//prefs.getBoolean(key, defValue)
		//prefs.getFloat(key, defValue)
		//prefs.getInt(key, defValue)
		//prefs.getLong(key, defValue)
		//prefs.getString(key, defValue)		
		return bean;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Editor edit()
	{
		return new Editor();
	}
	
	protected <T> T beanToPref(String key, Object value)
	{
		T result=null;
		if (converterMap.containsKey(key)){
			value=converterMap.get(key).convertToPreference(key, value);
		}
		
		return result;
	}
	
	protected <T> T prefToBean(String key, Object value)
	{
		T result=null;
		if (converterMap.containsKey(key)){
			value=converterMap.get(key).convertToConfig(key, value);
		}
		
		return result;
	}

}

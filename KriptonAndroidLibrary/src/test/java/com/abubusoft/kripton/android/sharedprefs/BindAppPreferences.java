package com.abubusoft.kripton.android.sharedprefs;

import java.util.HashMap;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class BindAppPreferences {
	
	public static final String SHARED_PREFERENCE_NAME="dummy";
	
	public BindAppPreferences()
	{
		prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		converterMap = new HashMap<String, Converter>();
		 
		 //converterMap.put("name", defaultConverter);
	}
			
	public class Editor
	{
		protected Editor()
		{
			edit=prefs.edit();
		}
		
		private android.content.SharedPreferences.Editor edit;
		
		public Editor putName(String value)
		{
			edit.putString("name", (String) BindAppPreferences.this.beanToPref("name", bean.name));
			
			return this;
		}
		
		public Editor clearName()
		{
			edit.remove("name");
			
			return this;
		}
		
		public Editor put(AppPreferences bean)
		{
			putName(bean.name);
			
			return this;
		}
		
		public Editor clear()
		{
			edit.clear();
			
			return this;
		}
		
		public Editor commit()
		{
			edit.commit();
			
			return this;
		}
		
		
	}
		
	protected AppPreferences bean;
	
	protected HashMap<String, Converter> converterMap;

	private SharedPreferences prefs;
	
	public static BindAppPreferences instance()
	{		
		BindAppPreferences result=new BindAppPreferences();
				
		//bean.name= bean.prefToBean("name", prefs.getString("name", "default"));
		//prefs.getBoolean(key, defValue)
		//prefs.getFloat(key, defValue)
		//prefs.getInt(key, defValue)
		//prefs.getLong(key, defValue)
		//prefs.getString(key, defValue)		
		return result;
	}
	
	public AppPreferences read()
	{		
		prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		AppPreferences bean=new AppPreferences();			
		
		bean.name=prefToBean("name", prefs.getString("name", "default"));
		
		return bean;
	}
	
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
	{
		prefs.registerOnSharedPreferenceChangeListener(listener);
	}
	
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
	{
		prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}
	
	public void write(AppPreferences bean)
	{
		Editor edit=this.edit();
		edit.put(bean);
		
		edit.commit();
	}
	
	public Editor edit()
	{
		return new Editor();
	}
	
	protected <T> T beanToPref(String key, Object value)
	{
		T result=null;
		if (converterMap.containsKey(key)){
			//value=converterMap.get(key).convertToPreference(key, value);
		} else 
		{
			//value=defaultConverter.convertToPreference(
		}
		
		return result;
	}
	
	protected <T> T prefToBean(String key, Object value)
	{
		T result=null;
		if (converterMap.containsKey(key)){
			//value=converterMap.get(key).convertToConfig(key, value);
		}
		
		return result;
	}

}

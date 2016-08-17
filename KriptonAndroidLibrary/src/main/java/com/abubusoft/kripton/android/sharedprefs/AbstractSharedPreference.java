/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.common.Base64;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class AbstractSharedPreference {
	
	protected AbstractSharedPreference() {
	}
	
	protected String writeObj(Object obj)
	{
		if (obj==null) return null;
		
		if (objWriter==null)
		{
			objWriter=BinderFactory.getJSONWriter(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
		}
		
		String result=null;		
		try {
			result = objWriter.write(obj);
			result=Base64.encode(result.getBytes());
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	protected <E> E readObj(String input, Class<E> clazz)
	{
		if (input==null || input.length()==0) return null;
		
		if (objReader==null)
		{
			objReader=BinderFactory.getJSONReader(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
		}
		
		E result=null;
		String buffer;
		try {
			buffer=new String(Base64.decode(input));
			result=objReader.read(clazz, buffer );
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	protected BinderWriter objWriter;
	
	protected BinderReader objReader;

	public static final String STRING_ARRAY_SEPARATOR = ";##@@;";

	//protected static final DefaultConverter defaultConverter = new DefaultConverter();

	//protected HashMap<String, Converter> converterMap;

	protected SharedPreferences prefs;
		
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

	/*
	protected Object writePreference(String key, Object value, PreferenceType preferenceType) {
		Object result;
		if (converterMap.containsKey(key)) {
			result = converterMap.get(key).convertToPreference(value, preferenceType);
		} else {
			result = defaultConverter.convertToPreference(value, preferenceType);
		}

		return result;
	}*/

	/*
	protected Object readPreference(String key, Object value, PreferenceType preferenceType) {
		Object result = null;
		if (converterMap.containsKey(key)) {
			// result = converterMap.get(key).convertToConfig(value, preferenceType);
		} else {
			// result = defaultConverter.convertToConfig(value, preferenceType);
		}

		return result;
	}*/

	protected String list2String(List<String> array) {
		String result;
		String separator = "";
		StringBuilder buffer = new StringBuilder();
		for (String item : array) {
			if (item == null)
				continue;
			buffer.append(separator + item);
			separator = STRING_ARRAY_SEPARATOR;
		}
		result = buffer.toString();

		return result;
	}

	protected String array2String(String[] array) {
		String result;
		String separator = "";
		StringBuilder buffer = new StringBuilder();
		for (String item : array) {
			if (item == null)
				continue;
			buffer.append(separator + item);
			separator = STRING_ARRAY_SEPARATOR;
		}
		result = buffer.toString();

		return result;
	}

	protected List<String> string2list(String input, List<String> defaultValue) {
		if (input==null) return new ArrayList<String>(defaultValue);		
		
		String tempValues[] = input.split(STRING_ARRAY_SEPARATOR);
		ArrayList<String> values;
		values = new ArrayList<String>();
		// ripuliamo gli array dalle stringhe vuote
		for (String item : tempValues) {
			if (item != null && item.trim().length() > 0) {
				values.add(item);
			}
		}

		return values;
	}

	protected String[] string2array(String input, String[] defaultValue) {
		if (input==null) return Arrays.copyOf(defaultValue, defaultValue.length);
		
		String tempValues[] = input.split(STRING_ARRAY_SEPARATOR);
		List<String> values = new ArrayList<String>();

		// ripuliamo gli array dalle stringhe vuote
		for (String item : tempValues) {
			if (item != null && item.trim().length() > 0) {
				values.add(item);
			}
		}

		return values.toArray(new String[values.size()]);

	}

	public class AbstractEditor
	{
		protected AbstractEditor()
		{
			editor=prefs.edit();
		}
		
		protected final SharedPreferences.Editor editor;
		
		public void commit()
		{
			editor.commit();
		}
	}

}

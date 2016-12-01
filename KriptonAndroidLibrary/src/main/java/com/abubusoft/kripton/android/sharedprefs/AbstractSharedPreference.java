/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public class AbstractSharedPreference {
	
	protected AbstractSharedPreference() {
	}
	
	protected String writeObj(Object obj)
	{
		if (obj==null) return null;
		
		BinderJsonWriter objWriter = ProcessorHelper.getWriter();
		
		String result=null;		
		try {
			result = objWriter.write(obj);
			result=Base64Utils.encode(result.getBytes());
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
		
		BinderJsonReader objReader = ProcessorHelper.getReader();
		
		E result=null;
		String buffer;
		try {
			buffer=new String(Base64Utils.decode(input));
			result=objReader.read(clazz, buffer );
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static final String STRING_ARRAY_SEPARATOR = ";##@@;";

	protected SharedPreferences prefs;
		
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

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
		return removeEmptyItems(tempValues);
	}

	/**
	 * @param tempValues
	 * @return
	 */
	private List<String> removeEmptyItems(String[] tempValues) {
		ArrayList<String> values = new ArrayList<String>();

		// remove empty item
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
		List<String> values = removeEmptyItems(tempValues);

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

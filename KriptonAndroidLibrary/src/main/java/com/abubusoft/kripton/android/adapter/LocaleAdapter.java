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
package com.abubusoft.kripton.android.adapter;

import java.util.Locale;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.util.Locale object
 * 
 * @author bulldog
 *
 */
public class LocaleAdapter implements SqliteAdapter<Locale> {
	
    private final Pattern pattern;
   
    public LocaleAdapter() {
       this.pattern = Pattern.compile("_");
    }

    public Locale read(String locale) throws Exception {
        String[] list = pattern.split(locale);
        
        if(list.length < 1) {
           throw new IllegalArgumentException("Invalid locale " + locale);
        }
        return read(list);
     }
     
     private Locale read(String[] locale)  throws Exception {
        String[] list = new String[] {"", "", ""};
        
        for(int i = 0; i < list.length; i++) {
           if(i < locale.length) {         
              list[i] = locale[i];
           }
        }
        return new Locale(list[0], list[1], list[2]);
     }
     
     public String write(Locale locale) {
        return locale.toString();
     }

	@Override
	public Locale readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		return read(value);
	}

	@Override
	public void writeValue(Locale value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}

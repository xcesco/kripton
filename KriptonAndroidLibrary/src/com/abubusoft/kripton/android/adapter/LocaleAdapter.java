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
		return read(cursor.getString(columnIndex));
	}

	@Override
	public void writeValue(Locale value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}

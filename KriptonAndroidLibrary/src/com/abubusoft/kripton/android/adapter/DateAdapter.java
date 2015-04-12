package com.abubusoft.kripton.android.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.util.Date object
 * 
 * @author bulldog
 *
 */
class DateAdapter implements SqliteAdapter<Date> {
	
	public static String FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	   
	public static String LONG = "yyyy-MM-dd HH:mm:ss z";
	
	public static String NORMAL = "yyyy-MM-dd z";
	
	public static String SHORT = "yyyy-MM-dd";
	
	public static String TIME_ZONE = "GMT";

	public static String getPattern(String text) {
        int length = text.length();

        if(length > 23) {
           return FULL;
        }
        if(length > 20) {
           return LONG;
        }
        if(length > 11) {
           return NORMAL;
        }
        return SHORT;
	}
	
	public static class ThreadLocalDateFormatter {
		
		private static final ThreadLocal<Map<String, DateFormat>> FORMATTERS 
		        = new ThreadLocal<Map<String, DateFormat>>() {
			        protected Map<String, DateFormat> initialValue() {
			    	    return new HashMap<String, DateFormat>();
			        }
		};
		
		@SuppressLint("SimpleDateFormat")
		static private final DateFormat getFormatter(final String pattern) {
			Map<String, DateFormat> formatterMap = FORMATTERS.get();
			DateFormat df = formatterMap.get(pattern);
			if ( null == df) {
				df = new SimpleDateFormat(pattern);
				TimeZone timeZoneGMT = TimeZone.getTimeZone(DateAdapter.TIME_ZONE);
				df.setTimeZone(timeZoneGMT);
				formatterMap.put(pattern, df);
			}
			return df;
		}
		
		/**
		 * static public and thread-safe method to parse a date from the given string
		 * 
		 * @param strDate: input string to parse
		 * @param pattern: date format pattern fo the input string
		 * @return Date value of the input string
		 * @throws ParseException if parse exception happened
		 */
		static public Date parse(final String strDate, final String pattern) throws ParseException {
			return getFormatter(pattern).parse(strDate);
		}
		
		/**
		 * A thread-safe method to format a given Date based-on the given pattern
		 * 
		 * @param theDate, Date to be formatted
		 * @param pattern, pattern used to format the date
		 * @return String of formatted date
		 */
		static public String format(final Date theDate, final String pattern) {
			return getFormatter(pattern).format(theDate);
		}
	}

	@Override
	public Date readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		String pattern = getPattern(value);
		Date date = ThreadLocalDateFormatter.parse(value, pattern);
		return date;
	}

	@Override
	public void writeValue(Date value, ContentValues content, String columnKey) throws Exception {		
		String text = ThreadLocalDateFormatter.format(value, FULL);
		content.put(columnKey, text);		
	}

}

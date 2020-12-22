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
package com.abubusoft.kripton.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * The Class CalendarUtils.
 */
public class CalendarUtils {

	/** The full. */
	public final static String FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/** The long. */
	public final static String LONG = "yyyy-MM-dd HH:mm:ss z";

	/** The normal. */
	public final static String NORMAL = "yyyy-MM-dd z";

	/** The short. */
	public final static String SHORT = "yyyy-MM-dd";

	/** The time zone. */
	public final static String TIME_ZONE = "GMT";
	
	/**
	 * Read.
	 *
	 * @param value the value
	 * @return the calendar
	 */
	public static Calendar read(String value) {
		if (value==null) return null;
		Date date=DateUtils.read(value);
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
	}


	/**
	 * Write.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String write(Calendar value) {
		if (value==null) return null;
		return DateUtils.write(value.getTime());
	}

	/**
	 * Gets the pattern.
	 *
	 * @param text the text
	 * @return the pattern
	 */
	static String getPattern(String text) {
		int length = text.length();

		if (length > 23) {
			return FULL;
		}
		if (length > 20) {
			return LONG;
		}
		if (length > 11) {
			return NORMAL;
		}
		return SHORT;
	}

	/**
	 * The Class ThreadLocalDateFormatter.
	 */
	public static class ThreadLocalDateFormatter {

		/** The Constant FORMATTERS. */
		private static final ThreadLocal<Map<String, DateFormat>> FORMATTERS = new ThreadLocal<Map<String, DateFormat>>() {
			protected Map<String, DateFormat> initialValue() {
				return new HashMap<String, DateFormat>();
			}
		};

		/**
		 * Gets the formatter.
		 *
		 * @param pattern the pattern
		 * @return the formatter
		 */
		static private final DateFormat getFormatter(final String pattern) {
			Map<String, DateFormat> formatterMap = FORMATTERS.get();
			DateFormat df = formatterMap.get(pattern);
			if (null == df) {
				df = new SimpleDateFormat(pattern);
				TimeZone timeZoneGMT = TimeZone.getTimeZone(CalendarUtils.TIME_ZONE);
				df.setTimeZone(timeZoneGMT);
				formatterMap.put(pattern, df);
			}
			return df;
		}

		/**
		 * static public and thread-safe method to parse a date from the given string.
		 *
		 * @param strDate            : input string to parse
		 * @param pattern            : date format pattern fo the input string
		 * @return Date value of the input string
		 * @throws ParseException             if parse exception happened
		 */
		static public Date parse(final String strDate, final String pattern) throws ParseException {
			return getFormatter(pattern).parse(strDate);
		}

		/**
		 * A thread-safe method to format a given Date based-on the given pattern.
		 *
		 * @param theDate            , Date to be formatted
		 * @param pattern            , pattern used to format the date
		 * @return String of formatted date
		 */
		static public String format(final Date theDate, final String pattern) {
			return getFormatter(pattern).format(theDate);
		}
	}
}
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

public class CalendarUtil {

	public static String FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static String LONG = "yyyy-MM-dd HH:mm:ss z";

	public static String NORMAL = "yyyy-MM-dd z";

	public static String SHORT = "yyyy-MM-dd";

	public static String TIME_ZONE = "GMT";
	
	public static Calendar read(String value) {
		Date date=DateUtil.read(value);
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
	}


	public static String write(Calendar value) {
		return DateUtil.write(value.getTime());
	}

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

	public static class ThreadLocalDateFormatter {

		private static final ThreadLocal<Map<String, DateFormat>> FORMATTERS = new ThreadLocal<Map<String, DateFormat>>() {
			protected Map<String, DateFormat> initialValue() {
				return new HashMap<String, DateFormat>();
			}
		};

		static private final DateFormat getFormatter(final String pattern) {
			Map<String, DateFormat> formatterMap = FORMATTERS.get();
			DateFormat df = formatterMap.get(pattern);
			if (null == df) {
				df = new SimpleDateFormat(pattern);
				TimeZone timeZoneGMT = TimeZone.getTimeZone(CalendarUtil.TIME_ZONE);
				df.setTimeZone(timeZoneGMT);
				formatterMap.put(pattern, df);
			}
			return df;
		}

		/**
		 * static public and thread-safe method to parse a date from the given string
		 * 
		 * @param strDate
		 *            : input string to parse
		 * @param pattern
		 *            : date format pattern fo the input string
		 * @return Date value of the input string
		 * @throws ParseException
		 *             if parse exception happened
		 */
		static public Date parse(final String strDate, final String pattern) throws ParseException {
			return getFormatter(pattern).parse(strDate);
		}

		/**
		 * A thread-safe method to format a given Date based-on the given pattern
		 * 
		 * @param theDate
		 *            , Date to be formatted
		 * @param pattern
		 *            , pattern used to format the date
		 * @return String of formatted date
		 */
		static public String format(final Date theDate, final String pattern) {
			return getFormatter(pattern).format(theDate);
		}
	}
}
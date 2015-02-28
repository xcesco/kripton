package com.abubusoft.kripton.binder.transform;

import java.util.Calendar;
import java.util.Date;

/**
 * Transformer between a string and a java.util.Calendar object
 * 
 * @author bulldog
 *
 */
public class CalendarTransform implements Transformable<Calendar> {
	
	private DateTransform dateTransform = new DateTransform();

	public Calendar read(String value) throws Exception {
		Date date = dateTransform.read(value);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public String write(Calendar value) throws Exception {
		Date date = value.getTime();
		String text = dateTransform.write(date);
		return text;
	}

}

package com.example01.datamodel;

import java.util.Date;

import com.abubusoft.kripton.binder.transform.CustomTransform;

/**
 * @author xcesco
 *
 */
public class DateTransform implements CustomTransform<Date> {

	public Date read(String value) throws Exception {
		return new Date(Long.parseLong(value));
	}

	public String write(Date value) throws Exception {
		return String.valueOf(value.getTime());
	}

}

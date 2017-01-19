package com.abubusoft.kripton.example01;

import java.util.Date;

import com.abubusoft.kripton.BindTypeAdapter;

public class DateAdapter implements BindTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) throws Exception {
		return new Date(dataValue);
	}

	@Override
	public Long toData(Date javaValue) throws Exception {
		return javaValue.getTime();
	}

}

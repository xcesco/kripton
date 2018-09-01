package com.abubusoft.kripton.example01;

import java.util.Date;

import com.abubusoft.kripton.TypeAdapter;


public class DateAdapter implements TypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		return new Date(dataValue);
	}

	@Override
	public Long toData(Date javaValue) {
		return javaValue.getTime();
	}

}

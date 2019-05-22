package com.abubusoft.kripton.android.sqlite.adapters;

import java.util.Date;

import com.abubusoft.kripton.android.SqlTypeAdapter;

/**
 * <p>Convert a Long into a util.Date and vice-versa.</p>
 * 
 * @author Francesco Benincasa
 *
 */
public class DateTime2LongTypeAdapter implements SqlTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		if (dataValue == null)
			return null;

		Date result = new Date(dataValue);
		return result;
	}

	@Override
	public Long toData(Date javaValue) {
		if (javaValue == null)
			return null;

		return javaValue.getTime();
	}

	@Override
	public String toString(Date javaValue) {
		return String.valueOf(toData(javaValue));
	}

}

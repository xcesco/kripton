package com.abubusoft.kripton.common;

import java.math.BigInteger;

public class BigIntegerUtils {

	public static BigInteger read(String value) {
		if (!StringUtils.hasText(value)) return null;
		
		return new BigInteger(value);
	}


	public static String write(BigInteger value) {
		if (value==null) return null;
		
		return value.toString();			
	}
}

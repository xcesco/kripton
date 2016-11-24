package com.abubusoft.kripton.common;

import java.math.BigInteger;

public class BigIntegerUtil {

	public static BigInteger read(String value) {
		if (!StringUtil.hasText(value)) return null;
		
		return new BigInteger(value);
	}


	public static String write(BigInteger value) {
		if (value==null) return null;
		
		return value.toString();			
	}
}

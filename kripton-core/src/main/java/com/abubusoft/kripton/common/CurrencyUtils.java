package com.abubusoft.kripton.common;

import java.util.Currency;

public class CurrencyUtils {

	public static String write(Currency value) {
		if (value==null) return null;
		return value.toString();		
	}
	
	public static Currency read(String value) {
		return Currency.getInstance(value);
	}
}

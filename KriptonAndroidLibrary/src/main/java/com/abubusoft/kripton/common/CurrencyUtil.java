package com.abubusoft.kripton.common;

import java.util.Currency;

public class CurrencyUtil {

	public static String write(Currency currency) {
		return currency.toString();		
	}
	
	public static Currency read(String string) {
		return Currency.getInstance(string);
	}
}

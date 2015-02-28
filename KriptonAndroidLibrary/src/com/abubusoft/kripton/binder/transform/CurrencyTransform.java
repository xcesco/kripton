package com.abubusoft.kripton.binder.transform;

import java.util.Currency;

/**
 * Transformer between a string and a java.util.Currency object
 * 
 * @author bulldog
 *
 */
class CurrencyTransform implements Transformable<Currency> {

	public Currency read(String value) throws Exception {
		return Currency.getInstance(value);
	}

	public String write(Currency value) throws Exception {
		return value.toString();
	}

}

package com.abubusoft.kripton.binder.transform;

import java.math.BigDecimal;

/**
 * Transformer between a string and a java.math.BigDecimal object
 * 
 * @author bulldog
 *
 */
class BigDecimalTransform implements Transformable<BigDecimal> {

	public BigDecimal read(String value) throws Exception {
		return new BigDecimal(value);
	}

	public String write(BigDecimal value) throws Exception {
		return value.toString();
	}

}

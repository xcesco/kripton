package com.abubusoft.kripton.processor.sqlite.transform;

import java.math.BigDecimal;

import com.abubusoft.kripton.binder.transform.Transform;

/**
 * Transformer between a string and a java.math.BigDecimal object
 * 
 * @author bulldog
 *
 */
class BigDecimalTransform implements Transform<BigDecimal> {

	public BigDecimal read(String value) throws Exception {
		return new BigDecimal(value);
	}

	public String write(BigDecimal value) throws Exception {
		return value.toString();
	}


}

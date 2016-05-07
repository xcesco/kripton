package com.abubusoft.kripton.processor.sqlite.transform;

import java.math.BigInteger;

import com.abubusoft.kripton.binder.transform.Transform;


/**
 * Transformer between a string and a java.math.BigInteger object
 * 
 * @author bulldog
 *
 */
class BigIntegerTransform implements Transform<BigInteger> {

	public BigInteger read(String value) throws Exception {
		return new BigInteger(value);
	}

	public String write(BigInteger value) throws Exception {
		return value.toString();
	}

}

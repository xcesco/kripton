package com.abubusoft.kripton.binder.transform;

import com.abubusoft.kripton.common.Base64;

/**
 * Transformer between a base64 encoded string and a byte[]
 * 
 * @author bulldog
 *
 */
public class Base64Transform implements Transform<byte[]> {

	public byte[] read(String value) throws Exception {
		return Base64.decode(value);
	}

	public String write(byte[] value) throws Exception {
		return Base64.encode(value);
	}

}

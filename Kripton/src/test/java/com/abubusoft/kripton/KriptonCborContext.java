package com.abubusoft.kripton;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonCborContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.CBOR;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new CBORFactory();
	}
	
}

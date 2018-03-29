package com.abubusoft.kripton;

import com.fasterxml.jackson.core.JsonFactory;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonJsonContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.JSON;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new JsonFactory();
	}
	
}

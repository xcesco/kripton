package com.abubusoft.kripton.binder.context;

import com.abubusoft.kripton.binder.BinderType;
import com.fasterxml.jackson.core.JsonFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
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

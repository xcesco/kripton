package com.abubusoft.kripton.binder.context;

import com.abubusoft.kripton.binder.BinderType;
import com.fasterxml.jackson.core.JsonFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class JsonBinderContext extends JacksonContext {

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

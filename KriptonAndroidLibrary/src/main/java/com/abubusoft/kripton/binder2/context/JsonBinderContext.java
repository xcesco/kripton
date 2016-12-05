package com.abubusoft.kripton.binder2.context;

import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
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

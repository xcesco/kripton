package com.abubusoft.kripton.binder2.context;

import com.abubusoft.kripton.binder2.BinderType;
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
	
	@Override
	public <E> E parse(byte[] is, Class<E> objectClazz) {
		return mapperFor(objectClazz).parse(this, is);
	}
	
}

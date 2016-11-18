package com.abubusoft.kripton.binder2.context;

import com.abubusoft.kripton.binder2.core.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class PropertiesBinderContext extends JacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.PROPERTIES;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new JavaPropsFactory();
	}
	
}

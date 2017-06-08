package com.abubusoft.kripton;

import com.abubusoft.kripton.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonPropertiesContext extends AbstractJacksonContext {

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

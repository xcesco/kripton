package com.abubusoft.kripton;

import com.abubusoft.kripton.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonPropertiesContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.PROPERTIES;
	}
	
	@Override
	protected JsonFactory createInnerFactory()
	{
		return new JavaPropsFactory();
	}
}

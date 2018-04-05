package com.abubusoft.kripton;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonSmileContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.SMILE;
	}
	
	@Override
	protected JsonFactory createInnerFactory()
	{
		return new SmileFactory();
	}
	
}

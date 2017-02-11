package com.abubusoft.kripton;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.core.AbstractJacksonContext;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonYamlContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.YAML;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new YAMLFactory();
	}
	
}

package com.abubusoft.kripton;

import com.abubusoft.kripton.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonYamlContext extends AbstractJacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.YAML;
	}
	
	@Override
	protected JsonFactory createInnerFactory()
	{
		return new YAMLFactory();
	}
	
}

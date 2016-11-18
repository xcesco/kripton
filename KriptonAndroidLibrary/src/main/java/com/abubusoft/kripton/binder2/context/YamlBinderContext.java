package com.abubusoft.kripton.binder2.context;

import com.abubusoft.kripton.binder2.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class YamlBinderContext extends JacksonContext {

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

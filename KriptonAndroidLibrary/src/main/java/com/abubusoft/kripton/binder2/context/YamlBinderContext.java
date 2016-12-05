package com.abubusoft.kripton.binder2.context;

import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

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
	
	@Override
	public <E> E parse(byte[] is, Class<E> objectClazz) {
		return mapperFor(objectClazz).parse(this, is);
	}

	@Override
	public <E> void serialize(E object, ParameterizedType parameterizedType, OutputStream os) {
		// TODO Auto-generated method stub
		
	}
	
}

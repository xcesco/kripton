package com.abubusoft.kripton.android.sql;

import java.io.Serializable;
import java.util.HashMap;

import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;

public abstract class AbstractAdapter implements DatabaseAdapter, Serializable {

	private static final long serialVersionUID = 2750525613358265759L;

	public AbstractAdapter() {
		map=new HashMap<>();
	}
	
	protected Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

	protected AbstractDatabase database;
	
	public void setDatabase(AbstractDatabase database) {
		this.database = database;
	}

	protected HashMap<String, Class<?>> map;
	
	public boolean registryBean(Object bean) throws MappingException
	{
		if (bean!=null)
		{
			
		}
		return false;
	}
	
	
	protected String toDbName(String value)
	{
		return converter.convert(value);
	}
	

}

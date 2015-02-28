package com.abubusoft.kripton.android.sql;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public abstract class AbstractDatabase implements Serializable {

	private static final long serialVersionUID = -2744236708542067417L;
	
	protected HashMap<Class<?>, String> field2Column;

	public AbstractDatabase()
	{
		this.field2Column=new HashMap<Class<?>, String>();
	}
	
	public abstract String class2columnType(Field field);
	
}

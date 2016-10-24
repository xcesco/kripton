package com.abubusoft.kripton.android.kripton20;

import org.junit.Test;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;

public class Issue20Test1 {

	@BindType
	public class Bean1
	{
		public String value;
	}
	
	@BindType
	@BindTypeXml
	public class Bean2
	{
		public String value;
	}
	
	@Test
	public void test01() throws MappingException {
		MappingSchema.fromClass(Bean1.class);
	}
	
	@Test//(expected=MappingException.class)
	public void test02() throws MappingException {
		MappingSchema.fromClass(Bean2.class);
	}
}

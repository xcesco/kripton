package com.abubusoft.kripton.examples;

import java.util.logging.Logger;

import org.junit.Test;

import com.abubusoft.kripton.android.BinderFactory;
import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.BinderWriter;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;
import com.abubusoft.kripton.binder.exception.WriterException;

/**
 * Simple example
 * @author xcesco
 *
 */
public class Example01 {

	Logger logger=Logger.getAnonymousLogger();
	
	@Test
	public void test01() throws WriterException, MappingException, ReaderException
	{
		SimpleBean bean=new SimpleBean();
		
		bean.setAge(25);
		bean.setName("John");
		
		// create json writer binder
		BinderWriter jsonWriter=BinderFactory.getJSONWriter();
		String jsonBuffer=jsonWriter.write(bean);
		
		// create xml writer binder
		BinderWriter xmlWriter=BinderFactory.getXMLWriter();
		String xmlBuffer=xmlWriter.write(bean);
		
		logger.info("outputJson:\n"+jsonBuffer);		
		logger.info("outputXml:\n"+xmlBuffer);
		
		SimpleBean beanResult;
		// create json reader binder
		BinderReader jsonReader=BinderFactory.getJSONReader();
		beanResult = jsonReader.read(SimpleBean.class, jsonBuffer);
		
		// create xml reader binder
		BinderReader xmlReader=BinderFactory.getXMLReader();
		beanResult = xmlReader.read(SimpleBean.class, xmlBuffer);

	}
}

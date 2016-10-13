package com.abubusoft.kripton.android.kripton54;

import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderFactory.XmlReaderType;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public class Test54 {

	@Test
	public void testWriteXmlNull() throws MappingException, WriterException
	{
		// write
		BinderWriter writer=BinderFactory.getXMLWriter();
		
		writer.write(null);
	}
	
	@Test
	public void testReadXml0Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=BinderFactory.getXMLReader();
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testReadXml1Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=BinderFactory.getXMLReader(XmlReaderType.DOM);
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testReadXml2Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=BinderFactory.getXMLReader(XmlReaderType.SAX);
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testWriteJsonNull() throws MappingException, WriterException
	{
		// write
		BinderWriter writer=BinderFactory.getJSONWriter();
		
		writer.write(null);
	}
	
	@Test
	public void testReadJson0Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=BinderFactory.getJSONReader();
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
}

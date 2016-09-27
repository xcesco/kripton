package com.abubusoft.kripton.processor.kripton42;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton42 extends BaseProcessorTest {
	
	private Restaurant bean;

	@Before
	public void setup()
	{
		bean=new Restaurant();
		bean.id=-1;
		bean.address="dummy address";
		bean.latitude=24.0;
		bean.longitude=20.0;
		bean.name="dummy restaurant";
	}
	
	@Test
	public void testJSON() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.json");
		JsonFactory jsonFactory = new JsonFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();
	}
	
	@Test
	public void testXML() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.xml");
		XmlFactory jsonFactory = new XmlFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		ToXmlGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		generator.configure(Feature.WRITE_XML_DECLARATION, true);					
		generator.initGenerator();
		
		generator.setNextName(new QName("restaurant"));
				
		RestaurantMapper.instance().writeXml(generator, bean);
		
		generator.close();
	}
	
	@Test
	public void testKritponXML() throws IOException, MappingException, WriterException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant_kripton.xml");
		
		BinderWriter writer = BinderFactory.getJSONWriter(BinderOptions.build().indent(true));
		writer.write(bean, new FileOutputStream(file));
	}
	
	@Test
	public void testYAML() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.yaml");
		JsonFactory jsonFactory = new YAMLFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();
	}
	
	@Test
	public void testCBOR() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.cbor");
		JsonFactory jsonFactory = new CBORFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();
	}
	
	@Test
	public void testSmile() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.smile");
		JsonFactory jsonFactory = new SmileFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();
	}
	
	@Test
	public void testProperties() throws IOException
	{
		File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.properties");
		JsonFactory jsonFactory = new JavaPropsFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();
	}


	@Test
	public void testSelectError() throws IOException {
		//buildTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}	
	
	
}

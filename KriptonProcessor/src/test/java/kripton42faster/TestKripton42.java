/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton42faster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import base.BaseProcessorTest;

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
	/*	File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.json");
		JsonFactory jsonFactory = new JsonFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();*/
	}
	
	@Test
	public void testXML() throws IOException
	{
	/*	File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.xml");
		XmlFactory jsonFactory = new XmlFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		ToXmlGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		generator.configure(Feature.WRITE_XML_DECLARATION, true);					
		generator.initGenerator();
		
		generator.setNextName(new QName("restaurant"));
				
		RestaurantMapper.instance().writeXml(generator, bean);
		
		generator.close();*/
	}
	
	@Test
	public void testKritponXML() throws IOException, MappingException, WriterException
	{
		/*File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant_kripton.xml");
		
		BinderWriter writer = BinderFactory.getJSONWriter(BinderOptions.build().indent(true));
		writer.write(bean, new FileOutputStream(file));*/
	}
	
	@Test
	public void testYAML() throws IOException
	{
	/*	File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.yaml");
		JsonFactory jsonFactory = new YAMLFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();*/
	}
	
	@Test
	public void testCBOR() throws IOException
	{
		/*File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.cbor");
		JsonFactory jsonFactory = new CBORFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();*/
	}
	
	@Test
	public void testSmile() throws IOException
	{
		/*File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.smile");
		JsonFactory jsonFactory = new SmileFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();*/
	}
	
	@Test
	public void testProperties() throws IOException
	{
	/*	File file=PathSourceType.SRC_TEST_RESULT.createFile("com/abubusoft/kripton/processor/kripton42/"+"restaurant.properties");
		JsonFactory jsonFactory = new JavaPropsFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		generator.useDefaultPrettyPrinter();
		
		RestaurantMapper.instance().write(generator, bean);
		
		generator.close();*/
	}


	@Test
	public void testSelectError() throws IOException {
		//buildTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}	
	
	
}

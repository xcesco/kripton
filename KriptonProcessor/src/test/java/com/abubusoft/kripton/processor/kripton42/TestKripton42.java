package com.abubusoft.kripton.processor.kripton42;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton42 extends BaseProcessorTest {
	
	//@Test
	public void testFormats() throws IOException
	{
		Restaurant bean=new Restaurant();
		bean.address="dummy address";
		bean.latitude=24.0;
		bean.longitude=20.0;
		bean.name="dummy restaurant";
		
		File file=new File("src/test/result/com/abubusoft/kripton/processor/kripton42/"+"restaurant.json");
		JsonFactory jsonFactory = new JsonFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8); // or Stream, Reader
		//generator.wr
		
		/*JsonFactory jsonFactory = new JsonFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory 
		  JsonParser jp = jsonFactory.createJsonParser(file); // or URL, Stream, Reader, String, byte[]*/
	}

	@Test
	public void testSelectError() throws IOException {
		//buildTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}	
	
	
}

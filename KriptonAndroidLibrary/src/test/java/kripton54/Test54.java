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
package kripton54;

import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonBinder.XmlReaderType;
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
		BinderWriter writer=KriptonBinder.getXmlWriter();
		
		writer.write(null);
	}
	
	@Test
	public void testReadXml0Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=KriptonBinder.getXmlReader();
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testReadXml1Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=KriptonBinder.getXmlReader(XmlReaderType.DOM);
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testReadXml2Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=KriptonBinder.getXmlReader(XmlReaderType.SAX);
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
	@Test
	public void testWriteJsonNull() throws MappingException, WriterException
	{
		// write
		BinderWriter writer=KriptonBinder.getJsonWriter();
		
		writer.write(null);
	}
	
	@Test
	public void testReadJson0Null() throws MappingException, WriterException, ReaderException
	{
		InputStream stream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));// );
		
		// write
		BinderReader reader=KriptonBinder.getJsonReader();
		
		Bean result=reader.read(Bean.class, stream);
		assertNull(result);
	}
	
}

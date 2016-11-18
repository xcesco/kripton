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
package all;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonBinder.XmlReaderType;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.binder.json.JsonReader;
import com.abubusoft.kripton.binder.json.JsonWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public abstract class IssueBaseTest<E> extends BaseTest {

	protected E beanInput;
	
	protected E beanOutput;

	public void check(E input, BinderWriter writer, BinderReader reader) throws WriterException, MappingException, ReaderException, IOException {

		// test string
		String buffer = writer.write(input);
		logger.log(Level.INFO, "\n"+buffer);
		Object output = reader.read(input.getClass(), buffer);
		String buffer2 = writer.write(output);
		logger.log(Level.INFO, "\n"+buffer2);

		// assert string and objects with reflection
		Assert.assertTrue(buffer.equals(buffer2));
		assertReflectionEquals(input, output);

		// test file
		File file = File.createTempFile(getClass().getSimpleName() + "-", "-" + String.valueOf((new Date()).getTime()) + ".txt");
		logger.log(Level.INFO, "test on file " + file.getAbsolutePath());
		file.deleteOnExit();
		FileOutputStream FOS = new FileOutputStream(file);
		writer.write(input, FOS);
		FOS.close();

		FileInputStream FIS = new FileInputStream(file);
		beanOutput = (E) reader.read(beanInput.getClass(), FIS);
		FIS.close();
		assertReflectionEquals(input, beanOutput);
	}

	/**
	 * test JSON Unformatted
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(false).useApostrophe(true);
		JsonWriter writer = (JsonWriter) KriptonBinder.getJsonWriter(format);
		JsonReader reader = (JsonReader) KriptonBinder.getJsonReader();

		check(beanInput, writer, reader);
	}
	
	/**
	 * test JSON formatted
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(true).useApostrophe(true);
		JsonWriter writer = (JsonWriter) KriptonBinder.getJsonWriter(format);
		JsonReader reader = (JsonReader) KriptonBinder.getJsonReader();

		check(beanInput, writer, reader);
	}

	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(false).useApostrophe(true);
		BinderWriter writer = KriptonBinder.getXmlWriter(format);
		BinderReader reader = KriptonBinder.getXmlReader(XmlReaderType.DOM, BinderOptions.build().indent(false));

		check(beanInput, writer, reader);
	}
	
	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(true).useApostrophe(true);
		BinderWriter writer = KriptonBinder.getXmlWriter(format);
		BinderReader reader = KriptonBinder.getXmlReader(XmlReaderType.DOM,BinderOptions.build().indent(false));

		check(beanInput, writer, reader);
	}

	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(false);
		BinderWriter writer = KriptonBinder.getXmlWriter(format);
		BinderReader reader = KriptonBinder.getXmlReader(XmlReaderType.SAX);

		check(beanInput, writer, reader);
	}
	
	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		BinderOptions format=BinderOptions.build().indent(true);
		BinderWriter writer = KriptonBinder.getXmlWriter(format);
		BinderReader reader = KriptonBinder.getXmlReader(XmlReaderType.SAX);

		check(beanInput, writer, reader);
	}
}

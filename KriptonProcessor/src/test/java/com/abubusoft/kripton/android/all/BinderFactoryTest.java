/**
 * 
 */
package com.abubusoft.kripton.android.all;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderFactory.XmlReaderType;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;

/**
 * @author xcesco
 *
 */
public class BinderFactoryTest {

	@Test
	public void test() {
		{
			BinderWriter writer1 = BinderFactory.getJSONWriter();
			BinderWriter writer2 = BinderFactory.getJSONWriter(BinderOptions.build());

			assertReflectionEquals(writer1, writer2);
		}

		{
			BinderWriter writer1 = BinderFactory.getXMLWriter();
			BinderWriter writer2 = BinderFactory.getXMLWriter(BinderOptions.build());

			assertReflectionEquals(writer1, writer2);
		}
		
		{
			BinderReader reader1 = BinderFactory.getJSONReader();
			BinderReader reader2 = BinderFactory.getJSONReader(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));

			assertReflectionEquals(reader1, reader2);
		}

		{
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(XmlReaderType.SAX, BinderOptions.build());

			assertReflectionEquals(reader1, reader2);
		}
		
		{
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(XmlReaderType.DOM, BinderOptions.build());

			Assert.assertNotEquals(reader1, reader2);
		}
		
		{
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(XmlReaderType.SAX, BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));

			assertReflectionEquals(reader1, reader2);
		}

	}
}

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

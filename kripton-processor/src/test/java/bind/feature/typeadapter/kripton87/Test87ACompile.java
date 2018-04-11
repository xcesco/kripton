/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.feature.typeadapter.kripton87;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;

import bind.AbstractBindTypeProcessorTest;
import bind.feature.typeadapter.kripton87.Bean87A_1;
import bind.feature.typeadapter.kripton87.Bean87A_2;
import bind.feature.typeadapter.kripton87.Bean87A_3;
import bind.feature.typeadapter.kripton87.Bean87A_5;
import bind.feature.typeadapter.kripton87.Bean87A_6;
import bind.feature.typeadapter.kripton87.Bean87A_7;
import bind.feature.typeadapter.kripton87.BooleanBigDecimalTypeAdapter;
import bind.feature.typeadapter.kripton87.BooleanByteArrayTypeAdapter;
import bind.feature.typeadapter.kripton87.DateLongTypeAdapter;
import bind.feature.typeadapter.kripton87.Enum87A;
import bind.feature.typeadapter.kripton87.Enum87BigIntegerTypeAdapter;
import bind.feature.typeadapter.kripton87.Enum87IntegerTypeAdapter;
import bind.feature.typeadapter.kripton87.StringInverterTypeAdapter;
import bind.feature.typeadapter.kripton87.StringUrlTypeAdapter;
import bind.feature.typeadapter.kripton87.UrlByteArrayTypeAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class Test87ACompile.
 */
public class Test87ACompile extends AbstractBindTypeProcessorTest {

	/**
	 * Test compile 1.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_1() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(DateLongTypeAdapter.class, StringInverterTypeAdapter.class, UrlByteArrayTypeAdapter.class, Bean87A_1.class);
	}
	
	/**
	 * Test compile 2.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_2() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(UrlByteArrayTypeAdapter.class, Bean87A_2.class);
	}
	
	/**
	 * Test compile 3.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_3() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BooleanByteArrayTypeAdapter.class, Enum87IntegerTypeAdapter.class, Bean87A_3.class, Enum87A.class);
	}
	
	/**
	 * Test compile 4.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_4() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAnnotationException.class);
		buildBindProcessorTest(BooleanBigDecimalTypeAdapter.class, Enum87BigIntegerTypeAdapter.class, Bean87A_4.class, Enum87A.class);
	}
	
	/**
	 * Test compile 5.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_5() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BooleanBigDecimalTypeAdapter.class, Enum87BigIntegerTypeAdapter.class, Bean87A_5.class, Enum87A.class);
	}
	
	/**
	 * Test compile 6.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_6() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(StringUrlTypeAdapter.class, Bean87A_6.class, Enum87A.class);
	}
	
	/**
	 * Test compile 7.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_7() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(StringInverterTypeAdapter.class, Bean87A_7.class, Enum87A.class);
	}
	
	/**
	 * Test compile 8.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile_8() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAnnotationException.class);
		buildBindProcessorTest(StringEnum87ATypeAdapter.class, Bean87A_8.class, Enum87A.class);
	}
}

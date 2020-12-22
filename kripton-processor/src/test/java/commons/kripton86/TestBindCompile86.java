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
package commons.kripton86;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;

import bind.AbstractBindTypeProcessorTest;
import commons.kripton86.test3.Bean3_1;
import commons.kripton86.test3.Bean3_2;
import commons.kripton86.test3.Bean3_3;
import commons.kripton86.test3.Bean3_4;
import commons.kripton86.test6.Bean6_1;
import commons.kripton86.test6.Bean6_2;


/**
 * The Class TestBindCompile86.
 */
public class TestBindCompile86 extends AbstractBindTypeProcessorTest {

	/**
	 * Test IncompatibleAttributesInAnnotationException.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test1Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_1.class);
	}
	
	/**
	 * Test IncompatibleAttributesInAnnotationException.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_2.class);
	}
	
	/**
	 * Test IncompatibleAttributesInAnnotationException.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test3Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_3.class);
	}

	/**
	 * Test IncompatibleAttributesInAnnotationException.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test4Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_4.class);
	}
	
	/**
	 * Test 6 1 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test6_1Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Bean6_1.class);
	}
	
	/**
	 * Test 6 2 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test6_2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Bean6_2.class);
	}
}

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
package bind.kripton76Errors;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;

import bind.AbstractBindTypeProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class Test76Value.
 */
public class Test76Value extends AbstractBindTypeProcessorTest {
	
	/**
	 * Test compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCompile() throws InstantiationException, IllegalAccessException, IOException 
	{		
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanValue76.class, BeanEnum.class);
	}
	
	/**
	 * Test array on xml value.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testArrayOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Array.class, BeanEnum.class);
	}
	
	/**
	 * Test list on xml value.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testListOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76List.class, BeanEnum.class);
	}
	
	/**
	 * Test set on xml value.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testSetOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Set.class, BeanEnum.class);
	}
	
	/**
	 * Test map on xml value.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testMapOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Map.class, BeanEnum.class);
	}
	
	
	
}

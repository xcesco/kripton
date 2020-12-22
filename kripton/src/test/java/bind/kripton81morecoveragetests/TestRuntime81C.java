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
package bind.kripton81morecoveragetests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntime81C.
 */
public class TestRuntime81C extends AbstractBaseTest {

	/**
	 * Test runtime.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime() throws Exception {
		assertTrue(Bean81CBindMap.class.getName()!=null);
		
		Bean81C bean=createBean();
		check(bean);
		
		bean.valueEnum=null;
		check(bean);
	}

	/**
	 * Creates the bean.
	 *
	 * @return the bean 81 C
	 */
	private Bean81C createBean() {
		Bean81C result=new Bean81C();
		
		result.id=24;
		result.valueEnum=Bean81Enum.VALUE_1;
		return result;
	}
	
}

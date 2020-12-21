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
package bind.kripton70;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntime70A.
 */
public class TestRuntime70A extends AbstractBaseTest {
	
	/**
	 * Test run 70 A 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun70A_1() throws Exception {
		assertNotNull(Bean70ABindMap.class.toString());
		
		Bean70A bean = new Bean70A();
		bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();

		check(bean);
	}

	/**
	 * test file bean empty.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun70A_2() throws Exception {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
	
	/**
	 * test file bean null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun70A_3() throws Exception {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		//bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
}

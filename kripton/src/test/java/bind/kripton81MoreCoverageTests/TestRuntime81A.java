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
package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime81A.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestRuntime81A extends AbstractBaseTest {

	/**
	 * Test runtime.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime() throws Exception {
		assertTrue(Bean81ABindMap.class.getName()!=null);
		
		Bean81A bean=createBean();
		check(bean);
		
		bean.valueEnum=null;
		bean.valueBidDecimal=null;
		bean.valueBidInteger=null;
		check(bean);
	}

	/**
	 * Creates the bean.
	 *
	 * @return the bean 81 A
	 */
	private Bean81A createBean() {
		Bean81A result=new Bean81A();
		
		result.id=24;
		result.valueEnum=Bean81Enum.VALUE_1;
		result.valueBidDecimal=BigDecimal.ONE;
		result.valueBidInteger=BigInteger.TEN;
		return result;
	}
	
}

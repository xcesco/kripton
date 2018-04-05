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
package bind.kripton74Map;

import java.util.HashMap;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime74.
 */
public class TestRuntime74 extends AbstractBaseTest {

	/**
	 * Creates the bean.
	 *
	 * @return the bean 74
	 */
	public Bean74 createBean() {
		Bean74 bean = new Bean74();
		
		Bean74 bean1 = new Bean74();
		bean1.valueString="nice to meet you";

		bean.valueMapStringInteger = new HashMap<>();
		bean.valueMapStringInteger.put("key1", 10);
		bean.valueMapStringInteger.put("key2", 20);
		
		bean.valueMapEnumBean=new HashMap<>();
		bean.valueMapEnumBean.put(Enum74.VALUE_3, null);
		bean.valueMapEnumBean.put(Enum74.VALUE_1, bean1);		
		bean.valueMapEnumBean.put(Enum74.VALUE_2, null);
		
		bean.valueMapIntByteArray=new HashMap<>();
		byte[] a=new byte[23];
		bean.valueMapIntByteArray.put(20, new byte[0]);
		bean.valueMapIntByteArray.put(23, a);
		bean.valueMapIntByteArray.put(27, null);
		
		bean.valueMapBeanLocale=new HashMap<>();
		bean.valueMapBeanLocale.put(new Bean74(), Locale.CANADA);
		bean.valueMapBeanLocale.put(new Bean74(), null);
		
		//bean.valueString="hello";

		return bean;
	}

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception {
		Assert.assertNotNull(new Bean74BindMap());

		Bean74 bean = createBean();

		check(bean);
	}


}

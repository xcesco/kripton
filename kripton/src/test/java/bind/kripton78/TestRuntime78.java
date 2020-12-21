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
package bind.kripton78;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntime78.
 */
public class TestRuntime78 extends AbstractBaseTest {

	/**
	 * Creates the bean.
	 *
	 * @return the bean element 78
	 */
	public BeanElement78 createBean() {
		BeanElement78 bean = new BeanElement78();
				
		bean.valueMapIntByteArray=new HashMap<>();
		byte[] a=new byte[23];
		bean.valueMapIntByteArray.put(20, new byte[0]);
		bean.valueMapIntByteArray.put(23, a);
		bean.valueMapIntByteArray.put(27, null);
		
		bean.valueListByteArray=new ArrayList<byte[]>();
		bean.valueListByteArray.add(a);
		bean.valueListByteArray.add(new byte[0]);
		bean.valueListByteArray.add(null);
		
		return bean;
	}

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception {
		Assert.assertNotNull(new BeanElement78BindMap());

		BeanElement78 bean = createBean();

		check(bean);
	}


}

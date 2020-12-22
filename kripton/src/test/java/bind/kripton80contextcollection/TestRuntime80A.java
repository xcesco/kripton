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
package bind.kripton80contextcollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderType;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntime80A.
 */
public class TestRuntime80A extends AbstractBaseTest {

	/**
	 * Test run 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun_1() throws Exception {
		Assert.assertNotNull(new Bean80ABindMap());
				
		List<Bean80A> list=new ArrayList<>();
		list.add(createBean());
		list.add(new Bean80A());
		
		checkCollection(list, Bean80A.class, BinderType.JSON, BinderType.CBOR, BinderType.YAML, BinderType.PROPERTIES);
	}
	
	/**
	 * Test run 2.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun_2() throws Exception {
		Assert.assertNotNull(new Bean80ABindMap());
		
		check(createBean());		
		check(new Bean80A());		
	}
	
	/**
	 * Creates the bean.
	 *
	 * @return the bean 80 A
	 */
	public Bean80A createBean()
	{
		Bean80A bean = new Bean80A();

		bean.id = 25;
		bean.valueBean = new Bean80A();
		bean.valueBean.id = 45;
		bean.valueString = "\"ciao";
		
		return bean;
	}

}

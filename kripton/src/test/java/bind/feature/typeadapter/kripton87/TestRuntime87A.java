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

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

import bind.AbstractBaseTest;
import bind.feature.typeadapter.kripton87.Bean87A_1;
import bind.feature.typeadapter.kripton87.Bean87A_2;
import bind.feature.typeadapter.kripton87.Bean87A_3;
import bind.feature.typeadapter.kripton87.Bean87A_5;
import bind.feature.typeadapter.kripton87.Bean87A_6;
import bind.feature.typeadapter.kripton87.Bean87A_7;
import bind.feature.typeadapter.kripton87.Enum87A;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime87A.
 */
public class TestRuntime87A extends AbstractBaseTest {

	/**
	 * Test runtime 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime1() throws Exception  {
		assertTrue(Bean87A_1BindMap.class.getName()!=null);
		
		Bean87A_1 bean=createBeanA();
		check(bean);
		bean.valueDate=null;
		bean.valueDescription=null;
		check(bean);
	}		
	
	/**
	 * Creates the bean A.
	 *
	 * @return the bean 87 A 1
	 */
	private Bean87A_1 createBeanA() {
		Bean87A_1 result=new Bean87A_1();
		
		result.valueDate=new Date();
		result.valueDescription="hello";
		
		return result;
	}	
	
	/**
	 * Test runtime 2.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime2() throws Exception  {
		assertTrue(Bean87A_2BindMap.class.getName()!=null);
		
		Bean87A_2 bean=createBeanA_2();
		check(bean);
		bean.attributeURL=null;
		bean.dataURL=null;
		bean.elementURL=null;
		check(bean);
	}
	
	/**
	 * Test runtime 3.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime3() throws Exception  {
		assertTrue(Bean87A_3BindMap.class.getName()!=null);
		
		Bean87A_3 bean=createBeanA_3();
		check(bean);
		bean.attributeBoolean=null;
		bean.dataBoolean=null;
		bean.elementBoolean=null;
		bean.elementEnum=null;
		
		check(bean);
	}	
	
	/**
	 * Test runtime 5.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime5() throws Exception  {
		assertTrue(Bean87A_5BindMap.class.getName()!=null);
		
		Bean87A_5 bean=createBeanA_5();
		check(bean);
		bean.attributeBoolean=null;
		bean.dataBoolean=null;
		bean.elementBoolean=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime 6.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime6() throws Exception  {
		assertTrue(Bean87A_6BindMap.class.getName()!=null);
		
		Bean87A_6 bean=createBeanA_6();
		check(bean);
		bean.attributeString=null;
		bean.dataString=null;
		bean.elementString=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime 7.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime7() throws Exception  {
		assertTrue(Bean87A_7BindMap.class.getName()!=null);
		
		Bean87A_7 bean=createBeanA_7();
		check(bean);
		bean.attributeString=null;
		bean.dataString=null;
		bean.elementString=null;
		
		check(bean);
	}
	
	/**
	 * Creates the bean A 2.
	 *
	 * @return the bean 87 A 2
	 * @throws MalformedURLException the malformed URL exception
	 */
	private Bean87A_2 createBeanA_2() throws MalformedURLException {
		Bean87A_2 result=new Bean87A_2();
		
		result.attributeURL=new URL("http://www.google.it");
		result.dataURL=new URL("http://www.google.it");
		result.elementURL=new URL("http://www.google.it");
		
		
		return result;
	}
	
	/**
	 * Creates the bean A 3.
	 *
	 * @return the bean 87 A 3
	 * @throws MalformedURLException the malformed URL exception
	 */
	private Bean87A_3 createBeanA_3() throws MalformedURLException {
		Bean87A_3 result=new Bean87A_3();
		
		result.attributeBoolean=true;
		result.dataBoolean=true;
		result.elementBoolean=true;
		result.elementEnum=Enum87A.VALUE_2;
		
		return result;
	}
	
	/**
	 * Creates the bean A 5.
	 *
	 * @return the bean 87 A 5
	 * @throws MalformedURLException the malformed URL exception
	 */
	private Bean87A_5 createBeanA_5() throws MalformedURLException {
		Bean87A_5 result=new Bean87A_5();
		
		result.attributeBoolean=true;
		result.dataBoolean=true;
		result.elementBoolean=true;
		
		return result;
	}
	
	/**
	 * Creates the bean A 6.
	 *
	 * @return the bean 87 A 6
	 * @throws MalformedURLException the malformed URL exception
	 */
	private Bean87A_6 createBeanA_6() throws MalformedURLException {
		Bean87A_6 result=new Bean87A_6();
		
		result.attributeString="http://www.google.it";
		result.dataString="http://www.google.it";
		result.elementString="http://www.google.it";
		
		return result;
	}
	
	/**
	 * Creates the bean A 7.
	 *
	 * @return the bean 87 A 7
	 * @throws MalformedURLException the malformed URL exception
	 */
	private Bean87A_7 createBeanA_7() throws MalformedURLException {
		Bean87A_7 result=new Bean87A_7();
		
		result.attributeString="http://www.google.it";
		result.dataString="http://www.google.it";
		result.elementString="http://www.google.it";
		
		return result;
	}
	
}

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
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime81V.
 */
public class TestRuntime81V extends AbstractBaseTest {

	/**
	 * Test runtime 0.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime0() throws Exception  {
		assertTrue(Bean81VBindMap.class.getName()!=null);
		
		Bean81V bean=createBean0();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	/**
	 * Test runtime 2.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime2() throws Exception  {
		assertTrue(Bean81V2BindMap.class.getName()!=null);
		
		Bean81V2 bean=createBean2();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	/**
	 * Test runtime 3.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime3() throws Exception  {
		assertTrue(Bean81V3BindMap.class.getName()!=null);
		
		Bean81V3 bean=createBean3();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	/**
	 * Test runtime 4.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntime4() throws Exception  {
		assertTrue(Bean81V4BindMap.class.getName()!=null);
		
		Bean81V4 bean=createBean4();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	/**
	 * Creates the bean 0.
	 *
	 * @return the bean 81 V
	 */
	private Bean81V createBean0() {
		Bean81V result=new Bean81V();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
	/**
	 * Creates the bean 2.
	 *
	 * @return the bean 81 V 2
	 */
	private Bean81V2 createBean2() {
		Bean81V2 result=new Bean81V2();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
	/**
	 * Creates the bean 3.
	 *
	 * @return the bean 81 V 3
	 */
	private Bean81V3 createBean3() {
		Bean81V3 result=new Bean81V3();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
	/**
	 * Creates the bean 4.
	 *
	 * @return the bean 81 V 4
	 */
	private Bean81V4 createBean4() {
		Bean81V4 result=new Bean81V4();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
}

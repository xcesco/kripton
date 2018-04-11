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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime81GHILMNOP.
 */
public class TestRuntime81GHILMNOP extends AbstractBaseTest {

	/**
	 * Creates the bean G.
	 *
	 * @return the bean 81 G
	 */
	private Bean81G createBeanG() {
		Bean81G result=new Bean81G();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	/**
	 * Creates the bean H.
	 *
	 * @return the bean 81 H
	 */
	private Bean81H createBeanH() {
		Bean81H result=new Bean81H();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	/**
	 * Creates the bean I.
	 *
	 * @return the bean 81 I
	 */
	private Bean81I createBeanI() {
		Bean81I result=new Bean81I();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	/**
	 * Creates the bean L.
	 *
	 * @return the bean 81 L
	 */
	private Bean81L createBeanL() {
		Bean81L result=new Bean81L();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	/**
	 * Creates the bean M.
	 *
	 * @return the bean 81 M
	 */
	private Bean81M createBeanM() {
		Bean81M result=new Bean81M();
		
		result.id=24;
		result.valueString1 ="value1";
		result.valueString2 ="value2";
		return result;
	}
	
	/**
	 * Creates the bean N.
	 *
	 * @return the bean 81 N
	 */
	private Bean81N createBeanN() {
		Bean81N result=new Bean81N();
		
		result.id=24;
		result.valueString1 ="value1";
		result.valueString2 ="value2";
		return result;
	}
	
	/**
	 * Creates the bean O.
	 *
	 * @return the bean 81 O
	 */
	private Bean81O createBeanO() {
		Bean81O result=new Bean81O();
		
		result.id=24;
		result.valueCurrency =Currency.getInstance(Locale.CANADA);
		result.valueLocale =Locale.CHINA;
		return result;
	}
	
	/**
	 * Creates the bean P.
	 *
	 * @return the bean 81 P
	 */
	private Bean81P createBeanP() {
		Bean81P result=new Bean81P();
		
		result.id=24;
		result.valueCurrency =Currency.getInstance(Locale.CANADA);
		result.valueLocale =Locale.CHINA;
		return result;
	}

	/**
	 * Creates the bean R.
	 *
	 * @return the bean 81 R
	 */
	private Bean81R createBeanR() {
		Bean81R bean = new Bean81R();
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		return bean;
	}
	
	/**
	 * Creates the bean S.
	 *
	 * @return the bean 81 S
	 */
	private Bean81S createBeanS() {
		Bean81S bean = new Bean81S();
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		return bean;
	}
	
	/**
	 * Creates the bean T.
	 *
	 * @return the bean 81 T
	 */
	private Bean81T createBeanT() {
		Bean81T bean = new Bean81T();
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		return bean;
	}
	
	/**
	 * Creates the bean U.
	 *
	 * @return the bean 81 U
	 */
	private Bean81U createBeanU() {
		Bean81U bean = new Bean81U();
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		return bean;
	}
	
	/**
	 * Test runtime G.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeG() throws Exception {
		assertTrue(Bean81GBindMap.class.getName()!=null);
		
		Bean81G bean=createBeanG();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	
	/**
	 * Test runtime H.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeH() throws Exception {
		assertTrue(Bean81HBindMap.class.getName()!=null);
		
		Bean81H bean=createBeanH();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	
	/**
	 * Test runtime I.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeI() throws Exception {
		assertTrue(Bean81IBindMap.class.getName()!=null);
		
		Bean81I bean=createBeanI();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime L.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeL() throws Exception {
		assertTrue(Bean81LBindMap.class.getName()!=null);
		
		Bean81L bean=createBeanL();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime M.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeM() throws Exception  {
		assertTrue(Bean81MBindMap.class.getName()!=null);
		
		Bean81M bean=createBeanM();
		check(bean);
		
		bean.valueString1=null;
		bean.valueString1=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime N.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeN() throws Exception  {
		assertTrue(Bean81NBindMap.class.getName()!=null);
		
		Bean81N bean=createBeanN();
		check(bean);
		
		bean.valueString1=null;
		bean.valueString1=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime O.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeO() throws Exception  {
		assertTrue(Bean81OBindMap.class.getName()!=null);
		
		Bean81O bean=createBeanO();
		check(bean);
		
		bean.valueCurrency=null;
		bean.valueLocale=null;
		
		check(bean);
	}
	
	/**
	 * Test runtime P.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeP() throws Exception  {
		assertTrue(Bean81PBindMap.class.getName()!=null);
		
		Bean81P bean=createBeanP();
		check(bean);
		
		bean.valueCurrency=null;
		bean.valueLocale=null;
		
		check(bean);
	}

	/**
	 * Test runtime R.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeR() throws Exception  {
		assertTrue(Bean81RBindMap.class.getName()!=null);
		
		Bean81R bean=createBeanR();
		check(bean);
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=null;				
		
		check(bean);
	}
	
	/**
	 * Test runtime S.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeS() throws Exception  {
		assertTrue(Bean81SBindMap.class.getName()!=null);
		
		Bean81S bean=createBeanS();
		check(bean);
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=null;				
		
		check(bean);
	}
	
	/**
	 * Test runtime T.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeT() throws Exception  {
		assertTrue(Bean81TBindMap.class.getName()!=null);
		
		Bean81T bean=createBeanT();
		check(bean);
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=null;				
		
		check(bean);
	}
	
	/**
	 * Test runtime U.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRuntimeU() throws Exception  {
		assertTrue(Bean81UBindMap.class.getName()!=null);
		
		Bean81U bean=createBeanU();
		check(bean);
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=null;				
		
		check(bean);
	}
	
}

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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;

public class Test76Attribute extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		
//		try {
			buildBindProcessorTest(BeanAttribute76.class, BeanEnum.class);	
//		} catch(AssertionError e)
//		{
//			System.out.println("["+e.getMessage()+"]");
//			System.out.println("["+IncompatibleAttributesInAnnotationException.class.getSimpleName()+"]");
//			System.out.println("["+e.getMessage()+"]".contains(IncompatibleAttributesInAnnotationException.class.getSimpleName()));
//		}
		
	}
	
	//@Test
	public void testRun() throws Exception
	{
		//Assert.assertNotNull(new BeanAttribute76BindMap());
		
		BeanAttribute76 bean=new BeanAttribute76();
		
		bean.valueBigDecimal=BigDecimal.valueOf(11.0);
		bean.valueBigInteger=BigInteger.valueOf(10);
		bean.valueEnum=BeanEnum.VALUE_2;
		bean.valueCalendar=Calendar.getInstance();
		bean.valueCurrency=Currency.getInstance(Locale.ITALY);
		bean.valueDate=new Date();
		bean.valueLocale=Locale.ITALY;
		bean.valueTime=new Time(0);
		bean.valueTimeZone=TimeZone.getDefault();
		bean.valueUrl=new URL("http://github.com");
		bean.id=25;
		bean.valueBean=new BeanAttribute76();
		bean.valueBean.id=45;
		bean.valueBoolType=true;
		bean.valueBool=true;
		bean.valueByteType=4;
		bean.valueByte=8;
		bean.valueShortType=25;
		bean.valueShort=25;
		bean.valueCharType='a';
		bean.valueChar='a';
		bean.valueIntType=12;
		bean.valueInt=12;
		bean.valueLongType=24;
		bean.valueLong=24L;
		bean.valueFloatType=24f;
		bean.valueFloat=24f;
		bean.valueDoubleType=24.0;
		bean.valueDouble=24.0;
		bean.valueString="\"ciao";
		
		check(bean);
	}
	
	@Test
	public void testArrayOnXmlAttribute() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Array.class, BeanEnum.class);
	}
	
	@Test
	public void testListOnXmlAttribute() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76List.class, BeanEnum.class);
	}
	
	@Test
	public void testSetOnXmlAttribute() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Set.class, BeanEnum.class);
	}
	
	@Test
	public void testMapOnXmlAttribute() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Map.class, BeanEnum.class);
	}
	

	
	
}

/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.abubusoft.kripton.android.kripton08;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.android.kripton08.Bean4.BeanType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import com.abubusoft.kripton.android.all.IssueBaseTest;

/**
 * @author xcesco
 *
 */
public class Issue8Test4 extends IssueBaseTest<Bean4> {

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean4();
		beanInput.fieldEnum=BeanType.TEST_2;
		beanInput.fieldEnum2=BeanType.TEST_1;
		/*bean.fieldLocale=Locale.CANADA;
		bean.fieldQName=QName.valueOf("wow");
		bean.fieldCalendar=Calendar.getInstance();
		bean.fieldChar='t';
		bean.fieldDate=new Date();		
		bean.fieldBigDecimal=BigDecimal.valueOf(34.0);
		bean.fieldBigInteger=BigInteger.valueOf(34);		
		bean.fieldUrl=new URL("http://www.google.it");		
		bean.fieldBoolean=true;
		bean.fieldByte=25;
		bean.fieldCurrency=Currency.getInstance(Locale.CHINA);
		bean.fieldDouble=45.0;
		bean.fieldFloat=34f;
		bean.fieldLong=64L;
		bean.fieldShort=2;
		bean.fieldTime=Time.valueOf(LocalTime.now());
		bean.fieldTimeZone=TimeZone.getDefault();
		bean.fieldInteger=56;
		bean.fieldString="hello!";*/
				
	}
	
	@Override
	@Test(expected=MappingException.class)
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Packed();
	}

	@Override
	@Test(expected=MappingException.class)
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Formatted();
	}

	@Override
	@Test(expected=MappingException.class)
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedDOM();
	}

	@Override
	@Test(expected=MappingException.class)
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedDOM();
	}

	@Override
	@Test(expected=MappingException.class)
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedSAXS();
	}

	@Override
	@Test(expected=MappingException.class)
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedSAXS();
	}

}

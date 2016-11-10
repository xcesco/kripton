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
package kripton05;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import kripton05.Bean2.BeanType;

import org.junit.Before;
import org.junit.Test;

import all.IssueBaseTest;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue5Test2 extends IssueBaseTest<Bean2> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean2();
		
		beanInput.fieldEnum=BeanType.TEST_2;
		beanInput.fieldLocale=Locale.CANADA;
		beanInput.fieldQName=QName.valueOf("wow");
		beanInput.fieldCalendar=Calendar.getInstance();
		beanInput.fieldChar='t';
		beanInput.fieldDate=new Date();		
		beanInput.fieldBigDecimal=BigDecimal.valueOf(34.0);
		beanInput.fieldBigInteger=BigInteger.valueOf(34);		
		beanInput.fieldUrl=new URL("http://www.google.it");		
		beanInput.fieldBoolean=true;
		beanInput.fieldByte=25;
		beanInput.fieldCurrency=Currency.getInstance(Locale.CHINA);
		beanInput.fieldDouble=45.0;
		beanInput.fieldFloat=34f;
		beanInput.fieldLong=64L;
		beanInput.fieldShort=2;
		beanInput.fieldTime=new Time(new Date().getTime());
		beanInput.fieldTimeZone=TimeZone.getDefault();
		beanInput.fieldInteger=56;
		beanInput.fieldString="hello!";		
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

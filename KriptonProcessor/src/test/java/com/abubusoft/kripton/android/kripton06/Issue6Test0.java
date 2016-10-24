/**
 * 
 */
package com.abubusoft.kripton.android.kripton06;

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

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;
import com.abubusoft.kripton.android.kripton06.Bean0.BeanType;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue6Test0 extends IssueBaseTest<Bean0> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean0();
		
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
		beanInput.fieldTime=new Time(3,3,3);
		beanInput.fieldTimeZone=TimeZone.getDefault();
		beanInput.fieldInteger=56;
		beanInput.fieldString="hello!";
	}
}

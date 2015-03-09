/**
 * 
 */
package issue.kripton_6;

import issue.IssueBaseTest;
import issue.kripton_6.Bean0.BeanType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import org.junit.Before;

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
		bean=new Bean0();
		
		bean.fieldEnum=BeanType.TEST_2;
		bean.fieldLocale=Locale.CANADA;
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
		bean.fieldString="hello!";
	}
}

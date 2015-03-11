/**
 * 
 */
package issue.kripton_8;

import java.net.MalformedURLException;

import issue.IssueBaseTest;
import issue.kripton_8.Bean4.BeanType;

import org.junit.Before;


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
	

}

/**
 * 
 */
package issue.kripton_5;

import issue.IssueBaseTest;
import issue.kripton_5.Bean2.BeanType;

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

import org.junit.Before;
import org.junit.Test;

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

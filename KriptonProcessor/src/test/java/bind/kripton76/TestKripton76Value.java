package bind.kripton76;

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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import bind.AbstractBindTypeProcessorTest;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestKripton76Value extends AbstractBindTypeProcessorTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	public <E extends KriptonProcessorException> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(IncompatibleAttributesInAnnotationException.class);
		//expectedEx.expectMessage(clazzException.getSimpleName());
	}
	
	@Before
	public void scalda()
	{
		expectedEx.expect(IncompatibleAttributesInAnnotationException.class);
	}

	@Test//(expected=AssertionError.class)
	public void testCompile() throws InstantiationException, IllegalAccessException, IOException 
	{
		
		//this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanValue76.class, BeanEnum.class);
	}
	
	//@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{		
		Assert.assertNotNull(new BeanValue76BindMap());
		
		BeanValue76 bean=new BeanValue76();
		
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
		bean.valueBean=new BeanValue76();
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
	public void testArrayOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Array.class, BeanEnum.class);
	}
	
	@Test
	public void testListOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76List.class, BeanEnum.class);
	}
	
	@Test
	public void testSetOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Set.class, BeanEnum.class);
	}
	
	@Test
	public void testMapOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Map.class, BeanEnum.class);
	}
	
	
	
}

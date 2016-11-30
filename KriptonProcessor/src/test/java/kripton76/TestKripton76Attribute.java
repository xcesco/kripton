package kripton76;

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
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.CborBinderContext;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;

import base.BaseProcessorTest;

public class TestKripton76Attribute extends BaseProcessorTest {
	
	@Before
	public void setup() {
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
		KriptonBinder2.registryBinder(new CborBinderContext());
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76.class, BeanEnum.class);
	}
	
	//@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new BeanAttribute76BindMap());
		
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

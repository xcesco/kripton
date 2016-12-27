package bind.kripton80ContextCollection;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import bind.AbstractBaseTest;

public class TestRuntime80All extends AbstractBaseTest {
	
	public Bean80 createBean() throws MalformedURLException
	{
		Bean80 bean = new Bean80();

		bean.valueBigDecimal = BigDecimal.valueOf(11.0);
		bean.valueBigInteger = BigInteger.valueOf(10);
		bean.valueEnum = BeanEnum.VALUE_2;
		bean.valueCalendar = Calendar.getInstance();
		bean.valueCurrency = Currency.getInstance(Locale.ITALY);
		bean.valueDate = new Date();
		bean.valueLocale = Locale.ITALY;
		bean.valueTime = new Time(0);
		bean.valueTimeZone = TimeZone.getDefault();
		bean.valueUrl = new URL("http://github.com");
		bean.id = 25;
		bean.valueBean = new Bean80();
		bean.valueBean.id = 45;
		bean.valueBoolType = true;
		bean.valueBool = true;
		bean.valueByteType = 4;
		bean.valueByte = 8;
		bean.valueShortType = 25;
		bean.valueShort = 25;
		bean.valueCharType = 'a';
		bean.valueChar = 'a';
		bean.valueIntType = 12;
		bean.valueInt = 12;
		bean.valueLongType = 24;
		bean.valueLong = 24L;
		bean.valueFloatType = 24f;
		bean.valueFloat = 24f;
		bean.valueDoubleType = 24.0;
		bean.valueDouble = 24.0;
		bean.valueString = "\"ciao";
		
		return bean;
	}
	
	public Bean80 createBean2() throws MalformedURLException
	{
		Bean80 bean = new Bean80();
		
		return bean;
	}

	@Test
	public void testRun_1() throws Exception {
		Assert.assertNotNull(new Bean80BindMap());
				
		List<Bean80> list=new ArrayList<>();
		list.add(createBean());
		list.add(createBean());
				
		checkCollection(list, Bean80.class, BinderType.JSON, BinderType.CBOR, BinderType.PROPERTIES, BinderType.YAML);
	}
	
	@Test(expected=KriptonRuntimeException.class)
	public void testRun_1_err() throws Exception {
		//this.expectedException(KriptonRuntimeException.class);		
		Assert.assertNotNull(new Bean80BindMap());
				
		List<Bean80> list=new ArrayList<>();
		list.add(createBean());
		list.add(createBean());
				
		checkCollection(list, Bean80.class, BinderType.XML);
	}
	
	@Test
	public void testRun_2() throws Exception {
		Assert.assertNotNull(new Bean80BindMap());
				
		List<Bean80> list=new ArrayList<>();
		list.add(createBean());
		list.add(createBean2());
				
		checkCollection(list, Bean80.class, BinderType.JSON, BinderType.CBOR, BinderType.PROPERTIES, BinderType.YAML);
	}
	
	@Test
	public void testRun_3() throws Exception {
		Assert.assertNotNull(new Bean80BindMap());		
				
		check(createBean());
		check(createBean2());
	}

}

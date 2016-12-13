package bind.kripton80ContextCollection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.binder.BinderType;

import bind.AbstractBaseTest;

public class TestRuntime80A extends AbstractBaseTest {

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(new Bean80ABindMap());
				
		List<Bean80A> list=new ArrayList<>();
		list.add(createBean());
		list.add(createBean());
		
		checkCollection(list, Bean80A.class, BinderType.JSON, BinderType.CBOR, BinderType.YAML, BinderType.PROPERTIES);
	}
	
	public Bean80A createBean() throws MalformedURLException
	{
		Bean80A bean = new Bean80A();

//		bean.valueBigDecimal = BigDecimal.valueOf(11.0);
//		bean.valueBigInteger = BigInteger.valueOf(10);
//		bean.valueEnum = BeanEnum.VALUE_2;
//		bean.valueCalendar = Calendar.getInstance();
//		bean.valueCurrency = Currency.getInstance(Locale.ITALY);
//		bean.valueDate = new Date();
//		bean.valueLocale = Locale.ITALY;
//		bean.valueTime = new Time(0);
//		bean.valueTimeZone = TimeZone.getDefault();
//		bean.valueUrl = new URL("http://github.com");
		bean.id = 25;
		bean.valueBean = new Bean80A();
		bean.valueBean.id = 45;
//		bean.valueBoolType = true;
//		bean.valueBool = true;
//		bean.valueByteType = 4;
//		bean.valueByte = 8;
//		bean.valueShortType = 25;
//		bean.valueShort = 25;
//		bean.valueCharType = 'a';
//		bean.valueChar = 'a';
//		bean.valueIntType = 12;
//		bean.valueInt = 12;
//		bean.valueLongType = 24;
//		bean.valueLong = 24L;
//		bean.valueFloatType = 24f;
//		bean.valueFloat = 24f;
//		bean.valueDoubleType = 24.0;
//		bean.valueDouble = 24.0;
		bean.valueString = "\"ciao";
		
		return bean;
	}



}

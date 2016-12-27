package bind.kripton70;

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
import org.junit.Test;

import bind.AbstractBaseTest;


public class TestRuntime70All extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		Assert.assertNotNull(new Bean70AllBindMap());

		Bean70All bean = new Bean70All();

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
		bean.valueBean = new Bean70All();
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
		bean.valueString = "ciao";

		check(bean);
	}

}

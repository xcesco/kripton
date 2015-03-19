package issue.kripton_8;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindValue;

@BindType
public class Bean1 extends Bean0 {

	public enum BeanType 
	{
		TEST_1,
		TEST_2;
	}
	
	@BindValue
	BeanType fieldEnum;
	
	@BindValue
	Locale fieldLocale;
	
	@BindValue
	QName fieldQName;
	
	@BindValue
	Calendar fieldCalendar;
	
	@BindValue
	Character fieldChar;
	
	@BindValue
	Date fieldDate;
	
	@BindValue
	BigDecimal fieldBigDecimal;
	
	@BindValue
	URL fieldUrl;
	
	@BindValue
	Boolean fieldBoolean;
	
	@BindValue
	Byte fieldByte;
	
	@BindValue
	Currency fieldCurrency;
	
	@BindValue
	Double fieldDouble;
	
	@BindValue
	Float fieldFloat;
	
	@BindValue
	Long fieldLong;
	
	@BindValue
	Short fieldShort;
	
	@BindValue
	Time fieldTime;
	
	@BindValue
	TimeZone fieldTimeZone;
	
	@BindValue
	Integer fieldInteger;
	
	@BindValue
	String fieldString;

	@BindValue
	public BigInteger fieldBigInteger;
}

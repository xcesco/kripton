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
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;

@BindType
public class Bean1 extends Bean0 {

	public enum BeanType 
	{
		TEST_1,
		TEST_2;
	}
	
	@BindXml(type=XmlType.VALUE)
	BeanType fieldEnum;
	
	@BindXml(type=XmlType.VALUE)
	Locale fieldLocale;
	
	@BindXml(type=XmlType.VALUE)
	QName fieldQName;
	
	@BindXml(type=XmlType.VALUE)
	Calendar fieldCalendar;
	
	@BindXml(type=XmlType.VALUE)
	Character fieldChar;
	
	@BindXml(type=XmlType.VALUE)
	Date fieldDate;
	
	@BindXml(type=XmlType.VALUE)
	BigDecimal fieldBigDecimal;
	
	@BindXml(type=XmlType.VALUE)
	URL fieldUrl;
	
	@BindXml(type=XmlType.VALUE)
	Boolean fieldBoolean;
	
	@BindXml(type=XmlType.VALUE)
	Byte fieldByte;
	
	@BindXml(type=XmlType.VALUE)
	Currency fieldCurrency;
	
	@BindXml(type=XmlType.VALUE)
	Double fieldDouble;
	
	@BindXml(type=XmlType.VALUE)
	Float fieldFloat;
	
	@BindXml(type=XmlType.VALUE)
	Long fieldLong;
	
	@BindXml(type=XmlType.VALUE)
	Short fieldShort;
	
	@BindXml(type=XmlType.VALUE)
	Time fieldTime;
	
	@BindXml(type=XmlType.VALUE)
	TimeZone fieldTimeZone;
	
	@BindXml(type=XmlType.VALUE)
	Integer fieldInteger;
	
	@BindXml(type=XmlType.VALUE)
	String fieldString;

	@BindXml(type=XmlType.VALUE)
	public BigInteger fieldBigInteger;
}

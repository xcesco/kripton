package kripton70;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean {

	@BindXml(xmlType = XmlType.TAG)
	public long id;
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String name;
	
	@BindXml(xmlType = XmlType.VALUE_CDATA)
	public String content;
		
	public String valueString;
		
	public byte valueByteType;
	
	public short valueShortType;
	
	public char valueCharType;
	
	public int valueIntType;
	
	public long valueLongType;
	
	public float valueFloatType;
	
	public double valueDoubleType;
	
	public Byte valueByte;
	
	public Short valueShort;
	
	public Character valueChar;
	
	public Integer valueInt;
	
	public Long valueLong;
	
	public Float valueFloat;
	
	public Double valueDouble;
	
	public BigDecimal valueBigDecimal;
	
	public BigInteger valueBigInteger;
	
	public Calendar valueCalendar;
	
	public Currency valueCurrency;
	
	public Date valueDate;
	
	public EnumBeanType valueEnumBean;
	
	public Locale valueLocale;
	
	public Time valueTime;
	
	public TimeZone valueTimeZone;
	
	public URL valueUrl;
	
	public Bean valueBean;
	
	public List<String> valueStringList;
	
	public String[] valueStringArray;
	
	public Map<String, String> valueStringMap;
		
}

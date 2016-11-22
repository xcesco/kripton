package kripton70;

import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
@BindTypeXml("root")
public class BeanElement70 {
	
	/*
	
	@BindXml(value="name", xmlType=XmlType.TAG)
	protected long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Integer valueContentBoolType;
	
	public boolean valueBoolType;
	
	public Boolean valueBool;	
	
	public byte valueByteType;
	
	public Byte valueByte;
	
	public short valueShortType;
	
	public Short valueShort;
	
	public char valueCharType;
	
	public Character valueChar;
	
	public int valueIntType;
	
	public Integer valueInt;
	
	public long valueLongType;
	
	public Long valueLong;
	
	public float valueFloatType;
	
	public Float valueFloat;
	
	public double valueDoubleType;
	
	public Double valueDouble;
	
	public String valueString;
	
	public BeanElement70 valueBean;
	
	*/
	
	public Calendar valueCalendar;
	
	public Currency valueCurrency;
	
	public Date valueDate;
	
	public Locale valueLocale;
	
	public Time valueTime;
	
	public TimeZone valueTimeZone;
	
	public URL valueUrl;
	
/*
	
	public BigDecimal valueBigDecimal;
	
	public BigInteger valueBigInteger;
	
	
	
	
	
	
	
	public EnumBeanType valueEnumBean;
	
	
	
	
	
	public List<String> valueStringList;
	
	public String[] valueStringArray;
	
	public Map<String, String> valueStringMap;
		*/
}

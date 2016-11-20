package kripton70;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
@BindTypeXml("root")
public class Bean70 {
	
	@BindXml(value="name", xmlType=XmlType.ATTRIBUTE)
	public long id;
	
	@Bind(order=40)
	@BindXml(value="nameaa", xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool2;
	
	@Bind(order=4)
	public Boolean valueBool1;
	
	@Bind(order=3)
	@BindXml(xmlType=XmlType.VALUE)
	public String valueString;
	
/*
	@BindXml(XmlType.ATTRIBUTE)
	public String name;
	
	@BindXml(XmlType.VALUE_CDATA)
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
		*/
}

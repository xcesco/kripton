package kripton70;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
@BindTypeXml("root")
public class BeanElement70 {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanElement70 other = (BeanElement70) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@BindXml(value="name", xmlType=XmlType.TAG)
	protected long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean valueBoolType;
	
	public short valueShortType;
	
	public byte valueByteType;
	
	public char valueCharType;
	
	public int valueIntType;
	
	public long valueLongType;
	
	public float valueFloatType;
	
	public double valueDoubleType;
	
	public Boolean valueBool;
	
	public Short valueShort;
	
	private Byte valueByte;
	
	public Byte getValueByte() {
		return valueByte;
	}

	public void setValueByte(Byte valueByte) {
		this.valueByte = valueByte;
	}

	public Character valueChar;
	
	public Integer valueInt;
	
	public Long valueLong;
	
	public Float valueFloat;
	
	public Double valueDouble;
	
	
	public String valueString;
	
	/*
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool2;
	
	@Bind(order=4)
	public Boolean valueBool1;
	
	@Bind(order=3)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueAttributeString;
	
	@Bind(order=3)
	@BindXml(xmlType=XmlType.TAG)
	public String valueElementString;
	
	@Bind(order=3)
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public String valueCDataString;
	
	public Bean valueBean;*/
	
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
	
	
	
	public List<String> valueStringList;
	
	public String[] valueStringArray;
	
	public Map<String, String> valueStringMap;
		*/
}

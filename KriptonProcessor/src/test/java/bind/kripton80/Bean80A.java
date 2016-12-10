package bind.kripton80;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean80A {
	
	protected long id;
	
	public Bean80A valueBean;

//	public BigDecimal valueBigDecimal;
//	
//	public BigInteger valueBigInteger;
//
//	public Boolean valueBool;
//
//	public boolean valueBoolType;
//	
//	public Byte valueByte;
//	
//	public byte valueByteType;	
//	
//	public Calendar valueCalendar;
//	
//	public Character valueChar;
//	
//	public char valueCharType;
//	
//	@BindXml(xmlType=XmlType.VALUE_CDATA)
//	public Integer valueContentBoolType;
//	
//	public Currency valueCurrency;
//	
//	public Date valueDate;
//	
//	public Double valueDouble;
//	
//	public double valueDoubleType;
//	
//	public BeanEnum valueEnum;
//	
//	public Float valueFloat;
//	
//	public float valueFloatType;
//	
//	public Integer valueInt;
//	
//	public int valueIntType;
//	
//	public Locale valueLocale;
//	
//	public Long valueLong;
//	
//	public long valueLongType;
//	
//	public Short valueShort;
//	
//	public short valueShortType;
	
	public String valueString;
	
//	public Time valueTime;
//	
//	public TimeZone valueTimeZone;
//	
//	public URL valueUrl;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean80A other = (Bean80A) obj;
		if (id != other.id)
			return false;
		if (valueBean == null) {
			if (other.valueBean != null)
				return false;
		} else if (!valueBean.equals(other.valueBean))
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		return true;
	}
	
	public long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((valueBean == null) ? 0 : valueBean.hashCode());
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		return result;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

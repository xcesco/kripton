package bind.kripton70;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType("root")
public class Bean70All {
	
	@Bind("typeName")
	@BindXml(xmlType=XmlType.TAG)
	protected long id;
	
	public Bean70All valueBean;

	public BigDecimal valueBigDecimal;
	
	public BigInteger valueBigInteger;

	public Boolean valueBool;

	public boolean valueBoolType;
	
	public Byte valueByte;
	
	public byte valueByteType;	
	
	public Calendar valueCalendar;
	
	public Character valueChar;
	
	public char valueCharType;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Integer valueContentBoolType;
	
	public Currency valueCurrency;
	
	public Date valueDate;
	
	public Double valueDouble;
	
	public double valueDoubleType;
	
	public BeanEnum valueEnum;
	
	public Float valueFloat;
	
	public float valueFloatType;
	
	public Integer valueInt;
	
	public int valueIntType;
	
	public Locale valueLocale;
	
	public Long valueLong;
	
	public long valueLongType;
	
	public Short valueShort;
	
	public short valueShortType;
	
	public String valueString;
	
	public Time valueTime;
	
	public TimeZone valueTimeZone;
	
	public URL valueUrl;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean70All other = (Bean70All) obj;
		if (id != other.id)
			return false;
		if (valueBean == null) {
			if (other.valueBean != null)
				return false;
		} else if (!valueBean.equals(other.valueBean))
			return false;
		if (valueBigDecimal == null) {
			if (other.valueBigDecimal != null)
				return false;
		} else if (!valueBigDecimal.equals(other.valueBigDecimal))
			return false;
		if (valueBigInteger == null) {
			if (other.valueBigInteger != null)
				return false;
		} else if (!valueBigInteger.equals(other.valueBigInteger))
			return false;
		if (valueBool == null) {
			if (other.valueBool != null)
				return false;
		} else if (!valueBool.equals(other.valueBool))
			return false;
		if (valueBoolType != other.valueBoolType)
			return false;
		if (valueByte == null) {
			if (other.valueByte != null)
				return false;
		} else if (!valueByte.equals(other.valueByte))
			return false;
		if (valueByteType != other.valueByteType)
			return false;
		if (valueCalendar == null) {
			if (other.valueCalendar != null)
				return false;
		} else if (!valueCalendar.equals(other.valueCalendar))
			return false;
		if (valueChar == null) {
			if (other.valueChar != null)
				return false;
		} else if (!valueChar.equals(other.valueChar))
			return false;
		if (valueCharType != other.valueCharType)
			return false;
		if (valueContentBoolType == null) {
			if (other.valueContentBoolType != null)
				return false;
		} else if (!valueContentBoolType.equals(other.valueContentBoolType))
			return false;
		if (valueCurrency == null) {
			if (other.valueCurrency != null)
				return false;
		} else if (!valueCurrency.equals(other.valueCurrency))
			return false;
		if (valueDate == null) {
			if (other.valueDate != null)
				return false;
		} else if (!valueDate.equals(other.valueDate))
			return false;
		if (valueDouble == null) {
			if (other.valueDouble != null)
				return false;
		} else if (!valueDouble.equals(other.valueDouble))
			return false;
		if (Double.doubleToLongBits(valueDoubleType) != Double.doubleToLongBits(other.valueDoubleType))
			return false;
		if (valueEnum != other.valueEnum)
			return false;
		if (valueFloat == null) {
			if (other.valueFloat != null)
				return false;
		} else if (!valueFloat.equals(other.valueFloat))
			return false;
		if (Float.floatToIntBits(valueFloatType) != Float.floatToIntBits(other.valueFloatType))
			return false;
		if (valueInt == null) {
			if (other.valueInt != null)
				return false;
		} else if (!valueInt.equals(other.valueInt))
			return false;
		if (valueIntType != other.valueIntType)
			return false;
		if (valueLocale == null) {
			if (other.valueLocale != null)
				return false;
		} else if (!valueLocale.equals(other.valueLocale))
			return false;
		if (valueLong == null) {
			if (other.valueLong != null)
				return false;
		} else if (!valueLong.equals(other.valueLong))
			return false;
		if (valueLongType != other.valueLongType)
			return false;
		if (valueShort == null) {
			if (other.valueShort != null)
				return false;
		} else if (!valueShort.equals(other.valueShort))
			return false;
		if (valueShortType != other.valueShortType)
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		if (valueTime == null) {
			if (other.valueTime != null)
				return false;
		} else if (!valueTime.equals(other.valueTime))
			return false;
		if (valueTimeZone == null) {
			if (other.valueTimeZone != null)
				return false;
		} else if (!valueTimeZone.equals(other.valueTimeZone))
			return false;
		if (valueUrl == null) {
			if (other.valueUrl != null)
				return false;
		} else if (!valueUrl.equals(other.valueUrl))
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
		result = prime * result + ((valueBigDecimal == null) ? 0 : valueBigDecimal.hashCode());
		result = prime * result + ((valueBigInteger == null) ? 0 : valueBigInteger.hashCode());
		result = prime * result + ((valueBool == null) ? 0 : valueBool.hashCode());
		result = prime * result + (valueBoolType ? 1231 : 1237);
		result = prime * result + ((valueByte == null) ? 0 : valueByte.hashCode());
		result = prime * result + valueByteType;
		result = prime * result + ((valueCalendar == null) ? 0 : valueCalendar.hashCode());
		result = prime * result + ((valueChar == null) ? 0 : valueChar.hashCode());
		result = prime * result + valueCharType;
		result = prime * result + ((valueContentBoolType == null) ? 0 : valueContentBoolType.hashCode());
		result = prime * result + ((valueCurrency == null) ? 0 : valueCurrency.hashCode());
		result = prime * result + ((valueDate == null) ? 0 : valueDate.hashCode());
		result = prime * result + ((valueDouble == null) ? 0 : valueDouble.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valueDoubleType);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valueEnum == null) ? 0 : valueEnum.hashCode());
		result = prime * result + ((valueFloat == null) ? 0 : valueFloat.hashCode());
		result = prime * result + Float.floatToIntBits(valueFloatType);
		result = prime * result + ((valueInt == null) ? 0 : valueInt.hashCode());
		result = prime * result + valueIntType;
		result = prime * result + ((valueLocale == null) ? 0 : valueLocale.hashCode());
		result = prime * result + ((valueLong == null) ? 0 : valueLong.hashCode());
		result = prime * result + (int) (valueLongType ^ (valueLongType >>> 32));
		result = prime * result + ((valueShort == null) ? 0 : valueShort.hashCode());
		result = prime * result + valueShortType;
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		result = prime * result + ((valueTime == null) ? 0 : valueTime.hashCode());
		result = prime * result + ((valueTimeZone == null) ? 0 : valueTimeZone.hashCode());
		result = prime * result + ((valueUrl == null) ? 0 : valueUrl.hashCode());
		return result;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

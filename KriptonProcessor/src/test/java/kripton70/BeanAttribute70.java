package kripton70;

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
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType(allFields=false)
public class BeanAttribute70 {

	// OK	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	protected long id;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.VALUE)
	public BeanAttribute70 valueBean;

	// OK
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBigDecimal;
		
	// OK
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBigInteger;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;	
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Byte valueByte;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte valueByteType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Calendar valueCalendar;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Character valueChar;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public char valueCharType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueContentBoolType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Currency valueCurrency;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Date valueDate;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Double valueDouble;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public double valueDoubleType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanEnum valueEnum;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Float valueFloat;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float valueFloatType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInt;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int valueIntType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long valueLong;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long valueLongType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Short valueShort;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public short valueShortType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Time valueTime;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public TimeZone valueTimeZone;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public URL valueUrl;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

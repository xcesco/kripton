package bind.kripton76Errors;

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

@BindType(value="root", allFields=true)
public class BeanAttribute76 {
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	protected long id;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanAttribute76 valueBean;

	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBigDecimal;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBigInteger;

	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;	
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Byte valueByte;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte valueByteType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Calendar valueCalendar;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Character valueChar;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public char valueCharType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueContentBoolType;

	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Currency valueCurrency;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Date valueDate;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Double valueDouble;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public double valueDoubleType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanEnum valueEnum;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Float valueFloat;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float valueFloatType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInt;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int valueIntType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long valueLong;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long valueLongType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Short valueShort;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public short valueShortType;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Time valueTime;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public TimeZone valueTimeZone;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public URL valueUrl;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

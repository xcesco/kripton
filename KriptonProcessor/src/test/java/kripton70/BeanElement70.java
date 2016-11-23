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

@BindType("root")
public class BeanElement70 {
	
	@Bind("name")
	@BindXml(xmlType=XmlType.TAG)
	protected long id;
	
	public BeanElement70 valueBean;

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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

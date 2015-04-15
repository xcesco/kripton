/**
 * 
 */
package kripton22;

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
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

/**
 * <p>
 * Messaggio base per tutte le comunicazioni con il server.
 * </p>
 * 
 * @author Cesco
 * 
 */
@BindType
@BindTable
@BindAllFields
public class Bean0 {

	public enum EnumValue
	{
		TYPE0,
		TYPE1
	}

	public boolean boolValue;

	public Boolean boolValue2;

	public byte[] content;

	public String description;

	public Double doubleNumber;

	public double doubleNumber2;
	
	public EnumValue enumValue;
	
	@Bind(order = 0)
	@BindColumn(ColumnType.PRIMARY_KEY)
	public Long id;
	
	public Integer intNumber;
	
	public int intNumber2;

	public long longNumber;
	
	public Long longNumber2;
	
	public short shortNumber;
	
	public Short shortNumber2;
	
	public char charNumber;
	
	public Character charNumber2;
	
	public Date dateValue;
	
	public Time timeValue;
	
	public URL url;
	
	public BigInteger bigIntegerValue;
	
	public BigDecimal bigDecimalValue;
	
	public Calendar calendarValue;
	
	public Currency currencyValue;
	
	public TimeZone timeZoneValue;

	public Locale localeValue;

	public byte byteValue;

	public Byte byteValue2;

	public float floatValue;

	public Float floatValue2;

}

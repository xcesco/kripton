/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton60;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean2.
 */
@BindType
@BindSharedPreferences
public class Bean2 {
	
	/** The id. */
	@BindPreference(enabled=true)
	private long id;
	
	/** The value bean. */
	@BindColumn(enabled=false)
	private Bean2 valueBean;

	/** The value bean array. */
	private Bean2[] valueBeanArray;
	
	/** The value big decimal. */
	private BigDecimal valueBigDecimal;
	
	/** The value big integer. */
	private BigInteger valueBigInteger;
	
	/** The value bool. */
	private Boolean valueBool;

	/** The value bool type. */
	private boolean valueBoolType;
	
	/** The value byte. */
	private Byte valueByte;
	
	/** The value byte array. */
	private byte[] valueByteArray;
	
	/** The value byte type. */
	private byte valueByteType;
	
	/** The value calendar. */
	private Calendar valueCalendar;
	
	/** The value char. */
	private Character valueChar;
	
	/** The value char array. */
	private Character[] valueCharArray;
	
	/** The value char list. */
	private LinkedList<Character> valueCharList;
	
	/** The value char type. */
	private char valueCharType;
	
	/** The value char type array. */
	private char[] valueCharTypeArray;
		
	/** The value currency. */
	private Currency valueCurrency;
	
	/** The value date. */
	private Date valueDate;
	
	/** The value double. */
	private Double valueDouble;
	
	/** The value double type. */
	private double valueDoubleType;
	
	/** The value enum type. */
	private EnumType valueEnumType;
	
	/** The value float. */
	private Float valueFloat;
	
	/** The value float type. */
	private float valueFloatType;
	
	/** The value int. */
	private Integer valueInt;
	
	/** The value int type. */
	private int valueIntType;
	
	/** The value locale. */
	private Locale valueLocale;
	
	/** The value long. */
	private Long valueLong;
	
	/** The value long array. */
	private Long[] valueLongArray;
	
	/** The value long list. */
	private LinkedList<Long> valueLongList;
	
	/** The value long type. */
	private long valueLongType;
	
	/** The value long type array. */
	private long[] valueLongTypeArray;
	
	/** The value short. */
	private Short valueShort;
	
	/** The value short type. */
	private short valueShortType;
	
	/** The value string. */
	private String valueString;
	
	/** The value string array. */
	private String[] valueStringArray;

	/** The value strin list. */
	private LinkedList<String> valueStrinList;

	/** The value time. */
	private Time valueTime;

	/** The value time list. */
	private List<Time> valueTimeList;

	/** The value time zone. */
	private TimeZone valueTimeZone;

	/** The value url. */
	private URL valueUrl;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the value bean.
	 *
	 * @return the value bean
	 */
	public Bean2 getValueBean() {
		return valueBean;
	}

	/**
	 * Gets the value bean array.
	 *
	 * @return the value bean array
	 */
	public Bean2[] getValueBeanArray() {
		return valueBeanArray;
	}

	/**
	 * Gets the value big decimal.
	 *
	 * @return the value big decimal
	 */
	public BigDecimal getValueBigDecimal() {
		return valueBigDecimal;
	}

	/**
	 * Gets the value big integer.
	 *
	 * @return the value big integer
	 */
	public BigInteger getValueBigInteger() {
		return valueBigInteger;
	}

	/**
	 * Gets the value bool.
	 *
	 * @return the value bool
	 */
	public Boolean getValueBool() {
		return valueBool;
	}

	/**
	 * Gets the value byte.
	 *
	 * @return the value byte
	 */
	public Byte getValueByte() {
		return valueByte;
	}

	/**
	 * Gets the value byte array.
	 *
	 * @return the value byte array
	 */
	public byte[] getValueByteArray() {
		return valueByteArray;
	}

	/**
	 * Gets the value byte type.
	 *
	 * @return the value byte type
	 */
	public byte getValueByteType() {
		return valueByteType;
	}

	/**
	 * Gets the value calendar.
	 *
	 * @return the value calendar
	 */
	public Calendar getValueCalendar() {
		return valueCalendar;
	}

	/**
	 * Gets the value char.
	 *
	 * @return the value char
	 */
	public Character getValueChar() {
		return valueChar;
	}

	/**
	 * Gets the value char array.
	 *
	 * @return the value char array
	 */
	public Character[] getValueCharArray() {
		return valueCharArray;
	}

	/**
	 * Gets the value char list.
	 *
	 * @return the value char list
	 */
	public LinkedList<Character> getValueCharList() {
		return valueCharList;
	}

	/**
	 * Gets the value char type.
	 *
	 * @return the value char type
	 */
	public char getValueCharType() {
		return valueCharType;
	}

	/**
	 * Gets the value char type array.
	 *
	 * @return the value char type array
	 */
	public char[] getValueCharTypeArray() {
		return valueCharTypeArray;
	}

	/**
	 * Gets the value currency.
	 *
	 * @return the value currency
	 */
	public Currency getValueCurrency() {
		return valueCurrency;
	}

	/**
	 * Gets the value date.
	 *
	 * @return the value date
	 */
	public Date getValueDate() {
		return valueDate;
	}

	/**
	 * Gets the value double.
	 *
	 * @return the value double
	 */
	public Double getValueDouble() {
		return valueDouble;
	}

	/**
	 * Gets the value double type.
	 *
	 * @return the value double type
	 */
	public double getValueDoubleType() {
		return valueDoubleType;
	}

	/**
	 * Gets the value enum type.
	 *
	 * @return the value enum type
	 */
	public EnumType getValueEnumType() {
		return valueEnumType;
	}

	/**
	 * Gets the value float.
	 *
	 * @return the value float
	 */
	public Float getValueFloat() {
		return valueFloat;
	}

	/**
	 * Gets the value float type.
	 *
	 * @return the value float type
	 */
	public float getValueFloatType() {
		return valueFloatType;
	}

	/**
	 * Gets the value int.
	 *
	 * @return the value int
	 */
	public Integer getValueInt() {
		return valueInt;
	}

	/**
	 * Gets the value int type.
	 *
	 * @return the value int type
	 */
	public int getValueIntType() {
		return valueIntType;
	}

	/**
	 * Gets the value locale.
	 *
	 * @return the value locale
	 */
	public Locale getValueLocale() {
		return valueLocale;
	}

	/**
	 * Gets the value long.
	 *
	 * @return the value long
	 */
	public Long getValueLong() {
		return valueLong;
	}

	/**
	 * Gets the value long array.
	 *
	 * @return the value long array
	 */
	public Long[] getValueLongArray() {
		return valueLongArray;
	}

	/**
	 * Gets the value long list.
	 *
	 * @return the value long list
	 */
	public LinkedList<Long> getValueLongList() {
		return valueLongList;
	}

	/**
	 * Gets the value long type.
	 *
	 * @return the value long type
	 */
	public long getValueLongType() {
		return valueLongType;
	}

	/**
	 * Gets the value long type array.
	 *
	 * @return the value long type array
	 */
	public long[] getValueLongTypeArray() {
		return valueLongTypeArray;
	}

	/**
	 * Gets the value short.
	 *
	 * @return the value short
	 */
	public Short getValueShort() {
		return valueShort;
	}

	/**
	 * Gets the value short type.
	 *
	 * @return the value short type
	 */
	public short getValueShortType() {
		return valueShortType;
	}

	/**
	 * Gets the value string.
	 *
	 * @return the value string
	 */
	public String getValueString() {
		return valueString;
	}

	/**
	 * Gets the value string array.
	 *
	 * @return the value string array
	 */
	public String[] getValueStringArray() {
		return valueStringArray;
	}

	/**
	 * Gets the value strin list.
	 *
	 * @return the value strin list
	 */
	public LinkedList<String> getValueStrinList() {
		return valueStrinList;
	}

	/**
	 * Gets the value time.
	 *
	 * @return the value time
	 */
	public Time getValueTime() {
		return valueTime;
	}

	/**
	 * Gets the value time list.
	 *
	 * @return the value time list
	 */
	public List<Time> getValueTimeList() {
		return valueTimeList;
	}

	/**
	 * Gets the value time zone.
	 *
	 * @return the value time zone
	 */
	public TimeZone getValueTimeZone() {
		return valueTimeZone;
	}

	/**
	 * Gets the value url.
	 *
	 * @return the value url
	 */
	public URL getValueUrl() {
		return valueUrl;
	}

	/**
	 * Checks if is value bool type.
	 *
	 * @return true, if is value bool type
	 */
	public boolean isValueBoolType() {
		return valueBoolType;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Sets the value bean.
	 *
	 * @param valueBean the new value bean
	 */
	public void setValueBean(Bean2 valueBean) {
		this.valueBean = valueBean;
	}

	/**
	 * Sets the value bean array.
	 *
	 * @param valueBeanArray the new value bean array
	 */
	public void setValueBeanArray(Bean2[] valueBeanArray) {
		this.valueBeanArray = valueBeanArray;
	}

	/**
	 * Sets the value big decimal.
	 *
	 * @param valueBigDecimal the new value big decimal
	 */
	public void setValueBigDecimal(BigDecimal valueBigDecimal) {
		this.valueBigDecimal = valueBigDecimal;
	}

	/**
	 * Sets the value big integer.
	 *
	 * @param valueBigInteger the new value big integer
	 */
	public void setValueBigInteger(BigInteger valueBigInteger) {
		this.valueBigInteger = valueBigInteger;
	}

	/**
	 * Sets the value bool.
	 *
	 * @param valueBool the new value bool
	 */
	public void setValueBool(Boolean valueBool) {
		this.valueBool = valueBool;
	}

	/**
	 * Sets the value bool type.
	 *
	 * @param valueBoolType the new value bool type
	 */
	public void setValueBoolType(boolean valueBoolType) {
		this.valueBoolType = valueBoolType;
	}

	/**
	 * Sets the value byte.
	 *
	 * @param valueByte the new value byte
	 */
	public void setValueByte(Byte valueByte) {
		this.valueByte = valueByte;
	}

	/**
	 * Sets the value byte array.
	 *
	 * @param valueByteArray the new value byte array
	 */
	public void setValueByteArray(byte[] valueByteArray) {
		this.valueByteArray = valueByteArray;
	}

	/**
	 * Sets the value byte type.
	 *
	 * @param valueByteType the new value byte type
	 */
	public void setValueByteType(byte valueByteType) {
		this.valueByteType = valueByteType;
	}

	/**
	 * Sets the value calendar.
	 *
	 * @param valueCalendar the new value calendar
	 */
	public void setValueCalendar(Calendar valueCalendar) {
		this.valueCalendar = valueCalendar;
	}

	/**
	 * Sets the value char.
	 *
	 * @param valueChar the new value char
	 */
	public void setValueChar(Character valueChar) {
		this.valueChar = valueChar;
	}

	/**
	 * Sets the value char array.
	 *
	 * @param valueCharArray the new value char array
	 */
	public void setValueCharArray(Character[] valueCharArray) {
		this.valueCharArray = valueCharArray;
	}

	/**
	 * Sets the value char list.
	 *
	 * @param valueCharList the new value char list
	 */
	public void setValueCharList(LinkedList<Character> valueCharList) {
		this.valueCharList = valueCharList;
	}

	/**
	 * Sets the value char type.
	 *
	 * @param valueCharType the new value char type
	 */
	public void setValueCharType(char valueCharType) {
		this.valueCharType = valueCharType;
	}

	/**
	 * Sets the value char type array.
	 *
	 * @param valueCharTypeArray the new value char type array
	 */
	public void setValueCharTypeArray(char[] valueCharTypeArray) {
		this.valueCharTypeArray = valueCharTypeArray;
	}

	/**
	 * Sets the value currency.
	 *
	 * @param valueCurrency the new value currency
	 */
	public void setValueCurrency(Currency valueCurrency) {
		this.valueCurrency = valueCurrency;
	}

	/**
	 * Sets the value date.
	 *
	 * @param valueDate the new value date
	 */
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * Sets the value double.
	 *
	 * @param valueDouble the new value double
	 */
	public void setValueDouble(Double valueDouble) {
		this.valueDouble = valueDouble;
	}

	/**
	 * Sets the value double type.
	 *
	 * @param valueDoubleType the new value double type
	 */
	public void setValueDoubleType(double valueDoubleType) {
		this.valueDoubleType = valueDoubleType;
	}

	/**
	 * Sets the value enum type.
	 *
	 * @param valueEnumType the new value enum type
	 */
	public void setValueEnumType(EnumType valueEnumType) {
		this.valueEnumType = valueEnumType;
	}

	/**
	 * Sets the value float.
	 *
	 * @param valueFloat the new value float
	 */
	public void setValueFloat(Float valueFloat) {
		this.valueFloat = valueFloat;
	}

	/**
	 * Sets the value float type.
	 *
	 * @param valueFloatType the new value float type
	 */
	public void setValueFloatType(float valueFloatType) {
		this.valueFloatType = valueFloatType;
	}

	/**
	 * Sets the value int.
	 *
	 * @param valueInt the new value int
	 */
	public void setValueInt(Integer valueInt) {
		this.valueInt = valueInt;
	}

	/**
	 * Sets the value int type.
	 *
	 * @param valueIntType the new value int type
	 */
	public void setValueIntType(int valueIntType) {
		this.valueIntType = valueIntType;
	}

	/**
	 * Sets the value locale.
	 *
	 * @param valueLocale the new value locale
	 */
	public void setValueLocale(Locale valueLocale) {
		this.valueLocale = valueLocale;
	}

	/**
	 * Sets the value long.
	 *
	 * @param valueLong the new value long
	 */
	public void setValueLong(Long valueLong) {
		this.valueLong = valueLong;
	}

	/**
	 * Sets the value long array.
	 *
	 * @param valueLongArray the new value long array
	 */
	public void setValueLongArray(Long[] valueLongArray) {
		this.valueLongArray = valueLongArray;
	}

	/**
	 * Sets the value long list.
	 *
	 * @param valueLongList the new value long list
	 */
	public void setValueLongList(LinkedList<Long> valueLongList) {
		this.valueLongList = valueLongList;
	}

	/**
	 * Sets the value long type.
	 *
	 * @param valueLongType the new value long type
	 */
	public void setValueLongType(long valueLongType) {
		this.valueLongType = valueLongType;
	}

	/**
	 * Sets the value long type array.
	 *
	 * @param valueLongTypeArray the new value long type array
	 */
	public void setValueLongTypeArray(long[] valueLongTypeArray) {
		this.valueLongTypeArray = valueLongTypeArray;
	}

	/**
	 * Sets the value short.
	 *
	 * @param valueShort the new value short
	 */
	public void setValueShort(Short valueShort) {
		this.valueShort = valueShort;
	}

	/**
	 * Sets the value short type.
	 *
	 * @param valueShortType the new value short type
	 */
	public void setValueShortType(short valueShortType) {
		this.valueShortType = valueShortType;
	}

	/**
	 * Sets the value string.
	 *
	 * @param valueString the new value string
	 */
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	/**
	 * Sets the value string array.
	 *
	 * @param valueStringArray the new value string array
	 */
	public void setValueStringArray(String[] valueStringArray) {
		this.valueStringArray = valueStringArray;
	}
	
	/**
	 * Sets the value strin list.
	 *
	 * @param valueStrinList the new value strin list
	 */
	public void setValueStrinList(LinkedList<String> valueStrinList) {
		this.valueStrinList = valueStrinList;
	}
	
	/**
	 * Sets the value time.
	 *
	 * @param valueTime the new value time
	 */
	public void setValueTime(Time valueTime) {
		this.valueTime = valueTime;
	}

	/**
	 * Sets the value time list.
	 *
	 * @param valueTimeList the new value time list
	 */
	public void setValueTimeList(List<Time> valueTimeList) {
		this.valueTimeList = valueTimeList;
	}

	/**
	 * Sets the value time zone.
	 *
	 * @param valueTimeZone the new value time zone
	 */
	public void setValueTimeZone(TimeZone valueTimeZone) {
		this.valueTimeZone = valueTimeZone;
	}
	
	/**
	 * Sets the value url.
	 *
	 * @param valueUrl the new value url
	 */
	public void setValueUrl(URL valueUrl) {
		this.valueUrl = valueUrl;
	}
}

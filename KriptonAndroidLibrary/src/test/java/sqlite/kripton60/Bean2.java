/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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

@BindType
@BindSharedPreferences
public class Bean2 {
	@BindPreference(enabled=true)
	private long id;
	
	@BindColumn(enabled=false)
	private Bean2 valueBean;

	private Bean2[] valueBeanArray;
	
	private BigDecimal valueBigDecimal;
	
	private BigInteger valueBigInteger;
	
	private Boolean valueBool;

	private boolean valueBoolType;
	
	private Byte valueByte;
	
	private byte[] valueByteArray;
	
	private byte valueByteType;
	
	private Calendar valueCalendar;
	
	private Character valueChar;
	
	private Character[] valueCharArray;
	
	private LinkedList<Character> valueCharList;
	
	private char valueCharType;
	
	private char[] valueCharTypeArray;
		
	private Currency valueCurrency;
	
	private Date valueDate;
	
	private Double valueDouble;
	
	private double valueDoubleType;
	
	private EnumType valueEnumType;
	
	private Float valueFloat;
	
	private float valueFloatType;
	
	private Integer valueInt;
	
	private int valueIntType;
	
	private Locale valueLocale;
	
	private Long valueLong;
	
	private Long[] valueLongArray;
	
	private LinkedList<Long> valueLongList;
	
	private long valueLongType;
	
	private long[] valueLongTypeArray;
	
	private Short valueShort;
	
	private short valueShortType;
	
	private String valueString;
	
	private String[] valueStringArray;

	private LinkedList<String> valueStrinList;

	private Time valueTime;

	private List<Time> valueTimeList;

	private TimeZone valueTimeZone;

	private URL valueUrl;

	public long getId() {
		return id;
	}

	public Bean2 getValueBean() {
		return valueBean;
	}

	public Bean2[] getValueBeanArray() {
		return valueBeanArray;
	}

	public BigDecimal getValueBigDecimal() {
		return valueBigDecimal;
	}

	public BigInteger getValueBigInteger() {
		return valueBigInteger;
	}

	public Boolean getValueBool() {
		return valueBool;
	}

	public Byte getValueByte() {
		return valueByte;
	}

	public byte[] getValueByteArray() {
		return valueByteArray;
	}

	public byte getValueByteType() {
		return valueByteType;
	}

	public Calendar getValueCalendar() {
		return valueCalendar;
	}

	public Character getValueChar() {
		return valueChar;
	}

	public Character[] getValueCharArray() {
		return valueCharArray;
	}

	public LinkedList<Character> getValueCharList() {
		return valueCharList;
	}

	public char getValueCharType() {
		return valueCharType;
	}

	public char[] getValueCharTypeArray() {
		return valueCharTypeArray;
	}

	public Currency getValueCurrency() {
		return valueCurrency;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public Double getValueDouble() {
		return valueDouble;
	}

	public double getValueDoubleType() {
		return valueDoubleType;
	}

	public EnumType getValueEnumType() {
		return valueEnumType;
	}

	public Float getValueFloat() {
		return valueFloat;
	}

	public float getValueFloatType() {
		return valueFloatType;
	}

	public Integer getValueInt() {
		return valueInt;
	}

	public int getValueIntType() {
		return valueIntType;
	}

	public Locale getValueLocale() {
		return valueLocale;
	}

	public Long getValueLong() {
		return valueLong;
	}

	public Long[] getValueLongArray() {
		return valueLongArray;
	}

	public LinkedList<Long> getValueLongList() {
		return valueLongList;
	}

	public long getValueLongType() {
		return valueLongType;
	}

	public long[] getValueLongTypeArray() {
		return valueLongTypeArray;
	}

	public Short getValueShort() {
		return valueShort;
	}

	public short getValueShortType() {
		return valueShortType;
	}

	public String getValueString() {
		return valueString;
	}

	public String[] getValueStringArray() {
		return valueStringArray;
	}

	public LinkedList<String> getValueStrinList() {
		return valueStrinList;
	}

	public Time getValueTime() {
		return valueTime;
	}

	public List<Time> getValueTimeList() {
		return valueTimeList;
	}

	public TimeZone getValueTimeZone() {
		return valueTimeZone;
	}

	public URL getValueUrl() {
		return valueUrl;
	}

	public boolean isValueBoolType() {
		return valueBoolType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setValueBean(Bean2 valueBean) {
		this.valueBean = valueBean;
	}

	public void setValueBeanArray(Bean2[] valueBeanArray) {
		this.valueBeanArray = valueBeanArray;
	}

	public void setValueBigDecimal(BigDecimal valueBigDecimal) {
		this.valueBigDecimal = valueBigDecimal;
	}

	public void setValueBigInteger(BigInteger valueBigInteger) {
		this.valueBigInteger = valueBigInteger;
	}

	public void setValueBool(Boolean valueBool) {
		this.valueBool = valueBool;
	}

	public void setValueBoolType(boolean valueBoolType) {
		this.valueBoolType = valueBoolType;
	}

	public void setValueByte(Byte valueByte) {
		this.valueByte = valueByte;
	}

	public void setValueByteArray(byte[] valueByteArray) {
		this.valueByteArray = valueByteArray;
	}

	public void setValueByteType(byte valueByteType) {
		this.valueByteType = valueByteType;
	}

	public void setValueCalendar(Calendar valueCalendar) {
		this.valueCalendar = valueCalendar;
	}

	public void setValueChar(Character valueChar) {
		this.valueChar = valueChar;
	}

	public void setValueCharArray(Character[] valueCharArray) {
		this.valueCharArray = valueCharArray;
	}

	public void setValueCharList(LinkedList<Character> valueCharList) {
		this.valueCharList = valueCharList;
	}

	public void setValueCharType(char valueCharType) {
		this.valueCharType = valueCharType;
	}

	public void setValueCharTypeArray(char[] valueCharTypeArray) {
		this.valueCharTypeArray = valueCharTypeArray;
	}

	public void setValueCurrency(Currency valueCurrency) {
		this.valueCurrency = valueCurrency;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public void setValueDouble(Double valueDouble) {
		this.valueDouble = valueDouble;
	}

	public void setValueDoubleType(double valueDoubleType) {
		this.valueDoubleType = valueDoubleType;
	}

	public void setValueEnumType(EnumType valueEnumType) {
		this.valueEnumType = valueEnumType;
	}

	public void setValueFloat(Float valueFloat) {
		this.valueFloat = valueFloat;
	}

	public void setValueFloatType(float valueFloatType) {
		this.valueFloatType = valueFloatType;
	}

	public void setValueInt(Integer valueInt) {
		this.valueInt = valueInt;
	}

	public void setValueIntType(int valueIntType) {
		this.valueIntType = valueIntType;
	}

	public void setValueLocale(Locale valueLocale) {
		this.valueLocale = valueLocale;
	}

	public void setValueLong(Long valueLong) {
		this.valueLong = valueLong;
	}

	public void setValueLongArray(Long[] valueLongArray) {
		this.valueLongArray = valueLongArray;
	}

	public void setValueLongList(LinkedList<Long> valueLongList) {
		this.valueLongList = valueLongList;
	}

	public void setValueLongType(long valueLongType) {
		this.valueLongType = valueLongType;
	}

	public void setValueLongTypeArray(long[] valueLongTypeArray) {
		this.valueLongTypeArray = valueLongTypeArray;
	}

	public void setValueShort(Short valueShort) {
		this.valueShort = valueShort;
	}

	public void setValueShortType(short valueShortType) {
		this.valueShortType = valueShortType;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public void setValueStringArray(String[] valueStringArray) {
		this.valueStringArray = valueStringArray;
	}
	
	public void setValueStrinList(LinkedList<String> valueStrinList) {
		this.valueStrinList = valueStrinList;
	}
	
	public void setValueTime(Time valueTime) {
		this.valueTime = valueTime;
	}

	public void setValueTimeList(List<Time> valueTimeList) {
		this.valueTimeList = valueTimeList;
	}

	public void setValueTimeZone(TimeZone valueTimeZone) {
		this.valueTimeZone = valueTimeZone;
	}
	
	public void setValueUrl(URL valueUrl) {
		this.valueUrl = valueUrl;
	}
}

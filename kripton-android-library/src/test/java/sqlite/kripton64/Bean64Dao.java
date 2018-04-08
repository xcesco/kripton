/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton64;

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
import java.util.Set;
import java.util.TimeZone;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface Bean64Dao.
 */
@BindDao(Bean64.class)
public interface Bean64Dao {
	
	/**
	 * Delete.
	 *
	 * @param valueBigDecimal the value big decimal
	 * @return the long
	 */
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigDecimal valueBigDecimal);
	
	/**
	 * Delete.
	 *
	 * @param valueBigDecimal the value big decimal
	 * @return the long
	 */
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigInteger valueBigDecimal);

	/**
	 * Delete.
	 *
	 * @param valueBoolType the value bool type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueBoolType=${valueBoolType}")
	long delete(boolean valueBoolType);
	
	/**
	 * Delete.
	 *
	 * @param valueBool the value bool
	 * @return the long
	 */
	@BindSqlDelete(where = "valueBool=${valueBool}")
	long delete(Boolean valueBool);
	
	/**
	 * Delete array bean type.
	 *
	 * @param valueBeanArray the value bean array
	 * @return the long
	 */
	@BindSqlDelete(where = "valueBeanArray=${valueBeanArray}")
	long deleteArrayBeanType(Bean64[] valueBeanArray);
	
	/**
	 * Delete array long.
	 *
	 * @param valueLongArray the value long array
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLongArray=${valueLongArray}")
	long deleteArrayLong(Long[] valueLongArray);

	/**
	 * Delete array long type.
	 *
	 * @param valueLongTypeArray the value long type array
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLongTypeArray=${valueLongTypeArray}")
	long deleteArrayLongType(long[] valueLongTypeArray);
	
	/**
	 * Delete byte.
	 *
	 * @param valueByte the value byte
	 * @return the long
	 */
	@BindSqlDelete(where = "valueByte=${valueByte}")
	long deleteByte(Byte valueByte);
	
	/**
	 * Delete byte type.
	 *
	 * @param valueByteType the value byte type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueByteType=${valueByteType}")
	long deleteByteType(boolean valueByteType);
	
	/**
	 * Delete calendar.
	 *
	 * @param valueCalendar the value calendar
	 * @return the long
	 */
	@BindSqlDelete(where = "valueCalendar=${valueCalendar}")
	long deleteCalendar(Date valueCalendar);
	
	/**
	 * Delete char.
	 *
	 * @param valueChar the value char
	 * @return the long
	 */
	@BindSqlDelete(where = "valueCharType=${valueChar}")
	long deleteChar(Character valueChar);
	
	/**
	 * Delete char type.
	 *
	 * @param valueCharType the value char type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueCharType=${valueCharType}")
	long deleteCharType(char valueCharType);
	
	/**
	 * Delete currency.
	 *
	 * @param valueCurrency the value currency
	 * @return the long
	 */
	@BindSqlDelete(where = "valueCurrency=${valueCurrency}")
	long deleteCurrency(Currency valueCurrency);
	
	/**
	 * Delete date.
	 *
	 * @param valueDate the value date
	 * @return the long
	 */
	@BindSqlDelete(where = "valueDate=${valueDate}")
	long deleteDate(Date valueDate);
	
	/**
	 * Delete double.
	 *
	 * @param valueDouble the value double
	 * @return the long
	 */
	@BindSqlDelete(where = "valueDouble=${valueDouble}")
	long deleteDouble(Double valueDouble);
	
	/**
	 * Delete double type.
	 *
	 * @param valueDoubleType the value double type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueDoubleType=${valueDoubleType}")
	long deleteDoubleType(double valueDoubleType);
	
	/**
	 * Delete enum type.
	 *
	 * @param valueEnumType the value enum type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueEnumType=${valueEnumType}")
	long deleteEnumType(EnumType valueEnumType);
	
	
	
	//--
	
	/**
	 * Delete float.
	 *
	 * @param valueFloat the value float
	 * @return the long
	 */
	@BindSqlDelete(where = "valueFloat=${valueFloat}")
	long deleteFloat(Float valueFloat);	

	/**
	 * Delete float type.
	 *
	 * @param valueFloatType the value float type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueFloatType=${valueFloatType}")
	long deleteFloatType(float valueFloatType);

	/**
	 * Delete int.
	 *
	 * @param valueInt the value int
	 * @return the long
	 */
	@BindSqlDelete(where = "valueInt=${valueInt}")
	long deleteInt(Integer valueInt);

	/**
	 * Delete int type.
	 *
	 * @param valueIntType the value int type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueIntType=${valueIntType}")
	long deleteIntType(int valueIntType);
	
	/**
	 * Delete list long.
	 *
	 * @param valueLongList the value long list
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLongList=${valueLongList}")
	long deleteListLong(LinkedList<Long> valueLongList);
	
	/**
	 * Delete locale.
	 *
	 * @param valueLocale the value locale
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLocale=${valueLocale}")
	long deleteLocale(Date valueLocale);
	
	/**
	 * Delete long.
	 *
	 * @param valueLong the value long
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLong=${valueLong}")
	long deleteLong(Long valueLong);
	
	/**
	 * Delete long type.
	 *
	 * @param valueLongType the value long type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueLongType=${valueLongType}")
	long deleteLongType(long valueLongType);
	
	/**
	 * Delete short.
	 *
	 * @param valueShort the value short
	 * @return the long
	 */
	@BindSqlDelete(where = "valueShort=${valueShort}")
	long deleteShort(Short valueShort);
	
	/**
	 * Delete short type.
	 *
	 * @param valueShortType the value short type
	 * @return the long
	 */
	@BindSqlDelete(where = "valueShortType=${valueShortType}")
	long deleteShortType(short valueShortType);
	
	/**
	 * Delete string.
	 *
	 * @param valueString the value string
	 * @return the long
	 */
	@BindSqlDelete(where = "valueString=${valueString}")
	long deleteString(String valueString);
	
	/**
	 * Delete time.
	 *
	 * @param valueTime the value time
	 * @return the long
	 */
	@BindSqlDelete(where = "valueTime=${valueTime}")
	long deleteTime(Time valueTime);
	
	/**
	 * Delete time zone.
	 *
	 * @param valueTimeZone the value time zone
	 * @return the long
	 */
	@BindSqlDelete(where = "valueTimeZone=${valueTimeZone}")
	long deleteTimeZone(TimeZone valueTimeZone);
	
	/**
	 * Delete URL.
	 *
	 * @param valueUrl the value url
	 * @return the long
	 */
	@BindSqlDelete(where = "valueUrl=${valueUrl}")
	long deleteURL(URL valueUrl);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(Bean64 bean);
	
	
	
	/**
	 * Insert.
	 *
	 * @param valueBigDecimal the value big decimal
	 * @return the long
	 */
	// BigDecimal
	@BindSqlInsert()
	long insert(BigDecimal valueBigDecimal);
	
	/**
	 * Insert.
	 *
	 * @param valueBigInteger the value big integer
	 * @return the long
	 */
	// BigInteger
	@BindSqlInsert()
	long insert(BigInteger valueBigInteger);
	
	/**
	 * Insert.
	 *
	 * @param valueBoolType the value bool type
	 * @return the long
	 */
	// boolean
	@BindSqlInsert()
	long insert(boolean valueBoolType);
	
	/**
	 * Insert.
	 *
	 * @param valueBool the value bool
	 * @return the long
	 */
	// Boolean
	@BindSqlInsert()
	long insert(Boolean valueBool);
	
	
	
	/**
	 * Insert array bean type.
	 *
	 * @param valueBeanArray the value bean array
	 * @return the long
	 */
	// array bean	
	@BindSqlInsert
	long insertArrayBeanType(Bean64[] valueBeanArray);
	
	/**
	 * Insert array long.
	 *
	 * @param valueLongArray the value long array
	 * @return the long
	 */
	// array Long	
	@BindSqlInsert
	long insertArrayLong(Long[] valueLongArray);
	
	/**
	 * Insert array long type.
	 *
	 * @param valueLongTypeArray the value long type array
	 * @return the long
	 */
	// array long	
	@BindSqlInsert
	long insertArrayLongType(long[] valueLongTypeArray);
	
	/**
	 * Insert byte.
	 *
	 * @param valueByte the value byte
	 * @return the long
	 */
	// Byte
	@BindSqlInsert()
	long insertByte(Byte valueByte);
	
	
	/**
	 * Insert byte type.
	 *
	 * @param valueByteType the value byte type
	 * @return the long
	 */
	// byte
	@BindSqlInsert()
	long insertByteType(byte valueByteType);
	
	/**
	 * Insert calendar.
	 *
	 * @param valueCalendar the value calendar
	 * @return the long
	 */
	// calendar
	@BindSqlInsert()
	long insertCalendar(Calendar valueCalendar);
	
	/**
	 * Insert char.
	 *
	 * @param valueChar the value char
	 * @return the long
	 */
	// char
	@BindSqlInsert()
	long insertChar(Character valueChar);
	
	/**
	 * Insert char type.
	 *
	 * @param valueCharType the value char type
	 * @return the long
	 */
	// char type
	@BindSqlInsert()
	long insertCharType(char valueCharType);
	
	
	/**
	 * Insert currency.
	 *
	 * @param valueCurrency the value currency
	 * @return the long
	 */
	// Currency
	@BindSqlInsert()
	long insertCurrency(Currency valueCurrency);
	
	/**
	 * Insert date.
	 *
	 * @param valueDate the value date
	 * @return the long
	 */
	// date
	@BindSqlInsert()
	long insertDate(Date valueDate);
	
	/**
	 * Insert double.
	 *
	 * @param valueDouble the value double
	 * @return the long
	 */
	// double
	@BindSqlInsert()
	long insertDouble(Double valueDouble);
	
	/**
	 * Insert double type.
	 *
	 * @param valueDoubleType the value double type
	 * @return the long
	 */
	// double type
	@BindSqlInsert()
	long insertDoubleType(double valueDoubleType);
	
	
	
	/**
	 * Insert enum type.
	 *
	 * @param valueEnumType the value enum type
	 * @return the long
	 */
	// enum
	@BindSqlInsert()
	long insertEnumType(EnumType valueEnumType);
	
	/**
	 * Insert float.
	 *
	 * @param valueFloat the value float
	 * @return the long
	 */
	// float
	@BindSqlInsert()
	long insertFloat(Float valueFloat);
	
	/**
	 * Insert float type.
	 *
	 * @param valueFloatType the value float type
	 * @return the long
	 */
	// float type
	@BindSqlInsert()
	long insertFloatType(float valueFloatType);
	
	/**
	 * Insert int.
	 *
	 * @param valueInt the value int
	 * @return the long
	 */
	// int
	@BindSqlInsert()
	long insertInt(Integer valueInt);
				
	/**
	 * Insert int type.
	 *
	 * @param valueIntType the value int type
	 * @return the long
	 */
	// int type
	@BindSqlInsert()
	long insertIntType(int valueIntType);
	
	/**
	 * Insert list long.
	 *
	 * @param valueLongList the value long list
	 * @return the long
	 */
	// List Long	
	@BindSqlInsert
	long insertListLong(LinkedList<Long> valueLongList);
	
	/**
	 * Insert locale.
	 *
	 * @param valueLocale the value locale
	 * @return the long
	 */
	// locale
	@BindSqlInsert()
	long insertLocale(Locale valueLocale);
	
	/**
	 * Insert long.
	 *
	 * @param valueLong the value long
	 * @return the long
	 */
	// long
	@BindSqlInsert()
	long insertLong(Long valueLong);
	
	
	
	/**
	 * Insert long type.
	 *
	 * @param valueLongType the value long type
	 * @return the long
	 */
	// long type
	@BindSqlInsert()
	long insertLongType(long valueLongType);
	
	/**
	 * Insert short.
	 *
	 * @param valueShort the value short
	 * @return the long
	 */
	// Short
	@BindSqlInsert()
	long insertShort(Short valueShort);
	
	/**
	 * Insert short type.
	 *
	 * @param valueShortType the value short type
	 * @return the long
	 */
	// short type
	@BindSqlInsert()
	long insertShortType(short valueShortType);
	
	/**
	 * Insert string.
	 *
	 * @param valueString the value string
	 * @return the long
	 */
	// string
	@BindSqlInsert()
	long insertString(String valueString);
				
	/**
	 * Insert time.
	 *
	 * @param valueTime the value time
	 * @return the long
	 */
	// Time
	@BindSqlInsert()
	long insertTime(Time valueTime);
	
	/**
	 * Insert time zone.
	 *
	 * @param valueTimeZone the value time zone
	 * @return the long
	 */
	// timezone
	@BindSqlInsert()
	long insertTimeZone(TimeZone valueTimeZone);
	
	/**
	 * Insert URL.
	 *
	 * @param valueUrl the value url
	 * @return the long
	 */
	// URL
	@BindSqlInsert()
	long insertURL(URL valueUrl);
	
	/**
	 * Select list.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where = "id = ${id}")
	List<Bean64> selectList(long id);
	
	
	/**
	 * Select one.
	 *
	 * @return the bean 64
	 */
	@BindSqlSelect()
	Bean64 selectOne();
	
	/**
	 * Select one.
	 *
	 * @param valueBigDecimal the value big decimal
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean64 selectOne(BigDecimal valueBigDecimal);
	
	/**
	 * Select one.
	 *
	 * @param valueBigDecimal the value big decimal
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean64 selectOne(BigInteger valueBigDecimal);
	
	/**
	 * Select one.
	 *
	 * @param valueBoolType the value bool type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueBoolType=${valueBoolType}")
	Bean64 selectOne(boolean valueBoolType);
				
	/**
	 * Select one.
	 *
	 * @param valueBool the value bool
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueBool=${valueBool}")
	Bean64 selectOne(Boolean valueBool);
	
	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, OnReadBeanListener<Bean64> listener);
	
	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, OnReadCursorListener listener);
	
	/**
	 * Select one array bean type.
	 *
	 * @param valueBeanArray the value bean array
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueBeanArray=${valueBeanArray}")
	Bean64 selectOneArrayBeanType(Bean64[] valueBeanArray);
	
	
	
	/**
	 * Select one array long.
	 *
	 * @param valueLongArray the value long array
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLongArray=${valueLongArray}")
	Bean64 selectOneArrayLong(Long[] valueLongArray);
	
	/**
	 * Select one array long type.
	 *
	 * @param valueLongTypeArray the value long type array
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLongTypeArray=${valueLongTypeArray}")
	Bean64 selectOneArrayLongType(long[] valueLongTypeArray);
	
	/**
	 * Select one byte.
	 *
	 * @param valueByte the value byte
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueByte=${valueByte}")
	Bean64 selectOneByte(Byte valueByte);
	
	/**
	 * Select one byte type.
	 *
	 * @param valueByteType the value byte type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueByteType=${valueByteType}")
	Bean64 selectOneByteType(boolean valueByteType);
				
	/**
	 * Select one calendar.
	 *
	 * @param valueCalendar the value calendar
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueCalendar=${valueCalendar}")
	Bean64 selectOneCalendar(Calendar valueCalendar);
	
	/**
	 * Select one char.
	 *
	 * @param valueChar the value char
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueCharType=${valueChar}")
	Bean64 selectOneChar(Character valueChar);
	
	/**
	 * Select one char type.
	 *
	 * @param valueCharType the value char type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueCharType=${valueCharType}")
	Bean64 selectOneCharType(char valueCharType);
	
	/**
	 * Select one currencye.
	 *
	 * @param valueCurrency the value currency
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueCurrency=${valueCurrency}")
	Bean64 selectOneCurrencye(Currency valueCurrency);


	/**
	 * Select one date.
	 *
	 * @param valueDate the value date
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueDate=${valueDate}")
	Bean64 selectOneDate(Date valueDate);
	
	/**
	 * Select one double.
	 *
	 * @param valueDouble the value double
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueDouble=${valueDouble}")
	Bean64 selectOneDouble(Double valueDouble);
	
	/**
	 * Select one double type.
	 *
	 * @param valueDoubleType the value double type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueDoubleType=${valueDoubleType}")
	Bean64 selectOneDoubleType(double valueDoubleType);
	
	/**
	 * Select one enum type.
	 *
	 * @param valueEnumType the value enum type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueEnumType=${valueEnumType}")
	Bean64 selectOneEnumType(EnumType valueEnumType);
				
	/**
	 * Select one float.
	 *
	 * @param valueFloat the value float
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueFloat=${valueFloat}")
	Bean64 selectOneFloat(Float valueFloat);
	
	/**
	 * Select one float type.
	 *
	 * @param valueFloatType the value float type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueFloatType=${valueFloatType}")
	Bean64 selectOneFloatType(float valueFloatType);
	
	/**
	 * Select one int.
	 *
	 * @param valueInt the value int
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueInt=${valueInt}")
	Bean64 selectOneInt(Integer valueInt);
	
	/**
	 * Select one int type.
	 *
	 * @param valueIntType the value int type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueIntType=${valueIntType}")
	Bean64 selectOneIntType(int valueIntType);
	
	/**
	 * Select one list long.
	 *
	 * @param valueLongList the value long list
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLongList=${valueLongList}")
	Bean64 selectOneListLong(LinkedList<Long> valueLongList);
	
	/**
	 * Select one locale.
	 *
	 * @param valueLocale the value locale
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLocale=${valueLocale}")
	Bean64 selectOneLocale(Calendar valueLocale);
	
	/**
	 * Select one long.
	 *
	 * @param valueLong the value long
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLong=${valueLong}")
	Bean64 selectOneLong(Long valueLong);
	
	/**
	 * Select one long type.
	 *
	 * @param valueLongType the value long type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueLongType=${valueLongType}")
	Bean64 selectOneLongType(long valueLongType);
				
	/**
	 * Select one short.
	 *
	 * @param valueShort the value short
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueShort=${valueShort}")
	Bean64 selectOneShort(Short valueShort);
	
	/**
	 * Select one short type.
	 *
	 * @param valueShortType the value short type
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueShortType=${valueShortType}")
	Bean64 selectOneShortType(short valueShortType);
	
	/**
	 * Select one string.
	 *
	 * @param valueString the value string
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueString=${valueString}")
	Bean64 selectOneString(String valueString);
	
	/**
	 * Select one time.
	 *
	 * @param valueTime the value time
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueTime=${valueTime}")
	Bean64 selectOneTime(Time valueTime);
	
	
	/**
	 * Select one time zone.
	 *
	 * @param valueTimeZone the value time zone
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueTimeZone=${valueTimeZone}")
	Bean64 selectOneTimeZone(TimeZone valueTimeZone);
	
	/**
	 * Select one URL.
	 *
	 * @param valueUrl the value url
	 * @return the bean 64
	 */
	@BindSqlSelect(where = "valueUrl=${valueUrl}")
	Bean64 selectOneURL(URL valueUrl);
	
	/**
	 * Select value bool.
	 *
	 * @return the boolean
	 */
	@BindSqlSelect(fields="valueBool")
	public Boolean selectValueBool();
	
	/**
	 * Select value bool type.
	 *
	 * @return true, if successful
	 */
	//-- select raw
	@BindSqlSelect(fields="valueBoolType")
	boolean selectValueBoolType();
	
	
	/**
	 * Select value byte.
	 *
	 * @return the byte
	 */
	@BindSqlSelect(fields="valueByte")
	public Byte selectValueByte();
	
	/**
	 * Select value byte type.
	 *
	 * @return the byte
	 */
	@BindSqlSelect(fields="valueByteType")
	public byte selectValueByteType();
	
	/**
	 * Select value char.
	 *
	 * @return the character
	 */
	@BindSqlSelect(fields="valueChar")
	public Character selectValueChar();
	
	/**
	 * Select value char type.
	 *
	 * @return the char
	 */
	@BindSqlSelect(fields="valueCharType")
	public char selectValueCharType();
	
	
	/**
	 * Select value double.
	 *
	 * @return the double
	 */
	@BindSqlSelect(fields="valueDouble")
	public Double selectValueDouble();
	
	/**
	 * Select value double type.
	 *
	 * @return the double
	 */
	@BindSqlSelect(fields="valueDoubleType")
	public double selectValueDoubleType();
	
	/**
	 * Select value float.
	 *
	 * @return the float
	 */
	@BindSqlSelect(fields="valueFloat")
	public Float selectValueFloat();
	
	/**
	 * Select value float type.
	 *
	 * @return the float
	 */
	@BindSqlSelect(fields="valueFloatType")
	public float selectValueFloatType();

	
	/**
	 * Select value int.
	 *
	 * @return the integer
	 */
	@BindSqlSelect(fields="valueInt")
	public Integer selectValueInt();
	
	/**
	 * Select value int type.
	 *
	 * @return the int
	 */
	@BindSqlSelect(fields="valueIntType")
	public int selectValueIntType();
	
	/**
	 * Select value long.
	 *
	 * @return the long
	 */
	@BindSqlSelect(fields="valueLong")
	public Long selectValueLong();
	
	/**
	 * Select value long type.
	 *
	 * @return the long
	 */
	@BindSqlSelect(fields="valueLongType")
	public long selectValueLongType();
	
	
	/**
	 * Select value short.
	 *
	 * @return the short
	 */
	@BindSqlSelect(fields="valueShort")
	public Short selectValueShort();
	
	/**
	 * Select value short type.
	 *
	 * @return the short
	 */
	@BindSqlSelect(fields="valueShortType")
	public short selectValueShortType();
	
	/**
	 * Select value string.
	 *
	 * @return the string
	 */
	@BindSqlSelect(fields="valueString")
	public String selectValueString();
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean64 value);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueBigDecimal the value big decimal
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(long id, BigDecimal valueBigDecimal);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueBigDecimal the value big decimal
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(long id, BigInteger valueBigDecimal);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueBoolType the value bool type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueBoolType=${valueBoolType}")
	long updateOne(long id, boolean valueBoolType);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueBool the value bool
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueBool=${valueBool}")
	long updateOne(long id, Boolean valueBool);
	
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueSetString the value set string
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id}")
	long updateOne(long id, Set<String> valueSetString);
	
	/**
	 * Update one array bean.
	 *
	 * @param id the id
	 * @param valueBeanArray the value bean array
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueBeanArray=${valueBeanArray}")
	long updateOneArrayBean(long id, Bean64[] valueBeanArray);
	
	/**
	 * Update one array long.
	 *
	 * @param id the id
	 * @param valueLongArray the value long array
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLongArray=${valueLongArray}")
	long updateOneArrayLong(long id, Long[] valueLongArray);
	
	/**
	 * Update one array long type.
	 *
	 * @param id the id
	 * @param valueLongTypeArray the value long type array
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLongTypeArray=${valueLongTypeArray}")
	long updateOneArrayLongType(long id, long[] valueLongTypeArray);
	
	
	/**
	 * Update one byte.
	 *
	 * @param id the id
	 * @param valueByte the value byte
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueByte=${valueByte}")
	long updateOneByte(long id, Byte valueByte);
	
	/**
	 * Update one byte type.
	 *
	 * @param id the id
	 * @param valueByteType the value byte type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueByteType=${valueByteType}")
	long updateOneByteType(long id, boolean valueByteType);
	
	/**
	 * Update one calendar.
	 *
	 * @param id the id
	 * @param valueCalendar the value calendar
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueCalendar=${valueCalendar}")
	long updateOneCalendar(long id, Calendar valueCalendar);
	
	/**
	 * Update one char.
	 *
	 * @param id the id
	 * @param valueChar the value char
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueCharType=${valueChar}")
	long updateOneChar(long id, Character valueChar);
	
	/**
	 * Update one char type.
	 *
	 * @param id the id
	 * @param valueCharType the value char type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueCharType=${valueCharType}")
	long updateOneCharType(long id, char valueCharType);
	
	/**
	 * Update one currency.
	 *
	 * @param id the id
	 * @param valueCurrency the value currency
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueCurrency=${valueCurrency}")
	long updateOneCurrency(long id, Currency valueCurrency);
	
	/**
	 * Update one date.
	 *
	 * @param id the id
	 * @param valueDate the value date
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueDate=${valueDate}")
	long updateOneDate(long id, Date valueDate);
	
	/**
	 * Update one double.
	 *
	 * @param id the id
	 * @param valueDouble the value double
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueDouble=${valueDouble}")
	long updateOneDouble(long id, Double valueDouble);
	
	/**
	 * Update one double type.
	 *
	 * @param id the id
	 * @param valueDoubleType the value double type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueDoubleType=${valueDoubleType}")
	long updateOneDoubleType(long id, double valueDoubleType);
	
	/**
	 * Update one enum type.
	 *
	 * @param id the id
	 * @param valueEnumType the value enum type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueEnumType=${valueEnumType}")
	long updateOneEnumType(long id, EnumType valueEnumType);
	
	/**
	 * Update one float.
	 *
	 * @param id the id
	 * @param valueFloat the value float
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueFloat=${valueFloat}")
	long updateOneFloat(long id, Float valueFloat);
	
	/**
	 * Update one float type.
	 *
	 * @param id the id
	 * @param valueFloatType the value float type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueFloatType=${valueFloatType}")
	long updateOneFloatType(long id, float valueFloatType);
	
	/**
	 * Update one int.
	 *
	 * @param id the id
	 * @param valueInt the value int
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueInt=${valueInt}")
	long updateOneInt(long id, Integer valueInt);
	
	/**
	 * Update one int type.
	 *
	 * @param id the id
	 * @param valueIntType the value int type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueIntType=${valueIntType}")
	long updateOneIntType(long id, int valueIntType);
	
	/**
	 * Update one list long.
	 *
	 * @param id the id
	 * @param valueLongList the value long list
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLongList=${valueLongList}")
	long updateOneListLong(long id, LinkedList<Long> valueLongList);
	
	/**
	 * Update one locale.
	 *
	 * @param id the id
	 * @param valueLocale the value locale
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLocale=${valueLocale}")
	long updateOneLocale(long id, Locale valueLocale);
	
	/**
	 * Update one long.
	 *
	 * @param id the id
	 * @param valueLong the value long
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLong=${valueLong}")
	long updateOneLong(long id, Long valueLong);
	
	/**
	 * Update one long type.
	 *
	 * @param id the id
	 * @param valueLongType the value long type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueLongType=${valueLongType}")
	long updateOneLongType(long id, long valueLongType);
	
	/**
	 * Update one short.
	 *
	 * @param id the id
	 * @param valueShort the value short
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueShort=${valueShort}")
	long updateOneShort(long id, Short valueShort);
	
	/**
	 * Update one short type.
	 *
	 * @param id the id
	 * @param valueShortType the value short type
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueShortType=${valueShortType}")
	long updateOneShortType(long id, short valueShortType);
	
	/**
	 * Update one string.
	 *
	 * @param id the id
	 * @param valueString the value string
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueString=${valueString}")
	long updateOneString(long id, Double valueString);
	
	/**
	 * Update one time.
	 *
	 * @param id the id
	 * @param valueTime the value time
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueTime=${valueTime}")
	long updateOneTime(long id, Time valueTime);
	
	/**
	 * Update one time zone.
	 *
	 * @param id the id
	 * @param valueTimeZone the value time zone
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueTimeZone=${valueTimeZone}")
	long updateOneTimeZone(long id, TimeZone valueTimeZone);
	
	/**
	 * Update one URL.
	 *
	 * @param id the id
	 * @param valueUrl the value url
	 * @return the long
	 */
	@BindSqlUpdate(where = "valueUrl=${valueUrl}")
	long updateOneURL(long id, URL valueUrl);
			
}
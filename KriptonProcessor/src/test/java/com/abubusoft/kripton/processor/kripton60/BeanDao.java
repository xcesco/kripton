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
 ******************************************************************************/
package com.abubusoft.kripton.processor.kripton60;

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

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean.class)
public interface BeanDao {
	//-- select raw
	@BindSelect(value="valueBoolType")
	boolean selectValueBoolType();
	
	@BindSelect(value="valueBool")
	public Boolean selectValueBool();

	@BindSelect(value="valueByteType")
	public byte selectValueByteType();
	
	@BindSelect(value="valueByte")
	public Byte selectValueByte();
	
	@BindSelect(value="valueShortType")
	public short selectValueShortType();
	
	@BindSelect(value="valueShort")
	public Short selectValueShort();

	@BindSelect(value="valueIntType")
	public int selectValueIntType();
	
	@BindSelect(value="valueInt")
	public Integer selectValueInt();
	
	@BindSelect(value="valueString")
	public String selectValueString();
	
	@BindSelect(value="valueCharType")
	public char selectValueCharType();
	
	@BindSelect(value="valueChar")
	public Character selectValueChar();
	
	@BindSelect(value="valueFloatType")
	public float selectValueFloatType();
	
	@BindSelect(value="valueFloat")
	public Float selectValueFloat();
	
	@BindSelect(value="valueLongType")
	public long selectValueLongType();
	
	@BindSelect(value="valueLong")
	public Long selectValueLong();
	
	@BindSelect(value="valueDoubleType")
	public double selectValueDoubleType();
	
	@BindSelect(value="valueDouble")
	public Double selectValueDouble();
	
	
	
	//--
	
	@BindSelect()
	Bean selectOne();	

	@BindSelect(where = "id = ${id}")
	void selectOne(int id, ReadBeanListener<Bean> listener);

	@BindSelect(where = "id = ${id}")
	void selectOne(long id, ReadCursorListener listener);

	@BindSelect(where = "id = ${id}")
	List<Bean> selectList(long id);
	
	@BindUpdate(where = "id=${value.id}")
	long updateOne(Bean value);
	
	@BindInsert()
	long insert(Bean bean);
	
	// BigDecimal
	@BindInsert()
	long insert(BigDecimal valueBigDecimal);
	
	@BindSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigDecimal valueBigDecimal);
	
	@BindDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigDecimal valueBigDecimal);
	
	@BindUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(BigDecimal valueBigDecimal);
	
	// BigInteger
	@BindInsert()
	long insert(BigInteger valueBigInteger);
	
	@BindSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigInteger valueBigDecimal);
	
	@BindDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigInteger valueBigDecimal);
	
	@BindUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(BigInteger valueBigDecimal);
	
	
	
	// boolean
	@BindInsert()
	long insert(boolean valueBoolType);
	
	@BindSelect(where = "valueBoolType=${valueBoolType}")
	Bean selectOne(boolean valueBoolType);
	
	@BindDelete(where = "valueBoolType=${valueBoolType}")
	long delete(boolean valueBoolType);
	
	@BindUpdate(where = "valueBoolType=${valueBoolType}")
	long updateOne(boolean valueBoolType);
	
	
	
	// Boolean
	@BindInsert()
	long insert(Boolean valueBool);
	
	@BindSelect(where = "valueBool=${valueBool}")
	Bean selectOne(Boolean valueBool);
	
	@BindDelete(where = "valueBool=${valueBool}")
	long delete(Boolean valueBool);
	
	@BindUpdate(where = "valueBool=${valueBool}")
	long updateOne(Boolean valueBool);
	
	
	// byte
	@BindInsert()
	long insertByteType(byte valueByteType);
	
	@BindSelect(where = "valueByteType=${valueByteType}")
	Bean selectOneByteType(boolean valueByteType);
	
	@BindDelete(where = "valueByteType=${valueByteType}")
	long deleteByteType(boolean valueByteType);
	
	@BindUpdate(where = "valueByteType=${valueByteType}")
	long updateOneByteType(boolean valueByteType);
	
	
	// Byte
	@BindInsert()
	long insertByte(Byte valueByte);
	
	@BindSelect(where = "valueByte=${valueByte}")
	Bean selectOneByte(Byte valueByte);
	
	@BindDelete(where = "valueByte=${valueByte}")
	long deleteByte(Byte valueByte);
	
	@BindUpdate(where = "valueByte=${valueByte}")
	long updateOneByte(Byte valueByte);
	
	
	
	// char type
	@BindInsert()
	long insertCharType(char valueCharType);
	
	@BindSelect(where = "valueCharType=${valueCharType}")
	Bean selectOneCharType(char valueCharType);
	
	@BindDelete(where = "valueCharType=${valueCharType}")
	long deleteCharType(char valueCharType);
	
	@BindUpdate(where = "valueCharType=${valueCharType}")
	long updateOneCharType(char valueCharType);
				
	// char
	@BindInsert()
	long insertChar(Character valueChar);
	
	@BindSelect(where = "valueCharType=${valueChar}")
	Bean selectOneChar(Character valueChar);
	
	@BindDelete(where = "valueCharType=${valueChar}")
	long deleteChar(Character valueChar);
	
	@BindUpdate(where = "valueCharType=${valueChar}")
	long updateOneChar(Character valueChar);
	
	
	
	// short type
	@BindInsert()
	long insertShortType(short valueShortType);
	
	@BindSelect(where = "valueShortType=${valueShortType}")
	Bean selectOneShortType(short valueShortType);
	
	@BindDelete(where = "valueShortType=${valueShortType}")
	long deleteShortType(short valueShortType);
	
	@BindUpdate(where = "valueShortType=${valueShortType}")
	long updateOneShortType(short valueShortType);
				
	// Short
	@BindInsert()
	long insertShort(Short valueShort);
	
	@BindSelect(where = "valueShort=${valueShort}")
	Bean selectOneShort(Short valueShort);
	
	@BindDelete(where = "valueShort=${valueShort}")
	long deleteShort(Short valueShort);
	
	@BindUpdate(where = "valueShort=${valueShort}")
	long updateOneShort(Short valueShort);
	
	
	// int type
	@BindInsert()
	long insertIntType(int valueIntType);
	
	@BindSelect(where = "valueIntType=${valueIntType}")
	Bean selectOneIntType(int valueIntType);
	
	@BindDelete(where = "valueIntType=${valueIntType}")
	long deleteIntType(int valueIntType);
	
	@BindUpdate(where = "valueIntType=${valueIntType}")
	long updateOneIntType(int valueIntType);
				
	// int
	@BindInsert()
	long insertInt(Integer valueInt);
	
	@BindSelect(where = "valueInt=${valueInt}")
	Bean selectOneInt(Integer valueInt);
	
	@BindDelete(where = "valueInt=${valueInt}")
	long deleteInt(Integer valueInt);
	
	@BindUpdate(where = "valueInt=${valueInt}")
	long updateOneInt(Integer valueInt);
	
	
	
	// long type
	@BindInsert()
	long insertLongType(long valueLongType);
	
	@BindSelect(where = "valueLongType=${valueLongType}")
	Bean selectOneLongType(long valueLongType);
	
	@BindDelete(where = "valueLongType=${valueLongType}")
	long deleteLongType(long valueLongType);
	
	@BindUpdate(where = "valueLongType=${valueLongType}")
	long updateOneLongType(long valueLongType);
				
	// long
	@BindInsert()
	long insertLong(Long valueLong);
	
	@BindSelect(where = "valueLong=${valueLong}")
	Bean selectOneLong(Long valueLong);
	
	@BindDelete(where = "valueLong=${valueLong}")
	long deleteLong(Long valueLong);
	
	@BindUpdate(where = "valueLong=${valueLong}")
	long updateOneLong(Long valueLong);


	// float type
	@BindInsert()
	long insertFloatType(float valueFloatType);
	
	@BindSelect(where = "valueFloatType=${valueFloatType}")
	Bean selectOneFloatType(float valueFloatType);
	
	@BindDelete(where = "valueFloatType=${valueFloatType}")
	long deleteFloatType(float valueFloatType);
	
	@BindUpdate(where = "valueFloatType=${valueFloatType}")
	long updateOneFloatType(float valueFloatType);
				
	// float
	@BindInsert()
	long insertFloat(Float valueFloat);
	
	@BindSelect(where = "valueFloat=${valueFloat}")
	Bean selectOneFloat(Float valueFloat);
	
	@BindDelete(where = "valueFloat=${valueFloat}")
	long deleteFloat(Float valueFloat);
	
	@BindUpdate(where = "valueFloat=${valueFloat}")
	long updateOneFloat(Float valueFloat);
	
	// double type
	@BindInsert()
	long insertDoubleType(double valueDoubleType);
	
	@BindSelect(where = "valueDoubleType=${valueDoubleType}")
	Bean selectOneDoubleType(double valueDoubleType);
	
	@BindDelete(where = "valueDoubleType=${valueDoubleType}")
	long deleteDoubleType(double valueDoubleType);
	
	@BindUpdate(where = "valueDoubleType=${valueDoubleType}")
	long updateOneDoubleType(double valueDoubleType);
				
	// double
	@BindInsert()
	long insertDouble(Double valueDouble);
	
	@BindSelect(where = "valueDouble=${valueDouble}")
	Bean selectOneDouble(Double valueDouble);
	
	@BindDelete(where = "valueDouble=${valueDouble}")
	long deleteDouble(Double valueDouble);
	
	@BindUpdate(where = "valueDouble=${valueDouble}")
	long updateOneDouble(Double valueDouble);
	
	
	// string
	@BindInsert()
	long insertString(String valueString);
	
	@BindSelect(where = "valueString=${valueString}")
	Bean selectOneString(String valueString);
	
	@BindDelete(where = "valueString=${valueString}")
	long deleteString(String valueString);
	
	@BindUpdate(where = "valueString=${valueString}")
	long updateOneString(Double valueString);
	
	
	// date
	@BindInsert()
	long insertDate(Date valueDate);
	
	@BindSelect(where = "valueDate=${valueDate}")
	Bean selectOneDate(Date valueDate);
	
	@BindDelete(where = "valueDate=${valueDate}")
	long deleteDate(Date valueDate);
	
	@BindUpdate(where = "valueDate=${valueDate}")
	long updateOneDate(Date valueDate);
	
	
	// calendar
	@BindInsert()
	long insertCalendar(Calendar valueCalendar);
	
	@BindSelect(where = "valueCalendar=${valueCalendar}")
	Bean selectOneCalendar(Calendar valueCalendar);
	
	@BindDelete(where = "valueCalendar=${valueCalendar}")
	long deleteCalendar(Date valueCalendar);
	
	@BindUpdate(where = "valueCalendar=${valueCalendar}")
	long updateOneCalendar(Calendar valueCalendar);

	
	// locale
	@BindInsert()
	long insertLocale(Locale valueLocale);
	
	@BindSelect(where = "valueLocale=${valueLocale}")
	Bean selectOneLocale(Calendar valueLocale);
	
	@BindDelete(where = "valueLocale=${valueLocale}")
	long deleteLocale(Date valueLocale);
	
	@BindUpdate(where = "valueLocale=${valueLocale}")
	long updateOneLocale(Locale valueLocale);
	
	
	// URL
	@BindInsert()
	long insertURL(URL valueUrl);
	
	@BindSelect(where = "valueUrl=${valueUrl}")
	Bean selectOneURL(URL valueUrl);
	
	@BindDelete(where = "valueUrl=${valueUrl}")
	long deleteURL(URL valueUrl);
	
	@BindUpdate(where = "valueUrl=${valueUrl}")
	long updateOneURL(URL valueUrl);
	
	// Time
	@BindInsert()
	long insertTime(Time valueTime);
	
	@BindSelect(where = "valueTime=${valueTime}")
	Bean selectOneTime(Time valueTime);
	
	@BindDelete(where = "valueTime=${valueTime}")
	long deleteTime(Time valueTime);
	
	@BindUpdate(where = "valueTime=${valueTime}")
	long updateOneTime(Time valueTime);
	
	
	// Currency
	@BindInsert()
	long insertCurrency(Currency valueCurrency);
	
	@BindSelect(where = "valueCurrency=${valueCurrency}")
	Bean selectOneCurrencye(Currency valueCurrency);
	
	@BindDelete(where = "valueCurrency=${valueCurrency}")
	long deleteCurrency(Currency valueCurrency);
	
	@BindUpdate(where = "valueCurrency=${valueCurrency}")
	long updateOneCurrency(Currency valueCurrency);
	
	
	// timezone
	@BindInsert()
	long insertTimeZone(TimeZone valueTimeZone);
	
	@BindSelect(where = "valueTimeZone=${valueTimeZone}")
	Bean selectOneTimeZone(TimeZone valueTimeZone);
	
	@BindDelete(where = "valueTimeZone=${valueTimeZone}")
	long deleteTimeZone(TimeZone valueTimeZone);
	
	@BindUpdate(where = "valueTimeZone=${valueTimeZone}")
	long updateOneTimeZone(TimeZone valueTimeZone);
	
	// enum
	@BindInsert()
	long insertEnumType(EnumType valueEnumType);
	
	@BindSelect(where = "valueEnumType=${valueEnumType}")
	Bean selectOneEnumType(EnumType valueEnumType);
	
	@BindDelete(where = "valueEnumType=${valueEnumType}")
	long deleteEnumType(EnumType valueEnumType);
	
	@BindUpdate(where = "valueEnumType=${valueEnumType}")
	long updateOneEnumType(EnumType valueEnumType);
	
	// array bean	
	@BindInsert
	long insertArrayBeanType(Bean[] valueBeanArray);
	
	@BindSelect(where = "valueBeanArray=${valueBeanArray}")
	Bean selectOneArrayBeanType(Bean[] valueBeanArray);
	
	@BindDelete(where = "valueBeanArray=${valueBeanArray}")
	long deleteArrayBeanType(Bean[] valueBeanArray);
	
	@BindUpdate(where = "valueBeanArray=${valueBeanArray}")
	long updateOneArrayBean(Bean[] valueBeanArray);
	
	// array long	
	@BindInsert
	long insertArrayLongType(long[] valueLongTypeArray);
	
	@BindSelect(where = "valueLongTypeArray=${valueLongTypeArray}")
	Bean selectOneArrayLongType(long[] valueLongTypeArray);
	
	@BindDelete(where = "valueLongTypeArray=${valueLongTypeArray}")
	long deleteArrayLongType(long[] valueLongTypeArray);
	
	@BindUpdate(where = "valueLongTypeArray=${valueLongTypeArray}")
	long updateOneArrayLongType(long[] valueLongTypeArray);
	
	// array Long	
	@BindInsert
	long insertArrayLong(Long[] valueLongArray);
	
	@BindSelect(where = "valueLongArray=${valueLongArray}")
	Bean selectOneArrayLong(Long[] valueLongArray);
	
	@BindDelete(where = "valueLongArray=${valueLongArray}")
	long deleteArrayLong(Long[] valueLongArray);
	
	@BindUpdate(where = "valueLongArray=${valueLongArray}")
	long updateOneArrayLong(Long[] valueLongArray);
	
	// List Long	
	@BindInsert
	long insertListLong(LinkedList<Long> valueLongList);
	
	@BindSelect(where = "valueLongList=${valueLongList}")
	Bean selectOneListLong(LinkedList<Long> valueLongList);
	
	@BindDelete(where = "valueLongList=${valueLongList}")
	long deleteListLong(LinkedList<Long> valueLongList);
	
	@BindUpdate(where = "valueLongList=${valueLongList}")
	long updateOneListLong(LinkedList<Long> valueLongList);
			
}
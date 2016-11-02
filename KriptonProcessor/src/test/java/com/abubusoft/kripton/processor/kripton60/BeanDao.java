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
	
	@BindSelect(where = "value=${value}")
	Bean selectOne(BigDecimal value);
	
	@BindDelete(where = "value=${value}")
	long delete(BigDecimal value);
	
	@BindUpdate(where = "value=${value}")
	long updateOne(BigDecimal value);
	
	// BigInteger
	@BindInsert()
	long insert(BigInteger valueBigInteger);
	
	@BindSelect(where = "value=${value}")
	Bean selectOne(BigInteger value);
	
	@BindDelete(where = "value=${value}")
	long delete(BigInteger value);
	
	@BindUpdate(where = "value=${value}")
	long updateOne(BigInteger value);
	
	
	
	// boolean
	@BindInsert()
	long insert(boolean valueBoolType);
	
	@BindSelect(where = "value=${valueBoolType}")
	Bean selectOne(boolean valueBoolType);
	
	@BindDelete(where = "value=${valueBoolType}")
	long delete(boolean valueBoolType);
	
	@BindUpdate(where = "value=${valueBoolType}")
	long updateOne(boolean valueBoolType);
	
	
	
	// Boolean
	@BindInsert()
	long insert(Boolean valueBool);
	
	@BindSelect(where = "value=${valueBool}")
	Bean selectOne(Boolean valueBool);
	
	@BindDelete(where = "value=${valueBool}")
	long delete(Boolean valueBool);
	
	@BindUpdate(where = "value=${valueBool}")
	long updateOne(Boolean valueBool);
	
	
	// byte
	@BindInsert()
	long insertByteType(byte valueByteType);
	
	@BindSelect(where = "value=${valueByteType}")
	Bean selectOneByteType(boolean valueByteType);
	
	@BindDelete(where = "value=${valueByteType}")
	long deleteByteType(boolean valueByteType);
	
	@BindUpdate(where = "value=${valueByteType}")
	long updateOneByteType(boolean valueByteType);
	
	
	// Byte
	@BindInsert()
	long insertByte(Byte valueByte);
	
	@BindSelect(where = "value=${valueByte}")
	Bean selectOneByte(Byte valueByte);
	
	@BindDelete(where = "value=${valueByte}")
	long deleteByte(Byte valueByte);
	
	@BindUpdate(where = "value=${valueByte}")
	long updateOneByte(Byte valueByte);
	
	
	
	// char type
	@BindInsert()
	long insertCharType(char valueCharType);
	
	@BindSelect(where = "value=${valueCharType}")
	Bean selectOneCharType(char valueCharType);
	
	@BindDelete(where = "value=${valueCharType}")
	long deleteCharType(char valueCharType);
	
	@BindUpdate(where = "value=${valueCharType}")
	long updateOneCharType(char valueCharType);
				
	// char
	@BindInsert()
	long insertChar(Character valueChar);
	
	@BindSelect(where = "value=${valueChar}")
	Bean selectOneChar(Character valueChar);
	
	@BindDelete(where = "value=${valueChar}")
	long deleteChar(Character valueChar);
	
	@BindUpdate(where = "value=${valueChar}")
	long updateOneChar(Character valueChar);
	
	
	
	// short type
	@BindInsert()
	long insertShortType(short valueShortType);
	
	@BindSelect(where = "value=${valueShortType}")
	Bean selectOneShortType(short valueShortType);
	
	@BindDelete(where = "value=${valueShortType}")
	long deleteShortType(short valueShortType);
	
	@BindUpdate(where = "value=${valueShortType}")
	long updateOneShortType(short valueShortType);
				
	// Short
	@BindInsert()
	long insertShort(Short valueShort);
	
	@BindSelect(where = "value=${valueShort}")
	Bean selectOneShort(Short valueShort);
	
	@BindDelete(where = "value=${valueShort}")
	long deleteShort(Short valueShort);
	
	@BindUpdate(where = "value=${valueShort}")
	long updateOneShort(Short valueShort);
	
	
	// int type
	@BindInsert()
	long insertIntType(int valueIntType);
	
	@BindSelect(where = "value=${valueIntType}")
	Bean selectOneIntType(int valueIntType);
	
	@BindDelete(where = "value=${valueIntType}")
	long deleteIntType(int valueIntType);
	
	@BindUpdate(where = "value=${valueIntType}")
	long updateOneIntType(int valueIntType);
				
	// int
	@BindInsert()
	long insertInt(Integer valueInt);
	
	@BindSelect(where = "value=${valueInt}")
	Bean selectOneInt(Integer valueInt);
	
	@BindDelete(where = "value=${valueInt}")
	long deleteInt(Integer valueInt);
	
	@BindUpdate(where = "value=${valueInt}")
	long updateOneInt(Integer valueInt);
	
	
	
	// long type
	@BindInsert()
	long insertLongType(long valueLongType);
	
	@BindSelect(where = "value=${valueLongType}")
	Bean selectOneLongType(long valueLongType);
	
	@BindDelete(where = "value=${valueLongType}")
	long deleteLongType(long valueLongType);
	
	@BindUpdate(where = "value=${valueLongType}")
	long updateOneLongType(long valueLongType);
				
	// long
	@BindInsert()
	long insertLong(Long valueLong);
	
	@BindSelect(where = "value=${valueLong}")
	Bean selectOneLong(Long valueLong);
	
	@BindDelete(where = "value=${valueLong}")
	long deleteLong(Long valueLong);
	
	@BindUpdate(where = "value=${valueLong}")
	long updateOneLong(Long valueLong);


	// float type
	@BindInsert()
	long insertFloatType(float valueFloatType);
	
	@BindSelect(where = "value=${valueFloatType}")
	Bean selectOneFloatType(float valueFloatType);
	
	@BindDelete(where = "value=${valueFloatType}")
	long deleteFloatType(float valueFloatType);
	
	@BindUpdate(where = "value=${valueFloatType}")
	long updateOneFloatType(float valueFloatType);
				
	// float
	@BindInsert()
	long insertFloat(Float valueFloat);
	
	@BindSelect(where = "value=${valueFloat}")
	Bean selectOneFloat(Float valueFloat);
	
	@BindDelete(where = "value=${valueFloat}")
	long deleteFloat(Float valueFloat);
	
	@BindUpdate(where = "value=${valueFloat}")
	long updateOneFloat(Float valueFloat);
	
	// double type
	@BindInsert()
	long insertDoubleType(double valueDoubleType);
	
	@BindSelect(where = "value=${valueDoubleType}")
	Bean selectOneDoubleType(double valueDoubleType);
	
	@BindDelete(where = "value=${valueDoubleType}")
	long deleteDoubleType(double valueDoubleType);
	
	@BindUpdate(where = "value=${valueDoubleType}")
	long updateOneDoubleType(double valueDoubleType);
				
	// double
	@BindInsert()
	long insertDouble(Double valueDouble);
	
	@BindSelect(where = "value=${valueDouble}")
	Bean selectOneDouble(Double valueDouble);
	
	@BindDelete(where = "value=${valueDouble}")
	long deleteDouble(Double valueDouble);
	
	@BindUpdate(where = "value=${valueDouble}")
	long updateOneDouble(Double valueDouble);
	
	
	// string
	@BindInsert()
	long insertString(String valueString);
	
	@BindSelect(where = "value=${valueString}")
	Bean selectOneString(String valueString);
	
	@BindDelete(where = "value=${valueString}")
	long deleteString(String valueString);
	
	@BindUpdate(where = "value=${valueString}")
	long updateOneString(Double valueString);
	
	
	// date
	@BindInsert()
	long insertDate(Date valueDate);
	
	@BindSelect(where = "value=${valueDate}")
	Bean selectOneDate(Date valueDate);
	
	@BindDelete(where = "value=${valueDate}")
	long deleteDate(Date valueDate);
	
	@BindUpdate(where = "value=${valueDate}")
	long updateOneDate(Date valueDate);
	
	
	// calendar
	@BindInsert()
	long insertCalendar(Calendar valueCalendar);
	
	@BindSelect(where = "value=${valueCalendar}")
	Bean selectOneCalendar(Calendar valueCalendar);
	
	@BindDelete(where = "value=${valueCalendar}")
	long deleteCalendar(Date valueCalendar);
	
	@BindUpdate(where = "value=${valueCalendar}")
	long updateOneCalendar(Calendar valueCalendar);

	
	// locale
	@BindInsert()
	long insertLocale(Locale valueLocale);
	
	@BindSelect(where = "value=${valueLocale}")
	Bean selectOneLocale(Calendar valueLocale);
	
	@BindDelete(where = "value=${valueLocale}")
	long deleteLocale(Date valueLocale);
	
	@BindUpdate(where = "value=${valueLocale}")
	long updateOneLocale(Locale valueLocale);
	
	
	// URL
	@BindInsert()
	long insertURL(URL valueUrl);
	
	@BindSelect(where = "value=${valueUrl}")
	Bean selectOneURL(URL valueUrl);
	
	@BindDelete(where = "value=${valueUrl}")
	long deleteURL(URL valueUrl);
	
	@BindUpdate(where = "value=${valueUrl}")
	long updateOneURL(URL valueUrl);
	
	// Time
	@BindInsert()
	long insertTime(Time valueTime);
	
	@BindSelect(where = "value=${valueTime}")
	Bean selectOneTime(Time valueTime);
	
	@BindDelete(where = "value=${valueTime}")
	long deleteTime(Time valueTime);
	
	@BindUpdate(where = "value=${valueTime}")
	long updateOneTime(Time valueTime);
	
	
	// Currency
	@BindInsert()
	long insertCurrency(Currency valueCurrency);
	
	@BindSelect(where = "value=${valueCurrency}")
	Bean selectOneCurrencye(Currency valueCurrency);
	
	@BindDelete(where = "value=${valueCurrency}")
	long deleteCurrency(Currency valueCurrency);
	
	@BindUpdate(where = "value=${valueCurrency}")
	long updateOneCurrency(Currency valueCurrency);
	
	
	// timezone
	@BindInsert()
	long insertTimeZone(TimeZone valueTimeZone);
	
	@BindSelect(where = "value=${valueTimeZone}")
	Bean selectOneTimeZone(TimeZone valueTimeZone);
	
	@BindDelete(where = "value=${valueTimeZone}")
	long deleteTimeZone(TimeZone valueTimeZone);
	
	@BindUpdate(where = "value=${valueTimeZone}")
	long updateOneTimeZone(TimeZone valueTimeZone);
	
	// enum
	@BindInsert()
	long insertEnumType(EnumType valueEnumType);
	
	@BindSelect(where = "value=${valueEnumType}")
	Bean selectOneEnumType(EnumType valueEnumType);
	
	@BindDelete(where = "value=${valueEnumType}")
	long deleteEnumType(EnumType valueEnumType);
	
	@BindUpdate(where = "value=${valueEnumType}")
	long updateOneEnumType(EnumType valueEnumType);
}
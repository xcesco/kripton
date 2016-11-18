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
package kripton64;

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

@BindDao(Bean.class)
public interface BeanDao {
	//-- select raw
	@BindSqlSelect(value="valueBoolType")
	boolean selectValueBoolType();
	
	@BindSqlSelect(value="valueBool")
	public Boolean selectValueBool();

	@BindSqlSelect(value="valueByteType")
	public byte selectValueByteType();
	
	@BindSqlSelect(value="valueByte")
	public Byte selectValueByte();
	
	@BindSqlSelect(value="valueShortType")
	public short selectValueShortType();
	
	@BindSqlSelect(value="valueShort")
	public Short selectValueShort();

	@BindSqlSelect(value="valueIntType")
	public int selectValueIntType();
	
	@BindSqlSelect(value="valueInt")
	public Integer selectValueInt();
	
	@BindSqlSelect(value="valueString")
	public String selectValueString();
	
	@BindSqlSelect(value="valueCharType")
	public char selectValueCharType();
	
	@BindSqlSelect(value="valueChar")
	public Character selectValueChar();
	
	@BindSqlSelect(value="valueFloatType")
	public float selectValueFloatType();
	
	@BindSqlSelect(value="valueFloat")
	public Float selectValueFloat();
	
	@BindSqlSelect(value="valueLongType")
	public long selectValueLongType();
	
	@BindSqlSelect(value="valueLong")
	public Long selectValueLong();
	
	@BindSqlSelect(value="valueDoubleType")
	public double selectValueDoubleType();
	
	@BindSqlSelect(value="valueDouble")
	public Double selectValueDouble();
	
	
	
	//--
	
	@BindSqlSelect()
	Bean selectOne();	

	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, OnReadBeanListener<Bean> listener);

	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, OnReadCursorListener listener);

	@BindSqlSelect(where = "id = ${id}")
	List<Bean> selectList(long id);
	
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean value);
	
	@BindSqlInsert()
	long insert(Bean bean);
	
	// BigDecimal
	@BindSqlInsert()
	long insert(BigDecimal valueBigDecimal);
	
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigDecimal valueBigDecimal);
	
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigDecimal valueBigDecimal);
	
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(BigDecimal valueBigDecimal);
	
	@BindSqlUpdate(where = "id=${id}")
	long updateOne(long id, Set<String> valueSetString);
	
	// BigInteger
	@BindSqlInsert()
	long insert(BigInteger valueBigInteger);
	
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigInteger valueBigDecimal);
	
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigInteger valueBigDecimal);
	
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(BigInteger valueBigDecimal);
	
	
	
	// boolean
	@BindSqlInsert()
	long insert(boolean valueBoolType);
	
	@BindSqlSelect(where = "valueBoolType=${valueBoolType}")
	Bean selectOne(boolean valueBoolType);
	
	@BindSqlDelete(where = "valueBoolType=${valueBoolType}")
	long delete(boolean valueBoolType);
	
	@BindSqlUpdate(where = "valueBoolType=${valueBoolType}")
	long updateOne(boolean valueBoolType);
	
	
	
	// Boolean
	@BindSqlInsert()
	long insert(Boolean valueBool);
	
	@BindSqlSelect(where = "valueBool=${valueBool}")
	Bean selectOne(Boolean valueBool);
	
	@BindSqlDelete(where = "valueBool=${valueBool}")
	long delete(Boolean valueBool);
	
	@BindSqlUpdate(where = "valueBool=${valueBool}")
	long updateOne(Boolean valueBool);
	
	
	// byte
	@BindSqlInsert()
	long insertByteType(byte valueByteType);
	
	@BindSqlSelect(where = "valueByteType=${valueByteType}")
	Bean selectOneByteType(boolean valueByteType);
	
	@BindSqlDelete(where = "valueByteType=${valueByteType}")
	long deleteByteType(boolean valueByteType);
	
	@BindSqlUpdate(where = "valueByteType=${valueByteType}")
	long updateOneByteType(boolean valueByteType);
	
	
	// Byte
	@BindSqlInsert()
	long insertByte(Byte valueByte);
	
	@BindSqlSelect(where = "valueByte=${valueByte}")
	Bean selectOneByte(Byte valueByte);
	
	@BindSqlDelete(where = "valueByte=${valueByte}")
	long deleteByte(Byte valueByte);
	
	@BindSqlUpdate(where = "valueByte=${valueByte}")
	long updateOneByte(Byte valueByte);
	
	
	
	// char type
	@BindSqlInsert()
	long insertCharType(char valueCharType);
	
	@BindSqlSelect(where = "valueCharType=${valueCharType}")
	Bean selectOneCharType(char valueCharType);
	
	@BindSqlDelete(where = "valueCharType=${valueCharType}")
	long deleteCharType(char valueCharType);
	
	@BindSqlUpdate(where = "valueCharType=${valueCharType}")
	long updateOneCharType(char valueCharType);
				
	// char
	@BindSqlInsert()
	long insertChar(Character valueChar);
	
	@BindSqlSelect(where = "valueCharType=${valueChar}")
	Bean selectOneChar(Character valueChar);
	
	@BindSqlDelete(where = "valueCharType=${valueChar}")
	long deleteChar(Character valueChar);
	
	@BindSqlUpdate(where = "valueCharType=${valueChar}")
	long updateOneChar(Character valueChar);
	
	
	
	// short type
	@BindSqlInsert()
	long insertShortType(short valueShortType);
	
	@BindSqlSelect(where = "valueShortType=${valueShortType}")
	Bean selectOneShortType(short valueShortType);
	
	@BindSqlDelete(where = "valueShortType=${valueShortType}")
	long deleteShortType(short valueShortType);
	
	@BindSqlUpdate(where = "valueShortType=${valueShortType}")
	long updateOneShortType(short valueShortType);
				
	// Short
	@BindSqlInsert()
	long insertShort(Short valueShort);
	
	@BindSqlSelect(where = "valueShort=${valueShort}")
	Bean selectOneShort(Short valueShort);
	
	@BindSqlDelete(where = "valueShort=${valueShort}")
	long deleteShort(Short valueShort);
	
	@BindSqlUpdate(where = "valueShort=${valueShort}")
	long updateOneShort(Short valueShort);
	
	
	// int type
	@BindSqlInsert()
	long insertIntType(int valueIntType);
	
	@BindSqlSelect(where = "valueIntType=${valueIntType}")
	Bean selectOneIntType(int valueIntType);
	
	@BindSqlDelete(where = "valueIntType=${valueIntType}")
	long deleteIntType(int valueIntType);
	
	@BindSqlUpdate(where = "valueIntType=${valueIntType}")
	long updateOneIntType(int valueIntType);
				
	// int
	@BindSqlInsert()
	long insertInt(Integer valueInt);
	
	@BindSqlSelect(where = "valueInt=${valueInt}")
	Bean selectOneInt(Integer valueInt);
	
	@BindSqlDelete(where = "valueInt=${valueInt}")
	long deleteInt(Integer valueInt);
	
	@BindSqlUpdate(where = "valueInt=${valueInt}")
	long updateOneInt(Integer valueInt);
	
	
	
	// long type
	@BindSqlInsert()
	long insertLongType(long valueLongType);
	
	@BindSqlSelect(where = "valueLongType=${valueLongType}")
	Bean selectOneLongType(long valueLongType);
	
	@BindSqlDelete(where = "valueLongType=${valueLongType}")
	long deleteLongType(long valueLongType);
	
	@BindSqlUpdate(where = "valueLongType=${valueLongType}")
	long updateOneLongType(long valueLongType);
				
	// long
	@BindSqlInsert()
	long insertLong(Long valueLong);
	
	@BindSqlSelect(where = "valueLong=${valueLong}")
	Bean selectOneLong(Long valueLong);
	
	@BindSqlDelete(where = "valueLong=${valueLong}")
	long deleteLong(Long valueLong);
	
	@BindSqlUpdate(where = "valueLong=${valueLong}")
	long updateOneLong(Long valueLong);


	// float type
	@BindSqlInsert()
	long insertFloatType(float valueFloatType);
	
	@BindSqlSelect(where = "valueFloatType=${valueFloatType}")
	Bean selectOneFloatType(float valueFloatType);
	
	@BindSqlDelete(where = "valueFloatType=${valueFloatType}")
	long deleteFloatType(float valueFloatType);
	
	@BindSqlUpdate(where = "valueFloatType=${valueFloatType}")
	long updateOneFloatType(float valueFloatType);
				
	// float
	@BindSqlInsert()
	long insertFloat(Float valueFloat);
	
	@BindSqlSelect(where = "valueFloat=${valueFloat}")
	Bean selectOneFloat(Float valueFloat);
	
	@BindSqlDelete(where = "valueFloat=${valueFloat}")
	long deleteFloat(Float valueFloat);
	
	@BindSqlUpdate(where = "valueFloat=${valueFloat}")
	long updateOneFloat(Float valueFloat);
	
	// double type
	@BindSqlInsert()
	long insertDoubleType(double valueDoubleType);
	
	@BindSqlSelect(where = "valueDoubleType=${valueDoubleType}")
	Bean selectOneDoubleType(double valueDoubleType);
	
	@BindSqlDelete(where = "valueDoubleType=${valueDoubleType}")
	long deleteDoubleType(double valueDoubleType);
	
	@BindSqlUpdate(where = "valueDoubleType=${valueDoubleType}")
	long updateOneDoubleType(double valueDoubleType);
				
	// double
	@BindSqlInsert()
	long insertDouble(Double valueDouble);
	
	@BindSqlSelect(where = "valueDouble=${valueDouble}")
	Bean selectOneDouble(Double valueDouble);
	
	@BindSqlDelete(where = "valueDouble=${valueDouble}")
	long deleteDouble(Double valueDouble);
	
	@BindSqlUpdate(where = "valueDouble=${valueDouble}")
	long updateOneDouble(Double valueDouble);
	
	
	// string
	@BindSqlInsert()
	long insertString(String valueString);
	
	@BindSqlSelect(where = "valueString=${valueString}")
	Bean selectOneString(String valueString);
	
	@BindSqlDelete(where = "valueString=${valueString}")
	long deleteString(String valueString);
	
	@BindSqlUpdate(where = "valueString=${valueString}")
	long updateOneString(Double valueString);
	
	
	// date
	@BindSqlInsert()
	long insertDate(Date valueDate);
	
	@BindSqlSelect(where = "valueDate=${valueDate}")
	Bean selectOneDate(Date valueDate);
	
	@BindSqlDelete(where = "valueDate=${valueDate}")
	long deleteDate(Date valueDate);
	
	@BindSqlUpdate(where = "valueDate=${valueDate}")
	long updateOneDate(Date valueDate);
	
	
	// calendar
	@BindSqlInsert()
	long insertCalendar(Calendar valueCalendar);
	
	@BindSqlSelect(where = "valueCalendar=${valueCalendar}")
	Bean selectOneCalendar(Calendar valueCalendar);
	
	@BindSqlDelete(where = "valueCalendar=${valueCalendar}")
	long deleteCalendar(Date valueCalendar);
	
	@BindSqlUpdate(where = "valueCalendar=${valueCalendar}")
	long updateOneCalendar(Calendar valueCalendar);

	
	// locale
	@BindSqlInsert()
	long insertLocale(Locale valueLocale);
	
	@BindSqlSelect(where = "valueLocale=${valueLocale}")
	Bean selectOneLocale(Calendar valueLocale);
	
	@BindSqlDelete(where = "valueLocale=${valueLocale}")
	long deleteLocale(Date valueLocale);
	
	@BindSqlUpdate(where = "valueLocale=${valueLocale}")
	long updateOneLocale(Locale valueLocale);
	
	
	// URL
	@BindSqlInsert()
	long insertURL(URL valueUrl);
	
	@BindSqlSelect(where = "valueUrl=${valueUrl}")
	Bean selectOneURL(URL valueUrl);
	
	@BindSqlDelete(where = "valueUrl=${valueUrl}")
	long deleteURL(URL valueUrl);
	
	@BindSqlUpdate(where = "valueUrl=${valueUrl}")
	long updateOneURL(URL valueUrl);
	
	// Time
	@BindSqlInsert()
	long insertTime(Time valueTime);
	
	@BindSqlSelect(where = "valueTime=${valueTime}")
	Bean selectOneTime(Time valueTime);
	
	@BindSqlDelete(where = "valueTime=${valueTime}")
	long deleteTime(Time valueTime);
	
	@BindSqlUpdate(where = "valueTime=${valueTime}")
	long updateOneTime(Time valueTime);
	
	
	// Currency
	@BindSqlInsert()
	long insertCurrency(Currency valueCurrency);
	
	@BindSqlSelect(where = "valueCurrency=${valueCurrency}")
	Bean selectOneCurrencye(Currency valueCurrency);
	
	@BindSqlDelete(where = "valueCurrency=${valueCurrency}")
	long deleteCurrency(Currency valueCurrency);
	
	@BindSqlUpdate(where = "valueCurrency=${valueCurrency}")
	long updateOneCurrency(Currency valueCurrency);
	
	
	// timezone
	@BindSqlInsert()
	long insertTimeZone(TimeZone valueTimeZone);
	
	@BindSqlSelect(where = "valueTimeZone=${valueTimeZone}")
	Bean selectOneTimeZone(TimeZone valueTimeZone);
	
	@BindSqlDelete(where = "valueTimeZone=${valueTimeZone}")
	long deleteTimeZone(TimeZone valueTimeZone);
	
	@BindSqlUpdate(where = "valueTimeZone=${valueTimeZone}")
	long updateOneTimeZone(TimeZone valueTimeZone);
	
	// enum
	@BindSqlInsert()
	long insertEnumType(EnumType valueEnumType);
	
	@BindSqlSelect(where = "valueEnumType=${valueEnumType}")
	Bean selectOneEnumType(EnumType valueEnumType);
	
	@BindSqlDelete(where = "valueEnumType=${valueEnumType}")
	long deleteEnumType(EnumType valueEnumType);
	
	@BindSqlUpdate(where = "valueEnumType=${valueEnumType}")
	long updateOneEnumType(EnumType valueEnumType);
	
	// array bean	
	@BindSqlInsert
	long insertArrayBeanType(Bean[] valueBeanArray);
	
	@BindSqlSelect(where = "valueBeanArray=${valueBeanArray}")
	Bean selectOneArrayBeanType(Bean[] valueBeanArray);
	
	@BindSqlDelete(where = "valueBeanArray=${valueBeanArray}")
	long deleteArrayBeanType(Bean[] valueBeanArray);
	
	@BindSqlUpdate(where = "valueBeanArray=${valueBeanArray}")
	long updateOneArrayBean(Bean[] valueBeanArray);
	
	// array long	
	@BindSqlInsert
	long insertArrayLongType(long[] valueLongTypeArray);
	
	@BindSqlSelect(where = "valueLongTypeArray=${valueLongTypeArray}")
	Bean selectOneArrayLongType(long[] valueLongTypeArray);
	
	@BindSqlDelete(where = "valueLongTypeArray=${valueLongTypeArray}")
	long deleteArrayLongType(long[] valueLongTypeArray);
	
	@BindSqlUpdate(where = "valueLongTypeArray=${valueLongTypeArray}")
	long updateOneArrayLongType(long[] valueLongTypeArray);
	
	// array Long	
	@BindSqlInsert
	long insertArrayLong(Long[] valueLongArray);
	
	@BindSqlSelect(where = "valueLongArray=${valueLongArray}")
	Bean selectOneArrayLong(Long[] valueLongArray);
	
	@BindSqlDelete(where = "valueLongArray=${valueLongArray}")
	long deleteArrayLong(Long[] valueLongArray);
	
	@BindSqlUpdate(where = "valueLongArray=${valueLongArray}")
	long updateOneArrayLong(Long[] valueLongArray);
	
	// List Long	
	@BindSqlInsert
	long insertListLong(LinkedList<Long> valueLongList);
	
	@BindSqlSelect(where = "valueLongList=${valueLongList}")
	Bean selectOneListLong(LinkedList<Long> valueLongList);
	
	@BindSqlDelete(where = "valueLongList=${valueLongList}")
	long deleteListLong(LinkedList<Long> valueLongList);
	
	@BindSqlUpdate(where = "valueLongList=${valueLongList}")
	long updateOneListLong(LinkedList<Long> valueLongList);
			
}
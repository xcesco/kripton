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
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigDecimal valueBigDecimal);
	
	@BindSqlDelete(where = "valueBigDecimal=${valueBigDecimal}")
	long delete(BigInteger valueBigDecimal);

	@BindSqlDelete(where = "valueBoolType=${valueBoolType}")
	long delete(boolean valueBoolType);
	
	@BindSqlDelete(where = "valueBool=${valueBool}")
	long delete(Boolean valueBool);
	
	@BindSqlDelete(where = "valueBeanArray=${valueBeanArray}")
	long deleteArrayBeanType(Bean[] valueBeanArray);
	
	@BindSqlDelete(where = "valueLongArray=${valueLongArray}")
	long deleteArrayLong(Long[] valueLongArray);

	@BindSqlDelete(where = "valueLongTypeArray=${valueLongTypeArray}")
	long deleteArrayLongType(long[] valueLongTypeArray);
	
	@BindSqlDelete(where = "valueByte=${valueByte}")
	long deleteByte(Byte valueByte);
	
	@BindSqlDelete(where = "valueByteType=${valueByteType}")
	long deleteByteType(boolean valueByteType);
	
	@BindSqlDelete(where = "valueCalendar=${valueCalendar}")
	long deleteCalendar(Date valueCalendar);
	
	@BindSqlDelete(where = "valueCharType=${valueChar}")
	long deleteChar(Character valueChar);
	
	@BindSqlDelete(where = "valueCharType=${valueCharType}")
	long deleteCharType(char valueCharType);
	
	@BindSqlDelete(where = "valueCurrency=${valueCurrency}")
	long deleteCurrency(Currency valueCurrency);
	
	@BindSqlDelete(where = "valueDate=${valueDate}")
	long deleteDate(Date valueDate);
	
	@BindSqlDelete(where = "valueDouble=${valueDouble}")
	long deleteDouble(Double valueDouble);
	
	@BindSqlDelete(where = "valueDoubleType=${valueDoubleType}")
	long deleteDoubleType(double valueDoubleType);
	
	@BindSqlDelete(where = "valueEnumType=${valueEnumType}")
	long deleteEnumType(EnumType valueEnumType);
	
	
	
	//--
	
	@BindSqlDelete(where = "valueFloat=${valueFloat}")
	long deleteFloat(Float valueFloat);	

	@BindSqlDelete(where = "valueFloatType=${valueFloatType}")
	long deleteFloatType(float valueFloatType);

	@BindSqlDelete(where = "valueInt=${valueInt}")
	long deleteInt(Integer valueInt);

	@BindSqlDelete(where = "valueIntType=${valueIntType}")
	long deleteIntType(int valueIntType);
	
	@BindSqlDelete(where = "valueLongList=${valueLongList}")
	long deleteListLong(LinkedList<Long> valueLongList);
	
	@BindSqlDelete(where = "valueLocale=${valueLocale}")
	long deleteLocale(Date valueLocale);
	
	@BindSqlDelete(where = "valueLong=${valueLong}")
	long deleteLong(Long valueLong);
	
	@BindSqlDelete(where = "valueLongType=${valueLongType}")
	long deleteLongType(long valueLongType);
	
	@BindSqlDelete(where = "valueShort=${valueShort}")
	long deleteShort(Short valueShort);
	
	@BindSqlDelete(where = "valueShortType=${valueShortType}")
	long deleteShortType(short valueShortType);
	
	@BindSqlDelete(where = "valueString=${valueString}")
	long deleteString(String valueString);
	
	@BindSqlDelete(where = "valueTime=${valueTime}")
	long deleteTime(Time valueTime);
	
	@BindSqlDelete(where = "valueTimeZone=${valueTimeZone}")
	long deleteTimeZone(TimeZone valueTimeZone);
	
	@BindSqlDelete(where = "valueUrl=${valueUrl}")
	long deleteURL(URL valueUrl);
	
	@BindSqlInsert()
	long insert(Bean bean);
	
	
	
	// BigDecimal
	@BindSqlInsert()
	long insert(BigDecimal valueBigDecimal);
	
	// BigInteger
	@BindSqlInsert()
	long insert(BigInteger valueBigInteger);
	
	// boolean
	@BindSqlInsert()
	long insert(boolean valueBoolType);
	
	// Boolean
	@BindSqlInsert()
	long insert(Boolean valueBool);
	
	
	
	// array bean	
	@BindSqlInsert
	long insertArrayBeanType(Bean[] valueBeanArray);
	
	// array Long	
	@BindSqlInsert
	long insertArrayLong(Long[] valueLongArray);
	
	// array long	
	@BindSqlInsert
	long insertArrayLongType(long[] valueLongTypeArray);
	
	// Byte
	@BindSqlInsert()
	long insertByte(Byte valueByte);
	
	
	// byte
	@BindSqlInsert()
	long insertByteType(byte valueByteType);
	
	// calendar
	@BindSqlInsert()
	long insertCalendar(Calendar valueCalendar);
	
	// char
	@BindSqlInsert()
	long insertChar(Character valueChar);
	
	// char type
	@BindSqlInsert()
	long insertCharType(char valueCharType);
	
	
	// Currency
	@BindSqlInsert()
	long insertCurrency(Currency valueCurrency);
	
	// date
	@BindSqlInsert()
	long insertDate(Date valueDate);
	
	// double
	@BindSqlInsert()
	long insertDouble(Double valueDouble);
	
	// double type
	@BindSqlInsert()
	long insertDoubleType(double valueDoubleType);
	
	
	
	// enum
	@BindSqlInsert()
	long insertEnumType(EnumType valueEnumType);
	
	// float
	@BindSqlInsert()
	long insertFloat(Float valueFloat);
	
	// float type
	@BindSqlInsert()
	long insertFloatType(float valueFloatType);
	
	// int
	@BindSqlInsert()
	long insertInt(Integer valueInt);
				
	// int type
	@BindSqlInsert()
	long insertIntType(int valueIntType);
	
	// List Long	
	@BindSqlInsert
	long insertListLong(LinkedList<Long> valueLongList);
	
	// locale
	@BindSqlInsert()
	long insertLocale(Locale valueLocale);
	
	// long
	@BindSqlInsert()
	long insertLong(Long valueLong);
	
	
	
	// long type
	@BindSqlInsert()
	long insertLongType(long valueLongType);
	
	// Short
	@BindSqlInsert()
	long insertShort(Short valueShort);
	
	// short type
	@BindSqlInsert()
	long insertShortType(short valueShortType);
	
	// string
	@BindSqlInsert()
	long insertString(String valueString);
				
	// Time
	@BindSqlInsert()
	long insertTime(Time valueTime);
	
	// timezone
	@BindSqlInsert()
	long insertTimeZone(TimeZone valueTimeZone);
	
	// URL
	@BindSqlInsert()
	long insertURL(URL valueUrl);
	
	@BindSqlSelect(where = "id = ${id}")
	List<Bean> selectList(long id);
	
	
	@BindSqlSelect()
	Bean selectOne();
	
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigDecimal valueBigDecimal);
	
	@BindSqlSelect(where = "valueBigDecimal=${valueBigDecimal}")
	Bean selectOne(BigInteger valueBigDecimal);
	
	@BindSqlSelect(where = "valueBoolType=${valueBoolType}")
	Bean selectOne(boolean valueBoolType);
				
	@BindSqlSelect(where = "valueBool=${valueBool}")
	Bean selectOne(Boolean valueBool);
	
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, OnReadBeanListener<Bean> listener);
	
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, OnReadCursorListener listener);
	
	@BindSqlSelect(where = "valueBeanArray=${valueBeanArray}")
	Bean selectOneArrayBeanType(Bean[] valueBeanArray);
	
	
	
	@BindSqlSelect(where = "valueLongArray=${valueLongArray}")
	Bean selectOneArrayLong(Long[] valueLongArray);
	
	@BindSqlSelect(where = "valueLongTypeArray=${valueLongTypeArray}")
	Bean selectOneArrayLongType(long[] valueLongTypeArray);
	
	@BindSqlSelect(where = "valueByte=${valueByte}")
	Bean selectOneByte(Byte valueByte);
	
	@BindSqlSelect(where = "valueByteType=${valueByteType}")
	Bean selectOneByteType(boolean valueByteType);
				
	@BindSqlSelect(where = "valueCalendar=${valueCalendar}")
	Bean selectOneCalendar(Calendar valueCalendar);
	
	@BindSqlSelect(where = "valueCharType=${valueChar}")
	Bean selectOneChar(Character valueChar);
	
	@BindSqlSelect(where = "valueCharType=${valueCharType}")
	Bean selectOneCharType(char valueCharType);
	
	@BindSqlSelect(where = "valueCurrency=${valueCurrency}")
	Bean selectOneCurrencye(Currency valueCurrency);


	@BindSqlSelect(where = "valueDate=${valueDate}")
	Bean selectOneDate(Date valueDate);
	
	@BindSqlSelect(where = "valueDouble=${valueDouble}")
	Bean selectOneDouble(Double valueDouble);
	
	@BindSqlSelect(where = "valueDoubleType=${valueDoubleType}")
	Bean selectOneDoubleType(double valueDoubleType);
	
	@BindSqlSelect(where = "valueEnumType=${valueEnumType}")
	Bean selectOneEnumType(EnumType valueEnumType);
				
	@BindSqlSelect(where = "valueFloat=${valueFloat}")
	Bean selectOneFloat(Float valueFloat);
	
	@BindSqlSelect(where = "valueFloatType=${valueFloatType}")
	Bean selectOneFloatType(float valueFloatType);
	
	@BindSqlSelect(where = "valueInt=${valueInt}")
	Bean selectOneInt(Integer valueInt);
	
	@BindSqlSelect(where = "valueIntType=${valueIntType}")
	Bean selectOneIntType(int valueIntType);
	
	@BindSqlSelect(where = "valueLongList=${valueLongList}")
	Bean selectOneListLong(LinkedList<Long> valueLongList);
	
	@BindSqlSelect(where = "valueLocale=${valueLocale}")
	Bean selectOneLocale(Calendar valueLocale);
	
	@BindSqlSelect(where = "valueLong=${valueLong}")
	Bean selectOneLong(Long valueLong);
	
	@BindSqlSelect(where = "valueLongType=${valueLongType}")
	Bean selectOneLongType(long valueLongType);
				
	@BindSqlSelect(where = "valueShort=${valueShort}")
	Bean selectOneShort(Short valueShort);
	
	@BindSqlSelect(where = "valueShortType=${valueShortType}")
	Bean selectOneShortType(short valueShortType);
	
	@BindSqlSelect(where = "valueString=${valueString}")
	Bean selectOneString(String valueString);
	
	@BindSqlSelect(where = "valueTime=${valueTime}")
	Bean selectOneTime(Time valueTime);
	
	
	@BindSqlSelect(where = "valueTimeZone=${valueTimeZone}")
	Bean selectOneTimeZone(TimeZone valueTimeZone);
	
	@BindSqlSelect(where = "valueUrl=${valueUrl}")
	Bean selectOneURL(URL valueUrl);
	
	@BindSqlSelect(value="valueBool")
	public Boolean selectValueBool();
	
	//-- select raw
	@BindSqlSelect(value="valueBoolType")
	boolean selectValueBoolType();
	
	
	@BindSqlSelect(value="valueByte")
	public Byte selectValueByte();
	
	@BindSqlSelect(value="valueByteType")
	public byte selectValueByteType();
	
	@BindSqlSelect(value="valueChar")
	public Character selectValueChar();
	
	@BindSqlSelect(value="valueCharType")
	public char selectValueCharType();
	
	
	@BindSqlSelect(value="valueDouble")
	public Double selectValueDouble();
	
	@BindSqlSelect(value="valueDoubleType")
	public double selectValueDoubleType();
	
	@BindSqlSelect(value="valueFloat")
	public Float selectValueFloat();
	
	@BindSqlSelect(value="valueFloatType")
	public float selectValueFloatType();

	
	@BindSqlSelect(value="valueInt")
	public Integer selectValueInt();
	
	@BindSqlSelect(value="valueIntType")
	public int selectValueIntType();
	
	@BindSqlSelect(value="valueLong")
	public Long selectValueLong();
	
	@BindSqlSelect(value="valueLongType")
	public long selectValueLongType();
	
	
	@BindSqlSelect(value="valueShort")
	public Short selectValueShort();
	
	@BindSqlSelect(value="valueShortType")
	public short selectValueShortType();
	
	@BindSqlSelect(value="valueString")
	public String selectValueString();
	
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean value);
	
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(long id, BigDecimal valueBigDecimal);
	
	@BindSqlUpdate(where = "valueBigDecimal=${valueBigDecimal}")
	long updateOne(long id, BigInteger valueBigDecimal);
	
	@BindSqlUpdate(where = "valueBoolType=${valueBoolType}")
	long updateOne(long id, boolean valueBoolType);
	
	@BindSqlUpdate(where = "valueBool=${valueBool}")
	long updateOne(long id, Boolean valueBool);
	
	
	@BindSqlUpdate(where = "id=${id}")
	long updateOne(long id, Set<String> valueSetString);
	
	@BindSqlUpdate(where = "valueBeanArray=${valueBeanArray}")
	long updateOneArrayBean(long id, Bean[] valueBeanArray);
	
	@BindSqlUpdate(where = "valueLongArray=${valueLongArray}")
	long updateOneArrayLong(long id, Long[] valueLongArray);
	
	@BindSqlUpdate(where = "valueLongTypeArray=${valueLongTypeArray}")
	long updateOneArrayLongType(long id, long[] valueLongTypeArray);
	
	
	@BindSqlUpdate(where = "valueByte=${valueByte}")
	long updateOneByte(long id, Byte valueByte);
	
	@BindSqlUpdate(where = "valueByteType=${valueByteType}")
	long updateOneByteType(long id, boolean valueByteType);
	
	@BindSqlUpdate(where = "valueCalendar=${valueCalendar}")
	long updateOneCalendar(long id, Calendar valueCalendar);
	
	@BindSqlUpdate(where = "valueCharType=${valueChar}")
	long updateOneChar(long id, Character valueChar);
	
	@BindSqlUpdate(where = "valueCharType=${valueCharType}")
	long updateOneCharType(long id, char valueCharType);
	
	@BindSqlUpdate(where = "valueCurrency=${valueCurrency}")
	long updateOneCurrency(long id, Currency valueCurrency);
	
	@BindSqlUpdate(where = "valueDate=${valueDate}")
	long updateOneDate(long id, Date valueDate);
	
	@BindSqlUpdate(where = "valueDouble=${valueDouble}")
	long updateOneDouble(long id, Double valueDouble);
	
	@BindSqlUpdate(where = "valueDoubleType=${valueDoubleType}")
	long updateOneDoubleType(long id, double valueDoubleType);
	
	@BindSqlUpdate(where = "valueEnumType=${valueEnumType}")
	long updateOneEnumType(long id, EnumType valueEnumType);
	
	@BindSqlUpdate(where = "valueFloat=${valueFloat}")
	long updateOneFloat(long id, Float valueFloat);
	
	@BindSqlUpdate(where = "valueFloatType=${valueFloatType}")
	long updateOneFloatType(long id, float valueFloatType);
	
	@BindSqlUpdate(where = "valueInt=${valueInt}")
	long updateOneInt(long id, Integer valueInt);
	
	@BindSqlUpdate(where = "valueIntType=${valueIntType}")
	long updateOneIntType(long id, int valueIntType);
	
	@BindSqlUpdate(where = "valueLongList=${valueLongList}")
	long updateOneListLong(long id, LinkedList<Long> valueLongList);
	
	@BindSqlUpdate(where = "valueLocale=${valueLocale}")
	long updateOneLocale(long id, Locale valueLocale);
	
	@BindSqlUpdate(where = "valueLong=${valueLong}")
	long updateOneLong(long id, Long valueLong);
	
	@BindSqlUpdate(where = "valueLongType=${valueLongType}")
	long updateOneLongType(long id, long valueLongType);
	
	@BindSqlUpdate(where = "valueShort=${valueShort}")
	long updateOneShort(long id, Short valueShort);
	
	@BindSqlUpdate(where = "valueShortType=${valueShortType}")
	long updateOneShortType(long id, short valueShortType);
	
	@BindSqlUpdate(where = "valueString=${valueString}")
	long updateOneString(long id, Double valueString);
	
	@BindSqlUpdate(where = "valueTime=${valueTime}")
	long updateOneTime(long id, Time valueTime);
	
	@BindSqlUpdate(where = "valueTimeZone=${valueTimeZone}")
	long updateOneTimeZone(long id, TimeZone valueTimeZone);
	
	@BindSqlUpdate(where = "valueUrl=${valueUrl}")
	long updateOneURL(long id, URL valueUrl);
			
}
/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Bean64.
 */
@BindType
@BindSharedPreferences
public class Bean64 {
	
	/** The value bool type. */
	public boolean valueBoolType;
	
	/** The value bool. */
	public Boolean valueBool;

	/** The value byte type. */
	public byte valueByteType;
	
	/** The value byte. */
	public Byte valueByte;
	
	/** The value short type. */
	public short valueShortType;
	
	/** The value short. */
	public Short valueShort;

	/** The value int type. */
	public int valueIntType;
	
	/** The value int. */
	public Integer valueInt;
	
	/** The value string. */
	public String valueString;
	
	/** The value char type. */
	public char valueCharType;
	
	/** The value char. */
	public Character valueChar;
	
	/** The value float type. */
	public float valueFloatType;
	
	/** The value float. */
	public Float valueFloat;
		
	/** The value big integer. */
	public BigInteger valueBigInteger;
	
	/** The value big decimal. */
	public BigDecimal valueBigDecimal;
	
	/** The value enum type. */
	public EnumType valueEnumType;
		
	/** The value long type. */
	public long valueLongType;
	
	/** The value long. */
	public Long valueLong;
	
	/** The value double type. */
	public double valueDoubleType;
	
	/** The value double. */
	public Double valueDouble;
	
	/** The value locale. */
	public Locale valueLocale;
	
	/** The value calendar. */
	public Calendar valueCalendar;
	
	/** The value date. */
	public Date valueDate;
	
	/** The value url. */
	public URL valueUrl;
	
	/** The value time. */
	public Time valueTime;
	
	/** The value currency. */
	public Currency valueCurrency;
	
	/** The value time zone. */
	public TimeZone valueTimeZone;
	
	/** The value time list. */
	public List<Time> valueTimeList;
	
	/** The value strin list. */
	public LinkedList<String> valueStrinList;
	
	/** The value long list. */
	public LinkedList<Long> valueLongList;
	
	/** The value byte array. */
	public byte[] valueByteArray;
	
	/** The value bean. */
	@BindSqlColumn(enabled=false)
	public Bean64 valueBean;
	
	/** The value long type array. */
	public long[] valueLongTypeArray;
	
	/** The value long array. */
	public Long[] valueLongArray;	
	
	/** The value bean array. */
	public Bean64[] valueBeanArray;
	
	/** The value string array. */
	public String[] valueStringArray;
	
	/** The value char list. */
	public LinkedList<Character> valueCharList;

	/** The value char type array. */
	public char[] valueCharTypeArray;

	/** The value char array. */
	public Character[] valueCharArray;
	
	/** The value map string bean. */
	public Map<String, Bean64> valueMapStringBean;
	
	/** The value linked map string bean. */
	public LinkedHashMap<String, Bean64> valueLinkedMapStringBean;
	
	/** The value set string. */
	public Set<String> valueSetString;
	
	/** The id. */
	@BindPreference(enabled=false)
	public long id;
}

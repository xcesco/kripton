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
package sqlite.kripton60;

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

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSharedPreferences
public class Bean {
	
	public boolean valueBoolType;
	
	public Boolean valueBool;

	public byte valueByteType;
	
	public Byte valueByte;
	
	public short valueShortType;
	
	public Short valueShort;

	public int valueIntType;
	
	public Integer valueInt;
	
	public String valueString;
	
	public char valueCharType;
	
	public Character valueChar;
	
	public float valueFloatType;
	
	public Float valueFloat;
		
	public BigInteger valueBigInteger;
	
	public BigDecimal valueBigDecimal;
	
	public EnumType valueEnumType;
		
	public long valueLongType;
	
	public Long valueLong;
	
	public double valueDoubleType;
	
	public Double valueDouble;
	
	public Locale valueLocale;
	
	public Calendar valueCalendar;
	
	public Date valueDate;
	
	public URL valueUrl;
	
	public Time valueTime;
	
	public Currency valueCurrency;
	
	public TimeZone valueTimeZone;
	
	@Bind(enabled=false)	
	public List<Time> valueTimeList;
	
	public LinkedList<String> valueStrinList;
	
	public LinkedList<Long> valueLongList;
	
	public byte[] valueByteArray;
	
	@BindColumn(enabled=false)
	public Bean valueBean;
	
	public long[] valueLongTypeArray;
	
	public Long[] valueLongArray;	
	
	public Bean[] valueBeanArray;
	
	public String[] valueStringArray;
	
	public LinkedList<Character> valueCharList;

	public char[] valueCharTypeArray;

	public Character[] valueCharArray;
	
	public Map<String, Bean> valueMapStringBean;
	
	public LinkedHashMap<String, Bean> valueLinkedMapStringBean;
	
	public Set<String> valueSetString;
	
	@BindPreference(enabled=false)
	public long id;
}

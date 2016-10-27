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
package com.abubusoft.kripton.processor.kripton60;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean {

	public long id;
	
	public BigDecimal valueBigDecimal;
	
	public BigInteger valueBigInteger;
	
	public boolean valueBoolType;
		
	//public Boolean valueBool;
	
	public byte valueByteType;
		
	//public Byte valueByte;
	
	/*
	
	public Calendar valueCalendar;
	
	public char valueCharType;
	
	public Character valueChar;
	
	public Currency valueCurrency;
	
	public Date valueDate;
	
	public double valueDoubleType;
	
	public Double valueDouble;
	
	public EnumType valueEnum;
	
	public float valueFloatType;
	
	public Float valueFloat;
	
	public int valueIntType;
	
	public Integer valueInt;
	
	public Locale valueLocale;
	
	public long valueLongType;
	
	public Long valueLong;
	
	public short valueShortType;
	
	public Short valueShort;
	
	public String valueString;
	
	public Time valueTime;
	
	public TimeZone valueTimeZone;
	
	public URL valueUrl;
	*/

}

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
package shared.feature.enumeration;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

/**
 * The Class App4Preferences.
 */
@BindSharedPreferences
public class App4Preferences {
	
	public ValueType value1=ValueType.VALUE_1;
	
	private ValueType value2=ValueType.VALUE_2;

	public ValueType getValue2() {
		return value2;
	}

	public void setValue2(ValueType value2) {
		this.value2 = value2;
	}
	
	public boolean valueBoolean;
	
	public short valueShort;
	
	public char valueChar;
	
	public int valueInt;
	
	public long valueLong;	
		
	public float valueFloat;
	
	public double valueDouble;
	
	public String valueString;
	
	public Boolean valueBoolean2;
	
	public Short valueShort2;
	
	public Character valueChar2;
	
	public Integer valueInt2;
	
	public Long valueLong2;	
		
	public Float valueFloat2;
	
	public Double valueDouble2;
	
	public URL valueUrl;
	
	public BigDecimal valueBigDecimal;
	
	public List<String> listString;
	
	public Set<String> setString;
	
	public Date valueDate;
	
	public Locale valueLocale;
	
	
	

}

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
package sqlite.kripton62;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Bean.
 */
@BindType
@BindSharedPreferences
public class Bean {
	
	/** The id. */
	public long id;
	
	/** The value. */
	public String value;
	
	/** The value byte set. */
	public Set<Byte> valueByteSet;
	
	/** The value short set. */
	public HashSet<Short> valueShortSet;
	
	/** The value integer set. */
	public LinkedHashSet<Integer> valueIntegerSet;
	
	/** The value string set. */
	public HashSet<String> valueStringSet;
	
	/** The value character set. */
	public Set<Character> valueCharacterSet;
	
	/** The value float set. */
	public HashSet<Float> valueFloatSet;
	
	/** The value double set. */
	public HashSet<Double> valueDoubleSet;
	
	/** The value big decimal set. */
	public HashSet<BigDecimal> valueBigDecimalSet;
	
	/** The value bean set. */
	public LinkedHashSet<Bean> valueBeanSet;
	
	/** The value enum type set. */
	public HashSet<EnumType> valueEnumTypeSet;
	
}

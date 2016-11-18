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
package kripton62;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSharedPreferences
public class Bean {
	
	public long id;
	
	public Set<Byte> valueByteSet;
	
	public HashSet<Short> valueShortSet;
	
	public LinkedHashSet<Integer> valueIntegerSet;
	
	public HashSet<String> valueStringSet;
	
	public Set<Character> valueCharacterSet;
	
	public HashSet<Float> valueFloatSet;
	
	public HashSet<Double> valueDoubleSet;
	
	public HashSet<BigDecimal> valueBigDecimalSet;
	
	public LinkedHashSet<Bean> valueBeanSet;
	
	public HashSet<EnumType> valueEnumTypeSet;
	
}

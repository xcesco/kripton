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
package bind.feature.typeAdapter.kripton87;

import com.abubusoft.kripton.TypeAdapter;

import bind.feature.typeAdapter.kripton87.Enum87A;

public class Enum87IntegerTypeAdapter implements TypeAdapter<Enum87A, Integer> {

	@Override
	public Enum87A toJava(Integer dataValue) {
		return Enum87A.values()[dataValue];
	}

	@Override
	public Integer toData(Enum87A javaValue) {
		
		return javaValue.ordinal();
	}

}

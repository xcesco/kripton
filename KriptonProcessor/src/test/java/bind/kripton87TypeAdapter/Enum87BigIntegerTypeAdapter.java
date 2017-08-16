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
package bind.kripton87TypeAdapter;

import java.math.BigDecimal;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class Enum87BigIntegerTypeAdapter implements BindTypeAdapter<Enum87A, BigDecimal> {

	@Override
	public Enum87A toJava(BigDecimal dataValue) throws Exception {
		return Enum87A.values()[dataValue.intValue()];
	}

	@Override
	public BigDecimal toData(Enum87A javaValue) throws Exception {
		return new BigDecimal(javaValue.ordinal());
	}

}

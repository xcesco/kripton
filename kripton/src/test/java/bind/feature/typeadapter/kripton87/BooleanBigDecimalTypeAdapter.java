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
package bind.feature.typeadapter.kripton87;

import java.math.BigDecimal;

import com.abubusoft.kripton.TypeAdapter;


/**
 * The Class BooleanBigDecimalTypeAdapter.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BooleanBigDecimalTypeAdapter implements TypeAdapter<Boolean, BigDecimal> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public Boolean toJava(BigDecimal dataValue) {
		return dataValue.longValue()>0;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public BigDecimal toData(Boolean javaValue) {
		if (javaValue==null) return null;
		
		return new BigDecimal(javaValue?1:0);
	}


}

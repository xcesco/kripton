/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.update.raw.err4;

import java.util.Date;
import com.abubusoft.kripton.android.SqlTypeAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class DateAdapter.
 */
public class DateAdapter implements SqlTypeAdapter<Date, Long> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public Date toJava(Long dataValue) {
		if (dataValue==null) return null;
		
		return new Date(dataValue);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public Long toData(Date javaValue) {
		if (javaValue!=null) return javaValue.getTime();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.SqlTypeAdapter#toString(java.lang.Object)
	 */
	@Override
	public String toString(Date javaValue) {
		if (javaValue==null) return null;
		
		return String.valueOf(toData(javaValue));
	}
	


}

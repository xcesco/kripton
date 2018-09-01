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
package sqlite.feature.typeadapter.kripton277.err1;

import java.sql.Date;

import com.abubusoft.kripton.android.SqlTypeAdapter;
import com.abubusoft.kripton.common.DateUtils;

/**
 * The Class DateAdapter.
 */
public class DateAdapter implements SqlTypeAdapter<Date, String> {


	@Override
	public Date toJava(String dataValue) {
		return new java.sql.Date(DateUtils.read(dataValue).getTime());
	}

	@Override
	public String toData(Date javaValue) {
		return DateUtils.writeShort(javaValue);
	}

	@Override
	public String toString(Date javaValue) {
		return DateUtils.writeShort(javaValue);
	}


}

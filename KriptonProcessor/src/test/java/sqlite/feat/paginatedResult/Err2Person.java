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
package sqlite.feat.paginatedResult;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable
public class Err2Person {
	public long id;

	@BindColumn(columnType = ColumnType.INDEXED)
	public String name;

	public String surname;
	public String birthCity;
	public Date birthDay;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", ");
		if (name != null) {
			builder.append("typeName=");
			builder.append(name);
			builder.append(", ");
		}
		if (surname != null) {
			builder.append("surname=");
			builder.append(surname);
			builder.append(", ");
		}
		if (birthCity != null) {
			builder.append("birthCity=");
			builder.append(birthCity);
			builder.append(", ");
		}
		if (birthDay != null) {
			builder.append("birthDay=");
			builder.append(birthDay);
		}
		builder.append("]");
		return builder.toString();
	}

}
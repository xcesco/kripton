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
package sqlite.feature.paginatedresult.error4;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Err4Person.
 */
@BindType
@BindTable
public class Err4Person {
	
	/** The id. */
	public long id;

	/** The name. */
	@BindSqlColumn(columnType = ColumnType.INDEXED)
	public String name;

	/** The surname. */
	public String surname;
	
	/** The birth city. */
	public String birthCity;
	
	/** The birth day. */
	public Date birthDay;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
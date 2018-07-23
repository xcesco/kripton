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
package sqlite.feature.immutable.pagedresult;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

/**
 * The Class Person.
 */
@BindSqlType
public class Person {
	
	/**
	 * @param pk
	 * @param name
	 * @param surname
	 * @param birthCity
	 * @param birthDay
	 */
	public Person(String pk, String name, String surname, String birthCity, Date birthDay) {
		super();
		this.pk = pk;
		this.name = name;
		this.surname = surname;
		this.birthCity = birthCity;
		this.birthDay = birthDay;
	}

	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	private String pk;

	public String getPk() {
		return pk;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	/** The name. */
	@BindSqlColumn(columnType = ColumnType.INDEXED)
	private String name;

	/** The surname. */
	private String surname;
	
	/** The birth city. */
	private String birthCity;
	
	/** The birth day. */
	private Date birthDay;


}
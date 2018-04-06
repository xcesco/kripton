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
package sqlite.feature.contentprovider.kripton35.entities;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
@BindType

public class Person {

	/** The id. */
	public long id;

	/** The parent id. */
	@BindColumn(value = "alias_parent_id", foreignKey = Person.class, nullable = true)
	public long parentId;

	/** The city. */
	@BindColumn(foreignKey = City.class)
	public long city;

	/** The birth city. */
	public String birthCity;

	/** The birth day. */
	public Date birthDay;

	/** The value. */
	public long value;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/** The name. */
	protected String name;

	/** The surname. */
	protected String surname;
}

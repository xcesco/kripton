/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Contact.
 */
@BindType
public class Contact {

	/** The id. */
	protected long id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** The surname. */
	public String surname;

	/** The birth day. */
	@BindSqlAdapter(adapter = DateAdapterType.class)
	public Date birthDay;

	/** The password. */
	@BindSqlAdapter(adapter = PasswordAdapterType.class)
	protected String password;

	/** The type. */
	@BindSqlAdapter(adapter = EnumAdapterType.class)
	public ContactType type;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** The update date. */
	public java.sql.Date updateDate;

	/** The update time. */
	public java.sql.Time updateTime;

	// @BindSqlAdapter(adapter = AdapterType.class, dataType = Long.class)
	// public byte[] password;
}

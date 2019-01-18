/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.typeadapter.update.err1;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.ContactType;
import sqlite.feature.typeadapter.DateAdapterType;
import sqlite.feature.typeadapter.EnumAdapterType;
import sqlite.feature.typeadapter.PasswordAdapterType;

// TODO: Auto-generated Javadoc
/**
 * The Interface ErrContactDao.
 */
@BindDao(Contact.class)
public interface ErrContactDao {
	
	/**
	 * Update JQL raw 1.
	 *
	 * @param password the password
	 * @param birthDay the birth day
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw1(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=DateAdapterType.class) Date birthDay, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
}

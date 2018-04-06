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
package sqlite.feature.typeadapter.insert.err1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;

import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.ContactType;
import sqlite.feature.typeadapter.PasswordAdapterType;

// TODO: Auto-generated Javadoc
/**
 * The Interface ErrContactDao.
 */
@BindDao(Contact.class)
public interface ErrContactDao {
	
	/**
	 * Insert compact raw.
	 *
	 * @param password the password
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlInsert
	long insertCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, ContactType type, long id);
	
}

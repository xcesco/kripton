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
package sqlite.feature.many2many.case6.persistence;


import java.sql.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.case6.model.Person;
import sqlite.feature.many2many.case6.model.PersonPhone;
import sqlite.feature.many2many.case6.model.PhoneNumber;


/**
 * Created by xcesco on 10/10/2017.
 */
@BindDao(PersonPhone.class)
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class )
public interface Person2PhoneDao {
	
	/**
	 * Select by date.
	 *
	 * @param since the since
	 * @return the list
	 */
	@BindSqlSelect(where="buyDate>${since}")
	List<PersonPhone> selectByDate(Date since);
}

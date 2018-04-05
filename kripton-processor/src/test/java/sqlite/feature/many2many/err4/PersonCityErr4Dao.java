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
package sqlite.feature.many2many.err4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;


// TODO: Auto-generated Javadoc
/**
 * The Class PersonCityErr4Dao.
 */
@BindDao(PersonCityErr4.class)
@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public class PersonCityErr4Dao {
	
	/**
	 * Select by id.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where="id=${id}")
	List<PersonCityErr4> selectById(long id) {
		return null;
	}
	
}

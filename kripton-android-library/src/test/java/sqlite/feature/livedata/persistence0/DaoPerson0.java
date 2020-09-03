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
package sqlite.feature.livedata.persistence0;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import androidx.lifecycle.LiveData;
import sqlite.feature.livedata.data.Person;

/**
 * The Interface DaoPerson0.
 */
@BindDao(Person.class)
public interface DaoPerson0 {

	/**
	 * Select.
	 *
	 * @param name the name
	 * @return the live data
	 */
	@BindSqlSelect(where="name=${name}")
	//List<Person> select(String name);
	LiveData<List<Person>> select(String name);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert
	void insert(Person bean);
	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(where ="id=${bean.id}")
	void update(Person bean);
	
}

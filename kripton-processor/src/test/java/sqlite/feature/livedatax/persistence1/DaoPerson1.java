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
package sqlite.feature.livedatax.persistence1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import androidx.lifecycle.LiveData;
import sqlite.feature.livedata.data.Person;

/**
 * The Interface DaoPerson1.
 */
@BindContentProviderPath(path="persons")
@BindDao(Person.class)
public interface DaoPerson1 {

	/**
	 * Select.
	 *
	 * @param name the name
	 * @return the live data
	 */
	@BindContentProviderEntry(path="${name}")
	@BindSqlSelect(where="name=${name}")
	LiveData<List<Person>> select(String name);
	
	/**
	 * Select all.
	 *
	 * @return the live data
	 */
	@BindContentProviderEntry
	@BindSqlSelect
	LiveData<List<Person>> selectAll();
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 */
	@BindContentProviderEntry
	@BindSqlInsert
	void insert(Person bean);
	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	@BindContentProviderEntry(path="${bean.id}")
	@BindSqlUpdate(where ="id=${bean.id}")
	void update(Person bean);
	
}

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
package sqlite.feature.dynamic.select1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.dynamic.Person;

/**
 * The Interface PersonDAO2.
 */
@BindDao(Person.class)
public interface PersonDAO2 {

	/**
	 * Select.
	 *
	 * @param id the id
	 * @param where the where
	 * @return the list
	 */
	@BindSqlSelect(jql="select * from person where id=:id #{DYNAMIC_WHERE}")
	List<Person> select(long id, @BindSqlDynamicWhere String where);

}
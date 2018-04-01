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
package sqlite.feature.javadoc.select.raw;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDao {

	/**
	 * Update RAW with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry
	@BindSqlSelect
	List<Person> selectAllBeans();
	
	@BindContentProviderEntry(path="jql/${name}/${surname}")
	@BindSqlSelect(jql="select * FROM Person WHERE name=${name} and surname=${surname} and student = 0")
	List<Person> selectAllBeansJQL(String name, String surname);
	
	/**
	 * JQL SELECT-FROM-SELECT can not be used as content provider method.
	 * @param bean
	 */
	@BindSqlSelect(jql="select * FROM Person WHERE name=${name} and surname=${surname} and student = (select student from Person where id=${student})")
	List<Person> selectFromSelectAllBeansJQL(String name, String surname, boolean student);
	
	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="${id}")
	@BindSqlSelect(where="id=${id}")
	List<Person> selectBean(long id);
	
	@BindContentProviderEntry(path="${id}/more")
	@BindSqlSelect(where="id=${id}")
	List<Person> selectBeanDynamic(long id, @BindSqlDynamicWhere String where);
	
	@BindContentProviderEntry(path="${id}/moreAndMore")
	@BindSqlSelect(where="id=${id}")
	List<Person> selectBeanDynamicWithArgs(long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);
	
}

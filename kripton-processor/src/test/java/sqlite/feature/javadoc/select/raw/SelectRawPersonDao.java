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
package sqlite.feature.javadoc.select.raw;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry.MultiplicityResultType;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.javadoc.Person;


/**
 * The Interface SelectRawPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface SelectRawPersonDao {

	/**
	 * select BEAN with no parameters.
	 *
	 * @return the list
	 */
	@BindContentProviderEntry
	@BindSqlSelect
	List<Person> selectAllBeans();
	
	/**
	 * select BEAN with no parameters.
	 *
	 * @return the int
	 */
	@BindContentProviderEntry(path="a")
	@BindSqlSelect(fields="count(*)")
	int selectAllBeansCount();
	
	/**
	 * select BEAN with one parameter.
	 *
	 * @param id the id
	 * @return the person
	 */
	@BindContentProviderEntry(path="${id}", multiplicityResult=MultiplicityResultType.ONE)
	@BindSqlSelect(where="id=${id}")
	Person selectOneBean(@BindSqlParam("id") long id);
	
	/**
	 * select BEAN with one parameter and dynamic where.
	 *
	 * @param id the id
	 * @param where the where
	 * @return the person
	 */
	@BindContentProviderEntry(path="dynamic/${id}")
	@BindSqlSelect(fields="personName",where="id=${id}")
	Person selectOneBeanWithDynamic(long id, @BindSqlDynamicWhere String where);
	
	/**
	 * select BEAN with one parameter and dynamic where and args.
	 *
	 * @param id the id
	 * @param name the name
	 * @param where the where
	 * @param args the args
	 * @return the person
	 */
	@BindContentProviderEntry(path="dynamicandArgs/${id}/${name}")
	@BindSqlSelect(where="id=${id} and personName=${name}")
	Person selectOneBeanWithDynamicAndArgs(long id, String name, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);
	
	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param id the id
	 * @param order the order
	 * @return the person
	 */
	@BindContentProviderEntry(path="dynamicOrder/${id}")
	@BindSqlSelect(where="id=${id}")
	Person selectOneBeanWithDynamicOrder(long id, @BindSqlDynamicOrderBy String order);
	
	
	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param surname the surname
	 * @param order the order
	 * @param listener the listener
	 */
	@BindContentProviderEntry(path="dynamicOrderAndLis/${surname}")
	@BindSqlSelect(where="personSurname=${surname}")
	void selectOneBeanWithDynamicOrderAndListener(String surname, @BindSqlDynamicOrderBy String order, OnReadBeanListener<Person> listener);
	
	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param id the id
	 * @return the person
	 */
	@BindContentProviderEntry(path="jql/${id}")
	@BindSqlSelect(jql="select * from Person where id=${id}")
	Person selectWithJQL(long id);
	
}

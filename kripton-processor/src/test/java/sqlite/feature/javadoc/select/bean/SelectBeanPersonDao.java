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
package sqlite.feature.javadoc.select.bean;

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

// TODO: Auto-generated Javadoc
/**
 * The Interface SelectBeanPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface SelectBeanPersonDao {

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
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "a/${love.id}")
	@BindSqlSelect(fields = "count(*)", where = " id=${love.id}")
	int selectAllBeansCount(@BindSqlParam("love") Person bean);

	/**
	 * select BEAN with one parameter.
	 *
	 * @param benza the benza
	 * @return the person
	 */
	@BindContentProviderEntry(path = "${bean.id}", multiplicityResult = MultiplicityResultType.ONE)
	@BindSqlSelect(where = "id=${bean.id}")
	Person selectOneBean(@BindSqlParam("bean") Person benza);

	/**
	 * select BEAN with one parameter and dynamic where.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @return the person
	 */
	@BindContentProviderEntry(path = "dynamic/${bean.id}")
	@BindSqlSelect(fields = "personname", where = "id=${bean.id}")
	Person selectOneBeanWithDynamic(Person bean, @BindSqlDynamicWhere String where);

	/**
	 * select BEAN with one parameter and dynamic where and args.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @param args the args
	 * @return the person
	 */
	@BindContentProviderEntry(path = "dynamicandArgs/${bean.id}")
	@BindSqlSelect(where = "id=${bean.id}")
	Person selectOneBeanWithDynamicAndArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param bean the bean
	 * @param order the order
	 * @return the person
	 */
	@BindContentProviderEntry(path = "dynamicOrder/${bean.id}")
	@BindSqlSelect(where = "id=${bean.id}")
	Person selectOneBeanWithDynamicOrder(Person bean, @BindSqlDynamicOrderBy String order);

	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param bean the bean
	 * @param order the order
	 * @param listener the listener
	 */
	@BindContentProviderEntry(path = "dynamicOrderAndLis/${bean.id}")
	@BindSqlSelect(where = "id=${bean.id}")
	void selectOneBeanWithDynamicOrderAndListener(Person bean, @BindSqlDynamicOrderBy String order, OnReadBeanListener<Person> listener);

	/**
	 * select BEAN with one parameter and dynamic order.
	 *
	 * @param bean the bean
	 * @return the person
	 */
	@BindContentProviderEntry(path = "jql/${bean.id}")
	@BindSqlSelect(jql = "select * from Person where id=${bean.id}")
	Person selectWithJQL(Person bean);

	/**
	 * select BEAN with JQL and inner SELECT.
	 *
	 * @param bean the bean
	 * @return the person
	 */
	@BindContentProviderEntry(path = "jqlAndInnserSQL/${bean.id}")
	@BindSqlSelect(jql = "select * from Person where id=${bean.id} and id in (select id from Person)")
	Person selectWithJQLAndInnerSQL(Person bean);

}

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
package sqlite.feature.javadoc.update.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.javadoc.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface UpdateBeanPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface UpdateBeanPersonDao {

	/**
	 * Update BEAN with no parameters.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry
	@BindSqlUpdate
	int updateAllBeans(Person bean);

	/**
	 * Update BEAN with one parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBean(Person bean);

	/**
	 * Update BEAN with one parameter and dynamic where.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @return the int
	 */
	@BindContentProviderEntry(path = "dynamic/${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBeanWithDynamic(Person bean, @BindSqlDynamicWhere String where);

	//
	/**
	 * Update BEAN with one parameter and dynamic where.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "dynamicArgs/${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBeanWithDynamicAndArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String args[]);

	/**
	 * Update all beans JQL.
	 *
	 * @param bean the bean
	 */
	@BindContentProviderEntry(path = "jql")
	@BindSqlUpdate(jql = "UPDATE Person SET personSurname=${bean.personSurname}, student = ${bean.student}")
	void updateAllBeansJQL(Person bean);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(jql = "UPDATE Person SET PersonName=${bean.personName}, student = (select student from Person where id=0)")
	void updateFromSelectAllBeansJQL(Person bean);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "jql/one/b/${bean.id}")
	@BindSqlUpdate(jql = "UPDATE Person SET personName=${bean.personname} where student = (select student from Person where id=${bean.id})")
	int updateFromSelectJQL(Person bean);

	/**
	 * Update bean dynamic.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${bean.id}/more")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateBeanDynamic(Person bean, @BindSqlDynamicWhere String where);

	/**
	 * Update bean dynamic with args.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${bean.id}/moreAndMore")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}

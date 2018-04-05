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
package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;

import sqlite.feature.javadoc.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface DeleteBeanPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface DeleteBeanPersonDao {

	/**
	 * delete BEAN with parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteOneBean(Person bean);

	/**
	 * delete BEAN with some parameters.
	 *
	 * @param bean the bean
	 */
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personName=${bean.personName} AND personSurname=${bean.personSurname} AND student = 0")
	void deleteAllBeansJQL(Person bean);

	/**
	 * JQL DELETE-FROM-SELECT can be used as content provider method. The
	 * important thing is params.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "a/${bean.personSurname}/${bean.personName}")
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personSurname=${bean.personSurname} and student = (select student from Person where personName=${bean.personName})")
	int deleteFromSelectAllBeansJQL(Person bean);

	/**
	 * Update BEAN with one parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "single/${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBean(Person bean);

	/**
	 * Delete bean dynamic.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @return the int
	 */
	@BindContentProviderEntry(path = "single2/${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBeanDynamic(Person bean, @BindSqlDynamicWhere String where);

	/**
	 * Delete bean dynamic with args.
	 *
	 * @param bean the bean
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${bean.id}/moreAndMore")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}

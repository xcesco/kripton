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
package sqlite.feature.javadoc.delete.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;

import sqlite.feature.javadoc.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface DeleteRawPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface DeleteRawPersonDao {

	/**
	 * delete BEAN with parameter.
	 *
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlDelete(where = "id=${id}")
	int deleteOneBean(long id);
	
	/**
	 * Delete one bean.
	 *
	 * @param surname the surname
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${surname}")
	@BindSqlDelete(where = "personSurname=${surname}")
	int deleteOneBean(String surname);

	/**
	 * delete BEAN with some parameters.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @return true, if successful
	 */
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personName=${name} and personSurname=${surname} AND student = 0")
	boolean deleteAllBeansJQL(String name, String surname);

	/**
	 * JQL DELETE-FROM-SELECT can be used as content provider method. The
	 * important thing is params.
	 *
	 * @param name the name
	 * @param surname the surname
	 */
	@BindContentProviderEntry(path = "a/${surname}/${name}")
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personSurname=${surname} and student = (select student from Person where personName=${name})")
	void deleteFromSelectAllBeansJQL(String name, String surname);

	/**
	 * Update BEAN with one parameter.
	 *
	 * @param id the id
	 * @return the long
	 */
	@BindContentProviderEntry(path = "single/${id}")
	@BindSqlDelete(where = "id=${id}")
	long deleteRaw(long id);

	/**
	 * Delete raw dynamic.
	 *
	 * @param id the id
	 * @param where the where
	 * @return the int
	 */
	@BindContentProviderEntry(path = "single2/${id}")
	@BindSqlDelete(where = "id=${id}")
	int deleteRawDynamic(long id, @BindSqlDynamicWhere String where);

	/**
	 * Delete bean dynamic with args.
	 *
	 * @param id the id
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}/moreAndMore")
	@BindSqlDelete(where = "id=${id}")
	int deleteBeanDynamicWithArgs(long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}

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
package sqlite.feature.javadoc.update.raw.error1;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.javadoc.Person;


/**
 * The Interface UpdateRawPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface UpdateRawPersonDao {

	/**
	 * Update RAW with no parameters.
	 *
	 * @param personName the person name
	 * @return the int
	 */
	@BindSqlUpdate
	int updateAllBeans();

	/**
	 * Update all beans JQL.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param student the student
	 */
	@BindContentProviderEntry(path = "jql/${personSurname}")
	@BindSqlUpdate(jql = "UPDATE perSon SET student = ${student}, PersonnAme=${personName}  where personSurname=${personSurname}")
	void updateAllBeansJQL(@BindSqlParam("personName") String name, @BindSqlParam("personSurname") String surname, boolean student);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 *
	 * @param surname the surname
	 * @param name the name
	 */
	@BindSqlUpdate(jql = "UPDATE Person SET Personname=${personName}, student = (select student from Person where personSurname=${surname})")
	void updateFromSelectAllBeansJQL(String surname, @BindSqlParam("personName") String name);

	/**
	 * this JQL UPDATE-FROM-SELECT can be used as content provider method.
	 *
	 * @param personName the person name
	 * @param surname the surname
	 */
	@BindContentProviderEntry(path = "jql/all/${surname}")
	@BindSqlUpdate(jql = "UPDATE Person SET personname=${personName} where student= (select student from Person where personsurname=${surname})")
	void updateFromSelectJQL(String personName, String surname);

	/**
	 * Update BEAN with one parameter.
	 *
	 * @param id the id
	 * @param personName the person name
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateBean(long id, String personName);

	/**
	 * Update bean dynamic.
	 *
	 * @param where the where
	 * @param personName the person name
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}/more")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamic(@BindSqlDynamicWhere String where, String personName, long id);

	/**
	 * Update bean dynamic with args.
	 *
	 * @param personName the person name
	 * @param id the id
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}/moreAndMore")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamicWithArgs(String personName, long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}

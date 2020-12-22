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
package sqlite.feature.jql.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.jql.entities.Child;



/**
 * The Interface DaoChild.
 */
@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

	/**
	 * Select by parent.
	 *
	 * @param parentId the parent id
	 * @return the list
	 */
	@BindSqlSelect(jql = "select * from Child where parentId in (select id from Person where id=${parentId})")
	public List<Child> selectByParent(long parentId);
	
	/**
	 * Select by parent 2.
	 *
	 * @param parentId the parent id
	 * @return the int
	 */
	@BindSqlSelect(jql = "select count(*) from Child where parentId in (select id from Person where id=${parentId})")
	public int selectByParent2(long parentId);
	
	/**
	 * Select by parent id.
	 *
	 * @param parentId the parent id
	 * @return the list
	 */
	@BindSqlSelect(where="parentId=${parentId}")
	public List<Child> selectByParentId(long parentId);

	// @BindSqlSelect(where = "id=${id}")
	// public List<Child> selectAll(long id);
	
	/**
	 * Insert by copy.
	 *
	 * @param parentId the parent id
	 * @param aliasParentId the alias parent id
	 * @param parent the parent
	 */
	@BindSqlInsert(jql="insert into Child (name, parentId) select name, parentId from Child where id=${parentId} or id=${test} or id=${aliasParentId}")
	public void insertByCopy(long parentId, long aliasParentId,@BindSqlParam("test") long parent);
	
	/**
	 * Insert by copy 3.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(jql="insert into Child (name, parentId) values (${bean.name}, ${bean.parentId})")
	public void insertByCopy3(Child bean);
	
	/**
	 * Insert by copy.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @return the int
	 */
	@BindSqlInsert
	public int insertByCopy(long parentId, String name);
	
	/**
	 * Update JQL.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 */
	@BindSqlUpdate(jql="update or replace Child set name=${name} where parentId=${a}")
	public void updateJQL(@BindSqlParam("a") long parentId, String name);
	
	/**
	 * Update JQL 2.
	 *
	 * @param parentId the parent id
	 */
	@BindSqlUpdate(jql="update or replace Child set parentId=${parentId}, name=(select id from Person where id=${parentId} )  where parentId=${parentId}")
	public void updateJQL2(long parentId);

}

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
package sqlite.feature.typeadapter;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactDao.
 */
@BindDao(Contact.class)
public interface ContactDao {
	
	/**
	 * Select by surname with adapter.
	 *
	 * @param dummy the dummy
	 * @return the list
	 */
	// -- SELECT MIXED
	@BindSqlSelect(where="surname=${dummyTest}")
	List<Contact> selectBySurnameWithAdapter(@BindSqlParam(value="dummyTest", adapter=PasswordAdapterType.class) String dummy);
	
	/**
	 * Select by surname.
	 *
	 * @param dummy the dummy
	 * @return the list
	 */
	@BindSqlSelect(where="surname=${dummy}")
	List<Contact> selectBySurname(String dummy);

	/**
	 * Delete compact bean.
	 *
	 * @param bean the bean
	 */
	//-- DELETE	
	@BindSqlDelete(where = "id=${bean.id} and type=${bean.type}")
	void deleteCompactBean(Contact bean);
	
	/**
	 * Delete compact raw.
	 *
	 * @param password the password
	 * @param type the type
	 */
	@BindSqlDelete(where="password=${password} and type=${type}")
	void deleteCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
		
	/**
	 * Delete JQL bean.
	 *
	 * @param bean the bean
	 */
	@BindSqlDelete(jql="DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}")
	void deleteJQLBean(Contact bean);
	
	/**
	 * Delete JQL raw.
	 *
	 * @param id the id
	 * @param type the type
	 * @return the long
	 */
	@BindSqlDelete(jql="DELETE FROM contact WHERE id=${id} and type=${type}")
	long deleteJQLRaw(long id, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);

	/**
	 * Select compact bean.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	//-- SELECT	
	@BindSqlSelect(where = "id=${bean.id}  and type=${bean.type}")
	List<Contact> selectCompactBean(Contact bean);
	
	/**
	 * Select JQL bean listener.
	 *
	 * @param bean the bean
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id=${bean.id} and password=${bean.password} and type=${bean.type}")
	void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener);
	
	/**
	 * Selec JQL bean.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	@BindSqlSelect(jql="SELECT birthDay, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}")
	List<Contact> selecJQLBean(Contact bean);
	
	/**
	 * Select JQL raw.
	 *
	 * @param password the password
	 * @param type the type
	 * @return the list
	 */
	@BindSqlSelect(jql="SELECT * FROM contact WHERE password=${password} and type=${type}")
	List<Contact> selectJQLRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
	
	/**
	 * Select compact raw.
	 *
	 * @param password the password
	 * @param type the type
	 * @return the list
	 */
	@BindSqlSelect(where="password=${password} and type=${type}")
	List<Contact> selectCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);

	/**
	 * Update compact bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	//--- UPDATE
	@BindSqlUpdate(fields={"id", "type"}, where = "id=${bean.id}  and password=${bean.password} and type=${bean.type}")
	long updateCompactBean(Contact bean);
	
	/**
	 * Update compact raw 1.
	 *
	 * @param password the password
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id}")
	long updateCompactRaw1(String password, ContactType type, long id);	
	
	/**
	 * Update compact raw 2.
	 *
	 * @param birthDay the birth day
	 * @param password the password
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlUpdate(where = "password=${password} and type=${type}")
	long updateCompactRaw2(Date birthDay, @BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	/**
	 * Update JQL bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(jql = "UPDATE contact SET birthDay=${bean.birthDay}, password=${bean.password}, type=${bean.type} WHERE type=${bean.type}  and type=${bean.password}")	
	long updateJQLBean(Contact bean);
	
	/**
	 * Update JQL raw.
	 *
	 * @param password the password
	 * @param birthDay the birth day
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, Date birthDay, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	/**
	 * Insert compact raw.
	 *
	 * @param password the password
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	//-- INSERT
	@BindSqlInsert
	long insertCompactRaw(String password, ContactType type, long id);

	/**
	 * Insert compact bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert(fields={"id", "type"}, includePrimaryKey=true)
	long insertCompactBean(Contact bean);
	
	/**
	 * Insert JQL bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert(jql = "INSERT INTO contact (password, type, id) VALUES (${bean.password}, ${bean.type}, ${bean.id})")	
	long insertJQLBean(Contact bean);

	/**
	 * Insert JQL raw.
	 *
	 * @param password the password
	 * @param birthDay the birth day
	 * @param type the type
	 * @param id the id
	 * @return the long
	 */
	@BindSqlInsert(jql = "INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})")	
	long insertJQLRaw(String password, Date birthDay, ContactType type, long id);


}

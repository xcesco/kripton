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
package sqlite.feature.typeadapter.kripton180.bean;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.kripton180.Employee;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeBeanDao.
 */
@BindDao(Employee.class)
public interface EmployeeBeanDao {

	/**
	 * Select by id.
	 *
	 * @param bean the bean
	 * @return the employee
	 */
	@BindSqlSelect(where = "id=${bean.id}")
	Employee selectById(Employee bean);

	/**
	 * Select by id JQL.
	 *
	 * @param bean the bean
	 * @return the employee
	 */
	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${bean.id}")
	Employee selectByIdJQL(Employee bean);

	/**
	 * Select by all.
	 *
	 * @param bean the bean
	 * @return the employee
	 */
	@BindSqlSelect(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	Employee selectByAll(Employee bean);

	/**
	 * Select by all JQL.
	 *
	 * @param bean the bean
	 * @return the employee
	 */
	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	Employee selectByAllJQL(Employee bean);

	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert
	long insert(Employee bean);

	/**
	 * Insert JQL.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert(jql = "INSERT INTO Employee (fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray) VALUES (${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})")
	long insertJQL(Employee bean);

	/**
	 * Update.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long update(Employee bean);

	/**
	 * Update by id.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${bean.id}")
	long updateById(Employee bean);

	/**
	 * Update JQL.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long updateJQL(Employee bean);

	/**
	 * Update by id JQL.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id}")
	long updateByIdJQL(Employee bean);

	/**
	 * Delete.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlDelete(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long delete(Employee bean);

	/**
	 * Delete JQL.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlDelete(jql = "DELETE FROM employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long deleteJQL(Employee bean);

	/**
	 * Delete by id.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlDelete(where = "id=${bean.id}")
	long deleteById(Employee bean);

}

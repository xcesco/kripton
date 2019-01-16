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
package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

import sqlite.feature.typeadapter.kripton180.Employee;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeBeanInsertSelectDao.
 */
@BindDao(Employee.class)
public interface EmployeeBeanInsertSelectDao {

	/**
	 * Insert JQL.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(jql = "INSERT INTO Employee (fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray) select fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray  from Employee where fieldBoolean=${bean.fieldBoolean} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	void insertJQL(Employee bean);

}

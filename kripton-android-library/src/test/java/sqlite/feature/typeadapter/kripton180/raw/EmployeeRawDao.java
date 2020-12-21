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
package sqlite.feature.typeadapter.kripton180.raw;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;


/**
 * The Interface EmployeeRawDao.
 */
@BindDao(Employee.class)
public interface EmployeeRawDao {

	/**
	 * Select by id.
	 *
	 * @param id the id
	 * @return the employee
	 */
	@BindSqlSelect(where = "id=${id}")
	Employee selectById(long id);

	/**
	 * Select by id JQL.
	 *
	 * @param id the id
	 * @return the employee
	 */
	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id}")
	Employee selectByIdJQL(long id);

	/**
	 * Select by all with adapter.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the employee
	 */
	@BindSqlSelect(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllWithAdapter(long id,String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Select by all.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the employee
	 */
	@BindSqlSelect(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAll(long id,@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, @BindSqlParam(adapter=TypeAdapterByte.class) String fieldByte,@BindSqlParam(adapter=TypeAdapterChar.class)  String fieldCharacter, @BindSqlParam(adapter=TypeAdapterShort.class) String fieldShort, @BindSqlParam(adapter=TypeAdapterInteger.class) String fieldInteger, @BindSqlParam(adapter=TypeAdapterLong.class) String fieldLong, @BindSqlParam(adapter=TypeAdapterFloat.class) String fieldFloat, @BindSqlParam(adapter=TypeAdapterDouble.class) String fieldDouble, @BindSqlParam(adapter=TypeAdapterString.class) String fieldString, @BindSqlParam(adapter=TypeAdapterByteArray.class) String fieldByteArray);

	/**
	 * Select by all JQL.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the employee
	 */
	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllJQL(long id,String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);

	/**
	 * Select by all JQL with adapter.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the employee
	 */
	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllJQLWithAdapter(long id,@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, @BindSqlParam(adapter=TypeAdapterByte.class) String fieldByte,@BindSqlParam(adapter=TypeAdapterChar.class)  String fieldCharacter, @BindSqlParam(adapter=TypeAdapterShort.class) String fieldShort, @BindSqlParam(adapter=TypeAdapterInteger.class) String fieldInteger, @BindSqlParam(adapter=TypeAdapterLong.class) String fieldLong, @BindSqlParam(adapter=TypeAdapterFloat.class) String fieldFloat, @BindSqlParam(adapter=TypeAdapterDouble.class) String fieldDouble, @BindSqlParam(adapter=TypeAdapterString.class) String fieldString, @BindSqlParam(adapter=TypeAdapterByteArray.class) String fieldByteArray);

	/**
	 * Insert.
	 *
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlInsert
	long insert(String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Insert with adapter.
	 *
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlInsert
	long insertWithAdapter(String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id}")
	long updateById(long id, String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Update.
	 *
	 * @param firstName the first name
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	long update(String firstName, long id, String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlDelete(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	long delete(long id, String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Delete JQL.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlDelete(jql = "DELETE FROM employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	long deleteJQL(long id, String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	/**
	 * Delete JQL with adapter.
	 *
	 * @param id the id
	 * @param fieldBoolean the field boolean
	 * @param fieldByte the field byte
	 * @param fieldCharacter the field character
	 * @param fieldShort the field short
	 * @param fieldInteger the field integer
	 * @param fieldLong the field long
	 * @param fieldFloat the field float
	 * @param fieldDouble the field double
	 * @param fieldString the field string
	 * @param fieldByteArray the field byte array
	 * @return the long
	 */
	@BindSqlDelete(jql = "DELETE FROM employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	long deleteJQLWithAdapter(long id,@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, @BindSqlParam(adapter=TypeAdapterByte.class) String fieldByte,@BindSqlParam(adapter=TypeAdapterChar.class)  String fieldCharacter, @BindSqlParam(adapter=TypeAdapterShort.class) String fieldShort, @BindSqlParam(adapter=TypeAdapterInteger.class) String fieldInteger, @BindSqlParam(adapter=TypeAdapterLong.class) String fieldLong, @BindSqlParam(adapter=TypeAdapterFloat.class) String fieldFloat, @BindSqlParam(adapter=TypeAdapterDouble.class) String fieldDouble, @BindSqlParam(adapter=TypeAdapterString.class) String fieldString, @BindSqlParam(adapter=TypeAdapterByteArray.class) String fieldByteArray);

	/*

	@BindSqlUpdate(where = "id=${bean.id}")
	long updateById(Employee bean);

	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long updateJQL(Employee bean);

	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id}")
	long updateByIdJQL(Employee bean);

	@BindSqlDelete(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long delete(Employee bean);

	@BindSqlDelete(jql = "DELETE FROM employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long deleteJQL(Employee bean);

	@BindSqlDelete(where = "id=${bean.id}")
	long deleteById(Employee bean);*/

}

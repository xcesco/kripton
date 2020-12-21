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
package sqlite.feature.typeadapter.kripton180.raw.err;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;

import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;


/**
 * The Interface EmployeeRawErrDao.
 */
@BindDao(Employee.class)
public interface EmployeeRawErrDao {


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
	long insertWithAdapter(@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);


}

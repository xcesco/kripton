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
/**
 * 
 */
package sqlite.feature.typeadapter.kripton180;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterAddress;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindTable(name = "employees")
public class Employee {
	
	/** The id. */
	public long id;

	/** The last name. */
	//
	public String lastName;
	
	/** The first name. */
	public String firstName;
	
	/** The birth date. */
	public Date birthDate;
	
	/** The hire date. */
	public Date hireDate;

	/** The address. */
	@BindSqlAdapter(adapter = TypeAdapterAddress.class)
	public Address address;

	/** The field boolean. */
	@BindSqlAdapter(adapter = TypeAdapterBoolean.class)
	public String fieldBoolean;

	/** The field byte. */
	@BindSqlAdapter(adapter = TypeAdapterByte.class)
	public String fieldByte;

	/** The field character. */
	@BindSqlAdapter(adapter = TypeAdapterChar.class)
	public String fieldCharacter;

	/** The field short. */
	@BindSqlAdapter(adapter = TypeAdapterShort.class)
	public String fieldShort;

	/** The field integer. */
	@BindSqlAdapter(adapter = TypeAdapterInteger.class)
	public String fieldInteger;

	/** The field long. */
	@BindSqlAdapter(adapter = TypeAdapterLong.class)
	public String fieldLong;

	/** The field float. */
	@BindSqlAdapter(adapter = TypeAdapterFloat.class)
	public String fieldFloat;

	/** The field double. */
	@BindSqlAdapter(adapter = TypeAdapterDouble.class)
	public String fieldDouble;

	/** The field string. */
	@BindSqlAdapter(adapter = TypeAdapterString.class)
	public String fieldString;

	/** The field byte array. */
	@BindSqlAdapter(adapter = TypeAdapterByteArray.class)
	public String fieldByteArray;
	//

}

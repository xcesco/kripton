/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.select.scalarlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;


/**
 * The Interface PersonDAO.
 */
@BindDao(Person.class)
public interface PersonDAO {

	/**
	 * Select all.
	 *
	 * @return the sets the
	 */
	@BindSqlSelect(fields="typeName", orderBy="typeName")
	Set<String> selectAll();	
	
	/**
	 * Select all 2.
	 *
	 * @return the array list
	 */
	@BindSqlSelect(fields="birthDay", orderBy="typeName")
	ArrayList<Date> selectAll2();	
}
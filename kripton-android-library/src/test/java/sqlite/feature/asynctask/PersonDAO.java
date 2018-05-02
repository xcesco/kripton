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
package sqlite.feature.asynctask;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.asynctask.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonDAO.
 */
@BindDao(Person.class)
public interface PersonDAO {

	/**
	 * Insert thread 1.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert
	public void insertThread1(Person bean);
	
	/**
	 * Insert thread 2.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert
	public void insertThread2(Person bean);
	
	/**
	 * Select thread 1.
	 *
	 * @return the person
	 */
	@BindSqlSelect
	public Person selectThread1();
	
	/**
	 * Select thread 2.
	 *
	 * @return the person
	 */
	@BindSqlSelect
	public Person selectThread2();
}
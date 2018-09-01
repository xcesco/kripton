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
package sqlite.feature.typeadapter.kripton277.err1;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import android.arch.lifecycle.LiveData;

/**
 * The Interface PersonDAO.
 */
@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlInsert
	public Person insert(Person bean);
	
	@BindSqlSelect
	public Person selectTheOne();

	@BindSqlSelect(where="birthDate=${birthDay}")
	public List<Person> selectByBirthday(@BindSqlParam(adapter=DateAdapter.class) Date birthDay);
	
	@BindSqlSelect(where="birthDate=${birthDay}")
	LiveData<List<Person>> select(@BindSqlParam(adapter=DateAdapter.class) Date birthDay);
	
	@BindSqlSelect(where="birthDate=${birthDay}")
	public void selectByBirthday(@BindSqlParam(adapter=DateAdapter.class) Date birthDay, OnReadBeanListener<Person> listener);
	
	
}

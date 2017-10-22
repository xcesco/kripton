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
package sqlite.feature.rx.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.rx.persistence.AbstractDao;
import sqlite.feature.rx.persistence.CountryDao;
import sqlite.feature.rx.persistence.Person2PhoneDao;
import sqlite.feature.rx.persistence.PersonDao;
import sqlite.feature.rx.persistence.PhoneDao;
import sqlite.feature.rx.persistence.PrefixConfigDao;
import sqlite.feature.rx.persistence.XenoDataSource;

@RunWith(JUnit4.class)
public class TestCompileRX extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile() throws Throwable {		
		buildDataSourceProcessorTest(ActionType.class,
				Country.class,
				Person.class,
				PhoneNumber.class,
				PrefixConfig.class,
				SMS.class,
				Translation.class,
				AbstractDao.class,
				CountryDao.class,
				Person2PhoneDao.class,
				PhoneDao.class,
				PrefixConfigDao.class,
				PersonDao.class,
				XenoDataSource.class);
	}

}

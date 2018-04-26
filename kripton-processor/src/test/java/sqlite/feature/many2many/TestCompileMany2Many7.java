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
package sqlite.feature.many2many;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.many2many.case7.AppDataSource;
import sqlite.feature.many2many.case7.PersonDao;
import sqlite.feature.many2many.case7.Person;
import sqlite.feature.many2many.case7.City;
import sqlite.feature.many2many.case7.CityDao;
import sqlite.feature.many2many.case7.City2PersonDao;

/**
 * The Class TestCompileMany2Many6.
 */
@RunWith(JUnit4.class)
public class TestCompileMany2Many7 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	@Test
	public void testCompile() throws Throwable {
		buildDataSourceProcessorTest(AppDataSource.class, PersonDao.class, Person.class, City.class, CityDao.class,
				City2PersonDao.class);
	}

}

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
package sqlite.feature.immutable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.immutable.many2many.BaseDao;
import sqlite.feature.immutable.many2many.City;
import sqlite.feature.immutable.many2many.CityDao;
import sqlite.feature.immutable.many2many.Entity;
import sqlite.feature.immutable.many2many.Person;
import sqlite.feature.immutable.many2many.PersonCirtyDataSource;
import sqlite.feature.immutable.many2many.PersonCityDao;
import sqlite.feature.immutable.many2many.PersonDao;

/**
 * The Class TestCompileMany2Many.
 */
@RunWith(JUnit4.class)
public class TestCompileMany2ManyImmutable extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test OK.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testOK() throws Throwable {				
		//formatter:off
		buildDataSourceProcessorTest(
				PersonCirtyDataSource.class, 
				Person.class, City.class, Entity.class,   
				PersonDao.class, CityDao.class, PersonCityDao.class, 
				BaseDao.class);
		//formatter:on
	}

}

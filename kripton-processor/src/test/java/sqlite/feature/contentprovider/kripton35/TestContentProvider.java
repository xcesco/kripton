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
package sqlite.feature.contentprovider.kripton35;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.contentprovider.kripton35.entities.City;
import sqlite.feature.contentprovider.kripton35.entities.Person;
import sqlite.feature.contentprovider.kripton35.nolog.City2DAO;
import sqlite.feature.contentprovider.kripton35.nolog.Person2DAO;
import sqlite.feature.contentprovider.kripton35.nolog.Person2DataSource;
import sqlite.feature.contentprovider.kripton35.persistence.CityDAO;
import sqlite.feature.contentprovider.kripton35.persistence.PersonDAO;
import sqlite.feature.contentprovider.kripton35.persistence.PersonDataSource;

/**
 * The Class TestContentProvider.
 */
@RunWith(JUnit4.class)
public class TestContentProvider extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile 01.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompile01() throws Throwable {
		buildDataSourceProcessorTest(PersonDataSource.class, PersonDAO.class, CityDAO.class,Person.class, City.class);
	}
	
	/**
	 * No @BindType is put in bean definition.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompileWithNoLog() throws Throwable {
		buildDataSourceProcessorTest(Person2DataSource.class, Person2DAO.class, City2DAO.class,Person.class, City.class);
	}

}

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
package sqlite.feature.many2many.err2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.ForeignKeyNotFoundException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Entity;
import sqlite.feature.many2many.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCompileMany2ManyErr2.
 */
@RunWith(JUnit4.class)
public class TestCompileMany2ManyErr2 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test foreign key not found.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testForeignKeyNotFound() throws Throwable {
		this.expectedException(ForeignKeyNotFoundException.class);
		buildDataSourceProcessorTest(PersonCirtyErr2DataSource.class, 
				PersonErr2Dao.class, Person.class, 
				CityErr2Dao.class, City.class, 
				PersonCityErr2Dao.class,PersonCityErr2.class, 
				Entity.class, BaseDao.class);
	}

}

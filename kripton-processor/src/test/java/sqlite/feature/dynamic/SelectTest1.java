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
package sqlite.feature.dynamic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.dynamic.select1.Person2DataSource;
import sqlite.feature.dynamic.select1.PersonDAO;
import sqlite.feature.dynamic.select1.PersonDAO2;
import sqlite.feature.dynamic.select1.PersonDataSource;

/**
 * The Class SelectTest.
 */
@RunWith(JUnit4.class)
public class SelectTest1 extends AbstractBindSQLiteProcessorTest {

	/**
	 * No @BindType is put in bean definition.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void test01() throws Throwable {
		buildDataSourceProcessorTest(PersonDataSource.class, PersonDAO.class, Person.class);
	}
	
	/**
	 * Test dynamic where on jql.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void test_dynamic_where_on_jql() throws Throwable {
		buildDataSourceProcessorTest(Person2DataSource.class, PersonDAO2.class, Person.class);
	}
}

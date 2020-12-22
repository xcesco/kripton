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
package sqlite.feature.jql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.jql.entities.Bean;
import sqlite.feature.jql.entities.Child;
import sqlite.feature.jql.entities.Person;
import sqlite.feature.jql.persistence.DaoBean;
import sqlite.feature.jql.persistence.DaoChild;
import sqlite.feature.jql.persistence.DaoPerson;
import sqlite.feature.jql.persistence.FamilyDataSource;


/**
 * The Class TestJQL.
 */
@RunWith(JUnit4.class)
public class TestJQL extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile 01.
	 *
	 * @throws Throwable the throwable
	 */
	@Test 
	public void testCompile01() throws Throwable {
		buildDataSourceProcessorTest( Bean.class, Child.class, Person.class,FamilyDataSource.class, DaoChild.class, DaoPerson.class,  DaoBean.class);
	}
}

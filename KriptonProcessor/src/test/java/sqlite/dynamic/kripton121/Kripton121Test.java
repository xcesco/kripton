/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.dynamic.kripton121;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.dynamic.Person;

@RunWith(JUnit4.class)
public class Kripton121Test extends AbstractBindSQLiteProcessorTest {

	/**
	 * Happy ending scenario. 
	 */
	@Test
	public void testMainScenario() throws Throwable {
		buildDataSourceProcessorTest(Person1DataSource.class, Person1DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> in insert method
	 * @throws Throwable
	 */
//	@Test
//	public void testErr1() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err1DataSource.class, Err1DAO.class, Person.class);
//	}
	
	
}

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
package sqlite.dynamic.update;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.dynamic.Person;
import sqlite.dynamic.select.Err1DAO;
import sqlite.dynamic.select.Err1DataSource;
import sqlite.dynamic.select.Err2DAO;
import sqlite.dynamic.select.Err2DataSource;
import sqlite.dynamic.select.Err3DAO;
import sqlite.dynamic.select.Err3DataSource;
import sqlite.dynamic.select.Err4DAO;
import sqlite.dynamic.select.Err4DataSource;
import sqlite.dynamic.select.Err5DAO;
import sqlite.dynamic.select.Err5DataSource;
import sqlite.dynamic.select.Err6DAO;
import sqlite.dynamic.select.Err6DataSource;
import sqlite.dynamic.select.PersonDAO;
import sqlite.dynamic.select.PersonDataSource;

@RunWith(JUnit4.class)
public class UpdateTest extends AbstractBindSQLiteProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * @throws Throwable 
	 */
	@Test
	public void testOK() throws Throwable {
		buildDataSourceProcessorTest(PersonUpdateDataSource.class, PersonUpdateDAO.class, Person.class);
	}
	
	/**
	 * Use raw update without parameters
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr1() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err1UpdateDataSource.class, Err1UpdateDAO.class, Person.class);
	}
	
	/**
	 * Use bean update without parameters
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr2() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err2UpdateDataSource.class, Err2UpdateDAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> in twice in SELECT
	 * @throws Throwable
	 */
//	@Test
//	public void testErr2() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err2DataSource.class, Err2DAO.class, Person.class);
//	}
	
	/**
	 * Use <code>BindSqlWhere</code> with no String parameter
	 * @throws Throwable
	 */
//	@Test
//	public void testErr3() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err3DataSource.class, Err3DAO.class, Person.class);
//	}
	
	/**
	 * Use <code>BindSqlWhere</code> with no String parameter
	 * @throws Throwable
	 */
//	@Test
//	public void testErr4() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err4DataSource.class, Err4DAO.class, Person.class);
//	}
	
	/**
	 * Use <code>BindSqlWhere</code> in insert method
	 * @throws Throwable
	 */
//	@Test
//	public void testErr5() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err5DataSource.class, Err5DAO.class, Person.class);
//	}
	
	/**
	 * Use <code>BindSqlWhere</code> in twice in SELECT
	 * @throws Throwable
	 */
//	@Test
//	public void testErr6() throws Throwable {
//		this.expectedException(InvalidMethodSignException.class);
//		buildDataSourceProcessorTest(Err6DataSource.class, Err6DAO.class, Person.class);
//	}
}

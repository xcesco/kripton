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
package sqlite.paginatedResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;

@RunWith(JUnit4.class)
public class TestPaginatedResult extends AbstractBindSQLiteProcessorTest {

	/**
	 * OK
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testOK() throws Throwable {
		buildDataSourceProcessorTest(PersonDataSource.class, PersonDAO.class, Person.class);
	}

	/**
	 * pageSize is a String
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr1() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err1PersonDataSource.class, Err1PersonDAO.class, Err1Person.class);
	}

	/**
	 * Twice pageSize parameter
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr2() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err2PersonDataSource.class, Err2PersonDAO.class, Err2Person.class);
	}

	/**
	 * Both pageSize in annotation and as parameter
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr3() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err3PersonDataSource.class, Err3PersonDAO.class, Err3Person.class);
	}

	/**
	 * pageSize in annotation negative
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr4() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err4PersonDataSource.class, Err4PersonDAO.class, Err4Person.class);
	}

}

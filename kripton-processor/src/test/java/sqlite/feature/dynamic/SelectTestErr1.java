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

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.InvalidTypeForAnnotationException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.dynamic.err1.Err11DAO;
import sqlite.feature.dynamic.err1.Err11DataSource;
import sqlite.feature.dynamic.err1.Err12DAO;
import sqlite.feature.dynamic.err1.Err12DataSource;
import sqlite.feature.dynamic.err1.Err13DAO;
import sqlite.feature.dynamic.err1.Err13DataSource;
import sqlite.feature.dynamic.err1.Err14DAO;
import sqlite.feature.dynamic.err1.Err14DataSource;
import sqlite.feature.dynamic.err1.Err15DAO;
import sqlite.feature.dynamic.err1.Err15DataSource;
import sqlite.feature.dynamic.err1.Err16DAO;
import sqlite.feature.dynamic.err1.Err16DataSource;
import sqlite.feature.dynamic.err1.Err1DAO;
import sqlite.feature.dynamic.err1.Err1DataSource;
import sqlite.feature.dynamic.err1.Err2DAO;
import sqlite.feature.dynamic.err1.Err2DataSource;
import sqlite.feature.dynamic.err1.Err3DAO;
import sqlite.feature.dynamic.err1.Err3DataSource;
import sqlite.feature.dynamic.err1.Err4DAO;
import sqlite.feature.dynamic.err1.Err4DataSource;
import sqlite.feature.dynamic.err1.Err5DAO;
import sqlite.feature.dynamic.err1.Err5DataSource;
import sqlite.feature.dynamic.err1.Err6DAO;
import sqlite.feature.dynamic.err1.Err6DataSource;

/**
 * The Class SelectTest.
 */
@RunWith(JUnit4.class)
public class SelectTestErr1 extends AbstractBindSQLiteProcessorTest {

	
	/**
	 * Use <code>BindSqlWhere</code> in insert method.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr1() throws Throwable {
		this.expectedException(InvalidTypeForAnnotationException.class);
		buildDataSourceProcessorTest(Err1DataSource.class, Err1DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> in twice in SELECT.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr2() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err2DataSource.class, Err2DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> with no String parameter.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr3() throws Throwable {
		this.expectedException(InvalidTypeForAnnotationException.class);
		buildDataSourceProcessorTest(Err3DataSource.class, Err3DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> with no String parameter.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr4() throws Throwable {
		this.expectedException(InvalidTypeForAnnotationException.class);
		buildDataSourceProcessorTest(Err4DataSource.class, Err4DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> in insert method.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr5() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err5DataSource.class, Err5DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlWhere</code> in twice in SELECT.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr6() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err6DataSource.class, Err6DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> in insert method.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr11() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err11DataSource.class, Err11DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> in twice in SELECT.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr12() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err12DataSource.class, Err12DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> with no String parameter.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr13() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err13DataSource.class, Err13DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> with no String parameter.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr14() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err14DataSource.class, Err14DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> in insert method.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr15() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err15DataSource.class, Err15DAO.class, Person.class);
	}
	
	/**
	 * Use <code>BindSqlOrderBy</code> in twice in SELECT.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr16() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err16DataSource.class, Err16DAO.class, Person.class);
	}
}

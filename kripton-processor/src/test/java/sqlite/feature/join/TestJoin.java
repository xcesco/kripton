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
package sqlite.feature.join;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.join.model.AppDataSource;
import sqlite.feature.join.model.Book;
import sqlite.feature.join.model.BookDao;
import sqlite.feature.join.model.DaoBase;
import sqlite.feature.join.model.Entity;
import sqlite.feature.join.model.Loan;
import sqlite.feature.join.model.LoanDao;
import sqlite.feature.join.model.User;
import sqlite.feature.join.model.UserDao;

// TODO: Auto-generated Javadoc
/**
 * The Class TestJoin.
 */
@RunWith(JUnit4.class)
public class TestJoin extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile 01.
	 *
	 * @throws Throwable the throwable
	 */
	@Test 
	public void testCompile01() throws Throwable {
		buildDataSourceProcessorTest( AppDataSource.class,
				Book.class,
				BookDao.class,
				DaoBase.class,
				Entity.class,
				Loan.class,
				LoanDao.class,
				User.class,
				UserDao.class);
	}
}

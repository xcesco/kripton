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
package sqlite.select.scalarlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.select.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSelectScalarList.
 */
@RunWith(JUnit4.class)
public class TestSelectScalarList extends AbstractBindSQLiteProcessorTest {

	/**
	 * OK.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testOK() throws Throwable {
		buildDataSourceProcessorTest(PersonDataSource.class, PersonDAO.class, Person.class);
	}
	
	/**
	 * Two fields are selected as result.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErr1() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err1PersonDataSource.class, Err1PersonDAO.class, Person.class);
	}
	


}

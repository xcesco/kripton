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
package sqlite.feature.indexes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.exceptions.UnknownPropertyInJQLException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.indexes.Person;
import sqlite.feature.indexes.PersonDAO;
import sqlite.feature.indexes.PersonDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexTest.
 */
@RunWith(JUnit4.class)
public class IndexCompileTest extends AbstractBindSQLiteProcessorTest {

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
	 * Error 1.
	 *
	 * @throws Throwable the throwable
	 * @BindTable indexes element use an undefined field typeName
	 */
	@Test
	public void error1() throws Throwable {
		this.expectedException(KriptonProcessorException.class);
		buildDataSourceProcessorTest(Err1PersonDataSource.class, Err1PersonDAO.class, Err1Person.class);
	}	
	
	/**
	 * Error 2.
	 *
	 * @throws Throwable the throwable
	 * @BindTable indexes element forgot a comma between two valid field typeName
	 */
	@Test
	public void error2() throws Throwable {
		this.expectedException(UnknownPropertyInJQLException.class);
		buildDataSourceProcessorTest(Err2PersonDataSource.class, Err2PersonDAO.class, Err2Person.class);
	}	
	
}

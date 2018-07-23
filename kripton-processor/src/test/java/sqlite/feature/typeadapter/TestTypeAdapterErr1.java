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
package sqlite.feature.typeadapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.typeadapter.kripton277.err1.*;
 
/**
 * The Class TestTypeAdapter.
 */
@RunWith(JUnit4.class)
public class TestTypeAdapterErr1 extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * Test OK.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testIncompatibleType() throws Throwable {
		this.expectedException(KriptonProcessorException.class, "KriptonProcessorException: In DAO 'PersonDAO', method 'selectByBirthday' has parameter 'birthDay' uses @BindSqlParam that manages type 'java.sql.Date' instead of 'java.util.Date'");
		buildDataSourceProcessorTest(AppDataSource.class, DateAdapter.class, Person.class, PersonDAO.class);
	}

}

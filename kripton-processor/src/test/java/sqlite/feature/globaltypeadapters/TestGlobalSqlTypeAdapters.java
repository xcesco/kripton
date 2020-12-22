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
package sqlite.feature.globaltypeadapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.globaltypeadapters.model1.Date2Long;
import sqlite.feature.globaltypeadapters.model1.Person1DataSource;
import sqlite.feature.globaltypeadapters.model1.Person1ErrDataSource;


/**
 * The Class TestGlobalSqlTypeAdapters.
 */
@RunWith(JUnit4.class)
public class TestGlobalSqlTypeAdapters extends AbstractBindSQLiteProcessorTest {

	/**
	 * Compile.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void compile() throws Throwable {
		buildDataSourceProcessorTest(DaoPerson.class, Person.class, Date2Long.class, Person1DataSource.class);
	}
	
	/**
	 * Compile with error.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void compileWithError() throws Throwable {
		this.expectedException(InvalidDefinition.class);
		buildDataSourceProcessorTest(DaoPerson.class, Person.class, Date2Long.class, Person1ErrDataSource.class);
	}

}

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
package sqlite.feature.in;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.in.err1.AppDataSource;
import sqlite.feature.in.err1.City;
import sqlite.feature.in.err1.DaoCity;

/**
 * Test the alias param
 */
@RunWith(JUnit4.class)
public class TestFeatureInErr1 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Compile.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void compile() throws Throwable {
		this.expectedException(InvalidMethodSignException.class, "In class 'DaoCity', method 'selectAll2' has an invalid signature: 'in' condition need an array or collection parameter");
		buildDataSourceProcessorTest(City.class, DaoCity.class, AppDataSource.class);
	}


}

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
package sqlite.feature.performance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.performance.simple.SimpleAddressDao;
import sqlite.feature.performance.simple.SimpleAddressItem;
import sqlite.feature.performance.simple.SimpleDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPerformance.
 */
@RunWith(JUnit4.class)
public class TestPerformance extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * Test compile performance.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompilePerformance() throws Throwable {
		buildDataSourceProcessorTest(SimpleAddressDao.class, SimpleAddressItem.class, SimpleDataSource.class);
	}

}

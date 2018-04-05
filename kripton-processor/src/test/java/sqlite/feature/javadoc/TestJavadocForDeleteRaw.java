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
package sqlite.feature.javadoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.javadoc.delete.raw.DeleteRawPersonDao;
import sqlite.feature.javadoc.delete.raw.DeleteRawPersonDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TestJavadocForDeleteRaw.
 */
@RunWith(JUnit4.class)
public class TestJavadocForDeleteRaw extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile delete raw.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompileDeleteRaw() throws Throwable {
		buildDataSourceProcessorTest(Person.class, DeleteRawPersonDao.class, DeleteRawPersonDataSource.class);
	}

}

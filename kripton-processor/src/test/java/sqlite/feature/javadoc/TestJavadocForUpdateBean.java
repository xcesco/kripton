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

import sqlite.feature.javadoc.update.bean.UpdateBeanPersonDao;
import sqlite.feature.javadoc.update.bean.UpdateBeanPersonDataSource;

import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestJavadocForUpdateBean.
 */
@RunWith(JUnit4.class)
public class TestJavadocForUpdateBean extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile update bean.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompileUpdateBean() throws Throwable {
		buildDataSourceProcessorTest(Person.class, UpdateBeanPersonDao.class, UpdateBeanPersonDataSource.class);
	}

}

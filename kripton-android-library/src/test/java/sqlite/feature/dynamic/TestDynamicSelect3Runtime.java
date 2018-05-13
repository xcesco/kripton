/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.dynamic;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.feature.dynamic.select3.BindPersonDataSource;

/**
 * The Class TestDynamicSelectRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestDynamicSelect3Runtime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 */
	@Test
	public void testRunSqlite1() {

		try (BindPersonDataSource dataSource = BindPersonDataSource.open()) {

			for (int i = 0; i < 10; i++) {
				dataSource.getPersonDAO().insert("name" + i, "surname" + i, "city" + i, new Date());				
			}

			{
				List<Person> list = dataSource.getPersonDAO().select("nam", "name >= 'name1' ");
				assertTrue(list.get(0).name.equals("name1"));
			}
			
			{
				List<Person> list = dataSource.getPersonDAO().select("name", null);
				assertTrue(list.size()==10);
			}
		}
	}

}

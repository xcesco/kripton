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
package sqlite.feature.includefields;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import base.BaseAndroidTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestIncludeFieldsRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestIncludeFieldsRuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {
		final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_city TEXT, birth_day TEXT, name TEXT, surname TEXT, type_name TEXT); CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_0 on person (birth_city, birth_day);";		                                 
		final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_person_name; DROP INDEX IF EXISTS idx_person_1;DROP TABLE IF EXISTS person;";

		Assert.assertTrue(CREATE_TABLE_SQL.equals(PersonTable.CREATE_TABLE_SQL));
		Assert.assertTrue(DROP_TABLE_SQL.equals(PersonTable.DROP_TABLE_SQL));
	}

}

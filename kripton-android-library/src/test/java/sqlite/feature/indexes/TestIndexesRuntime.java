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
package sqlite.feature.indexes;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.feature.indexes.BindPersonDataSource.Batch;

/**
 * The Class TestIndexesRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestIndexesRuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 */
	@Test
	public void testRunSqlite1() {	
		final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_city TEXT, birth_day TEXT, date TEXT, name TEXT, name_temp TEXT, surname TEXT, type_name TEXT, UNIQUE (type_name, date)); CREATE UNIQUE INDEX idx_person_0 on person (type_name, date); CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_0 on person (birth_city, birth_day); CREATE INDEX idx_person_1 on person (surname);";				                               
		final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_person_name; DROP INDEX IF EXISTS idx_person_1; DROP INDEX IF EXISTS idx_person_1; DROP INDEX IF EXISTS idx_person_2;DROP TABLE IF EXISTS person;";

		Assert.assertTrue(CREATE_TABLE_SQL.equals(PersonTable.CREATE_TABLE_SQL));
		Assert.assertTrue(DROP_TABLE_SQL.equals(PersonTable.DROP_TABLE_SQL));
		
		BindPersonDataSource.getInstance().executeBatch(new Batch<Void>() {

			@Override
			public Void onExecute(BindPersonDaoFactory daoFactory) {
				for (int i=0;i<10;i++)
				{
					daoFactory.getPersonDAO().insertOne("name"+i, "surname"+i, "city"+i, new Date());
				}
				
				{
					List<Person> list=daoFactory.getPersonDAO().selectOne("nam", "1=1", "name asc", new Date());
					assertTrue(list.get(0).typeName.equals("name0"));
				}
				
				{
					List<Person> list=daoFactory.getPersonDAO().selectOne("name", "type_name like 'name%'",  "type_name desc", new Date());
					assertTrue(list.get(0).typeName.equals("name9"));
				}
				return null;
			}
		});
				
	}

}

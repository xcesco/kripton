/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.indexes;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import base.BaseAndroidTest;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestIndexesRuntime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() {	
		BindPersonDataSource dataSource=BindPersonDataSource.instance();
		
		dataSource.open();
		
		for (int i=0;i<10;i++)
		{
			dataSource.getPersonDAO().insertOne("name"+i, "surname"+i, "city"+i, new Date());
		}
		
		{
			List<Person> list=dataSource.getPersonDAO().selectOne("nam", "and 1=1", "name asc", new Date());
			assertTrue(list.get(0).name.equals("name0"));
		}
		
		{
			List<Person> list=dataSource.getPersonDAO().selectOne("name", "and name like 'name%'",  "name desc", new Date());
			assertTrue(list.get(0).name.equals("name9"));
		}
		dataSource.close();
	}

}

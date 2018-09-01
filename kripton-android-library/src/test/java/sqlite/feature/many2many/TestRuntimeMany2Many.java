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
package sqlite.feature.many2many;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import base.BaseAndroidTest;
import org.junit.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntimeMany2Many.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimeMany2Many extends BaseAndroidTest {

	/**
	 * Test many 2 many.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void testMany2Many() throws InterruptedException {
		try (BindPersonCirtyDataSource dataSource = BindPersonCirtyDataSource.open()) {			
			List<City> cities=new ArrayList<City>();
			List<Person> persons=new ArrayList<Person>();
			
			CityDaoImpl cityDao = dataSource.getCityDao();
			PersonDaoImpl personDao = dataSource.getPersonDao();
			PersonCityDaoImpl m2mDao = dataSource.getPersonCityDao();

			// insert city
			for (int i = 0; i < 1; i++) {
				City bean = new City();
				bean.name = "city" + i;
				cityDao.insert(bean);
				
				cities.add(bean);
			}

			// insert person
			for (int i = 0; i < 1; i++) {
				Person bean = new Person();
				bean.name="person"+i;
				personDao.insert(bean);
				
				persons.add(bean);
			}
			
			long m2mId;
			{
				// m2m
				PersonCity bean=new PersonCity(0, persons.get(0).id, cities.get(0).id);
				//bean.cityId=cities.get(0).id;
				//bean.personId=persons.get(0).id;				
				m2mId=m2mDao.insert(bean);
			}
			
			// delete
			{
				personDao.deleteById(persons.get(0).id);
			}
			
			// check how many personCity there are
			//Assert.assertTrue(m2mId!=0);
			//PersonCity test = m2mDao.selectById(m2mId);
			//Assert.assertTrue(test==null);
			
			
		}

	}

}

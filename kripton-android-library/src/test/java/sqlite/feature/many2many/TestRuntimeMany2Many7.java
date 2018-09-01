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

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.many2many.case7.BindAppDaoFactory;
import sqlite.feature.many2many.case7.BindAppDataSource;
import sqlite.feature.many2many.case7.BindAppDataSource.Transaction;
import sqlite.feature.many2many.case7.City2PersonDaoImpl;
import sqlite.feature.many2many.case7.PersonDaoImpl;
import sqlite.feature.many2many.case7.CityDaoImpl;
import sqlite.feature.many2many.case7.CityPerson;
import sqlite.feature.many2many.case7.Person;
import sqlite.feature.many2many.case7.City;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimeMany2Many7 extends BaseAndroidTest {

	/**
	 * Test many 2 many.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test
	public void testMany2Many() throws InterruptedException {

		BindAppDataSource ds = BindAppDataSource.getInstance();
		ds.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				List<City> cities = new ArrayList<City>();
				List<Person> persons = new ArrayList<Person>();

				CityDaoImpl cityDao = daoFactory.getCityDao();
				PersonDaoImpl personDao = daoFactory.getPersonDao();
				City2PersonDaoImpl m2mDao = daoFactory.getCity2PersonDao();

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
					bean.name = "person" + i;
					personDao.insert(bean);

					persons.add(bean);
				}

				{
					// m2m
					CityPerson bean = new CityPerson(0,cities.get(0).id, persons.get(0).id);					
					m2mDao.insert(bean);
				}

				return TransactionResult.COMMIT;
			}
		});

	}

}

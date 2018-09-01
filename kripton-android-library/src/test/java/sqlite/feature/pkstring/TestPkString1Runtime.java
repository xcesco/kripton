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
package sqlite.feature.pkstring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import base.BaseAndroidTest;
import sqlite.feature.pkstring.many2many1.BindPersonCirtyDaoFactory;
import sqlite.feature.pkstring.many2many1.BindPersonCirtyDataSource;
import sqlite.feature.pkstring.many2many1.BindPersonCirtyDataSource.Batch;
import sqlite.feature.pkstring.many2many1.City;
import sqlite.feature.pkstring.many2many1.Person;
import sqlite.feature.pkstring.many2many1.PersonCity;


/**
 * The Class TestPkString1Runtime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestPkString1Runtime extends BaseAndroidTest {
	
	@Test
	public void testRun() {			
		BindPersonCirtyDataSource instance=BindPersonCirtyDataSource.getInstance();
		
		instance.executeBatch(new Batch<Void>() {

			@Override
			public Void onExecute(BindPersonCirtyDaoFactory daoFactory) {
				City city=new City();
				city.id="Oxford";
				city.name="City of Oxford";
				
				daoFactory.getCityDao().insert(city);
				
				Person person=new Person();
				person.name="Manero Tonj";
				
				daoFactory.getPersonDao().insert(person);
				
				PersonCity m2m=new PersonCity(0,person.id, city.id );				
				
				daoFactory.getPersonCityDao().insert(m2m);
				
				return null;
			}
			
		});
	}

}

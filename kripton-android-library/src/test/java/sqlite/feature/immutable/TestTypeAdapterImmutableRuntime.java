/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.immutable;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import base.BaseAndroidTest;
import sqlite.feature.immutable.adapter.BindAppDataSource;
import sqlite.feature.immutable.adapter.Person;

/**
 * The Class TestFeatureSQLTypeAdapterRuntime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestTypeAdapterImmutableRuntime extends BaseAndroidTest {

	/**
	 * Test select.
	 */
	@Test
	public void testSelect() {
		java.sql.Date now=new java.sql.Date(new java.util.Date().getTime());
		
		try (BindAppDataSource ds=BindAppDataSource.open()) {
			Person bean=new Person(0, "name","surname", now);
			
			ds.getPersonDAO().insert(bean);
			ds.getPersonDAO().selectByBirthday(now, new OnReadBeanListener<Person>() {
				
				@Override
				public void onRead(Person bean, int row, int rowCount) {
					log("found row %s", row);
					
				}
			});
		}

	}

}

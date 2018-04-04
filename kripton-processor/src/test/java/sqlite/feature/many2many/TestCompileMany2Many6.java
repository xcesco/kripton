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
package sqlite.feature.many2many;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.many2many.case6.model.ActionType;
import sqlite.feature.many2many.case6.model.Country;
import sqlite.feature.many2many.case6.model.Person;
import sqlite.feature.many2many.case6.model.PersonPhone;
import sqlite.feature.many2many.case6.model.PhoneNumber;
import sqlite.feature.many2many.case6.model.PrefixConfig;
import sqlite.feature.many2many.case6.model.SMS;
import sqlite.feature.many2many.case6.model.Translation;
import sqlite.feature.many2many.case6.persistence.AbstractDao;
import sqlite.feature.many2many.case6.persistence.CountryDao;
import sqlite.feature.many2many.case6.persistence.Person2PhoneDao;
import sqlite.feature.many2many.case6.persistence.PersonDao;
import sqlite.feature.many2many.case6.persistence.PhoneDao;
import sqlite.feature.many2many.case6.persistence.PrefixConfigDao;
import sqlite.feature.many2many.case6.persistence.XenoDataSource;

@RunWith(JUnit4.class)
public class TestCompileMany2Many6 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testNoMethods() throws Throwable {		
		buildDataSourceProcessorTest(ActionType.class,
				Country.class,
				Person.class,
				PhoneNumber.class,
				PrefixConfig.class,
				SMS.class,
				Translation.class,
				AbstractDao.class,
				CountryDao.class,
				PersonPhone.class,
				Person2PhoneDao.class,				
				PhoneDao.class,
				PrefixConfigDao.class,
				PersonDao.class,
				XenoDataSource.class);
	}

}

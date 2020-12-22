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
import sqlite.feature.many2many.case5.model.ActionType;
import sqlite.feature.many2many.case5.model.Country;
import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;
import sqlite.feature.many2many.case5.model.PrefixConfig;
import sqlite.feature.many2many.case5.model.SMS;
import sqlite.feature.many2many.case5.model.Translation;
import sqlite.feature.many2many.case5.persistence.AbstractDao;
import sqlite.feature.many2many.case5.persistence.CountryDao;
import sqlite.feature.many2many.case5.persistence.Person2PhoneDao;
import sqlite.feature.many2many.case5.persistence.PersonDao;
import sqlite.feature.many2many.case5.persistence.PhoneDao;
import sqlite.feature.many2many.case5.persistence.PrefixConfigDao;
import sqlite.feature.many2many.case5.persistence.XenoDataSource;


/**
 * The Class TestCompileMany2Many5.
 */
@RunWith(JUnit4.class)
public class TestCompileMany2Many5 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test no methods.
	 *
	 * @throws Throwable the throwable
	 */
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
				Person2PhoneDao.class,
				PhoneDao.class,
				PrefixConfigDao.class,
				PersonDao.class,
				XenoDataSource.class);
	}

}

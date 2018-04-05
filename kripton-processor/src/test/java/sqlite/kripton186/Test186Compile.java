/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.kripton186;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton186.model.ActionType;
import sqlite.kripton186.model.Country;
import sqlite.kripton186.model.PhoneNumber;
import sqlite.kripton186.model.PrefixConfig;
import sqlite.kripton186.model.Translation;
import sqlite.kripton186.persistence.AbstractDao;
import sqlite.kripton186.persistence.CountryDao;
import sqlite.kripton186.persistence.PhoneDao;
import sqlite.kripton186.persistence.PrefixConfigDao;
import sqlite.kripton186.persistence.XenoDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class Test186Compile.
 */
@RunWith(JUnit4.class)
public class Test186Compile extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test database.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testDatabase() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(XenoDataSource.class, PrefixConfigDao.class,PhoneDao.class,CountryDao.class,AbstractDao.class,Translation.class,PrefixConfig.class,PhoneNumber.class,Country.class,ActionType.class);
	}

}

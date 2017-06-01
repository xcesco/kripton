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
package sqlite.kripton111;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton111.model.ActionType;
import sqlite.kripton111.model.Country;
import sqlite.kripton111.model.PhoneNumber;
import sqlite.kripton111.model.PrefixConfig;
import sqlite.kripton111.persistence.AbstractDao;
import sqlite.kripton111.persistence.CountryDao;
import sqlite.kripton111.persistence.PhoneDao;
import sqlite.kripton111.persistence.PrefixConfigDao;
import sqlite.kripton111.persistence.XenoDataSource;

public class TestCompile111 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ActionType.class, Country.class, PhoneNumber.class, PrefixConfig.class, AbstractDao.class, CountryDao.class, PhoneDao.class, PrefixConfigDao.class,
				XenoDataSource.class);
	}

}

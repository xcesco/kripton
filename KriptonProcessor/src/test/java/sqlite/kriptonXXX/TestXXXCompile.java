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
package sqlite.kriptonXXX;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton56.entities.MessageEntity;
import sqlite.kripton56.entities.OwnerType;
import sqlite.kripton56.internal.MessageType;
import sqlite.kripton56.persistence.DaoMessage;
import sqlite.kripton56.persistence.WhisperDataSource;
import sqlite.kriptonXXX.model.ActionType;
import sqlite.kriptonXXX.model.Country;
import sqlite.kriptonXXX.model.PhoneNumber;
import sqlite.kriptonXXX.model.PrefixConfig;
import sqlite.kriptonXXX.model.Translation;
import sqlite.kriptonXXX.persistence.AbstractDao;
import sqlite.kriptonXXX.persistence.CountryDao;
import sqlite.kriptonXXX.persistence.PhoneDao;
import sqlite.kriptonXXX.persistence.PrefixConfigDao;
import sqlite.kriptonXXX.persistence.XenoDataSource;

@RunWith(JUnit4.class)
public class TestXXXCompile extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testDatabase() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(XenoDataSource.class, PrefixConfigDao.class,PhoneDao.class,CountryDao.class,AbstractDao.class,Translation.class,PrefixConfig.class,PhoneNumber.class,Country.class,ActionType.class);
	}

}

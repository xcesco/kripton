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
package sqlite.kripton48;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton48.entities.Bean01;
import sqlite.kripton48.entities.Bean02;
import sqlite.kripton48.persistence.BaseDao;
import sqlite.kripton48.persistence.DaoBean01;
import sqlite.kripton48.persistence.DaoBean02;
import sqlite.kripton48.persistence.Dummy01DataSource;
import sqlite.kripton48.persistence.Dummy02DataSource;


/**
 * The Class Test48Compile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(JUnit4.class)
public class Test48Compile extends AbstractBindSQLiteProcessorTest {

	/**
	 * id: Long id.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, BaseDao.class, Dummy02DataSource.class, DaoBean02.class, Bean02.class);
	}

}

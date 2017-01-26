/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton60;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class Test60Compile extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testFields() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, Bean.class, EnumType.class);
	}

	@Test
	public void testSharedFields() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(Bean.class, EnumType.class);
	}

	@Test
	public void testSharedFields2() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean2.class, EnumType.class);
		buildSharedPreferencesProcessorTest(Bean2.class, EnumType.class);
	}
}

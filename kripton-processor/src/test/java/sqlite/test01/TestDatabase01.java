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
package sqlite.test01;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDatabase01.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(JUnit4.class)
public class TestDatabase01 extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * No element annotaed .
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test//(expected = AssertionError.class)
	public void test00() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Bean01.class);
	}

	/**
	 * No @BindType is put in bean definition.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, Bean01.class);
	}

	/**
	 * No @BindType is put in bean definition.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test02() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy02Database.class, Bean01.class, Bean02.class);

	}

	/**
	 * No database schema with @BindDatabaseSchema was found.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test03() throws IOException, InstantiationException, IllegalAccessException {
		withGeneratedSourceCounter(buildDataSourceProcessorTest(Dummy03Database.class, Bean01.class, Bean02.class), 1);
	}

	/**
	 * Class com.abubusoft.kripton.processor.test01.Bean04, used in
	 * Dummy04DatabaseSchema DatabaseSchemaDefinition, has no property!
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test04() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy04Database.class, Bean04.class);
	}

	/**
	 * No database schema with @BindDatabaseSchema was found.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test05() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy05Database.class, Bean04.class);
	}

	/**
	 * Test 06.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test06() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy06Database.class, Bean06.class);
	}

	/**
	 * No primary key.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test07() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy07Database.class, Bean07.class);
	}

	/**
	 * No primary key.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test08() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy08Database.class, Bean08.class);
	}

	/**
	 * Primary key Long.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test09() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy09Database.class, Bean09.class);
	}

	/**
	 * Too many primary keys.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test10() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy10Database.class, Bean10.class);
	}

	/**
	 * Too many primary keys.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test11() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy11Error.class, Bean10.class);
	}

	/**
	 * Twice database definitino.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void test12() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy12ADatabase.class, Dummy12BDatabase.class, Bean12.class);
	}

}

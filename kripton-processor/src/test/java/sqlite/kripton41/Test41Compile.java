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
package sqlite.kripton41;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class Test41Compile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(JUnit4.class)
public class Test41Compile extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test select error invalid method sign exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testSelectErrorInvalidMethodSignException() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test select OK.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testSelectOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy02DataSource.class, DaoBeanSelectOK.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test insert error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void testInsertError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy03DataSource.class, DaoBeanInsertERR.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test insert OK.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testInsertOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy04DataSource.class, DaoBeanInsertOK.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test update error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void testUpdateError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy05DataSource.class, DaoBeanUpdateERR.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test update OK.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testUpdateOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy06DataSource.class, DaoBeanUpdateOK.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test delete error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test(expected = AssertionError.class)
	public void testDeleteError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy07DataSource.class, DaoBeanDeleteERR.class, Bean01.class, BaseDao.class);
	}

	/**
	 * Test delete OK.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testDeleteOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy08DataSource.class, DaoBeanDeleteOK.class, Bean01.class, BaseDao.class);
	}

}

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
package sqlite.kripton38;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementFound;

import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class Test38Compile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(JUnit4.class)
public class Test38Compile extends AbstractBindSQLiteProcessorTest {

	/**
	 * id: Long id.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, BaseDao.class);
	}

	/**
	 * id: long id.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test02() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy02DataSource.class, DaoBean02.class, Bean02.class, BaseDao.class);
	}

	/**
	 * Test 03.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @BindColumn(ColumnType.PRIMARY_KEY) on Long id;
	 */
	@Test
	public void test03() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy03DataSource.class, DaoBean03.class, Bean03.class, BaseDao.class);
	}

	/**
	 * No DAO definition with @BindDaoDefinition annotation was found for class
	 * Dummy01DatabaseSchema with @BindDatabaseSchema annotation.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorNoDaoElementsFound() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(NoDaoElementFound.class);
		buildDataSourceProcessorTest(Dummy04DataSource.class, DaoBean04.class, Bean04.class, BaseDao.class);
	}

	/**
	 * No DAO definition with @BindDaoDefinition annotation was found for class
	 * Dummy01DatabaseSchema with @BindDatabaseSchema annotation.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test05() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean05.class, BeanType.class);
		buildDataSourceProcessorTest(Dummy05DataSource.class, DaoBean05.class, Bean05.class, BaseDao.class, BeanType.class);
	}

	/**
	 * Test error method parameter not found exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorMethodParameterNotFoundException() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(Dummy06DataSource.class, DaoBean06.class, Bean06.class, BaseDao.class, BeanType.class);
	}

}

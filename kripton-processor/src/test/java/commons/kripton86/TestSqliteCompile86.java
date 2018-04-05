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
package commons.kripton86;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.DaoDefinitionWithoutAnnotatedMethodException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.exceptions.TooManySQLPrimaryKeyFoundException;

import commons.kripton86.test1.Bean1;
import commons.kripton86.test1.DS1DataSource;
import commons.kripton86.test1.Dao1;
import commons.kripton86.test2.Bean2;
import commons.kripton86.test2.DS2DataSource;
import commons.kripton86.test2.Dao2;
import commons.kripton86.test4.Bean4;
import commons.kripton86.test4.DS4DataSource;
import commons.kripton86.test4.Dao4;
import commons.kripton86.test5.Bean5;
import commons.kripton86.test5.DS5DataSource;
import commons.kripton86.test5.Dao5;
import commons.kripton86.test7.Bean7;
import commons.kripton86.test7.DS7DataSource;
import commons.kripton86.test7.Dao7;
import commons.kripton86.test8.Bean8;
import commons.kripton86.test8.DS8DataSource;
import commons.kripton86.test8.Dao8;
import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSqliteCompile86.
 */
public class TestSqliteCompile86 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test 1 compile.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test1Compile() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(DaoDefinitionWithoutAnnotatedMethodException.class);
		buildDataSourceProcessorTest(DS1DataSource.class, Dao1.class, Bean1.class);
	}
	
	/**
	 * Test 2 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(MethodWithoutSupportedAnnotationException.class);
		buildDataSourceProcessorTest(DS2DataSource.class, Dao2.class, Bean2.class);
	}
	
	/**
	 * Test 4 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test4Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(SQLPrimaryKeyNotFoundException.class);
		buildDataSourceProcessorTest(DS4DataSource.class, Dao4.class, Bean4.class);
	}
	
	/**
	 * Test 5 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test5Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(SQLPrimaryKeyNotValidTypeException.class);
		buildDataSourceProcessorTest(DS5DataSource.class, Dao5.class, Bean5.class);
	}
	
	/**
	 * Test 7 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test7Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(TooManySQLPrimaryKeyFoundException.class);
		buildDataSourceProcessorTest(DS7DataSource.class, Dao7.class, Bean7.class);
	}

	/**
	 * Test 8 compile.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void test8Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(DS8DataSource.class, Dao8.class, Bean8.class);
	}
}

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
package sqlite.feature.foreignKey;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.CircularRelationshipException;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class TestForeignKeyCompile extends AbstractBindSQLiteProcessorTest {

	/**
	 * test on select 1
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testForeignKeyCompile1() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanA_1.class, BeanA_2.class, DaoBeanA_1.class, DaoBeanA_2.class, DummyDataSource.class);
	}
	
	@Test
	public void testForeignKeyCompile2() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanA_3.class, BeanA_4.class, DaoBeanA_3.class, DaoBeanA_4.class, Dummy2DataSource.class);
	}
	
	@Test
	public void testForeignKeyCompile3() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(CircularRelationshipException.class);
		buildDataSourceProcessorTest(BeanA_5.class, BeanA_6.class, DaoBeanA_5.class, DaoBeanA_6.class, Dummy3DataSource.class);
	}

}

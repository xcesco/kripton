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
package sqlite.kripton33;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyInAnnotationNotFoundException;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@RunWith(JUnit4.class)
public class Test33Compile extends AbstractBindSQLiteProcessorTest {

	/**
	 * test on select 1
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorMethodParameterNotFound01() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(Channel01DataSource.class, DaoChannel01.class, Channel.class);
	}

	/**
	 * test on select 2
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorMethodParameterNotFound02() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(Channel02DataSource.class, DaoChannel02.class, Channel.class);
	}

	/**
	 * test on update raw
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorMethodParameterNotFound03() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(Channel03DataSource.class, DaoChannel03.class, Channel.class);
	}

	/**
	 * test on delete raw
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorMethodParameterNotFound04() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(Channel04DataSource.class, DaoChannel04.class, Channel.class);
	}

	/**
	 * test on update bean
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorPropertyInAnnotationNotFoundException01() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyInAnnotationNotFoundException.class);
		buildDataSourceProcessorTest(Channel05DataSource.class, DaoChannel05.class, Channel.class);
	}

	/**
	 * test on delete bean
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testErrorPropertyInAnnotationNotFoundException02() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyInAnnotationNotFoundException.class);
		buildDataSourceProcessorTest(Channel06DataSource.class, DaoChannel06.class, Channel.class);
	}

}

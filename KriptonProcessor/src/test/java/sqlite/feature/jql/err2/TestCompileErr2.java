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
package sqlite.feature.jql.err2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;

import sqlite.AbstractBindSQLiteProcessorTest;

@RunWith(JUnit4.class)
public class TestCompileErr2 extends AbstractBindSQLiteProcessorTest {
	
	
	@Test 
	public void test_must_work() throws Throwable {
		this.expectedException(MethodParameterNotFoundException.class);
		buildDataSourceProcessorTest(BeanErr2.class, DaoErr2.class, Err2DataSource.class);
	}

}

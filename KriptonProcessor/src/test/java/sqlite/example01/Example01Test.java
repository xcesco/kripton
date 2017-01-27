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
package sqlite.example01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;

@RunWith(JUnit4.class)
public class Example01Test extends AbstractBindSQLiteProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * @throws Throwable 
	 */
	@Test
	public void test01() throws Throwable {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoChannel.class, Channel.class);
	}
}

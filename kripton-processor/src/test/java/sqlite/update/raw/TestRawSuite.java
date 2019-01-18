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
package sqlite.update.raw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.update.raw.err1.TestRawErr1;
import sqlite.update.raw.err2.TestRawErr2;
import sqlite.update.raw.err3.TestRawErr3;
import sqlite.update.raw.err4.TestRawErr4;

/**
 * The Class TestAdapterSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestRawErr1.class,
		TestRawErr2.class,
		TestRawErr3.class,
		TestRawErr4.class
		 })
//@formatter:on
public class TestRawSuite extends BaseProcessorTest {

}

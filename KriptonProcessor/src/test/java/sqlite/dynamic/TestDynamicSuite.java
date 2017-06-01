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
package sqlite.dynamic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.dynamic.kripton121.Kripton121Test;
import sqlite.dynamic.select.SelectTest;
import sqlite.dynamic.update.UpdateTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		SelectTest.class,
		UpdateTest.class,
		Kripton121Test.class
		 })
//@formatter:on
public class TestDynamicSuite extends BaseProcessorTest {

}

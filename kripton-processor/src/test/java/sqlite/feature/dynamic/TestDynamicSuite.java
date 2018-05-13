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
package sqlite.feature.dynamic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.feature.dynamic.kripton121.Kripton121Test;

/**
 * The Class TestDynamicSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		SelectTest1.class,
		SelectTest2.class,
		SelectTest3.class,
		
		SelectTestErr1.class,
		
		UpdateTest1.class,
		UpdateTest2.class,
		
		DeleteTest1.class,
		Kripton121Test.class
		 })
//@formatter:on
public class TestDynamicSuite extends BaseProcessorTest {

}

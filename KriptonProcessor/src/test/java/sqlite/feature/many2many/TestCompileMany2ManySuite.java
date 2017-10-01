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
package sqlite.feature.many2many;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.feature.many2many.entity.TestCompileMany2ManyOk1;
import sqlite.feature.many2many.err1.TestCompileMany2ManyErr1;
import sqlite.feature.many2many.err2.TestCompileMany2ManyErr2;
import sqlite.feature.many2many.err3.TestCompileMany2ManyErr3;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestCompileMany2Many.class,
		TestCompileMany2ManyOk1.class,
		
		TestCompileMany2ManyErr1.class,
		TestCompileMany2ManyErr2.class,
		TestCompileMany2ManyErr3.class
		 })
//@formatter:on
public class TestCompileMany2ManySuite extends BaseProcessorTest {

}

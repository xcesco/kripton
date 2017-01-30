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
package sqlite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.dynamic.TestDynamicSuite;
import sqlite.example01.Example01Suite;
import sqlite.example02.Example02Suite;
import sqlite.foreignKey.TestForeignKeySuite;
import sqlite.includeFields.IncludeFieldsTestSuite;
import sqlite.indexes.IndexTestSuite;
import sqlite.kripton33.Test33Suite;
import sqlite.kripton38.Test38Suite;
import sqlite.kripton40.Test40Suite;
import sqlite.kripton41.Test41Suite;
import sqlite.kripton48.Test48Suite;
import sqlite.kripton49.Test49Suite;
import sqlite.kripton51.Test51Suite;
import sqlite.kripton56.Test56Suite;
import sqlite.kripton58.Test58Suite;
import sqlite.kripton60.Test60Suite;
import sqlite.kripton62.Test62Suite;
import sqlite.kripton63.Test63Suite;
import sqlite.kripton64.Test64Suite;
import sqlite.kripton84.Test84Suite;
import sqlite.kripton93.Test93Suite;
import sqlite.kripton96.Test96Suite;
import sqlite.pagedResult.TestPagedResultSuite;
import sqlite.quickstart.TestQuickstartSuite;
import sqlite.test01.Test01Suite;
import sqlite.test02.Test02Suite;
import sqlite.test02.TestDao01;
import sqlite.test03.Test03;
import sqlite.test03.Test03Suite;
import sqlite.test05firt_aid.Test05Suite;
import sqlite.test05firt_aid.TestFirstAid;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
			Example01Suite.class,
			Example02Suite.class,
			Test01Suite.class, 
			Test02Suite.class,
			Test03Suite.class, 
			Test05Suite.class,
			Test33Suite.class,
			Test38Suite.class,
			Test40Suite.class,
			Test41Suite.class,
			Test48Suite.class,
			Test49Suite.class,
			Test51Suite.class,
			Test56Suite.class,
			Test58Suite.class,
			Test60Suite.class,
			Test62Suite.class,
			Test63Suite.class,
			Test64Suite.class,
			Test84Suite.class,
			Test93Suite.class,
			Test96Suite.class,
			TestQuickstartSuite.class, 
			TestDao01.class, 
			Test03.class, 
			TestFirstAid.class,			
			TestForeignKeySuite.class,
			TestDynamicSuite.class,
			TestPagedResultSuite.class,
			IndexTestSuite.class,
			IncludeFieldsTestSuite.class
			})
//@formatter:on
public class SQLiteCompileTestSuite {

}

package sqlite;
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.example01.TestExample01RuntimeSuite;
import sqlite.foreignKey.TestForeignKeyRuntimeSuite;
import sqlite.kripton58.list.Test58RuntimeSuite;
import sqlite.kripton64.Test64RuntimeSuite;
import sqlite.kripton84.Test84RuntimeSuite;
import sqlite.quickstart.TestQuickstartRuntimeSuite;

@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({ 	
	Test64RuntimeSuite.class,
	Test84RuntimeSuite.class,
	TestForeignKeyRuntimeSuite.class,
	TestExample01RuntimeSuite.class,
	Test58RuntimeSuite.class,
	TestQuickstartRuntimeSuite.class
	 })
// @formatter:on
public class SQLiteRuntimeTestSuite {

}

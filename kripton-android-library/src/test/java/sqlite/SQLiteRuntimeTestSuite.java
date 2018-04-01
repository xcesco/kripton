package sqlite;
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.feat.includeFields.TestIncludeFieldsRuntimeSuite;
import sqlite.feature.contentprovider.kripton35.TestContentProviderRuntime;
import sqlite.feature.dynamic.TestDynamicRuntimeSuite;
import sqlite.feature.foreignKey.TestForeignKeyRuntimeSuite;
import sqlite.feature.jql.TestFeatJQLRuntimeSuite;
import sqlite.feature.many2many.TestRuntimeMany2ManySuite;
import sqlite.feature.multithread.TestRuntimeMultithreadSuite;
import sqlite.feature.paginatedResult.TestPaginatedResultRuntimeSuite;
import sqlite.feature.schema.TestSchemaRuntimeSuite;
import sqlite.feature.speed.TestSpeedRuntime;
import sqlite.kripton209.Test209RuntimeSuite;
import sqlite.kripton58.list.Test58RuntimeSuite;
import sqlite.kripton64.Test64RuntimeSuite;
import sqlite.kripton84.Test84RuntimeSuite;
import sqlite.kripton93.Test93RuntimeSuite;
import sqlite.kripton96.Test96RuntimeSuite;
import sqlite.quickstart.TestQuickstartRuntimeSuite;
import sqlite.stack44330452.TestStack44330452RuntimeSuite;
import sqlite.stack44633883.TestStack44633883RuntimeSuite;
import sqlite.stack45184504.TestStack45184504RuntimeSuite;

@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
	// verified results
	TestPaginatedResultRuntimeSuite.class,
	TestRuntimeMultithreadSuite.class,
	TestDynamicRuntimeSuite.class,
	TestContentProviderRuntime.class,
	TestSchemaRuntimeSuite.class, 
	
	// unverified results	
	TestForeignKeyRuntimeSuite.class,
	TestIncludeFieldsRuntimeSuite.class,
	TestRuntimeMany2ManySuite.class, 
	
	Test64RuntimeSuite.class,
	Test84RuntimeSuite.class,
	Test93RuntimeSuite.class,
	Test96RuntimeSuite.class,
	
	Test58RuntimeSuite.class,
	TestQuickstartRuntimeSuite.class,
	TestFeatJQLRuntimeSuite.class,
	TestSpeedRuntime.class,
	
	TestStack45184504RuntimeSuite.class,
	TestStack44330452RuntimeSuite.class,
	TestStack44633883RuntimeSuite.class,
	
	Test209RuntimeSuite.class
	
	 })
// @formatter:on
public class SQLiteRuntimeTestSuite {

}

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
package sqlite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.example01.Example01Suite;
import sqlite.example02.Example02Suite;
import sqlite.feat.grammars.contenturi.TestUriCheckerSuite;
import sqlite.feat.grammars.jql.TestJqlCheckerSuite;
import sqlite.feature.asynctask.TestCompileMultithreadSuite;
import sqlite.feature.childselect.TestChildSelectSuite;
import sqlite.feature.columnaffinity.TestColumnAffinityTestSuite;
import sqlite.feature.contentprovider.TestContentProviderSuite;
import sqlite.feature.datasourceoptions.FeatureDataSourceOptionsTest;
import sqlite.feature.dynamic.TestDynamicSuite;
import sqlite.feature.foreignkey.TestForeignKeySuite;
import sqlite.feature.foreignkeyaction.TestForeignKeyActionSuite;
import sqlite.feature.generichierarchy.TestGenericHierarchySuite;
import sqlite.feature.globaltypeadapters.TestGlobalSqlTypeAdaptersSuite;
import sqlite.feature.includefields.IncludeFieldsTestSuite;
import sqlite.feature.indexes.IndexTestSuite;
import sqlite.feature.javadoc.TestJavadocFeatureSuite;
import sqlite.feature.join.TestJoinFeatureSuite;
import sqlite.feature.jql.TestJQLFeatureSuite;
import sqlite.feature.livedata.TestLiveDataSuite;
import sqlite.feature.many2many.TestCompileMany2ManySuite;
import sqlite.feature.paginatedresult.TestPaginatedResultSuite;
import sqlite.feature.performance.TestPerformanceSuite;
import sqlite.feature.relations.TestRelationSuite;
import sqlite.feature.rx.TestRXSuite;
import sqlite.feature.schema.TestSchemaSuite;
import sqlite.feature.speed.TestCompileSpeed;
import sqlite.feature.typeadapter.TestTypeAdapterSuite;
import sqlite.feature.typeadapter.kripton180.Test180Suite;
import sqlite.kripton111.TestCompile111;
import sqlite.kripton186.Test186Suite;
import sqlite.kripton205.Test205Suite;
import sqlite.kripton209.Test209Suite;
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
import sqlite.kripton62.Test62Compile;
import sqlite.kripton62.Test62Suite;
import sqlite.kripton63.Test63Compile;
import sqlite.kripton63.Test63Suite;
import sqlite.kripton64.Test64Suite;
import sqlite.kripton84.Test84Suite;
import sqlite.kripton93.Test93Suite;
import sqlite.kripton96.Test96Suite;
import sqlite.quickstart.TestQuickstartSuite;
import sqlite.samples.TestSamplesSuite;
import sqlite.select.TestSelectSuite;
import sqlite.stack44330452.TestStack44330452Suite;
import sqlite.stack44633883.TestStack44633883Suite;
import sqlite.stack45184504.TestStack45184504Suite;
import sqlite.test01.Test01Suite;
import sqlite.test02.Test02Suite;
import sqlite.test02.TestDao01;
import sqlite.test03.Test03;
import sqlite.test03.Test03Suite;
import sqlite.test04.v1.Test04;
import sqlite.test05firt_aid.Test05Suite;
import sqlite.test05firt_aid.TestFirstAid;

/**
 * The Class SQLiteCompileTestSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{
			// features			
			TestContentProviderSuite.class,
			TestPaginatedResultSuite.class,
			TestCompileMultithreadSuite.class,
			TestGenericHierarchySuite.class,
			TestJavadocFeatureSuite.class,
			TestDynamicSuite.class,
			TestSchemaSuite.class,
			IndexTestSuite.class,			
			TestTypeAdapterSuite.class,			
			TestCompileMany2ManySuite.class,
			TestGlobalSqlTypeAdaptersSuite.class,
			TestForeignKeyActionSuite.class, 
			TestRXSuite.class,
			TestCompileSpeed.class,
			TestLiveDataSuite.class,
			TestJoinFeatureSuite.class,
			TestRelationSuite.class,
			TestColumnAffinityTestSuite.class,
			TestChildSelectSuite.class,
			
			// bug-fix			
			Example01Suite.class,
			Example02Suite.class,
			TestCompile111.class,
			TestStack44330452Suite.class,
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
			Test04.class,
			TestFirstAid.class,			
			TestForeignKeySuite.class,
			TestUriCheckerSuite.class,
			TestJqlCheckerSuite.class,
			Test186Suite.class,
			Test180Suite.class,
			TestStack44633883Suite.class,
			TestStack45184504Suite.class,	
			Test205Suite.class,
			FeatureDataSourceOptionsTest.class,
			
			TestSamplesSuite.class,
						
			TestPerformanceSuite.class,
			TestSelectSuite.class,
			IncludeFieldsTestSuite.class,						
			TestJQLFeatureSuite.class,
			Test62Compile.class,
			Test63Compile.class,
			Test209Suite.class			
			})
//@formatter:on
public class SQLiteCompileTestSuite {

}

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
package all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.bindenum.TestRuntimeEnumSuite;
import bind.directmap.TestRuntimeDirectMapSuite;
import bind.feature.generichierarchy.TestRuntimeHierarchySuite;
import bind.feature.git43.TestRuntimeGit43;
import bind.feature.namespace.TestRuntimeNamespaceSuite;
import bind.feature.typeadapter.kripton87.TestRuntime87Suite;
import bind.kripton42faster.TestRuntime42Suite;
import bind.kripton70.TestRuntime70Suite;
import bind.kripton71list.TestRuntime71Suite;
import bind.kripton72.TestRuntime72Suite;
import bind.kripton73array.TestRuntime73Suite;
import bind.kripton74map.TestRuntime74Suite;
import bind.kripton75bytearray.TestRuntime75Suite;
import bind.kripton78.TestRuntime78Suite;
import bind.kripton80contextcollection.TestRuntime80Suite;
import bind.kripton81morecoveragetests.TestRuntime81Suite;

/**
 * The Class KriptonRuntimeSuiteTest.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestRuntimeGit43.class,
		TestRuntime42Suite.class,
		TestRuntime70Suite.class,
		TestRuntime71Suite.class,
		TestRuntime72Suite.class,
		TestRuntime73Suite.class,
		TestRuntime74Suite.class,
		TestRuntime75Suite.class,
		TestRuntime78Suite.class,
		TestRuntime80Suite.class,
		TestRuntime81Suite.class,
		TestRuntime87Suite.class,
		TestRuntimeEnumSuite.class,
		TestRuntimeHierarchySuite.class,
		TestRuntimeDirectMapSuite.class,
		
		
		TestRuntimeNamespaceSuite.class
		 })
//@formatter:on
public class KriptonRuntimeSuiteTest {

}

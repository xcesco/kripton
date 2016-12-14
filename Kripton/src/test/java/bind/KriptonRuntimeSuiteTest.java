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
package bind;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.kripton42faster.TestRuntime42Suite;
import bind.kripton70.TestRuntime70Suite;
import bind.kripton71List.TestRuntime71Suite;
import bind.kripton72.TestRuntime72Suite;
import bind.kripton73Array.TestRuntime73Suite;
import bind.kripton74Map.TestRuntime74Suite;
import bind.kripton75ByteArray.TestRuntime75Suite;
import bind.kripton78.TestRuntime78Suite;
import bind.kripton80ContextCollection.TestRuntime80Suite;
import bind.kripton81MoreCoverageTests.TestRuntime81Suite;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestRuntime42Suite.class,
		TestRuntime70Suite.class,
		TestRuntime71Suite.class,
		TestRuntime72Suite.class,
		TestRuntime73Suite.class,
		TestRuntime74Suite.class,
		TestRuntime75Suite.class,
		TestRuntime78Suite.class,
		TestRuntime80Suite.class,
		TestRuntime81Suite.class
		 })
//@formatter:on
public class KriptonRuntimeSuiteTest {

}

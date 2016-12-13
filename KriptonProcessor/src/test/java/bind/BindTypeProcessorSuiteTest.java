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

import bind.kripton42faster.TestCompile42Suite;
import bind.kripton70.TestCompile70Suite;
import bind.kripton71List.TestCompile71Suite;
import bind.kripton72.TestCompile72Suite;
import bind.kripton73Array.TestCompile73Suite;
import bind.kripton74Map.TestCompile74Suite;
import bind.kripton75ByteArray.TestCompile75Suite;
import bind.kripton76Errors.TestCompile76Suite;
import bind.kripton77.Test77;
import bind.kripton78.TestCompile78Suite;
import bind.kripton80ContextCollection.TestCompile80Suite;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{
		TestCompile42Suite.class,
		TestCompile70Suite.class,
		TestCompile71Suite.class,
		TestCompile72Suite.class,
		TestCompile73Suite.class,
		TestCompile74Suite.class,
		TestCompile75Suite.class,
		TestCompile76Suite.class,
		Test77.class,
		TestCompile78Suite.class,
		TestCompile80Suite.class
		 })
//@formatter:on
public class BindTypeProcessorSuiteTest {

}

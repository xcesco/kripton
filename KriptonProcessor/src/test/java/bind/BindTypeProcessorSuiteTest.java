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

import bind.kripton70.TestKripton70;
import bind.kripton71.TestKripton71;
import bind.kripton72.TestKripton72;
import bind.kripton73.TestKripton73;
import bind.kripton74Map.TestKripton74;
import bind.kripton75.TestKripton75;
import bind.kripton76.TestKripton76Attribute;
import bind.kripton76.TestKripton76Value;
import bind.kripton78.TestKripton78;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestKripton70.class,
		TestKripton71.class,
		TestKripton72.class,
		TestKripton73.class,
		TestKripton74.class,
		TestKripton75.class,
		TestKripton76Value.class,
		TestKripton76Attribute.class,
		TestKripton78.class
		 })
//@formatter:on
public class BindTypeProcessorSuiteTest {

}

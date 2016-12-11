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

import bind.kripton70.Test70Suite;
import bind.kripton71List.Test71;
import bind.kripton72.Test72;
import bind.kripton73.Test73;
import bind.kripton74Map.Test74;
import bind.kripton75.Test75;
import bind.kripton76.Test76Suite;
import bind.kripton77.Test77;
import bind.kripton78.Test78;
import bind.kripton80.Test80Suite;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Test70Suite.class,
		Test71.class,
		Test72.class,
		Test73.class,
		Test74.class,
		Test75.class,
		Test76Suite.class,
		Test77.class,
		Test78.class,
		Test80Suite.class
		 })
//@formatter:on
public class BindTypeProcessorSuiteTest {

}

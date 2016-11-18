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
package base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import kripton45.TestKripton45;
import kripton47.TestKripton47;
import kripton50.TestKripton50;
import kripton62.TestKripton62;
import kripton63.TestKripton63;
import test05firt_aid.TestFirstAid;


@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
			TestKripton45.class, TestKripton47.class,
			TestKripton50.class,
			TestKripton62.class,
			TestKripton63.class,
			TestFirstAid.class })
//@formatter:on
public class SharedPreferenceProcessorSuiteTest {

}

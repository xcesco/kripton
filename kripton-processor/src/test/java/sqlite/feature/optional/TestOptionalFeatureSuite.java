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
package sqlite.feature.optional;

import base.BaseProcessorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The Class TestJoinFeatureSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
        {
                TestCompileOptionalCase1.class,
                TestCompileOptionalCase2.class,
                TestCompileOptionalCase3.class,
                TestCompileOptionalCase4.class,
                TestCompileOptionalCase5.class,
                TestCompileOptionalCase6.class
        })
//@formatter:on
public class TestOptionalFeatureSuite extends BaseProcessorTest {

}

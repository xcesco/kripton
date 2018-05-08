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
package bind.feature.namespace;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.feature.namespace.case1.TestCompileCase1;
import bind.feature.namespace.case2.TestCompileCase2;
import bind.feature.namespace.case3.TestCompileCase3;
import bind.feature.namespace.case4.TestCompileCase4;
import bind.feature.namespace.error1.TestNamespaceError1;
import bind.feature.namespace.error2.TestNamespaceError2;

/**
 * The Class BindCompileTestSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
{//@formatter:off
		
	TestCompileCase1.class,
	TestCompileCase2.class,
	TestCompileCase3.class,
	TestCompileCase4.class,
	
	TestNamespaceError1.class,
	TestNamespaceError2.class
		 })
//@formatter:on
public class FeatureNamespaceTestSuite {

}

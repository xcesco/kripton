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
package bind.kripton81MoreCoverageTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.bind.transform.Test81DCompile;
import com.abubusoft.kripton.processor.sharedprefs.transform.Test81ECompile;
import com.abubusoft.kripton.processor.sqlite.transform.Test81FCompile;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton81ExceptionCoverage.Test81ExceptionCompile;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Test81ACompile.class,
		Test81BCompile.class,
		Test81CCompile.class,
		Test81DCompile.class,
		Test81ECompile.class,
		Test81FCompile.class,
		Test81GHILMNOPCompile.class,
		Test81VCompile.class,
		Test81ExceptionCompile.class
		 })
//@formatter:on
public class Test81CompileSuite extends AbstractBindTypeProcessorTest {

}

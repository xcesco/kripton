/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.kripton81morecoveragetests;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton81morecoveragetests.Bean81Enum;
import bind.kripton81morecoveragetests.Bean81G;
import bind.kripton81morecoveragetests.Bean81H;
import bind.kripton81morecoveragetests.Bean81I;
import bind.kripton81morecoveragetests.Bean81L;
import bind.kripton81morecoveragetests.Bean81M;
import bind.kripton81morecoveragetests.Bean81N;
import bind.kripton81morecoveragetests.Bean81O;
import bind.kripton81morecoveragetests.Bean81P;
import bind.kripton81morecoveragetests.Bean81R;
import bind.kripton81morecoveragetests.Bean81S;
import bind.kripton81morecoveragetests.Bean81T;
import bind.kripton81morecoveragetests.Bean81U;


/**
 * The Class Test81GHILMNOPCompile.
 */
public class Test81GHILMNOPCompile extends AbstractBindTypeProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean81G.class, Bean81H.class, Bean81I.class, Bean81L.class, Bean81M.class, Bean81N.class, Bean81O.class, Bean81P.class,
				Bean81U.class, Bean81R.class, Bean81S.class, Bean81T.class, 
				Bean81Enum.class);
	}
}

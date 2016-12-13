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
package shared.kripton47;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import shared.AbstractBindSharedPreferenceProcessorTest;

@RunWith(JUnit4.class)
public class Test47Compile extends AbstractBindSharedPreferenceProcessorTest {

	@Test
	public void testCompileApp() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(App47.class);
		buildSharedPreferencesProcessorTest(App47.class, UserAccessToken.class);
	}

	@Test
	public void testCompileSecurity() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(DeviceAccessToken.class, UserIdentity.class, UserAccessToken.class);
		buildSharedPreferencesProcessorTest(Security47.class, DeviceAccessToken.class, UserIdentity.class, UserAccessToken.class);
	}

}

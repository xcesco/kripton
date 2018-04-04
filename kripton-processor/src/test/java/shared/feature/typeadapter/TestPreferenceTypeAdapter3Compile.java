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
package shared.feature.typeadapter;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import shared.AbstractBindSharedPreferenceProcessorTest;
import shared.feature.typeadapter.case3.App3Preferences;
import shared.feature.typeadapter.case3.StringSetTypeAdapter;

@RunWith(JUnit4.class)
public class TestPreferenceTypeAdapter3Compile extends AbstractBindSharedPreferenceProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(App3Preferences.class, StringSetTypeAdapter.class);
	}

}

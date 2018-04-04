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


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseAndroidTest;
import shared.feature.typeadapter.case4.App4Preferences;
import shared.feature.typeadapter.case4.BindApp4Preferences;

public class TestTypeAdapterCase4Runtime extends BaseAndroidTest {
	
	@Test
	public void testAppRun()
	{
		BindApp4Preferences sp=BindApp4Preferences.instance();		
		sp.edit().putFieldStringPrivate(null).commit();		
		sp.refresh();		
		assertTrue(sp.fieldStringPrivate().size()==2);
		
		App4Preferences value=new App4Preferences();
		value.setFieldStringPrivate(null);		
		sp.write(value);		
		assertTrue(sp.fieldStringPrivate().size()==2);
	}
	
	
}

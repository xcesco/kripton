/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package shared.feature.typeadapter;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseAndroidTest;
import shared.feature.typeadapter.case3.App3Preferences;
import shared.feature.typeadapter.case3.BindApp3Preferences;

// TODO: Auto-generated Javadoc
/**
 * The Class TestTypeAdapterCase3Runtime.
 */
public class TestTypeAdapterCase3Runtime extends BaseAndroidTest {
	
	/**
	 * Test app run.
	 */
	@Test
	public void testAppRun()
	{
		BindApp3Preferences sp=BindApp3Preferences.getInstance();		
		sp.edit().putFieldStringPrivate(null).commit();		
		sp.refresh();		
		assertTrue(sp.getFieldStringPrivate().equals("1|2"));
		
		App3Preferences value=new App3Preferences();
		value.setFieldStringPrivate(null);		
		sp.write(value);		
		assertTrue(sp.getFieldStringPrivate().equals("1|2"));
	}
	
	
}

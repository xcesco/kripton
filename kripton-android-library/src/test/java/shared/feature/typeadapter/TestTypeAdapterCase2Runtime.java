/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
import shared.feature.typeadapter.case2.App2Preferences;
import shared.feature.typeadapter.case2.BindApp2Preferences;

// TODO: Auto-generated Javadoc
/**
 * The Class TestTypeAdapterCase2Runtime.
 */
public class TestTypeAdapterCase2Runtime extends BaseAndroidTest {
	
	/**
	 * Test app run.
	 */
	@Test
	public void testAppRun()
	{
		BindApp2Preferences sp=BindApp2Preferences.instance();		
		sp.edit().putField2(1).commit();		
		sp.refresh();		
		assertTrue(sp.field2()==1);
		
		App2Preferences value=new App2Preferences();
		value.field2=26;		
		sp.write(value);		
		assertTrue(sp.field2()==1);
	}
	
	
}

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
package shared.feature.immutable.livedata;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;

import androidx.lifecycle.Observer;
import base.BaseAndroidTest;

/**
 * The Class TestTypeAdapterCase2Runtime.
 */
public class TesLiveDataCase1Runtime extends BaseAndroidTest {
	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();
	
	
	/**
	 * Test app run.
	 * @throws InterruptedException 
	 */
	@Test
	public void testAppRun() throws InterruptedException
	{
		BindAppPreferences sp=BindAppPreferences.getInstance();
		sp.getDescriptionAsLiveData().observeForever(new Observer<String>() {
			
			@Override
			public void onChanged(String t) {
				Logger.info("getDescriptionAsLiveData  "+t);
				
			}
		});		
				
		sp.edit().putDescription("ciao").commit();
						
		sp.edit().putName("name").commit();
						
		sp.edit().putDescription("hello").commit();
							
		sp.getNameAsLiveData().observeForever(new Observer<String>() {
			
			@Override
			public void onChanged(String t) {
				Logger.info("getNameAsLiveData  "+t);
				
			}
		});		
	}
	
	
}

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
package shared.feature.rx;


import org.junit.Test;
import org.robolectric.shadows.ShadowLooper;

import com.abubusoft.kripton.android.Logger;

import android.arch.lifecycle.Observer;
import base.BaseAndroidTest;
import shared.feature.rx.case1.BindAppPreferences;

/**
 * The Class TestTypeAdapterCase2Runtime.
 */
public class TesLiveDataCase1Runtime extends BaseAndroidTest {
	
	/**
	 * Test app run.
	 * @throws InterruptedException 
	 */
	@Test
	public void testAppRun() throws InterruptedException
	{		
		BindAppPreferences sp=BindAppPreferences.instance();
		sp.getDescriptionAsLiveData().observeForever(new Observer<String>() {
			
			
			@Override
			public void onChanged(String t) {
				Logger.info("getDescriptionAsLiveData  "+t);
				
			}
		});		
				
		sp.edit().putDescription("ciao").commit();
		System.out.println(ShadowLooper.getMainLooper());
		  ShadowLooper.runUiThreadTasks();
		  ShadowLooper.runUiThreadTasks();
		//ShadowLooper.runUiThreadTasks();
	   
	   //((ShadowLooper) ShadowExtractor.extract(thread.getLooper())).idle();
				
		//assertTrue(sp.getField2()==1);
		//Thread.currentThread().sleep(60000);
	}
	
	
}

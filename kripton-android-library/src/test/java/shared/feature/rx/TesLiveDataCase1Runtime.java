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

import com.abubusoft.kripton.android.Logger;

import android.arch.lifecycle.Observer;
import base.BaseAndroidTest;
import io.reactivex.functions.Consumer;
import shared.feature.rx.case1.AppPreferences;
import shared.feature.rx.case1.BindAppPreferences;

/**
 * The Class TestTypeAdapterCase2Runtime.
 */
public class TesLiveDataCase1Runtime extends BaseAndroidTest {
	
	/**
	 * Test app run.
	 */
	@Test
	public void testAppRun()
	{
		BindAppPreferences sp=BindAppPreferences.instance();
		sp.getDescriptionAsLiveData().observeForever(new Observer<String>() {
			
			@Override
			public void onChanged(String t) {
				Logger.info("getDescriptionAsLiveData  "+t);
				
			}
		});
		sp.getDescriptionAsObservable().subscribe(new Consumer<String>() {
			@Override
			public void accept(String result) throws Exception {
				Logger.info("getDescriptionAsObservable "+result);
			}
		});
				
		sp.edit().putDescription("ciao").commit();
				
		//assertTrue(sp.getField2()==1);
	}
	
	
}

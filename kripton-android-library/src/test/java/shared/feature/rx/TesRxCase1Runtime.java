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
package shared.feature.rx;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import io.reactivex.functions.Consumer;
import shared.feature.rx.case1.AppPreferences;
import shared.feature.rx.case1.BindAppPreferences;

/**
 * The Class TestTypeAdapterCase2Runtime.
 */
public class TesRxCase1Runtime extends BaseAndroidTest {
	
	/**
	 * Test app run.
	 */
	@Test
	public void testAppRun()
	{
		final One<Integer> counter=new One<>(0);
		final BindAppPreferences sp=BindAppPreferences.getInstance();
		sp.edit().putDescription("start").commit();
		
		sp.getDescriptionAsObservable().subscribe(new Consumer<String>() {
			@Override
			public void accept(String result) throws Exception {
				Logger.info("modify "+result);
				
				if (counter.value0==0)
					assertTrue(result.equals("start"));
				if (counter.value0>=1)
					assertTrue(result.equals("end"));
				
				counter.value0=counter.value0+1;
			}
		});
				
		sp.edit().putDescription("end").putName("name").commit();
				
		//
	}
	
	
}

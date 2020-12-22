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
package shared.feature.typeadapter.case1;

import java.util.HashSet;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

import shared.feature.typeadapter.case2.IntTypeAdapter;


/**
 * The Class App1WithErrorPreferences.
 */
@BindSharedPreferences
public class App1WithErrorPreferences {

	/** The value set. */
	@BindPreference
	public HashSet<String> valueSet;
	
	/*
	@BindPreferenceAdapter(adapter=SampleTypeAdapter.class)
	@BindPreference
	public String password;*/
	
	/** The wrong. */
	@BindPreferenceAdapter(adapter=IntTypeAdapter.class)
	@BindPreference
	public String wrong;
		
}

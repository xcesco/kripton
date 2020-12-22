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
/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;


/**
 * <p>Base class to generated Shared Preference classes.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class AbstractSharedPreference {
	
	/**
	 * Instantiates a new abstract shared preference.
	 */
	protected AbstractSharedPreference() {
	}
	
	/** The prefs. */
	protected SharedPreferences prefs;
		
	/**
	 * Register on shared preference change listener.
	 *
	 * @param listener the listener
	 */
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	/**
	 * Unregister on shared preference change listener.
	 *
	 * @param listener the listener
	 */
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

	/**
	 * The Class AbstractEditor.
	 */
	public class AbstractEditor
	{
		
		/**
		 * Instantiates a new abstract editor.
		 */
		protected AbstractEditor()
		{
			editor=prefs.edit();
		}
		
		/** The editor. */
		protected final SharedPreferences.Editor editor;
		
		/**
		 * Commit.
		 */
		public void commit()
		{
			editor.commit();
		}
		
		/**
		 * apply
		 */
		public void apply() {
			editor.apply();
		}
		
		
	}

}

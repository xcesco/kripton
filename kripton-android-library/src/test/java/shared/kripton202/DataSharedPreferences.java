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
package shared.kripton202;

import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;


/**
 * The Class DataSharedPreferences.
 */
@BindSharedPreferences
public class DataSharedPreferences {

	/** The example multi list. */
	public Set<String> exampleMultiList;
	
	/** The notification new message ringtone. */
	public String notificationNewMessageRingtone;
	
	/** The notifications new message vibrate. */
	public boolean notificationsNewMessageVibrate;
	
	/** The sync frequency. */
	public String syncFrequency;
}

/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationSettings.
 */
@BindType
public class ApplicationSettings {

	/**
	 * Instantiates a new application settings.
	 */
	public ApplicationSettings() {
		resetConfig = false;
		gestureListenerClazz = "org.abubu.argon.android.listener.ArgonGestureDefaultListenerImpl";
		upgradePolicyClazz = "org.abubu.elio.application.ApplicationUpgradePolicyImpl";
	}

	/** The activity clazz. */
	@Bind("applicationActivityClazz")
	public String activityClazz;

	/** The clazz. */
	@Bind("applicationClazz")
	public String clazz;

	/** The config clazz. */
	@Bind("applicationConfigClazz")
	public String configClazz;

	/** The reset config. */
	@Bind("applicationResetConfig")
	public Boolean resetConfig;

	/** The gesture listener clazz. */
	@Bind("applicationGestureListenerClazz")
	public String gestureListenerClazz;

	/** The upgrade policy clazz. */
	@Bind("applicationUpgradePolicyClazz")
	public String upgradePolicyClazz;

	/** The splash screen timeout. */
	@Bind("applicationSplashScreenTimeout")
	public int splashScreenTimeout = 3000;

	/** The startup task clazz. */
	@Bind("applicationStartupTaskClazz")
	public String startupTaskClazz;

	/** The mode. */
	@Bind("applicationMode")
	public ModeType mode;

}

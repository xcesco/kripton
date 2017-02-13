package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ApplicationSettings {

	public ApplicationSettings() {
		resetConfig = false;
		gestureListenerClazz = "org.abubu.argon.android.listener.ArgonGestureDefaultListenerImpl";
		upgradePolicyClazz = "org.abubu.elio.application.ApplicationUpgradePolicyImpl";
	}

	@Bind("applicationActivityClazz")
	public String activityClazz;

	@Bind("applicationClazz")
	public String clazz;

	@Bind("applicationConfigClazz")
	public String configClazz;

	@Bind("applicationResetConfig")
	public Boolean resetConfig;

	@Bind("applicationGestureListenerClazz")
	public String gestureListenerClazz;

	@Bind("applicationUpgradePolicyClazz")
	public String upgradePolicyClazz;

	@Bind("applicationSplashScreenTimeout")
	public int splashScreenTimeout = 3000;

	@Bind("applicationStartupTaskClazz")
	public String startupTaskClazz;

	@Bind("applicationMode")
	public ModeType mode;

}

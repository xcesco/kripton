package shared.kripton202;

import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

@BindSharedPreferences
public class DataSharedPreferences {

	public Set<String> exampleMultiList;
	
	public String notificationNewMessageRingtone;
	
	public boolean notificationsNewMessageVibrate;
	
	public String syncFrequency;
}

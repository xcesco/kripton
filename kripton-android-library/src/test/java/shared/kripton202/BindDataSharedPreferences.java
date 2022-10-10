package shared.kripton202;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.util.Set;

/**
 * This class is the shared preference binder defined for DataSharedPreferences
 *
 * @see DataSharedPreferences
 */
public class BindDataSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindDataSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final DataSharedPreferences defaultBean;

  /**
   * constructor
   */
  private BindDataSharedPreferences() {
    createPrefs();
    defaultBean=new DataSharedPreferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * create prefs
   */
  private void createPrefs() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
  }

  /**
   * force to refresh values
   */
  public BindDataSharedPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    DataSharedPreferences bean=new DataSharedPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public DataSharedPreferences read() {
    DataSharedPreferences bean=new DataSharedPreferences();
    bean.syncFrequency=prefs.getString("sync_frequency", defaultBean.syncFrequency);
    bean.notificationsNewMessageVibrate=(boolean)prefs.getBoolean("notifications_new_message_vibrate", (boolean)defaultBean.notificationsNewMessageVibrate);
     {
      Set<String> temp=prefs.getStringSet("example_multi_list", defaultBean.exampleMultiList);
      bean.exampleMultiList=temp;
    }

    bean.notificationNewMessageRingtone=prefs.getString("notification_new_message_ringtone", defaultBean.notificationNewMessageRingtone);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(DataSharedPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("sync_frequency",bean.syncFrequency);

    editor.putBoolean("notifications_new_message_vibrate",(boolean)bean.notificationsNewMessageVibrate);

    editor.putStringSet("example_multi_list",bean.exampleMultiList);

    editor.putString("notification_new_message_ringtone",bean.notificationNewMessageRingtone);


    editor.commit();
  }

  /**
   * reads property <code>syncFrequency</code> from shared pref with key <code>sync_frequency</code>
   *
   * @return property syncFrequency value
   */
  public String getSyncFrequency() {
    return prefs.getString("sync_frequency", defaultBean.syncFrequency);}

  /**
   * reads property <code>notificationsNewMessageVibrate</code> from shared pref with key <code>notifications_new_message_vibrate</code>
   *
   * @return property notificationsNewMessageVibrate value
   */
  public boolean getNotificationsNewMessageVibrate() {
    return (boolean)prefs.getBoolean("notifications_new_message_vibrate", (boolean)defaultBean.notificationsNewMessageVibrate);}

  /**
   * reads property <code>exampleMultiList</code> from shared pref with key <code>example_multi_list</code>
   *
   * @return property exampleMultiList value
   */
  public Set<String> getExampleMultiList() {
    Set<String> temp=prefs.getStringSet("example_multi_list", defaultBean.exampleMultiList);
    return temp;
  }

  /**
   * reads property <code>notificationNewMessageRingtone</code> from shared pref with key <code>notification_new_message_ringtone</code>
   *
   * @return property notificationNewMessageRingtone value
   */
  public String getNotificationNewMessageRingtone() {
    return prefs.getString("notification_new_message_ringtone", defaultBean.notificationNewMessageRingtone);}

  /**
   * get instance of shared preferences
   */
  public static synchronized BindDataSharedPreferences getInstance() {
    if (instance==null) {
      instance=new BindDataSharedPreferences();
      // read and write instance to sync with default values
      instance.write(instance.read());
    }
    return instance;
  }

  /**
   * editor class for shared preferences
   */
  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    /**
     * modifier for property syncFrequency
     */
    public BindEditor putSyncFrequency(String value) {
      editor.putString("sync_frequency",value);

      return this;
    }

    /**
     * remove property syncFrequency
     */
    public BindEditor removeSyncFrequency() {
      editor.remove("sync_frequency");
      return this;
    }

    /**
     * modifier for property notificationsNewMessageVibrate
     */
    public BindEditor putNotificationsNewMessageVibrate(boolean value) {
      editor.putBoolean("notifications_new_message_vibrate",(boolean)value);

      return this;
    }

    /**
     * remove property notificationsNewMessageVibrate
     */
    public BindEditor removeNotificationsNewMessageVibrate() {
      editor.remove("notifications_new_message_vibrate");
      return this;
    }

    /**
     * modifier for property exampleMultiList
     */
    public BindEditor putExampleMultiList(Set<String> value) {
      editor.putStringSet("example_multi_list",value);

      return this;
    }

    /**
     * remove property exampleMultiList
     */
    public BindEditor removeExampleMultiList() {
      editor.remove("example_multi_list");
      return this;
    }

    /**
     * modifier for property notificationNewMessageRingtone
     */
    public BindEditor putNotificationNewMessageRingtone(String value) {
      editor.putString("notification_new_message_ringtone",value);

      return this;
    }

    /**
     * remove property notificationNewMessageRingtone
     */
    public BindEditor removeNotificationNewMessageRingtone() {
      editor.remove("notification_new_message_ringtone");
      return this;
    }

    /**
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}

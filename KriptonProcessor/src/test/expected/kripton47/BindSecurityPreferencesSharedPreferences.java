package kripton47;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.lang.String;

/**
 * This class is the shared preference binder defined for SecurityPreferences
 *
 * @see SecurityPreferences
 */
public class BindSecurityPreferencesSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindSecurityPreferencesSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final SecurityPreferences defaultBean;

  /**
   * constructor
   */
  private BindSecurityPreferencesSharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new SecurityPreferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    SecurityPreferences bean=new SecurityPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public SecurityPreferences read() {
    SecurityPreferences bean=new SecurityPreferences();
    bean.fcmId=prefs.getString("fcmId", bean.fcmId);
    bean.authorizationToken=(prefs.getString("authorizationToken", null)!=null) ? (DeviceAccessToken)readObj(prefs.getString("authorizationToken", null), DeviceAccessToken.class): null;
    bean.deviceUid=prefs.getString("deviceUid", bean.deviceUid);
    bean.userIdentity=(prefs.getString("userIdentity", null)!=null) ? (UserIdentity)readObj(prefs.getString("userIdentity", null), UserIdentity.class): null;

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(SecurityPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("fcmId",bean.fcmId );
    if (bean.authorizationToken!=null) editor.putString("authorizationToken",writeObj(bean.authorizationToken)); else editor.putString("authorizationToken", null);
    editor.putString("deviceUid",bean.deviceUid );
    if (bean.userIdentity!=null) editor.putString("userIdentity",writeObj(bean.userIdentity)); else editor.putString("userIdentity", null);

    editor.commit();
  }

  /**
   * read property fcmId
   *
   * @return property fcmId value
   */
  public String fcmId() {
    return prefs.getString("fcmId", defaultBean.fcmId);
  }

  /**
   * read property authorizationToken
   *
   * @return property authorizationToken value
   */
  public DeviceAccessToken authorizationToken() {
    return (prefs.getString("authorizationToken", null)!=null) ? (DeviceAccessToken)readObj(prefs.getString("authorizationToken", null), DeviceAccessToken.class): null;
  }

  /**
   * read property deviceUid
   *
   * @return property deviceUid value
   */
  public String deviceUid() {
    return prefs.getString("deviceUid", defaultBean.deviceUid);
  }

  /**
   * read property userIdentity
   *
   * @return property userIdentity value
   */
  public UserIdentity userIdentity() {
    return (prefs.getString("userIdentity", null)!=null) ? (UserIdentity)readObj(prefs.getString("userIdentity", null), UserIdentity.class): null;
  }

  /**
   * get instance of shared preferences
   */
  public static BindSecurityPreferencesSharedPreferences instance() {
    if (instance==null) {
      instance=new BindSecurityPreferencesSharedPreferences();
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
     * modifier for property fcmId
     */
    public BindEditor putFcmId(String value) {
      editor.putString("fcmId",value);
      return this;
    }

    /**
     * modifier for property authorizationToken
     */
    public BindEditor putAuthorizationToken(DeviceAccessToken value) {
      if (value!=null) editor.putString("authorizationToken",writeObj(value)); else editor.putString("authorizationToken", null);
      return this;
    }

    /**
     * modifier for property deviceUid
     */
    public BindEditor putDeviceUid(String value) {
      editor.putString("deviceUid",value);
      return this;
    }

    /**
     * modifier for property userIdentity
     */
    public BindEditor putUserIdentity(UserIdentity value) {
      if (value!=null) editor.putString("userIdentity",writeObj(value)); else editor.putString("userIdentity", null);
      return this;
    }
  }
}

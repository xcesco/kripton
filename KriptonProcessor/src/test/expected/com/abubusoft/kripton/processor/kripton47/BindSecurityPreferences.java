package com.abubusoft.kripton.processor.kripton47;

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
public class BindSecurityPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindSecurityPreferences instance;

  /**
   * working instance of bean
   */
  private final SecurityPreferences defaultBean;

  /**
   * constructor
   */
  private BindSecurityPreferences() {
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
    bean.authorizationToken=(com.abubusoft.kripton.processor.kripton47.DeviceAccessToken)readObj(prefs.getString("authorizationToken", null), com.abubusoft.kripton.processor.kripton47.DeviceAccessToken.class);
    if (bean.authorizationToken==null)  {
      bean.authorizationToken=defaultBean.authorizationToken;
    }
    bean.deviceUid=prefs.getString("deviceUid", bean.deviceUid);
    bean.userIdentity=(com.abubusoft.kripton.processor.kripton47.UserIdentity)readObj(prefs.getString("userIdentity", null), com.abubusoft.kripton.processor.kripton47.UserIdentity.class);
    if (bean.userIdentity==null)  {
      bean.userIdentity=defaultBean.userIdentity;
    }

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(SecurityPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("fcmId", bean.fcmId);
    editor.putString("authorizationToken", writeObj(bean.authorizationToken));
    editor.putString("deviceUid", bean.deviceUid);
    editor.putString("userIdentity", writeObj(bean.userIdentity));

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
    com.abubusoft.kripton.processor.kripton47.DeviceAccessToken temp=(com.abubusoft.kripton.processor.kripton47.DeviceAccessToken)readObj(prefs.getString("authorizationToken", null), com.abubusoft.kripton.processor.kripton47.DeviceAccessToken.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.authorizationToken;
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
    com.abubusoft.kripton.processor.kripton47.UserIdentity temp=(com.abubusoft.kripton.processor.kripton47.UserIdentity)readObj(prefs.getString("userIdentity", null), com.abubusoft.kripton.processor.kripton47.UserIdentity.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.userIdentity;
  }

  /**
   * get instance of shared preferences
   */
  public static BindSecurityPreferences instance() {
    if (instance==null) {
      instance=new BindSecurityPreferences();
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
      editor.putString("fcmId", value);
      return this;
    }

    /**
     * modifier for property authorizationToken
     */
    public BindEditor putAuthorizationToken(DeviceAccessToken value) {
      editor.putString("authorizationToken", writeObj(value));
      return this;
    }

    /**
     * modifier for property deviceUid
     */
    public BindEditor putDeviceUid(String value) {
      editor.putString("deviceUid", value);
      return this;
    }

    /**
     * modifier for property userIdentity
     */
    public BindEditor putUserIdentity(UserIdentity value) {
      editor.putString("userIdentity", writeObj(value));
      return this;
    }
  }
}

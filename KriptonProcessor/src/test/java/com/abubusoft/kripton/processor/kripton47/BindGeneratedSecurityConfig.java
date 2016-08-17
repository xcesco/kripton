package com.abubusoft.kripton.processor.kripton47;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.lang.String;

public class BindGeneratedSecurityConfig extends AbstractSharedPreference {
  private static BindGeneratedSecurityConfig instance;

  private final SecurityConfig defaultBean;

  private BindGeneratedSecurityConfig() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new SecurityConfig();
  }

  public BindEditor edit() {
    return new BindEditor();
  }

  public void reset() {
    SecurityConfig bean=new SecurityConfig();
    write(bean);
  }

  public SecurityConfig read() {
    SecurityConfig bean=new SecurityConfig();
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

  public void write(SecurityConfig bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("fcmId", bean.fcmId);
    editor.putString("authorizationToken", writeObj(bean.authorizationToken));
    editor.putString("deviceUid", bean.deviceUid);
    editor.putString("userIdentity", writeObj(bean.userIdentity));

    editor.commit();
  }

  public String fcmId() {
    return prefs.getString("fcmId", defaultBean.fcmId);
  }

  public DeviceAccessToken authorizationToken() {
    com.abubusoft.kripton.processor.kripton47.DeviceAccessToken temp=(com.abubusoft.kripton.processor.kripton47.DeviceAccessToken)readObj(prefs.getString("authorizationToken", null), com.abubusoft.kripton.processor.kripton47.DeviceAccessToken.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.authorizationToken;
  }

  public String deviceUid() {
    return prefs.getString("deviceUid", defaultBean.deviceUid);
  }

  public UserIdentity userIdentity() {
    com.abubusoft.kripton.processor.kripton47.UserIdentity temp=(com.abubusoft.kripton.processor.kripton47.UserIdentity)readObj(prefs.getString("userIdentity", null), com.abubusoft.kripton.processor.kripton47.UserIdentity.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.userIdentity;
  }

  /**
   *
   * instance
   */
  public static BindGeneratedSecurityConfig instance() {
    if (instance==null) {
      instance=new BindGeneratedSecurityConfig();
    }
    return instance;
  }

  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    public BindEditor putFcmId(String value) {
      editor.putString("fcmId", value);
      return this;
    }

    public BindEditor putAuthorizationToken(DeviceAccessToken value) {
      editor.putString("authorizationToken", writeObj(value));
      return this;
    }

    public BindEditor putDeviceUid(String value) {
      editor.putString("deviceUid", value);
      return this;
    }

    public BindEditor putUserIdentity(UserIdentity value) {
      editor.putString("userIdentity", writeObj(value));
      return this;
    }
  }
}

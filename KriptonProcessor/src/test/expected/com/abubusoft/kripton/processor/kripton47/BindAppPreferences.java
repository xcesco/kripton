package com.abubusoft.kripton.processor.kripton47;

import android.content.Context;
import android.content.SharedPreferences;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.lang.String;

/**
 * This class is the shared preference binder defined for AppPreferences
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
  /**
   * shared preferences name for AppPreferences
   */
  public static final String SHARED_PREFERENCE_NAME = "appPreferences";

  /**
   * instance of shared preferences
   */
  private static BindAppPreferences instance;

  /**
   * working instance of bean
   */
  private final AppPreferences defaultBean;

  /**
   * constructor
   */
  private BindAppPreferences() {
    // using name attribute of annotation @BindSharedPreferences as name
    prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    defaultBean=new AppPreferences();
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
    AppPreferences bean=new AppPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public AppPreferences read() {
    AppPreferences bean=new AppPreferences();
    bean.name=prefs.getString("name", bean.name);
    bean.userAccessToken=(com.abubusoft.kripton.processor.kripton47.UserAccessToken)readObj(prefs.getString("userAccessToken", null), com.abubusoft.kripton.processor.kripton47.UserAccessToken.class);
    if (bean.userAccessToken==null)  {
      bean.userAccessToken=defaultBean.userAccessToken;
    }

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name", bean.name);
    editor.putString("userAccessToken", writeObj(bean.userAccessToken));

    editor.commit();
  }

  /**
   * read property name
   *
   * @return property name value
   */
  public String name() {
    return prefs.getString("name", defaultBean.name);
  }

  /**
   * read property userAccessToken
   *
   * @return property userAccessToken value
   */
  public UserAccessToken userAccessToken() {
    com.abubusoft.kripton.processor.kripton47.UserAccessToken temp=(com.abubusoft.kripton.processor.kripton47.UserAccessToken)readObj(prefs.getString("userAccessToken", null), com.abubusoft.kripton.processor.kripton47.UserAccessToken.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.userAccessToken;
  }

  /**
   * get instance of shared preferences
   */
  public static BindAppPreferences instance() {
    if (instance==null) {
      instance=new BindAppPreferences();
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
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name", value);
      return this;
    }

    /**
     * modifier for property userAccessToken
     */
    public BindEditor putUserAccessToken(UserAccessToken value) {
      editor.putString("userAccessToken", writeObj(value));
      return this;
    }
  }
}

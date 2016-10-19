package com.abubusoft.kripton.tutorial;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;

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
    bean.user=(com.abubusoft.kripton.tutorial.User)readObj(prefs.getString("user", null), com.abubusoft.kripton.tutorial.User.class);
    if (bean.user==null)  {
      bean.user=defaultBean.user;
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
    editor.putString("user", writeObj(bean.user));

    editor.commit();
  }

  /**
   * read property user
   *
   * @return property user value
   */
  public User user() {
    com.abubusoft.kripton.tutorial.User temp=(com.abubusoft.kripton.tutorial.User)readObj(prefs.getString("user", null), com.abubusoft.kripton.tutorial.User.class);
    if (temp!=null)  {
      return temp;
    }
    return defaultBean.user;
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
     * modifier for property user
     */
    public BindEditor putUser(User value) {
      editor.putString("user", writeObj(value));
      return this;
    }
  }
}

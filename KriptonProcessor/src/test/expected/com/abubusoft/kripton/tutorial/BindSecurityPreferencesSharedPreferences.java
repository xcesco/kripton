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
    bean.user=(prefs.getString("user", null)!=null) ? (User)readObj(prefs.getString("user", null), User.class): null;

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(SecurityPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.user!=null) editor.putString("user",writeObj(bean.user)); else editor.putString("user", null);

    editor.commit();
  }

  /**
   * read property user
   *
   * @return property user value
   */
  public User user() {
    return (prefs.getString("user", null)!=null) ? (User)readObj(prefs.getString("user", null), User.class): null;
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
     * modifier for property user
     */
    public BindEditor putUser(User value) {
      if (value!=null) editor.putString("user",writeObj(value)); else editor.putString("user", null);
      return this;
    }
  }
}

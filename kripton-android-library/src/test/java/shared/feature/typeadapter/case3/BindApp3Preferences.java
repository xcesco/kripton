package shared.feature.typeadapter.case3;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import java.util.Set;

/**
 * This class is the shared preference binder defined for App3Preferences
 *
 * @see App3Preferences
 */
public class BindApp3Preferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindApp3Preferences instance;

  /**
   * working instance of bean
   */
  private final App3Preferences defaultBean;

  /**
   * constructor
   */
  private BindApp3Preferences() {
    createPrefs();
    defaultBean=new App3Preferences();
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
  public BindApp3Preferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    App3Preferences bean=new App3Preferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App3Preferences read() {
    App3Preferences bean=new App3Preferences();
     {
      // Use PrefsTypeAdapterUtils to convert objects
      Set<String> temp=prefs.getStringSet("field_string_public", PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(defaultBean.fieldStringPublic));
      bean.fieldStringPublic=PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toJava(temp);
    }

     {
      // Use PrefsTypeAdapterUtils to convert objects
      Set<String> temp=prefs.getStringSet("field_string_private", PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(defaultBean.getFieldStringPrivate()));
      bean.setFieldStringPrivate(PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toJava(temp));
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App3Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    // Use PrefsTypeAdapterUtils to convert objects
    editor.putStringSet("field_string_public",PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(bean.fieldStringPublic));

    // Use PrefsTypeAdapterUtils to convert objects
    editor.putStringSet("field_string_private",PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(bean.getFieldStringPrivate()));


    editor.commit();
  }

  /**
   * reads property <code>fieldStringPublic</code> from shared pref with key <code>field_string_public</code>
   *
   * @return property fieldStringPublic value
   */
  public String getFieldStringPublic() {
    // Use PrefsTypeAdapterUtils to convert objects
    Set<String> temp=prefs.getStringSet("field_string_public", PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(defaultBean.fieldStringPublic));
    return PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toJava(temp);
  }

  /**
   * reads property <code>fieldStringPrivate</code> from shared pref with key <code>field_string_private</code>
   *
   * @return property fieldStringPrivate value
   */
  public String getFieldStringPrivate() {
    // Use PrefsTypeAdapterUtils to convert objects
    Set<String> temp=prefs.getStringSet("field_string_private", PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(defaultBean.getFieldStringPrivate()));
    return PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toJava(temp);
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindApp3Preferences getInstance() {
    if (instance==null) {
      instance=new BindApp3Preferences();
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
     * modifier for property fieldStringPublic
     */
    public BindEditor putFieldStringPublic(String value) {
      // Use PrefsTypeAdapterUtils to convert objects
      editor.putStringSet("field_string_public",PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(value));

      return this;
    }

    /**
     * remove property fieldStringPublic
     */
    public BindEditor removeFieldStringPublic() {
      editor.remove("field_string_public");
      return this;
    }

    /**
     * modifier for property fieldStringPrivate
     */
    public BindEditor putFieldStringPrivate(String value) {
      // Use PrefsTypeAdapterUtils to convert objects
      editor.putStringSet("field_string_private",PrefsTypeAdapterUtils.getAdapter(StringSetTypeAdapter.class).toData(value));

      return this;
    }

    /**
     * remove property fieldStringPrivate
     */
    public BindEditor removeFieldStringPrivate() {
      editor.remove("field_string_private");
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

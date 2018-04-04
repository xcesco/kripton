package shared.feature.typeadapter.case4;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import java.util.List;
import java.util.Set;

/**
 * This class is the shared preference binder defined for App4Preferences
 *
 * @see App4Preferences
 */
public class BindApp4Preferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindApp4Preferences instance;

  /**
   * working instance of bean
   */
  private final App4Preferences defaultBean;

  /**
   * constructor
   */
  private BindApp4Preferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new App4Preferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values
   */
  public BindApp4Preferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    App4Preferences bean=new App4Preferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App4Preferences read() {
    App4Preferences bean=new App4Preferences();
     {
      // Use PrefsTypeAdapterUtils to convert objects
      Set<String> temp=prefs.getStringSet("field_string_public", PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(defaultBean.fieldStringPublic));
      bean.fieldStringPublic=PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toJava(temp);
    }

     {
      // Use PrefsTypeAdapterUtils to convert objects
      Set<String> temp=prefs.getStringSet("field_string_private", PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(defaultBean.getFieldStringPrivate()));
      bean.setFieldStringPrivate(PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toJava(temp));
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App4Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    // Use PrefsTypeAdapterUtils to convert objects
    editor.putStringSet("field_string_public",PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(bean.fieldStringPublic));

    // Use PrefsTypeAdapterUtils to convert objects
    editor.putStringSet("field_string_private",PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(bean.getFieldStringPrivate()));


    editor.commit();
  }

  /**
   * read property fieldStringPublic
   *
   * @return property fieldStringPublic value
   */
  public List<String> fieldStringPublic() {
    // Use PrefsTypeAdapterUtils to convert objects
    Set<String> temp=prefs.getStringSet("field_string_public", PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(defaultBean.fieldStringPublic));
    return PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toJava(temp);

  }

  /**
   * read property fieldStringPrivate
   *
   * @return property fieldStringPrivate value
   */
  public List<String> fieldStringPrivate() {
    // Use PrefsTypeAdapterUtils to convert objects
    Set<String> temp=prefs.getStringSet("field_string_private", PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(defaultBean.getFieldStringPrivate()));
    return PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toJava(temp);

  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindApp4Preferences instance() {
    if (instance==null) {
      instance=new BindApp4Preferences();
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
    public BindEditor putFieldStringPublic(List<String> value) {
      // Use PrefsTypeAdapterUtils to convert objects
      editor.putStringSet("field_string_public",PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(value));

      return this;
    }

    /**
     * modifier for property fieldStringPrivate
     */
    public BindEditor putFieldStringPrivate(List<String> value) {
      // Use PrefsTypeAdapterUtils to convert objects
      editor.putStringSet("field_string_private",PrefsTypeAdapterUtils.getAdapter(Set2ListTypeAdapter.class).toData(value));

      return this;
    }
  }
}

package shared.feature.typeadapter.case2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;

/**
 * This class is the shared preference binder defined for App2Preferences
 *
 * @see App2Preferences
 */
public class BindApp2Preferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindApp2Preferences instance;

  /**
   * working instance of bean
   */
  private final App2Preferences defaultBean;

  /**
   * constructor
   */
  private BindApp2Preferences() {
    createPrefs();
    defaultBean=new App2Preferences();
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
  public BindApp2Preferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    App2Preferences bean=new App2Preferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App2Preferences read() {
    App2Preferences bean=new App2Preferences();
    bean.field2=PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("field2", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.field2)));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App2Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("field2",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.field2));


    editor.commit();
  }

  /**
   * reads property <code>field2</code> from shared pref with key <code>field2</code>
   *
   * @return property field2 value
   */
  public Integer getField2() {
    return PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("field2", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.field2)));}

  /**
   * get instance of shared preferences
   */
  public static synchronized BindApp2Preferences getInstance() {
    if (instance==null) {
      instance=new BindApp2Preferences();
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
     * modifier for property field2
     */
    public BindEditor putField2(Integer value) {
      editor.putString("field2",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(value));

      return this;
    }
  }
}

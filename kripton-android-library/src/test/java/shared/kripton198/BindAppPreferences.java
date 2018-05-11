package shared.kripton198;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;

/**
 * This class is the shared preference binder defined for AppPreferences
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
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
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
    defaultBean=new AppPreferences();
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
  public BindAppPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
    return this;
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
    bean.valueFloat=prefs.getFloat("value_float", bean.valueFloat);
    bean.valueBoolean=(boolean)prefs.getBoolean("value", (boolean)bean.valueBoolean);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putFloat("value_float",bean.valueFloat);

    editor.putBoolean("value",(boolean)bean.valueBoolean);


    editor.commit();
  }

  /**
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public float valueFloat() {
    return prefs.getFloat("value_float", defaultBean.valueFloat);
  }

  /**
   * read property valueBoolean
   *
   * @return property valueBoolean value
   */
  public boolean valueBoolean() {
    return (boolean)prefs.getBoolean("value", (boolean)defaultBean.valueBoolean);
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindAppPreferences instance() {
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
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("value_float",value);

      return this;
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("value",(boolean)value);

      return this;
    }
  }
}

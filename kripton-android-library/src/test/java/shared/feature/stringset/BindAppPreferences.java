package shared.feature.stringset;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.util.HashSet;
import java.util.Set;

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
     {
      Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
      bean.valueSet=new HashSet<String>(temp);
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
    editor.putStringSet("value_set",bean.valueSet);


    editor.commit();
  }

  /**
   * read property valueSet
   *
   * @return property valueSet value
   */
  public HashSet<String> getValueSet() {
    Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
    return new HashSet<String>(temp);

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
     * modifier for property valueSet
     */
    public BindEditor putValueSet(HashSet<String> value) {
      editor.putStringSet("value_set",value);

      return this;
    }
  }
}

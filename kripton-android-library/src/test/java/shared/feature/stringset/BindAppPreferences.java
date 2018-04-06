/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package shared.feature.stringset;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for AppPreferences.
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindAppPreferences instance;

  /** working instance of bean. */
  private final AppPreferences defaultBean;

  /**
   * constructor.
   */
  private BindAppPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new AppPreferences();
  }

  /**
   * create an editor to modify shared preferences.
   *
   * @return the bind editor
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values.
   *
   * @return the bind app preferences
   */
  public BindAppPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    AppPreferences bean=new AppPreferences();
    write(bean);
  }

  /**
   * read bean entirely.
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
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putStringSet("value_set",bean.valueSet);


    editor.commit();
  }

  /**
   * read property valueSet.
   *
   * @return property valueSet value
   */
  public HashSet<String> valueSet() {
    Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
    return new HashSet<String>(temp);

  }

  /**
   * get instance of shared preferences.
   *
   * @return the bind app preferences
   */
  public static synchronized BindAppPreferences instance() {
    if (instance==null) {
      instance=new BindAppPreferences();
    }
    return instance;
  }

  /**
   * editor class for shared preferences.
   */
  public class BindEditor extends AbstractEditor {
    
    /**
     * Instantiates a new bind editor.
     */
    private BindEditor() {
    }

    /**
     * modifier for property valueSet.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueSet(HashSet<String> value) {
      editor.putStringSet("value_set",value);

      return this;
    }
  }
}

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
package shared.feature.typeadapter.case1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import java.util.HashSet;
import java.util.Set;
import shared.feature.typeadapter.case2.IntTypeAdapter;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for App1Preferences.
 *
 * @see App1Preferences
 */
public class BindApp1Preferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindApp1Preferences instance;

  /** working instance of bean. */
  private final App1Preferences defaultBean;

  /**
   * constructor.
   */
  private BindApp1Preferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new App1Preferences();
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
   * @return the bind app 1 preferences
   */
  public BindApp1Preferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    App1Preferences bean=new App1Preferences();
    write(bean);
  }

  /**
   * read bean entirely.
   *
   * @return read bean
   */
  public App1Preferences read() {
    App1Preferences bean=new App1Preferences();
     {
      Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
      bean.valueSet=new HashSet<String>(temp);
    }

    bean.right=PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("right", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.right)));

    return bean;
  }

  /**
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(App1Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putStringSet("value_set",bean.valueSet);

    editor.putString("right",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.right));


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
   * read property right.
   *
   * @return property right value
   */
  public int right() {
    return PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("right", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.right)));
  }

  /**
   * get instance of shared preferences.
   *
   * @return the bind app 1 preferences
   */
  public static synchronized BindApp1Preferences instance() {
    if (instance==null) {
      instance=new BindApp1Preferences();
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

    /**
     * modifier for property right.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putRight(int value) {
      editor.putString("right",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(value));

      return this;
    }
  }
}

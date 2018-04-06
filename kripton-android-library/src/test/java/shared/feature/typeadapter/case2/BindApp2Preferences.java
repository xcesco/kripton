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
package shared.feature.typeadapter.case2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for App2Preferences.
 *
 * @see App2Preferences
 */
public class BindApp2Preferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindApp2Preferences instance;

  /** working instance of bean. */
  private final App2Preferences defaultBean;

  /**
   * constructor.
   */
  private BindApp2Preferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new App2Preferences();
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
   * @return the bind app 2 preferences
   */
  public BindApp2Preferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    App2Preferences bean=new App2Preferences();
    write(bean);
  }

  /**
   * read bean entirely.
   *
   * @return read bean
   */
  public App2Preferences read() {
    App2Preferences bean=new App2Preferences();
    bean.field2=PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("field2", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.field2)));

    return bean;
  }

  /**
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(App2Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("field2",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.field2));


    editor.commit();
  }

  /**
   * read property field2.
   *
   * @return property field2 value
   */
  public Integer field2() {
    return PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("field2", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.field2)));
  }

  /**
   * get instance of shared preferences.
   *
   * @return the bind app 2 preferences
   */
  public static synchronized BindApp2Preferences instance() {
    if (instance==null) {
      instance=new BindApp2Preferences();
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
     * modifier for property field2.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putField2(Integer value) {
      editor.putString("field2",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(value));

      return this;
    }
  }
}

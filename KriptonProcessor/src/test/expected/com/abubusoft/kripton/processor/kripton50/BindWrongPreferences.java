package com.abubusoft.kripton.processor.kripton50;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import java.lang.Long;
import java.lang.String;
import java.util.List;

/**
 * This class is the shared preference binder defined for Wrong
 *
 * @see Wrong
 */
public class BindWrongPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindWrongPreferences instance;

  /**
   * working instance of bean
   */
  private final Wrong defaultBean;

  /**
   * constructor
   */
  private BindWrongPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Wrong();
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
    Wrong bean=new Wrong();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Wrong read() {
    Wrong bean=new Wrong();
    bean.name=prefs.getString("name", bean.name);
    bean.setDescription(prefs.getString("description", bean.getDescription()));
    bean.valueFloat=prefs.getFloat("valueFloat",bean.valueFloat);
    bean.valueBoolean=prefs.getBoolean("valueBoolean",bean.valueBoolean);
    bean.setStringArray(string2array(prefs.getString("stringArray", null), bean.getStringArray()));
    bean.stringList=string2list(prefs.getString("stringList", null), bean.stringList);
    bean.valueInt=prefs.getInt("valueInt",bean.valueInt);
    bean.valueLong=prefs.getLong("valueLong",bean.valueLong);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Wrong bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name", bean.name);
    editor.putString("description", bean.getDescription());
    editor.putFloat("valueFloat", bean.valueFloat);
    editor.putBoolean("valueBoolean", bean.valueBoolean);
    editor.putString("stringArray", array2String(bean.getStringArray()));
    editor.putString("stringList", list2String(bean.stringList));
    editor.putInt("valueInt", bean.valueInt);
    editor.putLong("valueLong", bean.valueLong);

    editor.commit();
  }

  /**
   * read property name
   *
   * @return property name value
   */
  public String name() {
    return prefs.getString("name", defaultBean.name);
  }

  /**
   * read property description
   *
   * @return property description value
   */
  public String description() {
    return prefs.getString("description", defaultBean.getDescription());
  }

  /**
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public float valueFloat() {
    return prefs.getFloat("valueFloat",defaultBean.valueFloat);
  }

  /**
   * read property valueBoolean
   *
   * @return property valueBoolean value
   */
  public boolean valueBoolean() {
    return prefs.getBoolean("valueBoolean",defaultBean.valueBoolean);
  }

  /**
   * read property stringArray
   *
   * @return property stringArray value
   */
  public String[] stringArray() {
    return string2array(prefs.getString("stringArray", null), defaultBean.getStringArray());
  }

  /**
   * read property stringList
   *
   * @return property stringList value
   */
  public List<String> stringList() {
    return string2list(prefs.getString("stringList", null), defaultBean.stringList);
  }

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public int valueInt() {
    return prefs.getInt("valueInt",defaultBean.valueInt);
  }

  /**
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long valueLong() {
    return prefs.getLong("valueLong",defaultBean.valueLong);
  }

  /**
   * get instance of shared preferences
   */
  public static BindWrongPreferences instance() {
    if (instance==null) {
      instance=new BindWrongPreferences();
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
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name", value);
      return this;
    }

    /**
     * modifier for property description
     */
    public BindEditor putDescription(String value) {
      editor.putString("description", value);
      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("valueFloat", value);
      return this;
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("valueBoolean", value);
      return this;
    }

    /**
     * modifier for property stringArray
     */
    public BindEditor putStringArray(String[] value) {
      editor.putString("stringArray", array2String(value));
      return this;
    }

    /**
     * modifier for property stringList
     */
    public BindEditor putStringList(List<String> value) {
      editor.putString("stringList", list2String(value));
      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("valueInt", value);
      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      editor.putLong("valueLong", value);
      return this;
    }
  }
}

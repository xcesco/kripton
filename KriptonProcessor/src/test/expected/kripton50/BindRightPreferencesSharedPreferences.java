package kripton50;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.utils.StringUtility;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the shared preference binder defined for RightPreferences
 *
 * @see RightPreferences
 */
public class BindRightPreferencesSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindRightPreferencesSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final RightPreferences defaultBean;

  /**
   * constructor
   */
  private BindRightPreferencesSharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new RightPreferences();
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
    RightPreferences bean=new RightPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public RightPreferences read() {
    RightPreferences bean=new RightPreferences();
    bean.name=prefs.getString("name", bean.name);
    bean.setDescription(prefs.getString("description", bean.getDescription()));
    bean.valueFloat=prefs.getFloat("valueFloat", bean.valueFloat);
    bean.valueBoolean=(boolean)prefs.getBoolean("valueBoolean", (boolean)bean.valueBoolean);
     {
      // read stringArray
      String tempStringArray=prefs.getString("stringArray", null);
      ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempStringArray);
      bean.setStringArray((StringUtility.hasText(tempStringArray)) ? CollectionUtility.asArray(collection, new java.lang.String[collection.size()]): null);
    }

     {
      String temp=prefs.getString("stringList", null);
      bean.stringList=StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<String>(), String.class, temp): null;
    }

    bean.valueInt=(int)prefs.getInt("valueInt", (int)bean.valueInt);
    bean.valueLong=prefs.getLong("valueLong", (bean.valueLong==null?0L:bean.valueLong));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(RightPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);
    editor.putString("description",bean.getDescription());
    editor.putFloat("valueFloat",bean.valueFloat);
    editor.putBoolean("valueBoolean",(boolean)bean.valueBoolean);
    if (bean.getStringArray()!=null) editor.putString("stringArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getStringArray(), ArrayList.class))); else editor.putString("stringArray", null);
    if (bean.stringList!=null) editor.putString("stringList",ProcessorHelper.asString(bean.stringList)); else editor.putString("stringList", null);
    editor.putInt("valueInt",(int)bean.valueInt);
    if (bean.valueLong!=null) editor.putLong("valueLong",bean.valueLong);

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
    return prefs.getFloat("valueFloat", defaultBean.valueFloat);
  }

  /**
   * read property valueBoolean
   *
   * @return property valueBoolean value
   */
  public boolean valueBoolean() {
    return (boolean)prefs.getBoolean("valueBoolean", (boolean)defaultBean.valueBoolean);
  }

  /**
   * read property stringArray
   *
   * @return property stringArray value
   */
  public String[] stringArray() {
     {
      // read stringArray
      String tempStringArray=prefs.getString("stringArray", null);
      ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempStringArray);
      return (StringUtility.hasText(tempStringArray)) ? CollectionUtility.asArray(collection, new java.lang.String[collection.size()]): null;
    }

  }

  /**
   * read property stringList
   *
   * @return property stringList value
   */
  public List<String> stringList() {
     {
      String temp=prefs.getString("stringList", null);
      return StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<String>(), String.class, temp): null;
    }

  }

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public int valueInt() {
    return (int)prefs.getInt("valueInt", (int)defaultBean.valueInt);
  }

  /**
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long valueLong() {
    return prefs.getLong("valueLong", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
  }

  /**
   * get instance of shared preferences
   */
  public static BindRightPreferencesSharedPreferences instance() {
    if (instance==null) {
      instance=new BindRightPreferencesSharedPreferences();
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
      editor.putString("name",value);
      return this;
    }

    /**
     * modifier for property description
     */
    public BindEditor putDescription(String value) {
      editor.putString("description",value);
      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("valueFloat",value);
      return this;
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("valueBoolean",(boolean)value);
      return this;
    }

    /**
     * modifier for property stringArray
     */
    public BindEditor putStringArray(String[] value) {
      if (value!=null) editor.putString("stringArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("stringArray", null);
      return this;
    }

    /**
     * modifier for property stringList
     */
    public BindEditor putStringList(List<String> value) {
      if (value!=null) editor.putString("stringList",ProcessorHelper.asString(value)); else editor.putString("stringList", null);
      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("valueInt",(int)value);
      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null) editor.putLong("valueLong",value);
      return this;
    }
  }
}

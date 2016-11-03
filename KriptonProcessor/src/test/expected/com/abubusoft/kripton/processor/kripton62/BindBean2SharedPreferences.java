package com.abubusoft.kripton.processor.kripton62;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.ProcessorHelper;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class is the shared preference binder defined for Bean2
 *
 * @see Bean2
 */
public class BindBean2SharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBean2SharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean2 defaultBean;

  /**
   * constructor
   */
  private BindBean2SharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Bean2();
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
    Bean2 bean=new Bean2();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean2 read() {
    Bean2 bean=new Bean2();
    bean.setId((prefs.getString("bean", null)!=null) ? Long.valueOf(prefs.getString("id", "0")): null);
    bean.setValueByteSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Byte>(), Byte.class, prefs.getString("valueByteSet", null)): null);
    bean.setValueShortSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Short>(), Short.class, prefs.getString("valueShortSet", null)): null);
    bean.setValueIntegerSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Integer>(), Integer.class, prefs.getString("valueIntegerSet", null)): null);
    bean.setValueStringSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, prefs.getString("valueStringSet", null)): null);
    bean.setValueCharacterSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Character>(), Character.class, prefs.getString("valueCharacterSet", null)): null);
    bean.setValueFloatSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Float>(), Float.class, prefs.getString("valueFloatSet", null)): null);
    bean.setValueDoubleSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Double>(), Double.class, prefs.getString("valueDoubleSet", null)): null);
    bean.setValueBigDecimalSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, prefs.getString("valueBigDecimalSet", null)): null);
    bean.setValueBeanSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, prefs.getString("valueBeanSet", null)): null);
    bean.setValueEnumTypeSet((prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, prefs.getString("valueEnumTypeSet", null)): null);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean2 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("id",String.valueOf(bean.getId()));
    if (bean.getValueByteSet()!=null) editor.putString("valueByteSet",ProcessorHelper.asString(bean.getValueByteSet())); else editor.putString("valueByteSet", null);
    if (bean.getValueShortSet()!=null) editor.putString("valueShortSet",ProcessorHelper.asString(bean.getValueShortSet())); else editor.putString("valueShortSet", null);
    if (bean.getValueIntegerSet()!=null) editor.putString("valueIntegerSet",ProcessorHelper.asString(bean.getValueIntegerSet())); else editor.putString("valueIntegerSet", null);
    if (bean.getValueStringSet()!=null) editor.putString("valueStringSet",ProcessorHelper.asString(bean.getValueStringSet())); else editor.putString("valueStringSet", null);
    if (bean.getValueCharacterSet()!=null) editor.putString("valueCharacterSet",ProcessorHelper.asString(bean.getValueCharacterSet())); else editor.putString("valueCharacterSet", null);
    if (bean.getValueFloatSet()!=null) editor.putString("valueFloatSet",ProcessorHelper.asString(bean.getValueFloatSet())); else editor.putString("valueFloatSet", null);
    if (bean.getValueDoubleSet()!=null) editor.putString("valueDoubleSet",ProcessorHelper.asString(bean.getValueDoubleSet())); else editor.putString("valueDoubleSet", null);
    if (bean.getValueBigDecimalSet()!=null) editor.putString("valueBigDecimalSet",ProcessorHelper.asString(bean.getValueBigDecimalSet())); else editor.putString("valueBigDecimalSet", null);
    if (bean.getValueBeanSet()!=null) editor.putString("valueBeanSet",ProcessorHelper.asString(bean.getValueBeanSet())); else editor.putString("valueBeanSet", null);
    if (bean.getValueEnumTypeSet()!=null) editor.putString("valueEnumTypeSet",ProcessorHelper.asString(bean.getValueEnumTypeSet())); else editor.putString("valueEnumTypeSet", null);

    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long id() {
    return (prefs.getString("defaultBean", null)!=null) ? Long.valueOf(prefs.getString("id", "0")): null;
  }

  /**
   * read property valueByteSet
   *
   * @return property valueByteSet value
   */
  public Set<Byte> valueByteSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Byte>(), Byte.class, prefs.getString("valueByteSet", null)): null;
  }

  /**
   * read property valueShortSet
   *
   * @return property valueShortSet value
   */
  public HashSet<Short> valueShortSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Short>(), Short.class, prefs.getString("valueShortSet", null)): null;
  }

  /**
   * read property valueIntegerSet
   *
   * @return property valueIntegerSet value
   */
  public LinkedHashSet<Integer> valueIntegerSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Integer>(), Integer.class, prefs.getString("valueIntegerSet", null)): null;
  }

  /**
   * read property valueStringSet
   *
   * @return property valueStringSet value
   */
  public HashSet<String> valueStringSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, prefs.getString("valueStringSet", null)): null;
  }

  /**
   * read property valueCharacterSet
   *
   * @return property valueCharacterSet value
   */
  public Set<Character> valueCharacterSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Character>(), Character.class, prefs.getString("valueCharacterSet", null)): null;
  }

  /**
   * read property valueFloatSet
   *
   * @return property valueFloatSet value
   */
  public HashSet<Float> valueFloatSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Float>(), Float.class, prefs.getString("valueFloatSet", null)): null;
  }

  /**
   * read property valueDoubleSet
   *
   * @return property valueDoubleSet value
   */
  public HashSet<Double> valueDoubleSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Double>(), Double.class, prefs.getString("valueDoubleSet", null)): null;
  }

  /**
   * read property valueBigDecimalSet
   *
   * @return property valueBigDecimalSet value
   */
  public HashSet<BigDecimal> valueBigDecimalSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, prefs.getString("valueBigDecimalSet", null)): null;
  }

  /**
   * read property valueBeanSet
   *
   * @return property valueBeanSet value
   */
  public LinkedHashSet<Bean> valueBeanSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, prefs.getString("valueBeanSet", null)): null;
  }

  /**
   * read property valueEnumTypeSet
   *
   * @return property valueEnumTypeSet value
   */
  public HashSet<EnumType> valueEnumTypeSet() {
    return (prefs.getString("defaultBean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, prefs.getString("valueEnumTypeSet", null)): null;
  }

  /**
   * get instance of shared preferences
   */
  public static BindBean2SharedPreferences instance() {
    if (instance==null) {
      instance=new BindBean2SharedPreferences();
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
     * modifier for property id
     */
    public BindEditor putId(long value) {
      editor.putString("id",String.valueOf(value));
      return this;
    }

    /**
     * modifier for property valueByteSet
     */
    public BindEditor putValueByteSet(Set<Byte> value) {
      if (value!=null) editor.putString("valueByteSet",ProcessorHelper.asString(value)); else editor.putString("valueByteSet", null);
      return this;
    }

    /**
     * modifier for property valueShortSet
     */
    public BindEditor putValueShortSet(HashSet<Short> value) {
      if (value!=null) editor.putString("valueShortSet",ProcessorHelper.asString(value)); else editor.putString("valueShortSet", null);
      return this;
    }

    /**
     * modifier for property valueIntegerSet
     */
    public BindEditor putValueIntegerSet(LinkedHashSet<Integer> value) {
      if (value!=null) editor.putString("valueIntegerSet",ProcessorHelper.asString(value)); else editor.putString("valueIntegerSet", null);
      return this;
    }

    /**
     * modifier for property valueStringSet
     */
    public BindEditor putValueStringSet(HashSet<String> value) {
      if (value!=null) editor.putString("valueStringSet",ProcessorHelper.asString(value)); else editor.putString("valueStringSet", null);
      return this;
    }

    /**
     * modifier for property valueCharacterSet
     */
    public BindEditor putValueCharacterSet(Set<Character> value) {
      if (value!=null) editor.putString("valueCharacterSet",ProcessorHelper.asString(value)); else editor.putString("valueCharacterSet", null);
      return this;
    }

    /**
     * modifier for property valueFloatSet
     */
    public BindEditor putValueFloatSet(HashSet<Float> value) {
      if (value!=null) editor.putString("valueFloatSet",ProcessorHelper.asString(value)); else editor.putString("valueFloatSet", null);
      return this;
    }

    /**
     * modifier for property valueDoubleSet
     */
    public BindEditor putValueDoubleSet(HashSet<Double> value) {
      if (value!=null) editor.putString("valueDoubleSet",ProcessorHelper.asString(value)); else editor.putString("valueDoubleSet", null);
      return this;
    }

    /**
     * modifier for property valueBigDecimalSet
     */
    public BindEditor putValueBigDecimalSet(HashSet<BigDecimal> value) {
      if (value!=null) editor.putString("valueBigDecimalSet",ProcessorHelper.asString(value)); else editor.putString("valueBigDecimalSet", null);
      return this;
    }

    /**
     * modifier for property valueBeanSet
     */
    public BindEditor putValueBeanSet(LinkedHashSet<Bean> value) {
      if (value!=null) editor.putString("valueBeanSet",ProcessorHelper.asString(value)); else editor.putString("valueBeanSet", null);
      return this;
    }

    /**
     * modifier for property valueEnumTypeSet
     */
    public BindEditor putValueEnumTypeSet(HashSet<EnumType> value) {
      if (value!=null) editor.putString("valueEnumTypeSet",ProcessorHelper.asString(value)); else editor.putString("valueEnumTypeSet", null);
      return this;
    }
  }
}

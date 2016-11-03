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
 * This class is the shared preference binder defined for Bean
 *
 * @see Bean
 */
public class BindBeanSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBeanSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean defaultBean;

  /**
   * constructor
   */
  private BindBeanSharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Bean();
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
    Bean bean=new Bean();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean read() {
    Bean bean=new Bean();
    bean.id=(prefs.getString("bean", null)!=null) ? Long.valueOf(prefs.getString("id", "0")): null;
    bean.valueByteSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Byte>(), Byte.class, prefs.getString("valueByteSet", null)): null;
    bean.valueShortSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Short>(), Short.class, prefs.getString("valueShortSet", null)): null;
    bean.valueIntegerSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Integer>(), Integer.class, prefs.getString("valueIntegerSet", null)): null;
    bean.valueStringSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, prefs.getString("valueStringSet", null)): null;
    bean.valueCharacterSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Character>(), Character.class, prefs.getString("valueCharacterSet", null)): null;
    bean.valueFloatSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Float>(), Float.class, prefs.getString("valueFloatSet", null)): null;
    bean.valueDoubleSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<Double>(), Double.class, prefs.getString("valueDoubleSet", null)): null;
    bean.valueBigDecimalSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, prefs.getString("valueBigDecimalSet", null)): null;
    bean.valueBeanSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, prefs.getString("valueBeanSet", null)): null;
    bean.valueEnumTypeSet=(prefs.getString("bean", null)!=null) ? ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, prefs.getString("valueEnumTypeSet", null)): null;

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("id",String.valueOf(bean.id));
    if (bean.valueByteSet!=null) editor.putString("valueByteSet",ProcessorHelper.asString(bean.valueByteSet)); else editor.putString("valueByteSet", null);
    if (bean.valueShortSet!=null) editor.putString("valueShortSet",ProcessorHelper.asString(bean.valueShortSet)); else editor.putString("valueShortSet", null);
    if (bean.valueIntegerSet!=null) editor.putString("valueIntegerSet",ProcessorHelper.asString(bean.valueIntegerSet)); else editor.putString("valueIntegerSet", null);
    if (bean.valueStringSet!=null) editor.putString("valueStringSet",ProcessorHelper.asString(bean.valueStringSet)); else editor.putString("valueStringSet", null);
    if (bean.valueCharacterSet!=null) editor.putString("valueCharacterSet",ProcessorHelper.asString(bean.valueCharacterSet)); else editor.putString("valueCharacterSet", null);
    if (bean.valueFloatSet!=null) editor.putString("valueFloatSet",ProcessorHelper.asString(bean.valueFloatSet)); else editor.putString("valueFloatSet", null);
    if (bean.valueDoubleSet!=null) editor.putString("valueDoubleSet",ProcessorHelper.asString(bean.valueDoubleSet)); else editor.putString("valueDoubleSet", null);
    if (bean.valueBigDecimalSet!=null) editor.putString("valueBigDecimalSet",ProcessorHelper.asString(bean.valueBigDecimalSet)); else editor.putString("valueBigDecimalSet", null);
    if (bean.valueBeanSet!=null) editor.putString("valueBeanSet",ProcessorHelper.asString(bean.valueBeanSet)); else editor.putString("valueBeanSet", null);
    if (bean.valueEnumTypeSet!=null) editor.putString("valueEnumTypeSet",ProcessorHelper.asString(bean.valueEnumTypeSet)); else editor.putString("valueEnumTypeSet", null);

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
  public static BindBeanSharedPreferences instance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
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

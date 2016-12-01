package sqlite.kripton62;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtils;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
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
    bean.id=prefs.getLong("id", bean.id);
     {
      String temp=prefs.getString("valueByteSet", null);
      bean.valueByteSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Byte>(), Byte.class, temp): null;
    }

     {
      String temp=prefs.getString("valueShortSet", null);
      bean.valueShortSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Short>(), Short.class, temp): null;
    }

     {
      String temp=prefs.getString("valueIntegerSet", null);
      bean.valueIntegerSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedHashSet<Integer>(), Integer.class, temp): null;
    }

     {
      String temp=prefs.getString("valueStringSet", null);
      bean.valueStringSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, temp): null;
    }

     {
      String temp=prefs.getString("valueCharacterSet", null);
      bean.valueCharacterSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Character>(), Character.class, temp): null;
    }

     {
      String temp=prefs.getString("valueFloatSet", null);
      bean.valueFloatSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Float>(), Float.class, temp): null;
    }

     {
      String temp=prefs.getString("valueDoubleSet", null);
      bean.valueDoubleSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Double>(), Double.class, temp): null;
    }

     {
      String temp=prefs.getString("valueBigDecimalSet", null);
      bean.valueBigDecimalSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, temp): null;
    }

     {
      String temp=prefs.getString("valueBeanSet", null);
      bean.valueBeanSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, temp): null;
    }

     {
      String temp=prefs.getString("valueEnumTypeSet", null);
      bean.valueEnumTypeSet=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean2 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("id",bean.id);
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
    return prefs.getLong("id", defaultBean.id);
  }

  /**
   * read property valueByteSet
   *
   * @return property valueByteSet value
   */
  public Set<Byte> valueByteSet() {
    String temp=prefs.getString("valueByteSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Byte>(), Byte.class, temp): null;

  }

  /**
   * read property valueShortSet
   *
   * @return property valueShortSet value
   */
  public HashSet<Short> valueShortSet() {
    String temp=prefs.getString("valueShortSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Short>(), Short.class, temp): null;

  }

  /**
   * read property valueIntegerSet
   *
   * @return property valueIntegerSet value
   */
  public LinkedHashSet<Integer> valueIntegerSet() {
    String temp=prefs.getString("valueIntegerSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedHashSet<Integer>(), Integer.class, temp): null;

  }

  /**
   * read property valueStringSet
   *
   * @return property valueStringSet value
   */
  public HashSet<String> valueStringSet() {
    String temp=prefs.getString("valueStringSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, temp): null;

  }

  /**
   * read property valueCharacterSet
   *
   * @return property valueCharacterSet value
   */
  public Set<Character> valueCharacterSet() {
    String temp=prefs.getString("valueCharacterSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Character>(), Character.class, temp): null;

  }

  /**
   * read property valueFloatSet
   *
   * @return property valueFloatSet value
   */
  public HashSet<Float> valueFloatSet() {
    String temp=prefs.getString("valueFloatSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Float>(), Float.class, temp): null;

  }

  /**
   * read property valueDoubleSet
   *
   * @return property valueDoubleSet value
   */
  public HashSet<Double> valueDoubleSet() {
    String temp=prefs.getString("valueDoubleSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<Double>(), Double.class, temp): null;

  }

  /**
   * read property valueBigDecimalSet
   *
   * @return property valueBigDecimalSet value
   */
  public HashSet<BigDecimal> valueBigDecimalSet() {
    String temp=prefs.getString("valueBigDecimalSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, temp): null;

  }

  /**
   * read property valueBeanSet
   *
   * @return property valueBeanSet value
   */
  public LinkedHashSet<Bean> valueBeanSet() {
    String temp=prefs.getString("valueBeanSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, temp): null;

  }

  /**
   * read property valueEnumTypeSet
   *
   * @return property valueEnumTypeSet value
   */
  public HashSet<EnumType> valueEnumTypeSet() {
    String temp=prefs.getString("valueEnumTypeSet", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, temp): null;

  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindBean2SharedPreferences instance() {
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
      editor.putLong("id",value);
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

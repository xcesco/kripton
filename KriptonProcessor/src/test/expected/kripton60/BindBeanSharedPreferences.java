package kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.Base64Util;
import com.abubusoft.kripton.common.CalendarUtil;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.CurrencyUtil;
import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.common.LocaleUtil;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.TimeUtil;
import com.abubusoft.kripton.common.TimeZoneUtil;
import com.abubusoft.kripton.common.UrlUtil;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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
    bean.valueBoolType=(boolean)prefs.getBoolean("valueBoolType", (boolean)bean.valueBoolType);
    bean.valueBool=(boolean)prefs.getBoolean("valueBool", (boolean)(bean.valueBool==null?false:bean.valueBool));
    bean.valueByteType=(byte)prefs.getInt("valueByteType", (byte)bean.valueByteType);
    bean.valueByte=(byte)prefs.getInt("valueByte", (byte)(bean.valueByte==null?(byte)0:bean.valueByte));
    bean.valueShortType=(short)prefs.getInt("valueShortType", (short)bean.valueShortType);
    bean.valueShort=(short)prefs.getInt("valueShort", (short)(bean.valueShort==null?(short)0:bean.valueShort));
    bean.valueIntType=(int)prefs.getInt("valueIntType", (int)bean.valueIntType);
    bean.valueInt=(int)prefs.getInt("valueInt", (int)(bean.valueInt==null?0:bean.valueInt));
    bean.valueString=prefs.getString("valueString", bean.valueString);
    bean.valueCharType=(char)prefs.getInt("valueCharType", (char)bean.valueCharType);
    bean.valueChar=(char)prefs.getInt("valueChar", (char)(bean.valueChar==null?(char)0:bean.valueChar));
    bean.valueFloatType=prefs.getFloat("valueFloatType", bean.valueFloatType);
    bean.valueFloat=prefs.getFloat("valueFloat", (bean.valueFloat==null?0F:bean.valueFloat));
    bean.valueBigInteger=(prefs.getString("valueBigInteger", null)!=null) ? new BigInteger(prefs.getString("valueBigInteger", "0")): null;
    bean.valueBigDecimal=(prefs.getString("valueBigDecimal", null)!=null) ? new BigDecimal(prefs.getString("valueBigDecimal", "0")): null;
    bean.valueEnumType=(prefs.getString("valueEnumType", null)!=null) ? EnumType.valueOf(prefs.getString("valueEnumType", null)): null;
    bean.valueLongType=(prefs.getString("valueLongType", null)!=null) ? Long.valueOf(prefs.getString("valueLongType", "0")): null;
    bean.valueLong=(prefs.getString("valueLong", null)!=null) ? Long.valueOf(prefs.getString("valueLong", "0")): null;
    bean.valueDoubleType=(prefs.getString("valueDoubleType", null)!=null) ? Double.valueOf(prefs.getString("valueDoubleType", "0")): null;
    bean.valueDouble=(prefs.getString("valueDouble", null)!=null) ? Double.valueOf(prefs.getString("valueDouble", "0")): null;
    bean.valueLocale=(prefs.getString("valueLocale", null)!=null) ? LocaleUtil.read(prefs.getString("valueLocale", null)): null;
    bean.valueCalendar=(prefs.getString("valueCalendar", null)!=null) ? CalendarUtil.read(prefs.getString("valueCalendar", null)): null;
    bean.valueDate=(prefs.getString("valueDate", null)!=null) ? DateUtil.read(prefs.getString("valueDate", null)): null;
    bean.valueUrl=(prefs.getString("valueUrl", null)!=null) ? UrlUtil.read(prefs.getString("valueUrl", null)): null;
    bean.valueTime=(prefs.getString("valueTime", null)!=null) ? TimeUtil.read(prefs.getString("valueTime", null)): null;
    bean.valueCurrency=(prefs.getString("valueCurrency", null)!=null) ? CurrencyUtil.read(prefs.getString("valueCurrency", null)): null;
    bean.valueTimeZone=(prefs.getString("valueTimeZone", null)!=null) ? TimeZoneUtil.read(prefs.getString("valueTimeZone", null)): null;
    bean.valueTimeList=(prefs.getString("valueTimeList", null)!=null) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, prefs.getString("valueTimeList", null)): null;
    bean.valueStrinList=(prefs.getString("valueStrinList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, prefs.getString("valueStrinList", null)): null;
    bean.valueLongList=(prefs.getString("valueLongList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, prefs.getString("valueLongList", null)): null;
    bean.valueByteArray=(prefs.getString("valueByteArray", null)!=null) ? Base64Util.decode(prefs.getString("valueByteArray", null)): null;
    bean.valueBean=(prefs.getString("valueBean", null)!=null) ? (Bean)readObj(prefs.getString("valueBean", null), Bean.class): null;
    bean.valueLongTypeArray=(prefs.getString("valueLongTypeArray", null)!=null) ? CollectionUtility.asLongTypeArray(ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, prefs.getString("valueLongTypeArray", null))): null;
    bean.valueLongArray=(prefs.getString("valueLongArray", null)!=null) ? CollectionUtility.asLongArray(ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, prefs.getString("valueLongArray", null))): null;
    bean.valueBeanArray=(prefs.getString("valueBeanArray", null)!=null) ? CollectionUtility.asArray(ProcessorHelper.asCollection(new ArrayList<Bean>(), Bean.class, prefs.getString("valueBeanArray", null))): null;
    bean.valueStringArray=(prefs.getString("valueStringArray", null)!=null) ? CollectionUtility.asArray(ProcessorHelper.asCollection(new ArrayList<String>(), String.class, prefs.getString("valueStringArray", null))): null;
    bean.valueCharList=(prefs.getString("valueCharList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, prefs.getString("valueCharList", null)): null;
    bean.valueCharTypeArray=(prefs.getString("valueCharTypeArray", null)!=null) ? CollectionUtility.asCharacterTypeArray(ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, prefs.getString("valueCharTypeArray", null))): null;
    bean.valueCharArray=(prefs.getString("valueCharArray", null)!=null) ? CollectionUtility.asCharacterArray(ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, prefs.getString("valueCharArray", null))): null;
    bean.valueMapStringBean=(prefs.getString("valueMapStringBean", null)!=null) ? ProcessorHelper.asMap(new HashMap<String, Bean>(), String.class, Bean.class, prefs.getString("valueMapStringBean", null)): null;
    bean.valueLinkedMapStringBean=(prefs.getString("valueLinkedMapStringBean", null)!=null) ? ProcessorHelper.asMap(new LinkedHashMap<String, Bean>(), String.class, Bean.class, prefs.getString("valueLinkedMapStringBean", null)): null;
    bean.valueSetString=(prefs.getString("valueSetString", null)!=null) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, prefs.getString("valueSetString", null)): null;

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("valueBoolType",(boolean)bean.valueBoolType);
    if (bean.valueBool!=null) editor.putBoolean("valueBool",(boolean)bean.valueBool);
    editor.putInt("valueByteType",(int)bean.valueByteType);
    if (bean.valueByte!=null) editor.putInt("valueByte",(int)bean.valueByte);
    editor.putInt("valueShortType",(int)bean.valueShortType);
    if (bean.valueShort!=null) editor.putInt("valueShort",(int)bean.valueShort);
    editor.putInt("valueIntType",(int)bean.valueIntType);
    if (bean.valueInt!=null) editor.putInt("valueInt",(int)bean.valueInt);
    editor.putString("valueString",bean.valueString);
    editor.putInt("valueCharType",(char)bean.valueCharType);
    if (bean.valueChar!=null) editor.putInt("valueChar",(char)bean.valueChar);
    editor.putFloat("valueFloatType",bean.valueFloatType);
    if (bean.valueFloat!=null) editor.putFloat("valueFloat",bean.valueFloat);
    if (bean.valueBigInteger!=null) editor.putString("valueBigInteger",bean.valueBigInteger.toString() ); else editor.putString("valueBigInteger", null);
    if (bean.valueBigDecimal!=null) editor.putString("valueBigDecimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("valueBigDecimal", null);
    if (bean.valueEnumType!=null) editor.putString("valueEnumType",bean.valueEnumType.toString() ); else editor.putString("valueEnumType", null);
    editor.putString("valueLongType",String.valueOf(bean.valueLongType));
    if (bean.valueLong!=null) editor.putString("valueLong",String.valueOf(bean.valueLong)); else editor.putString("valueLong", null);
    editor.putString("valueDoubleType",String.valueOf(bean.valueDoubleType));
    if (bean.valueDouble!=null) editor.putString("valueDouble",String.valueOf(bean.valueDouble)); else editor.putString("valueDouble", null);
    if (bean.valueLocale!=null) editor.putString("valueLocale",LocaleUtil.write(bean.valueLocale)); else editor.putString("valueLocale", null);
    if (bean.valueCalendar!=null) editor.putString("valueCalendar",CalendarUtil.write(bean.valueCalendar)); else editor.putString("valueCalendar", null);
    if (bean.valueDate!=null) editor.putString("valueDate",DateUtil.write(bean.valueDate)); else editor.putString("valueDate", null);
    if (bean.valueUrl!=null) editor.putString("valueUrl",UrlUtil.write(bean.valueUrl)); else editor.putString("valueUrl", null);
    if (bean.valueTime!=null) editor.putString("valueTime",TimeUtil.write(bean.valueTime)); else editor.putString("valueTime", null);
    if (bean.valueCurrency!=null) editor.putString("valueCurrency",CurrencyUtil.write(bean.valueCurrency)); else editor.putString("valueCurrency", null);
    if (bean.valueTimeZone!=null) editor.putString("valueTimeZone",TimeZoneUtil.write(bean.valueTimeZone)); else editor.putString("valueTimeZone", null);
    if (bean.valueTimeList!=null) editor.putString("valueTimeList",ProcessorHelper.asString(bean.valueTimeList)); else editor.putString("valueTimeList", null);
    if (bean.valueStrinList!=null) editor.putString("valueStrinList",ProcessorHelper.asString(bean.valueStrinList)); else editor.putString("valueStrinList", null);
    if (bean.valueLongList!=null) editor.putString("valueLongList",ProcessorHelper.asString(bean.valueLongList)); else editor.putString("valueLongList", null);
    if (bean.valueByteArray!=null) editor.putString("valueByteArray",Base64Util.encode(bean.valueByteArray)); else editor.putString("valueByteArray", null);
    if (bean.valueBean!=null) editor.putString("valueBean",writeObj(bean.valueBean)); else editor.putString("valueBean", null);
    if (bean.valueLongTypeArray!=null) editor.putString("valueLongTypeArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueLongTypeArray, ArrayList.class))); else editor.putString("valueLongTypeArray", null);
    if (bean.valueLongArray!=null) editor.putString("valueLongArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueLongArray, ArrayList.class))); else editor.putString("valueLongArray", null);
    if (bean.valueBeanArray!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueBeanArray, ArrayList.class))); else editor.putString("valueBeanArray", null);
    if (bean.valueStringArray!=null) editor.putString("valueStringArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueStringArray, ArrayList.class))); else editor.putString("valueStringArray", null);
    if (bean.valueCharList!=null) editor.putString("valueCharList",ProcessorHelper.asString(bean.valueCharList)); else editor.putString("valueCharList", null);
    if (bean.valueCharTypeArray!=null) editor.putString("valueCharTypeArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueCharTypeArray, ArrayList.class))); else editor.putString("valueCharTypeArray", null);
    if (bean.valueCharArray!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtility.asList(bean.valueCharArray, ArrayList.class))); else editor.putString("valueCharArray", null);
    if (bean.valueMapStringBean!=null) editor.putString("valueMapStringBean",ProcessorHelper.asString(bean.valueMapStringBean)); else editor.putString("valueMapStringBean", null);
    if (bean.valueLinkedMapStringBean!=null) editor.putString("valueLinkedMapStringBean",ProcessorHelper.asString(bean.valueLinkedMapStringBean)); else editor.putString("valueLinkedMapStringBean", null);
    if (bean.valueSetString!=null) editor.putString("valueSetString",ProcessorHelper.asString(bean.valueSetString)); else editor.putString("valueSetString", null);

    editor.commit();
  }

  /**
   * read property valueBoolType
   *
   * @return property valueBoolType value
   */
  public boolean valueBoolType() {
    return (boolean)prefs.getBoolean("valueBoolType", (boolean)defaultBean.valueBoolType);
  }

  /**
   * read property valueBool
   *
   * @return property valueBool value
   */
  public Boolean valueBool() {
    return (boolean)prefs.getBoolean("valueBool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));
  }

  /**
   * read property valueByteType
   *
   * @return property valueByteType value
   */
  public byte valueByteType() {
    return (byte)prefs.getInt("valueByteType", (byte)defaultBean.valueByteType);
  }

  /**
   * read property valueByte
   *
   * @return property valueByte value
   */
  public Byte valueByte() {
    return (byte)prefs.getInt("valueByte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));
  }

  /**
   * read property valueShortType
   *
   * @return property valueShortType value
   */
  public short valueShortType() {
    return (short)prefs.getInt("valueShortType", (short)defaultBean.valueShortType);
  }

  /**
   * read property valueShort
   *
   * @return property valueShort value
   */
  public Short valueShort() {
    return (short)prefs.getInt("valueShort", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));
  }

  /**
   * read property valueIntType
   *
   * @return property valueIntType value
   */
  public int valueIntType() {
    return (int)prefs.getInt("valueIntType", (int)defaultBean.valueIntType);
  }

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public Integer valueInt() {
    return (int)prefs.getInt("valueInt", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));
  }

  /**
   * read property valueString
   *
   * @return property valueString value
   */
  public String valueString() {
    return prefs.getString("valueString", defaultBean.valueString);
  }

  /**
   * read property valueCharType
   *
   * @return property valueCharType value
   */
  public char valueCharType() {
    return (char)prefs.getInt("valueCharType", (char)defaultBean.valueCharType);
  }

  /**
   * read property valueChar
   *
   * @return property valueChar value
   */
  public Character valueChar() {
    return (char)prefs.getInt("valueChar", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));
  }

  /**
   * read property valueFloatType
   *
   * @return property valueFloatType value
   */
  public float valueFloatType() {
    return prefs.getFloat("valueFloatType", defaultBean.valueFloatType);
  }

  /**
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public Float valueFloat() {
    return prefs.getFloat("valueFloat", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));
  }

  /**
   * read property valueBigInteger
   *
   * @return property valueBigInteger value
   */
  public BigInteger valueBigInteger() {
    return (prefs.getString("valueBigInteger", null)!=null) ? new BigInteger(prefs.getString("valueBigInteger", "0")): null;
  }

  /**
   * read property valueBigDecimal
   *
   * @return property valueBigDecimal value
   */
  public BigDecimal valueBigDecimal() {
    return (prefs.getString("valueBigDecimal", null)!=null) ? new BigDecimal(prefs.getString("valueBigDecimal", "0")): null;
  }

  /**
   * read property valueEnumType
   *
   * @return property valueEnumType value
   */
  public EnumType valueEnumType() {
    return (prefs.getString("valueEnumType", null)!=null) ? EnumType.valueOf(prefs.getString("valueEnumType", null)): null;
  }

  /**
   * read property valueLongType
   *
   * @return property valueLongType value
   */
  public long valueLongType() {
    return (prefs.getString("valueLongType", null)!=null) ? Long.valueOf(prefs.getString("valueLongType", "0")): null;
  }

  /**
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long valueLong() {
    return (prefs.getString("valueLong", null)!=null) ? Long.valueOf(prefs.getString("valueLong", "0")): null;
  }

  /**
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double valueDoubleType() {
    return (prefs.getString("valueDoubleType", null)!=null) ? Double.valueOf(prefs.getString("valueDoubleType", "0")): null;
  }

  /**
   * read property valueDouble
   *
   * @return property valueDouble value
   */
  public Double valueDouble() {
    return (prefs.getString("valueDouble", null)!=null) ? Double.valueOf(prefs.getString("valueDouble", "0")): null;
  }

  /**
   * read property valueLocale
   *
   * @return property valueLocale value
   */
  public Locale valueLocale() {
    return (prefs.getString("valueLocale", null)!=null) ? LocaleUtil.read(prefs.getString("valueLocale", null)): null;
  }

  /**
   * read property valueCalendar
   *
   * @return property valueCalendar value
   */
  public Calendar valueCalendar() {
    return (prefs.getString("valueCalendar", null)!=null) ? CalendarUtil.read(prefs.getString("valueCalendar", null)): null;
  }

  /**
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date valueDate() {
    return (prefs.getString("valueDate", null)!=null) ? DateUtil.read(prefs.getString("valueDate", null)): null;
  }

  /**
   * read property valueUrl
   *
   * @return property valueUrl value
   */
  public URL valueUrl() {
    return (prefs.getString("valueUrl", null)!=null) ? UrlUtil.read(prefs.getString("valueUrl", null)): null;
  }

  /**
   * read property valueTime
   *
   * @return property valueTime value
   */
  public Time valueTime() {
    return (prefs.getString("valueTime", null)!=null) ? TimeUtil.read(prefs.getString("valueTime", null)): null;
  }

  /**
   * read property valueCurrency
   *
   * @return property valueCurrency value
   */
  public Currency valueCurrency() {
    return (prefs.getString("valueCurrency", null)!=null) ? CurrencyUtil.read(prefs.getString("valueCurrency", null)): null;
  }

  /**
   * read property valueTimeZone
   *
   * @return property valueTimeZone value
   */
  public TimeZone valueTimeZone() {
    return (prefs.getString("valueTimeZone", null)!=null) ? TimeZoneUtil.read(prefs.getString("valueTimeZone", null)): null;
  }

  /**
   * read property valueTimeList
   *
   * @return property valueTimeList value
   */
  public List<Time> valueTimeList() {
    return (prefs.getString("valueTimeList", null)!=null) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, prefs.getString("valueTimeList", null)): null;
  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> valueStrinList() {
    return (prefs.getString("valueStrinList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, prefs.getString("valueStrinList", null)): null;
  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> valueLongList() {
    return (prefs.getString("valueLongList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, prefs.getString("valueLongList", null)): null;
  }

  /**
   * read property valueByteArray
   *
   * @return property valueByteArray value
   */
  public byte[] valueByteArray() {
    return (prefs.getString("valueByteArray", null)!=null) ? Base64Util.decode(prefs.getString("valueByteArray", null)): null;
  }

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean valueBean() {
    return (prefs.getString("valueBean", null)!=null) ? (Bean)readObj(prefs.getString("valueBean", null), Bean.class): null;
  }

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] valueLongTypeArray() {
    return (prefs.getString("valueLongTypeArray", null)!=null) ? CollectionUtility.asLongTypeArray(ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, prefs.getString("valueLongTypeArray", null))): null;
  }

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] valueLongArray() {
    return (prefs.getString("valueLongArray", null)!=null) ? CollectionUtility.asLongArray(ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, prefs.getString("valueLongArray", null))): null;
  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean[] valueBeanArray() {
    return (prefs.getString("valueBeanArray", null)!=null) ? CollectionUtility.asArray(ProcessorHelper.asCollection(new ArrayList<Bean>(), Bean.class, prefs.getString("valueBeanArray", null))): null;
  }

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] valueStringArray() {
    return (prefs.getString("valueStringArray", null)!=null) ? CollectionUtility.asArray(ProcessorHelper.asCollection(new ArrayList<String>(), String.class, prefs.getString("valueStringArray", null))): null;
  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> valueCharList() {
    return (prefs.getString("valueCharList", null)!=null) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, prefs.getString("valueCharList", null)): null;
  }

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] valueCharTypeArray() {
    return (prefs.getString("valueCharTypeArray", null)!=null) ? CollectionUtility.asCharacterTypeArray(ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, prefs.getString("valueCharTypeArray", null))): null;
  }

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] valueCharArray() {
    return (prefs.getString("valueCharArray", null)!=null) ? CollectionUtility.asCharacterArray(ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, prefs.getString("valueCharArray", null))): null;
  }

  /**
   * read property valueMapStringBean
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> valueMapStringBean() {
    return (prefs.getString("valueMapStringBean", null)!=null) ? ProcessorHelper.asMap(new HashMap<String, Bean>(), String.class, Bean.class, prefs.getString("valueMapStringBean", null)): null;
  }

  /**
   * read property valueLinkedMapStringBean
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> valueLinkedMapStringBean() {
    return (prefs.getString("valueLinkedMapStringBean", null)!=null) ? ProcessorHelper.asMap(new LinkedHashMap<String, Bean>(), String.class, Bean.class, prefs.getString("valueLinkedMapStringBean", null)): null;
  }

  /**
   * read property valueSetString
   *
   * @return property valueSetString value
   */
  public Set<String> valueSetString() {
    return (prefs.getString("valueSetString", null)!=null) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, prefs.getString("valueSetString", null)): null;
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
     * modifier for property valueBoolType
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("valueBoolType",(boolean)value);
      return this;
    }

    /**
     * modifier for property valueBool
     */
    public BindEditor putValueBool(Boolean value) {
      if (value!=null) editor.putBoolean("valueBool",(boolean)value);
      return this;
    }

    /**
     * modifier for property valueByteType
     */
    public BindEditor putValueByteType(byte value) {
      editor.putInt("valueByteType",(int)value);
      return this;
    }

    /**
     * modifier for property valueByte
     */
    public BindEditor putValueByte(Byte value) {
      if (value!=null) editor.putInt("valueByte",(int)value);
      return this;
    }

    /**
     * modifier for property valueShortType
     */
    public BindEditor putValueShortType(short value) {
      editor.putInt("valueShortType",(int)value);
      return this;
    }

    /**
     * modifier for property valueShort
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null) editor.putInt("valueShort",(int)value);
      return this;
    }

    /**
     * modifier for property valueIntType
     */
    public BindEditor putValueIntType(int value) {
      editor.putInt("valueIntType",(int)value);
      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(Integer value) {
      if (value!=null) editor.putInt("valueInt",(int)value);
      return this;
    }

    /**
     * modifier for property valueString
     */
    public BindEditor putValueString(String value) {
      editor.putString("valueString",value);
      return this;
    }

    /**
     * modifier for property valueCharType
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("valueCharType",(char)value);
      return this;
    }

    /**
     * modifier for property valueChar
     */
    public BindEditor putValueChar(Character value) {
      if (value!=null) editor.putInt("valueChar",(char)value);
      return this;
    }

    /**
     * modifier for property valueFloatType
     */
    public BindEditor putValueFloatType(float value) {
      editor.putFloat("valueFloatType",value);
      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(Float value) {
      if (value!=null) editor.putFloat("valueFloat",value);
      return this;
    }

    /**
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("valueBigInteger",value.toString()); else editor.putString("valueBigInteger", null);
      return this;
    }

    /**
     * modifier for property valueBigDecimal
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("valueBigDecimal",value.toPlainString()); else editor.putString("valueBigDecimal", null);
      return this;
    }

    /**
     * modifier for property valueEnumType
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null) editor.putString("valueEnumType",value.toString()); else editor.putString("valueEnumType", null);
      return this;
    }

    /**
     * modifier for property valueLongType
     */
    public BindEditor putValueLongType(long value) {
      editor.putString("valueLongType",String.valueOf(value));
      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null) editor.putString("valueLong",String.valueOf(value)); else editor.putString("valueLong", null);
      return this;
    }

    /**
     * modifier for property valueDoubleType
     */
    public BindEditor putValueDoubleType(double value) {
      editor.putString("valueDoubleType",String.valueOf(value));
      return this;
    }

    /**
     * modifier for property valueDouble
     */
    public BindEditor putValueDouble(Double value) {
      if (value!=null) editor.putString("valueDouble",String.valueOf(value)); else editor.putString("valueDouble", null);
      return this;
    }

    /**
     * modifier for property valueLocale
     */
    public BindEditor putValueLocale(Locale value) {
      if (value!=null) editor.putString("valueLocale",LocaleUtil.write(value)); else editor.putString("valueLocale", null);
      return this;
    }

    /**
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null) editor.putString("valueCalendar",CalendarUtil.write(value)); else editor.putString("valueCalendar", null);
      return this;
    }

    /**
     * modifier for property valueDate
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null) editor.putString("valueDate",DateUtil.write(value)); else editor.putString("valueDate", null);
      return this;
    }

    /**
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null) editor.putString("valueUrl",UrlUtil.write(value)); else editor.putString("valueUrl", null);
      return this;
    }

    /**
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null) editor.putString("valueTime",TimeUtil.write(value)); else editor.putString("valueTime", null);
      return this;
    }

    /**
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null) editor.putString("valueCurrency",CurrencyUtil.write(value)); else editor.putString("valueCurrency", null);
      return this;
    }

    /**
     * modifier for property valueTimeZone
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null) editor.putString("valueTimeZone",TimeZoneUtil.write(value)); else editor.putString("valueTimeZone", null);
      return this;
    }

    /**
     * modifier for property valueTimeList
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null) editor.putString("valueTimeList",ProcessorHelper.asString(value)); else editor.putString("valueTimeList", null);
      return this;
    }

    /**
     * modifier for property valueStrinList
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null) editor.putString("valueStrinList",ProcessorHelper.asString(value)); else editor.putString("valueStrinList", null);
      return this;
    }

    /**
     * modifier for property valueLongList
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null) editor.putString("valueLongList",ProcessorHelper.asString(value)); else editor.putString("valueLongList", null);
      return this;
    }

    /**
     * modifier for property valueByteArray
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null) editor.putString("valueByteArray",Base64Util.encode(value)); else editor.putString("valueByteArray", null);
      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean value) {
      if (value!=null) editor.putString("valueBean",writeObj(value)); else editor.putString("valueBean", null);
      return this;
    }

    /**
     * modifier for property valueLongTypeArray
     */
    public BindEditor putValueLongTypeArray(long[] value) {
      if (value!=null) editor.putString("valueLongTypeArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueLongTypeArray", null);
      return this;
    }

    /**
     * modifier for property valueLongArray
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null) editor.putString("valueLongArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueLongArray", null);
      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueBeanArray", null);
      return this;
    }

    /**
     * modifier for property valueStringArray
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null) editor.putString("valueStringArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueStringArray", null);
      return this;
    }

    /**
     * modifier for property valueCharList
     */
    public BindEditor putValueCharList(LinkedList<Character> value) {
      if (value!=null) editor.putString("valueCharList",ProcessorHelper.asString(value)); else editor.putString("valueCharList", null);
      return this;
    }

    /**
     * modifier for property valueCharTypeArray
     */
    public BindEditor putValueCharTypeArray(char[] value) {
      if (value!=null) editor.putString("valueCharTypeArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueCharTypeArray", null);
      return this;
    }

    /**
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueCharArray", null);
      return this;
    }

    /**
     * modifier for property valueMapStringBean
     */
    public BindEditor putValueMapStringBean(Map<String, Bean> value) {
      if (value!=null) editor.putString("valueMapStringBean",ProcessorHelper.asString(value)); else editor.putString("valueMapStringBean", null);
      return this;
    }

    /**
     * modifier for property valueLinkedMapStringBean
     */
    public BindEditor putValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
      if (value!=null) editor.putString("valueLinkedMapStringBean",ProcessorHelper.asString(value)); else editor.putString("valueLinkedMapStringBean", null);
      return this;
    }

    /**
     * modifier for property valueSetString
     */
    public BindEditor putValueSetString(Set<String> value) {
      if (value!=null) editor.putString("valueSetString",ProcessorHelper.asString(value)); else editor.putString("valueSetString", null);
      return this;
    }
  }
}

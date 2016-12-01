package sqlite.kripton60;

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
import com.abubusoft.kripton.processor.utils.StringUtility;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
    bean.setId(prefs.getLong("id", bean.getId()));
    bean.setValueBean((prefs.getString("valueBean", null)!=null) ? (Bean2)readObj(prefs.getString("valueBean", null), Bean2.class): null);
     {
      // read valueBeanArray
      String tempValueBeanArray=prefs.getString("valueBeanArray", null);
      ArrayList<Bean2> collection=ProcessorHelper.asCollection(new ArrayList<Bean2>(), Bean2.class, tempValueBeanArray);
      bean.setValueBeanArray((StringUtility.hasText(tempValueBeanArray)) ? CollectionUtility.asArray(collection, new sqlite.kripton60.Bean2[collection.size()]): null);
    }

    bean.setValueBigDecimal((prefs.getString("valueBigDecimal", null)!=null) ? new BigDecimal(prefs.getString("valueBigDecimal", "0")): null);
    bean.setValueBigInteger((prefs.getString("valueBigInteger", null)!=null) ? new BigInteger(prefs.getString("valueBigInteger", "0")): null);
    bean.setValueBool((boolean)prefs.getBoolean("valueBool", (boolean)(bean.getValueBool()==null?false:bean.getValueBool())));
    bean.setValueBoolType((boolean)prefs.getBoolean("valueBoolType", (boolean)bean.isValueBoolType()));
    bean.setValueByte((byte)prefs.getInt("valueByte", (byte)(bean.getValueByte()==null?(byte)0:bean.getValueByte())));
    bean.setValueByteArray((prefs.getString("valueByteArray", null)!=null) ? Base64Util.decode(prefs.getString("valueByteArray", null)): null);
    bean.setValueByteType((byte)prefs.getInt("valueByteType", (byte)bean.getValueByteType()));
    bean.setValueCalendar((prefs.getString("valueCalendar", null)!=null) ? CalendarUtil.read(prefs.getString("valueCalendar", null)): null);
    bean.setValueChar((char)prefs.getInt("valueChar", (char)(bean.getValueChar()==null?(char)0:bean.getValueChar())));
     {
      // read valueCharArray
      String tempValueCharArray=prefs.getString("valueCharArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharArray);
      bean.setValueCharArray((StringUtility.hasText(tempValueCharArray)) ? CollectionUtility.asCharacterArray(collection): null);
    }

     {
      String temp=prefs.getString("valueCharList", null);
      bean.setValueCharList(StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, temp): null);
    }

    bean.setValueCharType((char)prefs.getInt("valueCharType", (char)bean.getValueCharType()));
     {
      // read valueCharTypeArray
      String tempValueCharTypeArray=prefs.getString("valueCharTypeArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharTypeArray);
      bean.setValueCharTypeArray((StringUtility.hasText(tempValueCharTypeArray)) ? CollectionUtility.asCharacterTypeArray(collection): null);
    }

    bean.setValueCurrency((prefs.getString("valueCurrency", null)!=null) ? CurrencyUtil.read(prefs.getString("valueCurrency", null)): null);
    bean.setValueDate((prefs.getString("valueDate", null)!=null) ? DateUtil.read(prefs.getString("valueDate", null)): null);
    bean.setValueDouble((prefs.getString("valueDouble", null)!=null) ? Double.valueOf(prefs.getString("valueDouble", "0")): null);
    bean.setValueDoubleType((prefs.getString("valueDoubleType", null)!=null) ? Double.valueOf(prefs.getString("valueDoubleType", "0")): null);
    bean.setValueEnumType((prefs.getString("valueEnumType", null)!=null) ? EnumType.valueOf(prefs.getString("valueEnumType", null)): null);
    bean.setValueFloat(prefs.getFloat("valueFloat", (bean.getValueFloat()==null?0F:bean.getValueFloat())));
    bean.setValueFloatType(prefs.getFloat("valueFloatType", bean.getValueFloatType()));
    bean.setValueInt((int)prefs.getInt("valueInt", (int)(bean.getValueInt()==null?0:bean.getValueInt())));
    bean.setValueIntType((int)prefs.getInt("valueIntType", (int)bean.getValueIntType()));
    bean.setValueLocale((prefs.getString("valueLocale", null)!=null) ? LocaleUtil.read(prefs.getString("valueLocale", null)): null);
    bean.setValueLong(prefs.getLong("valueLong", (bean.getValueLong()==null?0L:bean.getValueLong())));
     {
      // read valueLongArray
      String tempValueLongArray=prefs.getString("valueLongArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongArray);
      bean.setValueLongArray((StringUtility.hasText(tempValueLongArray)) ? CollectionUtility.asLongArray(collection): null);
    }

     {
      String temp=prefs.getString("valueLongList", null);
      bean.setValueLongList(StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, temp): null);
    }

    bean.setValueLongType(prefs.getLong("valueLongType", bean.getValueLongType()));
     {
      // read valueLongTypeArray
      String tempValueLongTypeArray=prefs.getString("valueLongTypeArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongTypeArray);
      bean.setValueLongTypeArray((StringUtility.hasText(tempValueLongTypeArray)) ? CollectionUtility.asLongTypeArray(collection): null);
    }

    bean.setValueShort((short)prefs.getInt("valueShort", (short)(bean.getValueShort()==null?(short)0:bean.getValueShort())));
    bean.setValueShortType((short)prefs.getInt("valueShortType", (short)bean.getValueShortType()));
    bean.setValueString(prefs.getString("valueString", bean.getValueString()));
     {
      // read valueStringArray
      String tempValueStringArray=prefs.getString("valueStringArray", null);
      ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempValueStringArray);
      bean.setValueStringArray((StringUtility.hasText(tempValueStringArray)) ? CollectionUtility.asArray(collection, new java.lang.String[collection.size()]): null);
    }

     {
      String temp=prefs.getString("valueStrinList", null);
      bean.setValueStrinList(StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, temp): null);
    }

    bean.setValueTime((prefs.getString("valueTime", null)!=null) ? TimeUtil.read(prefs.getString("valueTime", null)): null);
     {
      String temp=prefs.getString("valueTimeList", null);
      bean.setValueTimeList(StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, temp): null);
    }

    bean.setValueTimeZone((prefs.getString("valueTimeZone", null)!=null) ? TimeZoneUtil.read(prefs.getString("valueTimeZone", null)): null);
    bean.setValueUrl((prefs.getString("valueUrl", null)!=null) ? UrlUtil.read(prefs.getString("valueUrl", null)): null);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean2 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("id",bean.getId());
    if (bean.getValueBean()!=null) editor.putString("valueBean",writeObj(bean.getValueBean())); else editor.putString("valueBean", null);
    if (bean.getValueBeanArray()!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueBeanArray(), ArrayList.class))); else editor.putString("valueBeanArray", null);
    if (bean.getValueBigDecimal()!=null) editor.putString("valueBigDecimal",bean.getValueBigDecimal().toPlainString() ); else editor.putString("valueBigDecimal", null);;
    if (bean.getValueBigInteger()!=null) editor.putString("valueBigInteger",bean.getValueBigInteger().toString() ); else editor.putString("valueBigInteger", null);;
    if (bean.getValueBool()!=null) editor.putBoolean("valueBool",(boolean)bean.getValueBool());
    editor.putBoolean("valueBoolType",(boolean)bean.isValueBoolType());
    if (bean.getValueByte()!=null) editor.putInt("valueByte",(int)bean.getValueByte());
    if (bean.getValueByteArray()!=null) editor.putString("valueByteArray",Base64Util.encode(bean.getValueByteArray())); else editor.putString("valueByteArray", null);
    editor.putInt("valueByteType",(int)bean.getValueByteType());
    if (bean.getValueCalendar()!=null) editor.putString("valueCalendar",CalendarUtil.write(bean.getValueCalendar())); else editor.putString("valueCalendar", null);
    if (bean.getValueChar()!=null) editor.putInt("valueChar",(char)bean.getValueChar());
    if (bean.getValueCharArray()!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueCharArray(), ArrayList.class))); else editor.putString("valueCharArray", null);
    if (bean.getValueCharList()!=null) editor.putString("valueCharList",ProcessorHelper.asString(bean.getValueCharList())); else editor.putString("valueCharList", null);
    editor.putInt("valueCharType",(char)bean.getValueCharType());
    if (bean.getValueCharTypeArray()!=null) editor.putString("valueCharTypeArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueCharTypeArray(), ArrayList.class))); else editor.putString("valueCharTypeArray", null);
    if (bean.getValueCurrency()!=null) editor.putString("valueCurrency",CurrencyUtil.write(bean.getValueCurrency())); else editor.putString("valueCurrency", null);
    if (bean.getValueDate()!=null) editor.putString("valueDate",DateUtil.write(bean.getValueDate())); else editor.putString("valueDate", null);
    if (bean.getValueDouble()!=null) editor.putString("valueDouble",String.valueOf(bean.getValueDouble())); else editor.putString("valueDouble", null);
    editor.putString("valueDoubleType",String.valueOf(bean.getValueDoubleType()));
    if (bean.getValueEnumType()!=null) editor.putString("valueEnumType",bean.getValueEnumType().toString() ); else editor.putString("valueEnumType", null);
    if (bean.getValueFloat()!=null) editor.putFloat("valueFloat",bean.getValueFloat());
    editor.putFloat("valueFloatType",bean.getValueFloatType());
    if (bean.getValueInt()!=null) editor.putInt("valueInt",(int)bean.getValueInt());
    editor.putInt("valueIntType",(int)bean.getValueIntType());
    if (bean.getValueLocale()!=null) editor.putString("valueLocale",LocaleUtil.write(bean.getValueLocale())); else editor.putString("valueLocale", null);
    if (bean.getValueLong()!=null) editor.putLong("valueLong",bean.getValueLong());
    if (bean.getValueLongArray()!=null) editor.putString("valueLongArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueLongArray(), ArrayList.class))); else editor.putString("valueLongArray", null);
    if (bean.getValueLongList()!=null) editor.putString("valueLongList",ProcessorHelper.asString(bean.getValueLongList())); else editor.putString("valueLongList", null);
    editor.putLong("valueLongType",bean.getValueLongType());
    if (bean.getValueLongTypeArray()!=null) editor.putString("valueLongTypeArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueLongTypeArray(), ArrayList.class))); else editor.putString("valueLongTypeArray", null);
    if (bean.getValueShort()!=null) editor.putInt("valueShort",(int)bean.getValueShort());
    editor.putInt("valueShortType",(int)bean.getValueShortType());
    editor.putString("valueString",bean.getValueString());
    if (bean.getValueStringArray()!=null) editor.putString("valueStringArray",ProcessorHelper.asString(CollectionUtility.asList(bean.getValueStringArray(), ArrayList.class))); else editor.putString("valueStringArray", null);
    if (bean.getValueStrinList()!=null) editor.putString("valueStrinList",ProcessorHelper.asString(bean.getValueStrinList())); else editor.putString("valueStrinList", null);
    if (bean.getValueTime()!=null) editor.putString("valueTime",TimeUtil.write(bean.getValueTime())); else editor.putString("valueTime", null);
    if (bean.getValueTimeList()!=null) editor.putString("valueTimeList",ProcessorHelper.asString(bean.getValueTimeList())); else editor.putString("valueTimeList", null);
    if (bean.getValueTimeZone()!=null) editor.putString("valueTimeZone",TimeZoneUtil.write(bean.getValueTimeZone())); else editor.putString("valueTimeZone", null);
    if (bean.getValueUrl()!=null) editor.putString("valueUrl",UrlUtil.write(bean.getValueUrl())); else editor.putString("valueUrl", null);

    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long id() {
    return prefs.getLong("id", defaultBean.getId());
  }

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean2 valueBean() {
    return (prefs.getString("valueBean", null)!=null) ? (Bean2)readObj(prefs.getString("valueBean", null), Bean2.class): null;
  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean2[] valueBeanArray() {
     {
      // read valueBeanArray
      String tempValueBeanArray=prefs.getString("valueBeanArray", null);
      ArrayList<Bean2> collection=ProcessorHelper.asCollection(new ArrayList<Bean2>(), Bean2.class, tempValueBeanArray);
      return (StringUtility.hasText(tempValueBeanArray)) ? CollectionUtility.asArray(collection, new sqlite.kripton60.Bean2[collection.size()]): null;
    }

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
   * read property valueBigInteger
   *
   * @return property valueBigInteger value
   */
  public BigInteger valueBigInteger() {
    return (prefs.getString("valueBigInteger", null)!=null) ? new BigInteger(prefs.getString("valueBigInteger", "0")): null;
  }

  /**
   * read property valueBool
   *
   * @return property valueBool value
   */
  public Boolean valueBool() {
    return (boolean)prefs.getBoolean("valueBool", (boolean)(defaultBean.getValueBool()==null?false:defaultBean.getValueBool()));
  }

  /**
   * read property valueBoolType
   *
   * @return property valueBoolType value
   */
  public boolean valueBoolType() {
    return (boolean)prefs.getBoolean("valueBoolType", (boolean)defaultBean.isValueBoolType());
  }

  /**
   * read property valueByte
   *
   * @return property valueByte value
   */
  public Byte valueByte() {
    return (byte)prefs.getInt("valueByte", (byte)(defaultBean.getValueByte()==null?(byte)0:defaultBean.getValueByte()));
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
   * read property valueByteType
   *
   * @return property valueByteType value
   */
  public byte valueByteType() {
    return (byte)prefs.getInt("valueByteType", (byte)defaultBean.getValueByteType());
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
   * read property valueChar
   *
   * @return property valueChar value
   */
  public Character valueChar() {
    return (char)prefs.getInt("valueChar", (char)(defaultBean.getValueChar()==null?(char)0:defaultBean.getValueChar()));
  }

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] valueCharArray() {
     {
      // read valueCharArray
      String tempValueCharArray=prefs.getString("valueCharArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharArray);
      return (StringUtility.hasText(tempValueCharArray)) ? CollectionUtility.asCharacterArray(collection): null;
    }

  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> valueCharList() {
     {
      String temp=prefs.getString("valueCharList", null);
      return StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, temp): null;
    }

  }

  /**
   * read property valueCharType
   *
   * @return property valueCharType value
   */
  public char valueCharType() {
    return (char)prefs.getInt("valueCharType", (char)defaultBean.getValueCharType());
  }

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] valueCharTypeArray() {
     {
      // read valueCharTypeArray
      String tempValueCharTypeArray=prefs.getString("valueCharTypeArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharTypeArray);
      return (StringUtility.hasText(tempValueCharTypeArray)) ? CollectionUtility.asCharacterTypeArray(collection): null;
    }

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
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date valueDate() {
    return (prefs.getString("valueDate", null)!=null) ? DateUtil.read(prefs.getString("valueDate", null)): null;
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
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double valueDoubleType() {
    return (prefs.getString("valueDoubleType", null)!=null) ? Double.valueOf(prefs.getString("valueDoubleType", "0")): null;
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
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public Float valueFloat() {
    return prefs.getFloat("valueFloat", (defaultBean.getValueFloat()==null?0F:defaultBean.getValueFloat()));
  }

  /**
   * read property valueFloatType
   *
   * @return property valueFloatType value
   */
  public float valueFloatType() {
    return prefs.getFloat("valueFloatType", defaultBean.getValueFloatType());
  }

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public Integer valueInt() {
    return (int)prefs.getInt("valueInt", (int)(defaultBean.getValueInt()==null?0:defaultBean.getValueInt()));
  }

  /**
   * read property valueIntType
   *
   * @return property valueIntType value
   */
  public int valueIntType() {
    return (int)prefs.getInt("valueIntType", (int)defaultBean.getValueIntType());
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
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long valueLong() {
    return prefs.getLong("valueLong", (defaultBean.getValueLong()==null?0L:defaultBean.getValueLong()));
  }

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] valueLongArray() {
     {
      // read valueLongArray
      String tempValueLongArray=prefs.getString("valueLongArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongArray);
      return (StringUtility.hasText(tempValueLongArray)) ? CollectionUtility.asLongArray(collection): null;
    }

  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> valueLongList() {
     {
      String temp=prefs.getString("valueLongList", null);
      return StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, temp): null;
    }

  }

  /**
   * read property valueLongType
   *
   * @return property valueLongType value
   */
  public long valueLongType() {
    return prefs.getLong("valueLongType", defaultBean.getValueLongType());
  }

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] valueLongTypeArray() {
     {
      // read valueLongTypeArray
      String tempValueLongTypeArray=prefs.getString("valueLongTypeArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongTypeArray);
      return (StringUtility.hasText(tempValueLongTypeArray)) ? CollectionUtility.asLongTypeArray(collection): null;
    }

  }

  /**
   * read property valueShort
   *
   * @return property valueShort value
   */
  public Short valueShort() {
    return (short)prefs.getInt("valueShort", (short)(defaultBean.getValueShort()==null?(short)0:defaultBean.getValueShort()));
  }

  /**
   * read property valueShortType
   *
   * @return property valueShortType value
   */
  public short valueShortType() {
    return (short)prefs.getInt("valueShortType", (short)defaultBean.getValueShortType());
  }

  /**
   * read property valueString
   *
   * @return property valueString value
   */
  public String valueString() {
    return prefs.getString("valueString", defaultBean.getValueString());
  }

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] valueStringArray() {
     {
      // read valueStringArray
      String tempValueStringArray=prefs.getString("valueStringArray", null);
      ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempValueStringArray);
      return (StringUtility.hasText(tempValueStringArray)) ? CollectionUtility.asArray(collection, new java.lang.String[collection.size()]): null;
    }

  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> valueStrinList() {
     {
      String temp=prefs.getString("valueStrinList", null);
      return StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, temp): null;
    }

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
   * read property valueTimeList
   *
   * @return property valueTimeList value
   */
  public List<Time> valueTimeList() {
     {
      String temp=prefs.getString("valueTimeList", null);
      return StringUtility.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, temp): null;
    }

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
   * read property valueUrl
   *
   * @return property valueUrl value
   */
  public URL valueUrl() {
    return (prefs.getString("valueUrl", null)!=null) ? UrlUtil.read(prefs.getString("valueUrl", null)): null;
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
      editor.putLong("id",value);
      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean2 value) {
      if (value!=null) editor.putString("valueBean",writeObj(value)); else editor.putString("valueBean", null);
      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean2[] value) {
      if (value!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueBeanArray", null);
      return this;
    }

    /**
     * modifier for property valueBigDecimal
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("valueBigDecimal",value.toPlainString()); else editor.putString("valueBigDecimal", null);;
      return this;
    }

    /**
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("valueBigInteger",value.toString()); else editor.putString("valueBigInteger", null);;
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
     * modifier for property valueBoolType
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("valueBoolType",(boolean)value);
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
     * modifier for property valueByteArray
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null) editor.putString("valueByteArray",Base64Util.encode(value)); else editor.putString("valueByteArray", null);
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
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null) editor.putString("valueCalendar",CalendarUtil.write(value)); else editor.putString("valueCalendar", null);
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
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtility.asList(value, ArrayList.class))); else editor.putString("valueCharArray", null);
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
     * modifier for property valueCharType
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("valueCharType",(char)value);
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
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null) editor.putString("valueCurrency",CurrencyUtil.write(value)); else editor.putString("valueCurrency", null);
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
     * modifier for property valueDouble
     */
    public BindEditor putValueDouble(Double value) {
      if (value!=null) editor.putString("valueDouble",String.valueOf(value)); else editor.putString("valueDouble", null);
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
     * modifier for property valueEnumType
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null) editor.putString("valueEnumType",value.toString()); else editor.putString("valueEnumType", null);
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
     * modifier for property valueFloatType
     */
    public BindEditor putValueFloatType(float value) {
      editor.putFloat("valueFloatType",value);
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
     * modifier for property valueIntType
     */
    public BindEditor putValueIntType(int value) {
      editor.putInt("valueIntType",(int)value);
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
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null) editor.putLong("valueLong",value);
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
     * modifier for property valueLongList
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null) editor.putString("valueLongList",ProcessorHelper.asString(value)); else editor.putString("valueLongList", null);
      return this;
    }

    /**
     * modifier for property valueLongType
     */
    public BindEditor putValueLongType(long value) {
      editor.putLong("valueLongType",value);
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
     * modifier for property valueShort
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null) editor.putInt("valueShort",(int)value);
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
     * modifier for property valueString
     */
    public BindEditor putValueString(String value) {
      editor.putString("valueString",value);
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
     * modifier for property valueStrinList
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null) editor.putString("valueStrinList",ProcessorHelper.asString(value)); else editor.putString("valueStrinList", null);
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
     * modifier for property valueTimeList
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null) editor.putString("valueTimeList",ProcessorHelper.asString(value)); else editor.putString("valueTimeList", null);
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
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null) editor.putString("valueUrl",UrlUtil.write(value)); else editor.putString("valueUrl", null);
      return this;
    }
  }
}

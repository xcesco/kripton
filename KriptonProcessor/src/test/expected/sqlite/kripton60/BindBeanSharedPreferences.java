package sqlite.kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
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
     {
      String temp=prefs.getString("valueBigInteger", "0");
      bean.valueBigInteger=(StringUtils.hasText(temp)) ? new BigInteger(temp): null;
    }

     {
      String temp=prefs.getString("valueBigDecimal", "0");
      bean.valueBigDecimal=(StringUtils.hasText(temp)) ? new BigDecimal(temp): null;
    }

     {
      String temp=prefs.getString("valueEnumType", null);
      bean.valueEnumType=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;
    }

    bean.valueLongType=prefs.getLong("valueLongType", bean.valueLongType);
    bean.valueLong=prefs.getLong("valueLong", (bean.valueLong==null?0L:bean.valueLong));
     {
      String temp=prefs.getString("valueDoubleType", null);
      bean.valueDoubleType=(StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;
    }

     {
      String temp=prefs.getString("valueDouble", null);
      bean.valueDouble=(StringUtils.hasText(temp)) ? Double.valueOf(temp): null;
    }

     {
      String temp=prefs.getString("valueLocale", null);
      bean.valueLocale=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueCalendar", null);
      bean.valueCalendar=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueDate", null);
      bean.valueDate=(StringUtils.hasText(temp)) ? DateUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueUrl", null);
      bean.valueUrl=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueTime", null);
      bean.valueTime=(StringUtils.hasText(temp)) ? TimeUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueCurrency", null);
      bean.valueCurrency=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueTimeZone", null);
      bean.valueTimeZone=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;}

     {
      String temp=prefs.getString("valueTimeList", null);
      bean.valueTimeList=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, temp): null;
    }

     {
      String temp=prefs.getString("valueStrinList", null);
      bean.valueStrinList=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, temp): null;
    }

     {
      String temp=prefs.getString("valueLongList", null);
      bean.valueLongList=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, temp): null;
    }

     {
      String temp=prefs.getString("valueByteArray", null);
      bean.valueByteArray=(StringUtils.hasText(temp)) ? Base64Utils.decode(temp): null;
    }

     {
      String temp=prefs.getString("valueBean", null);
      bean.valueBean=(StringUtils.hasText(temp)) ? (Bean)readObj(temp, Bean.class): null;}

     {
      // read valueLongTypeArray
      String tempValueLongTypeArray=prefs.getString("valueLongTypeArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongTypeArray);
      bean.valueLongTypeArray=(StringUtils.hasText(tempValueLongTypeArray)) ? CollectionUtils.asLongTypeArray(collection): null;
    }

     {
      // read valueLongArray
      String tempValueLongArray=prefs.getString("valueLongArray", null);
      ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongArray);
      bean.valueLongArray=(StringUtils.hasText(tempValueLongArray)) ? CollectionUtils.asLongArray(collection): null;
    }

     {
      // read valueBeanArray
      String tempValueBeanArray=prefs.getString("valueBeanArray", null);
      ArrayList<Bean> collection=ProcessorHelper.asCollection(new ArrayList<Bean>(), Bean.class, tempValueBeanArray);
      bean.valueBeanArray=(StringUtils.hasText(tempValueBeanArray)) ? CollectionUtils.asArray(collection, new sqlite.kripton60.Bean[collection.size()]): null;
    }

     {
      // read valueStringArray
      String tempValueStringArray=prefs.getString("valueStringArray", null);
      ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempValueStringArray);
      bean.valueStringArray=(StringUtils.hasText(tempValueStringArray)) ? CollectionUtils.asArray(collection, new java.lang.String[collection.size()]): null;
    }

     {
      String temp=prefs.getString("valueCharList", null);
      bean.valueCharList=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, temp): null;
    }

     {
      // read valueCharTypeArray
      String tempValueCharTypeArray=prefs.getString("valueCharTypeArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharTypeArray);
      bean.valueCharTypeArray=(StringUtils.hasText(tempValueCharTypeArray)) ? CollectionUtils.asCharacterTypeArray(collection): null;
    }

     {
      // read valueCharArray
      String tempValueCharArray=prefs.getString("valueCharArray", null);
      ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharArray);
      bean.valueCharArray=(StringUtils.hasText(tempValueCharArray)) ? CollectionUtils.asCharacterArray(collection): null;
    }

     {
      String temp=prefs.getString("valueMapStringBean", null);
      bean.valueMapStringBean=(StringUtils.hasText(temp)) ? ProcessorHelper.asMap(new HashMap<String, Bean>(), String.class, Bean.class, temp): null;}

     {
      String temp=prefs.getString("valueLinkedMapStringBean", null);
      bean.valueLinkedMapStringBean=(StringUtils.hasText(temp)) ? ProcessorHelper.asMap(new LinkedHashMap<String, Bean>(), String.class, Bean.class, temp): null;}

     {
      String temp=prefs.getString("valueSetString", null);
      bean.valueSetString=StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, temp): null;
    }


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
    if (bean.valueBigInteger!=null) editor.putString("valueBigInteger",bean.valueBigInteger.toString() ); else editor.putString("valueBigInteger", null);;
    if (bean.valueBigDecimal!=null) editor.putString("valueBigDecimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("valueBigDecimal", null);;
    if (bean.valueEnumType!=null) editor.putString("valueEnumType",bean.valueEnumType.toString() ); else editor.putString("valueEnumType", null);
    editor.putLong("valueLongType",bean.valueLongType);
    if (bean.valueLong!=null) editor.putLong("valueLong",bean.valueLong);
    editor.putString("valueDoubleType",String.valueOf(bean.valueDoubleType));
    if (bean.valueDouble!=null) editor.putString("valueDouble",String.valueOf(bean.valueDouble)); else editor.putString("valueDouble", null);
    if (bean.valueLocale!=null) editor.putString("valueLocale",LocaleUtils.write(bean.valueLocale)); else editor.putString("valueLocale", null);
    if (bean.valueCalendar!=null) editor.putString("valueCalendar",CalendarUtils.write(bean.valueCalendar)); else editor.putString("valueCalendar", null);
    if (bean.valueDate!=null) editor.putString("valueDate",DateUtils.write(bean.valueDate)); else editor.putString("valueDate", null);
    if (bean.valueUrl!=null) editor.putString("valueUrl",UrlUtils.write(bean.valueUrl)); else editor.putString("valueUrl", null);
    if (bean.valueTime!=null) editor.putString("valueTime",TimeUtils.write(bean.valueTime)); else editor.putString("valueTime", null);
    if (bean.valueCurrency!=null) editor.putString("valueCurrency",CurrencyUtils.write(bean.valueCurrency)); else editor.putString("valueCurrency", null);
    if (bean.valueTimeZone!=null) editor.putString("valueTimeZone",TimeZoneUtils.write(bean.valueTimeZone)); else editor.putString("valueTimeZone", null);
    if (bean.valueTimeList!=null) editor.putString("valueTimeList",ProcessorHelper.asString(bean.valueTimeList)); else editor.putString("valueTimeList", null);
    if (bean.valueStrinList!=null) editor.putString("valueStrinList",ProcessorHelper.asString(bean.valueStrinList)); else editor.putString("valueStrinList", null);
    if (bean.valueLongList!=null) editor.putString("valueLongList",ProcessorHelper.asString(bean.valueLongList)); else editor.putString("valueLongList", null);
    if (bean.valueByteArray!=null) editor.putString("valueByteArray",Base64Utils.encode(bean.valueByteArray)); else editor.putString("valueByteArray", null);
    if (bean.valueBean!=null) editor.putString("valueBean",writeObj(bean.valueBean)); else editor.putString("valueBean", null);
    if (bean.valueLongTypeArray!=null) editor.putString("valueLongTypeArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueLongTypeArray, ArrayList.class))); else editor.putString("valueLongTypeArray", null);
    if (bean.valueLongArray!=null) editor.putString("valueLongArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueLongArray, ArrayList.class))); else editor.putString("valueLongArray", null);
    if (bean.valueBeanArray!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueBeanArray, ArrayList.class))); else editor.putString("valueBeanArray", null);
    if (bean.valueStringArray!=null) editor.putString("valueStringArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueStringArray, ArrayList.class))); else editor.putString("valueStringArray", null);
    if (bean.valueCharList!=null) editor.putString("valueCharList",ProcessorHelper.asString(bean.valueCharList)); else editor.putString("valueCharList", null);
    if (bean.valueCharTypeArray!=null) editor.putString("valueCharTypeArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueCharTypeArray, ArrayList.class))); else editor.putString("valueCharTypeArray", null);
    if (bean.valueCharArray!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtils.asList(bean.valueCharArray, ArrayList.class))); else editor.putString("valueCharArray", null);
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
    String temp=prefs.getString("valueBigInteger", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): null;

  }

  /**
   * read property valueBigDecimal
   *
   * @return property valueBigDecimal value
   */
  public BigDecimal valueBigDecimal() {
    String temp=prefs.getString("valueBigDecimal", "0");
    return (StringUtils.hasText(temp)) ? new BigDecimal(temp): null;

  }

  /**
   * read property valueEnumType
   *
   * @return property valueEnumType value
   */
  public EnumType valueEnumType() {
    String temp=prefs.getString("valueEnumType", null);
    return (StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;

  }

  /**
   * read property valueLongType
   *
   * @return property valueLongType value
   */
  public long valueLongType() {
    return prefs.getLong("valueLongType", defaultBean.valueLongType);
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
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double valueDoubleType() {
    String temp=prefs.getString("valueDoubleType", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;

  }

  /**
   * read property valueDouble
   *
   * @return property valueDouble value
   */
  public Double valueDouble() {
    String temp=prefs.getString("valueDouble", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): null;

  }

  /**
   * read property valueLocale
   *
   * @return property valueLocale value
   */
  public Locale valueLocale() {
    String temp=prefs.getString("valueLocale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;
  }

  /**
   * read property valueCalendar
   *
   * @return property valueCalendar value
   */
  public Calendar valueCalendar() {
    String temp=prefs.getString("valueCalendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;
  }

  /**
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date valueDate() {
    String temp=prefs.getString("valueDate", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): null;
  }

  /**
   * read property valueUrl
   *
   * @return property valueUrl value
   */
  public URL valueUrl() {
    String temp=prefs.getString("valueUrl", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;
  }

  /**
   * read property valueTime
   *
   * @return property valueTime value
   */
  public Time valueTime() {
    String temp=prefs.getString("valueTime", null);
    return (StringUtils.hasText(temp)) ? TimeUtils.read(temp): null;
  }

  /**
   * read property valueCurrency
   *
   * @return property valueCurrency value
   */
  public Currency valueCurrency() {
    String temp=prefs.getString("valueCurrency", null);
    return (StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;
  }

  /**
   * read property valueTimeZone
   *
   * @return property valueTimeZone value
   */
  public TimeZone valueTimeZone() {
    String temp=prefs.getString("valueTimeZone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;
  }

  /**
   * read property valueTimeList
   *
   * @return property valueTimeList value
   */
  public List<Time> valueTimeList() {
    String temp=prefs.getString("valueTimeList", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, temp): null;

  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> valueStrinList() {
    String temp=prefs.getString("valueStrinList", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<String>(), String.class, temp): null;

  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> valueLongList() {
    String temp=prefs.getString("valueLongList", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, temp): null;

  }

  /**
   * read property valueByteArray
   *
   * @return property valueByteArray value
   */
  public byte[] valueByteArray() {
    String temp=prefs.getString("valueByteArray", null);
    return (StringUtils.hasText(temp)) ? Base64Utils.decode(temp): null;

  }

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean valueBean() {
    String temp=prefs.getString("valueBean", null);
    return (StringUtils.hasText(temp)) ? (Bean)readObj(temp, Bean.class): null;
  }

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] valueLongTypeArray() {
    // read valueLongTypeArray
    String tempValueLongTypeArray=prefs.getString("valueLongTypeArray", null);
    ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongTypeArray);
    return (StringUtils.hasText(tempValueLongTypeArray)) ? CollectionUtils.asLongTypeArray(collection): null;

  }

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] valueLongArray() {
    // read valueLongArray
    String tempValueLongArray=prefs.getString("valueLongArray", null);
    ArrayList<Long> collection=ProcessorHelper.asCollection(new ArrayList<Long>(), Long.class, tempValueLongArray);
    return (StringUtils.hasText(tempValueLongArray)) ? CollectionUtils.asLongArray(collection): null;

  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean[] valueBeanArray() {
    // read valueBeanArray
    String tempValueBeanArray=prefs.getString("valueBeanArray", null);
    ArrayList<Bean> collection=ProcessorHelper.asCollection(new ArrayList<Bean>(), Bean.class, tempValueBeanArray);
    return (StringUtils.hasText(tempValueBeanArray)) ? CollectionUtils.asArray(collection, new sqlite.kripton60.Bean[collection.size()]): null;

  }

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] valueStringArray() {
    // read valueStringArray
    String tempValueStringArray=prefs.getString("valueStringArray", null);
    ArrayList<String> collection=ProcessorHelper.asCollection(new ArrayList<String>(), String.class, tempValueStringArray);
    return (StringUtils.hasText(tempValueStringArray)) ? CollectionUtils.asArray(collection, new java.lang.String[collection.size()]): null;

  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> valueCharList() {
    String temp=prefs.getString("valueCharList", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, temp): null;

  }

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] valueCharTypeArray() {
    // read valueCharTypeArray
    String tempValueCharTypeArray=prefs.getString("valueCharTypeArray", null);
    ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharTypeArray);
    return (StringUtils.hasText(tempValueCharTypeArray)) ? CollectionUtils.asCharacterTypeArray(collection): null;

  }

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] valueCharArray() {
    // read valueCharArray
    String tempValueCharArray=prefs.getString("valueCharArray", null);
    ArrayList<Character> collection=ProcessorHelper.asCollection(new ArrayList<Character>(), Character.class, tempValueCharArray);
    return (StringUtils.hasText(tempValueCharArray)) ? CollectionUtils.asCharacterArray(collection): null;

  }

  /**
   * read property valueMapStringBean
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> valueMapStringBean() {
    String temp=prefs.getString("valueMapStringBean", null);
    return (StringUtils.hasText(temp)) ? ProcessorHelper.asMap(new HashMap<String, Bean>(), String.class, Bean.class, temp): null;
  }

  /**
   * read property valueLinkedMapStringBean
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> valueLinkedMapStringBean() {
    String temp=prefs.getString("valueLinkedMapStringBean", null);
    return (StringUtils.hasText(temp)) ? ProcessorHelper.asMap(new LinkedHashMap<String, Bean>(), String.class, Bean.class, temp): null;
  }

  /**
   * read property valueSetString
   *
   * @return property valueSetString value
   */
  public Set<String> valueSetString() {
    String temp=prefs.getString("valueSetString", null);
    return StringUtils.hasText(temp) ? ProcessorHelper.asCollection(new HashSet<String>(), String.class, temp): null;

  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindBeanSharedPreferences instance() {
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
      if (value!=null) editor.putString("valueBigInteger",value.toString()); else editor.putString("valueBigInteger", null);;
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
      editor.putLong("valueLongType",value);
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
      if (value!=null) editor.putString("valueLocale",LocaleUtils.write(value)); else editor.putString("valueLocale", null);
      return this;
    }

    /**
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null) editor.putString("valueCalendar",CalendarUtils.write(value)); else editor.putString("valueCalendar", null);
      return this;
    }

    /**
     * modifier for property valueDate
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null) editor.putString("valueDate",DateUtils.write(value)); else editor.putString("valueDate", null);
      return this;
    }

    /**
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null) editor.putString("valueUrl",UrlUtils.write(value)); else editor.putString("valueUrl", null);
      return this;
    }

    /**
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null) editor.putString("valueTime",TimeUtils.write(value)); else editor.putString("valueTime", null);
      return this;
    }

    /**
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null) editor.putString("valueCurrency",CurrencyUtils.write(value)); else editor.putString("valueCurrency", null);
      return this;
    }

    /**
     * modifier for property valueTimeZone
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null) editor.putString("valueTimeZone",TimeZoneUtils.write(value)); else editor.putString("valueTimeZone", null);
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
      if (value!=null) editor.putString("valueByteArray",Base64Utils.encode(value)); else editor.putString("valueByteArray", null);
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
      if (value!=null) editor.putString("valueLongTypeArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueLongTypeArray", null);
      return this;
    }

    /**
     * modifier for property valueLongArray
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null) editor.putString("valueLongArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueLongArray", null);
      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null) editor.putString("valueBeanArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueBeanArray", null);
      return this;
    }

    /**
     * modifier for property valueStringArray
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null) editor.putString("valueStringArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueStringArray", null);
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
      if (value!=null) editor.putString("valueCharTypeArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueCharTypeArray", null);
      return this;
    }

    /**
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null) editor.putString("valueCharArray",ProcessorHelper.asString(CollectionUtils.asList(value, ArrayList.class))); else editor.putString("valueCharArray", null);
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

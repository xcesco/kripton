package sqlite.kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Exception;
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
   * BeanBindMap */
  private BeanBindMap beanBindMap = AbstractContext.mapperFor(Bean.class);

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
      bean.valueTimeList=StringUtils.hasText(temp) ? parseValueTimeList(temp): null;
    }

     {
      String temp=prefs.getString("valueStrinList", null);
      bean.valueStrinList=StringUtils.hasText(temp) ? parseValueStrinList(temp): null;
    }

     {
      String temp=prefs.getString("valueLongList", null);
      bean.valueLongList=StringUtils.hasText(temp) ? parseValueLongList(temp): null;
    }

     {
      String temp=prefs.getString("valueByteArray", null);
      bean.valueByteArray=StringUtils.hasText(temp) ? parseValueByteArray(temp): null;
    }

     {
      String temp=prefs.getString("valueBean", null);
      bean.valueBean=StringUtils.hasText(temp) ? parseValueBean(temp): null;
    }

     {
      String temp=prefs.getString("valueLongTypeArray", null);
      bean.valueLongTypeArray=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null;
    }

     {
      String temp=prefs.getString("valueLongArray", null);
      bean.valueLongArray=StringUtils.hasText(temp) ? parseValueLongArray(temp): null;
    }

     {
      String temp=prefs.getString("valueBeanArray", null);
      bean.valueBeanArray=StringUtils.hasText(temp) ? parseValueBeanArray(temp): null;
    }

     {
      String temp=prefs.getString("valueStringArray", null);
      bean.valueStringArray=StringUtils.hasText(temp) ? parseValueStringArray(temp): null;
    }

     {
      String temp=prefs.getString("valueCharList", null);
      bean.valueCharList=StringUtils.hasText(temp) ? parseValueCharList(temp): null;
    }

     {
      String temp=prefs.getString("valueCharTypeArray", null);
      bean.valueCharTypeArray=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null;
    }

     {
      String temp=prefs.getString("valueCharArray", null);
      bean.valueCharArray=StringUtils.hasText(temp) ? parseValueCharArray(temp): null;
    }

     {
      String temp=prefs.getString("valueMapStringBean", null);
      bean.valueMapStringBean=StringUtils.hasText(temp) ? parseValueMapStringBean(temp): null;
    }

     {
      String temp=prefs.getString("valueLinkedMapStringBean", null);
      bean.valueLinkedMapStringBean=StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): null;
    }

     {
      String temp=prefs.getString("valueSetString", null);
      bean.valueSetString=StringUtils.hasText(temp) ? parseValueSetString(temp): null;
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

    if (bean.valueBool!=null)  {
      editor.putBoolean("valueBool",(boolean)bean.valueBool);
    }

    editor.putInt("valueByteType",(int)bean.valueByteType);

    if (bean.valueByte!=null)  {
      editor.putInt("valueByte",(int)bean.valueByte);
    }

    editor.putInt("valueShortType",(int)bean.valueShortType);

    if (bean.valueShort!=null)  {
      editor.putInt("valueShort",(int)bean.valueShort);
    }

    editor.putInt("valueIntType",(int)bean.valueIntType);

    if (bean.valueInt!=null)  {
      editor.putInt("valueInt",(int)bean.valueInt);
    }

    editor.putString("valueString",bean.valueString);

    editor.putInt("valueCharType",(char)bean.valueCharType);

    if (bean.valueChar!=null)  {
      editor.putInt("valueChar",(char)bean.valueChar);
    }

    editor.putFloat("valueFloatType",bean.valueFloatType);

    if (bean.valueFloat!=null)  {
      editor.putFloat("valueFloat",bean.valueFloat);
    }

    if (bean.valueBigInteger!=null) editor.putString("valueBigInteger",bean.valueBigInteger.toString() ); else editor.putString("valueBigInteger", null);
    if (bean.valueBigDecimal!=null) editor.putString("valueBigDecimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("valueBigDecimal", null);
    if (bean.valueEnumType!=null)  {
      editor.putString("valueEnumType",bean.valueEnumType.toString() );
    } else {
      editor.remove("valueEnumType");
    }

    editor.putLong("valueLongType",bean.valueLongType);

    if (bean.valueLong!=null)  {
      editor.putLong("valueLong",bean.valueLong);
    }

    editor.putString("valueDoubleType",String.valueOf(bean.valueDoubleType));

    if (bean.valueDouble!=null)  {
      editor.putString("valueDouble",String.valueOf(bean.valueDouble));
    } else {
      editor.remove("valueDouble");
    }

    if (bean.valueLocale!=null)  {
      editor.putString("valueLocale",LocaleUtils.write(bean.valueLocale));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.valueCalendar!=null)  {
      editor.putString("valueCalendar",CalendarUtils.write(bean.valueCalendar));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.valueDate!=null)  {
      editor.putString("valueDate",DateUtils.write(bean.valueDate));
    } else {
      editor.remove("valueDate");
    }

    if (bean.valueUrl!=null)  {
      editor.putString("valueUrl",UrlUtils.write(bean.valueUrl));
    } else {
      editor.remove("valueUrl");
    }

    if (bean.valueTime!=null)  {
      editor.putString("valueTime",TimeUtils.write(bean.valueTime));
    } else {
      editor.remove("valueTime");
    }

    if (bean.valueCurrency!=null)  {
      editor.putString("valueCurrency",CurrencyUtils.write(bean.valueCurrency));
    } else {
      editor.remove("valueCurrency");
    }

    if (bean.valueTimeZone!=null)  {
      editor.putString("valueTimeZone",TimeZoneUtils.write(bean.valueTimeZone));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.valueTimeList!=null)  {
      String temp=serializeValueTimeList(bean.valueTimeList);
      editor.putString("valueTimeList",temp);
    }  else  {
      editor.remove("valueTimeList");
    }

    if (bean.valueStrinList!=null)  {
      String temp=serializeValueStrinList(bean.valueStrinList);
      editor.putString("valueStrinList",temp);
    }  else  {
      editor.remove("valueStrinList");
    }

    if (bean.valueLongList!=null)  {
      String temp=serializeValueLongList(bean.valueLongList);
      editor.putString("valueLongList",temp);
    }  else  {
      editor.remove("valueLongList");
    }

    if (bean.valueByteArray!=null)  {
      String temp=serializeValueByteArray(bean.valueByteArray);
      editor.putString("valueByteArray",temp);
    }  else  {
      editor.remove("valueByteArray");
    }

    if (bean.valueBean!=null)  {
      String temp=serializeValueBean(bean.valueBean);
      editor.putString("valueBean",temp);
    }  else  {
      editor.remove("valueBean");
    }

    if (bean.valueLongTypeArray!=null)  {
      String temp=serializeValueLongTypeArray(bean.valueLongTypeArray);
      editor.putString("valueLongTypeArray",temp);
    }  else  {
      editor.remove("valueLongTypeArray");
    }

    if (bean.valueLongArray!=null)  {
      String temp=serializeValueLongArray(bean.valueLongArray);
      editor.putString("valueLongArray",temp);
    }  else  {
      editor.remove("valueLongArray");
    }

    if (bean.valueBeanArray!=null)  {
      String temp=serializeValueBeanArray(bean.valueBeanArray);
      editor.putString("valueBeanArray",temp);
    }  else  {
      editor.remove("valueBeanArray");
    }

    if (bean.valueStringArray!=null)  {
      String temp=serializeValueStringArray(bean.valueStringArray);
      editor.putString("valueStringArray",temp);
    }  else  {
      editor.remove("valueStringArray");
    }

    if (bean.valueCharList!=null)  {
      String temp=serializeValueCharList(bean.valueCharList);
      editor.putString("valueCharList",temp);
    }  else  {
      editor.remove("valueCharList");
    }

    if (bean.valueCharTypeArray!=null)  {
      String temp=serializeValueCharTypeArray(bean.valueCharTypeArray);
      editor.putString("valueCharTypeArray",temp);
    }  else  {
      editor.remove("valueCharTypeArray");
    }

    if (bean.valueCharArray!=null)  {
      String temp=serializeValueCharArray(bean.valueCharArray);
      editor.putString("valueCharArray",temp);
    }  else  {
      editor.remove("valueCharArray");
    }

    if (bean.valueMapStringBean!=null)  {
      String temp=serializeValueMapStringBean(bean.valueMapStringBean);
      editor.putString("valueMapStringBean",temp);
    }  else  {
      editor.remove("valueMapStringBean");
    }

    if (bean.valueLinkedMapStringBean!=null)  {
      String temp=serializeValueLinkedMapStringBean(bean.valueLinkedMapStringBean);
      editor.putString("valueLinkedMapStringBean",temp);
    }  else  {
      editor.remove("valueLinkedMapStringBean");
    }

    if (bean.valueSetString!=null)  {
      String temp=serializeValueSetString(bean.valueSetString);
      editor.putString("valueSetString",temp);
    }  else  {
      editor.remove("valueSetString");
    }


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
    return StringUtils.hasText(temp) ? parseValueTimeList(temp): null;

  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> valueStrinList() {
    String temp=prefs.getString("valueStrinList", null);
    return StringUtils.hasText(temp) ? parseValueStrinList(temp): null;

  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> valueLongList() {
    String temp=prefs.getString("valueLongList", null);
    return StringUtils.hasText(temp) ? parseValueLongList(temp): null;

  }

  /**
   * read property valueByteArray
   *
   * @return property valueByteArray value
   */
  public byte[] valueByteArray() {
    String temp=prefs.getString("valueByteArray", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): null;

  }

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean valueBean() {
    String temp=prefs.getString("valueBean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): null;

  }

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] valueLongTypeArray() {
    String temp=prefs.getString("valueLongTypeArray", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null;

  }

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] valueLongArray() {
    String temp=prefs.getString("valueLongArray", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): null;

  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean[] valueBeanArray() {
    String temp=prefs.getString("valueBeanArray", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): null;

  }

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] valueStringArray() {
    String temp=prefs.getString("valueStringArray", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): null;

  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> valueCharList() {
    String temp=prefs.getString("valueCharList", null);
    return StringUtils.hasText(temp) ? parseValueCharList(temp): null;

  }

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] valueCharTypeArray() {
    String temp=prefs.getString("valueCharTypeArray", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null;

  }

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] valueCharArray() {
    String temp=prefs.getString("valueCharArray", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): null;

  }

  /**
   * read property valueMapStringBean
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> valueMapStringBean() {
    String temp=prefs.getString("valueMapStringBean", null);
    return StringUtils.hasText(temp) ? parseValueMapStringBean(temp): null;

  }

  /**
   * read property valueLinkedMapStringBean
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> valueLinkedMapStringBean() {
    String temp=prefs.getString("valueLinkedMapStringBean", null);
    return StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): null;

  }

  /**
   * read property valueSetString
   *
   * @return property valueSetString value
   */
  public Set<String> valueSetString() {
    String temp=prefs.getString("valueSetString", null);
    return StringUtils.hasText(temp) ? parseValueSetString(temp): null;

  }

  /**
   * write
   */
  protected String serializeValueTimeList(List<Time> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Time item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueTimeList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(TimeUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected List<Time> parseValueTimeList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Time> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Time> collection=new ArrayList<>();
        Time item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=TimeUtils.read(jacksonParser.getText());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueStrinList(LinkedList<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStrinList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected LinkedList<String> parseValueStrinList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<String> collection=new LinkedList<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueLongList(LinkedList<Long> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected LinkedList<Long> parseValueLongList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Long> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Long> collection=new LinkedList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueByteArray(byte[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteArray", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected byte[] parseValueByteArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueBean(Bean value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        beanBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Bean parseValueBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=beanBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueLongTypeArray(long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected long[] parseValueLongTypeArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueLongArray(Long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Long[] parseValueLongArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueBeanArray(Bean[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Bean item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            beanBindMap.serializeOnJackson(item, jacksonSerializer);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Bean[] parseValueBeanArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Bean[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Bean> collection=new ArrayList<>();
        Bean item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueStringArray(String[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected String[] parseValueStringArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      String[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<String> collection=new ArrayList<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new String[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueCharList(LinkedList<Character> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected LinkedList<Character> parseValueCharList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Character> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Character> collection=new LinkedList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueCharTypeArray(char[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected char[] parseValueCharTypeArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      char[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueCharArray(Character[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Character[] parseValueCharArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Character[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueMapStringBean(Map<String, Bean> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Map<String, Bean> parseValueMapStringBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Map<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Bean> collection=new HashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueLinkedMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueLinkedMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected LinkedHashMap<String, Bean> parseValueLinkedMapStringBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedHashMap<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedHashMap<String, Bean> collection=new LinkedHashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected String serializeValueSetString(Set<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueSetString");
        jacksonSerializer.writeStartArray();
        for (String item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Set<String> parseValueSetString(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Set<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<String> collection=new HashSet<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
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
      if (value!=null)  {
        editor.putBoolean("valueBool",(boolean)value);
      }

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
      if (value!=null)  {
        editor.putInt("valueByte",(int)value);
      }

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
      if (value!=null)  {
        editor.putInt("valueShort",(int)value);
      }

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
      if (value!=null)  {
        editor.putInt("valueInt",(int)value);
      }

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
      if (value!=null)  {
        editor.putInt("valueChar",(char)value);
      }

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
      if (value!=null)  {
        editor.putFloat("valueFloat",value);
      }

      return this;
    }

    /**
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("valueBigInteger",value.toString()); else editor.remove("valueBigInteger");
      return this;
    }

    /**
     * modifier for property valueBigDecimal
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("valueBigDecimal",value.toPlainString()); else editor.remove("valueBigDecimal");
      return this;
    }

    /**
     * modifier for property valueEnumType
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null)  {
        editor.putString("valueEnumType",value.toString() );
      } else {
        editor.remove("valueEnumType");
      }

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
      if (value!=null)  {
        editor.putLong("valueLong",value);
      }

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
      if (value!=null)  {
        editor.putString("valueDouble",String.valueOf(value));
      } else {
        editor.remove("valueDouble");
      }

      return this;
    }

    /**
     * modifier for property valueLocale
     */
    public BindEditor putValueLocale(Locale value) {
      if (value!=null)  {
        editor.putString("valueLocale",LocaleUtils.write(value));
      } else {
        editor.remove("valueLocale");
      }

      return this;
    }

    /**
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null)  {
        editor.putString("valueCalendar",CalendarUtils.write(value));
      } else {
        editor.remove("valueCalendar");
      }

      return this;
    }

    /**
     * modifier for property valueDate
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null)  {
        editor.putString("valueDate",DateUtils.write(value));
      } else {
        editor.remove("valueDate");
      }

      return this;
    }

    /**
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null)  {
        editor.putString("valueUrl",UrlUtils.write(value));
      } else {
        editor.remove("valueUrl");
      }

      return this;
    }

    /**
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null)  {
        editor.putString("valueTime",TimeUtils.write(value));
      } else {
        editor.remove("valueTime");
      }

      return this;
    }

    /**
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null)  {
        editor.putString("valueCurrency",CurrencyUtils.write(value));
      } else {
        editor.remove("valueCurrency");
      }

      return this;
    }

    /**
     * modifier for property valueTimeZone
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null)  {
        editor.putString("valueTimeZone",TimeZoneUtils.write(value));
      } else {
        editor.remove("valueTimeZone");
      }

      return this;
    }

    /**
     * modifier for property valueTimeList
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null)  {
        String temp=serializeValueTimeList(value);
        editor.putString("valueTimeList",temp);
      }  else  {
        editor.remove("valueTimeList");
      }

      return this;
    }

    /**
     * modifier for property valueStrinList
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null)  {
        String temp=serializeValueStrinList(value);
        editor.putString("valueStrinList",temp);
      }  else  {
        editor.remove("valueStrinList");
      }

      return this;
    }

    /**
     * modifier for property valueLongList
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null)  {
        String temp=serializeValueLongList(value);
        editor.putString("valueLongList",temp);
      }  else  {
        editor.remove("valueLongList");
      }

      return this;
    }

    /**
     * modifier for property valueByteArray
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null)  {
        String temp=serializeValueByteArray(value);
        editor.putString("valueByteArray",temp);
      }  else  {
        editor.remove("valueByteArray");
      }

      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("valueBean",temp);
      }  else  {
        editor.remove("valueBean");
      }

      return this;
    }

    /**
     * modifier for property valueLongTypeArray
     */
    public BindEditor putValueLongTypeArray(long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongTypeArray(value);
        editor.putString("valueLongTypeArray",temp);
      }  else  {
        editor.remove("valueLongTypeArray");
      }

      return this;
    }

    /**
     * modifier for property valueLongArray
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongArray(value);
        editor.putString("valueLongArray",temp);
      }  else  {
        editor.remove("valueLongArray");
      }

      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("valueBeanArray",temp);
      }  else  {
        editor.remove("valueBeanArray");
      }

      return this;
    }

    /**
     * modifier for property valueStringArray
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeValueStringArray(value);
        editor.putString("valueStringArray",temp);
      }  else  {
        editor.remove("valueStringArray");
      }

      return this;
    }

    /**
     * modifier for property valueCharList
     */
    public BindEditor putValueCharList(LinkedList<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharList(value);
        editor.putString("valueCharList",temp);
      }  else  {
        editor.remove("valueCharList");
      }

      return this;
    }

    /**
     * modifier for property valueCharTypeArray
     */
    public BindEditor putValueCharTypeArray(char[] value) {
      if (value!=null)  {
        String temp=serializeValueCharTypeArray(value);
        editor.putString("valueCharTypeArray",temp);
      }  else  {
        editor.remove("valueCharTypeArray");
      }

      return this;
    }

    /**
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null)  {
        String temp=serializeValueCharArray(value);
        editor.putString("valueCharArray",temp);
      }  else  {
        editor.remove("valueCharArray");
      }

      return this;
    }

    /**
     * modifier for property valueMapStringBean
     */
    public BindEditor putValueMapStringBean(Map<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueMapStringBean(value);
        editor.putString("valueMapStringBean",temp);
      }  else  {
        editor.remove("valueMapStringBean");
      }

      return this;
    }

    /**
     * modifier for property valueLinkedMapStringBean
     */
    public BindEditor putValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueLinkedMapStringBean(value);
        editor.putString("valueLinkedMapStringBean",temp);
      }  else  {
        editor.remove("valueLinkedMapStringBean");
      }

      return this;
    }

    /**
     * modifier for property valueSetString
     */
    public BindEditor putValueSetString(Set<String> value) {
      if (value!=null)  {
        String temp=serializeValueSetString(value);
        editor.putString("valueSetString",temp);
      }  else  {
        editor.remove("valueSetString");
      }

      return this;
    }
  }
}

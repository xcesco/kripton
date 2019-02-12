package sqlite.kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.BinderUtils;
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
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
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
  private BeanBindMap beanBindMap = BinderUtils.mapperFor(Bean.class);

  /**
   * constructor
   */
  private BindBeanSharedPreferences() {
    createPrefs();
    defaultBean=new Bean();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * create prefs
   */
  private void createPrefs() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
  }

  /**
   * force to refresh values
   */
  public BindBeanSharedPreferences refresh() {
    createPrefs();
    return this;
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
     {
      String temp=prefs.getString("value_enum_type", null);
      bean.valueEnumType=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): defaultBean.valueEnumType;
    }

     {
      String temp=prefs.getString("value_time", null);
      bean.valueTime=(StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): defaultBean.valueTime;}

     {
      String temp=prefs.getString("value_linked_map_string_bean", null);
      bean.valueLinkedMapStringBean=StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): defaultBean.valueLinkedMapStringBean;
    }

     {
      String temp=prefs.getString("value_currency", null);
      bean.valueCurrency=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): defaultBean.valueCurrency;}

    bean.valueShortType=(short)prefs.getInt("value_short_type", (short)defaultBean.valueShortType);
     {
      Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
      bean.valueSetString=temp;
    }

     {
      String temp=prefs.getString("value_big_integer", "0");
      bean.valueBigInteger=(StringUtils.hasText(temp)) ? new BigInteger(temp): defaultBean.valueBigInteger;
    }

    bean.valueInt=(int)prefs.getInt("value_int", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));
     {
      String temp=prefs.getString("value_url", null);
      bean.valueUrl=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): defaultBean.valueUrl;}

     {
      String temp=prefs.getString("value_strin_list", null);
      bean.valueStrinList=StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.valueStrinList;
    }

    bean.valueByte=(byte)prefs.getInt("value_byte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));
    bean.valueFloat=prefs.getFloat("value_float", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));
     {
      String temp=prefs.getString("value_char_list", null);
      bean.valueCharList=StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.valueCharList;
    }

    bean.valueIntType=(int)prefs.getInt("value_int_type", (int)defaultBean.valueIntType);
    bean.valueLongType=prefs.getLong("value_long_type", defaultBean.valueLongType);
     {
      String temp=prefs.getString("value_bean", null);
      bean.valueBean=StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.valueBean;
    }

    bean.valueLong=prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
     {
      String temp=prefs.getString("value_map_string_bean", null);
      bean.valueMapStringBean=StringUtils.hasText(temp) ? parseValueMapStringBean(temp): defaultBean.valueMapStringBean;
    }

    bean.valueShort=(short)prefs.getInt("value_short", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));
     {
      String temp=prefs.getString("value_long_type_array", null);
      bean.valueLongTypeArray=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.valueLongTypeArray;
    }

     {
      String temp=prefs.getString("value_time_list", null);
      bean.valueTimeList=StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.valueTimeList;
    }

     {
      String temp=prefs.getString("value_double", null);
      bean.valueDouble=(StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble;
    }

     {
      String temp=prefs.getString("value_byte_array", null);
      bean.valueByteArray=StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.valueByteArray;
    }

     {
      String temp=prefs.getString("value_big_decimal", "0");
      bean.valueBigDecimal=(StringUtils.hasText(temp)) ? new BigDecimal(temp): defaultBean.valueBigDecimal;
    }

     {
      String temp=prefs.getString("value_bean_array", null);
      bean.valueBeanArray=StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.valueBeanArray;
    }

     {
      String temp=prefs.getString("value_time_zone", null);
      bean.valueTimeZone=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): defaultBean.valueTimeZone;}

     {
      String temp=prefs.getString("value_char_type_array", null);
      bean.valueCharTypeArray=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.valueCharTypeArray;
    }

     {
      String temp=prefs.getString("value_double_type", null);
      bean.valueDoubleType=(StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDoubleType;
    }

    bean.valueCharType=(char)prefs.getInt("value_char_type", (char)defaultBean.valueCharType);
     {
      String temp=prefs.getString("value_date", null);
      bean.valueDate=(StringUtils.hasText(temp)) ? DateUtils.read(temp): defaultBean.valueDate;}

    bean.valueFloatType=prefs.getFloat("value_float_type", defaultBean.valueFloatType);
    bean.valueBool=(boolean)prefs.getBoolean("value_bool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));
    bean.valueBoolType=(boolean)prefs.getBoolean("value_bool_type", (boolean)defaultBean.valueBoolType);
     {
      String temp=prefs.getString("value_char_array", null);
      bean.valueCharArray=StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.valueCharArray;
    }

    bean.valueChar=(char)prefs.getInt("value_char", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));
     {
      String temp=prefs.getString("value_long_array", null);
      bean.valueLongArray=StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.valueLongArray;
    }

     {
      String temp=prefs.getString("value_string_array", null);
      bean.valueStringArray=StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.valueStringArray;
    }

    bean.valueString=prefs.getString("value_string", defaultBean.valueString);
     {
      String temp=prefs.getString("value_calendar", null);
      bean.valueCalendar=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): defaultBean.valueCalendar;}

     {
      String temp=prefs.getString("value_long_list", null);
      bean.valueLongList=StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.valueLongList;
    }

    bean.valueByteType=(byte)prefs.getInt("value_byte_type", (byte)defaultBean.valueByteType);
     {
      String temp=prefs.getString("value_locale", null);
      bean.valueLocale=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): defaultBean.valueLocale;}


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.valueEnumType!=null)  {
      editor.putString("value_enum_type",bean.valueEnumType.toString() );
    } else {
      editor.remove("valueEnumType");
    }

    if (bean.valueTime!=null)  {
      editor.putString("value_time",SQLTimeUtils.write(bean.valueTime));
    } else {
      editor.remove("valueTime");
    }

    if (bean.valueLinkedMapStringBean!=null)  {
      String temp=serializeValueLinkedMapStringBean(bean.valueLinkedMapStringBean);
      editor.putString("value_linked_map_string_bean",temp);
    }  else  {
      editor.remove("value_linked_map_string_bean");
    }

    if (bean.valueCurrency!=null)  {
      editor.putString("value_currency",CurrencyUtils.write(bean.valueCurrency));
    } else {
      editor.remove("valueCurrency");
    }

    editor.putInt("value_short_type",(int)bean.valueShortType);

    editor.putStringSet("value_set_string",bean.valueSetString);

    if (bean.valueBigInteger!=null) editor.putString("value_big_integer",bean.valueBigInteger.toString() ); else editor.putString("value_big_integer", null);
    if (bean.valueInt!=null)  {
      editor.putInt("value_int",(int)bean.valueInt);
    }

    if (bean.valueUrl!=null)  {
      editor.putString("value_url",UrlUtils.write(bean.valueUrl));
    } else {
      editor.remove("valueUrl");
    }

    if (bean.valueStrinList!=null)  {
      String temp=serializeValueStrinList(bean.valueStrinList);
      editor.putString("value_strin_list",temp);
    }  else  {
      editor.remove("value_strin_list");
    }

    if (bean.valueByte!=null)  {
      editor.putInt("value_byte",(int)bean.valueByte);
    }

    if (bean.valueFloat!=null)  {
      editor.putFloat("value_float",bean.valueFloat);
    }

    if (bean.valueCharList!=null)  {
      String temp=serializeValueCharList(bean.valueCharList);
      editor.putString("value_char_list",temp);
    }  else  {
      editor.remove("value_char_list");
    }

    editor.putInt("value_int_type",(int)bean.valueIntType);

    editor.putLong("value_long_type",bean.valueLongType);

    if (bean.valueBean!=null)  {
      String temp=serializeValueBean(bean.valueBean);
      editor.putString("value_bean",temp);
    }  else  {
      editor.remove("value_bean");
    }

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }

    if (bean.valueMapStringBean!=null)  {
      String temp=serializeValueMapStringBean(bean.valueMapStringBean);
      editor.putString("value_map_string_bean",temp);
    }  else  {
      editor.remove("value_map_string_bean");
    }

    if (bean.valueShort!=null)  {
      editor.putInt("value_short",(int)bean.valueShort);
    }

    if (bean.valueLongTypeArray!=null)  {
      String temp=serializeValueLongTypeArray(bean.valueLongTypeArray);
      editor.putString("value_long_type_array",temp);
    }  else  {
      editor.remove("value_long_type_array");
    }

    if (bean.valueTimeList!=null)  {
      String temp=serializeValueTimeList(bean.valueTimeList);
      editor.putString("value_time_list",temp);
    }  else  {
      editor.remove("value_time_list");
    }

    if (bean.valueDouble!=null)  {
      editor.putString("value_double",String.valueOf(bean.valueDouble));
    } else {
      editor.remove("valueDouble");
    }

    if (bean.valueByteArray!=null)  {
      String temp=serializeValueByteArray(bean.valueByteArray);
      editor.putString("value_byte_array",temp);
    }  else  {
      editor.remove("value_byte_array");
    }

    if (bean.valueBigDecimal!=null) editor.putString("value_big_decimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("value_big_decimal", null);
    if (bean.valueBeanArray!=null)  {
      String temp=serializeValueBeanArray(bean.valueBeanArray);
      editor.putString("value_bean_array",temp);
    }  else  {
      editor.remove("value_bean_array");
    }

    if (bean.valueTimeZone!=null)  {
      editor.putString("value_time_zone",TimeZoneUtils.write(bean.valueTimeZone));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.valueCharTypeArray!=null)  {
      String temp=serializeValueCharTypeArray(bean.valueCharTypeArray);
      editor.putString("value_char_type_array",temp);
    }  else  {
      editor.remove("value_char_type_array");
    }

    editor.putString("value_double_type",String.valueOf(bean.valueDoubleType));

    editor.putInt("value_char_type",(char)bean.valueCharType);

    if (bean.valueDate!=null)  {
      editor.putString("value_date",DateUtils.write(bean.valueDate));
    } else {
      editor.remove("valueDate");
    }

    editor.putFloat("value_float_type",bean.valueFloatType);

    if (bean.valueBool!=null)  {
      editor.putBoolean("value_bool",(boolean)bean.valueBool);
    }

    editor.putBoolean("value_bool_type",(boolean)bean.valueBoolType);

    if (bean.valueCharArray!=null)  {
      String temp=serializeValueCharArray(bean.valueCharArray);
      editor.putString("value_char_array",temp);
    }  else  {
      editor.remove("value_char_array");
    }

    if (bean.valueChar!=null)  {
      editor.putInt("value_char",(char)bean.valueChar);
    }

    if (bean.valueLongArray!=null)  {
      String temp=serializeValueLongArray(bean.valueLongArray);
      editor.putString("value_long_array",temp);
    }  else  {
      editor.remove("value_long_array");
    }

    if (bean.valueStringArray!=null)  {
      String temp=serializeValueStringArray(bean.valueStringArray);
      editor.putString("value_string_array",temp);
    }  else  {
      editor.remove("value_string_array");
    }

    editor.putString("value_string",bean.valueString);

    if (bean.valueCalendar!=null)  {
      editor.putString("value_calendar",CalendarUtils.write(bean.valueCalendar));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.valueLongList!=null)  {
      String temp=serializeValueLongList(bean.valueLongList);
      editor.putString("value_long_list",temp);
    }  else  {
      editor.remove("value_long_list");
    }

    editor.putInt("value_byte_type",(int)bean.valueByteType);

    if (bean.valueLocale!=null)  {
      editor.putString("value_locale",LocaleUtils.write(bean.valueLocale));
    } else {
      editor.remove("valueLocale");
    }


    editor.commit();
  }

  /**
   * reads property <code>valueEnumType</code> from shared pref with key <code>value_enum_type</code>
   *
   * @return property valueEnumType value
   */
  public EnumType getValueEnumType() {
    String temp=prefs.getString("value_enum_type", null);
    return (StringUtils.hasText(temp)) ? EnumType.valueOf(temp): defaultBean.valueEnumType;
  }

  /**
   * reads property <code>valueTime</code> from shared pref with key <code>value_time</code>
   *
   * @return property valueTime value
   */
  public Time getValueTime() {
    String temp=prefs.getString("value_time", null);
    return (StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): defaultBean.valueTime;}

  /**
   * reads property <code>valueLinkedMapStringBean</code> from shared pref with key <code>value_linked_map_string_bean</code>
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> getValueLinkedMapStringBean() {
    String temp=prefs.getString("value_linked_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): defaultBean.valueLinkedMapStringBean;
  }

  /**
   * reads property <code>valueCurrency</code> from shared pref with key <code>value_currency</code>
   *
   * @return property valueCurrency value
   */
  public Currency getValueCurrency() {
    String temp=prefs.getString("value_currency", null);
    return (StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): defaultBean.valueCurrency;}

  /**
   * reads property <code>valueShortType</code> from shared pref with key <code>value_short_type</code>
   *
   * @return property valueShortType value
   */
  public short getValueShortType() {
    return (short)prefs.getInt("value_short_type", (short)defaultBean.valueShortType);}

  /**
   * reads property <code>valueSetString</code> from shared pref with key <code>value_set_string</code>
   *
   * @return property valueSetString value
   */
  public Set<String> getValueSetString() {
    Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
    return temp;
  }

  /**
   * reads property <code>valueBigInteger</code> from shared pref with key <code>value_big_integer</code>
   *
   * @return property valueBigInteger value
   */
  public BigInteger getValueBigInteger() {
    String temp=prefs.getString("value_big_integer", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): defaultBean.valueBigInteger;
  }

  /**
   * reads property <code>valueInt</code> from shared pref with key <code>value_int</code>
   *
   * @return property valueInt value
   */
  public Integer getValueInt() {
    return (int)prefs.getInt("value_int", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));}

  /**
   * reads property <code>valueUrl</code> from shared pref with key <code>value_url</code>
   *
   * @return property valueUrl value
   */
  public URL getValueUrl() {
    String temp=prefs.getString("value_url", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): defaultBean.valueUrl;}

  /**
   * reads property <code>valueStrinList</code> from shared pref with key <code>value_strin_list</code>
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> getValueStrinList() {
    String temp=prefs.getString("value_strin_list", null);
    return StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.valueStrinList;
  }

  /**
   * reads property <code>valueByte</code> from shared pref with key <code>value_byte</code>
   *
   * @return property valueByte value
   */
  public Byte getValueByte() {
    return (byte)prefs.getInt("value_byte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));}

  /**
   * reads property <code>valueFloat</code> from shared pref with key <code>value_float</code>
   *
   * @return property valueFloat value
   */
  public Float getValueFloat() {
    return prefs.getFloat("value_float", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));}

  /**
   * reads property <code>valueCharList</code> from shared pref with key <code>value_char_list</code>
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> getValueCharList() {
    String temp=prefs.getString("value_char_list", null);
    return StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.valueCharList;
  }

  /**
   * reads property <code>valueIntType</code> from shared pref with key <code>value_int_type</code>
   *
   * @return property valueIntType value
   */
  public int getValueIntType() {
    return (int)prefs.getInt("value_int_type", (int)defaultBean.valueIntType);}

  /**
   * reads property <code>valueLongType</code> from shared pref with key <code>value_long_type</code>
   *
   * @return property valueLongType value
   */
  public long getValueLongType() {
    return prefs.getLong("value_long_type", defaultBean.valueLongType);}

  /**
   * reads property <code>valueBean</code> from shared pref with key <code>value_bean</code>
   *
   * @return property valueBean value
   */
  public Bean getValueBean() {
    String temp=prefs.getString("value_bean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.valueBean;
  }

  /**
   * reads property <code>valueLong</code> from shared pref with key <code>value_long</code>
   *
   * @return property valueLong value
   */
  public Long getValueLong() {
    return prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));}

  /**
   * reads property <code>valueMapStringBean</code> from shared pref with key <code>value_map_string_bean</code>
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> getValueMapStringBean() {
    String temp=prefs.getString("value_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueMapStringBean(temp): defaultBean.valueMapStringBean;
  }

  /**
   * reads property <code>valueShort</code> from shared pref with key <code>value_short</code>
   *
   * @return property valueShort value
   */
  public Short getValueShort() {
    return (short)prefs.getInt("value_short", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));}

  /**
   * reads property <code>valueLongTypeArray</code> from shared pref with key <code>value_long_type_array</code>
   *
   * @return property valueLongTypeArray value
   */
  public long[] getValueLongTypeArray() {
    String temp=prefs.getString("value_long_type_array", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.valueLongTypeArray;
  }

  /**
   * reads property <code>valueTimeList</code> from shared pref with key <code>value_time_list</code>
   *
   * @return property valueTimeList value
   */
  public List<Time> getValueTimeList() {
    String temp=prefs.getString("value_time_list", null);
    return StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.valueTimeList;
  }

  /**
   * reads property <code>valueDouble</code> from shared pref with key <code>value_double</code>
   *
   * @return property valueDouble value
   */
  public Double getValueDouble() {
    String temp=prefs.getString("value_double", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble;
  }

  /**
   * reads property <code>valueByteArray</code> from shared pref with key <code>value_byte_array</code>
   *
   * @return property valueByteArray value
   */
  public byte[] getValueByteArray() {
    String temp=prefs.getString("value_byte_array", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.valueByteArray;
  }

  /**
   * reads property <code>valueBigDecimal</code> from shared pref with key <code>value_big_decimal</code>
   *
   * @return property valueBigDecimal value
   */
  public BigDecimal getValueBigDecimal() {
    String temp=prefs.getString("value_big_decimal", "0");
    return (StringUtils.hasText(temp)) ? new BigDecimal(temp): defaultBean.valueBigDecimal;
  }

  /**
   * reads property <code>valueBeanArray</code> from shared pref with key <code>value_bean_array</code>
   *
   * @return property valueBeanArray value
   */
  public Bean[] getValueBeanArray() {
    String temp=prefs.getString("value_bean_array", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.valueBeanArray;
  }

  /**
   * reads property <code>valueTimeZone</code> from shared pref with key <code>value_time_zone</code>
   *
   * @return property valueTimeZone value
   */
  public TimeZone getValueTimeZone() {
    String temp=prefs.getString("value_time_zone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): defaultBean.valueTimeZone;}

  /**
   * reads property <code>valueCharTypeArray</code> from shared pref with key <code>value_char_type_array</code>
   *
   * @return property valueCharTypeArray value
   */
  public char[] getValueCharTypeArray() {
    String temp=prefs.getString("value_char_type_array", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.valueCharTypeArray;
  }

  /**
   * reads property <code>valueDoubleType</code> from shared pref with key <code>value_double_type</code>
   *
   * @return property valueDoubleType value
   */
  public double getValueDoubleType() {
    String temp=prefs.getString("value_double_type", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDoubleType;
  }

  /**
   * reads property <code>valueCharType</code> from shared pref with key <code>value_char_type</code>
   *
   * @return property valueCharType value
   */
  public char getValueCharType() {
    return (char)prefs.getInt("value_char_type", (char)defaultBean.valueCharType);}

  /**
   * reads property <code>valueDate</code> from shared pref with key <code>value_date</code>
   *
   * @return property valueDate value
   */
  public Date getValueDate() {
    String temp=prefs.getString("value_date", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): defaultBean.valueDate;}

  /**
   * reads property <code>valueFloatType</code> from shared pref with key <code>value_float_type</code>
   *
   * @return property valueFloatType value
   */
  public float getValueFloatType() {
    return prefs.getFloat("value_float_type", defaultBean.valueFloatType);}

  /**
   * reads property <code>valueBool</code> from shared pref with key <code>value_bool</code>
   *
   * @return property valueBool value
   */
  public Boolean getValueBool() {
    return (boolean)prefs.getBoolean("value_bool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));}

  /**
   * reads property <code>valueBoolType</code> from shared pref with key <code>value_bool_type</code>
   *
   * @return property valueBoolType value
   */
  public boolean getValueBoolType() {
    return (boolean)prefs.getBoolean("value_bool_type", (boolean)defaultBean.valueBoolType);}

  /**
   * reads property <code>valueCharArray</code> from shared pref with key <code>value_char_array</code>
   *
   * @return property valueCharArray value
   */
  public Character[] getValueCharArray() {
    String temp=prefs.getString("value_char_array", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.valueCharArray;
  }

  /**
   * reads property <code>valueChar</code> from shared pref with key <code>value_char</code>
   *
   * @return property valueChar value
   */
  public Character getValueChar() {
    return (char)prefs.getInt("value_char", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));}

  /**
   * reads property <code>valueLongArray</code> from shared pref with key <code>value_long_array</code>
   *
   * @return property valueLongArray value
   */
  public Long[] getValueLongArray() {
    String temp=prefs.getString("value_long_array", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.valueLongArray;
  }

  /**
   * reads property <code>valueStringArray</code> from shared pref with key <code>value_string_array</code>
   *
   * @return property valueStringArray value
   */
  public String[] getValueStringArray() {
    String temp=prefs.getString("value_string_array", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.valueStringArray;
  }

  /**
   * reads property <code>valueString</code> from shared pref with key <code>value_string</code>
   *
   * @return property valueString value
   */
  public String getValueString() {
    return prefs.getString("value_string", defaultBean.valueString);}

  /**
   * reads property <code>valueCalendar</code> from shared pref with key <code>value_calendar</code>
   *
   * @return property valueCalendar value
   */
  public Calendar getValueCalendar() {
    String temp=prefs.getString("value_calendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): defaultBean.valueCalendar;}

  /**
   * reads property <code>valueLongList</code> from shared pref with key <code>value_long_list</code>
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> getValueLongList() {
    String temp=prefs.getString("value_long_list", null);
    return StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.valueLongList;
  }

  /**
   * reads property <code>valueByteType</code> from shared pref with key <code>value_byte_type</code>
   *
   * @return property valueByteType value
   */
  public byte getValueByteType() {
    return (byte)prefs.getInt("value_byte_type", (byte)defaultBean.valueByteType);}

  /**
   * reads property <code>valueLocale</code> from shared pref with key <code>value_locale</code>
   *
   * @return property valueLocale value
   */
  public Locale getValueLocale() {
    String temp=prefs.getString("value_locale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): defaultBean.valueLocale;}

  /**
   * for attribute valueLinkedMapStringBean serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLinkedMapStringBean parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStrinList serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStrinList parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongTypeArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongTypeArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueTimeList serialization
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
            jacksonSerializer.writeString(SQLTimeUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueTimeList parsing
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
            item=SQLTimeUtils.read(jacksonParser.getText());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueByteArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueByteArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharTypeArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharTypeArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStringArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStringArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongList serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongList parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindBeanSharedPreferences getInstance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
      // read and write instance to sync with default values
      instance.write(instance.read());
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
     * modifier for property valueEnumType
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null)  {
        editor.putString("value_enum_type",value.toString() );
      } else {
        editor.remove("valueEnumType");
      }

      return this;
    }

    /**
     * remove property valueEnumType
     */
    public BindEditor removeValueEnumType() {
      editor.remove("value_enum_type");
      return this;
    }

    /**
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null)  {
        editor.putString("value_time",SQLTimeUtils.write(value));
      } else {
        editor.remove("valueTime");
      }

      return this;
    }

    /**
     * remove property valueTime
     */
    public BindEditor removeValueTime() {
      editor.remove("value_time");
      return this;
    }

    /**
     * modifier for property valueLinkedMapStringBean
     */
    public BindEditor putValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueLinkedMapStringBean(value);
        editor.putString("value_linked_map_string_bean",temp);
      }  else  {
        editor.remove("value_linked_map_string_bean");
      }

      return this;
    }

    /**
     * remove property valueLinkedMapStringBean
     */
    public BindEditor removeValueLinkedMapStringBean() {
      editor.remove("value_linked_map_string_bean");
      return this;
    }

    /**
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null)  {
        editor.putString("value_currency",CurrencyUtils.write(value));
      } else {
        editor.remove("valueCurrency");
      }

      return this;
    }

    /**
     * remove property valueCurrency
     */
    public BindEditor removeValueCurrency() {
      editor.remove("value_currency");
      return this;
    }

    /**
     * modifier for property valueShortType
     */
    public BindEditor putValueShortType(short value) {
      editor.putInt("value_short_type",(int)value);

      return this;
    }

    /**
     * remove property valueShortType
     */
    public BindEditor removeValueShortType() {
      editor.remove("value_short_type");
      return this;
    }

    /**
     * modifier for property valueSetString
     */
    public BindEditor putValueSetString(Set<String> value) {
      editor.putStringSet("value_set_string",value);

      return this;
    }

    /**
     * remove property valueSetString
     */
    public BindEditor removeValueSetString() {
      editor.remove("value_set_string");
      return this;
    }

    /**
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("value_big_integer",value.toString()); else editor.remove("value_big_integer");
      return this;
    }

    /**
     * remove property valueBigInteger
     */
    public BindEditor removeValueBigInteger() {
      editor.remove("value_big_integer");
      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(Integer value) {
      if (value!=null)  {
        editor.putInt("value_int",(int)value);
      }

      return this;
    }

    /**
     * remove property valueInt
     */
    public BindEditor removeValueInt() {
      editor.remove("value_int");
      return this;
    }

    /**
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null)  {
        editor.putString("value_url",UrlUtils.write(value));
      } else {
        editor.remove("valueUrl");
      }

      return this;
    }

    /**
     * remove property valueUrl
     */
    public BindEditor removeValueUrl() {
      editor.remove("value_url");
      return this;
    }

    /**
     * modifier for property valueStrinList
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null)  {
        String temp=serializeValueStrinList(value);
        editor.putString("value_strin_list",temp);
      }  else  {
        editor.remove("value_strin_list");
      }

      return this;
    }

    /**
     * remove property valueStrinList
     */
    public BindEditor removeValueStrinList() {
      editor.remove("value_strin_list");
      return this;
    }

    /**
     * modifier for property valueByte
     */
    public BindEditor putValueByte(Byte value) {
      if (value!=null)  {
        editor.putInt("value_byte",(int)value);
      }

      return this;
    }

    /**
     * remove property valueByte
     */
    public BindEditor removeValueByte() {
      editor.remove("value_byte");
      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(Float value) {
      if (value!=null)  {
        editor.putFloat("value_float",value);
      }

      return this;
    }

    /**
     * remove property valueFloat
     */
    public BindEditor removeValueFloat() {
      editor.remove("value_float");
      return this;
    }

    /**
     * modifier for property valueCharList
     */
    public BindEditor putValueCharList(LinkedList<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharList(value);
        editor.putString("value_char_list",temp);
      }  else  {
        editor.remove("value_char_list");
      }

      return this;
    }

    /**
     * remove property valueCharList
     */
    public BindEditor removeValueCharList() {
      editor.remove("value_char_list");
      return this;
    }

    /**
     * modifier for property valueIntType
     */
    public BindEditor putValueIntType(int value) {
      editor.putInt("value_int_type",(int)value);

      return this;
    }

    /**
     * remove property valueIntType
     */
    public BindEditor removeValueIntType() {
      editor.remove("value_int_type");
      return this;
    }

    /**
     * modifier for property valueLongType
     */
    public BindEditor putValueLongType(long value) {
      editor.putLong("value_long_type",value);

      return this;
    }

    /**
     * remove property valueLongType
     */
    public BindEditor removeValueLongType() {
      editor.remove("value_long_type");
      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("value_bean",temp);
      }  else  {
        editor.remove("value_bean");
      }

      return this;
    }

    /**
     * remove property valueBean
     */
    public BindEditor removeValueBean() {
      editor.remove("value_bean");
      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
      }

      return this;
    }

    /**
     * remove property valueLong
     */
    public BindEditor removeValueLong() {
      editor.remove("value_long");
      return this;
    }

    /**
     * modifier for property valueMapStringBean
     */
    public BindEditor putValueMapStringBean(Map<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueMapStringBean(value);
        editor.putString("value_map_string_bean",temp);
      }  else  {
        editor.remove("value_map_string_bean");
      }

      return this;
    }

    /**
     * remove property valueMapStringBean
     */
    public BindEditor removeValueMapStringBean() {
      editor.remove("value_map_string_bean");
      return this;
    }

    /**
     * modifier for property valueShort
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null)  {
        editor.putInt("value_short",(int)value);
      }

      return this;
    }

    /**
     * remove property valueShort
     */
    public BindEditor removeValueShort() {
      editor.remove("value_short");
      return this;
    }

    /**
     * modifier for property valueLongTypeArray
     */
    public BindEditor putValueLongTypeArray(long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongTypeArray(value);
        editor.putString("value_long_type_array",temp);
      }  else  {
        editor.remove("value_long_type_array");
      }

      return this;
    }

    /**
     * remove property valueLongTypeArray
     */
    public BindEditor removeValueLongTypeArray() {
      editor.remove("value_long_type_array");
      return this;
    }

    /**
     * modifier for property valueTimeList
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null)  {
        String temp=serializeValueTimeList(value);
        editor.putString("value_time_list",temp);
      }  else  {
        editor.remove("value_time_list");
      }

      return this;
    }

    /**
     * remove property valueTimeList
     */
    public BindEditor removeValueTimeList() {
      editor.remove("value_time_list");
      return this;
    }

    /**
     * modifier for property valueDouble
     */
    public BindEditor putValueDouble(Double value) {
      if (value!=null)  {
        editor.putString("value_double",String.valueOf(value));
      } else {
        editor.remove("valueDouble");
      }

      return this;
    }

    /**
     * remove property valueDouble
     */
    public BindEditor removeValueDouble() {
      editor.remove("value_double");
      return this;
    }

    /**
     * modifier for property valueByteArray
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null)  {
        String temp=serializeValueByteArray(value);
        editor.putString("value_byte_array",temp);
      }  else  {
        editor.remove("value_byte_array");
      }

      return this;
    }

    /**
     * remove property valueByteArray
     */
    public BindEditor removeValueByteArray() {
      editor.remove("value_byte_array");
      return this;
    }

    /**
     * modifier for property valueBigDecimal
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("value_big_decimal",value.toPlainString()); else editor.remove("value_big_decimal");
      return this;
    }

    /**
     * remove property valueBigDecimal
     */
    public BindEditor removeValueBigDecimal() {
      editor.remove("value_big_decimal");
      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("value_bean_array",temp);
      }  else  {
        editor.remove("value_bean_array");
      }

      return this;
    }

    /**
     * remove property valueBeanArray
     */
    public BindEditor removeValueBeanArray() {
      editor.remove("value_bean_array");
      return this;
    }

    /**
     * modifier for property valueTimeZone
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null)  {
        editor.putString("value_time_zone",TimeZoneUtils.write(value));
      } else {
        editor.remove("valueTimeZone");
      }

      return this;
    }

    /**
     * remove property valueTimeZone
     */
    public BindEditor removeValueTimeZone() {
      editor.remove("value_time_zone");
      return this;
    }

    /**
     * modifier for property valueCharTypeArray
     */
    public BindEditor putValueCharTypeArray(char[] value) {
      if (value!=null)  {
        String temp=serializeValueCharTypeArray(value);
        editor.putString("value_char_type_array",temp);
      }  else  {
        editor.remove("value_char_type_array");
      }

      return this;
    }

    /**
     * remove property valueCharTypeArray
     */
    public BindEditor removeValueCharTypeArray() {
      editor.remove("value_char_type_array");
      return this;
    }

    /**
     * modifier for property valueDoubleType
     */
    public BindEditor putValueDoubleType(double value) {
      editor.putString("value_double_type",String.valueOf(value));

      return this;
    }

    /**
     * remove property valueDoubleType
     */
    public BindEditor removeValueDoubleType() {
      editor.remove("value_double_type");
      return this;
    }

    /**
     * modifier for property valueCharType
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("value_char_type",(char)value);

      return this;
    }

    /**
     * remove property valueCharType
     */
    public BindEditor removeValueCharType() {
      editor.remove("value_char_type");
      return this;
    }

    /**
     * modifier for property valueDate
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null)  {
        editor.putString("value_date",DateUtils.write(value));
      } else {
        editor.remove("valueDate");
      }

      return this;
    }

    /**
     * remove property valueDate
     */
    public BindEditor removeValueDate() {
      editor.remove("value_date");
      return this;
    }

    /**
     * modifier for property valueFloatType
     */
    public BindEditor putValueFloatType(float value) {
      editor.putFloat("value_float_type",value);

      return this;
    }

    /**
     * remove property valueFloatType
     */
    public BindEditor removeValueFloatType() {
      editor.remove("value_float_type");
      return this;
    }

    /**
     * modifier for property valueBool
     */
    public BindEditor putValueBool(Boolean value) {
      if (value!=null)  {
        editor.putBoolean("value_bool",(boolean)value);
      }

      return this;
    }

    /**
     * remove property valueBool
     */
    public BindEditor removeValueBool() {
      editor.remove("value_bool");
      return this;
    }

    /**
     * modifier for property valueBoolType
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("value_bool_type",(boolean)value);

      return this;
    }

    /**
     * remove property valueBoolType
     */
    public BindEditor removeValueBoolType() {
      editor.remove("value_bool_type");
      return this;
    }

    /**
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null)  {
        String temp=serializeValueCharArray(value);
        editor.putString("value_char_array",temp);
      }  else  {
        editor.remove("value_char_array");
      }

      return this;
    }

    /**
     * remove property valueCharArray
     */
    public BindEditor removeValueCharArray() {
      editor.remove("value_char_array");
      return this;
    }

    /**
     * modifier for property valueChar
     */
    public BindEditor putValueChar(Character value) {
      if (value!=null)  {
        editor.putInt("value_char",(char)value);
      }

      return this;
    }

    /**
     * remove property valueChar
     */
    public BindEditor removeValueChar() {
      editor.remove("value_char");
      return this;
    }

    /**
     * modifier for property valueLongArray
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongArray(value);
        editor.putString("value_long_array",temp);
      }  else  {
        editor.remove("value_long_array");
      }

      return this;
    }

    /**
     * remove property valueLongArray
     */
    public BindEditor removeValueLongArray() {
      editor.remove("value_long_array");
      return this;
    }

    /**
     * modifier for property valueStringArray
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeValueStringArray(value);
        editor.putString("value_string_array",temp);
      }  else  {
        editor.remove("value_string_array");
      }

      return this;
    }

    /**
     * remove property valueStringArray
     */
    public BindEditor removeValueStringArray() {
      editor.remove("value_string_array");
      return this;
    }

    /**
     * modifier for property valueString
     */
    public BindEditor putValueString(String value) {
      editor.putString("value_string",value);

      return this;
    }

    /**
     * remove property valueString
     */
    public BindEditor removeValueString() {
      editor.remove("value_string");
      return this;
    }

    /**
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null)  {
        editor.putString("value_calendar",CalendarUtils.write(value));
      } else {
        editor.remove("valueCalendar");
      }

      return this;
    }

    /**
     * remove property valueCalendar
     */
    public BindEditor removeValueCalendar() {
      editor.remove("value_calendar");
      return this;
    }

    /**
     * modifier for property valueLongList
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null)  {
        String temp=serializeValueLongList(value);
        editor.putString("value_long_list",temp);
      }  else  {
        editor.remove("value_long_list");
      }

      return this;
    }

    /**
     * remove property valueLongList
     */
    public BindEditor removeValueLongList() {
      editor.remove("value_long_list");
      return this;
    }

    /**
     * modifier for property valueByteType
     */
    public BindEditor putValueByteType(byte value) {
      editor.putInt("value_byte_type",(int)value);

      return this;
    }

    /**
     * remove property valueByteType
     */
    public BindEditor removeValueByteType() {
      editor.remove("value_byte_type");
      return this;
    }

    /**
     * modifier for property valueLocale
     */
    public BindEditor putValueLocale(Locale value) {
      if (value!=null)  {
        editor.putString("value_locale",LocaleUtils.write(value));
      } else {
        editor.remove("valueLocale");
      }

      return this;
    }

    /**
     * remove property valueLocale
     */
    public BindEditor removeValueLocale() {
      editor.remove("value_locale");
      return this;
    }

    /**
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}

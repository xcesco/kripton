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
   * Bean2BindMap */
  private Bean2BindMap bean2BindMap = BinderUtils.mapperFor(Bean2.class);

  /**
   * constructor
   */
  private BindBean2SharedPreferences() {
    // no typeName specified, using default shared preferences
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
     {
      String temp=prefs.getString("valueBean", null);
      bean.setValueBean(StringUtils.hasText(temp) ? parseValueBean(temp): null);
    }

     {
      String temp=prefs.getString("valueBeanArray", null);
      bean.setValueBeanArray(StringUtils.hasText(temp) ? parseValueBeanArray(temp): null);
    }

     {
      String temp=prefs.getString("valueBigDecimal", "0");
      bean.setValueBigDecimal((StringUtils.hasText(temp)) ? new BigDecimal(temp): null);
    }

     {
      String temp=prefs.getString("valueBigInteger", "0");
      bean.setValueBigInteger((StringUtils.hasText(temp)) ? new BigInteger(temp): null);
    }

    bean.setValueBool((boolean)prefs.getBoolean("valueBool", (boolean)(bean.getValueBool()==null?false:bean.getValueBool())));
    bean.setValueBoolType((boolean)prefs.getBoolean("valueBoolType", (boolean)bean.isValueBoolType()));
    bean.setValueByte((byte)prefs.getInt("valueByte", (byte)(bean.getValueByte()==null?(byte)0:bean.getValueByte())));
     {
      String temp=prefs.getString("valueByteArray", null);
      bean.setValueByteArray(StringUtils.hasText(temp) ? parseValueByteArray(temp): null);
    }

    bean.setValueByteType((byte)prefs.getInt("valueByteType", (byte)bean.getValueByteType()));
     {
      String temp=prefs.getString("valueCalendar", null);
      bean.setValueCalendar((StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null);}

    bean.setValueChar((char)prefs.getInt("valueChar", (char)(bean.getValueChar()==null?(char)0:bean.getValueChar())));
     {
      String temp=prefs.getString("valueCharArray", null);
      bean.setValueCharArray(StringUtils.hasText(temp) ? parseValueCharArray(temp): null);
    }

     {
      String temp=prefs.getString("valueCharList", null);
      bean.setValueCharList(StringUtils.hasText(temp) ? parseValueCharList(temp): null);
    }

    bean.setValueCharType((char)prefs.getInt("valueCharType", (char)bean.getValueCharType()));
     {
      String temp=prefs.getString("valueCharTypeArray", null);
      bean.setValueCharTypeArray(StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null);
    }

     {
      String temp=prefs.getString("valueCurrency", null);
      bean.setValueCurrency((StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null);}

     {
      String temp=prefs.getString("valueDate", null);
      bean.setValueDate((StringUtils.hasText(temp)) ? DateUtils.read(temp): null);}

     {
      String temp=prefs.getString("valueDouble", null);
      bean.setValueDouble((StringUtils.hasText(temp)) ? Double.valueOf(temp): null);
    }

     {
      String temp=prefs.getString("valueDoubleType", null);
      bean.setValueDoubleType((StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0);
    }

     {
      String temp=prefs.getString("valueEnumType", null);
      bean.setValueEnumType((StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null);
    }

    bean.setValueFloat(prefs.getFloat("valueFloat", (bean.getValueFloat()==null?0F:bean.getValueFloat())));
    bean.setValueFloatType(prefs.getFloat("valueFloatType", bean.getValueFloatType()));
    bean.setValueInt((int)prefs.getInt("valueInt", (int)(bean.getValueInt()==null?0:bean.getValueInt())));
    bean.setValueIntType((int)prefs.getInt("valueIntType", (int)bean.getValueIntType()));
     {
      String temp=prefs.getString("valueLocale", null);
      bean.setValueLocale((StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null);}

    bean.setValueLong(prefs.getLong("valueLong", (bean.getValueLong()==null?0L:bean.getValueLong())));
     {
      String temp=prefs.getString("valueLongArray", null);
      bean.setValueLongArray(StringUtils.hasText(temp) ? parseValueLongArray(temp): null);
    }

     {
      String temp=prefs.getString("valueLongList", null);
      bean.setValueLongList(StringUtils.hasText(temp) ? parseValueLongList(temp): null);
    }

    bean.setValueLongType(prefs.getLong("valueLongType", bean.getValueLongType()));
     {
      String temp=prefs.getString("valueLongTypeArray", null);
      bean.setValueLongTypeArray(StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null);
    }

    bean.setValueShort((short)prefs.getInt("valueShort", (short)(bean.getValueShort()==null?(short)0:bean.getValueShort())));
    bean.setValueShortType((short)prefs.getInt("valueShortType", (short)bean.getValueShortType()));
    bean.setValueString(prefs.getString("valueString", bean.getValueString()));
     {
      String temp=prefs.getString("valueStringArray", null);
      bean.setValueStringArray(StringUtils.hasText(temp) ? parseValueStringArray(temp): null);
    }

     {
      String temp=prefs.getString("valueStrinList", null);
      bean.setValueStrinList(StringUtils.hasText(temp) ? parseValueStrinList(temp): null);
    }

     {
      String temp=prefs.getString("valueTime", null);
      bean.setValueTime((StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null);}

     {
      String temp=prefs.getString("valueTimeList", null);
      bean.setValueTimeList(StringUtils.hasText(temp) ? parseValueTimeList(temp): null);
    }

     {
      String temp=prefs.getString("valueTimeZone", null);
      bean.setValueTimeZone((StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null);}

     {
      String temp=prefs.getString("valueUrl", null);
      bean.setValueUrl((StringUtils.hasText(temp)) ? UrlUtils.read(temp): null);}


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

    if (bean.getValueBean()!=null)  {
      String temp=serializeValueBean(bean.getValueBean());
      editor.putString("valueBean",temp);
    }  else  {
      editor.remove("valueBean");
    }

    if (bean.getValueBeanArray()!=null)  {
      String temp=serializeValueBeanArray(bean.getValueBeanArray());
      editor.putString("valueBeanArray",temp);
    }  else  {
      editor.remove("valueBeanArray");
    }

    if (bean.getValueBigDecimal()!=null) editor.putString("valueBigDecimal",bean.getValueBigDecimal().toPlainString() ); else editor.putString("valueBigDecimal", null);
    if (bean.getValueBigInteger()!=null) editor.putString("valueBigInteger",bean.getValueBigInteger().toString() ); else editor.putString("valueBigInteger", null);
    if (bean.getValueBool()!=null)  {
      editor.putBoolean("valueBool",(boolean)bean.getValueBool());
    }

    editor.putBoolean("valueBoolType",(boolean)bean.isValueBoolType());

    if (bean.getValueByte()!=null)  {
      editor.putInt("valueByte",(int)bean.getValueByte());
    }

    if (bean.getValueByteArray()!=null)  {
      String temp=serializeValueByteArray(bean.getValueByteArray());
      editor.putString("valueByteArray",temp);
    }  else  {
      editor.remove("valueByteArray");
    }

    editor.putInt("valueByteType",(int)bean.getValueByteType());

    if (bean.getValueCalendar()!=null)  {
      editor.putString("valueCalendar",CalendarUtils.write(bean.getValueCalendar()));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.getValueChar()!=null)  {
      editor.putInt("valueChar",(char)bean.getValueChar());
    }

    if (bean.getValueCharArray()!=null)  {
      String temp=serializeValueCharArray(bean.getValueCharArray());
      editor.putString("valueCharArray",temp);
    }  else  {
      editor.remove("valueCharArray");
    }

    if (bean.getValueCharList()!=null)  {
      String temp=serializeValueCharList(bean.getValueCharList());
      editor.putString("valueCharList",temp);
    }  else  {
      editor.remove("valueCharList");
    }

    editor.putInt("valueCharType",(char)bean.getValueCharType());

    if (bean.getValueCharTypeArray()!=null)  {
      String temp=serializeValueCharTypeArray(bean.getValueCharTypeArray());
      editor.putString("valueCharTypeArray",temp);
    }  else  {
      editor.remove("valueCharTypeArray");
    }

    if (bean.getValueCurrency()!=null)  {
      editor.putString("valueCurrency",CurrencyUtils.write(bean.getValueCurrency()));
    } else {
      editor.remove("valueCurrency");
    }

    if (bean.getValueDate()!=null)  {
      editor.putString("valueDate",DateUtils.write(bean.getValueDate()));
    } else {
      editor.remove("valueDate");
    }

    if (bean.getValueDouble()!=null)  {
      editor.putString("valueDouble",String.valueOf(bean.getValueDouble()));
    } else {
      editor.remove("valueDouble");
    }

    editor.putString("valueDoubleType",String.valueOf(bean.getValueDoubleType()));

    if (bean.getValueEnumType()!=null)  {
      editor.putString("valueEnumType",bean.getValueEnumType().toString() );
    } else {
      editor.remove("valueEnumType");
    }

    if (bean.getValueFloat()!=null)  {
      editor.putFloat("valueFloat",bean.getValueFloat());
    }

    editor.putFloat("valueFloatType",bean.getValueFloatType());

    if (bean.getValueInt()!=null)  {
      editor.putInt("valueInt",(int)bean.getValueInt());
    }

    editor.putInt("valueIntType",(int)bean.getValueIntType());

    if (bean.getValueLocale()!=null)  {
      editor.putString("valueLocale",LocaleUtils.write(bean.getValueLocale()));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.getValueLong()!=null)  {
      editor.putLong("valueLong",bean.getValueLong());
    }

    if (bean.getValueLongArray()!=null)  {
      String temp=serializeValueLongArray(bean.getValueLongArray());
      editor.putString("valueLongArray",temp);
    }  else  {
      editor.remove("valueLongArray");
    }

    if (bean.getValueLongList()!=null)  {
      String temp=serializeValueLongList(bean.getValueLongList());
      editor.putString("valueLongList",temp);
    }  else  {
      editor.remove("valueLongList");
    }

    editor.putLong("valueLongType",bean.getValueLongType());

    if (bean.getValueLongTypeArray()!=null)  {
      String temp=serializeValueLongTypeArray(bean.getValueLongTypeArray());
      editor.putString("valueLongTypeArray",temp);
    }  else  {
      editor.remove("valueLongTypeArray");
    }

    if (bean.getValueShort()!=null)  {
      editor.putInt("valueShort",(int)bean.getValueShort());
    }

    editor.putInt("valueShortType",(int)bean.getValueShortType());

    editor.putString("valueString",bean.getValueString());

    if (bean.getValueStringArray()!=null)  {
      String temp=serializeValueStringArray(bean.getValueStringArray());
      editor.putString("valueStringArray",temp);
    }  else  {
      editor.remove("valueStringArray");
    }

    if (bean.getValueStrinList()!=null)  {
      String temp=serializeValueStrinList(bean.getValueStrinList());
      editor.putString("valueStrinList",temp);
    }  else  {
      editor.remove("valueStrinList");
    }

    if (bean.getValueTime()!=null)  {
      editor.putString("valueTime",SQLTimeUtils.write(bean.getValueTime()));
    } else {
      editor.remove("valueTime");
    }

    if (bean.getValueTimeList()!=null)  {
      String temp=serializeValueTimeList(bean.getValueTimeList());
      editor.putString("valueTimeList",temp);
    }  else  {
      editor.remove("valueTimeList");
    }

    if (bean.getValueTimeZone()!=null)  {
      editor.putString("valueTimeZone",TimeZoneUtils.write(bean.getValueTimeZone()));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.getValueUrl()!=null)  {
      editor.putString("valueUrl",UrlUtils.write(bean.getValueUrl()));
    } else {
      editor.remove("valueUrl");
    }


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
    String temp=prefs.getString("valueBean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): null;

  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean2[] valueBeanArray() {
    String temp=prefs.getString("valueBeanArray", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): null;

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
   * read property valueBigInteger
   *
   * @return property valueBigInteger value
   */
  public BigInteger valueBigInteger() {
    String temp=prefs.getString("valueBigInteger", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): null;

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
    String temp=prefs.getString("valueByteArray", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): null;

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
    String temp=prefs.getString("valueCalendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;
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
    String temp=prefs.getString("valueCharArray", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): null;

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
    String temp=prefs.getString("valueCharTypeArray", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null;

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
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date valueDate() {
    String temp=prefs.getString("valueDate", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): null;
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
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double valueDoubleType() {
    String temp=prefs.getString("valueDoubleType", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;

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
    String temp=prefs.getString("valueLocale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;
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
    String temp=prefs.getString("valueLongArray", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): null;

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
    String temp=prefs.getString("valueLongTypeArray", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null;

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
    String temp=prefs.getString("valueStringArray", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): null;

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
   * read property valueTime
   *
   * @return property valueTime value
   */
  public Time valueTime() {
    String temp=prefs.getString("valueTime", null);
    return (StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;
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
   * read property valueTimeZone
   *
   * @return property valueTimeZone value
   */
  public TimeZone valueTimeZone() {
    String temp=prefs.getString("valueTimeZone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;
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
   * for attribute valueBean serialization
   */
  protected String serializeValueBean(Bean2 value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        bean2BindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean parsing
   */
  protected Bean2 parseValueBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=bean2BindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray serialization
   */
  protected String serializeValueBeanArray(Bean2[] value) {
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
        Bean2 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            bean2BindMap.serializeOnJackson(item, jacksonSerializer);
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
   * for attribute valueBeanArray parsing
   */
  protected Bean2[] parseValueBeanArray(String input) {
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
      Bean2[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Bean2> collection=new ArrayList<>();
        Bean2 item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=bean2BindMap.parseOnJackson(jacksonParser);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean2[collection.size()]);
      }
      return result;
    } catch(Exception e) {
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
      throw(new KriptonRuntimeException(e.getMessage()));
    }
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
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean2 value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("valueBean",temp);
      }  else  {
        editor.remove("valueBean");
      }

      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean2[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("valueBeanArray",temp);
      }  else  {
        editor.remove("valueBeanArray");
      }

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
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("valueBigInteger",value.toString()); else editor.remove("valueBigInteger");
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
      if (value!=null)  {
        editor.putInt("valueByte",(int)value);
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
      if (value!=null)  {
        editor.putString("valueCalendar",CalendarUtils.write(value));
      } else {
        editor.remove("valueCalendar");
      }

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
      if (value!=null)  {
        String temp=serializeValueCharTypeArray(value);
        editor.putString("valueCharTypeArray",temp);
      }  else  {
        editor.remove("valueCharTypeArray");
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
      if (value!=null)  {
        editor.putString("valueEnumType",value.toString() );
      } else {
        editor.remove("valueEnumType");
      }

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
      if (value!=null)  {
        editor.putInt("valueInt",(int)value);
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
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("valueLong",value);
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
      if (value!=null)  {
        String temp=serializeValueLongTypeArray(value);
        editor.putString("valueLongTypeArray",temp);
      }  else  {
        editor.remove("valueLongTypeArray");
      }

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
      if (value!=null)  {
        String temp=serializeValueStringArray(value);
        editor.putString("valueStringArray",temp);
      }  else  {
        editor.remove("valueStringArray");
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
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null)  {
        editor.putString("valueTime",SQLTimeUtils.write(value));
      } else {
        editor.remove("valueTime");
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
  }
}

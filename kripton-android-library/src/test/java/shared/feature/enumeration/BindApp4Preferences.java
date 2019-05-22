package shared.feature.enumeration;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * This class is the shared preference binder defined for App4Preferences
 *
 * @see App4Preferences
 */
public class BindApp4Preferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindApp4Preferences instance;

  /**
   * working instance of bean
   */
  private final App4Preferences defaultBean;

  /**
   * constructor
   */
  private BindApp4Preferences() {
    createPrefs();
    defaultBean=new App4Preferences();
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
  public BindApp4Preferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    App4Preferences bean=new App4Preferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App4Preferences read() {
    App4Preferences bean=new App4Preferences();
    bean.valueBoolean=(boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);
    bean.valueInt2=(int)prefs.getInt("value_int2", (int)(defaultBean.valueInt2==null?0:defaultBean.valueInt2));
     {
      String temp=prefs.getString("value_double2", null);
      bean.valueDouble2=(StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble2;
    }

     {
      String temp=prefs.getString("value2", null);
      bean.setValue2((StringUtils.hasText(temp)) ? ValueType.valueOf(temp): defaultBean.getValue2());
    }

     {
      String temp=prefs.getString("value1", null);
      bean.value1=(StringUtils.hasText(temp)) ? ValueType.valueOf(temp): defaultBean.value1;
    }

     {
      String temp=prefs.getString("value_date", null);
      bean.valueDate=(StringUtils.hasText(temp)) ? DateUtils.read(temp): defaultBean.valueDate;}

    bean.valueInt=(int)prefs.getInt("value_int", (int)defaultBean.valueInt);
    bean.valueShort2=(short)prefs.getInt("value_short2", (short)(defaultBean.valueShort2==null?(short)0:defaultBean.valueShort2));
     {
      String temp=prefs.getString("value_url", null);
      bean.valueUrl=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): defaultBean.valueUrl;}

    bean.valueFloat=prefs.getFloat("value_float", defaultBean.valueFloat);
     {
      String temp=prefs.getString("list_string", null);
      bean.listString=StringUtils.hasText(temp) ? parseListString(temp): defaultBean.listString;
    }

    bean.valueChar2=(char)prefs.getInt("value_char2", (char)(defaultBean.valueChar2==null?(char)0:defaultBean.valueChar2));
    bean.valueFloat2=prefs.getFloat("value_float2", (defaultBean.valueFloat2==null?0F:defaultBean.valueFloat2));
     {
      Set<String> temp=prefs.getStringSet("set_string", defaultBean.setString);
      bean.setString=temp;
    }

    bean.valueChar=(char)prefs.getInt("value_char", (char)defaultBean.valueChar);
    bean.valueString=prefs.getString("value_string", defaultBean.valueString);
    bean.valueLong=prefs.getLong("value_long", defaultBean.valueLong);
    bean.valueBoolean2=(boolean)prefs.getBoolean("value_boolean2", (boolean)(defaultBean.valueBoolean2==null?false:defaultBean.valueBoolean2));
    bean.valueShort=(short)prefs.getInt("value_short", (short)defaultBean.valueShort);
    bean.valueLong2=prefs.getLong("value_long2", (defaultBean.valueLong2==null?0L:defaultBean.valueLong2));
     {
      String temp=prefs.getString("value_double", null);
      bean.valueDouble=(StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble;
    }

     {
      String temp=prefs.getString("value_locale", null);
      bean.valueLocale=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): defaultBean.valueLocale;}

     {
      String temp=prefs.getString("value_big_decimal", "0");
      bean.valueBigDecimal=(StringUtils.hasText(temp)) ? new BigDecimal(temp): defaultBean.valueBigDecimal;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App4Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("value_boolean",(boolean)bean.valueBoolean);

    if (bean.valueInt2!=null)  {
      editor.putInt("value_int2",(int)bean.valueInt2);
    }

    if (bean.valueDouble2!=null)  {
      editor.putString("value_double2",String.valueOf(bean.valueDouble2));
    } else {
      editor.remove("valueDouble2");
    }

    if (bean.getValue2()!=null)  {
      editor.putString("value2",bean.getValue2().toString() );
    } else {
      editor.remove("value2");
    }

    if (bean.value1!=null)  {
      editor.putString("value1",bean.value1.toString() );
    } else {
      editor.remove("value1");
    }

    if (bean.valueDate!=null)  {
      editor.putString("value_date",DateUtils.write(bean.valueDate));
    } else {
      editor.remove("valueDate");
    }

    editor.putInt("value_int",(int)bean.valueInt);

    if (bean.valueShort2!=null)  {
      editor.putInt("value_short2",(int)bean.valueShort2);
    }

    if (bean.valueUrl!=null)  {
      editor.putString("value_url",UrlUtils.write(bean.valueUrl));
    } else {
      editor.remove("valueUrl");
    }

    editor.putFloat("value_float",bean.valueFloat);

    if (bean.listString!=null)  {
      String temp=serializeListString(bean.listString);
      editor.putString("list_string",temp);
    }  else  {
      editor.remove("list_string");
    }

    if (bean.valueChar2!=null)  {
      editor.putInt("value_char2",(char)bean.valueChar2);
    }

    if (bean.valueFloat2!=null)  {
      editor.putFloat("value_float2",bean.valueFloat2);
    }

    editor.putStringSet("set_string",bean.setString);

    editor.putInt("value_char",(char)bean.valueChar);

    editor.putString("value_string",bean.valueString);

    editor.putLong("value_long",bean.valueLong);

    if (bean.valueBoolean2!=null)  {
      editor.putBoolean("value_boolean2",(boolean)bean.valueBoolean2);
    }

    editor.putInt("value_short",(int)bean.valueShort);

    if (bean.valueLong2!=null)  {
      editor.putLong("value_long2",bean.valueLong2);
    }

    editor.putString("value_double",String.valueOf(bean.valueDouble));

    if (bean.valueLocale!=null)  {
      editor.putString("value_locale",LocaleUtils.write(bean.valueLocale));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.valueBigDecimal!=null) editor.putString("value_big_decimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("value_big_decimal", null);

    editor.commit();
  }

  /**
   * reads property <code>valueBoolean</code> from shared pref with key <code>value_boolean</code>
   *
   * @return property valueBoolean value
   */
  public boolean getValueBoolean() {
    return (boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);}

  /**
   * reads property <code>valueInt2</code> from shared pref with key <code>value_int2</code>
   *
   * @return property valueInt2 value
   */
  public Integer getValueInt2() {
    return (int)prefs.getInt("value_int2", (int)(defaultBean.valueInt2==null?0:defaultBean.valueInt2));}

  /**
   * reads property <code>valueDouble2</code> from shared pref with key <code>value_double2</code>
   *
   * @return property valueDouble2 value
   */
  public Double getValueDouble2() {
    String temp=prefs.getString("value_double2", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble2;
  }

  /**
   * reads property <code>value2</code> from shared pref with key <code>value2</code>
   *
   * @return property value2 value
   */
  public ValueType getValue2() {
    String temp=prefs.getString("value2", null);
    return (StringUtils.hasText(temp)) ? ValueType.valueOf(temp): defaultBean.getValue2();
  }

  /**
   * reads property <code>value1</code> from shared pref with key <code>value1</code>
   *
   * @return property value1 value
   */
  public ValueType getValue1() {
    String temp=prefs.getString("value1", null);
    return (StringUtils.hasText(temp)) ? ValueType.valueOf(temp): defaultBean.value1;
  }

  /**
   * reads property <code>valueDate</code> from shared pref with key <code>value_date</code>
   *
   * @return property valueDate value
   */
  public Date getValueDate() {
    String temp=prefs.getString("value_date", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): defaultBean.valueDate;}

  /**
   * reads property <code>valueInt</code> from shared pref with key <code>value_int</code>
   *
   * @return property valueInt value
   */
  public int getValueInt() {
    return (int)prefs.getInt("value_int", (int)defaultBean.valueInt);}

  /**
   * reads property <code>valueShort2</code> from shared pref with key <code>value_short2</code>
   *
   * @return property valueShort2 value
   */
  public Short getValueShort2() {
    return (short)prefs.getInt("value_short2", (short)(defaultBean.valueShort2==null?(short)0:defaultBean.valueShort2));}

  /**
   * reads property <code>valueUrl</code> from shared pref with key <code>value_url</code>
   *
   * @return property valueUrl value
   */
  public URL getValueUrl() {
    String temp=prefs.getString("value_url", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): defaultBean.valueUrl;}

  /**
   * reads property <code>valueFloat</code> from shared pref with key <code>value_float</code>
   *
   * @return property valueFloat value
   */
  public float getValueFloat() {
    return prefs.getFloat("value_float", defaultBean.valueFloat);}

  /**
   * reads property <code>listString</code> from shared pref with key <code>list_string</code>
   *
   * @return property listString value
   */
  public List<String> getListString() {
    String temp=prefs.getString("list_string", null);
    return StringUtils.hasText(temp) ? parseListString(temp): defaultBean.listString;
  }

  /**
   * reads property <code>valueChar2</code> from shared pref with key <code>value_char2</code>
   *
   * @return property valueChar2 value
   */
  public Character getValueChar2() {
    return (char)prefs.getInt("value_char2", (char)(defaultBean.valueChar2==null?(char)0:defaultBean.valueChar2));}

  /**
   * reads property <code>valueFloat2</code> from shared pref with key <code>value_float2</code>
   *
   * @return property valueFloat2 value
   */
  public Float getValueFloat2() {
    return prefs.getFloat("value_float2", (defaultBean.valueFloat2==null?0F:defaultBean.valueFloat2));}

  /**
   * reads property <code>setString</code> from shared pref with key <code>set_string</code>
   *
   * @return property setString value
   */
  public Set<String> getSetString() {
    Set<String> temp=prefs.getStringSet("set_string", defaultBean.setString);
    return temp;
  }

  /**
   * reads property <code>valueChar</code> from shared pref with key <code>value_char</code>
   *
   * @return property valueChar value
   */
  public char getValueChar() {
    return (char)prefs.getInt("value_char", (char)defaultBean.valueChar);}

  /**
   * reads property <code>valueString</code> from shared pref with key <code>value_string</code>
   *
   * @return property valueString value
   */
  public String getValueString() {
    return prefs.getString("value_string", defaultBean.valueString);}

  /**
   * reads property <code>valueLong</code> from shared pref with key <code>value_long</code>
   *
   * @return property valueLong value
   */
  public long getValueLong() {
    return prefs.getLong("value_long", defaultBean.valueLong);}

  /**
   * reads property <code>valueBoolean2</code> from shared pref with key <code>value_boolean2</code>
   *
   * @return property valueBoolean2 value
   */
  public Boolean getValueBoolean2() {
    return (boolean)prefs.getBoolean("value_boolean2", (boolean)(defaultBean.valueBoolean2==null?false:defaultBean.valueBoolean2));}

  /**
   * reads property <code>valueShort</code> from shared pref with key <code>value_short</code>
   *
   * @return property valueShort value
   */
  public short getValueShort() {
    return (short)prefs.getInt("value_short", (short)defaultBean.valueShort);}

  /**
   * reads property <code>valueLong2</code> from shared pref with key <code>value_long2</code>
   *
   * @return property valueLong2 value
   */
  public Long getValueLong2() {
    return prefs.getLong("value_long2", (defaultBean.valueLong2==null?0L:defaultBean.valueLong2));}

  /**
   * reads property <code>valueDouble</code> from shared pref with key <code>value_double</code>
   *
   * @return property valueDouble value
   */
  public double getValueDouble() {
    String temp=prefs.getString("value_double", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): defaultBean.valueDouble;
  }

  /**
   * reads property <code>valueLocale</code> from shared pref with key <code>value_locale</code>
   *
   * @return property valueLocale value
   */
  public Locale getValueLocale() {
    String temp=prefs.getString("value_locale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): defaultBean.valueLocale;}

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
   * for attribute listString serialization
   */
  protected String serializeListString(List<String> value) {
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
        jacksonSerializer.writeFieldName("listString");
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
   * for attribute listString parsing
   */
  protected List<String> parseListString(String input) {
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
      List<String> result=null;
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
  public static synchronized BindApp4Preferences getInstance() {
    if (instance==null) {
      instance=new BindApp4Preferences();
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
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("value_boolean",(boolean)value);

      return this;
    }

    /**
     * remove property valueBoolean
     */
    public BindEditor removeValueBoolean() {
      editor.remove("value_boolean");
      return this;
    }

    /**
     * modifier for property valueInt2
     */
    public BindEditor putValueInt2(Integer value) {
      if (value!=null)  {
        editor.putInt("value_int2",(int)value);
      }

      return this;
    }

    /**
     * remove property valueInt2
     */
    public BindEditor removeValueInt2() {
      editor.remove("value_int2");
      return this;
    }

    /**
     * modifier for property valueDouble2
     */
    public BindEditor putValueDouble2(Double value) {
      if (value!=null)  {
        editor.putString("value_double2",String.valueOf(value));
      } else {
        editor.remove("valueDouble2");
      }

      return this;
    }

    /**
     * remove property valueDouble2
     */
    public BindEditor removeValueDouble2() {
      editor.remove("value_double2");
      return this;
    }

    /**
     * modifier for property value2
     */
    public BindEditor putValue2(ValueType value) {
      if (value!=null)  {
        editor.putString("value2",value.toString() );
      } else {
        editor.remove("value2");
      }

      return this;
    }

    /**
     * remove property value2
     */
    public BindEditor removeValue2() {
      editor.remove("value2");
      return this;
    }

    /**
     * modifier for property value1
     */
    public BindEditor putValue1(ValueType value) {
      if (value!=null)  {
        editor.putString("value1",value.toString() );
      } else {
        editor.remove("value1");
      }

      return this;
    }

    /**
     * remove property value1
     */
    public BindEditor removeValue1() {
      editor.remove("value1");
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
     * modifier for property valueInt
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("value_int",(int)value);

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
     * modifier for property valueShort2
     */
    public BindEditor putValueShort2(Short value) {
      if (value!=null)  {
        editor.putInt("value_short2",(int)value);
      }

      return this;
    }

    /**
     * remove property valueShort2
     */
    public BindEditor removeValueShort2() {
      editor.remove("value_short2");
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
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("value_float",value);

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
     * modifier for property listString
     */
    public BindEditor putListString(List<String> value) {
      if (value!=null)  {
        String temp=serializeListString(value);
        editor.putString("list_string",temp);
      }  else  {
        editor.remove("list_string");
      }

      return this;
    }

    /**
     * remove property listString
     */
    public BindEditor removeListString() {
      editor.remove("list_string");
      return this;
    }

    /**
     * modifier for property valueChar2
     */
    public BindEditor putValueChar2(Character value) {
      if (value!=null)  {
        editor.putInt("value_char2",(char)value);
      }

      return this;
    }

    /**
     * remove property valueChar2
     */
    public BindEditor removeValueChar2() {
      editor.remove("value_char2");
      return this;
    }

    /**
     * modifier for property valueFloat2
     */
    public BindEditor putValueFloat2(Float value) {
      if (value!=null)  {
        editor.putFloat("value_float2",value);
      }

      return this;
    }

    /**
     * remove property valueFloat2
     */
    public BindEditor removeValueFloat2() {
      editor.remove("value_float2");
      return this;
    }

    /**
     * modifier for property setString
     */
    public BindEditor putSetString(Set<String> value) {
      editor.putStringSet("set_string",value);

      return this;
    }

    /**
     * remove property setString
     */
    public BindEditor removeSetString() {
      editor.remove("set_string");
      return this;
    }

    /**
     * modifier for property valueChar
     */
    public BindEditor putValueChar(char value) {
      editor.putInt("value_char",(char)value);

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
     * modifier for property valueLong
     */
    public BindEditor putValueLong(long value) {
      editor.putLong("value_long",value);

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
     * modifier for property valueBoolean2
     */
    public BindEditor putValueBoolean2(Boolean value) {
      if (value!=null)  {
        editor.putBoolean("value_boolean2",(boolean)value);
      }

      return this;
    }

    /**
     * remove property valueBoolean2
     */
    public BindEditor removeValueBoolean2() {
      editor.remove("value_boolean2");
      return this;
    }

    /**
     * modifier for property valueShort
     */
    public BindEditor putValueShort(short value) {
      editor.putInt("value_short",(int)value);

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
     * modifier for property valueLong2
     */
    public BindEditor putValueLong2(Long value) {
      if (value!=null)  {
        editor.putLong("value_long2",value);
      }

      return this;
    }

    /**
     * remove property valueLong2
     */
    public BindEditor removeValueLong2() {
      editor.remove("value_long2");
      return this;
    }

    /**
     * modifier for property valueDouble
     */
    public BindEditor putValueDouble(double value) {
      editor.putString("value_double",String.valueOf(value));

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
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}

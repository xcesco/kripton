package sqlite.kripton50;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the shared preference binder defined for RightPreferences
 *
 * @see RightPreferences
 */
public class BindRightPreferencesSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindRightPreferencesSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final RightPreferences defaultBean;

  /**
   * constructor
   */
  private BindRightPreferencesSharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new RightPreferences();
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
    RightPreferences bean=new RightPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public RightPreferences read() {
    RightPreferences bean=new RightPreferences();
    bean.name=prefs.getString("name", bean.name);
    bean.setDescription(prefs.getString("description", bean.getDescription()));
    bean.valueFloat=prefs.getFloat("valueFloat", bean.valueFloat);
    bean.valueBoolean=(boolean)prefs.getBoolean("valueBoolean", (boolean)bean.valueBoolean);
     {
      String temp=prefs.getString("stringArray", null);
      bean.setStringArray(StringUtils.hasText(temp) ? parseStringArray(temp): null);
    }

     {
      String temp=prefs.getString("stringList", null);
      bean.stringList=StringUtils.hasText(temp) ? parseStringList(temp): null;
    }

    bean.valueInt=(int)prefs.getInt("valueInt", (int)bean.valueInt);
    bean.valueLong=prefs.getLong("valueLong", (bean.valueLong==null?0L:bean.valueLong));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(RightPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);

    editor.putString("description",bean.getDescription());

    editor.putFloat("valueFloat",bean.valueFloat);

    editor.putBoolean("valueBoolean",(boolean)bean.valueBoolean);

    if (bean.getStringArray()!=null)  {
      String temp=serializeStringArray(bean.getStringArray());
      editor.putString("stringArray",temp);
    }  else  {
      editor.remove("stringArray");
    }

    if (bean.stringList!=null)  {
      String temp=serializeStringList(bean.stringList);
      editor.putString("stringList",temp);
    }  else  {
      editor.remove("stringList");
    }

    editor.putInt("valueInt",(int)bean.valueInt);

    if (bean.valueLong!=null)  {
      editor.putLong("valueLong",bean.valueLong);
    }


    editor.commit();
  }

  /**
   * read property name
   *
   * @return property name value
   */
  public String name() {
    return prefs.getString("name", defaultBean.name);
  }

  /**
   * read property description
   *
   * @return property description value
   */
  public String description() {
    return prefs.getString("description", defaultBean.getDescription());
  }

  /**
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public float valueFloat() {
    return prefs.getFloat("valueFloat", defaultBean.valueFloat);
  }

  /**
   * read property valueBoolean
   *
   * @return property valueBoolean value
   */
  public boolean valueBoolean() {
    return (boolean)prefs.getBoolean("valueBoolean", (boolean)defaultBean.valueBoolean);
  }

  /**
   * read property stringArray
   *
   * @return property stringArray value
   */
  public String[] stringArray() {
    String temp=prefs.getString("stringArray", null);
    return StringUtils.hasText(temp) ? parseStringArray(temp): null;

  }

  /**
   * read property stringList
   *
   * @return property stringList value
   */
  public List<String> stringList() {
    String temp=prefs.getString("stringList", null);
    return StringUtils.hasText(temp) ? parseStringList(temp): null;

  }

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public int valueInt() {
    return (int)prefs.getInt("valueInt", (int)defaultBean.valueInt);
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
   * write
   */
  protected static String serializeStringArray(String[] value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("stringArray");
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
  protected static String[] parseStringArray(String input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
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
  protected static String serializeStringList(List<String> value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("stringList");
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
  protected static List<String> parseStringList(String input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
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
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindRightPreferencesSharedPreferences instance() {
    if (instance==null) {
      instance=new BindRightPreferencesSharedPreferences();
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
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name",value);

      return this;
    }

    /**
     * modifier for property description
     */
    public BindEditor putDescription(String value) {
      editor.putString("description",value);

      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("valueFloat",value);

      return this;
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("valueBoolean",(boolean)value);

      return this;
    }

    /**
     * modifier for property stringArray
     */
    public BindEditor putStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeStringArray(value);
        editor.putString("stringArray",temp);
      }  else  {
        editor.remove("stringArray");
      }

      return this;
    }

    /**
     * modifier for property stringList
     */
    public BindEditor putStringList(List<String> value) {
      if (value!=null)  {
        String temp=serializeStringList(value);
        editor.putString("stringList",temp);
      }  else  {
        editor.remove("stringList");
      }

      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("valueInt",(int)value);

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
  }
}

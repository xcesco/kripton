package sqlite.kripton63;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the shared preference binder defined for Bean63
 *
 * @see Bean63
 */
public class BindBean63SharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBean63SharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean63 defaultBean;

  /**
   * constructor
   */
  private BindBean63SharedPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Bean63();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values
   */
  public void refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    Bean63 bean=new Bean63();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean63 read() {
    Bean63 bean=new Bean63();
    bean.id=prefs.getLong("bean63", bean.id);
    bean.value=prefs.getString("bean63", bean.value);
     {
      String temp=prefs.getString("bean63", null);
      bean.valueMapStringByte=StringUtils.hasText(temp) ? parseValueMapStringByte(temp): null;
    }

     {
      String temp=prefs.getString("bean63", null);
      bean.valueMapEnumByte=StringUtils.hasText(temp) ? parseValueMapEnumByte(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean63 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("bean63",bean.id);

    editor.putString("bean63",bean.value);

    if (bean.valueMapStringByte!=null)  {
      String temp=serializeValueMapStringByte(bean.valueMapStringByte);
      editor.putString("valueMapStringByte",temp);
    }  else  {
      editor.remove("valueMapStringByte");
    }

    if (bean.valueMapEnumByte!=null)  {
      String temp=serializeValueMapEnumByte(bean.valueMapEnumByte);
      editor.putString("valueMapEnumByte",temp);
    }  else  {
      editor.remove("valueMapEnumByte");
    }


    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long id() {
    return prefs.getLong("bean63", defaultBean.id);
  }

  /**
   * read property value
   *
   * @return property value value
   */
  public String value() {
    return prefs.getString("bean63", defaultBean.value);
  }

  /**
   * read property valueMapStringByte
   *
   * @return property valueMapStringByte value
   */
  public Map<String, Byte> valueMapStringByte() {
    String temp=prefs.getString("bean63", null);
    return StringUtils.hasText(temp) ? parseValueMapStringByte(temp): null;

  }

  /**
   * read property valueMapEnumByte
   *
   * @return property valueMapEnumByte value
   */
  public HashMap<EnumType, Byte> valueMapEnumByte() {
    String temp=prefs.getString("bean63", null);
    return StringUtils.hasText(temp) ? parseValueMapEnumByte(temp): null;

  }

  /**
   * for attribute valueMapStringByte serialization
   */
  protected String serializeValueMapStringByte(Map<String, Byte> value) {
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
          jacksonSerializer.writeFieldName("valueMapStringByte");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeNumberField("value", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringByte");
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
   * for attribute valueMapStringByte parsing
   */
  protected Map<String, Byte> parseValueMapStringByte(String input) {
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
      Map<String, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Byte> collection=new HashMap<>();
        String key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
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
   * for attribute valueMapEnumByte serialization
   */
  protected String serializeValueMapEnumByte(HashMap<EnumType, Byte> value) {
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
          jacksonSerializer.writeFieldName("valueMapEnumByte");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<EnumType, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeNumberField("value", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapEnumByte");
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
   * for attribute valueMapEnumByte parsing
   */
  protected HashMap<EnumType, Byte> parseValueMapEnumByte(String input) {
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
      HashMap<EnumType, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<EnumType, Byte> collection=new HashMap<>();
        EnumType key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
           {
            String tempEnum=jacksonParser.getText();
            key=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
          }
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
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
   * get instance of shared preferences
   */
  public static synchronized BindBean63SharedPreferences instance() {
    if (instance==null) {
      instance=new BindBean63SharedPreferences();
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
      editor.putLong("bean63",value);

      return this;
    }

    /**
     * modifier for property value
     */
    public BindEditor putValue(String value) {
      editor.putString("bean63",value);

      return this;
    }

    /**
     * modifier for property valueMapStringByte
     */
    public BindEditor putValueMapStringByte(Map<String, Byte> value) {
      if (value!=null)  {
        String temp=serializeValueMapStringByte(value);
        editor.putString("valueMapStringByte",temp);
      }  else  {
        editor.remove("valueMapStringByte");
      }

      return this;
    }

    /**
     * modifier for property valueMapEnumByte
     */
    public BindEditor putValueMapEnumByte(HashMap<EnumType, Byte> value) {
      if (value!=null)  {
        String temp=serializeValueMapEnumByte(value);
        editor.putString("valueMapEnumByte",temp);
      }  else  {
        editor.remove("valueMapEnumByte");
      }

      return this;
    }
  }
}

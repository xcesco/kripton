package shared.kripton46;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the shared preference binder defined for AppPreferences
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindAppPreferences instance;

  /**
   * working instance of bean
   */
  private final AppPreferences defaultBean;

  /**
   * constructor
   */
  private BindAppPreferences() {
    createPrefs();
    defaultBean=new AppPreferences();
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
  public BindAppPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    AppPreferences bean=new AppPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public AppPreferences read() {
    AppPreferences bean=new AppPreferences();
    bean.name=prefs.getString("name", bean.name);
    bean.setDescription(prefs.getString("description", bean.getDescription()));
    bean.valueFloat=prefs.getFloat("value_float", bean.valueFloat);
    bean.valueBoolean=(boolean)prefs.getBoolean("value_boolean", (boolean)bean.valueBoolean);
     {
      String temp=prefs.getString("string_array", null);
      bean.setStringArray(StringUtils.hasText(temp) ? parseStringArray(temp): bean.getStringArray());
    }

     {
      String temp=prefs.getString("string_list", null);
      bean.stringList=StringUtils.hasText(temp) ? parseStringList(temp): bean.stringList;
    }

    bean.valueInt=(int)prefs.getInt("value_int", (int)bean.valueInt);
    bean.valueLong=prefs.getLong("value_long", (bean.valueLong==null?0L:bean.valueLong));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);

    editor.putString("description",bean.getDescription());

    editor.putFloat("value_float",bean.valueFloat);

    editor.putBoolean("value_boolean",(boolean)bean.valueBoolean);

    if (bean.getStringArray()!=null)  {
      String temp=serializeStringArray(bean.getStringArray());
      editor.putString("string_array",temp);
    }  else  {
      editor.remove("string_array");
    }

    if (bean.stringList!=null)  {
      String temp=serializeStringList(bean.stringList);
      editor.putString("string_list",temp);
    }  else  {
      editor.remove("string_list");
    }

    editor.putInt("value_int",(int)bean.valueInt);

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }


    editor.commit();
  }

  /**
   * reads property <code>name</code> from shared pref with key <code>name</code>
   *
   * @return property name value
   */
  public String getName() {
    return prefs.getString("name", defaultBean.name);}

  /**
   * reads property <code>description</code> from shared pref with key <code>description</code>
   *
   * @return property description value
   */
  public String getDescription() {
    return prefs.getString("description", defaultBean.getDescription());}

  /**
   * reads property <code>valueFloat</code> from shared pref with key <code>value_float</code>
   *
   * @return property valueFloat value
   */
  public float getValueFloat() {
    return prefs.getFloat("value_float", defaultBean.valueFloat);}

  /**
   * reads property <code>valueBoolean</code> from shared pref with key <code>value_boolean</code>
   *
   * @return property valueBoolean value
   */
  public boolean getValueBoolean() {
    return (boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);}

  /**
   * reads property <code>stringArray</code> from shared pref with key <code>string_array</code>
   *
   * @return property stringArray value
   */
  public String[] getStringArray() {
    String temp=prefs.getString("string_array", null);
    return StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray();
  }

  /**
   * reads property <code>stringList</code> from shared pref with key <code>string_list</code>
   *
   * @return property stringList value
   */
  public List<String> getStringList() {
    String temp=prefs.getString("string_list", null);
    return StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.stringList;
  }

  /**
   * reads property <code>valueInt</code> from shared pref with key <code>value_int</code>
   *
   * @return property valueInt value
   */
  public int getValueInt() {
    return (int)prefs.getInt("value_int", (int)defaultBean.valueInt);}

  /**
   * reads property <code>valueLong</code> from shared pref with key <code>value_long</code>
   *
   * @return property valueLong value
   */
  public Long getValueLong() {
    return prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));}

  /**
   * for attribute stringArray serialization
   */
  protected String serializeStringArray(String[] value) {
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
   * for attribute stringArray parsing
   */
  protected String[] parseStringArray(String input) {
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
   * for attribute stringList serialization
   */
  protected String serializeStringList(List<String> value) {
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
   * for attribute stringList parsing
   */
  protected List<String> parseStringList(String input) {
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
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindAppPreferences getInstance() {
    if (instance==null) {
      instance=new BindAppPreferences();
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
      editor.putFloat("value_float",value);

      return this;
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("value_boolean",(boolean)value);

      return this;
    }

    /**
     * modifier for property stringArray
     */
    public BindEditor putStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeStringArray(value);
        editor.putString("string_array",temp);
      }  else  {
        editor.remove("string_array");
      }

      return this;
    }

    /**
     * modifier for property stringList
     */
    public BindEditor putStringList(List<String> value) {
      if (value!=null)  {
        String temp=serializeStringList(value);
        editor.putString("string_list",temp);
      }  else  {
        editor.remove("string_list");
      }

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
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
      }

      return this;
    }
  }
}

package shared.feature.immutable.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

  @SuppressWarnings("rawtypes")
  private List<Pair<String, WeakReference<LiveDataHandler>>> liveDatas = new CopyOnWriteArrayList<Pair<String, WeakReference<LiveDataHandler>>>();

  /**
   * Listener used to propagate shared prefs changes through RX
   */
  private SharedPreferences.OnSharedPreferenceChangeListener liveDataListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences,
        final String key) {
      KriptonLibrary.getExecutorService().execute(new Runnable() {
        @Override
        public void run() {
          switch (key) {
            // value_boolean - valueBoolean
            case "value_boolean": {
            boolean _value=(boolean)sharedPreferences.getBoolean("value_boolean", (boolean)defaultBean.isValueBoolean());
            updateLiveData("value_boolean", _value);
            return;
            }
            // string_list - stringList
            case "string_list": {
            String temp=sharedPreferences.getString("string_list", null);
            List<String> _value=StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.getStringList();

            updateLiveData("string_list", _value);
            return;
            }
            // name - name
            case "name": {
            String _value=sharedPreferences.getString("name", defaultBean.getName());
            updateLiveData("name", _value);
            return;
            }
            // description - description
            case "description": {
            String _value=sharedPreferences.getString("description", defaultBean.getDescription());
            updateLiveData("description", _value);
            return;
            }
            // string_array - stringArray
            case "string_array": {
            String temp=sharedPreferences.getString("string_array", null);
            String[] _value=StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray();

            updateLiveData("string_array", _value);
            return;
            }
            // value_float - valueFloat
            case "value_float": {
            float _value=sharedPreferences.getFloat("value_float", defaultBean.getValueFloat());
            updateLiveData("value_float", _value);
            return;
            }
            default: return;
          }
        }
      });
    }
  };

  /**
   * constructor
   */
  private BindAppPreferences() {
    createPrefs();
    // immutable object: initialize temporary variables for properties
    String __name=null;
    String __description=null;
    float __valueFloat=0.0f;
    boolean __valueBoolean=false;
    String[] __stringArray=null;
    List<String> __stringList=null;
    // immutable object: inizialize object
    defaultBean=new AppPreferences(__name,__description,__valueFloat,__valueBoolean,__stringArray,(__stringList==null ? null : Collections.unmodifiableList(__stringList)));
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
    prefs.registerOnSharedPreferenceChangeListener(liveDataListener);
  }

  /**
   * force to refresh values
   */
  public BindAppPreferences refresh() {
    createPrefs();
    return this;
  }

  @SuppressWarnings("rawtypes")
  protected void registryLiveData(String key, LiveDataHandler value) {
    liveDatas.add(new Pair<String , WeakReference<LiveDataHandler>>(key, new WeakReference<LiveDataHandler>(value)));
  }

  @SuppressWarnings("rawtypes")
  protected void updateLiveData(String key, Object value) {
    for (Pair<String, WeakReference<LiveDataHandler>> item : liveDatas) {
      if (item.value0.equals(key) && item.value1.get() != null) {
        item.value1.get().invalidate();
      }
    }
  }

  /**
   * Obtains an LiveData to <code>valueBoolean</code> property
   *
   * @return
   * an LiveData to <code>valueBoolean</code> property
   */
  public MutableLiveData<Boolean> getValueBooleanAsLiveData() {
    KriptonLiveDataHandlerImpl<Boolean> liveData=new KriptonLiveDataHandlerImpl<Boolean>() {
      @Override
      protected Boolean compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueBoolean();
      }
    };
    registryLiveData("value_boolean", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>stringList</code> property
   *
   * @return
   * an LiveData to <code>stringList</code> property
   */
  public MutableLiveData<List<String>> getStringListAsLiveData() {
    KriptonLiveDataHandlerImpl<List<String>> liveData=new KriptonLiveDataHandlerImpl<List<String>>() {
      @Override
      protected List<String> compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getStringList();
      }
    };
    registryLiveData("string_list", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>name</code> property
   *
   * @return
   * an LiveData to <code>name</code> property
   */
  public MutableLiveData<String> getNameAsLiveData() {
    KriptonLiveDataHandlerImpl<String> liveData=new KriptonLiveDataHandlerImpl<String>() {
      @Override
      protected String compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getName();
      }
    };
    registryLiveData("name", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>description</code> property
   *
   * @return
   * an LiveData to <code>description</code> property
   */
  public MutableLiveData<String> getDescriptionAsLiveData() {
    KriptonLiveDataHandlerImpl<String> liveData=new KriptonLiveDataHandlerImpl<String>() {
      @Override
      protected String compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getDescription();
      }
    };
    registryLiveData("description", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>stringArray</code> property
   *
   * @return
   * an LiveData to <code>stringArray</code> property
   */
  public MutableLiveData<String[]> getStringArrayAsLiveData() {
    KriptonLiveDataHandlerImpl<String[]> liveData=new KriptonLiveDataHandlerImpl<String[]>() {
      @Override
      protected String[] compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getStringArray();
      }
    };
    registryLiveData("string_array", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>valueFloat</code> property
   *
   * @return
   * an LiveData to <code>valueFloat</code> property
   */
  public MutableLiveData<Float> getValueFloatAsLiveData() {
    KriptonLiveDataHandlerImpl<Float> liveData=new KriptonLiveDataHandlerImpl<Float>() {
      @Override
      protected Float compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueFloat();
      }
    };
    registryLiveData("value_float", liveData);
    return liveData.getLiveData();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    // immutable object: initialize temporary variables for properties
    String __name=null;
    String __description=null;
    float __valueFloat=0.0f;
    boolean __valueBoolean=false;
    String[] __stringArray=null;
    List<String> __stringList=null;
    // immutable object: inizialize object
    AppPreferences bean=new AppPreferences(__name,__description,__valueFloat,__valueBoolean,__stringArray,(__stringList==null ? null : Collections.unmodifiableList(__stringList)));
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public AppPreferences read() {

    // initialize temporary variable for immutable POJO
    // immutable object: initialize temporary variables for properties
    String __name=null;
    String __description=null;
    float __valueFloat=0.0f;
    boolean __valueBoolean=false;
    String[] __stringArray=null;
    List<String> __stringList=null;
    __valueBoolean=(boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.isValueBoolean());
     {
      String temp=prefs.getString("string_list", null);
      __stringList=StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.getStringList();
    }

    __name=prefs.getString("name", defaultBean.getName());
    __description=prefs.getString("description", defaultBean.getDescription());
     {
      String temp=prefs.getString("string_array", null);
      __stringArray=StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray();
    }

    __valueFloat=prefs.getFloat("value_float", defaultBean.getValueFloat());
    // reset temporary variable for immutable POJO
    // immutable object: inizialize object
    AppPreferences bean=new AppPreferences(__name,__description,__valueFloat,__valueBoolean,__stringArray,(__stringList==null ? null : Collections.unmodifiableList(__stringList)));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("value_boolean",(boolean)bean.isValueBoolean());

    if (bean.getStringList()!=null)  {
      String temp=serializeStringList(bean.getStringList());
      editor.putString("string_list",temp);
    }  else  {
      editor.remove("string_list");
    }

    editor.putString("name",bean.getName());

    editor.putString("description",bean.getDescription());

    if (bean.getStringArray()!=null)  {
      String temp=serializeStringArray(bean.getStringArray());
      editor.putString("string_array",temp);
    }  else  {
      editor.remove("string_array");
    }

    editor.putFloat("value_float",bean.getValueFloat());


    editor.commit();
  }

  /**
   * reads property <code>valueBoolean</code> from shared pref with key <code>value_boolean</code>
   *
   * @return property valueBoolean value
   */
  public boolean getValueBoolean() {
    return (boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.isValueBoolean());}

  /**
   * reads property <code>stringList</code> from shared pref with key <code>string_list</code>
   *
   * @return property stringList value
   */
  public List<String> getStringList() {
    String temp=prefs.getString("string_list", null);
    return StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.getStringList();
  }

  /**
   * reads property <code>name</code> from shared pref with key <code>name</code>
   *
   * @return property name value
   */
  public String getName() {
    return prefs.getString("name", defaultBean.getName());}

  /**
   * reads property <code>description</code> from shared pref with key <code>description</code>
   *
   * @return property description value
   */
  public String getDescription() {
    return prefs.getString("description", defaultBean.getDescription());}

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
   * reads property <code>valueFloat</code> from shared pref with key <code>value_float</code>
   *
   * @return property valueFloat value
   */
  public float getValueFloat() {
    return prefs.getFloat("value_float", defaultBean.getValueFloat());}

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
      List<String> __stringList=null;
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
        __stringList=collection;
      }
      return __stringList;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

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
      String[] __stringArray=null;
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
        __stringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
      }
      return __stringArray;
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
     * remove property stringList
     */
    public BindEditor removeStringList() {
      editor.remove("string_list");
      return this;
    }

    /**
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name",value);

      return this;
    }

    /**
     * remove property name
     */
    public BindEditor removeName() {
      editor.remove("name");
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
     * remove property description
     */
    public BindEditor removeDescription() {
      editor.remove("description");
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
     * remove property stringArray
     */
    public BindEditor removeStringArray() {
      editor.remove("string_array");
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
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}

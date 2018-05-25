package sqlite.kripton62;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
   * subject for field id - shared pref id
   */
  private Subject<Long> idSubject = BehaviorSubject.create();

  /**
   * subject for field value - shared pref value
   */
  private Subject<String> valueSubject = BehaviorSubject.create();

  /**
   * subject for field valueByteSet - shared pref value_byte_set
   */
  private Subject<Set<Byte>> valueByteSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueShortSet - shared pref value_short_set
   */
  private Subject<HashSet<Short>> valueShortSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueIntegerSet - shared pref value_integer_set
   */
  private Subject<LinkedHashSet<Integer>> valueIntegerSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueStringSet - shared pref value_string_set
   */
  private Subject<HashSet<String>> valueStringSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharacterSet - shared pref value_character_set
   */
  private Subject<Set<Character>> valueCharacterSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueFloatSet - shared pref value_float_set
   */
  private Subject<HashSet<Float>> valueFloatSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueDoubleSet - shared pref value_double_set
   */
  private Subject<HashSet<Double>> valueDoubleSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueBigDecimalSet - shared pref value_big_decimal_set
   */
  private Subject<HashSet<BigDecimal>> valueBigDecimalSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueBeanSet - shared pref value_bean_set
   */
  private Subject<LinkedHashSet<Bean>> valueBeanSetSubject = BehaviorSubject.create();

  /**
   * subject for field valueEnumTypeSet - shared pref value_enum_type_set
   */
  private Subject<HashSet<EnumType>> valueEnumTypeSetSubject = BehaviorSubject.create();

  /**
   * List of tables compose datasource
   */
  private SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
      switch (key) {
        // id - id
        case "id": {
        long _value=sharedPreferences.getLong("id", defaultBean.id);
        idSubject.onNext(_value); return;
        }
        // value - value
        case "value": {
        String _value=sharedPreferences.getString("value", defaultBean.value);
        valueSubject.onNext(_value); return;
        }
        // value_byte_set - valueByteSet
        case "value_byte_set": {
        String temp=sharedPreferences.getString("value_byte_set", null);
        Set<Byte> _value=StringUtils.hasText(temp) ? parseValueByteSet(temp): null;

        valueByteSetSubject.onNext(_value); return;
        }
        // value_short_set - valueShortSet
        case "value_short_set": {
        String temp=sharedPreferences.getString("value_short_set", null);
        HashSet<Short> _value=StringUtils.hasText(temp) ? parseValueShortSet(temp): null;

        valueShortSetSubject.onNext(_value); return;
        }
        // value_integer_set - valueIntegerSet
        case "value_integer_set": {
        String temp=sharedPreferences.getString("value_integer_set", null);
        LinkedHashSet<Integer> _value=StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null;

        valueIntegerSetSubject.onNext(_value); return;
        }
        // value_string_set - valueStringSet
        case "value_string_set": {
        Set<String> temp=sharedPreferences.getStringSet("value_string_set", defaultBean.getValueStringSet());
        HashSet<String> _value=new HashSet<String>(temp);

        valueStringSetSubject.onNext(_value); return;
        }
        // value_character_set - valueCharacterSet
        case "value_character_set": {
        String temp=sharedPreferences.getString("value_character_set", null);
        Set<Character> _value=StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null;

        valueCharacterSetSubject.onNext(_value); return;
        }
        // value_float_set - valueFloatSet
        case "value_float_set": {
        String temp=sharedPreferences.getString("value_float_set", null);
        HashSet<Float> _value=StringUtils.hasText(temp) ? parseValueFloatSet(temp): null;

        valueFloatSetSubject.onNext(_value); return;
        }
        // value_double_set - valueDoubleSet
        case "value_double_set": {
        String temp=sharedPreferences.getString("value_double_set", null);
        HashSet<Double> _value=StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null;

        valueDoubleSetSubject.onNext(_value); return;
        }
        // value_big_decimal_set - valueBigDecimalSet
        case "value_big_decimal_set": {
        String temp=sharedPreferences.getString("value_big_decimal_set", null);
        HashSet<BigDecimal> _value=StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null;

        valueBigDecimalSetSubject.onNext(_value); return;
        }
        // value_bean_set - valueBeanSet
        case "value_bean_set": {
        String temp=sharedPreferences.getString("value_bean_set", null);
        LinkedHashSet<Bean> _value=StringUtils.hasText(temp) ? parseValueBeanSet(temp): null;

        valueBeanSetSubject.onNext(_value); return;
        }
        // value_enum_type_set - valueEnumTypeSet
        case "value_enum_type_set": {
        String temp=sharedPreferences.getString("value_enum_type_set", null);
        HashSet<EnumType> _value=StringUtils.hasText(temp) ? parseValueEnumTypeSet(temp): null;

        valueEnumTypeSetSubject.onNext(_value); return;
        }
        default: return;
      }
    }
  };

  /**
   * BeanBindMap */
  private BeanBindMap beanBindMap = BinderUtils.mapperFor(Bean.class);

  /**
   * constructor
   */
  private BindBean2SharedPreferences() {
    createPrefs();
    defaultBean=new Bean2();
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
    prefs.registerOnSharedPreferenceChangeListener(prefsListener);
  }

  /**
   * force to refresh values
   */
  public BindBean2SharedPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * Obtains an observable to <code>id</code> property
   *
   * @return
   * an observable to <code>id</code> property
   */
  public Subject<Long> getIdAsObservable() {
    return idSubject;
  }

  /**
   * Obtains an observable to <code>value</code> property
   *
   * @return
   * an observable to <code>value</code> property
   */
  public Subject<String> getValueAsObservable() {
    return valueSubject;
  }

  /**
   * Obtains an observable to <code>valueByteSet</code> property
   *
   * @return
   * an observable to <code>valueByteSet</code> property
   */
  public Subject<Set<Byte>> getValueByteSetAsObservable() {
    return valueByteSetSubject;
  }

  /**
   * Obtains an observable to <code>valueShortSet</code> property
   *
   * @return
   * an observable to <code>valueShortSet</code> property
   */
  public Subject<HashSet<Short>> getValueShortSetAsObservable() {
    return valueShortSetSubject;
  }

  /**
   * Obtains an observable to <code>valueIntegerSet</code> property
   *
   * @return
   * an observable to <code>valueIntegerSet</code> property
   */
  public Subject<LinkedHashSet<Integer>> getValueIntegerSetAsObservable() {
    return valueIntegerSetSubject;
  }

  /**
   * Obtains an observable to <code>valueStringSet</code> property
   *
   * @return
   * an observable to <code>valueStringSet</code> property
   */
  public Subject<HashSet<String>> getValueStringSetAsObservable() {
    return valueStringSetSubject;
  }

  /**
   * Obtains an observable to <code>valueCharacterSet</code> property
   *
   * @return
   * an observable to <code>valueCharacterSet</code> property
   */
  public Subject<Set<Character>> getValueCharacterSetAsObservable() {
    return valueCharacterSetSubject;
  }

  /**
   * Obtains an observable to <code>valueFloatSet</code> property
   *
   * @return
   * an observable to <code>valueFloatSet</code> property
   */
  public Subject<HashSet<Float>> getValueFloatSetAsObservable() {
    return valueFloatSetSubject;
  }

  /**
   * Obtains an observable to <code>valueDoubleSet</code> property
   *
   * @return
   * an observable to <code>valueDoubleSet</code> property
   */
  public Subject<HashSet<Double>> getValueDoubleSetAsObservable() {
    return valueDoubleSetSubject;
  }

  /**
   * Obtains an observable to <code>valueBigDecimalSet</code> property
   *
   * @return
   * an observable to <code>valueBigDecimalSet</code> property
   */
  public Subject<HashSet<BigDecimal>> getValueBigDecimalSetAsObservable() {
    return valueBigDecimalSetSubject;
  }

  /**
   * Obtains an observable to <code>valueBeanSet</code> property
   *
   * @return
   * an observable to <code>valueBeanSet</code> property
   */
  public Subject<LinkedHashSet<Bean>> getValueBeanSetAsObservable() {
    return valueBeanSetSubject;
  }

  /**
   * Obtains an observable to <code>valueEnumTypeSet</code> property
   *
   * @return
   * an observable to <code>valueEnumTypeSet</code> property
   */
  public Subject<HashSet<EnumType>> getValueEnumTypeSetAsObservable() {
    return valueEnumTypeSetSubject;
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
    bean.id=prefs.getLong("id", bean.id);
    bean.value=prefs.getString("value", bean.value);
     {
      String temp=prefs.getString("value_byte_set", null);
      bean.setValueByteSet(StringUtils.hasText(temp) ? parseValueByteSet(temp): null);
    }

     {
      String temp=prefs.getString("value_short_set", null);
      bean.setValueShortSet(StringUtils.hasText(temp) ? parseValueShortSet(temp): null);
    }

     {
      String temp=prefs.getString("value_integer_set", null);
      bean.setValueIntegerSet(StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null);
    }

     {
      Set<String> temp=prefs.getStringSet("value_string_set", defaultBean.getValueStringSet());
      bean.setValueStringSet(new HashSet<String>(temp));
    }

     {
      String temp=prefs.getString("value_character_set", null);
      bean.setValueCharacterSet(StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null);
    }

     {
      String temp=prefs.getString("value_float_set", null);
      bean.setValueFloatSet(StringUtils.hasText(temp) ? parseValueFloatSet(temp): null);
    }

     {
      String temp=prefs.getString("value_double_set", null);
      bean.setValueDoubleSet(StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null);
    }

     {
      String temp=prefs.getString("value_big_decimal_set", null);
      bean.setValueBigDecimalSet(StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null);
    }

     {
      String temp=prefs.getString("value_bean_set", null);
      bean.setValueBeanSet(StringUtils.hasText(temp) ? parseValueBeanSet(temp): null);
    }

     {
      String temp=prefs.getString("value_enum_type_set", null);
      bean.setValueEnumTypeSet(StringUtils.hasText(temp) ? parseValueEnumTypeSet(temp): null);
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean2 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("id",bean.id);

    editor.putString("value",bean.value);

    if (bean.getValueByteSet()!=null)  {
      String temp=serializeValueByteSet(bean.getValueByteSet());
      editor.putString("value_byte_set",temp);
    }  else  {
      editor.remove("value_byte_set");
    }

    if (bean.getValueShortSet()!=null)  {
      String temp=serializeValueShortSet(bean.getValueShortSet());
      editor.putString("value_short_set",temp);
    }  else  {
      editor.remove("value_short_set");
    }

    if (bean.getValueIntegerSet()!=null)  {
      String temp=serializeValueIntegerSet(bean.getValueIntegerSet());
      editor.putString("value_integer_set",temp);
    }  else  {
      editor.remove("value_integer_set");
    }

    editor.putStringSet("value_string_set",bean.getValueStringSet());

    if (bean.getValueCharacterSet()!=null)  {
      String temp=serializeValueCharacterSet(bean.getValueCharacterSet());
      editor.putString("value_character_set",temp);
    }  else  {
      editor.remove("value_character_set");
    }

    if (bean.getValueFloatSet()!=null)  {
      String temp=serializeValueFloatSet(bean.getValueFloatSet());
      editor.putString("value_float_set",temp);
    }  else  {
      editor.remove("value_float_set");
    }

    if (bean.getValueDoubleSet()!=null)  {
      String temp=serializeValueDoubleSet(bean.getValueDoubleSet());
      editor.putString("value_double_set",temp);
    }  else  {
      editor.remove("value_double_set");
    }

    if (bean.getValueBigDecimalSet()!=null)  {
      String temp=serializeValueBigDecimalSet(bean.getValueBigDecimalSet());
      editor.putString("value_big_decimal_set",temp);
    }  else  {
      editor.remove("value_big_decimal_set");
    }

    if (bean.getValueBeanSet()!=null)  {
      String temp=serializeValueBeanSet(bean.getValueBeanSet());
      editor.putString("value_bean_set",temp);
    }  else  {
      editor.remove("value_bean_set");
    }

    if (bean.getValueEnumTypeSet()!=null)  {
      String temp=serializeValueEnumTypeSet(bean.getValueEnumTypeSet());
      editor.putString("value_enum_type_set",temp);
    }  else  {
      editor.remove("value_enum_type_set");
    }


    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long getId() {
    return prefs.getLong("id", defaultBean.id);}

  /**
   * read property value
   *
   * @return property value value
   */
  public String getValue() {
    return prefs.getString("value", defaultBean.value);}

  /**
   * read property valueByteSet
   *
   * @return property valueByteSet value
   */
  public Set<Byte> getValueByteSet() {
    String temp=prefs.getString("value_byte_set", null);
    return StringUtils.hasText(temp) ? parseValueByteSet(temp): null;
  }

  /**
   * read property valueShortSet
   *
   * @return property valueShortSet value
   */
  public HashSet<Short> getValueShortSet() {
    String temp=prefs.getString("value_short_set", null);
    return StringUtils.hasText(temp) ? parseValueShortSet(temp): null;
  }

  /**
   * read property valueIntegerSet
   *
   * @return property valueIntegerSet value
   */
  public LinkedHashSet<Integer> getValueIntegerSet() {
    String temp=prefs.getString("value_integer_set", null);
    return StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null;
  }

  /**
   * read property valueStringSet
   *
   * @return property valueStringSet value
   */
  public HashSet<String> getValueStringSet() {
    Set<String> temp=prefs.getStringSet("value_string_set", defaultBean.getValueStringSet());
    return new HashSet<String>(temp);
  }

  /**
   * read property valueCharacterSet
   *
   * @return property valueCharacterSet value
   */
  public Set<Character> getValueCharacterSet() {
    String temp=prefs.getString("value_character_set", null);
    return StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null;
  }

  /**
   * read property valueFloatSet
   *
   * @return property valueFloatSet value
   */
  public HashSet<Float> getValueFloatSet() {
    String temp=prefs.getString("value_float_set", null);
    return StringUtils.hasText(temp) ? parseValueFloatSet(temp): null;
  }

  /**
   * read property valueDoubleSet
   *
   * @return property valueDoubleSet value
   */
  public HashSet<Double> getValueDoubleSet() {
    String temp=prefs.getString("value_double_set", null);
    return StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null;
  }

  /**
   * read property valueBigDecimalSet
   *
   * @return property valueBigDecimalSet value
   */
  public HashSet<BigDecimal> getValueBigDecimalSet() {
    String temp=prefs.getString("value_big_decimal_set", null);
    return StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null;
  }

  /**
   * read property valueBeanSet
   *
   * @return property valueBeanSet value
   */
  public LinkedHashSet<Bean> getValueBeanSet() {
    String temp=prefs.getString("value_bean_set", null);
    return StringUtils.hasText(temp) ? parseValueBeanSet(temp): null;
  }

  /**
   * read property valueEnumTypeSet
   *
   * @return property valueEnumTypeSet value
   */
  public HashSet<EnumType> getValueEnumTypeSet() {
    String temp=prefs.getString("value_enum_type_set", null);
    return StringUtils.hasText(temp) ? parseValueEnumTypeSet(temp): null;
  }

  /**
   * for attribute valueByteSet serialization
   */
  protected String serializeValueByteSet(Set<Byte> value) {
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
        jacksonSerializer.writeFieldName("valueByteSet");
        jacksonSerializer.writeStartArray();
        for (Byte item: value) {
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
   * for attribute valueByteSet parsing
   */
  protected Set<Byte> parseValueByteSet(String input) {
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
      Set<Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<Byte> collection=new HashSet<>();
        Byte item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getByteValue();
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
   * for attribute valueShortSet serialization
   */
  protected String serializeValueShortSet(HashSet<Short> value) {
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
        jacksonSerializer.writeFieldName("valueShortSet");
        jacksonSerializer.writeStartArray();
        for (Short item: value) {
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
   * for attribute valueShortSet parsing
   */
  protected HashSet<Short> parseValueShortSet(String input) {
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
      HashSet<Short> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<Short> collection=new HashSet<>();
        Short item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getShortValue();
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
   * for attribute valueIntegerSet serialization
   */
  protected String serializeValueIntegerSet(LinkedHashSet<Integer> value) {
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
        jacksonSerializer.writeFieldName("valueIntegerSet");
        jacksonSerializer.writeStartArray();
        for (Integer item: value) {
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
   * for attribute valueIntegerSet parsing
   */
  protected LinkedHashSet<Integer> parseValueIntegerSet(String input) {
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
      LinkedHashSet<Integer> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedHashSet<Integer> collection=new LinkedHashSet<>();
        Integer item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getIntValue();
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
   * for attribute valueCharacterSet serialization
   */
  protected String serializeValueCharacterSet(Set<Character> value) {
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
        jacksonSerializer.writeFieldName("valueCharacterSet");
        jacksonSerializer.writeStartArray();
        for (Character item: value) {
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
   * for attribute valueCharacterSet parsing
   */
  protected Set<Character> parseValueCharacterSet(String input) {
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
      Set<Character> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<Character> collection=new HashSet<>();
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
   * for attribute valueFloatSet serialization
   */
  protected String serializeValueFloatSet(HashSet<Float> value) {
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
        jacksonSerializer.writeFieldName("valueFloatSet");
        jacksonSerializer.writeStartArray();
        for (Float item: value) {
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
   * for attribute valueFloatSet parsing
   */
  protected HashSet<Float> parseValueFloatSet(String input) {
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
      HashSet<Float> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<Float> collection=new HashSet<>();
        Float item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getFloatValue();
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
   * for attribute valueDoubleSet serialization
   */
  protected String serializeValueDoubleSet(HashSet<Double> value) {
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
        jacksonSerializer.writeFieldName("valueDoubleSet");
        jacksonSerializer.writeStartArray();
        for (Double item: value) {
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
   * for attribute valueDoubleSet parsing
   */
  protected HashSet<Double> parseValueDoubleSet(String input) {
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
      HashSet<Double> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<Double> collection=new HashSet<>();
        Double item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getDoubleValue();
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
   * for attribute valueBigDecimalSet serialization
   */
  protected String serializeValueBigDecimalSet(HashSet<BigDecimal> value) {
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
        jacksonSerializer.writeFieldName("valueBigDecimalSet");
        jacksonSerializer.writeStartArray();
        for (BigDecimal item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtils.write(item));
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
   * for attribute valueBigDecimalSet parsing
   */
  protected HashSet<BigDecimal> parseValueBigDecimalSet(String input) {
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
      HashSet<BigDecimal> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<BigDecimal> collection=new HashSet<>();
        BigDecimal item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=BigDecimalUtils.read(jacksonParser.getText());
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
   * for attribute valueBeanSet serialization
   */
  protected String serializeValueBeanSet(LinkedHashSet<Bean> value) {
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
        jacksonSerializer.writeFieldName("valueBeanSet");
        jacksonSerializer.writeStartArray();
        for (Bean item: value) {
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
   * for attribute valueBeanSet parsing
   */
  protected LinkedHashSet<Bean> parseValueBeanSet(String input) {
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
      LinkedHashSet<Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedHashSet<Bean> collection=new LinkedHashSet<>();
        Bean item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=beanBindMap.parseOnJackson(jacksonParser);
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
   * for attribute valueEnumTypeSet serialization
   */
  protected String serializeValueEnumTypeSet(HashSet<EnumType> value) {
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
        jacksonSerializer.writeFieldName("valueEnumTypeSet");
        jacksonSerializer.writeStartArray();
        for (EnumType item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item.toString());
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
   * for attribute valueEnumTypeSet parsing
   */
  protected HashSet<EnumType> parseValueEnumTypeSet(String input) {
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
      HashSet<EnumType> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<EnumType> collection=new HashSet<>();
        EnumType item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
             {
              String tempEnum=jacksonParser.getText();
              item=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
            }
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
     * modifier for property value
     */
    public BindEditor putValue(String value) {
      editor.putString("value",value);

      return this;
    }

    /**
     * modifier for property valueByteSet
     */
    public BindEditor putValueByteSet(Set<Byte> value) {
      if (value!=null)  {
        String temp=serializeValueByteSet(value);
        editor.putString("value_byte_set",temp);
      }  else  {
        editor.remove("value_byte_set");
      }

      return this;
    }

    /**
     * modifier for property valueShortSet
     */
    public BindEditor putValueShortSet(HashSet<Short> value) {
      if (value!=null)  {
        String temp=serializeValueShortSet(value);
        editor.putString("value_short_set",temp);
      }  else  {
        editor.remove("value_short_set");
      }

      return this;
    }

    /**
     * modifier for property valueIntegerSet
     */
    public BindEditor putValueIntegerSet(LinkedHashSet<Integer> value) {
      if (value!=null)  {
        String temp=serializeValueIntegerSet(value);
        editor.putString("value_integer_set",temp);
      }  else  {
        editor.remove("value_integer_set");
      }

      return this;
    }

    /**
     * modifier for property valueStringSet
     */
    public BindEditor putValueStringSet(HashSet<String> value) {
      editor.putStringSet("value_string_set",value);

      return this;
    }

    /**
     * modifier for property valueCharacterSet
     */
    public BindEditor putValueCharacterSet(Set<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharacterSet(value);
        editor.putString("value_character_set",temp);
      }  else  {
        editor.remove("value_character_set");
      }

      return this;
    }

    /**
     * modifier for property valueFloatSet
     */
    public BindEditor putValueFloatSet(HashSet<Float> value) {
      if (value!=null)  {
        String temp=serializeValueFloatSet(value);
        editor.putString("value_float_set",temp);
      }  else  {
        editor.remove("value_float_set");
      }

      return this;
    }

    /**
     * modifier for property valueDoubleSet
     */
    public BindEditor putValueDoubleSet(HashSet<Double> value) {
      if (value!=null)  {
        String temp=serializeValueDoubleSet(value);
        editor.putString("value_double_set",temp);
      }  else  {
        editor.remove("value_double_set");
      }

      return this;
    }

    /**
     * modifier for property valueBigDecimalSet
     */
    public BindEditor putValueBigDecimalSet(HashSet<BigDecimal> value) {
      if (value!=null)  {
        String temp=serializeValueBigDecimalSet(value);
        editor.putString("value_big_decimal_set",temp);
      }  else  {
        editor.remove("value_big_decimal_set");
      }

      return this;
    }

    /**
     * modifier for property valueBeanSet
     */
    public BindEditor putValueBeanSet(LinkedHashSet<Bean> value) {
      if (value!=null)  {
        String temp=serializeValueBeanSet(value);
        editor.putString("value_bean_set",temp);
      }  else  {
        editor.remove("value_bean_set");
      }

      return this;
    }

    /**
     * modifier for property valueEnumTypeSet
     */
    public BindEditor putValueEnumTypeSet(HashSet<EnumType> value) {
      if (value!=null)  {
        String temp=serializeValueEnumTypeSet(value);
        editor.putString("value_enum_type_set",temp);
      }  else  {
        editor.remove("value_enum_type_set");
      }

      return this;
    }
  }
}

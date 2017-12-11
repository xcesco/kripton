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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
    // no typeName specified, using default shared preferences
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
    bean.id=prefs.getLong("id", bean.id);
    bean.value=prefs.getString("value", bean.value);
     {
      String temp=prefs.getString("value_byte_set", null);
      bean.valueByteSet=StringUtils.hasText(temp) ? parseValueByteSet(temp): null;
    }

     {
      String temp=prefs.getString("value_short_set", null);
      bean.valueShortSet=StringUtils.hasText(temp) ? parseValueShortSet(temp): null;
    }

     {
      String temp=prefs.getString("value_integer_set", null);
      bean.valueIntegerSet=StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null;
    }

     {
      String temp=prefs.getString("value_string_set", null);
      bean.valueStringSet=StringUtils.hasText(temp) ? parseValueStringSet(temp): null;
    }

     {
      String temp=prefs.getString("value_character_set", null);
      bean.valueCharacterSet=StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null;
    }

     {
      String temp=prefs.getString("value_float_set", null);
      bean.valueFloatSet=StringUtils.hasText(temp) ? parseValueFloatSet(temp): null;
    }

     {
      String temp=prefs.getString("value_double_set", null);
      bean.valueDoubleSet=StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null;
    }

     {
      String temp=prefs.getString("value_big_decimal_set", null);
      bean.valueBigDecimalSet=StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null;
    }

     {
      String temp=prefs.getString("value_bean_set", null);
      bean.valueBeanSet=StringUtils.hasText(temp) ? parseValueBeanSet(temp): null;
    }

     {
      String temp=prefs.getString("value_enum_type_set", null);
      bean.valueEnumTypeSet=StringUtils.hasText(temp) ? parseValueEnumTypeSet(temp): null;
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
    editor.putLong("id",bean.id);

    editor.putString("value",bean.value);

    if (bean.valueByteSet!=null)  {
      String temp=serializeValueByteSet(bean.valueByteSet);
      editor.putString("valueByteSet",temp);
    }  else  {
      editor.remove("valueByteSet");
    }

    if (bean.valueShortSet!=null)  {
      String temp=serializeValueShortSet(bean.valueShortSet);
      editor.putString("valueShortSet",temp);
    }  else  {
      editor.remove("valueShortSet");
    }

    if (bean.valueIntegerSet!=null)  {
      String temp=serializeValueIntegerSet(bean.valueIntegerSet);
      editor.putString("valueIntegerSet",temp);
    }  else  {
      editor.remove("valueIntegerSet");
    }

    if (bean.valueStringSet!=null)  {
      String temp=serializeValueStringSet(bean.valueStringSet);
      editor.putString("valueStringSet",temp);
    }  else  {
      editor.remove("valueStringSet");
    }

    if (bean.valueCharacterSet!=null)  {
      String temp=serializeValueCharacterSet(bean.valueCharacterSet);
      editor.putString("valueCharacterSet",temp);
    }  else  {
      editor.remove("valueCharacterSet");
    }

    if (bean.valueFloatSet!=null)  {
      String temp=serializeValueFloatSet(bean.valueFloatSet);
      editor.putString("valueFloatSet",temp);
    }  else  {
      editor.remove("valueFloatSet");
    }

    if (bean.valueDoubleSet!=null)  {
      String temp=serializeValueDoubleSet(bean.valueDoubleSet);
      editor.putString("valueDoubleSet",temp);
    }  else  {
      editor.remove("valueDoubleSet");
    }

    if (bean.valueBigDecimalSet!=null)  {
      String temp=serializeValueBigDecimalSet(bean.valueBigDecimalSet);
      editor.putString("valueBigDecimalSet",temp);
    }  else  {
      editor.remove("valueBigDecimalSet");
    }

    if (bean.valueBeanSet!=null)  {
      String temp=serializeValueBeanSet(bean.valueBeanSet);
      editor.putString("valueBeanSet",temp);
    }  else  {
      editor.remove("valueBeanSet");
    }

    if (bean.valueEnumTypeSet!=null)  {
      String temp=serializeValueEnumTypeSet(bean.valueEnumTypeSet);
      editor.putString("valueEnumTypeSet",temp);
    }  else  {
      editor.remove("valueEnumTypeSet");
    }


    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long id() {
    return prefs.getLong("id", defaultBean.id);
  }

  /**
   * read property value
   *
   * @return property value value
   */
  public String value() {
    return prefs.getString("value", defaultBean.value);
  }

  /**
   * read property valueByteSet
   *
   * @return property valueByteSet value
   */
  public Set<Byte> valueByteSet() {
    String temp=prefs.getString("value_byte_set", null);
    return StringUtils.hasText(temp) ? parseValueByteSet(temp): null;

  }

  /**
   * read property valueShortSet
   *
   * @return property valueShortSet value
   */
  public HashSet<Short> valueShortSet() {
    String temp=prefs.getString("value_short_set", null);
    return StringUtils.hasText(temp) ? parseValueShortSet(temp): null;

  }

  /**
   * read property valueIntegerSet
   *
   * @return property valueIntegerSet value
   */
  public LinkedHashSet<Integer> valueIntegerSet() {
    String temp=prefs.getString("value_integer_set", null);
    return StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null;

  }

  /**
   * read property valueStringSet
   *
   * @return property valueStringSet value
   */
  public HashSet<String> valueStringSet() {
    String temp=prefs.getString("value_string_set", null);
    return StringUtils.hasText(temp) ? parseValueStringSet(temp): null;

  }

  /**
   * read property valueCharacterSet
   *
   * @return property valueCharacterSet value
   */
  public Set<Character> valueCharacterSet() {
    String temp=prefs.getString("value_character_set", null);
    return StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null;

  }

  /**
   * read property valueFloatSet
   *
   * @return property valueFloatSet value
   */
  public HashSet<Float> valueFloatSet() {
    String temp=prefs.getString("value_float_set", null);
    return StringUtils.hasText(temp) ? parseValueFloatSet(temp): null;

  }

  /**
   * read property valueDoubleSet
   *
   * @return property valueDoubleSet value
   */
  public HashSet<Double> valueDoubleSet() {
    String temp=prefs.getString("value_double_set", null);
    return StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null;

  }

  /**
   * read property valueBigDecimalSet
   *
   * @return property valueBigDecimalSet value
   */
  public HashSet<BigDecimal> valueBigDecimalSet() {
    String temp=prefs.getString("value_big_decimal_set", null);
    return StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null;

  }

  /**
   * read property valueBeanSet
   *
   * @return property valueBeanSet value
   */
  public LinkedHashSet<Bean> valueBeanSet() {
    String temp=prefs.getString("value_bean_set", null);
    return StringUtils.hasText(temp) ? parseValueBeanSet(temp): null;

  }

  /**
   * read property valueEnumTypeSet
   *
   * @return property valueEnumTypeSet value
   */
  public HashSet<EnumType> valueEnumTypeSet() {
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
   * for attribute valueStringSet serialization
   */
  protected String serializeValueStringSet(HashSet<String> value) {
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
        jacksonSerializer.writeFieldName("valueStringSet");
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
   * for attribute valueStringSet parsing
   */
  protected HashSet<String> parseValueStringSet(String input) {
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
      HashSet<String> result=null;
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
        editor.putString("valueByteSet",temp);
      }  else  {
        editor.remove("valueByteSet");
      }

      return this;
    }

    /**
     * modifier for property valueShortSet
     */
    public BindEditor putValueShortSet(HashSet<Short> value) {
      if (value!=null)  {
        String temp=serializeValueShortSet(value);
        editor.putString("valueShortSet",temp);
      }  else  {
        editor.remove("valueShortSet");
      }

      return this;
    }

    /**
     * modifier for property valueIntegerSet
     */
    public BindEditor putValueIntegerSet(LinkedHashSet<Integer> value) {
      if (value!=null)  {
        String temp=serializeValueIntegerSet(value);
        editor.putString("valueIntegerSet",temp);
      }  else  {
        editor.remove("valueIntegerSet");
      }

      return this;
    }

    /**
     * modifier for property valueStringSet
     */
    public BindEditor putValueStringSet(HashSet<String> value) {
      if (value!=null)  {
        String temp=serializeValueStringSet(value);
        editor.putString("valueStringSet",temp);
      }  else  {
        editor.remove("valueStringSet");
      }

      return this;
    }

    /**
     * modifier for property valueCharacterSet
     */
    public BindEditor putValueCharacterSet(Set<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharacterSet(value);
        editor.putString("valueCharacterSet",temp);
      }  else  {
        editor.remove("valueCharacterSet");
      }

      return this;
    }

    /**
     * modifier for property valueFloatSet
     */
    public BindEditor putValueFloatSet(HashSet<Float> value) {
      if (value!=null)  {
        String temp=serializeValueFloatSet(value);
        editor.putString("valueFloatSet",temp);
      }  else  {
        editor.remove("valueFloatSet");
      }

      return this;
    }

    /**
     * modifier for property valueDoubleSet
     */
    public BindEditor putValueDoubleSet(HashSet<Double> value) {
      if (value!=null)  {
        String temp=serializeValueDoubleSet(value);
        editor.putString("valueDoubleSet",temp);
      }  else  {
        editor.remove("valueDoubleSet");
      }

      return this;
    }

    /**
     * modifier for property valueBigDecimalSet
     */
    public BindEditor putValueBigDecimalSet(HashSet<BigDecimal> value) {
      if (value!=null)  {
        String temp=serializeValueBigDecimalSet(value);
        editor.putString("valueBigDecimalSet",temp);
      }  else  {
        editor.remove("valueBigDecimalSet");
      }

      return this;
    }

    /**
     * modifier for property valueBeanSet
     */
    public BindEditor putValueBeanSet(LinkedHashSet<Bean> value) {
      if (value!=null)  {
        String temp=serializeValueBeanSet(value);
        editor.putString("valueBeanSet",temp);
      }  else  {
        editor.remove("valueBeanSet");
      }

      return this;
    }

    /**
     * modifier for property valueEnumTypeSet
     */
    public BindEditor putValueEnumTypeSet(HashSet<EnumType> value) {
      if (value!=null)  {
        String temp=serializeValueEnumTypeSet(value);
        editor.putString("valueEnumTypeSet",temp);
      }  else  {
        editor.remove("valueEnumTypeSet");
      }

      return this;
    }
  }
}

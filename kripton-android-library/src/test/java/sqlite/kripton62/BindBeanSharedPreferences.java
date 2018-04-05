/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
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

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for Bean.
 *
 * @see Bean
 */
public class BindBeanSharedPreferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindBeanSharedPreferences instance;

  /** working instance of bean. */
  private final Bean defaultBean;

  /** BeanBindMap. */
  private BeanBindMap beanBindMap = BinderUtils.mapperFor(Bean.class);

  /**
   * constructor.
   */
  private BindBeanSharedPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Bean();
  }

  /**
   * create an editor to modify shared preferences.
   *
   * @return the bind editor
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values.
   *
   * @return the bind bean shared preferences
   */
  public BindBeanSharedPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    Bean bean=new Bean();
    write(bean);
  }

  /**
   * read bean entirely.
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
      Set<String> temp=prefs.getStringSet("value_string_set", defaultBean.valueStringSet);
      bean.valueStringSet=new HashSet<String>(temp);
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
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("id",bean.id);

    editor.putString("value",bean.value);

    if (bean.valueByteSet!=null)  {
      String temp=serializeValueByteSet(bean.valueByteSet);
      editor.putString("value_byte_set",temp);
    }  else  {
      editor.remove("value_byte_set");
    }

    if (bean.valueShortSet!=null)  {
      String temp=serializeValueShortSet(bean.valueShortSet);
      editor.putString("value_short_set",temp);
    }  else  {
      editor.remove("value_short_set");
    }

    if (bean.valueIntegerSet!=null)  {
      String temp=serializeValueIntegerSet(bean.valueIntegerSet);
      editor.putString("value_integer_set",temp);
    }  else  {
      editor.remove("value_integer_set");
    }

    editor.putStringSet("value_string_set",bean.valueStringSet);

    if (bean.valueCharacterSet!=null)  {
      String temp=serializeValueCharacterSet(bean.valueCharacterSet);
      editor.putString("value_character_set",temp);
    }  else  {
      editor.remove("value_character_set");
    }

    if (bean.valueFloatSet!=null)  {
      String temp=serializeValueFloatSet(bean.valueFloatSet);
      editor.putString("value_float_set",temp);
    }  else  {
      editor.remove("value_float_set");
    }

    if (bean.valueDoubleSet!=null)  {
      String temp=serializeValueDoubleSet(bean.valueDoubleSet);
      editor.putString("value_double_set",temp);
    }  else  {
      editor.remove("value_double_set");
    }

    if (bean.valueBigDecimalSet!=null)  {
      String temp=serializeValueBigDecimalSet(bean.valueBigDecimalSet);
      editor.putString("value_big_decimal_set",temp);
    }  else  {
      editor.remove("value_big_decimal_set");
    }

    if (bean.valueBeanSet!=null)  {
      String temp=serializeValueBeanSet(bean.valueBeanSet);
      editor.putString("value_bean_set",temp);
    }  else  {
      editor.remove("value_bean_set");
    }

    if (bean.valueEnumTypeSet!=null)  {
      String temp=serializeValueEnumTypeSet(bean.valueEnumTypeSet);
      editor.putString("value_enum_type_set",temp);
    }  else  {
      editor.remove("value_enum_type_set");
    }


    editor.commit();
  }

  /**
   * read property id.
   *
   * @return property id value
   */
  public long id() {
    return prefs.getLong("id", defaultBean.id);
  }

  /**
   * read property value.
   *
   * @return property value value
   */
  public String value() {
    return prefs.getString("value", defaultBean.value);
  }

  /**
   * read property valueByteSet.
   *
   * @return property valueByteSet value
   */
  public Set<Byte> valueByteSet() {
    String temp=prefs.getString("value_byte_set", null);
    return StringUtils.hasText(temp) ? parseValueByteSet(temp): null;

  }

  /**
   * read property valueShortSet.
   *
   * @return property valueShortSet value
   */
  public HashSet<Short> valueShortSet() {
    String temp=prefs.getString("value_short_set", null);
    return StringUtils.hasText(temp) ? parseValueShortSet(temp): null;

  }

  /**
   * read property valueIntegerSet.
   *
   * @return property valueIntegerSet value
   */
  public LinkedHashSet<Integer> valueIntegerSet() {
    String temp=prefs.getString("value_integer_set", null);
    return StringUtils.hasText(temp) ? parseValueIntegerSet(temp): null;

  }

  /**
   * read property valueStringSet.
   *
   * @return property valueStringSet value
   */
  public HashSet<String> valueStringSet() {
    Set<String> temp=prefs.getStringSet("value_string_set", defaultBean.valueStringSet);
    return new HashSet<String>(temp);

  }

  /**
   * read property valueCharacterSet.
   *
   * @return property valueCharacterSet value
   */
  public Set<Character> valueCharacterSet() {
    String temp=prefs.getString("value_character_set", null);
    return StringUtils.hasText(temp) ? parseValueCharacterSet(temp): null;

  }

  /**
   * read property valueFloatSet.
   *
   * @return property valueFloatSet value
   */
  public HashSet<Float> valueFloatSet() {
    String temp=prefs.getString("value_float_set", null);
    return StringUtils.hasText(temp) ? parseValueFloatSet(temp): null;

  }

  /**
   * read property valueDoubleSet.
   *
   * @return property valueDoubleSet value
   */
  public HashSet<Double> valueDoubleSet() {
    String temp=prefs.getString("value_double_set", null);
    return StringUtils.hasText(temp) ? parseValueDoubleSet(temp): null;

  }

  /**
   * read property valueBigDecimalSet.
   *
   * @return property valueBigDecimalSet value
   */
  public HashSet<BigDecimal> valueBigDecimalSet() {
    String temp=prefs.getString("value_big_decimal_set", null);
    return StringUtils.hasText(temp) ? parseValueBigDecimalSet(temp): null;

  }

  /**
   * read property valueBeanSet.
   *
   * @return property valueBeanSet value
   */
  public LinkedHashSet<Bean> valueBeanSet() {
    String temp=prefs.getString("value_bean_set", null);
    return StringUtils.hasText(temp) ? parseValueBeanSet(temp): null;

  }

  /**
   * read property valueEnumTypeSet.
   *
   * @return property valueEnumTypeSet value
   */
  public HashSet<EnumType> valueEnumTypeSet() {
    String temp=prefs.getString("value_enum_type_set", null);
    return StringUtils.hasText(temp) ? parseValueEnumTypeSet(temp): null;

  }

  /**
   * for attribute valueByteSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueByteSet parsing.
   *
   * @param input the input
   * @return the sets the
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
   * for attribute valueShortSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueShortSet parsing.
   *
   * @param input the input
   * @return the hash set
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
   * for attribute valueIntegerSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueIntegerSet parsing.
   *
   * @param input the input
   * @return the linked hash set
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
   * for attribute valueCharacterSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueCharacterSet parsing.
   *
   * @param input the input
   * @return the sets the
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
   * for attribute valueFloatSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueFloatSet parsing.
   *
   * @param input the input
   * @return the hash set
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
   * for attribute valueDoubleSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueDoubleSet parsing.
   *
   * @param input the input
   * @return the hash set
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
   * for attribute valueBigDecimalSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueBigDecimalSet parsing.
   *
   * @param input the input
   * @return the hash set
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
   * for attribute valueBeanSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueBeanSet parsing.
   *
   * @param input the input
   * @return the linked hash set
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
   * for attribute valueEnumTypeSet serialization.
   *
   * @param value the value
   * @return the string
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
   * for attribute valueEnumTypeSet parsing.
   *
   * @param input the input
   * @return the hash set
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
   * get instance of shared preferences.
   *
   * @return the bind bean shared preferences
   */
  public static synchronized BindBeanSharedPreferences instance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
    }
    return instance;
  }

  /**
   * editor class for shared preferences.
   */
  public class BindEditor extends AbstractEditor {
    
    /**
     * Instantiates a new bind editor.
     */
    private BindEditor() {
    }

    /**
     * modifier for property id.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putId(long value) {
      editor.putLong("id",value);

      return this;
    }

    /**
     * modifier for property value.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValue(String value) {
      editor.putString("value",value);

      return this;
    }

    /**
     * modifier for property valueByteSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueShortSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueIntegerSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueStringSet.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueStringSet(HashSet<String> value) {
      editor.putStringSet("value_string_set",value);

      return this;
    }

    /**
     * modifier for property valueCharacterSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueFloatSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueDoubleSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueBigDecimalSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueBeanSet.
     *
     * @param value the value
     * @return the bind editor
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
     * modifier for property valueEnumTypeSet.
     *
     * @param value the value
     * @return the bind editor
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

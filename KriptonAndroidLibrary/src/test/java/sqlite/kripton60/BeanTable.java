package sqlite.kripton60;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.TimeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Character;
import java.lang.Exception;
import java.lang.Long;
import java.lang.String;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Entity <code>Bean</code> is associated to table <code>bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean
 */
public class BeanTable {
  /**
   * Costant represents name of table bean
   */
  public static final String TABLE_NAME = "bean";

  /**
   * <p>
   * DDL to create table bean
   * </p>
   *
   * <pre>CREATE TABLE bean (VALUE_BOOL_TYPE INTEGER, VALUE_BOOL INTEGER, VALUE_BYTE_TYPE INTEGER, VALUE_BYTE INTEGER, VALUE_SHORT_TYPE INTEGER, VALUE_SHORT INTEGER, VALUE_INT_TYPE INTEGER, VALUE_INT INTEGER, VALUE_STRING TEXT, VALUE_CHAR_TYPE INTEGER, VALUE_CHAR INTEGER, VALUE_FLOAT_TYPE REAL, VALUE_FLOAT REAL, VALUE_BIG_INTEGER TEXT, VALUE_BIG_DECIMAL TEXT, VALUE_ENUM_TYPE TEXT, VALUE_LONG_TYPE INTEGER, VALUE_LONG INTEGER, VALUE_DOUBLE_TYPE REAL, VALUE_DOUBLE REAL, VALUE_LOCALE TEXT, VALUE_CALENDAR TEXT, VALUE_DATE TEXT, VALUE_URL TEXT, VALUE_TIME TEXT, VALUE_CURRENCY TEXT, VALUE_TIME_ZONE TEXT, VALUE_TIME_LIST BLOB, VALUE_STRIN_LIST BLOB, VALUE_LONG_LIST BLOB, VALUE_BYTE_ARRAY BLOB, VALUE_LONG_TYPE_ARRAY BLOB, VALUE_LONG_ARRAY BLOB, VALUE_BEAN_ARRAY BLOB, VALUE_STRING_ARRAY BLOB, VALUE_CHAR_LIST BLOB, VALUE_CHAR_TYPE_ARRAY BLOB, VALUE_CHAR_ARRAY BLOB, VALUE_MAP_STRING_BEAN BLOB, VALUE_LINKED_MAP_STRING_BEAN BLOB, VALUE_SET_STRING BLOB, ID INTEGER PRIMARY KEY AUTOINCREMENT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean (VALUE_BOOL_TYPE INTEGER, VALUE_BOOL INTEGER, VALUE_BYTE_TYPE INTEGER, VALUE_BYTE INTEGER, VALUE_SHORT_TYPE INTEGER, VALUE_SHORT INTEGER, VALUE_INT_TYPE INTEGER, VALUE_INT INTEGER, VALUE_STRING TEXT, VALUE_CHAR_TYPE INTEGER, VALUE_CHAR INTEGER, VALUE_FLOAT_TYPE REAL, VALUE_FLOAT REAL, VALUE_BIG_INTEGER TEXT, VALUE_BIG_DECIMAL TEXT, VALUE_ENUM_TYPE TEXT, VALUE_LONG_TYPE INTEGER, VALUE_LONG INTEGER, VALUE_DOUBLE_TYPE REAL, VALUE_DOUBLE REAL, VALUE_LOCALE TEXT, VALUE_CALENDAR TEXT, VALUE_DATE TEXT, VALUE_URL TEXT, VALUE_TIME TEXT, VALUE_CURRENCY TEXT, VALUE_TIME_ZONE TEXT, VALUE_TIME_LIST BLOB, VALUE_STRIN_LIST BLOB, VALUE_LONG_LIST BLOB, VALUE_BYTE_ARRAY BLOB, VALUE_LONG_TYPE_ARRAY BLOB, VALUE_LONG_ARRAY BLOB, VALUE_BEAN_ARRAY BLOB, VALUE_STRING_ARRAY BLOB, VALUE_CHAR_LIST BLOB, VALUE_CHAR_TYPE_ARRAY BLOB, VALUE_CHAR_ARRAY BLOB, VALUE_MAP_STRING_BEAN BLOB, VALUE_LINKED_MAP_STRING_BEAN BLOB, VALUE_SET_STRING BLOB, ID INTEGER PRIMARY KEY AUTOINCREMENT);";

  /**
   * <p>
   * DDL to drop table bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean;";

  /**
   * Entity's property <code>valueBoolType</code> is associated to table column <code>VALUE_BOOL_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueBoolType
   */
  public static final String COLUMN_VALUE_BOOL_TYPE = "VALUE_BOOL_TYPE";

  /**
   * Entity's property <code>valueBool</code> is associated to table column <code>VALUE_BOOL</code>. This costant represents column name.
   *
   *  @see Bean#valueBool
   */
  public static final String COLUMN_VALUE_BOOL = "VALUE_BOOL";

  /**
   * Entity's property <code>valueByteType</code> is associated to table column <code>VALUE_BYTE_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueByteType
   */
  public static final String COLUMN_VALUE_BYTE_TYPE = "VALUE_BYTE_TYPE";

  /**
   * Entity's property <code>valueByte</code> is associated to table column <code>VALUE_BYTE</code>. This costant represents column name.
   *
   *  @see Bean#valueByte
   */
  public static final String COLUMN_VALUE_BYTE = "VALUE_BYTE";

  /**
   * Entity's property <code>valueShortType</code> is associated to table column <code>VALUE_SHORT_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueShortType
   */
  public static final String COLUMN_VALUE_SHORT_TYPE = "VALUE_SHORT_TYPE";

  /**
   * Entity's property <code>valueShort</code> is associated to table column <code>VALUE_SHORT</code>. This costant represents column name.
   *
   *  @see Bean#valueShort
   */
  public static final String COLUMN_VALUE_SHORT = "VALUE_SHORT";

  /**
   * Entity's property <code>valueIntType</code> is associated to table column <code>VALUE_INT_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueIntType
   */
  public static final String COLUMN_VALUE_INT_TYPE = "VALUE_INT_TYPE";

  /**
   * Entity's property <code>valueInt</code> is associated to table column <code>VALUE_INT</code>. This costant represents column name.
   *
   *  @see Bean#valueInt
   */
  public static final String COLUMN_VALUE_INT = "VALUE_INT";

  /**
   * Entity's property <code>valueString</code> is associated to table column <code>VALUE_STRING</code>. This costant represents column name.
   *
   *  @see Bean#valueString
   */
  public static final String COLUMN_VALUE_STRING = "VALUE_STRING";

  /**
   * Entity's property <code>valueCharType</code> is associated to table column <code>VALUE_CHAR_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueCharType
   */
  public static final String COLUMN_VALUE_CHAR_TYPE = "VALUE_CHAR_TYPE";

  /**
   * Entity's property <code>valueChar</code> is associated to table column <code>VALUE_CHAR</code>. This costant represents column name.
   *
   *  @see Bean#valueChar
   */
  public static final String COLUMN_VALUE_CHAR = "VALUE_CHAR";

  /**
   * Entity's property <code>valueFloatType</code> is associated to table column <code>VALUE_FLOAT_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueFloatType
   */
  public static final String COLUMN_VALUE_FLOAT_TYPE = "VALUE_FLOAT_TYPE";

  /**
   * Entity's property <code>valueFloat</code> is associated to table column <code>VALUE_FLOAT</code>. This costant represents column name.
   *
   *  @see Bean#valueFloat
   */
  public static final String COLUMN_VALUE_FLOAT = "VALUE_FLOAT";

  /**
   * Entity's property <code>valueBigInteger</code> is associated to table column <code>VALUE_BIG_INTEGER</code>. This costant represents column name.
   *
   *  @see Bean#valueBigInteger
   */
  public static final String COLUMN_VALUE_BIG_INTEGER = "VALUE_BIG_INTEGER";

  /**
   * Entity's property <code>valueBigDecimal</code> is associated to table column <code>VALUE_BIG_DECIMAL</code>. This costant represents column name.
   *
   *  @see Bean#valueBigDecimal
   */
  public static final String COLUMN_VALUE_BIG_DECIMAL = "VALUE_BIG_DECIMAL";

  /**
   * Entity's property <code>valueEnumType</code> is associated to table column <code>VALUE_ENUM_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueEnumType
   */
  public static final String COLUMN_VALUE_ENUM_TYPE = "VALUE_ENUM_TYPE";

  /**
   * Entity's property <code>valueLongType</code> is associated to table column <code>VALUE_LONG_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueLongType
   */
  public static final String COLUMN_VALUE_LONG_TYPE = "VALUE_LONG_TYPE";

  /**
   * Entity's property <code>valueLong</code> is associated to table column <code>VALUE_LONG</code>. This costant represents column name.
   *
   *  @see Bean#valueLong
   */
  public static final String COLUMN_VALUE_LONG = "VALUE_LONG";

  /**
   * Entity's property <code>valueDoubleType</code> is associated to table column <code>VALUE_DOUBLE_TYPE</code>. This costant represents column name.
   *
   *  @see Bean#valueDoubleType
   */
  public static final String COLUMN_VALUE_DOUBLE_TYPE = "VALUE_DOUBLE_TYPE";

  /**
   * Entity's property <code>valueDouble</code> is associated to table column <code>VALUE_DOUBLE</code>. This costant represents column name.
   *
   *  @see Bean#valueDouble
   */
  public static final String COLUMN_VALUE_DOUBLE = "VALUE_DOUBLE";

  /**
   * Entity's property <code>valueLocale</code> is associated to table column <code>VALUE_LOCALE</code>. This costant represents column name.
   *
   *  @see Bean#valueLocale
   */
  public static final String COLUMN_VALUE_LOCALE = "VALUE_LOCALE";

  /**
   * Entity's property <code>valueCalendar</code> is associated to table column <code>VALUE_CALENDAR</code>. This costant represents column name.
   *
   *  @see Bean#valueCalendar
   */
  public static final String COLUMN_VALUE_CALENDAR = "VALUE_CALENDAR";

  /**
   * Entity's property <code>valueDate</code> is associated to table column <code>VALUE_DATE</code>. This costant represents column name.
   *
   *  @see Bean#valueDate
   */
  public static final String COLUMN_VALUE_DATE = "VALUE_DATE";

  /**
   * Entity's property <code>valueUrl</code> is associated to table column <code>VALUE_URL</code>. This costant represents column name.
   *
   *  @see Bean#valueUrl
   */
  public static final String COLUMN_VALUE_URL = "VALUE_URL";

  /**
   * Entity's property <code>valueTime</code> is associated to table column <code>VALUE_TIME</code>. This costant represents column name.
   *
   *  @see Bean#valueTime
   */
  public static final String COLUMN_VALUE_TIME = "VALUE_TIME";

  /**
   * Entity's property <code>valueCurrency</code> is associated to table column <code>VALUE_CURRENCY</code>. This costant represents column name.
   *
   *  @see Bean#valueCurrency
   */
  public static final String COLUMN_VALUE_CURRENCY = "VALUE_CURRENCY";

  /**
   * Entity's property <code>valueTimeZone</code> is associated to table column <code>VALUE_TIME_ZONE</code>. This costant represents column name.
   *
   *  @see Bean#valueTimeZone
   */
  public static final String COLUMN_VALUE_TIME_ZONE = "VALUE_TIME_ZONE";

  /**
   * Entity's property <code>valueTimeList</code> is associated to table column <code>VALUE_TIME_LIST</code>. This costant represents column name.
   *
   *  @see Bean#valueTimeList
   */
  public static final String COLUMN_VALUE_TIME_LIST = "VALUE_TIME_LIST";

  /**
   * Entity's property <code>valueStrinList</code> is associated to table column <code>VALUE_STRIN_LIST</code>. This costant represents column name.
   *
   *  @see Bean#valueStrinList
   */
  public static final String COLUMN_VALUE_STRIN_LIST = "VALUE_STRIN_LIST";

  /**
   * Entity's property <code>valueLongList</code> is associated to table column <code>VALUE_LONG_LIST</code>. This costant represents column name.
   *
   *  @see Bean#valueLongList
   */
  public static final String COLUMN_VALUE_LONG_LIST = "VALUE_LONG_LIST";

  /**
   * Entity's property <code>valueByteArray</code> is associated to table column <code>VALUE_BYTE_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueByteArray
   */
  public static final String COLUMN_VALUE_BYTE_ARRAY = "VALUE_BYTE_ARRAY";

  /**
   * Entity's property <code>valueLongTypeArray</code> is associated to table column <code>VALUE_LONG_TYPE_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueLongTypeArray
   */
  public static final String COLUMN_VALUE_LONG_TYPE_ARRAY = "VALUE_LONG_TYPE_ARRAY";

  /**
   * Entity's property <code>valueLongArray</code> is associated to table column <code>VALUE_LONG_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueLongArray
   */
  public static final String COLUMN_VALUE_LONG_ARRAY = "VALUE_LONG_ARRAY";

  /**
   * Entity's property <code>valueBeanArray</code> is associated to table column <code>VALUE_BEAN_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueBeanArray
   */
  public static final String COLUMN_VALUE_BEAN_ARRAY = "VALUE_BEAN_ARRAY";

  /**
   * Entity's property <code>valueStringArray</code> is associated to table column <code>VALUE_STRING_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueStringArray
   */
  public static final String COLUMN_VALUE_STRING_ARRAY = "VALUE_STRING_ARRAY";

  /**
   * Entity's property <code>valueCharList</code> is associated to table column <code>VALUE_CHAR_LIST</code>. This costant represents column name.
   *
   *  @see Bean#valueCharList
   */
  public static final String COLUMN_VALUE_CHAR_LIST = "VALUE_CHAR_LIST";

  /**
   * Entity's property <code>valueCharTypeArray</code> is associated to table column <code>VALUE_CHAR_TYPE_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueCharTypeArray
   */
  public static final String COLUMN_VALUE_CHAR_TYPE_ARRAY = "VALUE_CHAR_TYPE_ARRAY";

  /**
   * Entity's property <code>valueCharArray</code> is associated to table column <code>VALUE_CHAR_ARRAY</code>. This costant represents column name.
   *
   *  @see Bean#valueCharArray
   */
  public static final String COLUMN_VALUE_CHAR_ARRAY = "VALUE_CHAR_ARRAY";

  /**
   * Entity's property <code>valueMapStringBean</code> is associated to table column <code>VALUE_MAP_STRING_BEAN</code>. This costant represents column name.
   *
   *  @see Bean#valueMapStringBean
   */
  public static final String COLUMN_VALUE_MAP_STRING_BEAN = "VALUE_MAP_STRING_BEAN";

  /**
   * Entity's property <code>valueLinkedMapStringBean</code> is associated to table column <code>VALUE_LINKED_MAP_STRING_BEAN</code>. This costant represents column name.
   *
   *  @see Bean#valueLinkedMapStringBean
   */
  public static final String COLUMN_VALUE_LINKED_MAP_STRING_BEAN = "VALUE_LINKED_MAP_STRING_BEAN";

  /**
   * Entity's property <code>valueSetString</code> is associated to table column <code>VALUE_SET_STRING</code>. This costant represents column name.
   *
   *  @see Bean#valueSetString
   */
  public static final String COLUMN_VALUE_SET_STRING = "VALUE_SET_STRING";

  /**
   * Entity's property <code>id</code> is associated to table column <code>ID</code>. This costant represents column name.
   *
   *  @see Bean#id
   */
  public static final String COLUMN_ID = "ID";

  /**
   * write
   */
  public static byte[] serializeValueTimeList(List<Time> value) {
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
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(TimeUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<Time> parseValueTimeList(byte[] input) {
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
            item=TimeUtils.read(jacksonParser.getText());
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
   * write
   */
  public static byte[] serializeValueStrinList(LinkedList<String> value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static LinkedList<String> parseValueStrinList(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueLongList(LinkedList<Long> value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static LinkedList<Long> parseValueLongList(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueByteArray(byte[] value) {
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
        jacksonSerializer.writeBinaryField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static byte[] parseValueByteArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueLongTypeArray(long[] value) {
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
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static long[] parseValueLongTypeArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueLongArray(Long[] value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Long[] parseValueLongArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueBeanArray(Bean[] value) {
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
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            context.mapperFor(Bean.class).serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Bean[] parseValueBeanArray(byte[] input) {
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
            item=context.mapperFor(Bean.class).parseOnJackson(context, wrapper);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeValueStringArray(String[] value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static String[] parseValueStringArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueCharList(LinkedList<Character> value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static LinkedList<Character> parseValueCharList(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueCharTypeArray(char[] value) {
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
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static char[] parseValueCharTypeArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueCharArray(Character[] value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Character[] parseValueCharArray(byte[] input) {
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
   * write
   */
  public static byte[] serializeValueMapStringBean(Map<String, Bean> value) {
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
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              context.mapperFor(Bean.class).serializeOnJackson(context, item.getValue(), wrapper);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Map<String, Bean> parseValueMapStringBean(byte[] input) {
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
            value=context.mapperFor(Bean.class).parseOnJackson(context, wrapper);
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
   * write
   */
  public static byte[] serializeValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
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
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              context.mapperFor(Bean.class).serializeOnJackson(context, item.getValue(), wrapper);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static LinkedHashMap<String, Bean> parseValueLinkedMapStringBean(byte[] input) {
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
            value=context.mapperFor(Bean.class).parseOnJackson(context, wrapper);
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
   * write
   */
  public static byte[] serializeValueSetString(Set<String> value) {
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
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Set<String> parseValueSetString(byte[] input) {
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
      Set<String> result=null;
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
}

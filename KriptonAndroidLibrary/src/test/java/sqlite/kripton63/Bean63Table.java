package sqlite.kripton63;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Byte;
import java.lang.Exception;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Entity <code>Bean63</code> is associated to table <code>bean63</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean63
 */
public class Bean63Table {
  /**
   * Costant represents typeName of table bean63
   */
  public static final String TABLE_NAME = "bean63";

  /**
   * <p>
   * DDL to create table bean63
   * </p>
   *
   * <pre>CREATE TABLE bean63 (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT, value_map_string_byte BLOB, value_map_enum_byte BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean63 (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT, value_map_string_byte BLOB, value_map_enum_byte BLOB);";

  /**
   * <p>
   * DDL to drop table bean63
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean63;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean63;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean63#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see Bean63#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>valueMapStringByte</code> is associated to table column <code>value_map_string_byte</code>. This costant represents column name.
   *
   *  @see Bean63#valueMapStringByte
   */
  public static final String COLUMN_VALUE_MAP_STRING_BYTE = "value_map_string_byte";

  /**
   * Entity's property <code>valueMapEnumByte</code> is associated to table column <code>value_map_enum_byte</code>. This costant represents column name.
   *
   *  @see Bean63#valueMapEnumByte
   */
  public static final String COLUMN_VALUE_MAP_ENUM_BYTE = "value_map_enum_byte";

  /**
   * for attribute valueMapStringByte serialization
   */
  public static byte[] serializeValueMapStringByte(Map<String, Byte> value) {
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
   * for attribute valueMapStringByte parsing
   */
  public static Map<String, Byte> parseValueMapStringByte(byte[] input) {
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
  public static byte[] serializeValueMapEnumByte(HashMap<EnumType, Byte> value) {
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
   * for attribute valueMapEnumByte parsing
   */
  public static HashMap<EnumType, Byte> parseValueMapEnumByte(byte[] input) {
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
}

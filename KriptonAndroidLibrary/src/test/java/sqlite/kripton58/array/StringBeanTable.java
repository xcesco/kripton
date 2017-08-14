package sqlite.kripton58.array;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;

/**
 * <p>
 * Entity <code>StringBean</code> is associated to table <code>string_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see StringBean
 */
public class StringBeanTable {
  /**
   * Costant represents typeName of table string_bean
   */
  public static final String TABLE_NAME = "string_bean";

  /**
   * <p>
   * DDL to create table string_bean
   * </p>
   *
   * <pre>CREATE TABLE string_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE string_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table string_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS string_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS string_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see StringBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see StringBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see StringBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";

  /**
   * write
   */
  public static byte[] serializeValue(String[] value) {
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
  public static String[] parseValue(byte[] input) {
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
  public static byte[] serializeValue2(String[] value) {
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
  public static String[] parseValue2(byte[] input) {
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
}

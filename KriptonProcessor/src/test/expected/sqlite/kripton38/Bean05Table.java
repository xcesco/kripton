package sqlite.kripton38;

import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.String;

/**
 * <p>
 * Entity <code>Bean05</code> is associated to table <code>ws_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean05
 */
public class Bean05Table {
  /**
   * Costant represents name of table ws_bean
   */
  public static final String TABLE_NAME = "ws_bean";

  /**
   * <p>
   * DDL to create table ws_bean
   * </p>
   *
   * <pre>CREATE TABLE ws_bean (pk INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, bean_type TEXT, text TEXT, content BLOB, creation_time TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE ws_bean (pk INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, bean_type TEXT, text TEXT, content BLOB, creation_time TEXT);";

  /**
   * <p>
   * DDL to drop table ws_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS ws_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS ws_bean;";

  /**
   * Entity's property <code>pk</code> is associated to table column <code>pk</code>. This costant represents column name.
   *
   *  @see Bean05#pk
   */
  public static final String COLUMN_PK = "pk";

  /**
   * Entity's property <code>number</code> is associated to table column <code>number</code>. This costant represents column name.
   *
   *  @see Bean05#number
   */
  public static final String COLUMN_NUMBER = "number";

  /**
   * Entity's property <code>beanType</code> is associated to table column <code>bean_type</code>. This costant represents column name.
   *
   *  @see Bean05#beanType
   */
  public static final String COLUMN_BEAN_TYPE = "bean_type";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean05#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column name.
   *
   *  @see Bean05#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>creationTime</code> is associated to table column <code>creation_time</code>. This costant represents column name.
   *
   *  @see Bean05#creationTime
   */
  public static final String COLUMN_CREATION_TIME = "creation_time";

  /**
   * write
   */
  public static byte[] serializeContent(byte[] value) {
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
        jacksonSerializer.writeBinaryField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static byte[] parseContent(byte[] input) {
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
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}

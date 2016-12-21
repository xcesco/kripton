package sqlite.kripton38;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
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
   * <pre>CREATE TABLE ws_bean (PK INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER INTEGER, BEAN_TYPE TEXT, TEXT TEXT, CONTENT BLOB, CREATION_TIME TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE ws_bean (PK INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER INTEGER, BEAN_TYPE TEXT, TEXT TEXT, CONTENT BLOB, CREATION_TIME TEXT);";

  /**
   * <p>
   * DDL to drop table ws_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS ws_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS ws_bean;";

  /**
   * Entity's property <code>pk</code> is associated to table column <code>PK</code>. This costant represents column name.
   *
   *  @see Bean05#pk
   */
  public static final String COLUMN_PK = "PK";

  /**
   * Entity's property <code>number</code> is associated to table column <code>NUMBER</code>. This costant represents column name.
   *
   *  @see Bean05#number
   */
  public static final String COLUMN_NUMBER = "NUMBER";

  /**
   * Entity's property <code>beanType</code> is associated to table column <code>BEAN_TYPE</code>. This costant represents column name.
   *
   *  @see Bean05#beanType
   */
  public static final String COLUMN_BEAN_TYPE = "BEAN_TYPE";

  /**
   * Entity's property <code>text</code> is associated to table column <code>TEXT</code>. This costant represents column name.
   *
   *  @see Bean05#text
   */
  public static final String COLUMN_TEXT = "TEXT";

  /**
   * Entity's property <code>content</code> is associated to table column <code>CONTENT</code>. This costant represents column name.
   *
   *  @see Bean05#content
   */
  public static final String COLUMN_CONTENT = "CONTENT";

  /**
   * Entity's property <code>creationTime</code> is associated to table column <code>CREATION_TIME</code>. This costant represents column name.
   *
   *  @see Bean05#creationTime
   */
  public static final String COLUMN_CREATION_TIME = "CREATION_TIME";

  /**
   * write
   */
  public static byte[] serializeContent(byte[] value) {
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
  public static byte[] parseContent(byte[] input) {
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
}

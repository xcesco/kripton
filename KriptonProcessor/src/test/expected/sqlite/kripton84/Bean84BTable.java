package sqlite.kripton84;

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
 * Entity <code>Bean84B</code> is associated to table <code>bean84_b</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean84B
 */
public class Bean84BTable {
  /**
   * Costant represents name of table bean84_b
   */
  public static final String TABLE_NAME = "bean84_b";

  /**
   * <p>
   * DDL to create table bean84_b
   * </p>
   *
   * <pre>CREATE TABLE bean84_b (id INTEGER PRIMARY KEY AUTOINCREMENT, column_bean BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean84_b (id INTEGER PRIMARY KEY AUTOINCREMENT, column_bean BLOB);";

  /**
   * <p>
   * DDL to drop table bean84_b
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean84_b;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean84_b;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean84B#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>columnBean</code> is associated to table column <code>column_bean</code>. This costant represents column name.
   *
   *  @see Bean84B#columnBean
   */
  public static final String COLUMN_COLUMN_BEAN = "column_bean";

  /**
   * write
   */
  public static byte[] serializeColumnBean(Bean84B2 value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(Bean84B2.class).serializeOnJackson(context, value, wrapper);
      }
      jacksonSerializer.flush();
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Bean84B2 parseColumnBean(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean84B2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(Bean84B2.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}

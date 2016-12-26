package sqlite.kripton84;

import com.abubusoft.kripton.AbstractContext;
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
   * Bean84B2BindMap */
  private static Bean84B2BindMap bean84B2BindMap;

  private static Bean84B2BindMap bean84B2BindMap() {
    if (bean84B2BindMap==null) {
      bean84B2BindMap=AbstractContext.mapperFor(Bean84B2.class);
    }
    return bean84B2BindMap;
  }

  /**
   * write
   */
  public static byte[] serializeColumnBean(Bean84B2 value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        bean84B2BindMap().serializeOnJackson(context, value, wrapper);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
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
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean84B2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=bean84B2BindMap().parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}

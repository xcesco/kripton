package sqlite.git104;

import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * <p>
 * Entity <code>Document</code> is associated to table <code>documents</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Document
 */
public class DocumentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table documents
   */
  public static final String TABLE_NAME = "documents";

  /**
   * <p>
   * DDL to create table documents
   * </p>
   *
   * <pre>CREATE TABLE documents (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data TEXT, file_name TEXT, info BLOB, secret TEXT, update_time TEXT NOT NULL);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE documents (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data TEXT, file_name TEXT, info BLOB, secret TEXT, update_time TEXT NOT NULL);";

  /**
   * <p>
   * DDL to drop table documents
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS documents;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS documents;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Document#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>data</code> is associated to table column <code>data</code>. This costant represents column name.
   *
   *  @see Document#data
   */
  public static final String COLUMN_DATA = "data";

  /**
   * Entity's property <code>fileName</code> is associated to table column <code>file_name</code>. This costant represents column name.
   *
   *  @see Document#fileName
   */
  public static final String COLUMN_FILE_NAME = "file_name";

  /**
   * Entity's property <code>info</code> is associated to table column <code>info</code>. This costant represents column name.
   *
   *  @see Document#info
   */
  public static final String COLUMN_INFO = "info";

  /**
   * Entity's property <code>secret</code> is associated to table column <code>secret</code>. This costant represents column name.
   *
   *  @see Document#secret
   */
  public static final String COLUMN_SECRET = "secret";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see Document#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * binder for type DocumentInfo
   */
  private static DocumentInfoBindMap documentInfoBindMap = BinderUtils.mapperFor(DocumentInfo.class);

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DATA, COLUMN_FILE_NAME, COLUMN_INFO, COLUMN_SECRET, COLUMN_UPDATE_TIME};

  /**
   * for attribute info serialization
   */
  public static byte[] serializeInfo(DocumentInfo value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        documentInfoBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute info parsing
   */
  public static DocumentInfo parseInfo(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      DocumentInfo __info = null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        __info = documentInfoBindMap.parseOnJackson(jacksonParser);
      }
      return __info;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * Columns array
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}

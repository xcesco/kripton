package sqlite.feature.relations.case4.model;

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
 * Entity <code>Channel</code> is associated to table <code>channel</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Channel
 */
public class ChannelTable implements SQLiteTable {
  /**
   * Costant represents typeName of table channel
   */
  public static final String TABLE_NAME = "channel";

  /**
   * <p>
   * DDL to create table channel
   * </p>
   *
   * <pre>CREATE TABLE channel (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, link TEXT, description TEXT, language TEXT, copyright TEXT, pub_date TEXT, last_build_date TEXT, image BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE channel (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, link TEXT, description TEXT, language TEXT, copyright TEXT, pub_date TEXT, last_build_date TEXT, image BLOB);";

  /**
   * <p>
   * DDL to drop table channel
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS channel;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS channel;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Channel#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Channel#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>link</code> is associated to table column <code>link</code>. This costant represents column name.
   *
   *  @see Channel#link
   */
  public static final String COLUMN_LINK = "link";

  /**
   * Entity's property <code>description</code> is associated to table column <code>description</code>. This costant represents column name.
   *
   *  @see Channel#description
   */
  public static final String COLUMN_DESCRIPTION = "description";

  /**
   * Entity's property <code>language</code> is associated to table column <code>language</code>. This costant represents column name.
   *
   *  @see Channel#language
   */
  public static final String COLUMN_LANGUAGE = "language";

  /**
   * Entity's property <code>copyright</code> is associated to table column <code>copyright</code>. This costant represents column name.
   *
   *  @see Channel#copyright
   */
  public static final String COLUMN_COPYRIGHT = "copyright";

  /**
   * Entity's property <code>pubDate</code> is associated to table column <code>pub_date</code>. This costant represents column name.
   *
   *  @see Channel#pubDate
   */
  public static final String COLUMN_PUB_DATE = "pub_date";

  /**
   * Entity's property <code>lastBuildDate</code> is associated to table column <code>last_build_date</code>. This costant represents column name.
   *
   *  @see Channel#lastBuildDate
   */
  public static final String COLUMN_LAST_BUILD_DATE = "last_build_date";

  /**
   * Entity's property <code>image</code> is associated to table column <code>image</code>. This costant represents column name.
   *
   *  @see Channel#image
   */
  public static final String COLUMN_IMAGE = "image";

  /**
   * ImageBindMap */
  private static ImageBindMap imageBindMap = BinderUtils.mapperFor(Image.class);

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_LINK, COLUMN_DESCRIPTION, COLUMN_LANGUAGE, COLUMN_COPYRIGHT, COLUMN_PUB_DATE, COLUMN_LAST_BUILD_DATE, COLUMN_IMAGE};

  /**
   * for attribute image serialization
   */
  public static byte[] serializeImage(Image value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        imageBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute image parsing
   */
  public static Image parseImage(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Image result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=imageBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
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

package sqlite.feature.kotlin.immutable;

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
 * Entity <code>Article</code> is associated to table <code>articles</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Article
 */
public class ArticleTable implements SQLiteTable {
  /**
   * Costant represents typeName of table articles
   */
  public static final String TABLE_NAME = "articles";

  /**
   * <p>
   * DDL to create table articles
   * </p>
   *
   * <pre>CREATE TABLE articles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, author TEXT, channel_id INTEGER, comments TEXT, description TEXT, guid TEXT UNIQUE NOT NULL, link TEXT, read INTEGER, thumbnail BLOB, title TEXT, FOREIGN KEY(channel_id) REFERENCES channels(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE articles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, author TEXT, channel_id INTEGER, comments TEXT, description TEXT, guid TEXT UNIQUE NOT NULL, link TEXT, read INTEGER, thumbnail BLOB, title TEXT, FOREIGN KEY(channel_id) REFERENCES channels(id));";

  /**
   * <p>
   * DDL to drop table articles
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS articles;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS articles;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Article#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>author</code> is associated to table column <code>author</code>. This costant represents column name.
   *
   *  @see Article#author
   */
  public static final String COLUMN_AUTHOR = "author";

  /**
   * Entity's property <code>channelId</code> is associated to table column <code>channel_id</code>. This costant represents column name.
   *
   *  @see Article#channelId
   */
  public static final String COLUMN_CHANNEL_ID = "channel_id";

  /**
   * Entity's property <code>comments</code> is associated to table column <code>comments</code>. This costant represents column name.
   *
   *  @see Article#comments
   */
  public static final String COLUMN_COMMENTS = "comments";

  /**
   * Entity's property <code>description</code> is associated to table column <code>description</code>. This costant represents column name.
   *
   *  @see Article#description
   */
  public static final String COLUMN_DESCRIPTION = "description";

  /**
   * Entity's property <code>guid</code> is associated to table column <code>guid</code>. This costant represents column name.
   *
   *  @see Article#guid
   */
  public static final String COLUMN_GUID = "guid";

  /**
   * Entity's property <code>link</code> is associated to table column <code>link</code>. This costant represents column name.
   *
   *  @see Article#link
   */
  public static final String COLUMN_LINK = "link";

  /**
   * Entity's property <code>read</code> is associated to table column <code>read</code>. This costant represents column name.
   *
   *  @see Article#read
   */
  public static final String COLUMN_READ = "read";

  /**
   * Entity's property <code>thumbnail</code> is associated to table column <code>thumbnail</code>. This costant represents column name.
   *
   *  @see Article#thumbnail
   */
  public static final String COLUMN_THUMBNAIL = "thumbnail";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Article#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * ThumbnailBindMap */
  private static ThumbnailBindMap thumbnailBindMap = BinderUtils.mapperFor(Thumbnail.class);

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AUTHOR, COLUMN_CHANNEL_ID, COLUMN_COMMENTS, COLUMN_DESCRIPTION, COLUMN_GUID, COLUMN_LINK, COLUMN_READ, COLUMN_THUMBNAIL, COLUMN_TITLE};

  /**
   * for attribute thumbnail serialization
   */
  public static byte[] serializeThumbnail(Thumbnail value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        thumbnailBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute thumbnail parsing
   */
  public static Thumbnail parseThumbnail(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Thumbnail __thumbnail=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        __thumbnail=thumbnailBindMap.parseOnJackson(jacksonParser);
      }
      return __thumbnail;
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

package sqlite.feature.relations.case4.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Article</code> is associated to table <code>article</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Article
 */
public class ArticleTable implements SQLiteTable {
  /**
   * Costant represents typeName of table article
   */
  public static final String TABLE_NAME = "article";

  /**
   * <p>
   * DDL to create table article
   * </p>
   *
   * <pre>CREATE TABLE article (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, author TEXT, channel_id INTEGER, comments TEXT, description TEXT, guid TEXT, link TEXT, title TEXT, FOREIGN KEY(channel_id) REFERENCES channel(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE article (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, author TEXT, channel_id INTEGER, comments TEXT, description TEXT, guid TEXT, link TEXT, title TEXT, FOREIGN KEY(channel_id) REFERENCES channel(id));";

  /**
   * <p>
   * DDL to drop table article
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS article;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS article;";

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
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Article#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AUTHOR, COLUMN_CHANNEL_ID, COLUMN_COMMENTS, COLUMN_DESCRIPTION, COLUMN_GUID, COLUMN_LINK, COLUMN_TITLE};

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

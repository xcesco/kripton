package sqlite.quickstart.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Post</code> is associated to table <code>post</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Post
 */
public class PostTable implements SQLiteTable {
  /**
   * Costant represents typeName of table post
   */
  public static final String TABLE_NAME = "post";

  /**
   * <p>
   * DDL to create table post
   * </p>
   *
   * <pre>CREATE TABLE post (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, body TEXT, title TEXT, user_id INTEGER, FOREIGN KEY(user_id) REFERENCES user(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE post (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, body TEXT, title TEXT, user_id INTEGER, FOREIGN KEY(user_id) REFERENCES user(id));";

  /**
   * <p>
   * DDL to drop table post
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS post;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS post;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Post#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>body</code> is associated to table column <code>body</code>. This costant represents column name.
   *
   *  @see Post#body
   */
  public static final String COLUMN_BODY = "body";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Post#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see Post#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BODY, COLUMN_TITLE, COLUMN_USER_ID};

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

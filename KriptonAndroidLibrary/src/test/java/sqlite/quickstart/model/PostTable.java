package sqlite.quickstart.model;

import java.lang.String;

/**
 * <p>
 * Entity <code>Post</code> is associated to table <code>post</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Post
 */
public class PostTable {
  /**
   * Costant represents typeName of table post
   */
  public static final String TABLE_NAME = "post";

  /**
   * <p>
   * DDL to create table post
   * </p>
   *
   * <pre>CREATE TABLE post (user_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, body TEXT, FOREIGN KEY(user_id) REFERENCES user(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE post (user_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, body TEXT, FOREIGN KEY(user_id) REFERENCES user(id));";

  /**
   * <p>
   * DDL to drop table post
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS post;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS post;";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column typeName.
   *
   *  @see Post#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Post#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column typeName.
   *
   *  @see Post#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>body</code> is associated to table column <code>body</code>. This costant represents column typeName.
   *
   *  @see Post#body
   */
  public static final String COLUMN_BODY = "body";
}

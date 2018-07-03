package sqlite.quickstart.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Comment</code> is associated to table <code>comment</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Comment
 */
public class CommentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table comment
   */
  public static final String TABLE_NAME = "comment";

  /**
   * <p>
   * DDL to create table comment
   * </p>
   *
   * <pre>CREATE TABLE comment (id INTEGER PRIMARY KEY AUTOINCREMENT, body TEXT, email TEXT, name TEXT, post_id INTEGER, FOREIGN KEY(post_id) REFERENCES post(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE comment (id INTEGER PRIMARY KEY AUTOINCREMENT, body TEXT, email TEXT, name TEXT, post_id INTEGER, FOREIGN KEY(post_id) REFERENCES post(id));";

  /**
   * <p>
   * DDL to drop table comment
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS comment;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS comment;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Comment#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>body</code> is associated to table column <code>body</code>. This costant represents column name.
   *
   *  @see Comment#body
   */
  public static final String COLUMN_BODY = "body";

  /**
   * Entity's property <code>email</code> is associated to table column <code>email</code>. This costant represents column name.
   *
   *  @see Comment#email
   */
  public static final String COLUMN_EMAIL = "email";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Comment#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>postId</code> is associated to table column <code>post_id</code>. This costant represents column name.
   *
   *  @see Comment#postId
   */
  public static final String COLUMN_POST_ID = "post_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BODY, COLUMN_EMAIL, COLUMN_NAME, COLUMN_POST_ID};

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

package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Message</code> is associated to table <code>message</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Message
 */
public class MessageTable implements SQLiteTable {
  /**
   * Costant represents typeName of table message
   */
  public static final String TABLE_NAME = "message";

  /**
   * <p>
   * DDL to create table message
   * </p>
   *
   * <pre>CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, receiver_id INTEGER, sender_id INTEGER, FOREIGN KEY(receiver_id) REFERENCES user(id), FOREIGN KEY(sender_id) REFERENCES user(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, receiver_id INTEGER, sender_id INTEGER, FOREIGN KEY(receiver_id) REFERENCES user(id), FOREIGN KEY(sender_id) REFERENCES user(id));";

  /**
   * <p>
   * DDL to drop table message
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS message;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS message;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Message#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column name.
   *
   *  @see Message#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>receiverId</code> is associated to table column <code>receiver_id</code>. This costant represents column name.
   *
   *  @see Message#receiverId
   */
  public static final String COLUMN_RECEIVER_ID = "receiver_id";

  /**
   * Entity's property <code>senderId</code> is associated to table column <code>sender_id</code>. This costant represents column name.
   *
   *  @see Message#senderId
   */
  public static final String COLUMN_SENDER_ID = "sender_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_CONTENT, COLUMN_RECEIVER_ID, COLUMN_SENDER_ID};

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

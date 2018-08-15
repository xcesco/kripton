package sqlite.kripton56.entities;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>MessageEntity</code> is associated to table <code>message</code>
 * This class represents table associated to entity.
 * </p>
 *  @see MessageEntity
 */
public class MessageEntityTable implements SQLiteTable {
  /**
   * Costant represents typeName of table message
   */
  public static final String TABLE_NAME = "message";

  /**
   * <p>
   * DDL to create table message
   * </p>
   *
   * <pre>CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, channel_id INTEGER, channel_uid TEXT, face_uid TEXT, owner_type TEXT, owner_uid TEXT, text TEXT, update_time INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, channel_id INTEGER, channel_uid TEXT, face_uid TEXT, owner_type TEXT, owner_uid TEXT, text TEXT, update_time INTEGER);";

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
   *  @see MessageEntity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>channelId</code> is associated to table column <code>channel_id</code>. This costant represents column name.
   *
   *  @see MessageEntity#channelId
   */
  public static final String COLUMN_CHANNEL_ID = "channel_id";

  /**
   * Entity's property <code>channelUid</code> is associated to table column <code>channel_uid</code>. This costant represents column name.
   *
   *  @see MessageEntity#channelUid
   */
  public static final String COLUMN_CHANNEL_UID = "channel_uid";

  /**
   * Entity's property <code>faceUid</code> is associated to table column <code>face_uid</code>. This costant represents column name.
   *
   *  @see MessageEntity#faceUid
   */
  public static final String COLUMN_FACE_UID = "face_uid";

  /**
   * Entity's property <code>ownerType</code> is associated to table column <code>owner_type</code>. This costant represents column name.
   *
   *  @see MessageEntity#ownerType
   */
  public static final String COLUMN_OWNER_TYPE = "owner_type";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>owner_uid</code>. This costant represents column name.
   *
   *  @see MessageEntity#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "owner_uid";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see MessageEntity#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see MessageEntity#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_CHANNEL_ID, COLUMN_CHANNEL_UID, COLUMN_FACE_UID, COLUMN_OWNER_TYPE, COLUMN_OWNER_UID, COLUMN_TEXT, COLUMN_UPDATE_TIME};

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

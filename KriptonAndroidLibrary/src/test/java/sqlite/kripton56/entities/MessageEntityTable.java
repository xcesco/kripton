package sqlite.kripton56.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>MessageEntity</code> is associated to table <code>message</code>
 * This class represents table associated to entity.
 * </p>
 *  @see MessageEntity
 */
public class MessageEntityTable {
  /**
   * Costant represents name of table message
   */
  public static final String TABLE_NAME = "message";

  /**
   * <p>
   * DDL to create table message
   * </p>
   *
   * <pre>CREATE TABLE message (ID INTEGER PRIMARY KEY AUTOINCREMENT, CHANNEL_ID INTEGER, OWNER_TYPE TEXT, FACE_UID TEXT, TEXT TEXT, OWNER_UID TEXT, CHANNEL_UID TEXT, UPDATE_TIME INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE message (ID INTEGER PRIMARY KEY AUTOINCREMENT, CHANNEL_ID INTEGER, OWNER_TYPE TEXT, FACE_UID TEXT, TEXT TEXT, OWNER_UID TEXT, CHANNEL_UID TEXT, UPDATE_TIME INTEGER);";

  /**
   * <p>
   * DDL to drop table message
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS message;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS message;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>ID</code>. This costant represents column name.
   *
   *  @see MessageEntity#id
   */
  public static final String COLUMN_ID = "ID";

  /**
   * Entity's property <code>channelId</code> is associated to table column <code>CHANNEL_ID</code>. This costant represents column name.
   *
   *  @see MessageEntity#channelId
   */
  public static final String COLUMN_CHANNEL_ID = "CHANNEL_ID";

  /**
   * Entity's property <code>ownerType</code> is associated to table column <code>OWNER_TYPE</code>. This costant represents column name.
   *
   *  @see MessageEntity#ownerType
   */
  public static final String COLUMN_OWNER_TYPE = "OWNER_TYPE";

  /**
   * Entity's property <code>faceUid</code> is associated to table column <code>FACE_UID</code>. This costant represents column name.
   *
   *  @see MessageEntity#faceUid
   */
  public static final String COLUMN_FACE_UID = "FACE_UID";

  /**
   * Entity's property <code>text</code> is associated to table column <code>TEXT</code>. This costant represents column name.
   *
   *  @see MessageEntity#text
   */
  public static final String COLUMN_TEXT = "TEXT";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>OWNER_UID</code>. This costant represents column name.
   *
   *  @see MessageEntity#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "OWNER_UID";

  /**
   * Entity's property <code>channelUid</code> is associated to table column <code>CHANNEL_UID</code>. This costant represents column name.
   *
   *  @see MessageEntity#channelUid
   */
  public static final String COLUMN_CHANNEL_UID = "CHANNEL_UID";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>UPDATE_TIME</code>. This costant represents column name.
   *
   *  @see MessageEntity#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "UPDATE_TIME";
}

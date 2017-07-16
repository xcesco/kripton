package sqlite.kripton51.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>MessageEntity</code> is associated to table <code>message_entity</code>
 * This class represents table associated to entity.
 * </p>
 *  @see MessageEntity
 */
public class MessageEntityTable {
  /**
   * Costant represents typeName of table message_entity
   */
  public static final String TABLE_NAME = "message_entity";

  /**
   * <p>
   * DDL to create table message_entity
   * </p>
   *
   * <pre>CREATE TABLE message_entity (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, channel_id INTEGER, owner_type TEXT, uid TEXT, face_uid TEXT, text TEXT, owner_uid TEXT, channel_uid TEXT, update_time INTEGER, type TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE message_entity (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, channel_id INTEGER, owner_type TEXT, uid TEXT, face_uid TEXT, text TEXT, owner_uid TEXT, channel_uid TEXT, update_time INTEGER, type TEXT);";

  /**
   * <p>
   * DDL to drop table message_entity
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS message_entity;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS message_entity;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>channelId</code> is associated to table column <code>channel_id</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#channelId
   */
  public static final String COLUMN_CHANNEL_ID = "channel_id";

  /**
   * Entity's property <code>ownerType</code> is associated to table column <code>owner_type</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#ownerType
   */
  public static final String COLUMN_OWNER_TYPE = "owner_type";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>faceUid</code> is associated to table column <code>face_uid</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#faceUid
   */
  public static final String COLUMN_FACE_UID = "face_uid";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>owner_uid</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "owner_uid";

  /**
   * Entity's property <code>channelUid</code> is associated to table column <code>channel_uid</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#channelUid
   */
  public static final String COLUMN_CHANNEL_UID = "channel_uid";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * Entity's property <code>type</code> is associated to table column <code>type</code>. This costant represents column typeName.
   *
   *  @see MessageEntity#type
   */
  public static final String COLUMN_TYPE = "type";
}

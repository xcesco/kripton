package sqlite.feature.async;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

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
   * <pre>CREATE TABLE channel (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, owner_uid TEXT, uid TEXT, update_time INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE channel (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, owner_uid TEXT, uid TEXT, update_time INTEGER);";

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
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Channel#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>owner_uid</code>. This costant represents column name.
   *
   *  @see Channel#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "owner_uid";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see Channel#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see Channel#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_OWNER_UID, COLUMN_UID, COLUMN_UPDATE_TIME};

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

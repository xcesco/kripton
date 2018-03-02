package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>UserDevice</code> is associated to table <code>user_2_device</code>
 * This class represents table associated to entity.
 * </p>
 *  @see UserDevice
 */
public class UserDeviceTable implements SQLiteTable {
  /**
   * Costant represents typeName of table user_2_device
   */
  public static final String TABLE_NAME = "user_2_device";

  /**
   * <p>
   * DDL to create table user_2_device
   * </p>
   *
   * <pre>CREATE TABLE user_2_device (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULL, device_id INTEGER NOT NULL, FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE, FOREIGN KEY(device_id) REFERENCES device(id) ON DELETE CASCADE); CREATE INDEX idx_user_2_device_user_id ON user_2_device(user_id); CREATE INDEX idx_user_2_device_device_id ON user_2_device(device_id); CREATE UNIQUE INDEX idx_user_2_device_0 on user_2_device (user_id, device_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user_2_device (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULL, device_id INTEGER NOT NULL, FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE, FOREIGN KEY(device_id) REFERENCES device(id) ON DELETE CASCADE); CREATE INDEX idx_user_2_device_user_id ON user_2_device(user_id); CREATE INDEX idx_user_2_device_device_id ON user_2_device(device_id); CREATE UNIQUE INDEX idx_user_2_device_0 on user_2_device (user_id, device_id);";

  /**
   * <p>
   * DDL to drop table user_2_device
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_user_2_device_user_id; DROP INDEX IF EXISTS idx_user_2_device_device_id; DROP INDEX IF EXISTS idx_user_2_device_1;DROP TABLE IF EXISTS user_2_device;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_user_2_device_user_id; DROP INDEX IF EXISTS idx_user_2_device_device_id; DROP INDEX IF EXISTS idx_user_2_device_1;DROP TABLE IF EXISTS user_2_device;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see UserDevice#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see UserDevice#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Entity's property <code>deviceId</code> is associated to table column <code>device_id</code>. This costant represents column name.
   *
   *  @see UserDevice#deviceId
   */
  public static final String COLUMN_DEVICE_ID = "device_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USER_ID, COLUMN_DEVICE_ID};

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

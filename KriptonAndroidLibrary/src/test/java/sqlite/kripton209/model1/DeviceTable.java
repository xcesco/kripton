package sqlite.kripton209.model1;

/**
 * <p>
 * Entity <code>Device</code> is associated to table <code>device</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Device
 */
public class DeviceTable {
  /**
   * Costant represents typeName of table device
   */
  public static final String TABLE_NAME = "device";

  /**
   * <p>
   * DDL to create table device
   * </p>
   *
   * <pre>CREATE TABLE device (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE device (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

  /**
   * <p>
   * DDL to drop table device
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS device;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS device;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Device#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Device#name
   */
  public static final String COLUMN_NAME = "name";
}

package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>SimpleAddressItem</code> is associated to table <code>simple_address_item</code>
 * This class represents table associated to entity.
 * </p>
 *  @see SimpleAddressItem
 */
public class SimpleAddressItemTable implements SQLiteTable {
  /**
   * Costant represents typeName of table simple_address_item
   */
  public static final String TABLE_NAME = "simple_address_item";

  /**
   * <p>
   * DDL to create table simple_address_item
   * </p>
   *
   * <pre>CREATE TABLE simple_address_item (id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT, city TEXT, name TEXT, phone INTEGER, state TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE simple_address_item (id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT, city TEXT, name TEXT, phone INTEGER, state TEXT);";

  /**
   * <p>
   * DDL to drop table simple_address_item
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS simple_address_item;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS simple_address_item;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>city</code> is associated to table column <code>city</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#city
   */
  public static final String COLUMN_CITY = "city";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>phone</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#phone
   */
  public static final String COLUMN_PHONE = "phone";

  /**
   * Entity's property <code>state</code> is associated to table column <code>state</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#state
   */
  public static final String COLUMN_STATE = "state";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_ADDRESS, COLUMN_CITY, COLUMN_NAME, COLUMN_PHONE, COLUMN_STATE};

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

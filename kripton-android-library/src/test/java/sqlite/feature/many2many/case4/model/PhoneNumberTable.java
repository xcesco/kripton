package sqlite.feature.many2many.case4.model;

import com.abubusoft.kripton.android.orm.SQLiteTable;

/**
 * <p>
 * Entity <code>PhoneNumber</code> is associated to table <code>phone_number</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PhoneNumber
 */
public class PhoneNumberTable implements SQLiteTable {
  /**
   * Costant represents typeName of table phone_number
   */
  public static final String TABLE_NAME = "phone_number";

  /**
   * <p>
   * DDL to create table phone_number
   * </p>
   *
   * <pre>CREATE TABLE phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, action TEXT, number TEXT, country_code TEXT, contact_name TEXT, contact_id TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, action TEXT, number TEXT, country_code TEXT, contact_name TEXT, contact_id TEXT);";

  /**
   * <p>
   * DDL to drop table phone_number
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS phone_number;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS phone_number;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PhoneNumber#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>action</code> is associated to table column <code>action</code>. This costant represents column name.
   *
   *  @see PhoneNumber#action
   */
  public static final String COLUMN_ACTION = "action";

  /**
   * Entity's property <code>number</code> is associated to table column <code>number</code>. This costant represents column name.
   *
   *  @see PhoneNumber#number
   */
  public static final String COLUMN_NUMBER = "number";

  /**
   * Entity's property <code>countryCode</code> is associated to table column <code>country_code</code>. This costant represents column name.
   *
   *  @see PhoneNumber#countryCode
   */
  public static final String COLUMN_COUNTRY_CODE = "country_code";

  /**
   * Entity's property <code>contactName</code> is associated to table column <code>contact_name</code>. This costant represents column name.
   *
   *  @see PhoneNumber#contactName
   */
  public static final String COLUMN_CONTACT_NAME = "contact_name";

  /**
   * Entity's property <code>contactId</code> is associated to table column <code>contact_id</code>. This costant represents column name.
   *
   *  @see PhoneNumber#contactId
   */
  public static final String COLUMN_CONTACT_ID = "contact_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_ACTION, COLUMN_NUMBER, COLUMN_COUNTRY_CODE, COLUMN_CONTACT_NAME, COLUMN_CONTACT_ID};

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
